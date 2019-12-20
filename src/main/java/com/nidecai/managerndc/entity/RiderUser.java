package com.nidecai.managerndc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "hm_rider_user")
public class RiderUser implements Serializable {
    /**
     * 骑手名称
     */
    @Id
    private Integer rid;

    /**
     * 所属菜场
     */
    private Integer marketid;

    /**
     * 手机号
     */
    private String phone;

    private String name;

    /**
     * 身份证号
     */
    private String code;

    /**
     * 密码
     */
    private String password;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 0待审核1审核通过
     */
    private Byte state;

    /**
     * 最后上报时间
     */
    @Column(name = "last_time")
    private Integer lastTime;

    private Integer ctime;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 1正常0禁用
     */
    private Byte status;

    /**
     * 0结束接单1接单
     */
    @Column(name = "is_order")
    private Byte isOrder;

    @Column(name = "device_number")
    private String deviceNumber;

    /**
     * accid
     */
    private String accid;

    private String token;

    @Column(name = "test_user")
    private Integer testUser;

    /**
     * 登录凭证
     */
    @Column(name = "login_token")
    private String loginToken;

    /**
     * 累计VIP推广奖励
     */
    @Column(name = "vip_promotion_reward")
    private BigDecimal vipPromotionReward;

    /**
     * 已提现VIP推广奖励
     */
    @Column(name = "vip_promotion_rewardget")
    private BigDecimal vipPromotionRewardget;

    /**
     * 未提现VIP推广奖励
     */
    @Column(name = "vip_promotion_rewardover")
    private BigDecimal vipPromotionRewardover;

    /**
     * 公众号openid
     */
    @Column(name = "public_openid")
    private String publicOpenid;

    /**
     * 备选菜场
     */
    @Column(name = "market_backup")
    private Integer marketBackup;

    private static final long serialVersionUID = 1L;

    /**
     * 获取骑手名称
     *
     * @return rid - 骑手名称
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * 设置骑手名称
     *
     * @param rid 骑手名称
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * 获取所属菜场
     *
     * @return marketid - 所属菜场
     */
    public Integer getMarketid() {
        return marketid;
    }

    /**
     * 设置所属菜场
     *
     * @param marketid 所属菜场
     */
    public void setMarketid(Integer marketid) {
        this.marketid = marketid;
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取身份证号
     *
     * @return code - 身份证号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置身份证号
     *
     * @param code 身份证号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取0待审核1审核通过
     *
     * @return state - 0待审核1审核通过
     */
    public Byte getState() {
        return state;
    }

    /**
     * 设置0待审核1审核通过
     *
     * @param state 0待审核1审核通过
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * 获取最后上报时间
     *
     * @return last_time - 最后上报时间
     */
    public Integer getLastTime() {
        return lastTime;
    }

    /**
     * 设置最后上报时间
     *
     * @param lastTime 最后上报时间
     */
    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
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
     * 获取加密盐
     *
     * @return salt - 加密盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置加密盐
     *
     * @param salt 加密盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取1正常0禁用
     *
     * @return status - 1正常0禁用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置1正常0禁用
     *
     * @param status 1正常0禁用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取0结束接单1接单
     *
     * @return is_order - 0结束接单1接单
     */
    public Byte getIsOrder() {
        return isOrder;
    }

    /**
     * 设置0结束接单1接单
     *
     * @param isOrder 0结束接单1接单
     */
    public void setIsOrder(Byte isOrder) {
        this.isOrder = isOrder;
    }

    /**
     * @return device_number
     */
    public String getDeviceNumber() {
        return deviceNumber;
    }

    /**
     * @param deviceNumber
     */
    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    /**
     * 获取accid
     *
     * @return accid - accid
     */
    public String getAccid() {
        return accid;
    }

    /**
     * 设置accid
     *
     * @param accid accid
     */
    public void setAccid(String accid) {
        this.accid = accid;
    }

    /**
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return test_user
     */
    public Integer getTestUser() {
        return testUser;
    }

    /**
     * @param testUser
     */
    public void setTestUser(Integer testUser) {
        this.testUser = testUser;
    }

    /**
     * 获取登录凭证
     *
     * @return login_token - 登录凭证
     */
    public String getLoginToken() {
        return loginToken;
    }

    /**
     * 设置登录凭证
     *
     * @param loginToken 登录凭证
     */
    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    /**
     * 获取累计VIP推广奖励
     *
     * @return vip_promotion_reward - 累计VIP推广奖励
     */
    public BigDecimal getVipPromotionReward() {
        return vipPromotionReward;
    }

    /**
     * 设置累计VIP推广奖励
     *
     * @param vipPromotionReward 累计VIP推广奖励
     */
    public void setVipPromotionReward(BigDecimal vipPromotionReward) {
        this.vipPromotionReward = vipPromotionReward;
    }

    /**
     * 获取已提现VIP推广奖励
     *
     * @return vip_promotion_rewardget - 已提现VIP推广奖励
     */
    public BigDecimal getVipPromotionRewardget() {
        return vipPromotionRewardget;
    }

    /**
     * 设置已提现VIP推广奖励
     *
     * @param vipPromotionRewardget 已提现VIP推广奖励
     */
    public void setVipPromotionRewardget(BigDecimal vipPromotionRewardget) {
        this.vipPromotionRewardget = vipPromotionRewardget;
    }

    /**
     * 获取未提现VIP推广奖励
     *
     * @return vip_promotion_rewardover - 未提现VIP推广奖励
     */
    public BigDecimal getVipPromotionRewardover() {
        return vipPromotionRewardover;
    }

    /**
     * 设置未提现VIP推广奖励
     *
     * @param vipPromotionRewardover 未提现VIP推广奖励
     */
    public void setVipPromotionRewardover(BigDecimal vipPromotionRewardover) {
        this.vipPromotionRewardover = vipPromotionRewardover;
    }

    /**
     * 获取公众号openid
     *
     * @return public_openid - 公众号openid
     */
    public String getPublicOpenid() {
        return publicOpenid;
    }

    /**
     * 设置公众号openid
     *
     * @param publicOpenid 公众号openid
     */
    public void setPublicOpenid(String publicOpenid) {
        this.publicOpenid = publicOpenid;
    }

    /**
     * 获取备选菜场
     *
     * @return market_backup - 备选菜场
     */
    public Integer getMarketBackup() {
        return marketBackup;
    }

    /**
     * 设置备选菜场
     *
     * @param marketBackup 备选菜场
     */
    public void setMarketBackup(Integer marketBackup) {
        this.marketBackup = marketBackup;
    }
}