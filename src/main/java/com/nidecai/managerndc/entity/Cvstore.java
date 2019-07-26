package com.nidecai.managerndc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "hm_cvstore")
public class Cvstore implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 商品id
     */
    @Column(name = "gid")
    private Integer gid;

    /**
     * 用户id
     */
    @Column(name = "uid")
    private Integer uid;

    /**
     * 价格
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 单价
     */
    @Column(name = "unitPrice")
    private BigDecimal unitprice;

    /**
     * 数量
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * 商户id
     */
    @Column(name = "sid")
    private Integer sid;

    @Column(name = "ctime")
    private Integer ctime;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品id
     *
     * @return gid - 商品id
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * 设置商品id
     *
     * @param gid 商品id
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    /**
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取单价
     *
     * @return unitPrice - 单价
     */
    public BigDecimal getUnitprice() {
        return unitprice;
    }

    /**
     * 设置单价
     *
     * @param unitprice 单价
     */
    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    /**
     * 获取数量
     *
     * @return amount - 数量
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置数量
     *
     * @param amount 数量
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 获取商户id
     *
     * @return sid - 商户id
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * 设置商户id
     *
     * @param sid 商户id
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * @return ctime
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     * @param ctime
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}