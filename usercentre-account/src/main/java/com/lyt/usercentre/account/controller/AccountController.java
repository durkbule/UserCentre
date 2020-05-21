package com.lyt.usercentre.account.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyt.usercentre.account.entity.AccountEntity;
import com.lyt.usercentre.account.service.AccountService;
import com.lyt.common.utils.PageUtils;
import com.lyt.common.utils.R;



/**
 * 
 *
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-15 19:17:19
 */
@RestController
@RequestMapping("account/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("account:account:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = accountService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("account:account:info")
    public R info(@PathVariable("id") Integer id){
		AccountEntity account = accountService.getById(id);

        return R.ok().put("account", account);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("account:account:save")
    public R save(@RequestBody AccountEntity account){
		accountService.save(account);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("account:account:update")
    public R update(@RequestBody AccountEntity account){
		accountService.updateById(account);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("account:account:delete")
    public R delete(@RequestBody Integer[] ids){
		accountService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
