package com.lyt.usercentre.webapi.controller;

import java.util.Arrays;
import java.util.Map;

import com.lyt.common.exception.BizCodeEnum;
import com.lyt.usercentre.webapi.entity.UserEntity;
import com.lyt.usercentre.webapi.form.LoginForm;
import com.lyt.usercentre.webapi.form.RegisterForm;
import com.lyt.usercentre.webapi.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lyt.usercentre.webapi.entity.StateEntity;
import com.lyt.usercentre.webapi.service.StateService;
import com.lyt.common.utils.PageUtils;
import com.lyt.common.utils.R;



/**
 * 
 *
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-19 18:51:14
 */
@RestController
@RequestMapping("webapi/state")
public class StateController {
    @Autowired
    private StateService stateService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginForm loginForm) throws Exception {

        UserEntity user = userService.queryByUserName(loginForm.getUserName());
        String s = new Sha256Hash(loginForm.getPassword(), user.getSalt()).toHex();
        if(user == null || !user.getPassword().equals(new Sha256Hash(loginForm.getPassword(), user.getSalt()).toHex())) {
            return R.error(BizCodeEnum.USER_NAME_OR_PASSWORD_ERROR.getMsg());
        }

        if(user.getIsDeleted() == 1){
            return R.error(BizCodeEnum.USER_LOCKED.getCode(), BizCodeEnum.USER_LOCKED.getMsg());
        }

        R r = userService.createToken(user.getUserId());

        return r;
    }

    @PostMapping("/register")
    public R register(@RequestBody RegisterForm registerForm){

        UserEntity entity = userService.queryByUserName(registerForm.getUserName());

        UserEntity user = new UserEntity();
        user.setAppName(registerForm.getAppName());
        user.setEmail(registerForm.getEmail());
        user.setUserName(registerForm.getUserName());
        user.setPassword(registerForm.getPassword());
        user.setTel(registerForm.getTel());
        user.setAppId(registerForm.getAppId());
        if(entity != null){
            user.setUserId(entity.getUserId());
        }
        userService.saveUser(user);


        return R.ok();

    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("webapi:state:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = stateService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("webapi:state:info")
    public R info(@PathVariable("id") Integer id){
		StateEntity state = stateService.getById(id);

        return R.ok().put("state", state);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("webapi:state:save")
    public R save(@RequestBody StateEntity state){
		stateService.save(state);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("webapi:state:update")
    public R update(@RequestBody StateEntity state){
		stateService.updateById(state);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("webapi:state:delete")
    public R delete(@RequestBody Integer[] ids){
		stateService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
