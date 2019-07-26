package com.nidecai.managerndc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "hm_shopown")
public class Shopown implements Serializable {
    /**
     * 商户id
     */
    @Id
    @Column(name = "pid")
    private Integer pid;

    /**
     * 商户图片
     */
    @Column(name = "pic")
    private String pic;

    /**
     * 联系电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 商户名称
     */
    @Column(name = "sname")
    private String sname;

    /**
     * 真实名称
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 店铺经营范围
     */
    @Column(name = "scope_business")
    private String scopeBusiness;

    /**
     * 工商营业执照
     */
    @Column(name = "business_license")
    private String businessLicense;

    /**
     * 食品许可证
     */
    @Column(name = "food_license")
    private String foodLicense;

    /**
     * 身份证图片
     */
    @Column(name = "card_pic")
    private String cardPic;

    /**
     * 商户营业额
     */
    @Column(name = "money")
    private BigDecimal money;

    /**
     * 余额
     */
    @Column(name = "balance")
    private BigDecimal balance;

    /**
     * 所在菜场id
     */
    @Column(name = "marketid")
    private Integer marketid;

    /**
     * 是否推荐1未推荐
     */
    @Column(name = "is_hot")
    private Byte isHot;

    /**
     * 是否推荐1推荐
     */
    @Column(name = "is_recommend")
    private Byte isRecommend;

    /**
     * 等级
     */
    @Column(name = "grade")
    private String grade;

    /**
     * 摊位门牌号
     */
    @Column(name = "address")
    private String address;

    /**
     * 网易账号
     */
    @Column(name = "accid")
    private String accid;

    /**
     * 网易token
     */
    @Column(name = "token")
    private String token;

    /**
     * 手机设备号
     */
    @Column(name = "device_number")
    private String deviceNumber;

    /**
     * 摄像头id
     */
    @Column(name = "cameraid")
    private String cameraid;

    /**
     * 房间号
     */
    @Column(name = "roomid")
    private String roomid;

    /**
     * 2预约中1打样0开张
     */
    @Column(name = "status")
    private Integer status;

    @Column(name = "ctime")
    private Integer ctime;

    /**
     * 加密盐
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 1为菜场自提点
     */
    @Column(name = "is_since")
    private Boolean isSince;

    /**
     * 打印机编号
     */
    @Column(name = "printer_num")
    private String printerNum;

    /**
     * 打印机key
     */
    @Column(name = "printer_key")
    private String printerKey;

    /**
     * 0审核中1审核通过2审核失败
     */
    @Column(name = "examine")
    private Integer examine;

    /**
     * 0不显示1显示
     */
    @Column(name = "is_show")
    private Integer isShow;

    /**
     * 1正式商户0测试商户
     */
    @Column(name = "type")
    private Byte type;

    /**
     * 0关闭预约1开启预约
     */
    @Column(name = "is_appointment")
    private Integer isAppointment;

    /**
     * 推荐值，应急
     */
    @Column(name = "recommend")
    private Integer recommend;

    /**
     * 审核反馈信息
     */
    @Column(name = "examine_info")
    private String examineInfo;

    /**
     * 1小米2华为
     */
    @Column(name = "phoneType")
    private String phonetype;

    /**
     * 推送token
     */
    @Column(name = "pushToken")
    private String pushtoken;

    /**
     * 0优惠1不优惠
     */
    @Column(name = "unFavorable")
    private Integer unfavorable;

    /**
     * 网易云信Token
     */
    @Column(name = "login_token")
    private String loginToken;

    /**
     * 登录凭证
     */
    @Column(name = "yx_token")
    private String yxToken;

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
     * 商铺模式：0菜场商户1便利店商户
     */
    @Column(name = "mode")
    private Byte mode;

    private static final long serialVersionUID = 1L;

    /**
     * 获取商户id
     *
     * @return pid - 商户id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置商户id
     *
     * @param pid 商户id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取商户图片
     *
     * @return pic - 商户图片
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置商户图片
     *
     * @param pic 商户图片
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取商户名称
     *
     * @return sname - 商户名称
     */
    public String getSname() {
        return sname;
    }

    /**
     * 设置商户名称
     *
     * @param sname 商户名称
     */
    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    /**
     * 获取真实名称
     *
     * @return real_name - 真实名称
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实名称
     *
     * @param realName 真实名称
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 获取店铺经营范围
     *
     * @return scope_business - 店铺经营范围
     */
    public String getScopeBusiness() {
        return scopeBusiness;
    }

    /**
     * 设置店铺经营范围
     *
     * @param scopeBusiness 店铺经营范围
     */
    public void setScopeBusiness(String scopeBusiness) {
        this.scopeBusiness = scopeBusiness == null ? null : scopeBusiness.trim();
    }

