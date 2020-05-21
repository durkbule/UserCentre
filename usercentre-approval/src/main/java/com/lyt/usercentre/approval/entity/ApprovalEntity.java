package com.lyt.usercentre.approval.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.lyt.common.validate.AddGroup;
import com.lyt.common.validate.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 
 * 
 * @author lyt
 * @email 820781560@qq.com
 * @date 2020-05-15 19:16:05
 */
@Data
@TableName("usercentre_approval")
public class ApprovalEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Null(message = "新增id必须为空", groups = {AddGroup.class})
	@NotNull(message = "id不能为空", groups = {UpdateGroup.class})
	@TableId
	private Integer id;
	/**
	 * 申请提交人id
	 */
	@NotNull(message = "申请人id不能为空", groups = {AddGroup.class})
	private Integer applicantId;
	/**
	 * 申请人
	 */
	@NotBlank(message = "申请人名称不能为空", groups = {AddGroup.class})
	private String applicantName;
	/**
	 * 应用名
	 */
	@NotBlank(message = "应用名称不能为空", groups = {AddGroup.class})
	private String appName;
	/**
	 * 审核状态[0-未通过，1-通过]
	 */
	@NotNull(message = "状态不能为空", groups = {UpdateGroup.class})
	private Integer state;
	/**
	 * 审查人id
	 */
	@NotNull(message = "审查人id不能为空", groups = {UpdateGroup.class})
	private Integer reviewerId;
	/**
	 * 审查人
	 */
	@NotBlank(message = "审查人名称不能为空", groups = {UpdateGroup.class})
	private String reviewerName;
	/**
	 * 修改时间
	 */
	private Date modTime;
	/**
	 * 添加时间
	 */
	private Date addTime;

}
