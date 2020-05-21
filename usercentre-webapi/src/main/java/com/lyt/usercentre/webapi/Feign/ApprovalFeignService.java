package com.lyt.usercentre.webapi.Feign;

import com.lyt.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("usercentre-approval")
public interface ApprovalFeignService {

    @GetMapping("approval/approval/getadmin")
    public R getadmin();


}