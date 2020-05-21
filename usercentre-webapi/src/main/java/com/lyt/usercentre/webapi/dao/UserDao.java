package com.lyt.usercentre.webapi.dao;

import com.lyt.usercentre.webapi.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-20 19:35:48
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    UserEntity queryByUserName(String username);

    UserEntity queryByTel(String tel);

    List<UserEntity> queryByUserId(Integer userId);
	
}
