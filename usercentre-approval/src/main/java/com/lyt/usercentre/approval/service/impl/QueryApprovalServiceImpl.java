package com.lyt.usercentre.approval.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lyt.common.utils.PageUtils;
import com.lyt.usercentre.approval.entity.ApprovalEntity;
import com.lyt.usercentre.approval.service.ApprovalService;
import com.lyt.usercentre.approval.service.QueryApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryApprovalServiceImpl implements QueryApprovalService {

    @Autowired
    ApprovalService approvalService;

    @Override
    public PageUtils query(String username) {
        List<ApprovalEntity> list =
                approvalService.list(new QueryWrapper<ApprovalEntity>().eq("applicant_name", username));
        return null;
    }
}
