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
 * @date 2020-05-19 18:51:13
 */
@Data
@TableName("usercentre_app")
public class AppEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 应用名称
	 */
	private String appName;
	/**
	 * 审查id
	 */
	private Integer approvalId;
	/**
	 * 应用申请人id
	 */
	private Integer applicantId;
	/**
	 * 应用申请人用户名
	 */
	private String applicantName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 回掉地址
	 */
	private String redirectRul;
	/**
	 * 是否可用
	 */
	private Integer isDelete;

}
