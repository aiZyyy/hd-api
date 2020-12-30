package com.hd.kbswarn.modules.rain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.kbswarn.modules.rain.domain.entity.SlRainCur;
import com.hd.kbswarn.modules.rain.domain.vo.RainCurListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 降雨实时
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
@Repository
public interface SlRainCurMapper extends BaseMapper<SlRainCur> {

    List<RainCurListVo> homeList(@Param("item") HashMap<String, Object> map);
}
