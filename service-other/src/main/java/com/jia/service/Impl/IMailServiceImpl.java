package com.jia.service.Impl;


import com.jia.mapper.TMailMapper;
import com.jia.service.IMailService;
import com.jia.entity.TMail;
import com.jia.entity.TMailExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IMailServiceImpl implements IMailService {

    @Autowired
    private TMailMapper mailMapper;

    @Override
    public int countByExample(TMailExample example) {
        return mailMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(TMailExample example) {
        return mailMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TMail record) {
        return mailMapper.insert(record);
    }

    @Override
    public int insertSelective(TMail record) {
        return mailMapper.insertSelective(record);
    }

    @Override
    public List<TMail> selectByExample(TMailExample example) {
        return mailMapper.selectByExample(example);
    }

    @Override
    public TMail selectByPrimaryKey(Long id) {
        return mailMapper.selectByPrimaryKey(id);
    }

    @Override
    public TMail selectByConsumerId(Long id) {
        return mailMapper.selectByConsumerId(id);
    }

    @Override
    public int updateByExampleSelective(TMail record, TMailExample example) {
        return mailMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(TMail record, TMailExample example) {
        return mailMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(TMail record) {
        return mailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TMail record) {
        return mailMapper.updateByPrimaryKey(record);
    }
}
