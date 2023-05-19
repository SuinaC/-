package com.jia.front.controller;

import com.jia.entity.TDrugStorage;
import com.jia.front.client.IDrugStorageClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("medicalShop")
@Controller
public class MedicalShopController {
    private static final Logger logger = LoggerFactory.getLogger(MedicalShopController.class);

    @Resource
    IDrugStorageClient drugStorageService;


    @RequestMapping("home")
    public String home(Model model){
        List<TDrugStorage> tDrugStorageBanner = drugStorageService.selectByBanner(3);
        List<TDrugStorage> tDrugStorageDiscount = drugStorageService.selectByBanner(2);
        for (TDrugStorage tDrugStorage : tDrugStorageDiscount) {
            Double price = tDrugStorage.getPrice();
            Double discount = tDrugStorage.getDiscount();
            Double dis = (price - discount) / price * 100;
            tDrugStorage.setDisCount(dis + "%");
        }
        model.addAttribute("tDrugStorageBanner",tDrugStorageBanner);
        model.addAttribute("tDrugStorageDiscount",tDrugStorageDiscount);
        return "medicalShop/home";
    }

    @RequestMapping("simpleProduce")
    public String simpleProduce(Long id,Model model){
        if(id == null){
            return "500";
        }
        TDrugStorage tDrugStorage = drugStorageService.selectByPrimaryKey(id);
        model.addAttribute("tDrugStorage",tDrugStorage);
        return "medicalShop/simpleProduct";

    }

    @RequestMapping("shop")
    public String shop(String search,Model model){
        List<TDrugStorage> tDrugStorages = drugStorageService.selectBySearch(search);
        model.addAttribute("tDrugStorages",tDrugStorages);
        return "medicalShop/shop";

    }
}
