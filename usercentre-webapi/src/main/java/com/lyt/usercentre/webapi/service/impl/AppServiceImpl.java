package com.lyt.usercentre.webapi.service.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyt.common.utils.PageUtils;
import com.lyt.common.utils.Query;

import com.lyt.usercentre.webapi.dao.AppDao;
import com.lyt.usercentre.webapi.entity.AppEntity;
import com.lyt.usercentre.webapi.service.AppService;


@Service("appService")
public class AppServiceImpl extends ServiceImpl<AppDao, AppEntity> implements AppService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AppEntity> page = this.page(
                new Query<AppEntity>().getPage(params),
                new QueryWrapper<AppEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByApplicant(Map<String, Object> params) {
        String applicant_name = params.get("applicant_name").toString();
        IPage<AppEntity> page = this.page(
                new Query<AppEntity>().getPage(params),
                new QueryWrapper<AppEntity>().eq("applicant_name", applicant_name)
        );
        return new PageUtils(page);
    }

    @Override
    public List<AppEntity> querypageByApplicantId(Integer id) {
        Map<String,Object> map = new HashMap<>();
        //查询所有 不分页
        map.put("limit","10000");
        IPage<AppEntity> page = this.page(
                new Query<AppEntity>().getPage(map),
                new QueryWrapper<AppEntity>().eq("applicant_id", id)
        );

        return page.getRecords();
    }
}