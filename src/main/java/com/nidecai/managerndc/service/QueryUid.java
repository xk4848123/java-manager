package com.nidecai.managerndc.service;


/**
 * @author river
 * @title: QueryUid
 * @projectName manager-ndc
 * @description: TODO
 * @date 2019/11/818:19
 */
public interface QueryUid {
      void  queryUidById();
      
      void  queryName();
      
      void promotionStat();
      
      void storeStat();
      
      void queryStore();
      
      void startGiveCoupon(String mongoId,Integer couponId);
      
      void test();
}
