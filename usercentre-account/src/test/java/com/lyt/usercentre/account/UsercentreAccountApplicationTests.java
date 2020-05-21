package com.lyt.usercentre.account;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyt.usercentre.account.entity.AccountEntity;
import com.lyt.usercentre.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UsercentreAccountApplicationTests {

    @Autowired
    AccountService accountService;



    @Test
    void contextLoads() {

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1);
        accountEntity.setUserName("ccccccccccc");
        accountEntity.setTel("17640396423");
        accountService.updateById(accountEntity);

        List<AccountEntity> list = accountService.list(new QueryWrapper<AccountEntity>().eq("user_name", 1));
        list.forEach((item)->{
            System.out.println(item);
        });
    }

}
