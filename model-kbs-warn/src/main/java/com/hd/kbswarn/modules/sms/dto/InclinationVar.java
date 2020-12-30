package com.hd.kbswarn.modules.sms.dto;

import lombok.Data;

/**
 * 倾斜度短信变量实体
 *
 * @author suoyasong
 * @Date：2019-01-05
 */
@Data
public class InclinationVar {

    /**
     * 名称
     */
    private String name;
    /**
     * 时间
     */
    private String time;

    /**
     * x倾角
     */
    private String xaxis;

    /**
     * y倾角
     */
    private String yaxis;

    public InclinationVar() {
    }

    public InclinationVar(String name, String time, String xaxis, String yaxis) {
        this.name = name;
        this.time = time;
        this.xaxis = xaxis;
        this.yaxis = yaxis;
    }

}
