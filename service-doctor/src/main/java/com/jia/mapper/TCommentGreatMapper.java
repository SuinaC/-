package com.jia.mapper;

import com.jia.entity.TCommentGreat;
import com.jia.entity.TCommentGreatExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCommentGreatMapper {
    int countByExample(TCommentGreatExample example);

    int deleteByExample(TCommentGreatExample example);

    int insert(TCommentGreat record);

    int insertSelective(TCommentGreat record);

    TCommentGreat selectByPrimaryKey(Long id);

    List<TCommentGreat> selectByExample(TCommentGreatExample example);

    int updateByExampleSelective(@Param("record") TCommentGreat record, @Param("example") TCommentGreatExample example);

    int updateByExample(@Param("record") TCommentGreat record, @Param("example") TCommentGreatExample example);

    int updateByPrimaryKey(@Param("record") TCommentGreat record);

    TCommentGreat selectByConsumerIdAndCommentId(@Param("consumerId") Long consumerId,@Param("commentId") Long commentId);
}