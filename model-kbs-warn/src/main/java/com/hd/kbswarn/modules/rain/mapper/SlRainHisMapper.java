package com.hd.kbswarn.modules.rain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainHis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 雨量历史
 * @Author: FishCoder
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Repository
public interface SlRainHisMapper extends BaseMapper<SlRainHis> {

}
