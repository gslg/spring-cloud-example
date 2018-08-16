package com.cloud.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 手机短信消息模板
 */
@AllArgsConstructor
@Getter
@Setter
public class SMSTemplate implements Serializable {

    /**手机号*/
    private String phoneNumber;
    /**
     * 文本
     */
    private String text;

    /**
     * 类型(通道)
     */
    private String type;


}
