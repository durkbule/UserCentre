package io.renren;

import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.sys.controller.SysLoginController;
import io.renren.modules.sys.form.RegisterForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private SysLoginController sysLoginController;


    @Test
    public void test() {
        String token = jwtUtils.generateToken(1);

        System.out.println(token);
    }

    @Test
    public void test1(){
        RegisterForm form = new RegisterForm();

        form.setUsername("abcd");
        form.setEmail("8201613@qq.com");
        form.setPassword("1234");
        form.setMobile("11644598874");

        System.out.println(sysLoginController.register(form));



    }

}
