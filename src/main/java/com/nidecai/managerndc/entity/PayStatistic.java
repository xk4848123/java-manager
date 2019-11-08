package com.nidecai.managerndc.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author river
 * @title: PayStatistic
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/11/818:10
 */
@Table(name = "hm_pay_statistic")
public class PayStatistic implements Serializable {
    @Id
    @Column(name = "id")
    private  String id;
    @Column(name = "count")
    private  Integer count;
    @Column(name = "uid")
    private  String uid;
    @Column(name = "uid_count")
    private  Integer uidCount;

    public Integer getUidCount() {
        return uidCount;
    }

    public void setUidCount(Integer uidCount) {
        this.uidCount = uidCount;
    }

    private List<PayidOrder> payidOrderList;

    public List<PayidOrder> getPayidOrderList() {
        return payidOrderList;
    }

    public void setPayidOrderList(List<PayidOrder> payidOrderList) {
        this.payidOrderList = payidOrderList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
