package com.jia.controller;

import com.jia.entity.TDoctorCollection;
import com.jia.entity.vo.ChatVo;
import com.jia.service.IDoctorCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @title DoctorCollectionController
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/9 10:37
 **/
@RestController
@RequestMapping("collection")
public class DoctorCollectionController {

    @Autowired
    IDoctorCollectionService doctorCollectionService;


    @RequestMapping("/consumer")
    public List<TDoctorCollection> selectCollectionByConsumerId(@RequestBody Long id){
        return doctorCollectionService.selectByConsumerId(id);
    }

    @RequestMapping("/doctor/consumer")
    public TDoctorCollection selectByConsumerIdAndDoctorId(@RequestBody ChatVo chatVo){
        Long did = chatVo.getDoctorId();
        Long cid = chatVo.getConsumerId();
        return doctorCollectionService.selectByConsumerIdAndDoctorId(did,cid);
    }

    @RequestMapping("/insert")
    public int insert(@RequestBody TDoctorCollection tDoctorCollection){
        return doctorCollectionService.insert(tDoctorCollection);
    }

    @RequestMapping("/delete")
    public int deleteByPrimaryKey(@RequestBody Long id){
        return doctorCollectionService.deleteByPrimaryKey(id);
    }

}
