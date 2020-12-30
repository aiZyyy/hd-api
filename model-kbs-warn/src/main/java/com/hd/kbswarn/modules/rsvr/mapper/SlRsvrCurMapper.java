package com.hd.kbswarn.modules.rsvr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.kbswarn.modules.rsvr.domain.entity.SlRsvrCur;
import com.hd.kbswarn.modules.rsvr.domain.vo.RsvrCurListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 水库水情实时
 * @Author: FishCoder
 * @Date: 2020-11-17
 * @Version: V1.0
 */
@Repository
public interface SlRsvrCurMapper extends BaseMapper<SlRsvrCur> {

    List<RsvrCurListVo> homeList(@Param("item") HashMap<String, Object> map);
}
