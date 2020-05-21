package com.lyt.usercentre.webapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.lyt.common.utils.R;
import com.lyt.usercentre.webapi.entity.AppEntity;
import com.lyt.usercentre.webapi.entity.CounterEntity;
import com.lyt.usercentre.webapi.entity.StateEntity;
import com.lyt.usercentre.webapi.service.AppService;
import com.lyt.usercentre.webapi.service.CounterService;
import com.lyt.usercentre.webapi.service.StateService;
import com.lyt.usercentre.webapi.utils.TokenGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyt.common.utils.PageUtils;
import com.lyt.common.utils.Query;

import com.lyt.usercentre.webapi.dao.UserDao;
import com.lyt.usercentre.webapi.entity.UserEntity;
import com.lyt.usercentre.webapi.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    private final static int EXPIRE = 3600 * 12;


    @Autowired
    private AppService appService;

    @Autowired
    private StateService stateService;

    @Autowired
    private CounterService counterService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        List<AppEntity> list = appService.querypageByApplicantId(Integer.parseInt((String) params.get("applicant_id")));
        Map<Integer, String> nameMap = new HashMap<>();
        for (AppEntity entity : list) {
            nameMap.put(entity.getId(), entity.getAppName());
        }

        String tel = "";
        if (ObjectUtils.isNotEmpty(params.get("key"))) {
            tel = (String) params.get("key");
            IPage<UserEntity> page = this.page(
                    new Query<UserEntity>().getPage(params),
                    new QueryWrapper<UserEntity>().eq("tel", tel).in("app_id", nameMap.keySet()).groupBy("app_id")
            );
            return new PageUtils(page);
        } else {
            IPage<UserEntity> page = this.page(
                    new Query<UserEntity>().getPage(params),
                    new QueryWrapper<UserEntity>().in("app_id", nameMap.keySet()).groupBy("app_id")
            );

            return new PageUtils(page);
        }
    }

    @Override
    public void updateInfo(UserEntity entity) {
        QueryWrapper<UserEntity> wrapper= new QueryWrapper<UserEntity>();
        wrapper.eq("user_id", entity.getUserId());
        entity.setAppId(null);
        entity.setAppName(null);
        this.update(entity, wrapper);

    }

    @Override
    public UserEntity queryByUserName(String userName) {

        UserEntity entity = baseMapper.queryByUserName(userName);

        return entity;
    }

    @Override
    public R createToken(Integer userId) throws Exception {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        StateEntity stateEntity = stateService.getById(userId);
        if(stateEntity == null){
            stateEntity = new StateEntity();
            stateEntity.setUserId(userId);
            stateEntity.setToken(token);
            stateEntity.setUpdateTime(now);
            stateEntity.setExpireTime(expireTime);

            //保存token
            stateService.save(stateEntity);
        }else{
            stateEntity.setToken(token);
            stateEntity.setUpdateTime(now);
            stateEntity.setExpireTime(expireTime);

            //更新token
            stateService.updateById(stateEntity);
        }

        R r = R.ok().put("token", token).put("expire", EXPIRE);

        return null;
    }

    @Override
    public UserEntity queryByTel(String tel) {

        UserEntity entity = this.baseMapper.queryByTel(tel);
        return entity;
    }

    @Override
    public List<UserEntity> queryByUserId(Integer id) {

        List<UserEntity> entityList = this.baseMapper.queryByUserId(id);

        return entityList;
    }

    @Override
    public void saveUser(UserEntity user) {
        user.setAddTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        user.setIsDeleted(0);
        //userID
        if(ObjectUtils.isEmpty(user.getUserId())) {
            CounterEntity counterEntity = new CounterEntity();
            counterService.createUserId(counterEntity);
            user.setUserId(counterEntity.getId());
        }
        this.save(user);
    }
}