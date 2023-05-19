package com.jia.service;


import com.jia.entity.TChatMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IChatMessageService {

    List<TChatMessage> selectByFromConsumerIdAndDoctorId(@Param("doctorId")Long doctorId, @Param("consumerId") Long consumerId);

    List<TChatMessage> selectByFromDoctorIdAndConsumerId(@Param("doctorId")Long doctorId,@Param("consumerId") Long consumerId);

    List<TChatMessage> selectByConsumerIdAndDoctorId(@Param("doctorId")Long doctorId,@Param("consumerId") Long consumerId);

    List<TChatMessage> selectByDoctorIdAndStatus(@Param("doctorId")Long doctorId);

    int insert(TChatMessage record);

    int updateByPrimaryKey(TChatMessage record);
}
