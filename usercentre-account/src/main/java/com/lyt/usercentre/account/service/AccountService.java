package com.lyt.usercentre.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyt.common.utils.PageUtils;
import com.lyt.usercentre.account.entity.AccountEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-15 19:17:19
 */
public interface AccountService extends IService<AccountEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