    /**
     * 获取工商营业执照
     *
     * @return business_license - 工商营业执照
     */
    public String getBusinessLicense() {
        return businessLicense;
    }

    /**
     * 设置工商营业执照
     *
     * @param businessLicense 工商营业执照
     */
    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    /**
     * 获取食品许可证
     *
     * @return food_license - 食品许可证
     */
    public String getFoodLicense() {
        return foodLicense;
    }

    /**
     * 设置食品许可证
     *
     * @param foodLicense 食品许可证
     */
    public void setFoodLicense(String foodLicense) {
        this.foodLicense = foodLicense == null ? null : foodLicense.trim();
    }

    /**
     * 获取身份证图片
     *
     * @return card_pic - 身份证图片
     */
    public String getCardPic() {
        return cardPic;
    }

    /**
     * 设置身份证图片
     *
     * @param cardPic 身份证图片
     */
    public void setCardPic(String cardPic) {
        this.cardPic = cardPic == null ? null : cardPic.trim();
    }

    /**
     * 获取商户营业额
     *
     * @return money - 商户营业额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置商户营业额
     *
     * @param money 商户营业额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取余额
     *
     * @return balance - 余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 设置余额
     *
     * @param balance 余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 获取所在菜场id
     *
     * @return marketid - 所在菜场id
     */
    public Integer getMarketid() {
        return marketid;
    }

    /**
     * 设置所在菜场id
     *
     * @param marketid 所在菜场id
     */
    public void setMarketid(Integer marketid) {
        this.marketid = marketid;
    }

    /**
     * 获取是否推荐1未推荐
     *
     * @return is_hot - 是否推荐1未推荐
     */
    public Byte getIsHot() {
        return isHot;
    }

    /**
     * 设置是否推荐1未推荐
     *
     * @param isHot 是否推荐1未推荐
     */
    public void setIsHot(Byte isHot) {
        this.isHot = isHot;
    }

    /**
     * 获取是否推荐1推荐
     *
     * @return is_recommend - 是否推荐1推荐
     */
    public Byte getIsRecommend() {
        return isRecommend;
    }

    /**
     * 设置是否推荐1推荐
     *
     * @param isRecommend 是否推荐1推荐
     */
    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * 获取等级
     *
     * @return grade - 等级
     */
    public String getGrade() {
        return grade;
    }

    /**
     * 设置等级
     *
     * @param grade 等级
     */
    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    /**
     * 获取摊位门牌号
     *
     * @return address - 摊位门牌号
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置摊位门牌号
     *
     * @param address 摊位门牌号
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取网易账号
     *
     * @return accid - 网易账号
     */
    public String getAccid() {
        return accid;
    }

    /**
     * 设置网易账号
     *
     * @param accid 网易账号
     */
    public void setAccid(String accid) {
        this.accid = accid == null ? null : accid.trim();
    }

    /**
     * 获取网易token
     *
     * @return token - 网易token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置网易token
     *
     * @param token 网易token
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * 获取手机设备号
     *
     * @return device_number - 手机设备号
     */
    public String getDeviceNumber() {
        return deviceNumber;
    }

