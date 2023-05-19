package com.jia.service;


import com.jia.entity.TConsumer;

import java.util.List;

public interface IConsumerService{
    TConsumer selectByPrimaryKey(Long id);
    TConsumer selectByPhone(Long phone);
    TConsumer selectByWchat(String wchat);

    TConsumer selectByEmail(String email);

    int updateByPrimaryKey(TConsumer consumer);

    int insert(TConsumer consumer);

    TConsumer selectByPrimaryKeyAndPassword(TConsumer tConsumer);

    Long selectIdByUsernameAndPassword(TConsumer tConsumer);

    TConsumer selectByIdCard(String IdCard);

    List<TConsumer> selectAll();

    List<TConsumer> selectByAddress(String address);

    int selectCount();

}
