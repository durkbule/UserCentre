package com.lyt.usercentre.webapi.VO;


import java.io.Serializable;
import java.util.Date;

public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;


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
     * 邮箱
     */
    private String email;
    /**
     * 所属应用
     */
    private String appId;
    /**
     * 注册时间
     */
    private Date addTime;
    /**
     * 是否可用[0-可用，1-不可用]
     */
    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
