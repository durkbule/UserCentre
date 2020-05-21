package com.lyt.usercentre.approval.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyt.common.exception.BizCodeEnum;
import com.lyt.common.validate.AddGroup;
import com.lyt.common.validate.UpdateGroup;
import com.lyt.usercentre.approval.config.AdminConfig;
import com.lyt.usercentre.approval.entity.QueryApprovalEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.lyt.usercentre.approval.entity.ApprovalEntity;
import com.lyt.usercentre.approval.service.ApprovalService;
import com.lyt.common.utils.PageUtils;
import com.lyt.common.utils.R;

import javax.validation.Valid;


/**
 * 
 *
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-15 19:16:05
 */
@RestController
@RequestMapping("approval/approval")
public class ApprovalController {
    @Autowired
    private ApprovalService approvalService;

    @Autowired
    AdminConfig adminConfig;

    @GetMapping("/getadmin")
    public R getadmin(){
        return R.ok().put("admin",adminConfig.getAdmin());
    }

    @GetMapping("/query")
    public R query(@RequestParam Map<String, Object> params ,@RequestParam(value = "applicant_name") String applicant_name){

        if(StringUtils.isEmpty(applicant_name) || ObjectUtils.isEmpty(params)){
            return R.error(BizCodeEnum.UNKNOW_ERROR.getCode(), BizCodeEnum.UNKNOW_ERROR.getMsg());
        }
        //不是管理员只能查看自己的申请
        if(!adminConfig.getAdmin().contains(applicant_name)){
            PageUtils page = approvalService.query(applicant_name, params);
            return R.ok().put("page",page).put("isAdmin", "false");

        }
        //是管理员能查看未审核的申请和自己审核的申请
        else {
            PageUtils page = approvalService.adminQuery(params);
            return R.ok().put("page",page).put("isAdmin", "true");
        }

    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("approval:approval:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = approvalService.queryPage(params);


        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("approval:approval:info")
    public R info(@PathVariable("id") Integer id){
		ApprovalEntity approval = approvalService.getById(id);

        return R.ok().put("approval", approval);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("approval:approval:save")
    public R save(@Validated({AddGroup.class})  @RequestBody ApprovalEntity approval){
		approvalService.save(approval);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("approval:approval:update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody ApprovalEntity approval){
        //通过则添加到
		approvalService.updateApproval(approval);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("approval:approval:delete")
    public R delete(@RequestBody Integer[] ids){
        //物理删除
		approvalService.removeByIds(Arrays.asList(ids));


        return R.ok();
    }

}
