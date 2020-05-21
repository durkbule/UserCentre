package com.lyt.usercentre.webapi.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.lyt.usercentre.webapi.Feign.ApprovalFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lyt.usercentre.webapi.entity.AppEntity;
import com.lyt.usercentre.webapi.service.AppService;
import com.lyt.common.utils.PageUtils;
import com.lyt.common.utils.R;



/**
 * 
 *
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-19 18:51:13
 */
@RestController
@RequestMapping("webapi/app")
public class AppController {
    @Autowired
    private AppService appService;

    @Autowired
    private ApprovalFeignService approvalFeignService;

    @GetMapping("/test")
    public R test(){

        R r = approvalFeignService.getadmin();
        return r;
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("webapi:app:list")
    public R list(@RequestParam Map<String, Object> params){
        //如果是管理员则显示全部应用，并且可以启用或禁用
        if(((List)approvalFeignService.getadmin().get("admin")).contains(params.get("applicant_name").toString()))
        {
            PageUtils page = appService.queryPage(params);
            return R.ok().put("page", page).put("isAdmin", "true");
        } else {
            PageUtils page = appService.queryPageByApplicant(params);
            return R.ok().put("page", page).put("isAdmin", "false");
        }
//        PageUtils page = appService.queryPage(params);
//        return R.ok().put("page", page).put("isAdmin", false);

    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("webapi:app:info")
    public R info(@PathVariable("id") Integer id){
		AppEntity app = appService.getById(id);

        return R.ok().put("app", app);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("webapi:app:save")
    public R save(@RequestBody AppEntity app){
		appService.save(app);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("webapi:app:update")
    public R update(@RequestBody AppEntity app){
		appService.updateById(app);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("webapi:app:delete")
    public R delete(@RequestBody Integer[] ids){
		appService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
