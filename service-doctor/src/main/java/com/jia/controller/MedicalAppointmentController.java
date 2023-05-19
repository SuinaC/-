package com.jia.controller;

import com.jia.entity.TMedicalAppointment;
import com.jia.service.IMedicalAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @title MedicalAppointmentController
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/5 10:19
 **/
@RestController
@RequestMapping("medicalAppointment")
public class MedicalAppointmentController {
    @Autowired
    IMedicalAppointmentService medicalAppointmentService;

    @RequestMapping("/consumer")
    @ResponseBody
    public List<TMedicalAppointment> selectMedicalByCid(@RequestBody Long id){
        List<TMedicalAppointment> tMedicalAppointments = medicalAppointmentService.selectByConsumerId(id);
        return tMedicalAppointments;
    }
    @RequestMapping("/key")
    @ResponseBody
    public TMedicalAppointment selectMAByPrimaryKey(@RequestBody Long id){
        TMedicalAppointment tMedicalAppointment = medicalAppointmentService.selectByPrimaryKey(id);
        return tMedicalAppointment;
    }

    @RequestMapping("/insert")
    public int insertAppointment(@RequestBody TMedicalAppointment tMedicalAppointment){
        int insert = medicalAppointmentService.insert(tMedicalAppointment);
        return insert;
    }


}
