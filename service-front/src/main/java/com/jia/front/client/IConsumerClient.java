package com.jia.front.client;

import com.jia.entity.TConsumer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title IConsumerClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:00
 **/
@FeignClient("consumer-service")
public interface IConsumerClient {

    @RequestMapping("/consumerInfo/key")
    @ResponseBody
    TConsumer selectByPrimaryKey(@RequestBody Long cid);

    @RequestMapping("/consumerInfo/count")
    @ResponseBody
    Integer selectCount();

    @RequestMapping("/consumerInfo/address")
    @ResponseBody
    List<TConsumer> selectByAddress(@RequestBody String address);

    @RequestMapping("/consumerInfo/all")
    @ResponseBody
    List<TConsumer> selectAll();

    @RequestMapping("/consumerInfo/phone")
    @ResponseBody
    TConsumer selectByPhone(@RequestBody Long phone);

    @RequestMapping("/consumerInfo/wechat")
    @ResponseBody
    TConsumer selectByWchat(@RequestBody String wechat);

    @RequestMapping("/consumerInfo/email")
    @ResponseBody
    TConsumer selectByEmail(@RequestBody String email);

    @RequestMapping("/consumerInfo/update")
    @ResponseBody
    int updateByPrimaryKey(@RequestBody TConsumer consumer);

    @RequestMapping("/consumerInfo/insert")
    @ResponseBody
    int insert(@RequestBody TConsumer tConsumer);

    @RequestMapping("/consumerInfo/key/pwd")
    @ResponseBody
    TConsumer selectByPrimaryKeyAndPassword(@RequestBody TConsumer tConsumer);

    @RequestMapping("/consumerInfo/user/pwd")
    @ResponseBody
    Long selectIdByUsernameAndPassword(@RequestBody TConsumer tConsumer);

    @RequestMapping("/consumerInfo/idcard")
    @ResponseBody
    TConsumer selectByIdCard(@RequestBody String idCard);
}
