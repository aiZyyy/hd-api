package com.hd.kbswarn.modules.rain.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName RainCurVo
 * Description
 * Create by ZY
 * Date 2020/12/1 9:51
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RainCurVo {
    private List<RainCurListVo> rainCurListVos;
    private Integer stCount;
    private Integer rainWarnCount;
    private Integer rainWarnLevel1Count;
    private Integer rainWarnLevel2Count;
    private Integer rainWarnLevel3Count;
}
