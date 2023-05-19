package com.jia.front.client;

import com.jia.entity.TAppointmentSchedule;
import com.jia.entity.TDoctor;
import com.jia.entity.vo.DateAddressVo;
import com.jia.entity.vo.DateDoctorVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title IPatientOrderClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:03
 **/

@FeignClient("patient-service")
public interface IPatientOrderClient {
    @RequestMapping("patientOrder/exist")
    @ResponseBody
    Long selectIdByExisitingId(@RequestBody Long id);

    @RequestMapping("patientOrder/key")
    @ResponseBody
    TAppointmentSchedule selectByPrimaryKey(@RequestBody Long id);

    @RequestMapping("patientOrder/update")
    @ResponseBody
    int updateByPrimaryKey(@RequestBody TAppointmentSchedule tAppointmentSchedule);

    @RequestMapping("patientOrder/order")
    @ResponseBody
    List<TAppointmentSchedule> selectOrderByDateIdAndDoctorId(@RequestBody DateDoctorVo dateDoctorVo);

    @RequestMapping("patientOrder/consumer")
    @ResponseBody
    List<TAppointmentSchedule> selectByConsumerId(@RequestBody Long cid);

    @RequestMapping("patientOrder/order2")
    @ResponseBody
    List<TAppointmentSchedule> selectByDateIdAndAddress(@RequestBody DateAddressVo dateAddressVo);
    @RequestMapping("patientOrder/insert")
    @ResponseBody
    int insert(@RequestBody TAppointmentSchedule tAppointmentSchedule);
    @RequestMapping("patientOrder/delete")
    @ResponseBody
    int deleteByPrimarykey(@RequestBody Long id);
    @RequestMapping("patientOrder/consumer/doctor")
    @ResponseBody
    List<TAppointmentSchedule> selectConsumerByDoctorId(@RequestBody Long id);

    @RequestMapping("patientOrder/date/doctor/update")
    @ResponseBody
    List<TAppointmentSchedule> selectByUpDateIdAndDoctorId(@RequestBody DateDoctorVo dateDoctorVo);

    @RequestMapping("patientOrder/date/doctor")
    @ResponseBody
    List<TAppointmentSchedule> selectByDateIdAndDoctorId(@RequestBody DateDoctorVo dateDoctorVo);

    @RequestMapping("patientOrder/order/sum")
    @ResponseBody
    long selectOrderSumByDoctorId(@RequestBody DateDoctorVo dateDoctorVo);

    @RequestMapping("patientOrder/today/sum")
    @ResponseBody
    long selectTodaySumByDoctorId(@RequestBody DateDoctorVo dateDoctorVo);

    @RequestMapping("patientOrder/sum/doctor")
    @ResponseBody
    long selectSumByDoctorId(@RequestBody Long id);

    @RequestMapping("patientOrder/evaluate")
    @ResponseBody
    List<TDoctor> selectDoctorEvaluate();

    @RequestMapping("patientOrder/evaluateSum")
    @ResponseBody
    List<TDoctor> selectDoctorEvaluateSum();
}
