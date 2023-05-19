package com.jia.front.client;

import com.jia.entity.TNurse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title INurseClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:03
 **/
@FeignClient("patient-service")
public interface INurseClient {
    @RequestMapping("nurse/address")
    @ResponseBody
    List<TNurse> selectByAddress(@RequestBody String address);

    @RequestMapping("nurse/key")
    @ResponseBody
    TNurse selectByPrimaryKey(@RequestBody Long id);

    @RequestMapping("nurse/update")
    @ResponseBody
    int updateByPrimaryKey(@RequestBody TNurse nurse);

    @RequestMapping("nurse/delete")
    @ResponseBody
    int deleteByPrimaryKey(@RequestBody Long id);

    @RequestMapping("nurse/name/address")
    @ResponseBody
    List<TNurse> selectByNameAndAddress(@RequestBody TNurse tNurse);

    @RequestMapping("nurse/user")
    @ResponseBody
    TNurse selectByUsername(@RequestBody String username);

    @RequestMapping("nurse/phone")
    @ResponseBody
    TNurse selectByPhone(@RequestBody Long id);

    @RequestMapping("nurse/Wechat")
    @ResponseBody
    TNurse selectByWchat(@RequestBody String wechat);

    @RequestMapping("nurse/email")
    @ResponseBody
    TNurse selectByEmail(@RequestBody String email);

    @RequestMapping("nurse/idcard")
    @ResponseBody
    TNurse selectByIdCard(@RequestBody String id);

    @RequestMapping("nurse/insert")
    @ResponseBody
    int insert(@RequestBody TNurse tNurse);


}
