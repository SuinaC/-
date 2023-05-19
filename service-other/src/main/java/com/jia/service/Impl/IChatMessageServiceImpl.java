package com.jia.service.Impl;


import com.jia.entity.TChatMessage;
import com.jia.mapper.TChatMessageMapper;
import com.jia.service.IChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IChatMessageServiceImpl implements IChatMessageService {

    @Autowired
    private TChatMessageMapper chatMessageMapper;


    @Override
    public List<TChatMessage> selectByFromConsumerIdAndDoctorId(Long doctorId, Long consumerId) {
        return chatMessageMapper.selectByFromConsumerIdAndDoctorId(doctorId,consumerId);
    }

    @Override
    public List<TChatMessage> selectByFromDoctorIdAndConsumerId(Long doctorId, Long consumerId) {
        return chatMessageMapper.selectByFromDoctorIdAndConsumerId(doctorId,consumerId);
    }

    @Override
    public List<TChatMessage> selectByConsumerIdAndDoctorId(Long doctorId, Long consumerId) {
        return chatMessageMapper.selectByConsumerIdAndDoctorId(doctorId,consumerId);
    }

    @Override
    public List<TChatMessage> selectByDoctorIdAndStatus(Long doctorId) {
        return chatMessageMapper.selectByDoctorIdAndStatus(doctorId);
    }

    @Override
    public int insert(TChatMessage record) {
        return chatMessageMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKey(TChatMessage record) {
        return chatMessageMapper.updateByPrimaryKey(record);
    }
}
