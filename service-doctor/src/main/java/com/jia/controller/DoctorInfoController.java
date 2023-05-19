package com.jia.controller;

import com.jia.entity.TDoctor;
import com.jia.entity.TDoctorResume;
import com.jia.service.IDoctorResumeService;
import com.jia.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * @title DoctorInfoController
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:07
 **/
@RequestMapping("doctorInfo")
@RestController
public class DoctorInfoController {

    @Autowired
    IDoctorService doctorService;


    @Autowired
    IDoctorResumeService doctorResumeService;

    @RequestMapping("/key")
    @ResponseBody
    public TDoctor findDoctorById(@RequestBody Long id){
        return doctorService.selectByPrimaryKey(id);
    }



    @RequestMapping("/updateDoctor")
    public int  updateByPrimaryKey(@RequestBody TDoctor doctor){
       return doctorService.updateByPrimaryKey(doctor);
    }
    @RequestMapping("/allDoctor")
    public List<TDoctor> allDoctor(){
        List<TDoctor> tDoctors=doctorService.selectAll();
        return tDoctors;
    }

    @RequestMapping("/department")
    @ResponseBody
    public List<TDoctor> selectByDepartment(@RequestBody String s){
        List<TDoctor> tDoctors=doctorService.selectByDepartment(s);
        return tDoctors;
    }


    @RequestMapping("/DoctorCount")
    public Integer DoctorCount(){
        return doctorService.selectCount();
    }


    @RequestMapping("/user")
    @ResponseBody
    public TDoctor ByUserName(@RequestBody String username){
        System.out.println(username);
        return doctorService.selectByUsername(username);
    }

    @RequestMapping("/address")
    @ResponseBody
    public List<TDoctor> selectByAddress(@RequestBody String address){
        return doctorService.selectByAddress(address);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int deleteByPrimaryKey(@RequestBody Long id){
        return doctorService.deleteByPrimaryKey(id);
    }
    @RequestMapping("/name/address")
    public List<TDoctor> selectByNameAndAddress(@RequestBody TDoctor tDoctor){
        return doctorService.selectByNameAndAddress(tDoctor);
    }
    @RequestMapping("/phone")
    @ResponseBody
    public TDoctor selectByPhone(@RequestBody Long phone){
        return doctorService.selectByPhone(phone);
    }
    @RequestMapping("/wechat")
    @ResponseBody
    public TDoctor selectByWechat(@RequestBody String wechat){
        return doctorService.selectByWchat(wechat);
    }

    @RequestMapping("/email")
    @ResponseBody
    public TDoctor selectByEmail(@RequestBody String email){
        return doctorService.selectByEmail(email);
    }

    @RequestMapping("/idcard")
    @ResponseBody
    public TDoctor selectByIdCard(@RequestBody String idCard){
        return doctorService.selectByIdCard(idCard);
    }

    @RequestMapping("/insert")
    public int insert(@RequestBody TDoctor doctor){
        return doctorService.insert(doctor);
    }

    @RequestMapping("/resume")
    @ResponseBody
    public List<TDoctorResume> ResumeById(@RequestBody Long id){
        return doctorResumeService.selectByDoctorId(id);
    }

    @RequestMapping("/resume/insert")
    public int resumeInsert(@RequestBody TDoctorResume doctorResume){
        return doctorResumeService.insert(doctorResume);}

}
