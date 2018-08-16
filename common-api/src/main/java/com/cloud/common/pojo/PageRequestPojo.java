package com.cloud.common.pojo;


import lombok.Getter;
import lombok.Setter;

/**
 * 基础查询参数实体
 */
@Getter
@Setter
public class PageRequestPojo {

    protected Integer pageIndex;

    protected Integer pageSize;

}
