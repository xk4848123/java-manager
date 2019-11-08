package com.nidecai.managerndc.service.impl;

import com.nidecai.managerndc.entity.PayStatistic;
import com.nidecai.managerndc.mapper.PayStatisticMapper;
import com.nidecai.managerndc.mapper.PayidOrderMapper;
import com.nidecai.managerndc.mapper.RiderOrderMapper;
import com.nidecai.managerndc.service.QueryUid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * @author river
 * @title: QueryUidImpl
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/11/818:25
 */
@Service
public class QueryUidImpl implements QueryUid {
    @Autowired
    private PayStatisticMapper payStatisticMapper;
    @Autowired
    private PayidOrderMapper payidOrderMapper;
    @Autowired
    private RiderOrderMapper riderOrderMapper;
    StringBuilder stringBuilder;

    @Override
    public PayStatistic queryUidById() {
        List<PayStatistic> payStatistics = payStatisticMapper.selectAll();
        PayStatistic newPayStatistic = new PayStatistic();
        if (!CollectionUtils.isEmpty(payStatistics)) {
            for (PayStatistic payStatistic : payStatistics) {
                List<String> stringListPayOrder = payidOrderMapper.selectListPayOrder(payStatistic.getId());
                List<Integer> userId = riderOrderMapper.selectList(stringListPayOrder);
                if (userId == null || userId.size() == 0) {
                    continue;
                }
                stringBuilder = new StringBuilder();
                for (int i = userId.size() - 1; i >= 0; i--) {
                    if (i == 0) {
                        stringBuilder.append(userId.get(i));
                    } else {
                        stringBuilder.append(userId.get(i)).append(",");
                    }

                }
                //去除末尾逗号
                String buider = stringBuilder.toString();
                newPayStatistic.setUid(buider);
                newPayStatistic.setCount(userId.size());
            }

        }
        return newPayStatistic;
    }
}
