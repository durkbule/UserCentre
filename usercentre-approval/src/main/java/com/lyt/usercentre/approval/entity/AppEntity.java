package com.lyt.usercentre.approval.entity;

import java.io.Serializable;

public class AppEntity implements Serializable {

    private static final long serialVersionUID = 1L;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRedirectRul() {
        return redirectRul;
    }

    public void setRedirectRul(String redirectRul) {
        this.redirectRul = redirectRul;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
