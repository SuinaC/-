package com.jia.service.Impl;

import com.jia.base.BaseServiceImpl;
import com.jia.base.IBaseDao;
import com.jia.entity.TDoctor;
import com.jia.mapper.TDoctorMapper;
import com.jia.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDoctorServiceImpl  extends BaseServiceImpl<TDoctor> implements IDoctorService {

    @Autowired
    private TDoctorMapper doctorMapper;

    @Override
    public IBaseDao<TDoctor> getBaseDao() {
        return doctorMapper;
    }

    @Override
    public List<TDoctor> selectByDepartment(String department) {
        return doctorMapper.selectByDepartment(department);
    }
}