package com.lyt.usercentre.approval.service;

import com.lyt.common.utils.PageUtils;
import org.springframework.stereotype.Service;

@Service
public interface QueryApprovalService {


    PageUtils query(String username);
}
