package com.nidecai.managerndc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "hm_pay_order")
public class PayOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sno;

    private String tno;

    private String tid;

    private Integer roid;

    private Integer ctime;

    private String cstr;

    private Integer uid;

    private BigDecimal tprice;

    private BigDecimal rpay;

    private BigDecimal ifee;

    private BigDecimal cfee;

    private BigDecimal vfee;

    private BigDecimal ofee;

    private String sinfo;

    private String address;

    private BigDecimal mfee;

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
     * @return sno
     */
    public String getSno() {
        return sno;
    }

    /**
     * @param sno
     */
    public void setSno(String sno) {
        this.sno = sno;
    }

    /**
     * @return tno
     */
    public String getTno() {
        return tno;
    }

    /**
     * @param tno
     */
    public void setTno(String tno) {
        this.tno = tno;
    }

    /**
     * @return tid
     */
    public String getTid() {
        return tid;
    }

    /**
     * @param tid
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    /**
     * @return roid
     */
    public Integer getRoid() {
        return roid;
    }

    /**
     * @param roid
     */
    public void setRoid(Integer roid) {
        this.roid = roid;
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
     * @return cstr
     */
    public String getCstr() {
        return cstr;
    }

    /**
     * @param cstr
     */
    public void setCstr(String cstr) {
        this.cstr = cstr;
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
     * @return tprice
     */
    public BigDecimal getTprice() {
        return tprice;
    }

    /**
     * @param tprice
     */
    public void setTprice(BigDecimal tprice) {
        this.tprice = tprice;
    }

    /**
     * @return rpay
     */
    public BigDecimal getRpay() {
        return rpay;
    }

    /**
     * @param rpay
     */
    public void setRpay(BigDecimal rpay) {
        this.rpay = rpay;
    }

    /**
     * @return ifee
     */
    public BigDecimal getIfee() {
        return ifee;
    }

    /**
     * @param ifee
     */
    public void setIfee(BigDecimal ifee) {
        this.ifee = ifee;
    }

    /**
     * @return cfee
     */
    public BigDecimal getCfee() {
        return cfee;
    }

    /**
     * @param cfee
     */
    public void setCfee(BigDecimal cfee) {
        this.cfee = cfee;
    }

    /**
     * @return vfee
     */
    public BigDecimal getVfee() {
        return vfee;
    }

    /**
     * @param vfee
     */
    public void setVfee(BigDecimal vfee) {
        this.vfee = vfee;
    }

    /**
     * @return ofee
     */
    public BigDecimal getOfee() {
        return ofee;
    }

    /**
     * @param ofee
     */
    public void setOfee(BigDecimal ofee) {
        this.ofee = ofee;
    }

    /**
     * @return sinfo
     */
    public String getSinfo() {
        return sinfo;
    }

    /**
     * @param sinfo
     */
    public void setSinfo(String sinfo) {
        this.sinfo = sinfo;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return mfee
     */
    public BigDecimal getMfee() {
        return mfee;
    }

    /**
     * @param mfee
     */
    public void setMfee(BigDecimal mfee) {
        this.mfee = mfee;
    }
}