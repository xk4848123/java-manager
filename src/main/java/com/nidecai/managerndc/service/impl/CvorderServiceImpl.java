package com.nidecai.managerndc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nidecai.managerndc.entity.*;
import com.nidecai.managerndc.mapper.*;
import com.nidecai.managerndc.service.CvorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author river
 * @title: CvorderServiceImpl
 * @projectName manager-ndc
 * @description: 便利店订单列表显示
 * @date 2019/6/2414:59
 */
@Service
public class CvorderServiceImpl implements CvorderService {
    @Autowired
    private CvorderMapper cvorderMapper;
    @Autowired
    private OrderAddressMapper orderAddressMapper;
    @Autowired
    private RiderUserMapper riderUserMapper;
    @Autowired
    private CvuserOrderMapper cvuserOrderMapper;
    @Autowired
    private CvstoreMapper cvstoreMapper;

    @Override
    public PageInfo<Cvorder> getOrderList(Cvorder cvorder, int page, int size) {
        PageHelper.startPage(page, size);
        List<Cvorder> cvorderList = cvorderMapper.select(cvorder);
        for (Cvorder cvCvorder : cvorderList) {
            Example exampleAddress = new Example(OrderAddress.class);
            Example.Criteria criteriaAddress = exampleAddress.createCriteria();
            criteriaAddress.andEqualTo("riderSn",cvCvorder.getOrderSn());
            List<OrderAddress> orderAddresses = orderAddressMapper.selectByExample(exampleAddress);
            Example exampleRiderUser = new Example(RiderUser.class);
            Example.Criteria criteriaRiderUser = exampleRiderUser.createCriteria();
            criteriaRiderUser.andEqualTo("rid",cvCvorder.getRid());
            List<RiderUser> riderUsers = riderUserMapper.selectByExample(exampleRiderUser);
            Example exampleCvuserOrder = new Example(CvuserOrder.class);
            Example.Criteria criteriaCvuserOrder = exampleCvuserOrder.createCriteria();
            criteriaCvuserOrder.andEqualTo("orderSn",cvCvorder.getOrderSn());
            List<CvuserOrder> cvuserOrders = cvuserOrderMapper.selectByExample(exampleCvuserOrder);
            for (OrderAddress address : orderAddresses) {
                for (RiderUser cVriderUser : riderUsers) {
                    for (CvuserOrder cvuserOrder : cvuserOrders) {
                          cvCvorder.setOrderAddress(address);
                          cvCvorder.setRiderUser(cVriderUser);
                          cvCvorder.setCvuserOrder(cvuserOrder);
                    }
                }
            }
        }
        return new PageInfo<Cvorder>(cvorderList);
    }

    @Override
    public Cvorder getOneById(int id) {
        Cvorder cvorder = cvorderMapper.selectByPrimaryKey(id);
        int rid = cvorder.getRid();
        if (cvorder.getDeliverModel()!=0&&cvorder.getDeliverModel()!=1&&cvorder.getOrderStatus()!=0) {
            Example example = new Example(RiderUser.class);
            Example.Criteria criteriaRid = example.createCriteria();
            if (cvorder != null && rid != 0) {
                criteriaRid.andEqualTo("marketid", rid);
            }
            List<RiderUser> riderUsers = riderUserMapper.selectByExample(example);
            for (RiderUser riderUser : riderUsers) {
                cvorder.setRiderUser(riderUser);
            }
            return cvorder;
        }
        return cvorder;
    }

    @Override
    public int deleteId(int id) {
        Cvorder cvorder = cvorderMapper.selectByPrimaryKey(id);
        if (cvorder != null && cvorder.getOrderSn() != null) {
            orderAddressMapper.deleteByPrimaryKey(cvorder.getOrderSn());
        }
        if (cvorder != null && cvorder.getUoid() != null) {
            cvuserOrderMapper.deleteByPrimaryKey(cvorder.getUoid());
        }
        if (cvorder != null && cvorder.getSid() != null) {
            Example example = new Example(Cvstore.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("sid", cvorder.getSid());
            List<Cvstore> cvstores = cvstoreMapper.selectByExample(example);
            for (Cvstore cvstore : cvstores) {
                Integer cvstoreId = cvorder.getId();
                cvstoreMapper.deleteByPrimaryKey(cvstoreId);
            }
        }
        return cvorderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteIds(List<Integer> cvorderList) {
        for (Integer cvorderId : cvorderList) {
            Cvorder cvorder = cvorderMapper.selectByPrimaryKey(cvorderId);
            if (cvorder != null && cvorder.getOrderSn() != null) {
                orderAddressMapper.deleteByPrimaryKey(cvorder.getOrderSn());
            }
            if (cvorder != null && cvorder.getUoid() != null) {
                cvuserOrderMapper.deleteByPrimaryKey(cvorder.getUoid());
            }
            if (cvorder != null && cvorder.getSid() != null) {
                Example example = new Example(Cvstore.class);
                Example.Criteria criteria = example.createCriteria();
                List<Cvstore> cvstores = cvstoreMapper.selectByExample(cvorder.getSid());
                for (Cvstore cvstore : cvstores) {
                    Integer cvstoreId = cvorder.getId();
                    cvstoreMapper.deleteByPrimaryKey(cvstoreId);
                }
            }
            if (cvorder != null && cvorder.getSid() != null) {
                Example example = new Example(Cvstore.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("sid", cvorder.getSid());
                List<Cvstore> cvstores = cvstoreMapper.selectByExample(example);
                for (Cvstore cvstore : cvstores) {
                    Integer cvstoreId = cvorder.getId();
                    cvstoreMapper.deleteByPrimaryKey(cvstoreId);
                }
            }

            return cvorderMapper.deleteByPrimaryKey(cvorderId);
        }
        return -1;
    }


    @Override
    public int updateRiderUser(int ridUserId,int cvOrderId) {
        Cvorder cvorder = new Cvorder();
        cvorder.setRid(ridUserId);
        cvorder.setId(cvOrderId);
        int i = cvorderMapper.updateByPrimaryKeySelective(cvorder);
        return  i;
    }
}