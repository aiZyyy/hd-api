package org.jeecg.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import com.hd.common.api.vo.Result;
import com.hd.common.constant.CacheConstant;
import com.hd.common.util.RedisUtil;
import org.jeecg.modules.system.entity.SlAddressBook;
import org.jeecg.modules.system.entity.SysDepart;
import org.jeecg.modules.system.mapper.SlAddressBookMapper;
import org.jeecg.modules.system.mapper.SysDepartMapper;
import org.jeecg.modules.system.service.ISlAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description: 通讯录管理
 * @Author: FishCoder
 * @Date: 2020-12-03
 * @Version: V1.0
 */
@Service
public class SlAddressBookServiceImpl extends ServiceImpl<SlAddressBookMapper, SlAddressBook> implements ISlAddressBookService {

    @Autowired
    private SysDepartMapper sysDepartMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result<?> queryPageList(IPage<SlAddressBook> pageList, HttpServletRequest req) {
        String name = req.getParameter("name");
        String position = req.getParameter("position");
        String unit = req.getParameter("unit");
        String depIds = req.getParameter("depId");
        QueryWrapper<SlAddressBook> addressBookQueryWrapper = new QueryWrapper<>();
        addressBookQueryWrapper.lambda()
                .orderByDesc(SlAddressBook::getId);
        List<String> idList = new ArrayList<>();
        if (StringUtils.isNotBlank(depIds)) {
            idList.add(depIds);
            this.checkChildrenExists(depIds, idList);
            addressBookQueryWrapper.lambda().in(SlAddressBook::getDepId, idList);
        }
        if (StringUtils.isNotBlank(name)) {
            addressBookQueryWrapper.lambda().eq(SlAddressBook::getName, name);
        }
        if (StringUtils.isNotBlank(position)) {
            addressBookQueryWrapper.lambda().eq(SlAddressBook::getPosition, position);
        }
        if (StringUtils.isNotBlank(unit)) {
            addressBookQueryWrapper.lambda().eq(SlAddressBook::getUnit, unit);
        }
        IPage<SlAddressBook> page = this.baseMapper.selectPage(pageList, addressBookQueryWrapper);
        List<SlAddressBook> records = page.getRecords();
        if (CollectionUtils.isNotEmpty(records)) {
            records.stream().forEach(item -> {
                String depId = item.getDepId();
                if (redisUtil.hasKey(CacheConstant.SYS_DEP_AB_CACHE + depId)) {
                    item.setDepTreeName(redisUtil.get(CacheConstant.SYS_DEP_AB_CACHE + depId).toString());
                } else {
                    ArrayList<String> depNames = new ArrayList<>();
                    getSysDeps(depNames, depId);
                    StringBuffer sb = new StringBuffer();
                    for (int i = depNames.size() - 1; i >= 0; i--) {
                        if (i == depNames.size() - 1) {
                            sb.append(depNames.get(i));
                        } else {
                            sb.append("/").append(depNames.get(i));
                        }
                    }
                    item.setDepTreeName(sb.toString());
                    redisUtil.set(CacheConstant.SYS_DEP_AB_CACHE + depId, sb.toString(), 60 * 60 * 12 * 7);
                }
            });
            page.setRecords(records);
            return Result.OK(page);
        } else {
            return Result.OK(pageList);
        }

    }

    private void getSysDeps(List<String> depNames, String depId) {
        SysDepart sysDepart = sysDepartMapper.selectById(depId);
        if (Objects.nonNull(sysDepart)) {
            String departName = sysDepart.getDepartName();
            depNames.add(departName);
            String parentId = sysDepart.getParentId();
            if (StringUtils.isNotBlank(parentId)) {
                getSysDeps(depNames, parentId);
            }
        }
    }

    private void checkChildrenExists(String id, List<String> idList) {
        LambdaQueryWrapper<SysDepart> query = new LambdaQueryWrapper<SysDepart>();
        query.eq(SysDepart::getParentId, id);
        List<SysDepart> departList = sysDepartMapper.selectList(query);
        if (departList != null && departList.size() > 0) {
            for (SysDepart depart : departList) {
                idList.add(depart.getId());
                this.checkChildrenExists(depart.getId(), idList);
            }
        }
    }
}
