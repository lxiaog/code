package com.atguigu.atcrowdfunding.entity;


import java.util.Date;
import java.util.List;

public class User extends BasePage {
    private Integer id;

    private Date createDate;

    private String email;

    private String name;

    private String password;

    private Date updateDate;

    private String username;

    private List<JoinUserRole> roles;

    private List<Permission> permissions;

    private String pid = "";

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<JoinUserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<JoinUserRole> roles) {
        this.roles = roles;
    }
}