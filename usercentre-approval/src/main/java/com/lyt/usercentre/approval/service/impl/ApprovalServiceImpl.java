package com.lyt.usercentre.approval.service.impl;

import com.lyt.common.utils.BaseUtils;
import com.lyt.usercentre.approval.entity.AppEntity;
import com.lyt.usercentre.approval.feign.WebapiFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyt.common.utils.PageUtils;
import com.lyt.common.utils.Query;

import com.lyt.usercentre.approval.dao.ApprovalDao;
import com.lyt.usercentre.approval.entity.ApprovalEntity;
import com.lyt.usercentre.approval.service.ApprovalService;


@Service("approvalService")
public class ApprovalServiceImpl extends ServiceImpl<ApprovalDao, ApprovalEntity> implements ApprovalService {


    @Autowired
    WebapiFeignService webapiFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ApprovalEntity> page = this.page(
                new Query<ApprovalEntity>().getPage(params),
                new QueryWrapper<ApprovalEntity>()
        );

        page.getRecords().forEach(item ->{
            item.setAddTime(BaseUtils.correctTime(item.getAddTime()));
            item.setModTime(BaseUtils.correctTime(item.getModTime()));
        });

        return new PageUtils(page);
    }


    @Override
    public PageUtils query(String applicant_name, Map<String, Object> params) {

        IPage<ApprovalEntity> page = this.page(
                new Query<ApprovalEntity>().getPage(params),
                new QueryWrapper<ApprovalEntity>().eq("applicant_name", applicant_name)
        );

//        List<ApprovalEntity> list = this.list(new QueryWrapper<ApprovalEntity>().eq("applicant_name", applicant_name));

        return new PageUtils(page);
    }

    @Override
    public PageUtils adminQuery(Map<String, Object> params) {
        IPage<ApprovalEntity> page = this.page(
                new Query<ApprovalEntity>().getPage(params),
                new QueryWrapper<ApprovalEntity>().eq("state", 0).or().eq("reviewer_name", params.get("reviewer_name")).groupBy("state","id")
        );

        return new PageUtils(page);
    }

    @Override
    public void updateApproval(ApprovalEntity approval) {
        //通过申请，添加到应用信息表
        if(approval.getState() == 1){
            //todo rpc request to webapi
            AppEntity appEntity = new AppEntity();
            appEntity.setApplicantName(approval.getApplicantName());
            appEntity.setApplicantId(approval.getApplicantId());
            appEntity.setAppName(approval.getAppName());
            appEntity.setApprovalId(approval.getId());

            webapiFeignService.save(appEntity);
            //save
            this.updateById(approval);
        } else {
            this.updateById(approval);
        }
    }
}