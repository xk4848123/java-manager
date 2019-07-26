package com.nidecai.managerndc.common.codeutil;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author river
 * @title: CommonMessageEnum
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/6/2016:36
 */
@AllArgsConstructor
public enum CommonMessageEnum {

    /**
     * 响应消息枚举列表
     */
    SUCCESS("通用成功", 10000),
    FAIL("通用失败", 10001),
    PARAM_LOST("参数缺失", 10008),
    USER_IS_NULL("用户不存在", 11001),
    SERVERERR("服务异常", 20000),
    NO_PERMISION("您没有权限执行此操作", 50000),
    ;

    private @Getter
    String msg;
    private @Getter
    int code;

}
