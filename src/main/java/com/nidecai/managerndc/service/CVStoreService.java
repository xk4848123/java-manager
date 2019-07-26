package com.nidecai.managerndc.service;


import java.util.List;

/**
 * @author river
 * @title: CVStoreService
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/6/2114:08
 */
public interface CVStoreService {
    //获取token
    List<String> getPermissionsByToken(String loginToken);

}
