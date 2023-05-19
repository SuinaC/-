package com.jia.front.controller;


import com.jia.entity.TAppointmentSchedule;
import com.jia.entity.TConsumer;
import com.jia.entity.TDoctor;
import com.jia.entity.TMedicalAppointment;
import com.jia.entity.vo.DateAddressVo;
import com.jia.front.client.*;
import com.jia.front.config.RedisUtil;
import com.jia.utils.CommonDiseasesUtil;
import com.jia.utils.DateUtil;
import com.jia.utils.MailUtils;
import com.jia.utils.ZhenZiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wangjia
 */
@RequestMapping("consumerOrder")
@Controller
public class ConsumerOrderController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerOrderController.class);

    @Resource
    IMedicalAppointmentClient medicalAppointmentService;

    @Resource
    IConsumerClient consumerService;

    @Resource
    IPatientOrderClient patientOrderService;

    @Resource
    IDoctorCommentClient doctorCommentService;

    @Resource
    IDoctorClient doctorService;


    @Autowired
    RedisUtil redisUtil;



    private static String PCODE = null;

    private static String ECODE = null;


    @RequestMapping("consumerOrder")
    public String consumerOrder(String id, Model model){
        if(id.equals("")){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        if(tConsumer != null){
            model.addAttribute("tConsumer",tConsumer);
            return "information/consumerOrder";
        }
        return "500";
    }

    @RequestMapping("updateOrder")
    public String updateOrder(String address,String department,Model model){
        if(address.equals("") || department.equals("")){
            return "error";
        }
        String[] split = address.split("/");
        Date date = new Date();
        int day = date.getDay();

        DateAddressVo vs = new DateAddressVo();
        vs.setDateId(DateUtil.getLongDate());
        vs.setAddress(split[split.length - 1]);
        vs.setDepartment(department);
        List<TAppointmentSchedule> tAppointmentSchedules = patientOrderService.selectByDateIdAndAddress(vs);

        DateAddressVo v1 = new DateAddressVo();
        v1.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000)));
        v1.setAddress(split[split.length - 1]);
        v1.setDepartment(department);
        List<TAppointmentSchedule> tAppointmentSchedules1 = patientOrderService.selectByDateIdAndAddress(v1);

        DateAddressVo v2 = new DateAddressVo();
        v2.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 2)));
        v2.setAddress(split[split.length - 1]);
        v2.setDepartment(department);
        List<TAppointmentSchedule> tAppointmentSchedules2 = patientOrderService.selectByDateIdAndAddress(v2);

        DateAddressVo v3 = new DateAddressVo();
        v3.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 3)));
        v3.setAddress(split[split.length - 1]);
        v3.setDepartment(department);
        List<TAppointmentSchedule> tAppointmentSchedules3 = patientOrderService.selectByDateIdAndAddress(v3);

        DateAddressVo v4 = new DateAddressVo();
        v4.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 4)));
        v4.setAddress(split[split.length - 1]);
        v4.setDepartment(department);
        List<TAppointmentSchedule> tAppointmentSchedules4 = patientOrderService.selectByDateIdAndAddress(v4);

        DateAddressVo v5 = new DateAddressVo();
        v5.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 5)));
        v5.setAddress(split[split.length - 1]);
        v5.setDepartment(department);
        List<TAppointmentSchedule> tAppointmentSchedules5 = patientOrderService.selectByDateIdAndAddress(v5);

        DateAddressVo v6 = new DateAddressVo();
        v6.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 6)));
        v6.setAddress(split[split.length - 1]);
        v6.setDepartment(department);
        List<TAppointmentSchedule> tAppointmentSchedules6 = patientOrderService.selectByDateIdAndAddress(v6);
        if(day == 0){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules1);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules2);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules3);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules4);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules5);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules6);
            return "information/doctorOrder::order_refresh";
        }else if(day == 1){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules6);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules1);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules2);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules3);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules4);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules5);
            return "information/doctorOrder::order_refresh";
        }else if(day == 2){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules5);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules6);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules1);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules2);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules3);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules4);
            return "information/doctorOrder::order_refresh";
        }else if(day == 3){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules4);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules5);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules6);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules1);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules2);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules3);
            return "information/doctorOrder::order_refresh";
        }else if(day == 4){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules3);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules4);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules5);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules6);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules1);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules2);
            return "information/doctorOrder::order_refresh";
        }else if(day == 5){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules2);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules3);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules4);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules5);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules6);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules1);
            return "information/doctorOrder::order_refresh";
        }else if(day == 6){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules1);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules2);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules3);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules4);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules5);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules6);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules);
            return "information/doctorOrder::order_refresh";
        } 
        return "error";
    }


    @RequestMapping("orderTime")
    @ResponseBody
    public String orderTime(String id,String consumerId){
        if(id.equals("") || consumerId.equals("")){
            return "error";
        }
        Boolean flag = redisUtil.hasKey(id);
        if(flag){
            return "error1";
        }
        System.out.println("before"+consumerId);
        redisUtil.lLeftPush(id,consumerId);
        redisUtil.expire(id,300, TimeUnit.SECONDS);
        List<String> range = redisUtil.getListRange(id, 0, -1);
        String s = range.get(0);
        System.out.println("after"+consumerId);
        if (s.equals(consumerId)){
            TAppointmentSchedule tAppointmentSchedule = new TAppointmentSchedule();
            tAppointmentSchedule = patientOrderService.selectByPrimaryKey(Long.parseLong(id));
            tAppointmentSchedule.setConsumerId(Long.parseLong(consumerId));
            tAppointmentSchedule.setGmtModified(DateUtil.getDaDate());
            tAppointmentSchedule.setStatus(0);
            int i = patientOrderService.updateByPrimaryKey(tAppointmentSchedule);
            if(i == 1){
                return "success";
            }else {
                return "error";
            }
        }else {
            return "error1";
        }
    }

    private List<String> getConsumerId(String id) {
        List<String> range = redisUtil.getListRange(id, 0, -1);
        System.out.println(range);
        return range;

    }


    @RequestMapping("touristOrder")
    public String touristOrder(){
        return "information/touristOrder";
    }

    @RequestMapping("checkPhone")
    @ResponseBody
    public String checkPhone(String phone){
        String[] split = phone.split("@");
        TConsumer tConsumer = null;
        if(split.length == 2){
             tConsumer = consumerService.selectByEmail(phone);
        }else if(split.length == 1){
             tConsumer = consumerService.selectByPhone(Long.parseLong(phone));
        }
        if(tConsumer == null){
            return "success";
        }else if(tConsumer.getIdCard().equals("") || tConsumer.getIdCard() == null){
            return "success";
        }else {
            return "error";
        }

    }

    @RequestMapping("sendCode")
    @ResponseBody
    public String sendCode(String phone){
        if (phone.equals("")){
            return "error";
        }
        String[] split = phone.split("@");
        if(split.length == 2){
            try {
                ECODE = MailUtils.sendMail(phone);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return "success";
        }else if(split.length == 1){
            Map<String, Object> stringObjectMap = ZhenZiUtil.sendSms(phone);
            PCODE = String.valueOf(stringObjectMap.get("verifyCode"));
            return "success";
        }
        return "error";
    }

    @RequestMapping("checkPhoneCode")
    @ResponseBody
    public String checkPhoneCode(String phoneCode,String phone){
        if (phone.equals("") || phoneCode.equals("")){
            return "error";
        }
        String[] split = phone.split("@");
        if(split.length == 2){
           if(phoneCode.equals(ECODE)){
               return "success";
           }else {
               return "error";
           }
        }else if(split.length == 1){
            if(phoneCode.equals(PCODE)){
                return "success";
            }else {
                return "error";
            }
        }
        return "error";
    }

    @RequestMapping("touristOrderTime")
    @ResponseBody
    public String touristOrderTime(TConsumer tConsumer){
        if(tConsumer == null){
            return "error";
        }
        Long id = tConsumer.getId();
        tConsumer.setId(null);
        tConsumer.setAddress(null);
        tConsumer.setGmtCreate(DateUtil.getDaDate());
        if(tConsumer.getPhone() != null){
            TConsumer tConsumer1 = consumerService.selectByPhone(tConsumer.getPhone());
            if(tConsumer1 == null){
                int insert = consumerService.insert(tConsumer);
                if (insert == 1){
                    TAppointmentSchedule tAppointmentSchedule = patientOrderService.selectByPrimaryKey(id);
                    assert tConsumer1 != null;
                    tAppointmentSchedule.setConsumerId(tConsumer1.getId());
                    tAppointmentSchedule.setStatus(0);
                    tAppointmentSchedule.setGmtModified(DateUtil.getDaDate());
                    int insert1 = patientOrderService.updateByPrimaryKey(tAppointmentSchedule);
                    if(insert1 == 1){
                        return "success";
                    }else {
                        return "error";
                    }
                }else {
                    return "error";
                }
            }else {
                TAppointmentSchedule tAppointmentSchedule = patientOrderService.selectByPrimaryKey(id);
                tAppointmentSchedule.setConsumerId(tConsumer1.getId());
                tAppointmentSchedule.setStatus(0);
                tAppointmentSchedule.setGmtModified(DateUtil.getDaDate());
                int insert = patientOrderService.updateByPrimaryKey(tAppointmentSchedule);
                if(insert == 1){
                    return "success";
                }else {
                    return "error";
                }
            }
        }
        if(tConsumer.getEmail() != null){
            TConsumer tConsumer1 = consumerService.selectByEmail(tConsumer.getEmail());
            if(tConsumer1 == null){
                int insert = consumerService.insert(tConsumer);
                if (insert == 1){
                    TAppointmentSchedule tAppointmentSchedule = patientOrderService.selectByPrimaryKey(id);
                    assert tConsumer1 != null;
                    tAppointmentSchedule.setConsumerId(tConsumer1.getId());
                    tAppointmentSchedule.setStatus(0);
                    tAppointmentSchedule.setGmtModified(DateUtil.getDaDate());
                    int insert1 = patientOrderService.updateByPrimaryKey(tAppointmentSchedule);
                    if(insert1 == 1){
                        return "success";
                    }else {
                        return "error";
                    }
                }else {
                    return "error";
                }
            }else {
                TAppointmentSchedule tAppointmentSchedule = patientOrderService.selectByPrimaryKey(id);
                tAppointmentSchedule.setConsumerId(tConsumer1.getId());
                tAppointmentSchedule.setStatus(0);
                tAppointmentSchedule.setGmtModified(DateUtil.getDaDate());
                int insert = patientOrderService.updateByPrimaryKey(tAppointmentSchedule);
                if(insert == 1){
                    return "success";
                }else {
                    return "error";
                }
            }
        }

        return "error";
    }

    @RequestMapping("evluate")
    @ResponseBody
    public String evluate(String id,String evalute){
        if(id.equals("") || evalute.equals("")){
            return "error";
        }
        TAppointmentSchedule tAppointmentSchedule = patientOrderService.selectByPrimaryKey(Long.parseLong(id));
        tAppointmentSchedule.setEvaluate(Double.valueOf(evalute));
        int i = patientOrderService.updateByPrimaryKey(tAppointmentSchedule);
        if(i == 1){
            List<TDoctor> tDoctors = patientOrderService.selectDoctorEvaluate();
            List<TDoctor> tDoctors1 = patientOrderService.selectDoctorEvaluateSum();
            for (TDoctor doctor : tDoctors) {
                TDoctor doctor1 = doctorService.selectByPrimaryKey(doctor.getId());
                doctor1.setEvaluate(doctor.getEvaluate());
                doctorService.updateByPrimaryKey(doctor1);
            }
            for (TDoctor doctor : tDoctors1) {
                TDoctor doctor1 = doctorService.selectByPrimaryKey(doctor.getId());
                doctor1.setEvaluateSum(doctor.getEvaluateSum());
                doctorService.updateByPrimaryKey(doctor1);
            }
            return "success";
        }
        return "error";

    }

    @RequestMapping("searchDisease")
    public String searchDisease(String searchDisease,Long id,Model model){
        if(searchDisease.equals("")){
            return "error";
        }
        List<TDoctor> tDoctors = doctorService.selectAll();
        TConsumer tConsumer = consumerService.selectByPrimaryKey(id);
        Set<TDoctor> set = new HashSet<>();
        for (TDoctor tDoctor : tDoctors) {
            if (tDoctor.getIntroduction() != null) {
                if (tDoctor.getIntroduction().contains(searchDisease)) {
                    set.add(tDoctor);
                }
                if (tDoctor.getLabel().contains(searchDisease)) {
                    set.add(tDoctor);
                }
            }
        }
        String s = CommonDiseasesUtil.SearchDepartment(searchDisease);
        List<TDoctor> tDoctors1 = doctorService.selectByDepartment(s);
        set.addAll(tDoctors1);
        for (TDoctor doctor : set) {
            int commentSum = doctorCommentService.selectCountByDoctorId(doctor.getId());
            if (doctor.getLabel().split("、").length != 0){
                List<String> split = Arrays.asList(doctor.getLabel().split("、"));
                doctor.setLabelList(split);
            }
            String paise = (double)(Math.round(doctor.getEvaluate() * 10)) + "%";
            doctor.setPraise(paise);
            doctor.setCommentSum(commentSum);
        }
        List<TDoctor> list = new ArrayList<>(set);
        list.sort(new Comparator<TDoctor>() {
            @Override
            public int compare(TDoctor o1, TDoctor o2) {
                return o1.getEvaluate() > o2.getEvaluate() ? -1 : 1;
            }
        });
        model.addAttribute("tDoctors",list);
        model.addAttribute("tConsumer",tConsumer);
        return "information/searchDoctor";
    }

    @RequestMapping("medicalOrder")
    public String medicalOrder(Long id,Model model){
        if(id == null){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(id);
        model.addAttribute("tConsumer",tConsumer);

        return "information/medicalOrder";
    }

    @RequestMapping("addMedicalOrder")
    @ResponseBody
    public String addMedicalOrder(TMedicalAppointment tMedicalAppointment){
        tMedicalAppointment.setStatus(0);
        tMedicalAppointment.setGmtCreate(DateUtil.getDaDate());
        if(!tMedicalAppointment.getMedicalExamination().equals("")){
            tMedicalAppointment.setMedicalExamination(tMedicalAppointment.getMedicalExamination().substring(1));
        }
        if(!tMedicalAppointment.getBloodRoutineExamination().equals("")){
            tMedicalAppointment.setBloodRoutineExamination(tMedicalAppointment.getBloodRoutineExamination().substring(1));
        }
        if(!tMedicalAppointment.getGeneralInspection().equals("")){
            tMedicalAppointment.setGeneralInspection(tMedicalAppointment.getGeneralInspection().substring(1));
        }
        if(!tMedicalAppointment.getEcgExamination().equals("")){
            tMedicalAppointment.setEcgExamination(tMedicalAppointment.getEcgExamination().substring(1));
        }
        if(!tMedicalAppointment.getHepaticExamination().equals("")){
            tMedicalAppointment.setHepaticExamination(tMedicalAppointment.getHepaticExamination().substring(1));
        }
        if(!tMedicalAppointment.getSurgicalExamination().equals("")){
            tMedicalAppointment.setSurgicalExamination(tMedicalAppointment.getSurgicalExamination().substring(1));
        }
        if(!tMedicalAppointment.getUrineRoutineExamination().equals("")){
            tMedicalAppointment.setUrineRoutineExamination(tMedicalAppointment.getUrineRoutineExamination().substring(1));
        }
        if(!tMedicalAppointment.getxRayExamination().equals("")){
            tMedicalAppointment.setxRayExamination(tMedicalAppointment.getxRayExamination().substring(1));
        }
        int insert = medicalAppointmentService.insert(tMedicalAppointment);
        if(insert == 1){
//            System.out.println(tMedicalAppointment.getConsumerId());
            return "success:" + tMedicalAppointment.getConsumerId();
        }
        return "error";
    }


    @RequestMapping("medicalAppointmentList")
    public String medicalAppointmentList(Long id,Model model){
        if(id == null){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(id);
        List<TMedicalAppointment> tMedicalAppointments = medicalAppointmentService.selectByConsumerId(id);
        model.addAttribute("tMedicalAppointments",tMedicalAppointments);
        model.addAttribute("tConsumer",tConsumer);
        return "information/medicalAppointmentList";
    }

    @RequestMapping("showMedicalAppointments")
    public String showMedicalAppointments(Long id,Long consumerId,Model model){
        if (id == null || consumerId == null){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(consumerId);
        TMedicalAppointment tMedicalAppointment = medicalAppointmentService.selectByPrimaryKey(id);
        String[] generalInspection = tMedicalAppointment.getGeneralInspection().split("、");
        String[] medicalExamination = tMedicalAppointment.getMedicalExamination().split("、");
        String[] surgicalExamination = tMedicalAppointment.getSurgicalExamination().split("、");
        String[] ecgExamination = tMedicalAppointment.getEcgExamination().split("、");
        String[] xRayExamination = tMedicalAppointment.getxRayExamination().split("、");
        String[] bloodRoutineExamination = tMedicalAppointment.getBloodRoutineExamination().split("、");
        String[] urineRoutineExamination = tMedicalAppointment.getUrineRoutineExamination().split("、");
        String[] hepaticExamination = tMedicalAppointment.getHepaticExamination().split("、");
        model.addAttribute("tConsumer",tConsumer);
        model.addAttribute("generalInspection",generalInspection);
        model.addAttribute("medicalExamination",medicalExamination);
        model.addAttribute("surgicalExamination",surgicalExamination);
        model.addAttribute("ecgExamination",ecgExamination);
        model.addAttribute("xRayExamination",xRayExamination);
        model.addAttribute("bloodRoutineExamination",bloodRoutineExamination);
        model.addAttribute("urineRoutineExamination",urineRoutineExamination);
        model.addAttribute("hepaticExamination",hepaticExamination);
        model.addAttribute("tMedicalAppointment",tMedicalAppointment);
        return "information/showMedicalAppointments";
    }

    /***
     * 跳转预约进度
     */
    @RequestMapping("appointmentProgress")
    public String appointmentProgress(Long id,Model model){
        if (id == null){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(id);
        List<TAppointmentSchedule> tAppointmentSchedules = patientOrderService.selectByConsumerId(id);
        Collections.reverse(tAppointmentSchedules);
        if (tConsumer != null && tAppointmentSchedules != null){
            model.addAttribute("tConsumer",tConsumer);
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules);
            return "information/appointmentProgress";
        }
        return "500";
    }

    /***
     * 取消预约
     */
    @RequestMapping("orderCancle")
    public String orderCancle(Long consumerId,Long id,Model model){
        if (id == null || consumerId == null){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(consumerId);
        TAppointmentSchedule tAppointmentSchedule = patientOrderService.selectByPrimaryKey(id);
        tAppointmentSchedule.setConsumerId(null);
        tAppointmentSchedule.setId(id);
        tAppointmentSchedule.setStatus(1);
        int i = patientOrderService.updateByPrimaryKey(tAppointmentSchedule);
        if(i == 1){
            List<TAppointmentSchedule> tAppointmentSchedules = patientOrderService.selectByConsumerId(consumerId);
            Collections.reverse(tAppointmentSchedules);
            if (tConsumer != null && tAppointmentSchedules != null){
                model.addAttribute("tConsumer",tConsumer);
                model.addAttribute("tAppointmentSchedules",tAppointmentSchedules);
                return "information/appointmentProgress";
            }
        }

        return "500";
    }

    public static void main(String[] args) {

        System.out.println();


    }

}
