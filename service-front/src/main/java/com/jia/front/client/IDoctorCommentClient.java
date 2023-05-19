package com.jia.front.client;

import com.jia.entity.TCommentGreat;
import com.jia.entity.TCommentReply;
import com.jia.entity.TDoctorComment;
import com.jia.entity.vo.ConsumerCommentVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title IDoctorCommentClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/9 00:17
 **/
@FeignClient("doctor-service")
public interface IDoctorCommentClient {
    @RequestMapping("/comment/count/doctor")
    @ResponseBody
    int selectCountByDoctorId(@RequestBody Long id);

    @RequestMapping("/comment/doctor")
    @ResponseBody
    List<TDoctorComment> selectByDoctorId(@RequestBody Long id);

    @RequestMapping("/comment/great/consumer")
    @ResponseBody
    TCommentGreat selectTCommentGreatByConsumerIdAndCommentId(@RequestBody ConsumerCommentVo consumerCommentVo);

    @RequestMapping("/comment/reply/consumer")
    @ResponseBody
    List<TCommentReply> selectTCommentReplyByConsumerIdAndCommentId(@RequestBody ConsumerCommentVo consumerCommentVo);

    @RequestMapping("/comment/insert")
    @ResponseBody
    int insert(@RequestBody TDoctorComment t);

    @RequestMapping("/comment/great/insert")
    @ResponseBody
    int insertCommentGreat(@RequestBody TCommentGreat t);

    @RequestMapping("/comment/key")
    @ResponseBody
    TDoctorComment selectCommentByPrimaryKey(@RequestBody Long id);

    @RequestMapping("/comment/great")
    @ResponseBody
    TCommentGreat selectGreatByPrimaryKey(@RequestBody Long id);

    @RequestMapping("/comment/update")
    @ResponseBody
    int updateCommentByPrimaryKey(@RequestBody TDoctorComment t);

    @RequestMapping("/comment/great/update")
    @ResponseBody
    int updateGreatByPrimaryKey(@RequestBody TCommentGreat t);

    @RequestMapping("/comment/reply/insert")
    @ResponseBody
    int insertTCommentReply(@RequestBody TCommentReply tCommentReply);

    @RequestMapping("/comment/reply")
    @ResponseBody
    TCommentReply selectTCommentReplyByPrimaryKey(@RequestBody Long id);

    @RequestMapping("/comment/reply/update")
    @ResponseBody
    int updateTCommentReplyByPrimaryKey(@RequestBody TCommentReply t);

}
