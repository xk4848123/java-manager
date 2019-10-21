package com.nidecai.managerndc.service.impl;

import com.nidecai.managerndc.common.codeutil.DateUtil;
import com.nidecai.managerndc.common.entitycommon.MakertStatResultDTO;
import com.nidecai.managerndc.entity.MakertStatDay;
import com.nidecai.managerndc.entity.MakertStatMonth;
import com.nidecai.managerndc.entity.MakertStatYear;
import com.nidecai.managerndc.mapper.*;
import com.nidecai.managerndc.service.MarketStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @Author: ldd
 */
@Service
public class MarketStatServiceImpl implements MarketStatService {

    @Autowired
    private RiderOrderMapper riderOrderMapper;

    @Autowired
    private MarketMapper marketMapper;

    @Autowired
    private MakertStatDayMapper makertStatDayMapper;

    @Autowired
    private MakertStatMonthMapper makertStatMonthMapper;

    @Autowired
    private MakertStatYearMapper makertStatYearMapper;


    @Override
    public void recordMarketStatDay() {
        try {
            //统计前一天订单
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long current = System.currentTimeMillis();    //当前时间毫秒数
            long endT = current / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();  //今天零点零分零秒的毫秒数
            String end = simpleDateFormat.format(endT);
            long zeroT = endT - 24 * 60 * 60 * 1000;
            String start = simpleDateFormat.format(zeroT);
            long timeStart = (simpleDateFormat.parse(start).getTime() / 1000);
            long timeEnd = (simpleDateFormat.parse(end).getTime() / 1000);
            String currentTime = DateUtil.getCurrentTimeByDay(new Date());

            List<MakertStatResultDTO> maketStatDay = riderOrderMapper.findMaketStatDay(timeStart, timeEnd);
            //查出所有状态为1的菜场.
            List<Integer> marketId = marketMapper.selectMarketId((byte) 1);
            for (Integer integer : marketId) {
                for (MakertStatResultDTO makertStatResultDTO : maketStatDay) {
                    //判断昨天菜场是否下过单如果不等于则没有下过单.
                    MakertStatDay marketStatDay = new MakertStatDay();
                    if (!integer.equals(makertStatResultDTO.getMarketId())) {
                        marketStatDay.setMarketid(integer);
                        marketStatDay.setOrderNum(0);
                        marketStatDay.setOrderPay(new BigDecimal(0));
                        marketStatDay.setOrderRiderPay(new BigDecimal(0));
                        marketStatDay.setOrderCoupon(new BigDecimal(0));
                        marketStatDay.setOrderIntegral(new BigDecimal(0));
                        marketStatDay.setOrderActivity(new BigDecimal(0));
                        marketStatDay.setOrderVipRelief(new BigDecimal(0));
                        marketStatDay.setDate(currentTime);
                    } else {
                        if (null == makertStatResultDTO.getOrderCoupon()) {
                            makertStatResultDTO.setOrderCoupon(new BigDecimal(0));
                        }
                        if (null == makertStatResultDTO.getOrderIntegral()) {
                            makertStatResultDTO.setOrderIntegral(new BigDecimal(0));
                        }
                        if (null == makertStatResultDTO.getOrderActivity()) {
                            makertStatResultDTO.setOrderActivity(new BigDecimal(0));
                        }
                        if (null == makertStatResultDTO.getOrderVipRelief()) {
                            makertStatResultDTO.setOrderVipRelief(new BigDecimal(0));
                        }
                        marketStatDay.setMarketid(makertStatResultDTO.getMarketId());
                        marketStatDay.setOrderNum(makertStatResultDTO.getOrderNum());
                        marketStatDay.setOrderPay(makertStatResultDTO.getOrderPay());
                        marketStatDay.setOrderRiderPay(makertStatResultDTO.getOrderRiderPay());
                        marketStatDay.setOrderCoupon(makertStatResultDTO.getOrderCoupon());
                        marketStatDay.setOrderIntegral(makertStatResultDTO.getOrderIntegral());
                        marketStatDay.setOrderActivity(makertStatResultDTO.getOrderActivity());
                        marketStatDay.setOrderVipRelief(makertStatResultDTO.getOrderVipRelief());
                        marketStatDay.setDate(currentTime);
                    }
                    makertStatDayMapper.insertSelective(marketStatDay);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void recordMarketStatMonth() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date); // 设置为当前时间
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
            calendar.set(Calendar.DAY_OF_MONTH, 1); // 设置为上一个月第一天
            //将小时至0
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            //将分钟至0
            calendar.set(Calendar.MINUTE, 0);
            //将秒至0
            calendar.set(Calendar.SECOND, 0);
            date = calendar.getTime();
            String accDate = format.format(date);    //上一个月时间为00：00：00开始
            long lasetTimeStart = (format.parse(accDate).getTime() / 1000);

            Date date1 = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date1);
            //设置为当月最后一天
            c.set(Calendar.MONTH, calendar.get(Calendar.MONTH)); // 设置为上一个月
            c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
            //将小时至23
            c.set(Calendar.HOUR_OF_DAY, 23);
            //将分钟至59
            c.set(Calendar.MINUTE, 59);
            //将秒至59
            c.set(Calendar.SECOND, 59);
            date1 = c.getTime();
            String format1 = format.format(date1);
            long lastTimeEnd = (format.parse(format1).getTime() / 1000);
            String currentTime = DateUtil.getCurrentTimeByMonth(new Date());

            List<MakertStatResultDTO> maketStatDay = riderOrderMapper.findMaketStatDay(lasetTimeStart, lastTimeEnd);

            //查出所有状态为1的菜场.
            List<Integer> marketId = marketMapper.selectMarketId((byte) 1);
            for (Integer integer : marketId) {
                for (MakertStatResultDTO makertStatResultDTO : maketStatDay) {
                    //判断昨天菜场是否下过单如果不等于则没有下过单.
                    MakertStatMonth makertStatMonth = new MakertStatMonth();
                    if (!integer.equals(makertStatResultDTO.getMarketId())) {
                        makertStatMonth.setMarketid(integer);
                        makertStatMonth.setOrderNum(0);
                        makertStatMonth.setOrderPay(new BigDecimal(0));
                        makertStatMonth.setOrderRiderPay(new BigDecimal(0));
                        makertStatMonth.setOrderCoupon(new BigDecimal(0));
                        makertStatMonth.setOrderIntegral(new BigDecimal(0));
                        makertStatMonth.setOrderActivity(new BigDecimal(0));
                        makertStatMonth.setOrderVipRelief(new BigDecimal(0));
                        makertStatMonth.setDate(currentTime);
                    } else {
                        if (null == makertStatResultDTO.getOrderCoupon()) {
                            makertStatResultDTO.setOrderCoupon(new BigDecimal(0));
                        }
                        if (null == makertStatResultDTO.getOrderIntegral()) {
                            makertStatResultDTO.setOrderIntegral(new BigDecimal(0));
                        }
                        if (null == makertStatResultDTO.getOrderActivity()) {
                            makertStatResultDTO.setOrderActivity(new BigDecimal(0));
                        }
                        if (null == makertStatResultDTO.getOrderVipRelief()) {
                            makertStatResultDTO.setOrderVipRelief(new BigDecimal(0));
                        }
                        makertStatMonth.setMarketid(makertStatResultDTO.getMarketId());
                        makertStatMonth.setOrderNum(makertStatResultDTO.getOrderNum());
                        makertStatMonth.setOrderPay(makertStatResultDTO.getOrderPay());
                        makertStatMonth.setOrderRiderPay(makertStatResultDTO.getOrderRiderPay());
                        makertStatMonth.setOrderCoupon(makertStatResultDTO.getOrderCoupon());
                        makertStatMonth.setOrderIntegral(makertStatResultDTO.getOrderIntegral());
                        makertStatMonth.setOrderActivity(makertStatResultDTO.getOrderActivity());
                        makertStatMonth.setOrderVipRelief(makertStatResultDTO.getOrderVipRelief());
                        makertStatMonth.setDate(currentTime);
                    }
                    makertStatMonthMapper.insertSelective(makertStatMonth);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void recordMarketStatYear() {

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar instance = Calendar.getInstance();
            Date date = new Date();
            instance.setTime(date);
            instance.add(Calendar.YEAR, -1);
            instance.set(Calendar.MONTH, 0); // 设置为上一个月
            instance.set(Calendar.DAY_OF_YEAR, 1); // 设置为上一个月
            //将小时至0
            instance.set(Calendar.HOUR_OF_DAY, 0);
            //将分钟至0
            instance.set(Calendar.MINUTE, 0);
            //将秒至0
            instance.set(Calendar.SECOND, 0);
            Date time = instance.getTime();
            String format = simpleDateFormat.format(time);
            long lastYearStart = simpleDateFormat.parse(format).getTime() / 1000;

            Calendar c = Calendar.getInstance();
            Date date1 = new Date();
            c.setTime(date1);
            c.add(Calendar.YEAR, -1);
            c.set(Calendar.MONTH, 11); // 设置为上一个月
            c.set(Calendar.DAY_OF_MONTH, 31);
            //将小时至23
            c.set(Calendar.HOUR_OF_DAY, 23);
            //将分钟至59
            c.set(Calendar.MINUTE, 59);
            //将秒至59
            c.set(Calendar.SECOND, 59);
            String format1 = simpleDateFormat.format(c.getTime());
            long lastYearEnd = simpleDateFormat.parse(format1).getTime() / 1000;
            String currentTime = DateUtil.getCurrentTimeByMonth(new Date());

            List<MakertStatResultDTO> maketStatDay = riderOrderMapper.findMaketStatDay(lastYearStart, lastYearEnd);

            //查出所有状态为1的菜场.
            List<Integer> marketId = marketMapper.selectMarketId((byte) 1);
            for (Integer integer : marketId) {
                for (MakertStatResultDTO makertStatResultDTO : maketStatDay) {
                    //判断昨天菜场是否下过单如果不等于则没有下过单.
                    MakertStatYear makertStatYear = new MakertStatYear();
                    if (!integer.equals(makertStatResultDTO.getMarketId())) {
                        makertStatYear.setMarketid(integer);
                        makertStatYear.setOrderNum(0);
                        makertStatYear.setOrderPay(new BigDecimal(0));
                        makertStatYear.setOrderRiderPay(new BigDecimal(0));
                        makertStatYear.setOrderCoupon(new BigDecimal(0));
                        makertStatYear.setOrderIntegral(new BigDecimal(0));
                        makertStatYear.setOrderActivity(new BigDecimal(0));
                        makertStatYear.setOrderVipRelief(new BigDecimal(0));
                        makertStatYear.setDate(currentTime);
                    } else {
                        if (null == makertStatResultDTO.getOrderCoupon()) {
                            makertStatResultDTO.setOrderCoupon(new BigDecimal(0));
                        }
                        if (null == makertStatResultDTO.getOrderIntegral()) {
                            makertStatResultDTO.setOrderIntegral(new BigDecimal(0));
                        }
                        if (null == makertStatResultDTO.getOrderActivity()) {
                            makertStatResultDTO.setOrderActivity(new BigDecimal(0));
                        }
                        if (null == makertStatResultDTO.getOrderVipRelief()) {
                            makertStatResultDTO.setOrderVipRelief(new BigDecimal(0));
                        }
                        makertStatYear.setMarketid(makertStatResultDTO.getMarketId());
                        makertStatYear.setOrderNum(makertStatResultDTO.getOrderNum());
                        makertStatYear.setOrderPay(makertStatResultDTO.getOrderPay());
                        makertStatYear.setOrderRiderPay(makertStatResultDTO.getOrderRiderPay());
                        makertStatYear.setOrderCoupon(makertStatResultDTO.getOrderCoupon());
                        makertStatYear.setOrderIntegral(makertStatResultDTO.getOrderIntegral());
                        makertStatYear.setOrderActivity(makertStatResultDTO.getOrderActivity());
                        makertStatYear.setOrderVipRelief(makertStatResultDTO.getOrderVipRelief());
                        makertStatYear.setDate(currentTime);
                    }
                    makertStatYearMapper.insertSelective(makertStatYear);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

