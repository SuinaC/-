package com.jia.front.controller;



import com.jia.entity.TConsumer;
import com.jia.front.client.IConsumerClient;
import com.jia.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/message")
public class MessageController {
    private static TConsumer consumer;

    @Resource
    private IConsumerClient consumerService;

    public void setUsernameAndPassword(TConsumer con) {
        consumer = con;
    }


    @RequestMapping("baseMessage")
    @ResponseBody
    public ModelAndView baseMessage(ModelAndView modelAndView) {

        modelAndView.setViewName("/message/basemessage");
        modelAndView.addObject("error", "您是首次登录，请填写基本信息");
        return modelAndView;
    }

    //    @RequestMapping("saveMessage")
    public ModelAndView saveMessage1(@Validated TConsumer tConsumer, ModelAndView modelAndView) {
        Long id = null;
        if (consumer.getUsername() != null && consumer.getPassword() != null) {
            tConsumer.setUsername(consumer.getUsername());
            tConsumer.setPhone(Long.parseLong(consumer.getUsername()));
            tConsumer.setPassword(consumer.getPassword());
            tConsumer.setGmtCreate(consumer.getGmtCreate());
            tConsumer.setGmtModified(DateUtil.getDaDate());
            id = consumerService.selectIdByUsernameAndPassword(tConsumer);
        }
        if (id == null) {
            modelAndView.addObject("error", "用户信息错误，请重新登录");
            modelAndView.setViewName("/message/basemessage");
            return modelAndView;
        }
        TConsumer tConsumer1 = consumerService.selectByIdCard(tConsumer.getIdCard());
        if (tConsumer1 != null) {
            modelAndView.addObject("error", "身份证已存在，请重新尝试");
            modelAndView.setViewName("/message/basemessage");
            return modelAndView;
        }
        TConsumer tConsumer2 = consumerService.selectByEmail(tConsumer.getEmail());
        if (tConsumer2 != null) {
            modelAndView.addObject("error", "电子邮箱已存在，请重新尝试");
            modelAndView.setViewName("/message/basemessage");
            return modelAndView;
        }
        TConsumer tConsumer3 = consumerService.selectByWchat(tConsumer.getWchat());
        if (tConsumer3 != null) {
            modelAndView.addObject("error", "微信已存在，请重新尝试");
            modelAndView.setViewName("/message/basemessage");
            return modelAndView;
        }
        if (id != null) {
            tConsumer.setId(id);
            consumerService.updateByPrimaryKey(tConsumer);
            modelAndView.setViewName("home/home");
            modelAndView.addObject("tConsumer", tConsumer);
            return modelAndView;
        }
        modelAndView.setViewName("/message/messageError");
        return modelAndView;
    }

    @RequestMapping("saveMessage")
    @ResponseBody
    public String saveMessage(TConsumer tConsumer, ModelAndView modelAndView) {
        Long id = null;
        if (consumer.getUsername() != null && consumer.getPassword() != null) {
            tConsumer.setUsername(consumer.getUsername());
            tConsumer.setPhone(Long.parseLong(consumer.getUsername()));
            tConsumer.setPassword(consumer.getPassword());
            tConsumer.setGmtCreate(consumer.getGmtCreate());
            tConsumer.setGmtModified(DateUtil.getDaDate());
            id = consumerService.selectIdByUsernameAndPassword(tConsumer);
        }
        if (id == null) {
            modelAndView.addObject("error", "用户信息错误，请重新登录");
            modelAndView.setViewName("/message/basemessage");
            return "error";
        }
        TConsumer tConsumer1 = consumerService.selectByIdCard(tConsumer.getIdCard());
        if (tConsumer1 != null) {
            modelAndView.addObject("error", "身份证已存在，请重新尝试");
            modelAndView.setViewName("/message/basemessage");
            return "error1";
        }
        TConsumer tConsumer2 = consumerService.selectByEmail(tConsumer.getEmail());
        if (tConsumer2 != null) {
            modelAndView.addObject("error", "电子邮箱已存在，请重新尝试");
            modelAndView.setViewName("/message/basemessage");
            return "error2";
        }
        TConsumer tConsumer3 = consumerService.selectByWchat(tConsumer.getWchat());
        if (tConsumer3 != null) {
            modelAndView.addObject("error", "微信已存在，请重新尝试");
            modelAndView.setViewName("/message/basemessage");
            return "error3";
        }
        if (id != null) {
            tConsumer.setId(id);
            consumerService.updateByPrimaryKey(tConsumer);
            modelAndView.setViewName("home/home");
            modelAndView.addObject("tConsumer", tConsumer);
            return id.toString();
        }
        modelAndView.setViewName("/message/messageError");
        return "error4";
    }

}
