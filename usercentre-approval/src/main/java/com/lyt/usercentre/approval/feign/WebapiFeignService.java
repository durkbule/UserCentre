package com.lyt.usercentre.approval.feign;


import com.lyt.common.utils.R;
import com.lyt.usercentre.approval.entity.AppEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("usercentre-webapi")
public interface WebapiFeignService {

    @RequestMapping(value = "/webapi/app/save" , method = RequestMethod.POST,consumes = "application/json")
    //@RequiresPermissions("webapi:app:save")
    public R save(@RequestBody AppEntity app);

}
