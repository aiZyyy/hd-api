package com.hd.kbswarn.modules.sms.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xlhua
 * @since 2020-07-29
 */
@Data
@TableName("sms_answer")
public class SmsAnswer extends Model<SmsAnswer> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.UUID)
    private Integer id;
    private String phone;
    /**
     * 回复内容
     */
    private String answer;
    /**
     * 回复时间
     */
    private Date time;
    /**
     * 存短信收到状态
     */
    private String status;
    private String expand1;
    private String expand2;
    private String expand3;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
