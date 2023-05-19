package com.jia.front.client;

import com.jia.entity.TDrugStorage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title IDrugStorageClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:02
 **/
@FeignClient("drug-service")
public interface IDrugStorageClient {
    @RequestMapping("/drugStorage/alldrug")
    List<TDrugStorage> selectAll();

    @RequestMapping("/drugStorage/insert")
    @ResponseBody
    int insert(@RequestBody TDrugStorage tDrugStorage);

    @RequestMapping("/drugStorage/key")
    @ResponseBody
    TDrugStorage selectByPrimaryKey(@RequestBody Long id);

    @RequestMapping("/drugStorage/update")
    @ResponseBody
    int updateByPrimaryKey(@RequestBody TDrugStorage tDrugStorage);

    @RequestMapping("/drugStorage/delete")
    @ResponseBody
    int deleteByPrimaryKey(@RequestBody Long id);
    @RequestMapping("/drugStorage/banner")
    @ResponseBody
    List<TDrugStorage> selectByBanner(@RequestBody Integer b);

    @RequestMapping("/drugStorage/search")
    @ResponseBody
    List<TDrugStorage> selectBySearch(@RequestBody String s);

}
