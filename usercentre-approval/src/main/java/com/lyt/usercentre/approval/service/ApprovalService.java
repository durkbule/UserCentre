package com.lyt.usercentre.approval.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyt.common.utils.PageUtils;
import com.lyt.usercentre.approval.entity.ApprovalEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-15 19:16:05
 */
public interface ApprovalService extends IService<ApprovalEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils query(String applicant_name, Map<String, Object> params);

    PageUtils adminQuery(Map<String, Object> params);

    void updateApproval(ApprovalEntity approval);
}

