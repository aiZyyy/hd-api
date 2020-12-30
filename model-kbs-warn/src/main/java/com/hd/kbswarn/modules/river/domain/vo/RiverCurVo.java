package com.hd.kbswarn.modules.river.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * ClassName RiverCurVo
 * Description
 * Create by ZY
 * Date 2020/11/30 9:13
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiverCurVo {
    private Integer stCount;
    private Integer zWarnCount;
    private Integer zWarnLevel1Count;
    private Integer zWarnLevel2Count;
    private Integer zWarnLevel3Count;
    private Integer qWarnCount;
    private Integer qWarnLevel1Count;
    private Integer qWarnLevel2Count;
    private Integer qWarnLevel3Count;
    private List<RiverCurListVo> riverCurListVos;
}
