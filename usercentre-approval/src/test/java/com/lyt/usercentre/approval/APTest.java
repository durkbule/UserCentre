package com.lyt.usercentre.approval;

import com.lyt.usercentre.approval.config.AdminConfig;
import com.lyt.usercentre.approval.controller.ApprovalController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class APTest {

    @Autowired
    private ApprovalController approvalController;

    @Autowired
    AdminConfig adminConfig;
    @Test
    void contextLoads() {

        String user_name ="admin";

        System.out.println(adminConfig.getAdmin().toString());


    }

}
