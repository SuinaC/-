package com.jia.front.controller;


import com.jia.entity.TChatMessage;
import com.jia.entity.TConsumer;
import com.jia.entity.TDoctor;
import com.jia.entity.vo.ChatVo;
import com.jia.front.client.IChatMessageClient;
import com.jia.front.client.IConsumerClient;
import com.jia.front.client.IDoctorClient;
import com.jia.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("doctorChat")
@Controller
public class DoctorChatController {

    @Resource
    IConsumerClient consumerService;

    @Resource
    IDoctorClient doctorService;

    @Resource
    IChatMessageClient chatMessageService;

    @RequestMapping("doctorChat")
    public String doctorChat(Long id,Long doctorId, Model model){
        TConsumer tConsumer = consumerService.selectByPrimaryKey(id);
        TDoctor tDoctor = doctorService.selectByPrimaryKey(doctorId);

        ChatVo cd5 = new ChatVo();
        cd5.setDoctorId(doctorId);
        cd5.setConsumerId(id);
        List<TChatMessage> tChatMessages = chatMessageService.selectByConsumerIdAndDoctorId(cd5);
        model.addAttribute("tConsumer",tConsumer);
        model.addAttribute("tChatMessages",tChatMessages);
        model.addAttribute("tDoctor",tDoctor);
        return "information/doctorChat";
    }

    @RequestMapping("addChat")
    public String addChat(Long fromUserId,Long toUserId,String contentText,Model model){
        if (fromUserId == null || toUserId == null || contentText == "" || contentText == null){
            return "error";
        }
        TChatMessage tChatMessage = new TChatMessage();
        tChatMessage.setMessage(contentText);
        tChatMessage.setDoctorId(toUserId);
        tChatMessage.setConsumerId(fromUserId);
        tChatMessage.setFromId(fromUserId);
        tChatMessage.setToId(toUserId);
        tChatMessage.setStatus(0);
        tChatMessage.setGmtCreate(DateUtil.getDaDate());
        int insert = chatMessageService.insert(tChatMessage);
        if(insert == 1){
            ChatVo cd = new ChatVo();
            cd.setConsumerId(toUserId);
            cd.setDoctorId(fromUserId);
            List<TChatMessage> tChatMessages = chatMessageService.selectByConsumerIdAndDoctorId(cd);
            model.addAttribute("tChatMessages",tChatMessages);
            return "information/doctorChat::chat_refresh";
        }
        return "error";

    }

    @RequestMapping("freshChat")
    public String freshChat(Long consumerId,Long doctorId,Model model){
        if(consumerId == null || doctorId == null){
            return "error";
        }
        ChatVo dc = new ChatVo();
        dc.setDoctorId(doctorId);
        dc.setConsumerId(consumerId);
        List<TChatMessage> tChatMessages = chatMessageService.selectByConsumerIdAndDoctorId(dc);
        model.addAttribute("tChatMessages",tChatMessages);
        return "information/doctorChat::chat_refresh";
    }

}
