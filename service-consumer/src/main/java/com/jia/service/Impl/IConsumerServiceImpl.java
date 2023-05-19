package com.jia.service.Impl;


import com.jia.mapper.TConsumerMapper;
import com.jia.entity.TConsumer;
import com.jia.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IConsumerServiceImpl implements IConsumerService {

    @Autowired
    TConsumerMapper consumerMapper;

    @Override
    public TConsumer selectByPrimaryKey(Long id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

    @Override
    public TConsumer selectByPhone(Long phone) {
        return consumerMapper.selectByPhone(phone);
    }

    @Override
    public TConsumer selectByWchat(String wchat) {
        return consumerMapper.selectByWchat(wchat);
    }

    @Override
    public TConsumer selectByEmail(String email) {
        return consumerMapper.selectByEmail(email);
    }

    @Override
    public int updateByPrimaryKey(TConsumer consumer) {
        return consumerMapper.updateByPrimaryKey(consumer);
    }

    @Override
    public int insert(TConsumer consumer) {
        return consumerMapper.insert(consumer);
    }

    @Override
    public TConsumer selectByPrimaryKeyAndPassword(TConsumer tConsumer) {
        return consumerMapper.selectByPrimaryKeyAndPassword(tConsumer);
    }

    @Override
    public Long selectIdByUsernameAndPassword(TConsumer tConsumer) {
        return consumerMapper.selectIdByUsernameAndPassword(tConsumer);
    }

    @Override
    public TConsumer selectByIdCard(String IdCard) {
        return consumerMapper.selectByIdCard(IdCard);
    }

    @Override
    public List<TConsumer> selectAll() {
        return consumerMapper.selectAll();
    }

    @Override
    public List<TConsumer> selectByAddress(String address) {
        return consumerMapper.selectByAddress(address);
    }

    @Override
    public int selectCount() {
        return consumerMapper.selectCount();
    }


}
