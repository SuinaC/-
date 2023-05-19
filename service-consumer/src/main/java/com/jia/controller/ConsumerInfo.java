package com.jia.controller;

import com.jia.entity.TConsumer;
import com.jia.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @title ConsumerInfo
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:59
 **/
@RestController
@RequestMapping("consumerInfo")
public class ConsumerInfo {

    @Autowired
    IConsumerService consumerService;

    @RequestMapping("/key")
    public TConsumer selectByKey(@RequestBody Long cid){
        TConsumer tConsumer = consumerService.selectByPrimaryKey(cid);
        return tConsumer;
    }


    @RequestMapping("/all")
    public List<TConsumer> selectAllConsumer(){
        List<TConsumer> tConsumers=consumerService.selectAll();
        return tConsumers;
    }

    @RequestMapping("/address")
    public List<TConsumer> selectByAdress(@RequestBody String address){
        List<TConsumer> tConsumers=consumerService.selectByAddress(address);
        return tConsumers;
    }
    @RequestMapping("/count")
    public int ConsumerCount(){
        return consumerService.selectCount();
    }

    @RequestMapping("/phone")
    public TConsumer selectByPhone(@RequestBody Long phone){
        return consumerService.selectByPhone(phone);
    }
    @RequestMapping("/wechat")
    public TConsumer selectByWchat(@RequestBody String wechat){
        return consumerService.selectByWchat(wechat);
    }
    @RequestMapping("/email")
    public TConsumer selectByEmail(@RequestBody String email){
        return consumerService.selectByEmail(email);
    }
    @RequestMapping("/update")
    public int updateByPrimaryKey(@RequestBody TConsumer consumer){
        return consumerService.updateByPrimaryKey(consumer);
    }

    @RequestMapping("/insert")
    public int insert(@RequestBody TConsumer tConsumer){
        return consumerService.insert(tConsumer);
    }

    @RequestMapping("/key/pwd")
    public TConsumer selectByPrimaryKeyAndPassword(@RequestBody TConsumer tConsumer){
        return consumerService.selectByPrimaryKeyAndPassword(tConsumer);
    }

    @RequestMapping("/user/pwd")
    public Long selectIdByUsernameAndPassword(@RequestBody TConsumer tConsumer){
        return consumerService.selectIdByUsernameAndPassword(tConsumer);
    }

    @RequestMapping("/idcard")
    public TConsumer selectByIdCard(@RequestBody String idCard){
        return consumerService.selectByIdCard(idCard);
    }

}
