package com.nidecai.managerndc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "hm_cvuser_order")
public class CvuserOrder implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 订单序号(UCV开头)
     */
    @Column(name = "order_sn")
    private String orderSn;

    /**
     * 三方号(微信小程序CVJS开头，微信app CVWX开头)
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 支付状态0待支付1已支付
     */
    @Column(name = "pay_status")
    private Byte payStatus;

    @Column(name = "uid")
    private Integer uid;

    /**
     * 积分抵扣金额
     */
    @Column(name = "integralPrice")
    private BigDecimal integralprice;

    /**
     * 商户活动减免(额外记录商户活动减免)
     */
    @Column(name = "store_activity_price")
    private BigDecimal storeActivityPrice;

    /**
     * vip减免
     */
    @Column(name = "vip_relief")
    private BigDecimal vipRelief;

    /**
     * 优惠券额度
     */
    @Column(name = "coupon_price")
    private BigDecimal couponPrice;

    /**
     * 优惠券id
     */
    @Column(name = "coupon_id")
    private Integer couponId;

    /**
     * 用户是否显示该单（完成的订单增加删除）1显示0删除
     */
    @Column(name = "is_show")
    private Byte isShow;

    /**
     * 订单实付
     */
    @Column(name = "totalPrice")
    private BigDecimal totalprice;

    /**
     * 配送费冗余
     */
    @Column(name = "deliver_fee")
    private BigDecimal deliverFee;

    /**
     * 原价(商户该得的金额)
     */
    @Column(name = "originalPrice")
    private BigDecimal originalprice;

    /**
     * 创建时间
     */
    @Column(name = "ctime")
    private Integer ctime;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Integer payTime;

    /**
     * 程序后顺序校验1有错误0无误
     */
    @Column(name = "checkfield")
    private Byte checkfield;

    /**
     * 支付方式
     */
    @Column(name = "type_pay")
    private String typePay;

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
     * 获取订单序号(UCV开头)
     *
     * @return order_sn - 订单序号(UCV开头)
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     * 设置订单序号(UCV开头)
     *
     * @param orderSn 订单序号(UCV开头)
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    /**
     * 获取三方号(微信小程序CVJS开头，微信app CVWX开头)
     *
     * @return out_trade_no - 三方号(微信小程序CVJS开头，微信app CVWX开头)
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置三方号(微信小程序CVJS开头，微信app CVWX开头)
     *
     * @param outTradeNo 三方号(微信小程序CVJS开头，微信app CVWX开头)
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    /**
     * 获取支付状态0待支付1已支付
     *
     * @return pay_status - 支付状态0待支付1已支付
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态0待支付1已支付
     *
     * @param payStatus 支付状态0待支付1已支付
     */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取积分抵扣金额
     *
     * @return integralPrice - 积分抵扣金额
     */
    public BigDecimal getIntegralprice() {
        return integralprice;
    }

    /**
     * 设置积分抵扣金额
     *
     * @param integralprice 积分抵扣金额
     */
    public void setIntegralprice(BigDecimal integralprice) {
        this.integralprice = integralprice;
    }

    /**
     * 获取商户活动减免(额外记录商户活动减免)
     *
     * @return store_activity_price - 商户活动减免(额外记录商户活动减免)
     */
    public BigDecimal getStoreActivityPrice() {
        return storeActivityPrice;
    }

    /**
     * 设置商户活动减免(额外记录商户活动减免)
     *
     * @param storeActivityPrice 商户活动减免(额外记录商户活动减免)
     */
    public void setStoreActivityPrice(BigDecimal storeActivityPrice) {
        this.storeActivityPrice = storeActivityPrice;
    }

    /**
     * 获取vip减免
     *
     * @return vip_relief - vip减免
     */
    public BigDecimal getVipRelief() {
        return vipRelief;
    }

    /**
     * 设置vip减免
     *
     * @param vipRelief vip减免
     */
    public void setVipRelief(BigDecimal vipRelief) {
        this.vipRelief = vipRelief;
    }

    /**
     * 获取优惠券额度
     *
     * @return coupon_price - 优惠券额度
     */
    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    /**
     * 设置优惠券额度
     *
     * @param couponPrice 优惠券额度
     */
    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     * 获取优惠券id
     *
     * @return coupon_id - 优惠券id
     */
    public Integer getCouponId() {
        return couponId;
    }

    /**
     * 设置优惠券id
     *
     * @param couponId 优惠券id
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取用户是否显示该单（完成的订单增加删除）1显示0删除
     *
     * @return is_show - 用户是否显示该单（完成的订单增加删除）1显示0删除
     */
    public Byte getIsShow() {
        return isShow;
    }

    /**
     * 设置用户是否显示该单（完成的订单增加删除）1显示0删除
     *
     * @param isShow 用户是否显示该单（完成的订单增加删除）1显示0删除
     */
    public void setIsShow(Byte isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取订单实付
     *
     * @return totalPrice - 订单实付
     */
    public BigDecimal getTotalprice() {
        return totalprice;
    }

    /**
     * 设置订单实付
     *
     * @param totalprice 订单实付
     */
    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * 获取配送费冗余
     *
     * @return deliver_fee - 配送费冗余
     */
    public BigDecimal getDeliverFee() {
        return deliverFee;
    }

    /**
     * 设置配送费冗余
     *
     * @param deliverFee 配送费冗余
     */
    public void setDeliverFee(BigDecimal deliverFee) {
        this.deliverFee = deliverFee;
    }

    /**
     * 获取原价(商户该得的金额)
     *
     * @return originalPrice - 原价(商户该得的金额)
     */
    public BigDecimal getOriginalprice() {
        return originalprice;
    }

    /**
     * 设置原价(商户该得的金额)
     *
     * @param originalprice 原价(商户该得的金额)
     */
    public void setOriginalprice(BigDecimal originalprice) {
        this.originalprice = originalprice;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Integer getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取程序后顺序校验1有错误0无误
     *
     * @return checkfield - 程序后顺序校验1有错误0无误
     */
    public Byte getCheckfield() {
        return checkfield;
    }

    /**
     * 设置程序后顺序校验1有错误0无误
     *
     * @param checkfield 程序后顺序校验1有错误0无误
     */
    public void setCheckfield(Byte checkfield) {
        this.checkfield = checkfield;
    }

    /**
     * 获取支付方式
     *
     * @return type_pay - 支付方式
     */
    public String getTypePay() {
        return typePay;
    }

    /**
     * 设置支付方式
     *
     * @param typePay 支付方式
     */
    public void setTypePay(String typePay) {
        this.typePay = typePay == null ? null : typePay.trim();
    }
}