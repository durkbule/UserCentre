package com.lyt.usercentre.account.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-15 19:17:19
 */
@Data
@TableName("usercentre_account")
public class AccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 账号id
	 */
	@TableId
	private Integer id;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 注册时间
	 */
	private Date addTime;
	/**
	 * 电话号码
	 */
	private String tel;
	/**
	 * 是否被可用[0-不可用，1-可用]
	 */
	private Integer isDeleted;

}
