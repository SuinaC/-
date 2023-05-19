package com.jia.front.client;

import com.jia.entity.TChatMessage;
import com.jia.entity.vo.ChatVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title IChatMessageClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:01
 **/
@FeignClient("other-service")
public interface IChatMessageClient {
    @RequestMapping("chat/update")
    @ResponseBody
    int updateByPrimaryKey(@RequestBody TChatMessage tChatMessage);

    @RequestMapping("chat/insert")
    @ResponseBody
    int insert(@RequestBody TChatMessage tChatMessage);

    @RequestMapping("chat/message1")
    @ResponseBody
    List<TChatMessage> selectByConsumerIdAndDoctorId(@RequestBody ChatVo chatVo);
    @RequestMapping("chat/message2")
    @ResponseBody
    List<TChatMessage> selectByDoctorIdAndStatus(@RequestBody Long doctor);


}
