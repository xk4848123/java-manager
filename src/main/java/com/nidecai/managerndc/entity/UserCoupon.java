package com.nidecai.managerndc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "hm_user_coupon")
public class UserCoupon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 优惠券id
     */
    private Integer couponid;

    /**
     * 优惠券ID(Mongodb)
     */
    private String coupon;

    /**
     * 创建时间
     */
    private Integer ctime;

    /**
     * 未使用、1已使用、2已过期
     */
    private Byte state;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Integer updateTime;

    /**
     * 优惠券数量
     */
    private Integer numbers;

    @Column(name = "start_time")
    private Integer startTime;

    @Column(name = "end_time")
    private Integer endTime;

    @Column(name = "is_new")
    private Integer isNew;

    /**
     * 0红包1后台配发
     */
    private Integer type;

    /**
     * 优惠金额
     */
    private BigDecimal price;

    /**
     * 适用场景0全场,1菜场,2社区铺
     */
    private Integer model;

    /**
     * 0未激活1激活
     */
    @Column(name = "is_active")
    private Byte isActive;

    /**
     * 券的等级
     */
    private Byte level;

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
     * 获取优惠券id
     *
     * @return couponid - 优惠券id
     */
    public Integer getCouponid() {
        return couponid;
    }

    /**
     * 设置优惠券id
     *
     * @param couponid 优惠券id
     */
    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }

    /**
     * 获取优惠券ID(Mongodb)
     *
     * @return coupon - 优惠券ID(Mongodb)
     */
    public String getCoupon() {
        return coupon;
    }

    /**
     * 设置优惠券ID(Mongodb)
     *
     * @param coupon 优惠券ID(Mongodb)
     */
    public void setCoupon(String coupon) {
        this.coupon = coupon;
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
     * 获取未使用、1已使用、2已过期
     *
     * @return state - 未使用、1已使用、2已过期
     */
    public Byte getState() {
        return state;
    }

    /**
     * 设置未使用、1已使用、2已过期
     *
     * @param state 未使用、1已使用、2已过期
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Integer getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取优惠券数量
     *
     * @return numbers - 优惠券数量
     */
    public Integer getNumbers() {
        return numbers;
    }

    /**
     * 设置优惠券数量
     *
     * @param numbers 优惠券数量
     */
    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    /**
     * @return start_time
     */
    public Integer getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end_time
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    /**
     * @return is_new
     */
    public Integer getIsNew() {
        return isNew;
    }

    /**
     * @param isNew
     */
    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    /**
     * 获取0红包1后台配发
     *
     * @return type - 0红包1后台配发
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0红包1后台配发
     *
     * @param type 0红包1后台配发
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取优惠金额
     *
     * @return price - 优惠金额
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置优惠金额
     *
     * @param price 优惠金额
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取适用场景0全场,1菜场,2社区铺
     *
     * @return model - 适用场景0全场,1菜场,2社区铺
     */
    public Integer getModel() {
        return model;
    }

    /**
     * 设置适用场景0全场,1菜场,2社区铺
     *
     * @param model 适用场景0全场,1菜场,2社区铺
     */
    public void setModel(Integer model) {
        this.model = model;
    }

    /**
     * 获取0未激活1激活
     *
     * @return is_active - 0未激活1激活
     */
    public Byte getIsActive() {
        return isActive;
    }

    /**
     * 设置0未激活1激活
     *
     * @param isActive 0未激活1激活
     */
    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取券的等级
     *
     * @return level - 券的等级
     */
    public Byte getLevel() {
        return level;
    }

    /**
     * 设置券的等级
     *
     * @param level 券的等级
     */
    public void setLevel(Byte level) {
        this.level = level;
    }
}