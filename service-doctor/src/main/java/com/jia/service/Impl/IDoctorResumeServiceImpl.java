package com.jia.service.Impl;

import com.jia.entity.TDoctorResume;
import com.jia.mapper.TDoctorResumeMapper;
import com.jia.service.IDoctorResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IDoctorResumeServiceImpl implements IDoctorResumeService {

    @Autowired
    private TDoctorResumeMapper doctorResumeMapper;

    @Override
    public List<TDoctorResume> selectByDoctorId(Long id) {
        List<TDoctorResume> tDoctorResumes = doctorResumeMapper.selectByDoctorId(id);
        ArrayList<TDoctorResume> tDoctorResumes1 = new ArrayList<>();
        if(tDoctorResumes == null || tDoctorResumes.size() == 0){
            return tDoctorResumes1;
        }

        return tDoctorResumes;
    }

    @Override
    public int insert(TDoctorResume record) {
        return doctorResumeMapper.insert(record);
    }
}
