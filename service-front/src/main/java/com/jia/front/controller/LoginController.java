package com.jia.front.controller;

import com.jia.front.client.IConsumerClient;

import com.jia.entity.TConsumer;
import com.jia.utils.DESCrypto;
import com.jia.utils.DateUtil;
import com.jia.utils.ZhenZiUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("consumer")
public class LoginController {

    private static String vCode = null;

    @Resource
    private IConsumerClient consumerService;

    @RequestMapping("get/{id}")
    @ResponseBody
    public TConsumer getById(@PathVariable("id") Long id) {

        return consumerService.selectByPrimaryKey(1L);
    }

    @GetMapping("login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("signup/login");
        return modelAndView;
    }

    @RequestMapping("index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @RequestMapping("login")
    @ResponseBody
    public String checkLogin(String username, String password) {
        TConsumer tConsumer = new TConsumer();
        tConsumer.setUsername(username);
        String pwd = DESCrypto.encode(password);

        tConsumer.setPassword(pwd);
        System.out.println(tConsumer.getPassword());
        TConsumer tConsumer1 = null;
        try {
            tConsumer1 = consumerService.selectByPrimaryKeyAndPassword(tConsumer);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error2");
        }
        if (tConsumer1 == null) {
            return "error1";
        }
        MessageController messageController = new MessageController();
        messageController.setUsernameAndPassword(tConsumer1);
        if (tConsumer1.getIdCard() == null || "".equals(tConsumer1.getIdCard())) {
            return "error";
        }
        Long id = tConsumer1.getId();
        if (id != null) {
            return id.toString();
        } else {
            return "error";
        }
    }

    @RequestMapping("register")
    @ResponseBody
    public String register(String username, String password, String verifyCode) {
        TConsumer tConsumer = new TConsumer();
        tConsumer.setUsername(String.valueOf(username));
        tConsumer.setPhone(Long.parseLong(String.valueOf(username)));
        tConsumer.setGmtCreate(DateUtil.getDaDate());
        tConsumer.setImg("/images/doctorPhoto/000000/default.jpg");
        tConsumer.setGmtModified(DateUtil.getDaDate());
        String password1 = DESCrypto.encode(String.valueOf(password));
        tConsumer.setPassword(password1);
        if (!verifyCode.equals(vCode)) {
            return "error1";
        }
        TConsumer tConsumer1 = consumerService.selectByPhone(Long.parseLong(username));
        if (tConsumer1 != null) {
            return "error2";
        }
        int insert = consumerService.insert(tConsumer);
        if (insert == 0) {
            return "error";
        }
        return "success";
    }

    @RequestMapping("sendSms")
    @ResponseBody
    public String sendSms(String regname, HttpSession session) {
        System.out.println(regname);
        Map<String, Object> stringObjectMap = ZhenZiUtil.sendSms(regname);
        if (stringObjectMap.get("send") == null) {
            return "error";
        }
        vCode = String.valueOf(stringObjectMap.get("verifyCode"));
        session.setAttribute("verifyCode", vCode);
        return "success";
    }

    @RequestMapping("forgetPassword")
    public ModelAndView forgetPassword(ModelAndView modelAndView) {
        modelAndView.setViewName("/signup/forgetPassword");
        return modelAndView;
    }

    @RequestMapping("forgetPwd")
    @ResponseBody
    public String forgetPwd(String verifyCode) {
        if (!vCode.equals(verifyCode)) {
            return "error";
        }
        return "success";
    }

    @RequestMapping("resetPwd")
    public String resetPwd(Model model, String phone) {
        TConsumer tConsumer = new TConsumer();
        tConsumer = consumerService.selectByPhone(Long.parseLong(phone));
        model.addAttribute("tConsumer", tConsumer);
        return "signup/reset";
    }

    @RequestMapping("reset")
    @ResponseBody
    public String reset(String phone, String pwd) {
        TConsumer tConsumer = new TConsumer();
        tConsumer = consumerService.selectByPhone(Long.parseLong(phone));
        String password = DESCrypto.encode(pwd);
        if (password.equals(tConsumer.getPassword())) {
            return "error1";
        }
        tConsumer.setPassword(password);
        tConsumer.setGmtModified(DateUtil.getDaDate());
        int i = consumerService.updateByPrimaryKey(tConsumer);
        if (i == 0) {
            return "error";
        }
        return "success";
    }

    @RequestMapping("rephone")
    @ResponseBody
    public String rePhone(String phone) {
        TConsumer tConsumer = new TConsumer();
        tConsumer = consumerService.selectByPhone(Long.parseLong(phone));
        if (tConsumer != null) {
            return "error";
        }
        return "success";
    }

}
