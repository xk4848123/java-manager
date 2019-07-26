package com.nidecai.managerndc.common.annoation;

import java.lang.annotation.*;

/**
 * @author river
 * @title: ConvenientStore
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/6/2017:58
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConvenientStore {

    //定义一个ConvenientStore的属性
    String value() default  "";

}
