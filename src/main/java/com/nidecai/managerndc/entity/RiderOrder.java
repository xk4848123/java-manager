package com.nidecai.managerndc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "hm_rider_order")
public class RiderOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 配送订单
     */
    @Column(name = "rider_sn")
    private String riderSn;

    /**
     * 配送的所有订单
     */
    @Column(name = "order_sn")
    private String orderSn;

    /**
     * 待接单、1配送中、2已送达、3自提、4已评价
     */
    @Column(name = "rider_status")
    private Byte riderStatus;

    /**
     * 0待支付、1支付成功、2支付失败、3退款中、4退款成功
     */
    @Column(name = "pay_status")
    private Byte payStatus;

    private Integer uid;

    /**
     * 配送人员
     */
    private Integer rid;

    /**
     * 菜场id
     */
    private Integer marketid;

    /**
     * 配送费用
     */
    @Column(name = "rider_pay")
    private BigDecimal riderPay;

    /**
     * 配送地址
     */
    @Column(name = "address_id")
    private Integer addressId;

    /**
     * 用户优惠券id
     */
    private Integer couponid;

    /**
     * 支付方式
     */
    @Column(name = "type_pay")
    private String typePay;

    @Column(name = "totalPrice")
    private Double totalprice;

    /**
     * 订单支付时间
     */
    @Column(name = "pay_time")
    private Integer payTime;

    /**
     * 预约送到时间
     */
    @Column(name = "service_time")
    private Integer serviceTime;

    /**
     * 接单时间
     */
    @Column(name = "order_time")
    private Integer orderTime;

    /**
     * 完成时间
     */
    @Column(name = "finish_time")
    private Integer finishTime;

    private Integer ctime;

    private Integer integral;

    /**
     * 0不预约1预约
     */
    @Column(name = "is_appointment")
    private Short isAppointment;

    /**
     * 预约结束时间
     */
    @Column(name = "end_time")
    private Integer endTime;

    /**
     * 记录原价
     */
    @Column(name = "original_price")
    private BigDecimal originalPrice;

    /**
     * 第三方支付平台订单号
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 优惠券金额
     */
    @Column(name = "coupon_price")
    private BigDecimal couponPrice;

    /**
     * 菜场优惠金额
     */
    @Column(name = "market_activity_price")
    private BigDecimal marketActivityPrice;

    /**
     * 商户活动减免
     */
    @Column(name = "store_activity_price")
    private BigDecimal storeActivityPrice;

    /**
     * VIP减免
     */
    @Column(name = "vip_relief")
    private BigDecimal vipRelief;

    /**
     * 错误金额
     */
    @Column(name = "error_price")
    private BigDecimal errorPrice;

    /**
     * 佣金收益
     */
    @Column(name = "commission_price")
    private BigDecimal commissionPrice;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 测试订单标识
     */
    private Integer test;

    /**
     * 设备号
     */
    @Column(name = "deviceId")
    private String deviceid;

    /**
     * -1未知0非自提1自提
     */
    @Column(name = "self_sufficiency")
    private Byte selfSufficiency;

    /**
     * 1删除
     */
    @Column(name = "is_delete")
    private Byte isDelete;

    private String identity;

    @Column(name = "pay_platform_no")
    private String payPlatformNo;

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
     * 获取配送订单
     *
     * @return rider_sn - 配送订单
     */
    public String getRiderSn() {
        return riderSn;
    }

    /**
     * 设置配送订单
     *
     * @param riderSn 配送订单
     */
    public void setRiderSn(String riderSn) {
        this.riderSn = riderSn;
    }

    /**
     * 获取配送的所有订单
     *
     * @return order_sn - 配送的所有订单
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     * 设置配送的所有订单
     *
     * @param orderSn 配送的所有订单
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取待接单、1配送中、2已送达、3自提、4已评价
     *
     * @return rider_status - 待接单、1配送中、2已送达、3自提、4已评价
     */
    public Byte getRiderStatus() {
        return riderStatus;
    }

    /**
     * 设置待接单、1配送中、2已送达、3自提、4已评价
     *
     * @param riderStatus 待接单、1配送中、2已送达、3自提、4已评价
     */
    public void setRiderStatus(Byte riderStatus) {
        this.riderStatus = riderStatus;
    }

    /**
     * 获取0待支付、1支付成功、2支付失败、3退款中、4退款成功
     *
     * @return pay_status - 0待支付、1支付成功、2支付失败、3退款中、4退款成功
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * 设置0待支付、1支付成功、2支付失败、3退款中、4退款成功
     *
     * @param payStatus 0待支付、1支付成功、2支付失败、3退款中、4退款成功
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
     * 获取配送人员
     *
     * @return rid - 配送人员
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * 设置配送人员
     *
     * @param rid 配送人员
     */
    public void setRid(Integer rid) {
        this.rid = rid;
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
     * 获取配送费用
     *
     * @return rider_pay - 配送费用
     */
    public BigDecimal getRiderPay() {
        return riderPay;
    }

    /**
     * 设置配送费用
     *
     * @param riderPay 配送费用
     */
    public void setRiderPay(BigDecimal riderPay) {
        this.riderPay = riderPay;
    }

    /**
     * 获取配送地址
     *
     * @return address_id - 配送地址
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * 设置配送地址
     *
     * @param addressId 配送地址
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * 获取用户优惠券id
     *
     * @return couponid - 用户优惠券id
     */
    public Integer getCouponid() {
        return couponid;
    }

    /**
     * 设置用户优惠券id
     *
     * @param couponid 用户优惠券id
     */
    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
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
        this.typePay = typePay;
    }

    /**
     * @return totalPrice
     */
    public Double getTotalprice() {
        return totalprice;
    }

    /**
     * @param totalprice
     */
    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * 获取订单支付时间
     *
     * @return pay_time - 订单支付时间
     */
    public Integer getPayTime() {
        return payTime;
    }

    /**
     * 设置订单支付时间
     *
     * @param payTime 订单支付时间
     */
    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取预约送到时间
     *
     * @return service_time - 预约送到时间
     */
    public Integer getServiceTime() {
        return serviceTime;
    }

    /**
     * 设置预约送到时间
     *
     * @param serviceTime 预约送到时间
     */
    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * 获取接单时间
     *
     * @return order_time - 接单时间
     */
    public Integer getOrderTime() {
        return orderTime;
    }

    /**
     * 设置接单时间
     *
     * @param orderTime 接单时间
     */
    public void setOrderTime(Integer orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 获取完成时间
     *
     * @return finish_time - 完成时间
     */
    public Integer getFinishTime() {
        return finishTime;
    }

    /**
     * 设置完成时间
     *
     * @param finishTime 完成时间
     */
    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
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
     * @return integral
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * @param integral
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取0不预约1预约
     *
     * @return is_appointment - 0不预约1预约
     */
    public Short getIsAppointment() {
        return isAppointment;
    }

    /**
     * 设置0不预约1预约
     *
     * @param isAppointment 0不预约1预约
     */
    public void setIsAppointment(Short isAppointment) {
        this.isAppointment = isAppointment;
    }

    /**
     * 获取预约结束时间
     *
     * @return end_time - 预约结束时间
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * 设置预约结束时间
     *
     * @param endTime 预约结束时间
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取记录原价
     *
     * @return original_price - 记录原价
     */
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    /**
     * 设置记录原价
     *
     * @param originalPrice 记录原价
     */
    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * 获取第三方支付平台订单号
     *
     * @return out_trade_no - 第三方支付平台订单号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设置第三方支付平台订单号
     *
     * @param outTradeNo 第三方支付平台订单号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * 获取优惠券金额
     *
     * @return coupon_price - 优惠券金额
     */
    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    /**
     * 设置优惠券金额
     *
     * @param couponPrice 优惠券金额
     */
    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     * 获取菜场优惠金额
     *
     * @return market_activity_price - 菜场优惠金额
     */
    public BigDecimal getMarketActivityPrice() {
        return marketActivityPrice;
    }

    /**
     * 设置菜场优惠金额
     *
     * @param marketActivityPrice 菜场优惠金额
     */
    public void setMarketActivityPrice(BigDecimal marketActivityPrice) {
        this.marketActivityPrice = marketActivityPrice;
    }

    /**
     * 获取商户活动减免
     *
     * @return store_activity_price - 商户活动减免
     */
    public BigDecimal getStoreActivityPrice() {
        return storeActivityPrice;
    }

    /**
     * 设置商户活动减免
     *
     * @param storeActivityPrice 商户活动减免
     */
    public void setStoreActivityPrice(BigDecimal storeActivityPrice) {
        this.storeActivityPrice = storeActivityPrice;
    }

    /**
     * 获取VIP减免
     *
     * @return vip_relief - VIP减免
     */
    public BigDecimal getVipRelief() {
        return vipRelief;
    }

    /**
     * 设置VIP减免
     *
     * @param vipRelief VIP减免
     */
    public void setVipRelief(BigDecimal vipRelief) {
        this.vipRelief = vipRelief;
    }

    /**
     * 获取错误金额
     *
     * @return error_price - 错误金额
     */
    public BigDecimal getErrorPrice() {
        return errorPrice;
    }

    /**
     * 设置错误金额
     *
     * @param errorPrice 错误金额
     */
    public void setErrorPrice(BigDecimal errorPrice) {
        this.errorPrice = errorPrice;
    }

    /**
     * 获取佣金收益
     *
     * @return commission_price - 佣金收益
     */
    public BigDecimal getCommissionPrice() {
        return commissionPrice;
    }

    /**
     * 设置佣金收益
     *
     * @param commissionPrice 佣金收益
     */
    public void setCommissionPrice(BigDecimal commissionPrice) {
        this.commissionPrice = commissionPrice;
    }

    /**
     * 获取备注信息
     *
     * @return remark - 备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注信息
     *
     * @param remark 备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取测试订单标识
     *
     * @return test - 测试订单标识
     */
    public Integer getTest() {
        return test;
    }

    /**
     * 设置测试订单标识
     *
     * @param test 测试订单标识
     */
    public void setTest(Integer test) {
        this.test = test;
    }

    /**
     * 获取设备号
     *
     * @return deviceId - 设备号
     */
    public String getDeviceid() {
        return deviceid;
    }

    /**
     * 设置设备号
     *
     * @param deviceid 设备号
     */
    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    /**
     * 获取-1未知0非自提1自提
     *
     * @return self_sufficiency - -1未知0非自提1自提
     */
    public Byte getSelfSufficiency() {
        return selfSufficiency;
    }

    /**
     * 设置-1未知0非自提1自提
     *
     * @param selfSufficiency -1未知0非自提1自提
     */
    public void setSelfSufficiency(Byte selfSufficiency) {
        this.selfSufficiency = selfSufficiency;
    }

    /**
     * 获取1删除
     *
     * @return is_delete - 1删除
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * 设置1删除
     *
     * @param isDelete 1删除
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return identity
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * @param identity
     */
    public void setIdentity(String identity) {
        this.identity = identity;
    }

    /**
     * @return pay_platform_no
     */
    public String getPayPlatformNo() {
        return payPlatformNo;
    }

    /**
     * @param payPlatformNo
     */
    public void setPayPlatformNo(String payPlatformNo) {
        this.payPlatformNo = payPlatformNo;
    }
}