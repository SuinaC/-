package com.jia.front.client;

import com.jia.entity.TMail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * @title IMailClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/9 10:52
 **/
@FeignClient("other-service")
public interface IMailClient {
    @RequestMapping("mail/insert")
    @ResponseBody
    int insert(@RequestBody TMail tMail);

    @RequestMapping("mail/consumer")
    @ResponseBody
    TMail selectByConsumerId(@RequestBody Long id);
}
