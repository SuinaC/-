package com.jia.service.Impl;


import com.jia.entity.TCommentGreat;
import com.jia.entity.TCommentReply;
import com.jia.entity.TDoctorComment;
import com.jia.mapper.TCommentGreatMapper;
import com.jia.mapper.TCommentReplyMapper;
import com.jia.mapper.TDoctorCommentMapper;
import com.jia.service.IDoctorCommentService;
import com.jia.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDoctorCommentServiceImpl implements IDoctorCommentService {

    @Autowired
    private TDoctorCommentMapper doctorCommentMapper;

    @Autowired
    private TCommentGreatMapper commentGreatMapper;

    @Autowired
    private TCommentReplyMapper commentReplyMapper;


    @Override
    public List<TDoctorComment> selectByDoctorId(Long doctorId) {
        return doctorCommentMapper.selectByDoctorId(doctorId);
    }

    @Override
    public List<TDoctorComment> selectAllByDoctorId(Long doctorId) {
        return doctorCommentMapper.selectAllByDoctorId(doctorId);
    }

    @Override
    public int selectCountByDoctorId(Long doctorId) {
        return doctorCommentMapper.selectCountByDoctorId(doctorId);
    }

    @Override
    public int insert(TDoctorComment record) {
        record.setGmtCreate(DateUtil.getDaDate());
        return doctorCommentMapper.insert(record);
    }

    @Override
    public TDoctorComment selectCommentByPrimaryKey(Long id) {
        return doctorCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public TCommentGreat selectTCommentGreatByConsumerIdAndCommentId(Long consumerId, Long commentId) {
        return commentGreatMapper.selectByConsumerIdAndCommentId(consumerId,commentId);
    }

    @Override
    public List<TCommentReply> selectTCommentReplyByConsumerIdAndCommentId(Long consumerId, Long commentId) {
        return commentReplyMapper.selectByConsumerIdAndCommentId(consumerId,commentId);
    }

    @Override
    public int updateCommentByPrimaryKey(TDoctorComment record) {
        return doctorCommentMapper.updateByPrimaryKey(record);
    }

    @Override
    public int insertCommentGreat(TCommentGreat record) {
        return commentGreatMapper.insert(record);
    }

    @Override
    public TCommentGreat selectGreatByPrimaryKey(Long id) {
        return commentGreatMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateGreatByPrimaryKey(TCommentGreat record) {
        return commentGreatMapper.updateByPrimaryKey(record);
    }

    @Override
    public int insertTCommentReply(TCommentReply record) {
        return commentReplyMapper.insert(record);
    }

    @Override
    public TCommentReply selectTCommentReplyByPrimaryKey(Long id) {
        return commentReplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateTCommentReplyByPrimaryKey(TCommentReply record) {
        return commentReplyMapper.updateByPrimaryKey(record);
    }
}
