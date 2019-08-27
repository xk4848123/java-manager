package com.nidecai.managerndc.common.shedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nidecai.managerndc.service.CloseUpShopService;

@Component
public class MarketShedule {

	@Autowired
	private CloseUpShopService closeUpShopService;
	
	@Scheduled(cron = "0 */1 * * * ?")
	public void closeShop() {
		closeUpShopService.closeShop();
	}
}
