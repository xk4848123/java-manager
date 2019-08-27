package com.nidecai.managerndc.service.impl;

import com.nidecai.managerndc.ExceptionHandle.BusinessException;
import com.nidecai.managerndc.common.codeutil.CommonMessageEnum;
import com.nidecai.managerndc.entity.Market;
import com.nidecai.managerndc.entity.Shopown;
import com.nidecai.managerndc.mapper.MarketMapper;
import com.nidecai.managerndc.mapper.ShopownMapper;
import com.nidecai.managerndc.service.CloseUpShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author river
 * @title: CloseUpShopService
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/8/2613:19
 */
@Service
@Slf4j
public class CloseUpShopServiceImpl implements CloseUpShopService {
    @Autowired
    private MarketMapper marketMapper;
    @Autowired
    private ShopownMapper shopownMapper;

    @Override
    public void closeShop() throws BusinessException {
        Market market = new Market();
        market.setState(new Byte("1"));
        List<Market> marketList = marketMapper.selectMarket(market.getState());
        if (CollectionUtils.isEmpty(marketList)) {
            throw new BusinessException(CommonMessageEnum.FAIL.getCode(), "无菜场存在");
        }
        for (Market hmMarket : marketList) {
            try {
                //菜场关店时间
                String marketEndtime = hmMarket.getEndtime();
                //获取当前系统日期时间
                Long currentTimeMillis = System.currentTimeMillis();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentFormat = simpleDateFormat.format(currentTimeMillis);
                String[] currentTime = currentFormat.split(" ");
                String[] currentHourMin = currentTime[1].split(":");
                String[] marketEndHourMin = marketEndtime.split(":");
                if (currentHourMin[0].equals(marketEndHourMin[0]) && currentHourMin[1].equals(marketEndHourMin[1]))
                 {
                    //获取菜场下的所有对应的店铺
                    Integer hmMarketId = hmMarket.getId();
                    List<Shopown> shopowns = shopownMapper.selectShown(hmMarketId);
                    if (CollectionUtils.isEmpty(shopowns)) {
                        throw new BusinessException(CommonMessageEnum.FAIL.getCode(), "无商户存在");
                    }
                    for (Shopown shopown : shopowns) {
                        if (shopown.getExamine() == 1 && shopown.getIsShow()==1 &&"1".equals(shopown.getType().toString())) {
                            shopown.setStatus(1);
                            shopownMapper.updateStatus(shopown.getStatus());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}