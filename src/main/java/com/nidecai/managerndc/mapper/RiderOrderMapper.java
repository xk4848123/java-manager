package com.nidecai.managerndc.mapper;

import com.nidecai.managerndc.common.entitycommon.MakertStatResultDTO;
import com.nidecai.managerndc.entity.RiderOrder;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface RiderOrderMapper extends Mapper<RiderOrder> {

    Integer getAllSumOfRid(@Param("set") Set<Integer> idList);

    Integer getNewSumOfRidByCtime(@Param("ctime") Integer ctime, @Param("set") Set<Integer> idList);

    List<Integer> getOldUser(@Param("ctime") Integer ctime, @Param("set") Set<Integer> idList);

    Integer getOldSumOfRidByCtime(@Param("ctime") Integer ctime, @Param("list") List<Integer> idList);

    Integer getNewUserOrderNum(@Param("ctime") Integer ctime);

    List<MakertStatResultDTO> findMaketStatDay(@Param("timeStart") Long timestart, @Param("timeEnd") Long timeEnd);


}