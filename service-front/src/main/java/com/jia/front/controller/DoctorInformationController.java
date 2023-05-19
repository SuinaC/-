package com.jia.front.controller;


import com.jia.entity.*;
import com.jia.entity.vo.ChatVo;
import com.jia.entity.vo.ConsumerCommentVo;
import com.jia.entity.vo.DateDoctorVo;
import com.jia.front.client.*;
import com.jia.utils.DateUtil;
import com.jia.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RequestMapping("doctorInformation")
@Controller
public class DoctorInformationController {
    private static final Logger logger = LoggerFactory.getLogger(DoctorInformationController.class);

    @Resource
    IConsumerClient consumerService;

    @Resource
    IPatientOrderClient patientOrderService;

    @Resource
    IDoctorClient doctorService;

    @Resource
    IDoctorResumeClient doctorResumeService;

    @Resource
    IDoctorCommentClient doctorCommentService;

    @Resource
    IDoctorCollectionClient doctorCollectionService;


    @RequestMapping("doctorProfile")
    public String doctorProfile(String id,String consumerId, Model model){
        if(id == "" || id == null || consumerId == "" || consumerId == null){
            return "500";
        }

        Date date = new Date();
        int day = date.getDay();
        TDoctor tDoctor = doctorService.selectByPrimaryKey(Long.parseLong(id));
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(consumerId));
        String paise = (double)(Math.round(tDoctor.getEvaluate()*10)/1.0) + "%";
        tDoctor.setPraise(paise);
        return getLitingHours(day,tDoctor,tConsumer,model);
    }

    private String getTime(List<TAppointmentSchedule> tAppointmentSchedule){
        String time = null;
        if(tAppointmentSchedule.size() == 0){
            time = null;
        }else {
            String min = null,max = null;
            int j = 0;
            min = tAppointmentSchedule.get(0).getStartTime();
            max = tAppointmentSchedule.get(0).getEndTime();
            for (int i = 0; i < tAppointmentSchedule.size() -1; i++) {
                j = i + 1;
                if(j > tAppointmentSchedule.size() - 1){
                    break;
                }
                if((min).compareTo(tAppointmentSchedule.get(j).getStartTime()) > 0){
                    min = tAppointmentSchedule.get(j).getStartTime();
                }

                if((max).compareTo(tAppointmentSchedule.get(j).getEndTime()) < 0){
                    max = tAppointmentSchedule.get(j).getEndTime();
                }
            }
            if(min.compareTo("12:00") > 0){
                time = "下午 " + min;
            }else {
                time = "上午 " + min;
            }

            if(max.compareTo("12:00") > 0){
                time = time + "    " + "下午 " + max;
            }else {
                time = time + "    " + "上午 " + max;
            }

        }
        return time;
    }


    private String getLitingHours(Integer day,TDoctor tDoctor,TConsumer tConsumer,Model model){
        Date date = new Date();
        List<TDoctorResume> tDoctorResumes = doctorResumeService.selectByDoctorId(tDoctor.getId());
        List<TDoctorComment> tDoctorComments = doctorCommentService.selectByDoctorId(tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules7 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDate(),tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules1 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000)),tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules2 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 2)),tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules3 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 3)),tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules4 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 4)),tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules5 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 5)),tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules6 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 6)),tDoctor.getId());

        DateDoctorVo dateDoctorVo = new DateDoctorVo();
        dateDoctorVo.setDateId(DateUtil.getLongDate());
        dateDoctorVo.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules7 = patientOrderService.selectOrderByDateIdAndDoctorId(dateDoctorVo);
        DateDoctorVo d1=new DateDoctorVo();
        d1.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000)));
        d1.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules1 = patientOrderService.selectOrderByDateIdAndDoctorId(d1);
        DateDoctorVo d2=new DateDoctorVo();
        d2.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 2)));
        d2.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules2 = patientOrderService.selectOrderByDateIdAndDoctorId(d2);
        DateDoctorVo d3=new DateDoctorVo();
        d3.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 3)));
        d3.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules3 = patientOrderService.selectOrderByDateIdAndDoctorId(d3);
        DateDoctorVo d4=new DateDoctorVo();
        d4.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 4)));
        d4.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules4 = patientOrderService.selectOrderByDateIdAndDoctorId(d4);
        DateDoctorVo d5=new DateDoctorVo();
        d5.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 5)));
        d5.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules5 = patientOrderService.selectOrderByDateIdAndDoctorId(d5);
        DateDoctorVo d6=new DateDoctorVo();
        d6.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000* 6)));
        d6.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules6 = patientOrderService.selectOrderByDateIdAndDoctorId(d6);

        String time7 = getTime(tAppointmentSchedules7);
        String time6 = getTime(tAppointmentSchedules6);
        String time5 = getTime(tAppointmentSchedules5);
        String time4 = getTime(tAppointmentSchedules4);
        String time3 = getTime(tAppointmentSchedules3);
        String time2 = getTime(tAppointmentSchedules2);
        String time1 = getTime(tAppointmentSchedules1);

        if(day == 0){
            model.addAttribute("time",time7);
            model.addAttribute("time1",time1);
            model.addAttribute("time2",time2);
            model.addAttribute("time3",time3);
            model.addAttribute("time4",time4);
            model.addAttribute("time5",time5);
            model.addAttribute("time6",time6);
        }else if(day == 1){
            model.addAttribute("time",time6);
            model.addAttribute("time1",time7);
            model.addAttribute("time2",time1);
            model.addAttribute("time3",time2);
            model.addAttribute("time4",time3);
            model.addAttribute("time5",time4);
            model.addAttribute("time6",time5);
        }else if(day == 2){
            model.addAttribute("time",time5);
            model.addAttribute("time1",time6);
            model.addAttribute("time2",time7);
            model.addAttribute("time3",time1);
            model.addAttribute("time4",time2);
            model.addAttribute("time5",time3);
            model.addAttribute("time6",time4);
        }else if(day == 3){
            model.addAttribute("time",time4);
            model.addAttribute("time1",time5);
            model.addAttribute("time2",time6);
            model.addAttribute("time3",time7);
            model.addAttribute("time4",time1);
            model.addAttribute("time5",time2);
            model.addAttribute("time6",time3);
        }else if(day == 4){
            model.addAttribute("time",time3);
            model.addAttribute("time1",time4);
            model.addAttribute("time2",time5);
            model.addAttribute("time3",time6);
            model.addAttribute("time4",time7);
            model.addAttribute("time5",time1);
            model.addAttribute("time6",time2);
        }else if(day == 5){
            model.addAttribute("time",time2);
            model.addAttribute("time1",time3);
            model.addAttribute("time2",time4);
            model.addAttribute("time3",time5);
            model.addAttribute("time4",time6);
            model.addAttribute("time5",time7);
            model.addAttribute("time6",time1);
        }else if(day == 6){
            model.addAttribute("time",time1);
            model.addAttribute("time1",time2);
            model.addAttribute("time2",time3);
            model.addAttribute("time3",time4);
            model.addAttribute("time4",time5);
            model.addAttribute("time5",time6);
            model.addAttribute("time6",time7);
        }
        int commentSum = doctorCommentService.selectCountByDoctorId(tDoctor.getId());
        int i = 0;
        do {
            if(tDoctorComments == null || tDoctorComments.size() == 0){
                break;
            }
            Long id = tDoctorComments.get(i).getId();
            ConsumerCommentVo v1 = new ConsumerCommentVo();
            v1.setConsumerId(tConsumer.getId());
            v1.setCommentId(id);
            TCommentGreat tCommentGreats = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(v1);

            ConsumerCommentVo v2 = new ConsumerCommentVo();
            v2.setConsumerId(tConsumer.getId());
            v2.setCommentId(id);
            List<TCommentReply> tCommentReplies = doctorCommentService.selectTCommentReplyByConsumerIdAndCommentId(v2);
            int j = 0;
            do {
                if(tCommentReplies == null || tCommentReplies.size() == 0){

                }else {
                    ConsumerCommentVo v3 = new ConsumerCommentVo();
                    v3.setConsumerId(tConsumer.getId());
                    v3.setCommentId(tCommentReplies.get(j).getId());
                    TCommentGreat tCommentGreat = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(v3);
                    tCommentReplies.get(j).settCommentGreat(tCommentGreat);
                }
                j++;
            }while (j < tCommentReplies.size() - 1);
            tDoctorComments.get(i).settCommentReplies(tCommentReplies);
            tDoctorComments.get(i).settCommentGreat(tCommentGreats);
            i++;
        }while(i < tDoctorComments.size());
        ChatVo dc = new ChatVo();
        dc.setDoctorId(tDoctor.getId());
        dc.setConsumerId(tConsumer.getId());
        TDoctorCollection tDoctorCollection = doctorCollectionService.selectByConsumerIdAndDoctorId(dc);
        model.addAttribute("tDoctor",tDoctor);
        model.addAttribute("tConsumer",tConsumer);
        model.addAttribute("today",time7);
        model.addAttribute("todayTime",DateUtil.getStrDate());
        model.addAttribute("tDoctorResumes",tDoctorResumes);
        model.addAttribute("tDoctorComments",tDoctorComments);
        model.addAttribute("commentSum",commentSum);
        model.addAttribute("tDoctorCollection",tDoctorCollection);
        return "information/doctorProfile";
    }

    @RequestMapping("doctorOrder")
    public String doctorOrder(String id,String doctorId,Model model){
        if(id == "" || id == null || doctorId == "" || doctorId == null){

            return "500";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(id));
        TDoctor tDoctor = doctorService.selectByPrimaryKey(Long.parseLong(doctorId));
        Date date = new Date();
        int day = date.getDay();
//        List<TAppointmentSchedule> tAppointmentSchedules = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDate(),tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules1 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000)),tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules2 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 2)),tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules3 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 3)),tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules4 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 4)),tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules5 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 5)),tDoctor.getId());
//        List<TAppointmentSchedule> tAppointmentSchedules6 = patientOrderService.selectOrderByDateIdAndDoctorId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 6)),tDoctor.getId());
        DateDoctorVo dateDoctorVo = new DateDoctorVo();
        dateDoctorVo.setDateId(DateUtil.getLongDate());
        dateDoctorVo.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules = patientOrderService.selectOrderByDateIdAndDoctorId(dateDoctorVo);
        DateDoctorVo d1=new DateDoctorVo();
        d1.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000)));
        d1.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules1 = patientOrderService.selectOrderByDateIdAndDoctorId(d1);
        DateDoctorVo d2=new DateDoctorVo();
        d2.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 2)));
        d2.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules2 = patientOrderService.selectOrderByDateIdAndDoctorId(d2);
        DateDoctorVo d3=new DateDoctorVo();
        d3.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 3)));
        d3.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules3 = patientOrderService.selectOrderByDateIdAndDoctorId(d3);
        DateDoctorVo d4=new DateDoctorVo();
        d4.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 4)));
        d4.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules4 = patientOrderService.selectOrderByDateIdAndDoctorId(d4);
        DateDoctorVo d5=new DateDoctorVo();
        d5.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000 * 5)));
        d5.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules5 = patientOrderService.selectOrderByDateIdAndDoctorId(d5);
        DateDoctorVo d6=new DateDoctorVo();
        d6.setDateId(DateUtil.getLongDateByInput(new Date(date.getTime() + 86400000* 6)));
        d6.setDoctorId(tDoctor.getId());
        List<TAppointmentSchedule> tAppointmentSchedules6 = patientOrderService.selectOrderByDateIdAndDoctorId(d6);

        if(day == 0){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules1);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules2);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules3);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules4);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules5);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules6);
        }else if(day == 1){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules6);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules1);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules2);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules3);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules4);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules5);
        }else if(day == 2){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules5);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules6);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules1);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules2);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules3);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules4);
        }else if(day == 3){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules4);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules5);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules6);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules1);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules2);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules3);
        }else if(day == 4){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules3);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules4);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules5);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules6);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules1);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules2);
        }else if(day == 5){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules2);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules3);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules4);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules5);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules6);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules1);
        }else if(day == 6){
            model.addAttribute("tAppointmentSchedules",tAppointmentSchedules1);
            model.addAttribute("tAppointmentSchedules1",tAppointmentSchedules2);
            model.addAttribute("tAppointmentSchedules2",tAppointmentSchedules3);
            model.addAttribute("tAppointmentSchedules3",tAppointmentSchedules4);
            model.addAttribute("tAppointmentSchedules4",tAppointmentSchedules5);
            model.addAttribute("tAppointmentSchedules5",tAppointmentSchedules6);
            model.addAttribute("tAppointmentSchedules6",tAppointmentSchedules);
        }
        model.addAttribute("tConsumer",tConsumer);
        model.addAttribute("tDoctor",tDoctor);
        return "information/doctorOrder";

    }

    @RequestMapping("addComment")
    public String addComment(String consumerId,String doctorId,String commentContent,Model model){
        if(consumerId == "" || doctorId == "" || consumerId == null || doctorId == null){
            return "error";
        }
        TDoctorComment tDoctorComment = new TDoctorComment();
        tDoctorComment.setConsumerId(Long.parseLong(consumerId));
        tDoctorComment.setCommentContent(commentContent);
        tDoctorComment.setDoctorId(Long.parseLong(doctorId));
        tDoctorComment.setGmtCreate(DateUtil.getDaDate());
        tDoctorComment.setFabulous(0);
        tDoctorComment.setStepOn(0);
        TDoctor tDoctor = doctorService.selectByPrimaryKey(Long.parseLong(doctorId));
        int insert = doctorCommentService.insert(tDoctorComment);
        if(insert == 1){
            List<TDoctorComment> tDoctorComments = doctorCommentService.selectByDoctorId(Long.parseLong(doctorId));
            for (int i = 0; i < tDoctorComments.size(); i++) {
                if(tDoctorComments == null || tDoctorComments.size() == 0){
                    break;
                }
                Long id = tDoctorComments.get(i).getId();

                ConsumerCommentVo g1 = new ConsumerCommentVo();
                g1.setConsumerId(Long.parseLong(consumerId));
                g1.setCommentId(id);
                TCommentGreat tCommentGreats = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g1);

                List<TCommentReply> tCommentReplies = doctorCommentService.selectTCommentReplyByConsumerIdAndCommentId(g1);
                tDoctorComments.get(i).settCommentReplies(tCommentReplies);
                tDoctorComments.get(i).settCommentGreat(tCommentGreats);
            }
            TConsumer tConsumer = consumerService.selectByPrimaryKey(Long.parseLong(consumerId));
            model.addAttribute("tDoctorComments",tDoctorComments);
            model.addAttribute("tConsumer",tConsumer);
            model.addAttribute("tDoctor",tDoctor);
            return "information/doctorProfile::comment_refresh";
        }
        return "error";
    }

    @RequestMapping("giveTheThumbsUp")
    public String giveTheThumbsUp(Long id,Long consumerId,String doctorId,Model model){
        if(id == null || consumerId == null ){
            return "error";
        }
        TCommentGreat tCommentGreat = new TCommentGreat();
        tCommentGreat.setFabulous(true);
        tCommentGreat.setStep(false);
        tCommentGreat.setConsumerId(consumerId);
        tCommentGreat.setCommentId(id);
        tCommentGreat.setGmtCreate(DateUtil.getDaDate());
        int i1 = doctorCommentService.insertCommentGreat(tCommentGreat);
        TDoctorComment tDoctorComment = doctorCommentService.selectCommentByPrimaryKey(id);
        tDoctorComment.setFabulous((tDoctorComment.getFabulous() + 1 ));
        tDoctorComment.setGmtModified(DateUtil.getDaDate());
        int in = doctorCommentService.updateCommentByPrimaryKey(tDoctorComment);
        int commentSum = doctorCommentService.selectCountByDoctorId(Long.parseLong(doctorId));
        if(in == 1){
            List<TDoctorComment> tDoctorComments = doctorCommentService.selectByDoctorId(Long.parseLong(doctorId));
            int i = 0;
            do{
                if(tDoctorComments == null || tDoctorComments.size() == 0){
                    break;
                }
                Long ids = tDoctorComments.get(i).getId();
                ConsumerCommentVo g2 = new ConsumerCommentVo();
                g2.setConsumerId(consumerId);
                g2.setCommentId(ids);
                TCommentGreat tCommentGreats = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g2);
                List<TCommentReply> tCommentReplies = doctorCommentService.selectTCommentReplyByConsumerIdAndCommentId(g2);
                int j = 0;
                do {
                    if (tCommentReplies == null || tCommentReplies.size() == 0){

                    }else {
                        ConsumerCommentVo g3 = new ConsumerCommentVo();
                        g3.setConsumerId(consumerId);
                        g3.setCommentId(tCommentReplies.get(j).getId());
                        TCommentGreat commentGreat = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g3);
                        tCommentReplies.get(j).settCommentGreat(commentGreat);
                    }
                    j++;
                }while (j < tCommentReplies.size() );
                tDoctorComments.get(i).settCommentReplies(tCommentReplies);
                tDoctorComments.get(i).settCommentGreat(tCommentGreats);
                i++;
            }while(i < tDoctorComments.size() );
            TConsumer tConsumer = consumerService.selectByPrimaryKey(consumerId);
            model.addAttribute("tDoctorComments",tDoctorComments);
            model.addAttribute("tConsumer",tConsumer);
            model.addAttribute("commentSum",commentSum);
            return "information/doctorProfile::comment_refresh";
        }
        return "error";
    }

    @RequestMapping("giveNoTheThumbsUp")
    public String giveNoTheThumbsUp(Long id,Long consumerId,Long doctorId,Long greatId,Model model){
        if(id == null || consumerId == null || greatId == null ){
            return "error";
        }
        return giveThumbsUp(id,consumerId,doctorId,greatId,-1,model);
    }

    private String giveThumbsUp(Long id,Long consumerId,Long doctorId,Long greatId,int num,Model model){
        TDoctorComment tDoctorComment = doctorCommentService.selectCommentByPrimaryKey(id);
        TCommentGreat tCommentGreat = doctorCommentService.selectGreatByPrimaryKey(greatId);
        if(tCommentGreat.getStep() == true){
            tDoctorComment.setStepOn(tDoctorComment.getStepOn() - 1);
        }
        if(num == 1){
            tCommentGreat.setFabulous(true);
        }else if(num == -1){
            tCommentGreat.setFabulous(false);
        }
        tCommentGreat.setStep(false);
        tCommentGreat.setConsumerId(consumerId);
        tCommentGreat.setCommentId(id);
        tCommentGreat.setGmtModified(DateUtil.getDaDate());
        int i1 = doctorCommentService.updateGreatByPrimaryKey(tCommentGreat);
        tDoctorComment.setFabulous((tDoctorComment.getFabulous() + num ));
        tDoctorComment.setGmtModified(DateUtil.getDaDate());
        int in = doctorCommentService.updateCommentByPrimaryKey(tDoctorComment);
        int commentSum = doctorCommentService.selectCountByDoctorId(doctorId);
        if(in == 1){
            List<TDoctorComment> tDoctorComments = doctorCommentService.selectByDoctorId(doctorId);
            int i = 0;
            do  {
                if(tDoctorComments == null || tDoctorComments.size() == 0){
                    break;
                }
                Long ids = tDoctorComments.get(i).getId();
                ConsumerCommentVo g4 = new ConsumerCommentVo();
                g4.setConsumerId(consumerId);
                g4.setCommentId(ids);
                TCommentGreat tCommentGreats = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g4);
                List<TCommentReply> tCommentReplies = doctorCommentService.selectTCommentReplyByConsumerIdAndCommentId(g4);
                int j = 0;
                do {
                    if (tCommentReplies == null || tCommentReplies.size() == 0 ){

                    }else {
                        ConsumerCommentVo g5 = new ConsumerCommentVo();
                        g5.setConsumerId(consumerId);
                        g5.setCommentId(tCommentReplies.get(j).getId());
                        TCommentGreat commentGreat = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g5);
                        tCommentReplies.get(j).settCommentGreat(commentGreat);
                    }
                    j++;
                }while (j < tCommentReplies.size()  );
                tDoctorComments.get(i).settCommentReplies(tCommentReplies);
                tDoctorComments.get(i).settCommentGreat(tCommentGreats);
                i++;
            }while(i < tDoctorComments.size() );
            TConsumer tConsumer = consumerService.selectByPrimaryKey(consumerId);
            model.addAttribute("tDoctorComments",tDoctorComments);
            model.addAttribute("tConsumer",tConsumer);
            model.addAttribute("commentSum",commentSum);
            return "information/doctorProfile::comment_refresh";
        }
        return "error";
    }

    @RequestMapping("giveNotTheThumbsUp")
    public String giveNotTheThumbsUp(Long id,Long consumerId,Long doctorId,Long greatId,Model model){
        if(id == null || consumerId == null || greatId == null ){
            return "error";
        }
        return giveThumbsUp(id,consumerId,doctorId,greatId,1,model);
    }

    @RequestMapping("stepOn")
    public String stepOn(Long id,Long consumerId,Long doctorId,Model model){
        if(id == null || consumerId == null ){
            return "error";
        }
        TCommentGreat tCommentGreat = new TCommentGreat();
        tCommentGreat.setFabulous(false);
        tCommentGreat.setStep(true);
        tCommentGreat.setConsumerId(consumerId);
        tCommentGreat.setCommentId(id);
        tCommentGreat.setGmtCreate(DateUtil.getDaDate());
        int i1 = doctorCommentService.insertCommentGreat(tCommentGreat);
        TDoctorComment tDoctorComment = doctorCommentService.selectCommentByPrimaryKey(id);
        tDoctorComment.setStepOn((tDoctorComment.getStepOn() + 1 ));
        tDoctorComment.setGmtModified(DateUtil.getDaDate());
        int in = doctorCommentService.updateCommentByPrimaryKey(tDoctorComment);
        int commentSum = doctorCommentService.selectCountByDoctorId(doctorId);
        if(in == 1){
            List<TDoctorComment> tDoctorComments = doctorCommentService.selectByDoctorId(doctorId);
            int i = 0;
            do{
                if(tDoctorComments == null || tDoctorComments.size() == 0){
                    break;
                }
                Long ids = tDoctorComments.get(i).getId();
                ConsumerCommentVo g6 = new ConsumerCommentVo();
                g6.setConsumerId(consumerId);
                g6.setCommentId(ids);
                TCommentGreat tCommentGreats = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g6);
                List<TCommentReply> tCommentReplies = doctorCommentService.selectTCommentReplyByConsumerIdAndCommentId(g6);
                int j = 0;
                do {

                    if (tCommentReplies == null || tCommentReplies.size() == 0){

                    }else {
                        ConsumerCommentVo g7 = new ConsumerCommentVo();
                        g7.setConsumerId(consumerId);
                        g7.setCommentId(tCommentReplies.get(j).getId());
                        TCommentGreat commentGreat = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g7);
                        tCommentReplies.get(j).settCommentGreat(commentGreat);
                    }
                    j++;
                }while (j < tCommentReplies.size() );
                tDoctorComments.get(i).settCommentReplies(tCommentReplies);
                tDoctorComments.get(i).settCommentGreat(tCommentGreats);
                i++;
            }while(i < tDoctorComments.size());
            TConsumer tConsumer = consumerService.selectByPrimaryKey(consumerId);
            model.addAttribute("tDoctorComments",tDoctorComments);
            model.addAttribute("tConsumer",tConsumer);
            model.addAttribute("commentSum",commentSum);
            return "information/doctorProfile::comment_refresh";
        }
        return "error";
    }

    @RequestMapping("stepNoOn")
    public String stepNoOn(Long id,Long consumerId,Long doctorId,Long greatId,Model model){
        if(id == null || consumerId == null || greatId == null ){
            return "error";
        }
        return stepOnCommon(id,consumerId,doctorId,greatId,-1,model);
    }

    private String stepOnCommon(Long id,Long consumerId,Long doctorId,Long greatId,int num,Model model){
        TDoctorComment tDoctorComment = doctorCommentService.selectCommentByPrimaryKey(id);
        TCommentGreat tCommentGreat = doctorCommentService.selectGreatByPrimaryKey(greatId);
        if(tCommentGreat.getFabulous() == true){
            tDoctorComment.setFabulous((tDoctorComment.getFabulous() - 1  ));
        }
        if(num == 1){
            tCommentGreat.setStep(true);
        }else if(num == -1){
            tCommentGreat.setStep(false);
        }
        tCommentGreat.setFabulous(false);
        tCommentGreat.setConsumerId(consumerId);
        tCommentGreat.setCommentId(id);
        tCommentGreat.setGmtModified(DateUtil.getDaDate());
        int i1 = doctorCommentService.updateGreatByPrimaryKey(tCommentGreat);
        tDoctorComment.setStepOn((tDoctorComment.getStepOn() + num ));
        tDoctorComment.setGmtModified(DateUtil.getDaDate());
        int in = doctorCommentService.updateCommentByPrimaryKey(tDoctorComment);
        int commentSum = doctorCommentService.selectCountByDoctorId(doctorId);
        if(in == 1){
            List<TDoctorComment> tDoctorComments = doctorCommentService.selectByDoctorId(doctorId);
            int i = 0;
            do  {
                if(tDoctorComments == null || tDoctorComments.size() == 0){
                    break;
                }
                Long ids = tDoctorComments.get(i).getId();
                ConsumerCommentVo g8 = new ConsumerCommentVo();
                g8.setConsumerId(consumerId);
                g8.setCommentId(ids);
                TCommentGreat tCommentGreats = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g8);
                List<TCommentReply> tCommentReplies = doctorCommentService.selectTCommentReplyByConsumerIdAndCommentId(g8);
                int j = 0;
                do {
                    if (tCommentReplies == null || tCommentReplies.size() == 0){

                    }else {
                        ConsumerCommentVo g9 = new ConsumerCommentVo();
                        g9.setConsumerId(consumerId);
                        g9.setCommentId(tCommentReplies.get(j).getId());
                        TCommentGreat commentGreat = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g9);
                        tCommentReplies.get(j).settCommentGreat(commentGreat);
                    }
                    j++;
                }while (j < tCommentReplies.size());
                tDoctorComments.get(i).settCommentReplies(tCommentReplies);
                tDoctorComments.get(i).settCommentGreat(tCommentGreats);
                i++;
            }while(i < tDoctorComments.size());
            TConsumer tConsumer = consumerService.selectByPrimaryKey(consumerId);
            model.addAttribute("tDoctorComments",tDoctorComments);
            model.addAttribute("tConsumer",tConsumer);
            model.addAttribute("commentSum",commentSum);
            return "information/doctorProfile::comment_refresh";
        }
        return "error";
    }

    @RequestMapping("stepNotOn")
    public String stepNotOn(Long id,Long consumerId,Long doctorId,Long greatId,Model model){
        if(id == null || consumerId == null || greatId == null ){
            return "error";
        }
        return stepOnCommon(id,consumerId,doctorId,greatId,1,model);
    }


    @RequestMapping("reply")
    public String reply(Long commentId,String reply,Long consumerId,Long doctorId,Model model){
        if (commentId == null ||reply == null || consumerId == null){
            return "error";
        }
        String[] split = reply.split(":");
        split[0] = "";
        String s = StringUtil.stringArrayToString(split);
        TCommentReply tCommentReply = new TCommentReply();
        tCommentReply.setConsumerId(consumerId);
        tCommentReply.setCommentId(commentId);
        tCommentReply.setReplyContent(s);
        tCommentReply.setFabulous(0);
        tCommentReply.setStepOn(0);
        tCommentReply.setGmtCreate(DateUtil.getDaDate());
        int in = doctorCommentService.insertTCommentReply(tCommentReply);
        int commentSum = doctorCommentService.selectCountByDoctorId(doctorId);
        if(in == 1){
            List<TDoctorComment> tDoctorComments = doctorCommentService.selectByDoctorId(doctorId);
            int i = 0;
            do  {
                if(tDoctorComments == null || tDoctorComments.size() == 0){
                    break;
                }
                Long ids = tDoctorComments.get(i).getId();
                ConsumerCommentVo g10 = new ConsumerCommentVo();
                g10.setConsumerId(consumerId);
                g10.setCommentId(ids);
                TCommentGreat tCommentGreats = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g10);
                List<TCommentReply> tCommentReplies = doctorCommentService.selectTCommentReplyByConsumerIdAndCommentId(g10);
                int j = 0;
                do {
                    if (tCommentReplies == null || tCommentReplies.size() == 0){

                    }else {
                        ConsumerCommentVo g11 = new ConsumerCommentVo();
                        g11.setConsumerId(consumerId);
                        g11.setCommentId(tCommentReplies.get(j).getId());
                        TCommentGreat commentGreat = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g11);
                        tCommentReplies.get(j).settCommentGreat(commentGreat);
                    }
                    j++;
                }while (j < tCommentReplies.size());
                tDoctorComments.get(i).settCommentReplies(tCommentReplies);
                tDoctorComments.get(i).settCommentGreat(tCommentGreats);
                i++;
            }while(i < tDoctorComments.size());
            TConsumer tConsumer = consumerService.selectByPrimaryKey(consumerId);
            model.addAttribute("tDoctorComments",tDoctorComments);
            model.addAttribute("tConsumer",tConsumer);
            model.addAttribute("commentSum",commentSum);
            return "information/doctorProfile::comment_refresh";
        }
        return "error";


    }

    @RequestMapping("giveReplyTheThumbsUp")
    public String giveReplyTheThumbsUp(Long id,Long consumerId,String doctorId,Model model){
        if(id == null || consumerId == null ){
            return "error";
        }
        TCommentGreat tCommentGreat = new TCommentGreat();
        tCommentGreat.setFabulous(true);
        tCommentGreat.setStep(false);
        tCommentGreat.setConsumerId(consumerId);
        tCommentGreat.setCommentId(id);
        tCommentGreat.setGmtCreate(DateUtil.getDaDate());
        int i1 = doctorCommentService.insertCommentGreat(tCommentGreat);
        TCommentReply tCommentReply = doctorCommentService.selectTCommentReplyByPrimaryKey(id);
        tCommentReply.setFabulous((tCommentReply.getFabulous() + 1 ));
        tCommentReply.setGmtModified(DateUtil.getDaDate());
        int in = doctorCommentService.updateTCommentReplyByPrimaryKey(tCommentReply);
        int commentSum = doctorCommentService.selectCountByDoctorId(Long.parseLong(doctorId));
        if(in == 1){
            List<TDoctorComment> tDoctorComments = doctorCommentService.selectByDoctorId(Long.parseLong(doctorId));
            int i = 0;
            do{
                if(tDoctorComments == null || tDoctorComments.size() == 0){
                    break;
                }
                Long ids = tDoctorComments.get(i).getId();
                ConsumerCommentVo g12 = new ConsumerCommentVo();
                g12.setConsumerId(consumerId);
                g12.setCommentId(ids);
                TCommentGreat tCommentGreats = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g12);
                List<TCommentReply> tCommentReplies = doctorCommentService.selectTCommentReplyByConsumerIdAndCommentId(g12);
                int j = 0;
                do {
                    if (tCommentReplies == null || tCommentReplies.size() == 0){

                    }else {
                        ConsumerCommentVo g13 = new ConsumerCommentVo();
                        g13.setConsumerId(consumerId);
                        g13.setCommentId(tCommentReplies.get(j).getId());
                        TCommentGreat commentGreat = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g13);
                        tCommentReplies.get(j).settCommentGreat(commentGreat);
                    }
                    j++;
                }while (j < tCommentReplies.size());
                tDoctorComments.get(i).settCommentReplies(tCommentReplies);
                tDoctorComments.get(i).settCommentGreat(tCommentGreats);
                i++;
            }while(i < tDoctorComments.size());
            TConsumer tConsumer = consumerService.selectByPrimaryKey(consumerId);
            model.addAttribute("tDoctorComments",tDoctorComments);
            model.addAttribute("tConsumer",tConsumer);
            model.addAttribute("commentSum",commentSum);
            return "information/doctorProfile::comment_refresh";
        }
        return "error";
    }

    @RequestMapping("giveReplyNoTheThumbsUp")
    public String giveReplyNoTheThumbsUp(Long id,Long consumerId,Long doctorId,Long greatId,Model model){
        if(id == null || consumerId == null || greatId == null ){
            return "error";
        }
        return giveReplyThumbsUp(id,consumerId,doctorId,greatId,-1,model);
    }

    private String giveReplyThumbsUp(Long id,Long consumerId,Long doctorId,Long greatId,int num,Model model){
        TCommentReply tCommentReply = doctorCommentService.selectTCommentReplyByPrimaryKey(id);
        TCommentGreat tCommentGreat = doctorCommentService.selectGreatByPrimaryKey(greatId);
        if(tCommentGreat.getStep() == true){
            tCommentReply.setStepOn(tCommentReply.getStepOn() - 1);
        }
        if(num == 1){
            tCommentGreat.setFabulous(true);
        }else if(num == -1){
            tCommentGreat.setFabulous(false);
        }
        tCommentGreat.setStep(false);
        tCommentGreat.setConsumerId(consumerId);
        tCommentGreat.setCommentId(id);
        tCommentGreat.setGmtModified(DateUtil.getDaDate());
        int i1 = doctorCommentService.updateGreatByPrimaryKey(tCommentGreat);
        tCommentReply.setFabulous((tCommentReply.getFabulous() + 1 ));
        tCommentReply.setGmtModified(DateUtil.getDaDate());
        int in = doctorCommentService.updateTCommentReplyByPrimaryKey(tCommentReply);
        int commentSum = doctorCommentService.selectCountByDoctorId(doctorId);
        if(in == 1){
            List<TDoctorComment> tDoctorComments = doctorCommentService.selectByDoctorId(doctorId);
            int i = 0;
            do  {
                if(tDoctorComments == null || tDoctorComments.size() == 0){
                    break;
                }
                Long ids = tDoctorComments.get(i).getId();
                ConsumerCommentVo g14 = new ConsumerCommentVo();
                g14.setConsumerId(consumerId);
                g14.setCommentId(ids);
                TCommentGreat tCommentGreats = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g14);
                List<TCommentReply> tCommentReplies = doctorCommentService.selectTCommentReplyByConsumerIdAndCommentId(g14);
                int j = 0;
                do {
                    if (tCommentReplies == null || tCommentReplies.size() == 0){

                    }else {
                        ConsumerCommentVo g15 = new ConsumerCommentVo();
                        g15.setConsumerId(consumerId);
                        g15.setCommentId(tCommentReplies.get(j).getId());
                        TCommentGreat commentGreat = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g15);
                        tCommentReplies.get(j).settCommentGreat(commentGreat);
                    }
                    j++;
                }while (j < tCommentReplies.size() );
                tDoctorComments.get(i).settCommentReplies(tCommentReplies);
                tDoctorComments.get(i).settCommentGreat(tCommentGreats);
                i++;
            }while(i < tDoctorComments.size() );
            TConsumer tConsumer = consumerService.selectByPrimaryKey(consumerId);
            model.addAttribute("tDoctorComments",tDoctorComments);
            model.addAttribute("tConsumer",tConsumer);
            model.addAttribute("commentSum",commentSum);
            return "information/doctorProfile::comment_refresh";
        }
        return "error";
    }

    @RequestMapping("giveReplyNotTheThumbsUp")
    public String giveReplyNotTheThumbsUp(Long id,Long consumerId,Long doctorId,Long greatId,Model model){
        if(id == null || consumerId == null || greatId == null ){
            return "error";
        }
        return giveReplyThumbsUp(id,consumerId,doctorId,greatId,1,model);
    }

    @RequestMapping("stepReplyOn")
    public String stepReplyOn(Long id,Long consumerId,Long doctorId,Model model){
        if(id == null || consumerId == null ){
            return "error";
        }
        TCommentGreat tCommentGreat = new TCommentGreat();
        tCommentGreat.setFabulous(false);
        tCommentGreat.setStep(true);
        tCommentGreat.setConsumerId(consumerId);
        tCommentGreat.setCommentId(id);
        tCommentGreat.setGmtCreate(DateUtil.getDaDate());
        int i1 = doctorCommentService.insertCommentGreat(tCommentGreat);
        TCommentReply tCommentReply = doctorCommentService.selectTCommentReplyByPrimaryKey(id);
        tCommentReply.setStepOn((tCommentReply.getStepOn() + 1 ));
        tCommentReply.setGmtModified(DateUtil.getDaDate());
        int in = doctorCommentService.updateTCommentReplyByPrimaryKey(tCommentReply);
        int commentSum = doctorCommentService.selectCountByDoctorId(doctorId);
        if(in == 1){
            List<TDoctorComment> tDoctorComments = doctorCommentService.selectByDoctorId(doctorId);
            int i = 0;
            do{
                if(tDoctorComments == null || tDoctorComments.size() == 0){
                    break;
                }
                Long ids = tDoctorComments.get(i).getId();
                ConsumerCommentVo g16 = new ConsumerCommentVo();
                g16.setConsumerId(consumerId);
                g16.setCommentId(ids);
                TCommentGreat tCommentGreats = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g16);
                List<TCommentReply> tCommentReplies = doctorCommentService.selectTCommentReplyByConsumerIdAndCommentId(g16);
                int j = 0;
                do {
                    if (tCommentReplies == null || tCommentReplies.size() == 0){

                    }else {
                        ConsumerCommentVo g17 = new ConsumerCommentVo();
                        g17.setConsumerId(consumerId);
                        g17.setCommentId(tCommentReplies.get(j).getId());
                        TCommentGreat commentGreat = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g17);
                        tCommentReplies.get(j).settCommentGreat(commentGreat);
                    }
                    j++;
                }while (j < tCommentReplies.size());
                tDoctorComments.get(i).settCommentReplies(tCommentReplies);
                tDoctorComments.get(i).settCommentGreat(tCommentGreats);
                i++;
            }while(i < tDoctorComments.size());
            TConsumer tConsumer = consumerService.selectByPrimaryKey(consumerId);
            model.addAttribute("tDoctorComments",tDoctorComments);
            model.addAttribute("tConsumer",tConsumer);
            model.addAttribute("commentSum",commentSum);
            return "information/doctorProfile::comment_refresh";
        }
        return "error";
    }

    @RequestMapping("stepReplyNoOn")
    public String stepReplyNoOn(Long id,Long consumerId,Long doctorId,Long greatId,Model model){
        if(id == null || consumerId == null || greatId == null ){
            return "error";
        }
        return stepReplyOnCommon(id,consumerId,doctorId,greatId,-1,model);
    }

    private String stepReplyOnCommon(Long id,Long consumerId,Long doctorId,Long greatId,int num,Model model){
        TCommentReply tCommentReply = doctorCommentService.selectTCommentReplyByPrimaryKey(id);
        TCommentGreat tCommentGreat = doctorCommentService.selectGreatByPrimaryKey(greatId);
        if(tCommentGreat.getFabulous() == true){
            tCommentReply.setFabulous((tCommentReply.getFabulous() - 1  ));
        }
        if(num == 1){
            tCommentGreat.setStep(true);
        }else if(num == -1){
            tCommentGreat.setStep(false);
        }
        tCommentGreat.setFabulous(false);
        tCommentGreat.setConsumerId(consumerId);
        tCommentGreat.setCommentId(id);
        tCommentGreat.setGmtModified(DateUtil.getDaDate());
        int i1 = doctorCommentService.updateGreatByPrimaryKey(tCommentGreat);
        tCommentReply.setStepOn((tCommentReply.getStepOn() + num ));
        tCommentReply.setGmtModified(DateUtil.getDaDate());
        int in = doctorCommentService.updateTCommentReplyByPrimaryKey(tCommentReply);
        int commentSum = doctorCommentService.selectCountByDoctorId(doctorId);
        if(in == 1){
            List<TDoctorComment> tDoctorComments = doctorCommentService.selectByDoctorId(doctorId);
            int i = 0;
            do  {
                if(tDoctorComments == null || tDoctorComments.size() == 0){
                    break;
                }
                Long ids = tDoctorComments.get(i).getId();
                ConsumerCommentVo g19 = new ConsumerCommentVo();
                g19.setConsumerId(consumerId);
                g19.setCommentId(ids);
                TCommentGreat tCommentGreats = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g19);
                List<TCommentReply> tCommentReplies = doctorCommentService.selectTCommentReplyByConsumerIdAndCommentId(g19);
                int j = 0;
                do {
                    if (tCommentReplies == null || tCommentReplies.size() == 0){

                    }else {
                        ConsumerCommentVo g20 = new ConsumerCommentVo();
                        g20.setConsumerId(consumerId);
                        g20.setCommentId(tCommentReplies.get(j).getId());
                        TCommentGreat commentGreat = doctorCommentService.selectTCommentGreatByConsumerIdAndCommentId(g20);
                        tCommentReplies.get(j).settCommentGreat(commentGreat);
                    }
                    j++;
                }while (j < tCommentReplies.size());
                tDoctorComments.get(i).settCommentReplies(tCommentReplies);
                tDoctorComments.get(i).settCommentGreat(tCommentGreats);
                i++;
            }while(i < tDoctorComments.size());
            TConsumer tConsumer = consumerService.selectByPrimaryKey(consumerId);
            model.addAttribute("tDoctorComments",tDoctorComments);
            model.addAttribute("tConsumer",tConsumer);
            model.addAttribute("commentSum",commentSum);
            return "information/doctorProfile::comment_refresh";
        }
        return "error";
    }

    @RequestMapping("stepReplyNotOn")
    public String stepReplyNotOn(Long id,Long consumerId,Long doctorId,Long greatId,Model model){
        if(id == null || consumerId == null || greatId == null ){
            return "error";
        }
        return stepReplyOnCommon(id,consumerId,doctorId,greatId,1,model);
    }

    @RequestMapping("addCollection")
    public String addCollection(Long consumerId,Long doctorId,Model model){
        if(consumerId == null || doctorId == null){
            return "error";
        }
        TConsumer tConsumer = consumerService.selectByPrimaryKey(consumerId);
        TDoctor tDoctor = doctorService.selectByPrimaryKey(doctorId);

        ChatVo cd1 = new ChatVo();
        cd1.setDoctorId(doctorId);
        cd1.setConsumerId(consumerId);
        TDoctorCollection tDoctorCollection = doctorCollectionService.selectByConsumerIdAndDoctorId(cd1);
        if(tDoctorCollection == null){
            TDoctorCollection tDoctorCollection2 = new TDoctorCollection();
            tDoctorCollection2.setDoctorId(doctorId);
            tDoctorCollection2.setConsumerId(consumerId);
            tDoctorCollection2.setGmtCreate(DateUtil.getDaDate());
            int insert = doctorCollectionService.insert(tDoctorCollection2);
            if(insert == 1){
                ChatVo cd2 = new ChatVo();
                cd2.setDoctorId(doctorId);
                cd2.setConsumerId(consumerId);
                TDoctorCollection tDoctorCollection1 = doctorCollectionService.selectByConsumerIdAndDoctorId(cd2);
                model.addAttribute("tDoctorCollection",tDoctorCollection1);
                model.addAttribute("tConsumer",tConsumer);
                model.addAttribute("tDoctor",tDoctor);
                return "information/doctorProfile::collection_refresh";
            }else {
                return "error";
            }
        }else {
            int i = doctorCollectionService.deleteByPrimaryKey(tDoctorCollection.getId());
            if(i == 1){
                ChatVo cd3 = new ChatVo();
                cd3.setDoctorId(doctorId);
                cd3.setConsumerId(consumerId);
                TDoctorCollection tDoctorCollection1 = doctorCollectionService.selectByConsumerIdAndDoctorId(cd3);
                model.addAttribute("tDoctorCollection",tDoctorCollection1);
                model.addAttribute("tConsumer",tConsumer);
                model.addAttribute("tDoctor",tDoctor);
                return "information/doctorProfile::collection_refresh";
            }else {
                return "error";
            }
        }
    }

}
