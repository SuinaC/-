package com.jia.front.client;

import com.jia.entity.TDoctorCollection;
import com.jia.entity.vo.ChatVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title IDoctorCollectionClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/9 00:17
 **/
@FeignClient("doctor-service")
public interface IDoctorCollectionClient {
    @RequestMapping("/collection/consumer")
    @ResponseBody
    List<TDoctorCollection> selectByConsumerId(@RequestBody Long id);

    @RequestMapping("/collection/doctor/consumer")
    @ResponseBody
    TDoctorCollection selectByConsumerIdAndDoctorId(@RequestBody ChatVo chatVo);

    @RequestMapping("/collection/insert")
    @ResponseBody
    int insert(@RequestBody TDoctorCollection tDoctorCollection);

    @RequestMapping("/collection/delete")
    @ResponseBody
    int deleteByPrimaryKey(@RequestBody Long id);
}
