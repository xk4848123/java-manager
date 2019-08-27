package com.nidecai.managerndc.service;

import com.nidecai.managerndc.ExceptionHandle.BusinessException;

import java.util.List; /**
 * @author river
 * @title: CloseUpShop
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/8/2613:18
 */
public interface CloseUpShopService  {

    void closeShop() throws BusinessException;
}
