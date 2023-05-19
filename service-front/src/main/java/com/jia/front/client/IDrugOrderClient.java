package com.jia.front.client;

import com.jia.entity.TDrugOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title IDrugOrderClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:03
 **/
@FeignClient("drug-service")
public interface IDrugOrderClient {
    @RequestMapping("drugOrder/allOrderById")
    @ResponseBody
    List<TDrugOrder> selectAllByDoctorId(@RequestBody Long id);

    @RequestMapping("drugOrder/insert")
    @ResponseBody
    int insert(@RequestBody TDrugOrder tDrugOrder);

    @RequestMapping("drugOrder/all/consumer")
    @ResponseBody
    List<TDrugOrder> selectAllByConsumerId(@RequestBody Long id);
}
