package com.lyt.usercentre.account.uitls;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

@RefreshScope
@Component
public class Constant {

    @Value("#{'${account.admin}'.split(',')}")
    private List<String> admin;

    public  List<String> getAdmin(){
    return admin;

    }
}
