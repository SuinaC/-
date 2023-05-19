package com.jia.front.client;

import com.jia.entity.TDoctor;
import com.jia.entity.TDoctorResume;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title IDoctorService
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/4/29 20:54
 **/
@FeignClient("doctor-service")
public interface IDoctorClient {


    @RequestMapping("/doctorInfo/key")
    @ResponseBody
    TDoctor selectByPrimaryKey(@RequestBody Long id);

    @RequestMapping("/doctorInfo/updateDoctor")
    @ResponseBody
    int updateByPrimaryKey(@RequestBody TDoctor doctor);

    @RequestMapping("/doctorInfo/allDoctor")
    @ResponseBody
    List<TDoctor> selectAll();

    @RequestMapping("/doctorInfo/department")
    @ResponseBody
    List<TDoctor> selectByDepartment(@RequestBody String s);

    @RequestMapping("/doctorInfo/DoctorCount")
    @ResponseBody
    Integer selectCount();

    @RequestMapping("/doctorInfo/resume")
    @ResponseBody
    List<TDoctorResume> selectByDoctorId(@RequestBody Long id);

    @RequestMapping("/doctorInfo/user")
    @ResponseBody
    TDoctor selectByUsername(@RequestBody String username);

    @RequestMapping("/doctorInfo/address")
    @ResponseBody
    List<TDoctor> selectByAddress(@RequestBody String address);

    @RequestMapping("/doctorInfo/delete")
    @ResponseBody
    int deleteByPrimaryKey(@RequestBody Long id);

    @RequestMapping("/doctorInfo/name/address")
    @ResponseBody
    List<TDoctor> selectByNameAndAddress(@RequestBody TDoctor tDoctor);

    @RequestMapping("/doctorInfo/phone")
    @ResponseBody
    TDoctor selectByPhone(@RequestBody Long phone);

    @RequestMapping("/doctorInfo/wechat")
    @ResponseBody
    TDoctor selectByWchat(@RequestBody String wechat);

    @RequestMapping("/doctorInfo/email")
    @ResponseBody
    TDoctor selectByEmail(@RequestBody String email);

    @RequestMapping("/doctorInfo/idcard")
    @ResponseBody
    TDoctor selectByIdCard(@RequestBody String idCard);

    @RequestMapping("/doctorInfo/insert")
    @ResponseBody
    int insert(@RequestBody TDoctor doctor);

}
