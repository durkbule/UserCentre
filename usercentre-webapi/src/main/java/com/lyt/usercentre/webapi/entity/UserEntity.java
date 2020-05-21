package com.lyt.usercentre.webapi.entity;

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
 * @date 2020-05-20 19:35:48
 */
@Data
@TableName("usercentre_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private Integer id;
	/**
	 * 用户id，标识唯一用户
	 */
	private Integer userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 电话号码,标识唯一用户
	 */
	private String tel;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 应用信息id
	 */
	private Integer appId;
	/**
	 * 应用名
	 */
	private String appName;
	/**
	 * 注册时间
	 */
	private Date addTime;
	/**
	 * 盐
	 */
	private String salt;
	/**
	 * 是否可用[0-不可用，1-可用]
	 */
	private Integer isDeleted;

}
