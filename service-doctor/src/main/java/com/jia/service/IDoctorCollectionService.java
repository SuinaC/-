package com.jia.service;


import com.jia.entity.TDoctorCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDoctorCollectionService {

    List<TDoctorCollection> selectByConsumerId(Long id);

    TDoctorCollection selectByConsumerIdAndDoctorId(@Param("doctorId") Long doctorId, @Param("consumerId") Long consumerId);

    int insert(TDoctorCollection record);

    int deleteByPrimaryKey(Long id);
}
