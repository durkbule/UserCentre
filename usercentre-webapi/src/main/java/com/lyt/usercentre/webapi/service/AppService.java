package com.lyt.usercentre.webapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyt.common.utils.PageUtils;
import com.lyt.usercentre.webapi.entity.AppEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-19 18:51:13
 */

public interface AppService extends IService<AppEntity> {

    PageUtils queryPage(Map<String, Object> params);


    PageUtils queryPageByApplicant(Map<String, Object> params);

    List<AppEntity> querypageByApplicantId(Integer id);


}

