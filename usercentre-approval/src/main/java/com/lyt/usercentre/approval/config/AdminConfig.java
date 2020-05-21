package com.lyt.usercentre.approval.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

@RefreshScope
@Component
public class AdminConfig {

        @Value("#{'${admin.list}'.split(',')}")
        private List<String> admin;

        public  List<String> getAdmin(){
            return admin;

        }

}
