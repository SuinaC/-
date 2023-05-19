package com.jia.mapper;

import com.jia.entity.TChatMessage;
import com.jia.entity.TChatMessageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TChatMessageMapper {
    int countByExample(TChatMessageExample example);

    int deleteByExample(TChatMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TChatMessage record);

    int insertSelective(TChatMessage record);

    List<TChatMessage> selectByExample(TChatMessageExample example);

    TChatMessage selectByPrimaryKey(Long id);

    List<TChatMessage> selectByFromConsumerIdAndDoctorId(@Param("doctorId")Long doctorId,@Param("consumerId") Long consumerId);

    List<TChatMessage> selectByConsumerIdAndDoctorId(@Param("doctorId")Long doctorId,@Param("consumerId") Long consumerId);

    List<TChatMessage> selectByFromDoctorIdAndConsumerId(@Param("doctorId")Long doctorId,@Param("consumerId") Long consumerId);

    List<TChatMessage> selectByDoctorIdAndStatus(@Param("doctorId")Long doctorId);

    int updateByExampleSelective(@Param("record") TChatMessage record, @Param("example") TChatMessageExample example);

    int updateByExample(@Param("record") TChatMessage record, @Param("example") TChatMessageExample example);

    int updateByPrimaryKeySelective(TChatMessage record);

    int updateByPrimaryKey(TChatMessage record);
}