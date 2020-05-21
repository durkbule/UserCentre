package com.lyt.usercentre.webapi.dao;

import com.lyt.usercentre.webapi.entity.AppEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-19 18:51:13
 */
@Mapper
public interface AppDao extends BaseMapper<AppEntity> {
	
}
