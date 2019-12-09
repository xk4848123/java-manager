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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Transactional
    public void recordMarketStatDay() {
        try {
            //统计前一天订单
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date); // 设置为当前时间
            calendar.add(Calendar.DATE, -1);//-1.昨天时间 0.当前时间 1.明天时间 *以此类推
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            date = calendar.getTime();
            String accDate = format.format(date);  
            long timeStart = format.parse(accDate).getTime() / 1000;

            Date last = new Date();
            Calendar calendarLast = Calendar.getInstance();
            calendarLast.setTime(last);
            calendarLast.add(Calendar.DATE, -1);//-1.昨天时间 0.当前时间 1.明天时间 *以此类推
            calendarLast.set(Calendar.HOUR_OF_DAY, 23);
            calendarLast.set(Calendar.MINUTE, 59);
            calendarLast.set(Calendar.SECOND, 59);
            String end = format.format(calendarLast.getTime());
            long timeEnd = format.parse(end).getTime() / 1000;

            List<MakertStatResultDTO> maketStatDay = riderOrderMapper.findMaketStatDay(timeStart, timeEnd);
            //查出所有状态为1的菜场.
            List<Integer> marketId = marketMapper.selectMarketId((byte) 1);
            List<Integer> list = new ArrayList<Integer>();
            for (MakertStatResultDTO makertStatResultDTO : maketStatDay) {
                //判断昨天菜场是否下过单如果不等于则没有下过单.
                MakertStatDay makertStatDay = new MakertStatDay();
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
                makertStatDay.setMarketid(makertStatResultDTO.getMarketId());
                makertStatDay.setOrderNum(makertStatResultDTO.getOrderNum());
                makertStatDay.setOrderPay(makertStatResultDTO.getOrderPay());
                makertStatDay.setOrderRiderPay(makertStatResultDTO.getOrderRiderPay());
                makertStatDay.setOrderCoupon(makertStatResultDTO.getOrderCoupon());
                makertStatDay.setOrderIntegral(makertStatResultDTO.getOrderIntegral());
                makertStatDay.setOrderActivity(makertStatResultDTO.getOrderActivity());
                makertStatDay.setOrderVipRelief(makertStatResultDTO.getOrderVipRelief());
                makertStatDay.setOrderCommissionPrice(makertStatResultDTO.getOrderCommissionPrice());
                makertStatDay.setDate(accDate.split(" ")[0]);

                list.add(makertStatResultDTO.getMarketId());
                makertStatDayMapper.insertSelective(makertStatDay);
            }
            marketId.removeAll(list);
            for (Integer integer : marketId) {
                MakertStatDay makertStatDay = new MakertStatDay();
                makertStatDay.setMarketid(integer);
                makertStatDay.setOrderNum(0);
                makertStatDay.setOrderPay(new BigDecimal(0));
                makertStatDay.setOrderRiderPay(new BigDecimal(0));
                makertStatDay.setOrderCoupon(new BigDecimal(0));
                makertStatDay.setOrderIntegral(new BigDecimal(0));
                makertStatDay.setOrderActivity(new BigDecimal(0));
                makertStatDay.setOrderVipRelief(new BigDecimal(0));
                makertStatDay.setOrderCommissionPrice(new BigDecimal(0));
                makertStatDay.setDate(accDate.split(" ")[0]);
                makertStatDayMapper.insertSelective(makertStatDay);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void recordMarketStatMonth(String lastMonthString) {
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

            List<MakertStatResultDTO> maketStatDay = riderOrderMapper.findMaketStatDay(lasetTimeStart, lastTimeEnd);

            //查出所有状态为1的菜场.
            List<Integer> marketId = marketMapper.selectMarketId((byte) 1);
            List<Integer> list = new ArrayList<Integer> ();
            for (MakertStatResultDTO makertStatResultDTO : maketStatDay) {
                //判断昨天菜场是否下过单如果不等于则没有下过单.
                MakertStatMonth makertStatMonth = new MakertStatMonth();
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
                makertStatMonth.setOrderCommissionPrice(makertStatResultDTO.getOrderCommissionPrice());
                makertStatMonth.setDate(lastMonthString);

                list.add(makertStatResultDTO.getMarketId());
                makertStatMonthMapper.insertSelective(makertStatMonth);
            }
            marketId.removeAll(list);
            for (Integer integer : marketId) {
                MakertStatMonth makertStatMonth = new MakertStatMonth();
                makertStatMonth.setMarketid(integer);
                makertStatMonth.setOrderNum(0);
                makertStatMonth.setOrderPay(new BigDecimal(0));
                makertStatMonth.setOrderRiderPay(new BigDecimal(0));
                makertStatMonth.setOrderCoupon(new BigDecimal(0));
                makertStatMonth.setOrderIntegral(new BigDecimal(0));
                makertStatMonth.setOrderActivity(new BigDecimal(0));
                makertStatMonth.setOrderVipRelief(new BigDecimal(0));
                makertStatMonth.setOrderCommissionPrice(new BigDecimal(0));
                makertStatMonth.setDate(lastMonthString);
                makertStatMonthMapper.insertSelective(makertStatMonth);
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
            List list = new ArrayList();
            for (MakertStatResultDTO makertStatResultDTO : maketStatDay) {
                //判断昨天菜场是否下过单如果不等于则没有下过单.
                MakertStatYear makertStatYear = new MakertStatYear();
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
                makertStatYear.setOrderCommissionPrice(makertStatResultDTO.getOrderCommissionPrice());
                makertStatYear.setDate(currentTime);

                list.add(makertStatResultDTO.getMarketId());
                makertStatYearMapper.insertSelective(makertStatYear);
            }
            marketId.removeAll(list);
            for (Integer integer : marketId) {
                MakertStatYear makertStatYear = new MakertStatYear();
                makertStatYear.setMarketid(integer);
                makertStatYear.setOrderNum(0);
                makertStatYear.setOrderPay(new BigDecimal(0));
                makertStatYear.setOrderRiderPay(new BigDecimal(0));
                makertStatYear.setOrderCoupon(new BigDecimal(0));
                makertStatYear.setOrderIntegral(new BigDecimal(0));
                makertStatYear.setOrderActivity(new BigDecimal(0));
                makertStatYear.setOrderVipRelief(new BigDecimal(0));
                makertStatYear.setOrderCommissionPrice(new BigDecimal(0));
                makertStatYear.setDate(currentTime);
                makertStatYearMapper.insertSelective(makertStatYear);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

