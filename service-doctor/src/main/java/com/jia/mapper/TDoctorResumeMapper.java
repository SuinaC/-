package com.jia.mapper;

import com.jia.entity.TDoctorResume;
import com.jia.entity.TDoctorResumeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TDoctorResumeMapper {
    int countByExample(TDoctorResumeExample example);

    int deleteByExample(TDoctorResumeExample example);

    int insert(TDoctorResume record);

    int insertSelective(TDoctorResume record);

    List<TDoctorResume> selectByExample(TDoctorResumeExample example);

    int updateByExampleSelective(@Param("record") TDoctorResume record, @Param("example") TDoctorResumeExample example);

    int updateByExample(@Param("record") TDoctorResume record, @Param("example") TDoctorResumeExample example);

    List<TDoctorResume> selectByDoctorId(@Param("id") Long id);
}