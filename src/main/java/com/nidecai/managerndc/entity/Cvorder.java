package com.nidecai.managerndc.entity;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "hm_cvorder")
public class Cvorder implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    //用户订单信息(一对一)
    @Transient
    private  OrderAddress orderAddress;

    public OrderAddress getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(OrderAddress orderAddress) {
        this.orderAddress = orderAddress;
    }

    //订单对应的骑手(一对一)
    @Transient
    private RiderUser riderUser;

    public RiderUser getRiderUser() {
        return riderUser;
    }

    public void setRiderUser(RiderUser riderUser) {
        this.riderUser = riderUser;
    }
    //用户购买详细信息(一对一)
    @Transient
    private  CvuserOrder cvuserOrder;

    public CvuserOrder getCvuserOrder() {
        return cvuserOrder;
    }

    public void setCvuserOrder(CvuserOrder cvuserOrder) {
        this.cvuserOrder = cvuserOrder;
    }

    /**
     * 订单序列号
     */
    @Column(name = "order_sn")
    private String orderSn;

    /**
     * 商户id
     */
    @Column(name = "sid")
    private Integer sid;

    /**
     * 原价(商品原价)
     */
    @Column(name = "total")
    private BigDecimal total;

    /**
     * 现价(用户最终该得的金额)
     */
    @Column(name = "payment")
    private BigDecimal payment;

    /**
     * 创建时间
     */
    @Column(name = "ctime")
    private Integer ctime;

    /**
     * 接单时间(商户点击接单的时间)
     */
    @Column(name = "ordertaking_time")
    private Integer ordertakingTime;

    /**
     * 订单完成时间(商户点击完单的时间)
     */
    @Column(name = "orderend_time")
    private Integer orderendTime;

    /**
     * 预约时间
     */
    @Column(name = "service_time")
    private Integer serviceTime;

    /**
     * 地址id(商户送单地址及用户相关信息)
     */
    @Column(name = "addressid")
    private Integer addressid;

    /**
     * 0待接单1接单2送达3自提4评价完成
     */
    @Column(name = "order_status")
    private Byte orderStatus;

    /**
     * 0待支付1支付（冗余用户订单的支付状态）
     */
    @Column(name = "pay_status")
    private Byte payStatus;

    /**
     * 配送费(商户收取的手续费)
     */
    @Column(name = "deliver_fee")
    private BigDecimal deliverFee;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 关联的用户订单
     */
    @Column(name = "uoid")
    private Integer uoid;

    /**
     * 骑手id
     */
    @Column(name = "rid")
    private Integer rid;

    /**
     * 配送模式初始为0商户接单1平台配送2
     */
    @Column(name = "deliver_model")
    private Byte deliverModel;

    /**
     * 平台配送收取商户的手续费
     */
    @Column(name = "service_fee")
    private BigDecimal serviceFee;

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
     * 获取订单序列号
     *
     * @return order_sn - 订单序列号
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     * 设置订单序列号
     *
     * @param orderSn 订单序列号
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
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
     * 获取原价(商品原价)
     *
     * @return total - 原价(商品原价)
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 设置原价(商品原价)
     *
     * @param total 原价(商品原价)
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * 获取现价(用户最终该得的金额)
     *
     * @return payment - 现价(用户最终该得的金额)
     */
    public BigDecimal getPayment() {
        return payment;
    }

    /**
     * 设置现价(用户最终该得的金额)
     *
     * @param payment 现价(用户最终该得的金额)
     */
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
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
     * 获取接单时间(商户点击接单的时间)
     *
     * @return ordertaking_time - 接单时间(商户点击接单的时间)
     */
    public Integer getOrdertakingTime() {
        return ordertakingTime;
    }

    /**
     * 设置接单时间(商户点击接单的时间)
     *
     * @param ordertakingTime 接单时间(商户点击接单的时间)
     */
    public void setOrdertakingTime(Integer ordertakingTime) {
        this.ordertakingTime = ordertakingTime;
    }

    /**
     * 获取订单完成时间(商户点击完单的时间)
     *
     * @return orderend_time - 订单完成时间(商户点击完单的时间)
     */
    public Integer getOrderendTime() {
        return orderendTime;
    }

    /**
     * 设置订单完成时间(商户点击完单的时间)
     *
     * @param orderendTime 订单完成时间(商户点击完单的时间)
     */
    public void setOrderendTime(Integer orderendTime) {
        this.orderendTime = orderendTime;
    }

    /**
     * 获取预约时间
     *
     * @return service_time - 预约时间
     */
    public Integer getServiceTime() {
        return serviceTime;
    }

    /**
     * 设置预约时间
     *
     * @param serviceTime 预约时间
     */
    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    /**
     * 获取地址id(商户送单地址及用户相关信息)
     *
     * @return addressid - 地址id(商户送单地址及用户相关信息)
     */
    public Integer getAddressid() {
        return addressid;
    }

    /**
     * 设置地址id(商户送单地址及用户相关信息)
     *
     * @param addressid 地址id(商户送单地址及用户相关信息)
     */
    public void setAddressid(Integer addressid) {
        this.addressid = addressid;
    }

    /**
     * 获取0待接单1接单2送达3自提4评价完成
     *
     * @return order_status - 0待接单1接单2送达3自提4评价完成
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置0待接单1接单2送达3自提4评价完成
     *
     * @param orderStatus 0待接单1接单2送达3自提4评价完成
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取0待支付1支付（冗余用户订单的支付状态）
     *
     * @return pay_status - 0待支付1支付（冗余用户订单的支付状态）
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * 设置0待支付1支付（冗余用户订单的支付状态）
     *
     * @param payStatus 0待支付1支付（冗余用户订单的支付状态）
     */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取配送费(商户收取的手续费)
     *
     * @return deliver_fee - 配送费(商户收取的手续费)
     */
    public BigDecimal getDeliverFee() {
        return deliverFee;
    }

    /**
     * 设置配送费(商户收取的手续费)
     *
     * @param deliverFee 配送费(商户收取的手续费)
     */
    public void setDeliverFee(BigDecimal deliverFee) {
        this.deliverFee = deliverFee;
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

    /**
     * 获取关联的用户订单
     *
     * @return uoid - 关联的用户订单
     */
    public Integer getUoid() {
        return uoid;
    }

    /**
     * 设置关联的用户订单
     *
     * @param uoid 关联的用户订单
     */
    public void setUoid(Integer uoid) {
        this.uoid = uoid;
    }

    /**
     * 获取骑手id
     *
     * @return rid - 骑手id
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * 设置骑手id
     *
     * @param rid 骑手id
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * 获取配送模式初始为0商户接单1平台配送2
     *
     * @return deliver_model - 配送模式初始为0商户接单1平台配送2
     */
    public Byte getDeliverModel() {
        return deliverModel;
    }

    /**
     * 设置配送模式初始为0商户接单1平台配送2
     *
     * @param deliverModel 配送模式初始为0商户接单1平台配送2
     */
    public void setDeliverModel(Byte deliverModel) {
        this.deliverModel = deliverModel;
    }

    /**
     * 获取平台配送收取商户的手续费
     *
     * @return service_fee - 平台配送收取商户的手续费
     */
    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    /**
     * 设置平台配送收取商户的手续费
     *
     * @param serviceFee 平台配送收取商户的手续费
     */
    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }
}