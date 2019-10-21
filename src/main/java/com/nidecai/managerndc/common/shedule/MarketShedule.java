package com.nidecai.managerndc.common.shedule;

import com.nidecai.managerndc.entity.MakertStatTag;
import com.nidecai.managerndc.mapper.MakertStatTagMapper;
import com.nidecai.managerndc.service.MarketStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nidecai.managerndc.service.CloseUpShopService;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class MarketShedule {

	@Autowired
	private CloseUpShopService closeUpShopService;

	@Autowired
	private MakertStatTagMapper makertStatTagMapper;

	@Autowired
	private MarketStatService marketStatService;
	
	@Scheduled(cron = "0 */1 * * * ?")
	public void closeShop() {
		closeUpShopService.closeShop();
	}

	@Scheduled(cron = "0 10 12 * * ?")
	public void cronTime() {

		marketStatService.recordMarketStatDay();
		Date date = new Date();
		SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("yyyy-MM-dd");

		Calendar instanceDay= Calendar.getInstance();
		instanceDay.setTime(date);
		int i = instanceDay.get(Calendar.DATE);
		instanceDay.set(Calendar.DATE, i-1);

		List<MakertStatTag> makertStatTags = makertStatTagMapper.selectAll();
		MakertStatTag makertStatTag = makertStatTags.get(makertStatTags.size() - 1);
		SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("yyyy-MM");
		//获取上一个月月份
		Calendar instanceMonth = Calendar.getInstance();

		instanceMonth.setTime(date);
		instanceMonth.set(Calendar.MONTH, instanceMonth.get(Calendar.MONTH) - 1); // 设置为上一个月
		if(!makertStatTag.getMonthDate().equals(simpleDateFormatMonth.format(instanceMonth.getTime()))){
			marketStatService.recordMarketStatMonth();
		}

		SimpleDateFormat simpleDateFormatYear = new SimpleDateFormat("yyyy");
		Calendar instanceYear = Calendar.getInstance();
		instanceYear.setTime(date);
		instanceYear.add(Calendar.YEAR, -1);
		if(!makertStatTag.getYearDate().equals(simpleDateFormatYear.format(instanceYear.getTime()))){
			marketStatService.recordMarketStatYear();
		}

		MakertStatTag makertStatbean = new MakertStatTag();
		makertStatbean.setDayDate(simpleDateFormatDay.format(instanceDay.getTime()));
		makertStatbean.setMonthDate(simpleDateFormatMonth.format(instanceMonth.getTime()));
		makertStatbean.setYearDate(simpleDateFormatYear.format(instanceYear.getTime()));
		makertStatTagMapper.insertSelective(makertStatbean);

	}
}
