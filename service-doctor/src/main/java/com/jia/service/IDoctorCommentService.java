package com.jia.service;


import com.jia.entity.TCommentGreat;
import com.jia.entity.TCommentReply;
import com.jia.entity.TDoctorComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IDoctorCommentService {

    List<TDoctorComment> selectByDoctorId(@Param("doctorId") Long doctorId);

    List<TDoctorComment> selectAllByDoctorId(@Param("doctorId") Long doctorId);

    int selectCountByDoctorId(@Param("doctorId") Long doctorId);

    int insert(TDoctorComment record);

    TDoctorComment selectCommentByPrimaryKey(Long id);

    TCommentGreat selectTCommentGreatByConsumerIdAndCommentId(@Param("consumerId") Long consumerId, @Param("commentId") Long commentId);

    List<TCommentReply> selectTCommentReplyByConsumerIdAndCommentId(@Param("consumerId")Long consumerId, @Param("commentId") Long commentId);

    int updateCommentByPrimaryKey(TDoctorComment record);

    int insertCommentGreat(TCommentGreat record);

    TCommentGreat selectGreatByPrimaryKey(Long id);

    int updateGreatByPrimaryKey(@Param("record") TCommentGreat record);

    int insertTCommentReply(TCommentReply record);

    TCommentReply selectTCommentReplyByPrimaryKey(Long id);

    int updateTCommentReplyByPrimaryKey(@Param("record") TCommentReply record);

}
