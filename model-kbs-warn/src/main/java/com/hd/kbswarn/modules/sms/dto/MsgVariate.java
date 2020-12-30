package com.hd.kbswarn.modules.sms.dto;

/**
 * 短信变量实体
 *
 * @author suoyasong
 * @Date：2019-01-05
 */
public class MsgVariate {

    /**
     * 名称
     */
    private String stnm;
    /**
     * 时间
     */
    private String tm;

    public String getStnm() {
        return stnm;
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public MsgVariate() {
    }

    public MsgVariate(String stnm, String tm) {
        this.stnm = stnm;
        this.tm = tm;
    }

}
