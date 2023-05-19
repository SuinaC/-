package com.jia.front.controller;


import com.jia.entity.TConsumer;
import com.jia.front.client.IConsumerClient;
import com.jia.utils.GetPlaceByIp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


/**
 * @author wangjia
 */
@Controller
@RequestMapping("home")
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Resource
    private IConsumerClient consumerService;

    @RequestMapping("home")
    public ModelAndView toHome(ModelAndView modelAndView) {
        TConsumer tConsumer = new TConsumer();
        tConsumer.setName("游客");
        tConsumer.setAddress(GetPlaceByIp.getAddress());
        modelAndView.addObject("tConsumer", tConsumer);
        modelAndView.setViewName("home/home");
        return modelAndView;
    }

    @RequestMapping("login")
    public ModelAndView loginToHome(ModelAndView modelAndView, String id) {
        if (id != null || !"".equals(id)) {
            TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
            tConsumer.setAddress(GetPlaceByIp.getAddress());
            modelAndView.addObject("tConsumer", tConsumer);
        } else {
            modelAndView.setViewName("home/home");
            return modelAndView;
        }
        modelAndView.setViewName("home/home");
        return modelAndView;
    }


    @RequestMapping("changeAddress")
    @ResponseBody
    public String changeAddress() {
        String address = GetPlaceByIp.getAddress();
        if (address != "error") {
            return address;
        } else {
            return "error";
        }
    }


}
