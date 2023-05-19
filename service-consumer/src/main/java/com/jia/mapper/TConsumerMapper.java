package com.jia.mapper;


import com.jia.entity.TConsumer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TConsumerMapper{

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