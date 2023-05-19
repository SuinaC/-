package com.jia.service;


import com.jia.entity.TMedicalAppointment;
import com.jia.entity.TMedicalAppointmentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IMedicalAppointmentService {

    int countByExample(TMedicalAppointmentExample example);

    int deleteByExample(TMedicalAppointmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TMedicalAppointment record);

    int insertSelective(TMedicalAppointment record);

    List<TMedicalAppointment> selectByExample(TMedicalAppointmentExample example);

    TMedicalAppointment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TMedicalAppointment record, @Param("example") TMedicalAppointmentExample example);

    int updateByExample(@Param("record") TMedicalAppointment record, @Param("example") TMedicalAppointmentExample example);

    int updateByPrimaryKeySelective(TMedicalAppointment record);

    int updateByPrimaryKey(TMedicalAppointment record);

    List<TMedicalAppointment> selectByConsumerId(Long id);
}