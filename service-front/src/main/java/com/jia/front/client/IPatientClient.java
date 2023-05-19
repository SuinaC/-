package com.jia.front.client;

import com.jia.entity.TExistingPatient;
import com.jia.entity.TMedicalRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title IPatientClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:01
 **/
@FeignClient("patient-service")
public interface IPatientClient {

    @RequestMapping("patient/medical/record/doctor")
    @ResponseBody
    List<TMedicalRecord> selectTMedicalRecordByDoctorId(@RequestBody Long id);

    @RequestMapping("patient/medical/record")
    @ResponseBody
    int updateTMedicalRecordByPrimaryKey(@RequestBody TMedicalRecord tMedicalRecord);

    @RequestMapping("patient/exist/doctor")
    @ResponseBody
    List<TExistingPatient> selectTExistingPatientByDoctorId(@RequestBody Long id);

    @RequestMapping("patient/exist")
    @ResponseBody
    TExistingPatient selectByTExistingPatientPrimaryKey(@RequestBody Long id);

    @RequestMapping("patient/exist/delete")
    @ResponseBody
    int deleteTExistingPatientByPrimaryKey(@RequestBody Long id);

    @RequestMapping("patient/medical/record/insert")
    @ResponseBody
    int insertTMedicalRecordSelective(@RequestBody TMedicalRecord tMedicalRecord);

    @RequestMapping("patient/medical/record/last")
    Long selectTMedicalRecordLastInsertId();

    @RequestMapping("patient/exist/update")
    @ResponseBody
    int updateTExistingPatientByPrimaryKey(@RequestBody TExistingPatient tExistingPatient);

    @RequestMapping("patient/exist/search")
    @ResponseBody
    List<TExistingPatient> selectTExistingPatientBySearch(@RequestBody TExistingPatient tExistingPatient);

    @RequestMapping("patient/medical/record/search")
    @ResponseBody
    List<TMedicalRecord> selectTMedicalRecordBySearch(@RequestBody TMedicalRecord tMedicalRecord);

    @RequestMapping("patient/exist/insert")
    @ResponseBody
    int insertTExistingPatientSelective(@RequestBody TExistingPatient tExistingPatient);

    @RequestMapping("patient/exist/last")
    Long selectTExistingPatientLastInsertId();
    @RequestMapping("patient/medical/record/key")
    @ResponseBody
    TMedicalRecord selectByTMedicalRecordPrimaryKey(@RequestBody Long id);

    @RequestMapping("patient/medical/record/consumer")
    @ResponseBody
    List<TMedicalRecord> selectTMedicalRecordByConsumerId(@RequestBody Long id);

    @RequestMapping("patient/exist/consumer")
    @ResponseBody
    List<TExistingPatient> selectTExistingPatientByConsumerId(@RequestBody Long id);

}
