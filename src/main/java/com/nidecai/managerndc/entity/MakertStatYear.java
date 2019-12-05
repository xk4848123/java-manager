package com.nidecai.managerndc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "hm_makertstat_year")
public class MakertStatYear implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 菜场id
     */
    private Integer marketid;

    /**
     * 单量总计
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 实付总计
     */
    @Column(name = "order_pay")
    private BigDecimal orderPay;

    /**
     * 配送总计
     */
    @Column(name = "order_rider_pay")
    private BigDecimal orderRiderPay;

    /**
     * 优惠券总计
     */
    @Column(name = "order_coupon")
    private BigDecimal orderCoupon;

    /**
     * 积分优惠总计
     */
    @Column(name = "order_integral")
    private BigDecimal orderIntegral;

    /**
     * 菜场活动总计
     */
    @Column(name = "order_activity")
    private BigDecimal orderActivity;

    /**
     * vip优惠总计
     */
    @Column(name = "order_vip_relief")
    private BigDecimal orderVipRelief;

    @Column(name = "order_commission_price")
    private BigDecimal orderCommissionPrice;

    /**
     * 存如:2019
     */
    private String date;

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
     * 获取菜场id
     *
     * @return marketid - 菜场id
     */
    public Integer getMarketid() {
        return marketid;
    }

    /**
     * 设置菜场id
     *
     * @param marketid 菜场id
     */
    public void setMarketid(Integer marketid) {
        this.marketid = marketid;
    }

    /**
     * 获取单量总计
     *
     * @return order_num - 单量总计
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置单量总计
     *
     * @param orderNum 单量总计
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取实付总计
     *
     * @return order_pay - 实付总计
     */
    public BigDecimal getOrderPay() {
        return orderPay;
    }

    /**
     * 设置实付总计
     *
     * @param orderPay 实付总计
     */
    public void setOrderPay(BigDecimal orderPay) {
        this.orderPay = orderPay;
    }

    /**
     * 获取配送总计
     *
     * @return order_rider_pay - 配送总计
     */
    public BigDecimal getOrderRiderPay() {
        return orderRiderPay;
    }

    /**
     * 设置配送总计
     *
     * @param orderRiderPay 配送总计
     */
    public void setOrderRiderPay(BigDecimal orderRiderPay) {
        this.orderRiderPay = orderRiderPay;
    }

    /**
     * 获取优惠券总计
     *
     * @return order_coupon - 优惠券总计
     */
    public BigDecimal getOrderCoupon() {
        return orderCoupon;
    }

    /**
     * 设置优惠券总计
     *
     * @param orderCoupon 优惠券总计
     */
    public void setOrderCoupon(BigDecimal orderCoupon) {
        this.orderCoupon = orderCoupon;
    }

    /**
     * 获取积分优惠总计
     *
     * @return order_integral - 积分优惠总计
     */
    public BigDecimal getOrderIntegral() {
        return orderIntegral;
    }

    /**
     * 设置积分优惠总计
     *
     * @param orderIntegral 积分优惠总计
     */
    public void setOrderIntegral(BigDecimal orderIntegral) {
        this.orderIntegral = orderIntegral;
    }

    /**
     * 获取菜场活动总计
     *
     * @return order_activity - 菜场活动总计
     */
    public BigDecimal getOrderActivity() {
        return orderActivity;
    }

    /**
     * 设置菜场活动总计
     *
     * @param orderActivity 菜场活动总计
     */
    public void setOrderActivity(BigDecimal orderActivity) {
        this.orderActivity = orderActivity;
    }

    /**
     * 获取vip优惠总计
     *
     * @return order_vip_relief - vip优惠总计
     */
    public BigDecimal getOrderVipRelief() {
        return orderVipRelief;
    }

    /**
     * 设置vip优惠总计
     *
     * @param orderVipRelief vip优惠总计
     */
    public void setOrderVipRelief(BigDecimal orderVipRelief) {
        this.orderVipRelief = orderVipRelief;
    }

    /**
     * @return order_commission_price
     */
    public BigDecimal getOrderCommissionPrice() {
        return orderCommissionPrice;
    }

    /**
     * @param orderCommissionPrice
     */
    public void setOrderCommissionPrice(BigDecimal orderCommissionPrice) {
        this.orderCommissionPrice = orderCommissionPrice;
    }

    /**
     * 获取存如:2019
     *
     * @return date - 存如:2019
     */
    public String getDate() {
        return date;
    }

    /**
     * 设置存如:2019
     *
     * @param date 存如:2019
     */
    public void setDate(String date) {
        this.date = date;
    }
}