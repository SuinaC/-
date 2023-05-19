package com.jia.mapper;

import com.jia.entity.TDoctorComment;
import com.jia.entity.TDoctorCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TDoctorCommentMapper {
    int countByExample(TDoctorCommentExample example);

    int deleteByExample(TDoctorCommentExample example);

    int insert(TDoctorComment record);

    int insertSelective(TDoctorComment record);

    List<TDoctorComment> selectByExample(TDoctorCommentExample example);

    int updateByExampleSelective(@Param("record") TDoctorComment record, @Param("example") TDoctorCommentExample example);

    int updateByExample(@Param("record") TDoctorComment record, @Param("example") TDoctorCommentExample example);

    int updateByPrimaryKey(@Param("record") TDoctorComment record);

    List<TDoctorComment> selectByDoctorId(@Param("doctorId") Long doctorId);

    List<TDoctorComment> selectAllByDoctorId(@Param("doctorId") Long doctorId);

    int selectCountByDoctorId(@Param("doctorId") Long doctorId);

    TDoctorComment selectByPrimaryKey(Long id);
}