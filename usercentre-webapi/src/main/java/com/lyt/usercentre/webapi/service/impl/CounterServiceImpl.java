package com.lyt.usercentre.webapi.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyt.common.utils.PageUtils;
import com.lyt.common.utils.Query;

import com.lyt.usercentre.webapi.dao.CounterDao;
import com.lyt.usercentre.webapi.entity.CounterEntity;
import com.lyt.usercentre.webapi.service.CounterService;


@Service("counterService")
public class CounterServiceImpl extends ServiceImpl<CounterDao, CounterEntity> implements CounterService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CounterEntity> page = this.page(
                new Query<CounterEntity>().getPage(params),
                new QueryWrapper<CounterEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public int createUserId(CounterEntity counterEntity) {

        return this.baseMapper.createUserId(counterEntity);

    }

}