    /**
     * 设置手机设备号
     *
     * @param deviceNumber 手机设备号
     */
    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber == null ? null : deviceNumber.trim();
    }

    /**
     * 获取摄像头id
     *
     * @return cameraid - 摄像头id
     */
    public String getCameraid() {
        return cameraid;
    }

    /**
     * 设置摄像头id
     *
     * @param cameraid 摄像头id
     */
    public void setCameraid(String cameraid) {
        this.cameraid = cameraid == null ? null : cameraid.trim();
    }

    /**
     * 获取房间号
     *
     * @return roomid - 房间号
     */
    public String getRoomid() {
        return roomid;
    }

    /**
     * 设置房间号
     *
     * @param roomid 房间号
     */
    public void setRoomid(String roomid) {
        this.roomid = roomid == null ? null : roomid.trim();
    }

    /**
     * 获取2预约中1打样0开张
     *
     * @return status - 2预约中1打样0开张
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置2预约中1打样0开张
     *
     * @param status 2预约中1打样0开张
     */
    public void setStatus(Integer status) {
        this.status = status;
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
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 获取1为菜场自提点
     *
     * @return is_since - 1为菜场自提点
     */
    public Boolean getIsSince() {
        return isSince;
    }

    /**
     * 设置1为菜场自提点
     *
     * @param isSince 1为菜场自提点
     */
    public void setIsSince(Boolean isSince) {
        this.isSince = isSince;
    }

    /**
     * 获取打印机编号
     *
     * @return printer_num - 打印机编号
     */
    public String getPrinterNum() {
        return printerNum;
    }

    /**
     * 设置打印机编号
     *
     * @param printerNum 打印机编号
     */
    public void setPrinterNum(String printerNum) {
        this.printerNum = printerNum == null ? null : printerNum.trim();
    }

    /**
     * 获取打印机key
     *
     * @return printer_key - 打印机key
     */
    public String getPrinterKey() {
        return printerKey;
    }

    /**
     * 设置打印机key
     *
     * @param printerKey 打印机key
     */
    public void setPrinterKey(String printerKey) {
        this.printerKey = printerKey == null ? null : printerKey.trim();
    }

    /**
     * 获取0审核中1审核通过2审核失败
     *
     * @return examine - 0审核中1审核通过2审核失败
     */
    public Integer getExamine() {
        return examine;
    }

    /**
     * 设置0审核中1审核通过2审核失败
     *
     * @param examine 0审核中1审核通过2审核失败
     */
    public void setExamine(Integer examine) {
        this.examine = examine;
    }

    /**
     * 获取0不显示1显示
     *
     * @return is_show - 0不显示1显示
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * 设置0不显示1显示
     *
     * @param isShow 0不显示1显示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * 获取1正式商户0测试商户
     *
     * @return type - 1正式商户0测试商户
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置1正式商户0测试商户
     *
     * @param type 1正式商户0测试商户
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取0关闭预约1开启预约
     *
     * @return is_appointment - 0关闭预约1开启预约
     */
    public Integer getIsAppointment() {
        return isAppointment;
    }

    /**
     * 设置0关闭预约1开启预约
     *
     * @param isAppointment 0关闭预约1开启预约
     */
    public void setIsAppointment(Integer isAppointment) {
        this.isAppointment = isAppointment;
    }

    /**
     * 获取推荐值，应急
     *
     * @return recommend - 推荐值，应急
     */
    public Integer getRecommend() {
        return recommend;
    }

    /**
     * 设置推荐值，应急
     *
     * @param recommend 推荐值，应急
     */
    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    /**
     * 获取审核反馈信息
     *
     * @return examine_info - 审核反馈信息
     */
    public String getExamineInfo() {
        return examineInfo;
    }

    /**
     * 设置审核反馈信息
     *
     * @param examineInfo 审核反馈信息
     */
    public void setExamineInfo(String examineInfo) {
        this.examineInfo = examineInfo == null ? null : examineInfo.trim();
    }

    /**
     * 获取1小米2华为
     *
     * @return phoneType - 1小米2华为
     */
    public String getPhonetype() {
        return phonetype;
    }

    /**
     * 设置1小米2华为
     *
     * @param phonetype 1小米2华为
     */
    public void setPhonetype(String phonetype) {
        this.phonetype = phonetype == null ? null : phonetype.trim();
    }

    /**
     * 获取推送token
     *
     * @return pushToken - 推送token
     */
    public String getPushtoken() {
        return pushtoken;
    }

    /**
     * 设置推送token
     *
     * @param pushtoken 推送token
     */
    public void setPushtoken(String pushtoken) {
        this.pushtoken = pushtoken == null ? null : pushtoken.trim();
    }

    /**
     * 获取0优惠1不优惠
     *
     * @return unFavorable - 0优惠1不优惠
     */
    public Integer getUnfavorable() {
        return unfavorable;
    }

    /**
     * 设置0优惠1不优惠
     *
     * @param unfavorable 0优惠1不优惠
     */
    public void setUnfavorable(Integer unfavorable) {
        this.unfavorable = unfavorable;
    }

    /**
     * 获取网易云信Token
     *
     * @return login_token - 网易云信Token
     */
    public String getLoginToken() {
        return loginToken;
    }

    /**
     * 设置网易云信Token
     *
     * @param loginToken 网易云信Token
     */
    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken == null ? null : loginToken.trim();
    }

    /**
     * 获取登录凭证
     *
     * @return yx_token - 登录凭证
     */
    public String getYxToken() {
        return yxToken;
    }

    /**
     * 设置登录凭证
     *
     * @param yxToken 登录凭证
     */
    public void setYxToken(String yxToken) {
        this.yxToken = yxToken == null ? null : yxToken.trim();
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
        this.publicOpenid = publicOpenid == null ? null : publicOpenid.trim();
    }

    /**
     * 获取商铺模式：0菜场商户1便利店商户
     *
     * @return mode - 商铺模式：0菜场商户1便利店商户
     */
    public Byte getMode() {
        return mode;
    }

    /**
     * 设置商铺模式：0菜场商户1便利店商户
     *
     * @param mode 商铺模式：0菜场商户1便利店商户
     */
    public void setMode(Byte mode) {
        this.mode = mode;
    }
}