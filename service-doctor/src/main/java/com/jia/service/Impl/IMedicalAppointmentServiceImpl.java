package com.jia.service.Impl;


import com.jia.entity.TMedicalAppointment;
import com.jia.entity.TMedicalAppointmentExample;
import com.jia.mapper.TMedicalAppointmentMapper;
import com.jia.service.IMedicalAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IMedicalAppointmentServiceImpl implements IMedicalAppointmentService {

    @Autowired
    private TMedicalAppointmentMapper tMedicalAppointmentMapper;

    @Override
    public int countByExample(TMedicalAppointmentExample example) {
        return tMedicalAppointmentMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TMedicalAppointmentExample example) {
        return tMedicalAppointmentMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tMedicalAppointmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TMedicalAppointment record) {
        return tMedicalAppointmentMapper.insert(record);
    }

    @Override
    public int insertSelective(TMedicalAppointment record) {
        return tMedicalAppointmentMapper.insertSelective(record);
    }

    @Override
    public List<TMedicalAppointment> selectByExample(TMedicalAppointmentExample example) {
        return tMedicalAppointmentMapper.selectByExample(example);
    }

    @Override
    public TMedicalAppointment selectByPrimaryKey(Long id) {
        return tMedicalAppointmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(TMedicalAppointment record, TMedicalAppointmentExample example) {
        return tMedicalAppointmentMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(TMedicalAppointment record, TMedicalAppointmentExample example) {
        return tMedicalAppointmentMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(TMedicalAppointment record) {
        return tMedicalAppointmentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TMedicalAppointment record) {
        return tMedicalAppointmentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TMedicalAppointment> selectByConsumerId(Long id) {
        return tMedicalAppointmentMapper.selectByConsumerId(id);
    }
}
