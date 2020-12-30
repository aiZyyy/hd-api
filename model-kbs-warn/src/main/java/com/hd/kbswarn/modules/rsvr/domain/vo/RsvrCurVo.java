package com.hd.kbswarn.modules.rsvr.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName RsvrCurVo
 * Description
 * Create by ZY
 * Date 2020/11/30 9:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RsvrCurVo {
    /**
     * 水库水情实时列表
     */
    private List<RsvrCurListVo> rsvrCurListVos;
    /**
     * 测站总数量
     */
    private Integer stCount;
    /**
     * 库上预警总数量
     */
    private Integer rzWarnCount;
    /**
     * 库下预警总数量
     */
    private Integer blrzWarnCount;
    /**
     * 入库流量预警总数量
     */
    private Integer inqWarnCount;
    /**
     * 出库流量总数量
     */
    private Integer otqWarnCount;

    /**
     * 库上预警1~3总数量
     */
    private Integer rzWarnLevel1Count;
    private Integer rzWarnLevel2Count;
    private Integer rzWarnLevel3Count;

    /**
     * 库下预警1~3总数量
     */
    private Integer blrzWarnLevel1Count;
    private Integer blrzWarnLevel2Count;
    private Integer blrzWarnLevel3Count;

    /**
     * 入库流量预警1~3总数量
     */
    private Integer inqWarnLevel1Count;
    private Integer inqWarnLevel2Count;
    private Integer inqWarnLevel3Count;

    /**
     * 出库流量预警1~3总数量
     */
    private Integer otqWarnLevel1Count;
    private Integer otqWarnLevel2Count;
    private Integer otqWarnLevel3Count;

}
