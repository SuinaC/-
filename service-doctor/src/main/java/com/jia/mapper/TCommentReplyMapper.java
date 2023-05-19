package com.jia.mapper;

import com.jia.entity.TCommentReply;
import com.jia.entity.TCommentReplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCommentReplyMapper {
    int countByExample(TCommentReplyExample example);

    int deleteByExample(TCommentReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TCommentReply record);

    int insertSelective(TCommentReply record);

    List<TCommentReply> selectByExample(TCommentReplyExample example);

    TCommentReply selectByPrimaryKey(Long id);

    List<TCommentReply> selectByConsumerIdAndCommentId(@Param("consumerId")Long consumerId,@Param("commentId") Long commentId);

    int updateByExampleSelective(@Param("record") TCommentReply record, @Param("example") TCommentReplyExample example);

    int updateByExample(@Param("record") TCommentReply record, @Param("example") TCommentReplyExample example);

    int updateByPrimaryKeySelective(TCommentReply record);

    int updateByPrimaryKey(TCommentReply record);
}