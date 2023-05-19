package com.jia.controller;

import com.jia.entity.TMail;
import com.jia.service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @title MailController
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/9 10:51
 **/

@RestController
@RequestMapping("mail")
public class MailController {

    @Autowired
    IMailService mailService;

    @RequestMapping("/insert")
    public int insert(@RequestBody TMail tMail){
        return mailService.insert(tMail);
    }

    @RequestMapping("/consumer")
    public TMail selectByConsumerId(@RequestBody Long id){
        return mailService.selectByConsumerId(id);
    }

//    int insert = mailService.insert(tMail);
//    TMail tMail = mailService.selectByConsumerId(id);

}
