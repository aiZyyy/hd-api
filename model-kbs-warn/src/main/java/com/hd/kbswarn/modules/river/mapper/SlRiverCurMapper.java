package com.hd.kbswarn.modules.river.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hd.kbswarn.modules.river.domain.entity.SlRiverCur;
import com.hd.kbswarn.modules.river.domain.vo.RiverCurListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 河道水情实时表
 * @Author: FishCoder
 * @Date: 2020-11-20
 * @Version: V1.0
 */
@Repository
public interface SlRiverCurMapper extends BaseMapper<SlRiverCur> {

    List<RiverCurListVo> homeList(@Param("item") HashMap<String, Object> map);
}
