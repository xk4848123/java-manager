package com.nidecai.managerndc.service.impl;

import com.nidecai.managerndc.common.mongoentity.AdminAuthority;
import com.nidecai.managerndc.common.mongoentity.User;
import com.nidecai.managerndc.service.CVStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author river
 * @title: CVStoreMogon
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/6/2114:05
 */
@Service("cVStoreService")
public class CVStoreMogon implements CVStoreService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<String> getPermissionsByToken(String loginToken) {
        Query queryUser = new Query();
        Criteria criteria = Criteria.where("loginToken").is(loginToken);
        queryUser.addCriteria(criteria);
        User user = mongoTemplate.findOne(queryUser, User.class);
        if (user !=null) {
            Query queryAdminAuthority = new Query();
            Criteria criteria1 = Criteria.where("id").is(user.getAuthority());
            queryAdminAuthority.addCriteria(criteria1);
            AdminAuthority adminAuthority = mongoTemplate.findOne(queryAdminAuthority, AdminAuthority.class);
            List<String> permission = adminAuthority.getPermission();
            return permission;
        }else {
            return  null;
        }
    }
}

