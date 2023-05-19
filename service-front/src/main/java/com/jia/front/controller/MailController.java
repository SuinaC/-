package com.jia.front.controller;


import com.jia.entity.TConsumer;
import com.jia.entity.TMail;
import com.jia.front.client.IConsumerClient;
import com.jia.front.client.IMailClient;
import com.jia.utils.DateUtil;
import com.jia.utils.MailUtils;
import com.jia.utils.ReciveMail;
import com.jia.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

@RequestMapping("mailInbox")
@Controller
public class MailController {
    private static final Logger logger = LoggerFactory.getLogger(MailController.class);

    @Resource
    IConsumerClient consumerService;

    @Resource
    IMailClient mailService;

    @Value("${file.upload.path}")
    private String filePath;

    @RequestMapping("mail")
    public String mail(Long id, Model model){
        if(id == null){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(id);
        TMail tMail = mailService.selectByConsumerId(id);
        if(tMail == null){
            model.addAttribute("tConsumer",tConsumer);
            return "information/addMailMessage";
        }
        List<Map<String, Object>> maps = ReciveMail.reciveMail(tMail.getUsername(), tMail.getPassword(), id);
        for (int i = 0; i < maps.size(); i++) {
            String form = StringUtil.toString(maps.get(i).get("form"));
            String[] split = form.split("<");
            maps.get(i).put("form",split[0]);
            String s = StringUtil.deleteCharString8(split[split.length - 1], '>');
            maps.get(i).put("formId",s);
            String url = "/images/mail/" + tConsumer.getId() + "/" + maps.get(i).get("Message-ID") + ".html";
            maps.get(i).put("url",url);
        }
        model.addAttribute("tConsumer",tConsumer);
        model.addAttribute("mailList",maps);
        model.addAttribute("tMail",tMail);
        return "information/mail";
    }

    @RequestMapping("addMailMessage")
    @ResponseBody
    public String addMailMessage(String email,String password,Long consumerId){
        if(email == "" || password == ""){
            return "error:" + consumerId;
        }
        TMail tMail = new TMail();
        tMail.setUsername(email);
        tMail.setPassword(password);
        tMail.setConsumerId(consumerId);
        tMail.setGmtCreate(DateUtil.getDaDate());
        int insert = mailService.insert(tMail);
        if(insert == 1){
            return "success:" + consumerId;
        }
        return "error:" + consumerId;
    }

    @RequestMapping("sendMail")
    public String sendMail(Long id,Model model){
        if(id == null){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(id);
        model.addAttribute("tConsumer",tConsumer);
        return "information/sendMail";
    }

    @RequestMapping("sendMailMessage")
    @ResponseBody
    public String sendMailMessage(String toUser,String title,String contentText,Long consumerId){
        if(toUser == "" || title == "" || contentText == ""){
            return "error:" + consumerId;
        }
        contentText = contentText.replaceAll("\n","<br>");
        TMail tMail = mailService.selectByConsumerId(consumerId);
        try {
            MailUtils.sendMailMessage(toUser,title,contentText,tMail.getUsername(),tMail.getPassword());
            return "success:" + consumerId;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "error:" + consumerId;
    }
}
