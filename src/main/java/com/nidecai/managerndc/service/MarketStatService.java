package com.nidecai.managerndc.service;

/**
 * @Author: ldd
 */
public interface MarketStatService {

    /**
     * 前一天的订单量
     */
    void recordMarketStatDay();

    /**
     * 前一月的订单量
     */
    void recordMarketStatMonth(String lastMonthString);

    /**
     * 前一年的订单量
     */
    void recordMarketStatYear();

}
