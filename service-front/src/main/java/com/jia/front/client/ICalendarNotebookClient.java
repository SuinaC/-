package com.jia.front.client;

import com.jia.entity.TCalendarNotebook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/***
 * @title ICalendarNotebookClient
 * @description <TODO description class purpose>
 * @author wangjia
 * @version 1.0.0
 * @create 2023/5/8 17:04
 **/

@FeignClient("drug-service")
public interface ICalendarNotebookClient {
    @RequestMapping("/note/insert")
    @ResponseBody
    int insert(@RequestBody TCalendarNotebook note);

    @RequestMapping("/note/consumer")
    @ResponseBody
    List<TCalendarNotebook> selectByConsumerId(@RequestBody Long id);

    @RequestMapping("/note/key")
    @ResponseBody
    TCalendarNotebook selectByPrimaryKey(@RequestBody Long id);

    @RequestMapping("/note/update")
    @ResponseBody
    int updateByPrimaryKey(@RequestBody TCalendarNotebook tCalendarNotebook);

    @RequestMapping("/note/lastInsert")
    @ResponseBody
    Long selectLastInsertId();

    @RequestMapping("/note/delete")
    @ResponseBody
    int deleteByPrimaryKey(@RequestBody Long id);
}
