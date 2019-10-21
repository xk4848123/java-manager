package com.nidecai.managerndc.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "hm_marketstat_tag")
public class MakertStatTag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 前一次日统计
     */
    @Column(name = "day_date")
    private String dayDate;

    /**
     * 前一次月统计
     */
    @Column(name = "month_date")
    private String monthDate;

    /**
     * 前一次年统计
     */
    @Column(name = "year_date")
    private String yearDate;

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
     * 获取前一次日统计
     *
     * @return day_date - 前一次日统计
     */
    public String getDayDate() {
        return dayDate;
    }

    /**
     * 设置前一次日统计
     *
     * @param dayDate 前一次日统计
     */
    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }

    /**
     * 获取前一次月统计
     *
     * @return month_date - 前一次月统计
     */
    public String getMonthDate() {
        return monthDate;
    }

    /**
     * 设置前一次月统计
     *
     * @param monthDate 前一次月统计
     */
    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }

    /**
     * 获取前一次年统计
     *
     * @return year_date - 前一次年统计
     */
    public String getYearDate() {
        return yearDate;
    }

    /**
     * 设置前一次年统计
     *
     * @param yearDate 前一次年统计
     */
    public void setYearDate(String yearDate) {
        this.yearDate = yearDate;
    }
}