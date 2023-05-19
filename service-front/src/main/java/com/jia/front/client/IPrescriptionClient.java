package com.jia.front.client;

import com.jia.entity.TPrescription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title IPrescriptionClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:04
 **/
@FeignClient("drug-service")
public interface IPrescriptionClient {
    @RequestMapping("prescription/consumer")
    @ResponseBody
    List<TPrescription> selectByConsumerId(@RequestBody Long id);

    @RequestMapping("prescription/insert")
    @ResponseBody
    long insert(@RequestBody TPrescription tPrescription);

    @RequestMapping("prescription/lastInsert")
    @ResponseBody
    long selectLastInsertId();

    @RequestMapping("prescription/key")
    @ResponseBody
    TPrescription selectByPrimaryKey(@RequestBody Long id);

    @RequestMapping("prescription/update")
    @ResponseBody
    int updateByPrimaryKey(@RequestBody TPrescription tPrescription);

    @RequestMapping("prescription/doctor")
    @ResponseBody
    List<TPrescription> selectByDoctorId(@RequestBody Long id);
}
