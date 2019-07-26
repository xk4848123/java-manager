package com.nidecai.managerndc.common.entitycommon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author river
 * @title: ResultDTO
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/6/2016:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResultDTO<T> {
    private static final long serialVersionUID = 197018972999527001L;

    private Boolean flag;

    private T data;

    private String msg;

    private int code;

    private Integer total;


}
