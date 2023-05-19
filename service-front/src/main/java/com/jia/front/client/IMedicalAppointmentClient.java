package com.jia.front.client;

import com.jia.entity.TMedicalAppointment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title IMedicalAppointmentClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/9 00:42
 **/
@FeignClient("doctor-service")
public interface IMedicalAppointmentClient {
    @RequestMapping("/medicalAppointment/consumer")
    @ResponseBody
    List<TMedicalAppointment> selectByConsumerId(@RequestBody  Long id);

    @RequestMapping("/medicalAppointment/key")
    @ResponseBody
    TMedicalAppointment selectByPrimaryKey(@RequestBody Long id);

    @RequestMapping("/medicalAppointment/insert")
    @ResponseBody
    int insert(@RequestBody TMedicalAppointment tMedicalAppointment);
}
