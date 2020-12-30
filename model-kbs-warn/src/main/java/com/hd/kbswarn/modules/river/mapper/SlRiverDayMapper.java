package com.hd.kbswarn.modules.river.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.hd.kbswarn.modules.river.domain.entity.SlRiverDay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 河道水情日极值
 * @Author: FishCoder
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Repository
public interface SlRiverDayMapper extends BaseMapper<SlRiverDay> {

}
