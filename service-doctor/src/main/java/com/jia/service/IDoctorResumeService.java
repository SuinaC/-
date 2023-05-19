package com.jia.service;


import com.jia.entity.TDoctorResume;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IDoctorResumeService {

    List<TDoctorResume> selectByDoctorId(@Param("id") Long id);

    int insert(TDoctorResume record);
}
