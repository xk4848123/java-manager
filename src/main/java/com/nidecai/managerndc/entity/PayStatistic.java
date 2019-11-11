package com.nidecai.managerndc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;



@Table(name = "hm_pay_statistic")
public class PayStatistic implements Serializable {
    /**
     * 标识
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 次数
     */
    private Integer count;

    /**
     * uid数量
     */
    @Column(name = "uid_count")
    private Integer uidCount;

    /**
     * 配送费
     */
    @Column(name = "rider_pay")
    private BigDecimal riderPay;

    /**
     * 优惠券
     */
    @Column(name = "coupon_price")
    private BigDecimal couponPrice;

    /**
     * vip优惠
     */
    @Column(name = "vip_relief")
    private BigDecimal vipRelief;

    /**
     * 积分优惠券
     */
    private BigDecimal intagral;

    /**
     * 骑手集合
     */
    private String rid;

    /**
     * 菜场集合
     */
    private String marketid;

    /**
     * 骑手名字集合
     */
    @Column(name = "rid_name")
    private String ridName;

    /**
     * 菜场名字集合
     */
    @Column(name = "market_name")
    private String marketName;

    /**
     * 菜场优惠金额
     */
    @Column(name = "market_price")
    private BigDecimal marketPrice;

    /**
     * uid集合
     */
    private String uid;

    /**
     * 前两个phone
     */
    private String phone;

    @Column(name = "rid_pro")
    private String ridPro;

    @Column(name = "store_info")
    private String storeInfo;

    private static final long serialVersionUID = 1L;

    /**
     * 获取标识
     *
     * @return id - 标识
     */
    public String getId() {
        return id;
    }

    /**
     * 设置标识
     *
     * @param id 标识
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取次数
     *
     * @return count - 次数
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置次数
     *
     * @param count 次数
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取uid数量
     *
     * @return uid_count - uid数量
     */
    public Integer getUidCount() {
        return uidCount;
    }

    /**
     * 设置uid数量
     *
     * @param uidCount uid数量
     */
    public void setUidCount(Integer uidCount) {
        this.uidCount = uidCount;
    }

    /**
     * 获取配送费
     *
     * @return rider_pay - 配送费
     */
    public BigDecimal getRiderPay() {
        return riderPay;
    }

    /**
     * 设置配送费
     *
     * @param riderPay 配送费
     */
    public void setRiderPay(BigDecimal riderPay) {
        this.riderPay = riderPay;
    }

    /**
     * 获取优惠券
     *
     * @return coupon_price - 优惠券
     */
    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    /**
     * 设置优惠券
     *
     * @param couponPrice 优惠券
     */
    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     * 获取vip优惠
     *
     * @return vip_relief - vip优惠
     */
    public BigDecimal getVipRelief() {
        return vipRelief;
    }

    /**
     * 设置vip优惠
     *
     * @param vipRelief vip优惠
     */
    public void setVipRelief(BigDecimal vipRelief) {
        this.vipRelief = vipRelief;
    }

    /**
     * 获取积分优惠券
     *
     * @return intagral - 积分优惠券
     */
    public BigDecimal getIntagral() {
        return intagral;
    }

    /**
     * 设置积分优惠券
     *
     * @param intagral 积分优惠券
     */
    public void setIntagral(BigDecimal intagral) {
        this.intagral = intagral;
    }

    /**
     * 获取骑手集合
     *
     * @return rid - 骑手集合
     */
    public String getRid() {
        return rid;
    }

    /**
     * 设置骑手集合
     *
     * @param rid 骑手集合
     */
    public void setRid(String rid) {
        this.rid = rid;
    }

    /**
     * 获取菜场集合
     *
     * @return marketid - 菜场集合
     */
    public String getMarketid() {
        return marketid;
    }

    /**
     * 设置菜场集合
     *
     * @param marketid 菜场集合
     */
    public void setMarketid(String marketid) {
        this.marketid = marketid;
    }

    /**
     * 获取骑手名字集合
     *
     * @return rid_name - 骑手名字集合
     */
    public String getRidName() {
        return ridName;
    }

    /**
     * 设置骑手名字集合
     *
     * @param ridName 骑手名字集合
     */
    public void setRidName(String ridName) {
        this.ridName = ridName;
    }

    /**
     * 获取菜场名字集合
     *
     * @return market_name - 菜场名字集合
     */
    public String getMarketName() {
        return marketName;
    }

    /**
     * 设置菜场名字集合
     *
     * @param marketName 菜场名字集合
     */
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    /**
     * 获取菜场优惠金额
     *
     * @return market_price - 菜场优惠金额
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * 设置菜场优惠金额
     *
     * @param marketPrice 菜场优惠金额
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取uid集合
     *
     * @return uid - uid集合
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置uid集合
     *
     * @param uid uid集合
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 获取前两个phone
     *
     * @return phone - 前两个phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置前两个phone
     *
     * @param phone 前两个phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return rid_pro
     */
    public String getRidPro() {
        return ridPro;
    }

    /**
     * @param ridPro
     */
    public void setRidPro(String ridPro) {
        this.ridPro = ridPro;
    }

    /**
     * @return store_info
     */
    public String getStoreInfo() {
        return storeInfo;
    }

    /**
     * @param storeInfo
     */
    public void setStoreInfo(String storeInfo) {
        this.storeInfo = storeInfo;
    }
}