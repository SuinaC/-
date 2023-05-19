package com.jia.controller;

import com.jia.entity.TChatMessage;
import com.jia.entity.vo.ChatVo;
import com.jia.service.IChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @title ChatController
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:55
 **/
@RestController
@RequestMapping("chat")
public class ChatController {

    @Autowired
    IChatMessageService chatMessageService;


    @RequestMapping("/update")
    public int update(@RequestBody TChatMessage tChatMessage){
        return chatMessageService.updateByPrimaryKey(tChatMessage);
    }

    @RequestMapping("/message1")
    public List<TChatMessage> getMessage(@RequestBody ChatVo chatVo){
        Long doctor = chatVo.getDoctorId();
        Long consumer = chatVo.getConsumerId();
        return chatMessageService.selectByConsumerIdAndDoctorId(doctor,consumer);
    }
    @RequestMapping("/message2")
    public List<TChatMessage> getMessageDoctor(@RequestBody  Long doctor){
        return chatMessageService.selectByDoctorIdAndStatus(doctor);
    }

    @RequestMapping("/insert")
    public int insert(@RequestBody TChatMessage tChatMessage){
        return chatMessageService.insert(tChatMessage);
    }
}
