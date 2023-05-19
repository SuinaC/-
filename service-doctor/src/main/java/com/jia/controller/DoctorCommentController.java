package com.jia.controller;

import com.jia.entity.TCommentGreat;
import com.jia.entity.TCommentReply;
import com.jia.entity.TDoctorComment;
import com.jia.entity.vo.ConsumerCommentVo;
import com.jia.service.IDoctorCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * @title DoctorCommentController
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/9 09:57
 **/
@RestController
@RequestMapping("comment")
public class DoctorCommentController {
    @Autowired
    IDoctorCommentService doctorCommentService;


    @RequestMapping("/count/doctor")
    @ResponseBody
    public int selectCountByDoctorId(@RequestBody Long id){
        return doctorCommentService.selectCountByDoctorId(id);
    }

    @RequestMapping("/doctor")
    @ResponseBody
    public List<TDoctorComment> selectByDoctorId(@RequestBody Long id){
        return doctorCommentService.selectByDoctorId(id);
    }

    @RequestMapping("/great/consumer")
    @ResponseBody
    public TCommentGreat selectTCommentGreatByConsumerIdAndCommentId(@RequestBody ConsumerCommentVo consumerCommentVo){
        Long cid = consumerCommentVo.getConsumerId();
        Long id = consumerCommentVo.getCommentId();
        return doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(cid,id);
    }

    @RequestMapping("/reply/consumer")
    @ResponseBody
    public List<TCommentReply> selectTCommentReplyByConsumerIdAndCommentId(@RequestBody ConsumerCommentVo consumerCommentVo){
        Long cid = consumerCommentVo.getConsumerId();
        Long id = consumerCommentVo.getCommentId();
        return doctorCommentService.selectTCommentReplyByConsumerIdAndCommentId(cid,id);
    }

    @RequestMapping("/insert")
    public int insert(@RequestBody TDoctorComment t){
        return doctorCommentService.insert(t);
    }

    @RequestMapping("/great/insert")
    public int insertCommentGreat(@RequestBody TCommentGreat t){
        return doctorCommentService.insertCommentGreat(t);
    }

    @RequestMapping("/key")
    @ResponseBody
    public TDoctorComment selectCommentByPrimaryKey(@RequestBody Long id){
        return doctorCommentService.selectCommentByPrimaryKey(id);
    }

    @RequestMapping("/great")
    @ResponseBody
    public TCommentGreat selectGreatByPrimaryKey(@RequestBody Long id){
        return doctorCommentService.selectGreatByPrimaryKey(id);
    }

    @RequestMapping("/update")
    public int updateCommentByPrimaryKey(@RequestBody TDoctorComment t){
        return doctorCommentService.updateCommentByPrimaryKey(t);
    }

    @RequestMapping("/great/update")
    public int updateGreatByPrimaryKey(@RequestBody TCommentGreat t){
        return doctorCommentService.updateGreatByPrimaryKey(t);
    }

    @RequestMapping("/reply/insert")
    public int insertTCommentReply(@RequestBody TCommentReply tCommentReply){
        return doctorCommentService.insertTCommentReply(tCommentReply);
    }

    @RequestMapping("/reply")
    @ResponseBody
    public TCommentReply selectTCommentReplyByPrimaryKey(@RequestBody Long id){
        return doctorCommentService.selectTCommentReplyByPrimaryKey(id);
    }

    @RequestMapping("/reply/update")
    public int updateTCommentReplyByPrimaryKey(@RequestBody TCommentReply t){
        return doctorCommentService.updateTCommentReplyByPrimaryKey(t);
    }


}
