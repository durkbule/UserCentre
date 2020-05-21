package com.lyt.usercentre.webapi;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyt.usercentre.webapi.controller.AppController;
import com.lyt.usercentre.webapi.controller.UserController;
import com.lyt.usercentre.webapi.entity.AppEntity;
import com.lyt.usercentre.webapi.entity.CounterEntity;
import com.lyt.usercentre.webapi.service.AppService;
import com.lyt.usercentre.webapi.service.CounterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class UsercentreWebapiApplicationTests {

    @Autowired
    private AppController appController;

    @Autowired
    private AppService appService;

    @Autowired
    private UserController userController;

    @Autowired
    CounterService counterService;

    @Test
    void contextLoads() {
        Map<String,Object> map = new HashMap<>();

        map.put("page", "1");
        map.put("limit", "10");
        map.put("key", "");
        map.put("applicant_id", "1");

        System.out.println(JSON.toJSON(userController.list(map)));
    }


    @Test
    void apptest(){
        Object applicant_id = "6";
        Map<String, Object> map = appService.getMap(new QueryWrapper<AppEntity>().eq("applicant_id", applicant_id));
        System.out.println(map.get("app_name"));
        System.out.println(JSON.toJSON(map));


    }

    @Test
    void testt(){
        CounterEntity counterEntity = new CounterEntity();
        counterService.createUserId(counterEntity);
        System.out.println(counterEntity.getId());
    }
}
