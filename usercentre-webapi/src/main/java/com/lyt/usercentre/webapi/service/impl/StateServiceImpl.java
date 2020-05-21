package com.lyt.usercentre.webapi.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyt.common.utils.PageUtils;
import com.lyt.common.utils.Query;

import com.lyt.usercentre.webapi.dao.StateDao;
import com.lyt.usercentre.webapi.entity.StateEntity;
import com.lyt.usercentre.webapi.service.StateService;


@Service("stateService")
public class StateServiceImpl extends ServiceImpl<StateDao, StateEntity> implements StateService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StateEntity> page = this.page(
                new Query<StateEntity>().getPage(params),
                new QueryWrapper<StateEntity>()
        );

        return new PageUtils(page);
    }

}