package com.nidecai.managerndc.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author river
 * @title: PayStatic
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/11/818:05
 */@Table(name = "hm_payid_order")

public class PayidOrder implements Serializable {
    @Id
    @Column(name = "pay_id")
    private String payId;
    @Column(name = "out_trade_no")
    private String out_trade_no;

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }



}
