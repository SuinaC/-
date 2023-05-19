package com.jia.service.Impl;


import com.jia.entity.TDoctorCollection;
import com.jia.mapper.TDoctorCollectionMapper;
import com.jia.service.IDoctorCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IDoctorCollectionServiceImpl implements IDoctorCollectionService {

    @Autowired
    private TDoctorCollectionMapper doctorCollectionMapper;

    @Override
    public List<TDoctorCollection> selectByConsumerId(Long id) {
        return doctorCollectionMapper.selectByConsumerId(id);
    }

    @Override
    public TDoctorCollection selectByConsumerIdAndDoctorId(Long doctorId, Long consumerId) {
        return doctorCollectionMapper.selectByConsumerIdAndDoctorId(doctorId,consumerId);
    }

    @Override
    public int insert(TDoctorCollection record) {
        return doctorCollectionMapper.insert(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return doctorCollectionMapper.deleteByPrimaryKey(id);
    }
}
