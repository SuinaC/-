package com.jia.front.controller;


import com.jia.entity.TConsumer;
import com.jia.entity.TDrugOrder;
import com.jia.front.client.IConsumerClient;
import com.jia.front.client.IDrugOrderClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("drug")
public class DrugOrderController {

    @Resource
    IDrugOrderClient drugOrderService;

    @Resource
    IConsumerClient consumerService;

    @RequestMapping("drugOrderList")
    public String drugOrderList(Long id, Model model){
        if(id == null){
            return "500";
        }

        List<TDrugOrder> tDrugOrders = drugOrderService.selectAllByConsumerId(id);
        TConsumer tConsumer = consumerService.selectByPrimaryKey(id);
        model.addAttribute("tDrugOrders",tDrugOrders);
        model.addAttribute("tConsumer",tConsumer);
        return "information/drugOrderList";
    }
}
