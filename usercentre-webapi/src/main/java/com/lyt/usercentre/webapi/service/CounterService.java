package com.lyt.usercentre.webapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyt.common.utils.PageUtils;
import com.lyt.usercentre.webapi.entity.CounterEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-20 23:04:47
 */

public interface CounterService extends IService<CounterEntity> {

    PageUtils queryPage(Map<String, Object> params);

    int createUserId(CounterEntity counterEntity);
}

