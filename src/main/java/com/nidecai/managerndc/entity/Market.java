package com.nidecai.managerndc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "hm_market")
public class Market implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 菜场名称
     */
    @Column(name = "ename")
    private String ename;

    /**
     * 联系人
     */
    @Column(name = "contacts")
    private String contacts;

    /**
     * 联系电话
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 省份
     */
    @Column(name = "province")
    private String province;

    /**
     * 城市
     */
    @Column(name = "city")
    private String city;

    /**
     * 区域
     */
    @Column(name = "area")
    private String area;

    /**
     * 摊位地址
     */
    @Column(name = "address")
    private String address;

    /**
     * 添加时间
     */
    @Column(name = "ctime")
    private Integer ctime;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private String longitude;

    /**
     * 纬度
     */
    @Column(name = "latitude")
    private String latitude;

    /**
     * 开始时间
     */
    @Column(name = "starttime")
    private String starttime;

    /**
     * 菜场关门时间
     */
    @Column(name = "endtime")
    private String endtime;

    /**
     * 0未开通1已开通
     */
    @Column(name = "state")
    private Byte state;

    /**
     * 面积
     */
    @Column(name = "size")
    private String size;

    /**
     * 菜场图片
     */
    @Column(name = "market_pic")
    private String marketPic;

    /**
     * 1存在0删除
     */
    @Column(name = "is_delete")
    private Byte isDelete;

    /**
     * 活动0已关闭1已开启
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 活动内容
     */
    @Column(name = "activity")
    private String activity;

    /**
     * 不配送区域
     */
    @Column(name = "no_pay")
    private String noPay;

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
     * 获取菜场名称
     *
     * @return ename - 菜场名称
     */
    public String getEname() {
        return ename;
    }

    /**
     * 设置菜场名称
     *
     * @param ename 菜场名称
     */
    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    /**
     * 获取联系人
     *
     * @return contacts - 联系人
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * 设置联系人
     *
     * @param contacts 联系人
     */
    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
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
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 获取区域
     *
     * @return area - 区域
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置区域
     *
     * @param area 区域
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * 获取摊位地址
     *
     * @return address - 摊位地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置摊位地址
     *
     * @param address 摊位地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取添加时间
     *
     * @return ctime - 添加时间
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     * 设置添加时间
     *
     * @param ctime 添加时间
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
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
        this.longitude = longitude == null ? null : longitude.trim();
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
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * 获取开始时间
     *
     * @return starttime - 开始时间
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * 设置开始时间
     *
     * @param starttime 开始时间
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    /**
     * 获取菜场关门时间
     *
     * @return endtime - 菜场关门时间
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * 设置菜场关门时间
     *
     * @param endtime 菜场关门时间
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }

    /**
     * 获取0未开通1已开通
     *
     * @return state - 0未开通1已开通
     */
    public Byte getState() {
        return state;
    }

    /**
     * 设置0未开通1已开通
     *
     * @param state 0未开通1已开通
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * 获取面积
     *
     * @return size - 面积
     */
    public String getSize() {
        return size;
    }

    /**
     * 设置面积
     *
     * @param size 面积
     */
    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    /**
     * 获取菜场图片
     *
     * @return market_pic - 菜场图片
     */
    public String getMarketPic() {
        return marketPic;
    }

    /**
     * 设置菜场图片
     *
     * @param marketPic 菜场图片
     */
    public void setMarketPic(String marketPic) {
        this.marketPic = marketPic == null ? null : marketPic.trim();
    }

    /**
     * 获取1存在0删除
     *
     * @return is_delete - 1存在0删除
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * 设置1存在0删除
     *
     * @param isDelete 1存在0删除
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取活动0已关闭1已开启
     *
     * @return status - 活动0已关闭1已开启
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置活动0已关闭1已开启
     *
     * @param status 活动0已关闭1已开启
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取活动内容
     *
     * @return activity - 活动内容
     */
    public String getActivity() {
        return activity;
    }

    /**
     * 设置活动内容
     *
     * @param activity 活动内容
     */
    public void setActivity(String activity) {
        this.activity = activity == null ? null : activity.trim();
    }

    /**
     * 获取不配送区域
     *
     * @return no_pay - 不配送区域
     */
    public String getNoPay() {
        return noPay;
    }

    /**
     * 设置不配送区域
     *
     * @param noPay 不配送区域
     */
    public void setNoPay(String noPay) {
        this.noPay = noPay == null ? null : noPay.trim();
    }
}