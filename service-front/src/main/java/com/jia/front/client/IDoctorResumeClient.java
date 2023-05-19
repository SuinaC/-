package com.jia.front.client;

import com.jia.entity.TDoctorResume;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title IDoctorResumeClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:02
 **/

@FeignClient("doctor-service")
public interface IDoctorResumeClient {
    @RequestMapping("doctorInfo/resume")
    @ResponseBody
    List<TDoctorResume> selectByDoctorId(@RequestBody Long id);

    @RequestMapping("doctorInfo/resume/insert")
    @ResponseBody
    int insert(@RequestBody TDoctorResume doctorResume);

}
