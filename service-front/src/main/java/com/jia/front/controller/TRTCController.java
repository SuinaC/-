package com.jia.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("TRTC")
@Controller
public class TRTCController {

    @RequestMapping("video")
    public String demo(){
        return "TRTC/index";
    }

}
