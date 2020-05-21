package com.lyt.usercentre.account.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyt.common.utils.PageUtils;
import com.lyt.common.utils.Query;

import com.lyt.usercentre.account.dao.AccountDao;
import com.lyt.usercentre.account.entity.AccountEntity;
import com.lyt.usercentre.account.service.AccountService;


@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountDao, AccountEntity> implements AccountService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AccountEntity> page = this.page(
                new Query<AccountEntity>().getPage(params),
                new QueryWrapper<AccountEntity>()
        );

        return new PageUtils(page);
    }

}