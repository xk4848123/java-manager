package com.nidecai.managerndc.service;

import com.github.pagehelper.PageInfo;
import com.nidecai.managerndc.entity.Cvorder;
import com.nidecai.managerndc.entity.OrderAddress;
import com.nidecai.managerndc.entity.RiderUser;

import java.util.List;

/**
 * @author river
 * @title: CvorderService
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/6/2414:55
 */

public interface CvorderService {
    PageInfo<Cvorder> getOrderList(Cvorder cvorder, int page, int size);

    Cvorder getOneById(int id);

    int deleteId(int id);

    int deleteIds(List<Integer> cvorderList);

    int updateRiderUser(int ridUserId,int cvOrderId);
}
