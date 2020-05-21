package com.lyt.usercentre.webapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyt.common.utils.PageUtils;
import com.lyt.usercentre.webapi.entity.StateEntity;

import java.util.Map;

/**
 * 
 *
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-19 18:51:14
 */

public interface StateService extends IService<StateEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

