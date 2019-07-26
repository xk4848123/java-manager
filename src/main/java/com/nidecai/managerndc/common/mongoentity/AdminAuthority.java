package com.nidecai.managerndc.common.mongoentity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author river
 * @title: AdminAuthority
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/6/2117:20
 */
@Document(collection = "adminAuthority")
public class AdminAuthority {
    @Id
    private  String id;
    private String  createTime;
    private String name;
    private List<String> permission;

    public AdminAuthority() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }
}
