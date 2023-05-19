package com.jia.front.controller;


import com.alibaba.fastjson.JSON;
import com.jia.entity.*;
import com.jia.front.client.*;
import com.jia.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RequestMapping("/consumerInformation")
@Controller
public class ConsumerController {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);


    @Resource
    IConsumerClient consumerService;

    @Resource
    IPatientOrderClient patientOrderService;

    @Resource
    IPatientClient patientSerive;

    @Resource
    IDoctorClient doctorService;

    @Resource
    IDoctorCommentClient doctorCommentService;

    @Resource
    ICalendarNotebookClient calendarNotebookService;

    @Resource
    IDoctorCollectionClient doctorCollectionService;

    @Resource
    IPrescriptionClient prescriptionService;


    /**上传地址*/
    @Value("${file.upload.path}")
    private String filePath;

    private static String PCODE = null;

    private static String ECODE = null;

    @RequestMapping("index")
    public String index(String id, Model model){
        if (id == null || "".equals(id)){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        model.addAttribute("tConsumer",tConsumer);
        return "information/index";
    }

    @RequestMapping("information")
    public String information(String id,Model model){
        if (id == null || "".equals(id)){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        model.addAttribute("tConsumer",tConsumer);
        return "information/information";
    }

    @RequestMapping("updateInformation")
    public String updateInformation(String id,Model model){
        if (id == null || "".equals(id)){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        model.addAttribute("tConsumer",tConsumer);
        return "information/updateInformation";
    }

    @RequestMapping("checkPhone")
    @ResponseBody
    public String checkPhone(String phone){
        if(phone.equals("")){
            return "error";
        }
        TConsumer tConsumer = consumerService.selectByPhone(Long.parseLong(phone));
        if(tConsumer == null){
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping("sendCode")
    @ResponseBody
    public String sendCode(String phone,String email){
        if(phone.equals("") || email.equals("")){
            return "error";
        }
        Map<String, Object> stringObjectMap = ZhenZiUtil.sendSms(phone);
        PCODE = String.valueOf(stringObjectMap.get("verifyCode"));

        try {
            ECODE = MailUtils.sendMail(email);
        } catch (MessagingException e) {
            logger.error("MailUtils error",e);
            return "error";
        }

        return "success";

    }

    @RequestMapping("checkPhoneCode")
    @ResponseBody
    public String checkPhoneCode(String phoneCode){
        if("".equals(phoneCode)){
            return "error";
        }
        if(phoneCode.equals(PCODE)){
            return "success";
        }
        return "error";
    }

    @RequestMapping("checkEmailCode")
    @ResponseBody
    public String checkEmailCode(String emailCode){
        if("".equals(emailCode)){
            return "error";
        }
        if(emailCode.equals(ECODE)){
            return "success";
        }
        return "error";
    }

    @RequestMapping("checkWchat")
    @ResponseBody
    public String checkWchat(String wchat){
        if("".equals(wchat)){
            return "error";
        }
        TConsumer tConsumer = consumerService.selectByWchat(wchat);
        if(tConsumer == null){
            return "success";
        }
        return "error";
    }

    @RequestMapping("checkEmail")
    @ResponseBody
    public String checkEmail(String email){
        if(email.equals("")){
            return "error";
        }
        TConsumer tConsumer = consumerService.selectByEmail(email);
        if(tConsumer == null){
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping("updateConsumerInformation")
    @ResponseBody
    public String updateInformation(TConsumer consumer){
        if (consumer == null){
            return "error";
        }
        Long id = consumer.getId();
        TConsumer tConsumer = consumerService.selectByPrimaryKey(consumer.getId());
        consumer.setId(id);
        consumer.setAge(tConsumer.getAge());
        consumer.setSex(tConsumer.getSex());
        consumer.setIdCard(tConsumer.getIdCard());
        consumer.setImg(tConsumer.getImg());
        consumer.setGmtCreate(tConsumer.getGmtCreate());
        consumer.setGmtModified(DateUtil.getDaDate());
        consumer.setUsername(tConsumer.getUsername());
        consumer.setPassword(tConsumer.getPassword());
        int i = consumerService.updateByPrimaryKey(consumer);
        if(i == 1){
            return "success:" + id;
        }
        return "error";
    }

    @RequestMapping("updatePassword")
    public String updatePassword(String id,Model model){
        if(id.equals("")){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        if(tConsumer != null){
            model.addAttribute("tConsumer",tConsumer);
            return "information/updatePassword";
        }
        return "500";
    }

    @RequestMapping("checkPassword")
    @ResponseBody
    public String checkPassword(String id,String password){
        if(id.equals("") || password.equals("")){
            return "error";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        password = DESCrypto.encode(password);
        if(password.equals(tConsumer.getPassword())){
            return "success";
        }
        return "error";
    }

    @RequestMapping("updateConsumerPassword")
    @ResponseBody
    public String updateConsumerPassword(String id,String password){
        if(id.equals("") || password.equals("")){
            return "error";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        tConsumer.setId(Long.parseLong(id));
        password = DESCrypto.encode(password);
        tConsumer.setPassword(password);
        tConsumer.setGmtModified(DateUtil.getDaDate());
        int i = consumerService.updateByPrimaryKey(tConsumer);
        if(i == 1){
            return "success";
        }

        return "error";
    }

    @RequestMapping("medical")
    public String medical(String id,Model model){
        if(id.equals("")){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        if(tConsumer == null){
            return "500";
        }
        List<TMedicalRecord> tMedicalRecords = patientSerive.selectTMedicalRecordByConsumerId(Long.parseLong(id));
        model.addAttribute("tMedicalRecords",tMedicalRecords);
        model.addAttribute("tConsumer",tConsumer);
        return "/information/medical";
    }

    @RequestMapping("existingMedical")
    public String existingMedical(String id,Model model){
        if(id.equals("")){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        if(tConsumer == null){
            return "500";
        }
        List<TExistingPatient> tExistingPatients = patientSerive.selectTExistingPatientByConsumerId(Long.parseLong(id));
        model.addAttribute("tExistingPatients",tExistingPatients);
        model.addAttribute("tConsumer",tConsumer);
        return "/information/existingMedical";
    }

    @RequestMapping("calendar")
    public String calendar(String id,Model model){
        if(id.equals("")){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        List<TCalendarNotebook> tCalendarNotebooks = calendarNotebookService.selectByConsumerId(Long.parseLong(id));
        List<Map<String,Object>> list = new ArrayList<>();
        for (TCalendarNotebook tCalendarNotebook : tCalendarNotebooks) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", tCalendarNotebook.getId());
            map.put("title", tCalendarNotebook.getTitle());
            map.put("start", tCalendarNotebook.getStart().getTime());
            map.put("end", tCalendarNotebook.getEnd().getTime());
            map.put("className", tCalendarNotebook.getClassname());
            list.add(map);
        }
        String str = JSON.toJSONString(list);
        model.addAttribute("tConsumer",tConsumer);
        model.addAttribute("tCalendarNotebooks",str);
        return "/event/calendar";
    }

    @RequestMapping("updateCalendarTitle")
    @ResponseBody
    public String updateCalendarTitle(String title,Long id){
        if(title.equals("") || id == null){
            return "error";
        }
        TCalendarNotebook tCalendarNotebook = calendarNotebookService.selectByPrimaryKey(id);
        tCalendarNotebook.setTitle(title);
        tCalendarNotebook.setGmtModified(DateUtil.getDaDate());
        int i = calendarNotebookService.updateByPrimaryKey(tCalendarNotebook);
        if(i == 1){
            return "success";
        }
        return "error";
    }

    @RequestMapping("insertCalendar")
    @ResponseBody
    public String insertCalendar(TCalendarNotebook tCalendarNoteBook){
        if(tCalendarNoteBook == null){
            return "error";
        }
        tCalendarNoteBook.setGmtCreate(DateUtil.getDaDate());
        tCalendarNoteBook.setEnd(tCalendarNoteBook.getStart());
        int insert = calendarNotebookService.insert(tCalendarNoteBook);
        if(insert == 1){
            Long aLong = calendarNotebookService.selectLastInsertId();
            return String.valueOf(aLong);
        }
        return "error";
    }

    @RequestMapping("deleteCalendar")
    @ResponseBody
    public String deleteCalendar(Long id){
        if(id == null){
            return "error";
        }
        int i = calendarNotebookService.deleteByPrimaryKey(id);
        if(i == 1){
            return "success";
        }
        return "error";
    }


    // 执行上传
    @RequestMapping("upload")
    public String upload(@RequestParam("title") MultipartFile title, String id, Model model) {
        if(id == null){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        // 获取上传文件名
        String filename = title.getOriginalFilename();
        assert filename != null;
        String[] split = filename.split("\\.");
        split[split.length - 2] = split[split.length - 2] + "-" + System.currentTimeMillis() + ".";
        filename = StringUtil.stringArrayToString(split);
        // 定义上传文件保存路径
        String path = filePath+"consumerPhoto/" + tConsumer.getId() + "/";
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            title.transferTo(new File(path + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        tConsumer.setImg("/images/consumerPhoto/" + tConsumer.getId() + "/" +filename);
        int i = consumerService.updateByPrimaryKey(tConsumer);
        if(i==1){
            // 将src路径发送至html页面
            model.addAttribute("tConsumer", tConsumer);
            return "information/updateInformation";
        }
        return "500";
    }

    @RequestMapping("consumerOrderList")
    public String consumerOrderList(String id,Model model){
        if(id.equals("")){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        List<TAppointmentSchedule> tAppointmentSchedules = patientOrderService.selectByConsumerId(Long.parseLong(id));
        model.addAttribute("tConsumer",tConsumer);
        model.addAttribute("tAppointmentSchedules",tAppointmentSchedules);
        return "information/consumerOrderList";
    }

    @RequestMapping("searchDoctor")
    public String searchDoctor(String id,Model model){
        if(id.equals("")){
            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        List<TDoctor> tDoctors = doctorService.selectAll();
        for (TDoctor doctor : tDoctors) {
            int commentSum = doctorCommentService.selectCountByDoctorId(doctor.getId());
            if (doctor.getLabel().split("、").length != 0){
                List<String> split = Arrays.asList(doctor.getLabel().split("、"));
                doctor.setLabelList(split);
            }
            String paise = (double)(Math.round(doctor.getEvaluate() * 10)) + "%";
            doctor.setPraise(paise);
            doctor.setCommentSum(commentSum);
        }
        model.addAttribute("tConsumer",tConsumer);
        model.addAttribute("tDoctors",tDoctors);
        return "information/searchDoctor";

    }

    @RequestMapping("doctorCollection")
    public String doctorCollection(Long id,Model model){
        if(id == null){
            return "500";
        }
        List<TDoctorCollection> tDoctorCollections = doctorCollectionService.selectByConsumerId(id);
        TConsumer tConsumer = consumerService.selectByPrimaryKey(id);
        model.addAttribute("tDoctorCollections",tDoctorCollections);
        model.addAttribute("tConsumer",tConsumer);
        return "information/doctorCollection";
    }

    @RequestMapping("prescriptionList")
    public String prescriptionList(Long id,Model model){
        if(id == null){
            return "500";
        }
        List<TPrescription> tPrescriptions = prescriptionService.selectByConsumerId(id);
        TConsumer tConsumer = consumerService.selectByPrimaryKey(id);
        model.addAttribute("tPrescriptions",tPrescriptions);
        model.addAttribute("tConsumer",tConsumer);
        return "information/prescriptionList";
    }

    //    Integer consumerSum = consumerService.selectCount();

}
