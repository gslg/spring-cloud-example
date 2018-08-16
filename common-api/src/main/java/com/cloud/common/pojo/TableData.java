package com.cloud.common.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TableData<T> {

    private Long total;

    private List<T> rows;

}
