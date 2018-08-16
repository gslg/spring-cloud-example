package com.cloud.common.util;

import java.util.UUID;

/**
 * UUID 生成器
 */
public class UuidUtil {
    public static String uuid32(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
