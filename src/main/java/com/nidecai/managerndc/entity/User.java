package com.nidecai.managerndc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "hm_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 0正常用户1新用户送红包2老用户送红包
     */
    @Column(name = "user_type")
    private Integer userType;

    /**
     *  昵称
     */
    private String nick;

    /**
     * 网易ID
     */
    private String accid;

    private String token;

    /**
     * 用户积分
     */
    private Integer integral;

    /**
     * 用户头像
     */
    private String pic;

    /**
     * 创建时间
     */
    private Integer ctime;

    /**
     * 1正常0禁用
     */
    private Byte status;

    private String salt;

    private Integer prizetimes;

    @Column(name = "late_marketid")
    private Integer lateMarketid;

    /**
     * 被推广人父级id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 小程序openid
     */
    @Column(name = "applets_openid")
    private String appletsOpenid;

    /**
     * 0外部1内部
     */
    @Column(name = "is_inside")
    private Integer isInside;

    /**
     * 分享人id
     */
    @Column(name = "share_id")
    private Integer shareId;

    @Column(name = "visit_time")
    private String visitTime;

    @Column(name = "login_token")
    private String loginToken;

    /**
     * 网易云信Token
     */
    @Column(name = "yx_token")
    private String yxToken;

    /**
     * VIP激活状态
     */
    @Column(name = "vip_status")
    private Integer vipStatus;

    /**
     * VIP等级
     */
    @Column(name = "vip_level")
    private Integer vipLevel;

    /**
     * VIP第一次激活时间
     */
    @Column(name = "vip_start")
    private Integer vipStart;

    /**
     * VIP到期时间
     */
    @Column(name = "vip_expire")
    private Integer vipExpire;

    /**
     * VIP上一次停止时间
     */
    @Column(name = "vip_stop")
    private Integer vipStop;

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
     * 获取0正常用户1新用户送红包2老用户送红包
     *
     * @return user_type - 0正常用户1新用户送红包2老用户送红包
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置0正常用户1新用户送红包2老用户送红包
     *
     * @param userType 0正常用户1新用户送红包2老用户送红包
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取 昵称
     *
     * @return nick -  昵称
     */
    public String getNick() {
        return nick;
    }

    /**
     * 设置 昵称
     *
     * @param nick  昵称
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * 获取网易ID
     *
     * @return accid - 网易ID
     */
    public String getAccid() {
        return accid;
    }

    /**
     * 设置网易ID
     *
     * @param accid 网易ID
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
     * 获取用户积分
     *
     * @return integral - 用户积分
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * 设置用户积分
     *
     * @param integral 用户积分
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取用户头像
     *
     * @return pic - 用户头像
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置用户头像
     *
     * @param pic 用户头像
     */
    public void setPic(String pic) {
        this.pic = pic;
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
     * @return salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return prizetimes
     */
    public Integer getPrizetimes() {
        return prizetimes;
    }

    /**
     * @param prizetimes
     */
    public void setPrizetimes(Integer prizetimes) {
        this.prizetimes = prizetimes;
    }

    /**
     * @return late_marketid
     */
    public Integer getLateMarketid() {
        return lateMarketid;
    }

    /**
     * @param lateMarketid
     */
    public void setLateMarketid(Integer lateMarketid) {
        this.lateMarketid = lateMarketid;
    }

    /**
     * 获取被推广人父级id
     *
     * @return parent_id - 被推广人父级id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置被推广人父级id
     *
     * @param parentId 被推广人父级id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取微信openid
     *
     * @return openid - 微信openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置微信openid
     *
     * @param openid 微信openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取小程序openid
     *
     * @return applets_openid - 小程序openid
     */
    public String getAppletsOpenid() {
        return appletsOpenid;
    }

    /**
     * 设置小程序openid
     *
     * @param appletsOpenid 小程序openid
     */
    public void setAppletsOpenid(String appletsOpenid) {
        this.appletsOpenid = appletsOpenid;
    }

    /**
     * 获取0外部1内部
     *
     * @return is_inside - 0外部1内部
     */
    public Integer getIsInside() {
        return isInside;
    }

    /**
     * 设置0外部1内部
     *
     * @param isInside 0外部1内部
     */
    public void setIsInside(Integer isInside) {
        this.isInside = isInside;
    }

    /**
     * 获取分享人id
     *
     * @return share_id - 分享人id
     */
    public Integer getShareId() {
        return shareId;
    }

    /**
     * 设置分享人id
     *
     * @param shareId 分享人id
     */
    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    /**
     * @return visit_time
     */
    public String getVisitTime() {
        return visitTime;
    }

    /**
     * @param visitTime
     */
    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    /**
     * @return login_token
     */
    public String getLoginToken() {
        return loginToken;
    }

    /**
     * @param loginToken
     */
    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    /**
     * 获取网易云信Token
     *
     * @return yx_token - 网易云信Token
     */
    public String getYxToken() {
        return yxToken;
    }

    /**
     * 设置网易云信Token
     *
     * @param yxToken 网易云信Token
     */
    public void setYxToken(String yxToken) {
        this.yxToken = yxToken;
    }

    /**
     * 获取VIP激活状态
     *
     * @return vip_status - VIP激活状态
     */
    public Integer getVipStatus() {
        return vipStatus;
    }

    /**
     * 设置VIP激活状态
     *
     * @param vipStatus VIP激活状态
     */
    public void setVipStatus(Integer vipStatus) {
        this.vipStatus = vipStatus;
    }

    /**
     * 获取VIP等级
     *
     * @return vip_level - VIP等级
     */
    public Integer getVipLevel() {
        return vipLevel;
    }

    /**
     * 设置VIP等级
     *
     * @param vipLevel VIP等级
     */
    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    /**
     * 获取VIP第一次激活时间
     *
     * @return vip_start - VIP第一次激活时间
     */
    public Integer getVipStart() {
        return vipStart;
    }

    /**
     * 设置VIP第一次激活时间
     *
     * @param vipStart VIP第一次激活时间
     */
    public void setVipStart(Integer vipStart) {
        this.vipStart = vipStart;
    }

    /**
     * 获取VIP到期时间
     *
     * @return vip_expire - VIP到期时间
     */
    public Integer getVipExpire() {
        return vipExpire;
    }

    /**
     * 设置VIP到期时间
     *
     * @param vipExpire VIP到期时间
     */
    public void setVipExpire(Integer vipExpire) {
        this.vipExpire = vipExpire;
    }

    /**
     * 获取VIP上一次停止时间
     *
     * @return vip_stop - VIP上一次停止时间
     */
    public Integer getVipStop() {
        return vipStop;
    }

    /**
     * 设置VIP上一次停止时间
     *
     * @param vipStop VIP上一次停止时间
     */
    public void setVipStop(Integer vipStop) {
        this.vipStop = vipStop;
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
}