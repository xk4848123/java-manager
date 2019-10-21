package com.nidecai.managerndc.common.entitycommon;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: ldd
 */
@Data
public class MakertStatResultDTO implements Serializable {

    private static final long serialVersionUID = 197018972999527001L;

    private Integer marketId;

    private Integer orderNum;

    private BigDecimal orderPay;

    private BigDecimal orderRiderPay;

    private BigDecimal orderCoupon;

    private BigDecimal orderIntegral;

    private BigDecimal orderActivity;

    private BigDecimal orderVipRelief;

    private String date;

}


