package com.lyt.usercentre.webapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyt.common.utils.PageUtils;
import com.lyt.common.utils.R;
import com.lyt.usercentre.webapi.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-20 19:35:48
 */

public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateInfo(UserEntity entity);

    UserEntity queryByUserName(String userName);

    R createToken(Integer userId) throws Exception;

    UserEntity queryByTel(String tel);

    List<UserEntity> queryByUserId(Integer id);

    void saveUser(UserEntity user);
}

