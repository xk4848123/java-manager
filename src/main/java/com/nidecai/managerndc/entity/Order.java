package com.nidecai.managerndc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "hm_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 订单号
     */
    @Column(name = "order_sn")
    private String orderSn;

    /**
     * 商家pid
     */
    private Integer pid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 菜场id
     */
    private Integer marketid;

    /**
     * 订单总价
     */
    private BigDecimal total;

    /**
     * 实际到账金额
     */
    @Column(name = "actual_achieve")
    private BigDecimal actualAchieve;

    /**
     * 实际支付金额
     */
    private BigDecimal payment;

    /**
     * 0配送1自提
     */
    private Byte type;

    /**
     * 使用积分
     */
    private Integer integral;

    private Integer couponid;

    /**
     * 支付状态0未支付1已支付2支付超时
     */
    @Column(name = "pay_status")
    private Byte payStatus;

    /**
     * 订单状态0未配送1待接单2已送达3自提4已评价5已取消6配送中
     */
    @Column(name = "order_status")
    private Byte orderStatus;

    /**
     * 0未确认1已确认2驳回
     */
    @Column(name = "is_confirm")
    private Byte isConfirm;

    /**
     * 创建时间
     */
    private Integer ctime;

    /**
     * 支付回调时间
     */
    @Column(name = "pay_time")
    private Integer payTime;

    /**
     * 购物车记录
     */
    private String cartids;

    private String remark;

    /**
     * 0普通单1预约单
     */
    @Column(name = "is_appointment")
    private Integer isAppointment;

    /**
     * 所属RI订单id
     */
    private Integer roid;

    /**
     * 测试订单标识
     */
    private Integer test;

    /**
     * 1删除
     */
    @Column(name = "is_delete")
    private Byte isDelete;

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
     * 获取订单号
     *
     * @return order_sn - 订单号
     */
    public String getOrderSn() {
        return orderSn;
    }

    /**
     * 设置订单号
     *
     * @param orderSn 订单号
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取商家pid
     *
     * @return pid - 商家pid
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置商家pid
     *
     * @param pid 商家pid
     */
    public void setPid(Integer pid) {
        this.pid = pid;
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
     * 获取订单总价
     *
     * @return total - 订单总价
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * 设置订单总价
     *
     * @param total 订单总价
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * 获取实际到账金额
     *
     * @return actual_achieve - 实际到账金额
     */
    public BigDecimal getActualAchieve() {
        return actualAchieve;
    }

    /**
     * 设置实际到账金额
     *
     * @param actualAchieve 实际到账金额
     */
    public void setActualAchieve(BigDecimal actualAchieve) {
        this.actualAchieve = actualAchieve;
    }

    /**
     * 获取实际支付金额
     *
     * @return payment - 实际支付金额
     */
    public BigDecimal getPayment() {
        return payment;
    }

    /**
     * 设置实际支付金额
     *
     * @param payment 实际支付金额
     */
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    /**
     * 获取0配送1自提
     *
     * @return type - 0配送1自提
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置0配送1自提
     *
     * @param type 0配送1自提
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取使用积分
     *
     * @return integral - 使用积分
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * 设置使用积分
     *
     * @param integral 使用积分
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * @return couponid
     */
    public Integer getCouponid() {
        return couponid;
    }

    /**
     * @param couponid
     */
    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }

    /**
     * 获取支付状态0未支付1已支付2支付超时
     *
     * @return pay_status - 支付状态0未支付1已支付2支付超时
     */
    public Byte getPayStatus() {
        return payStatus;
    }

    /**
     * 设置支付状态0未支付1已支付2支付超时
     *
     * @param payStatus 支付状态0未支付1已支付2支付超时
     */
    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取订单状态0未配送1待接单2已送达3自提4已评价5已取消6配送中
     *
     * @return order_status - 订单状态0未配送1待接单2已送达3自提4已评价5已取消6配送中
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态0未配送1待接单2已送达3自提4已评价5已取消6配送中
     *
     * @param orderStatus 订单状态0未配送1待接单2已送达3自提4已评价5已取消6配送中
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取0未确认1已确认2驳回
     *
     * @return is_confirm - 0未确认1已确认2驳回
     */
    public Byte getIsConfirm() {
        return isConfirm;
    }

    /**
     * 设置0未确认1已确认2驳回
     *
     * @param isConfirm 0未确认1已确认2驳回
     */
    public void setIsConfirm(Byte isConfirm) {
        this.isConfirm = isConfirm;
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
     * 获取支付回调时间
     *
     * @return pay_time - 支付回调时间
     */
    public Integer getPayTime() {
        return payTime;
    }

    /**
     * 设置支付回调时间
     *
     * @param payTime 支付回调时间
     */
    public void setPayTime(Integer payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取购物车记录
     *
     * @return cartids - 购物车记录
     */
    public String getCartids() {
        return cartids;
    }

    /**
     * 设置购物车记录
     *
     * @param cartids 购物车记录
     */
    public void setCartids(String cartids) {
        this.cartids = cartids;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取0普通单1预约单
     *
     * @return is_appointment - 0普通单1预约单
     */
    public Integer getIsAppointment() {
        return isAppointment;
    }

    /**
     * 设置0普通单1预约单
     *
     * @param isAppointment 0普通单1预约单
     */
    public void setIsAppointment(Integer isAppointment) {
        this.isAppointment = isAppointment;
    }

    /**
     * 获取所属RI订单id
     *
     * @return roid - 所属RI订单id
     */
    public Integer getRoid() {
        return roid;
    }

    /**
     * 设置所属RI订单id
     *
     * @param roid 所属RI订单id
     */
    public void setRoid(Integer roid) {
        this.roid = roid;
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
}