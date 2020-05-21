package com.lyt.usercentre.webapi.dao;

import com.lyt.usercentre.webapi.entity.CounterEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-20 23:04:47
 */
@Mapper
public interface CounterDao extends BaseMapper<CounterEntity> {

    int createUserId(CounterEntity counterEntity);
	
}
