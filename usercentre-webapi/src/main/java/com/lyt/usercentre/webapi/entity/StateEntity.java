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
 * @date 2020-05-19 18:51:14
 */
@Data
@TableName("usercentre_state")
public class StateEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 手机号码
	 */
	private String tel;
	/**
	 * 应用信息id
	 */
	private Integer appId;
	/**
	 * token
	 */
	private String token;
	/**
	 * 是否可用[0-不可用，1-可用]
	 */
	private Integer isDeleted;
	/**
	 * 
	 */
	private Date expireTime;
	/**
	 * 
	 */
	private Date updateTime;

}
