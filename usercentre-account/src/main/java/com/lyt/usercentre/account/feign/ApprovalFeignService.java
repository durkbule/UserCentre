package com.lyt.usercentre.account.feign;


import com.lyt.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("usercentre-approval")
public interface ApprovalFeignService {

    @RequestMapping("/approval/approval/test")
    public R test();

}
