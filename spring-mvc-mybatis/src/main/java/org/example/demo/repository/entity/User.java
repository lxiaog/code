package org.example.demo.repository.entity;

import java.util.Date;
import java.util.List;

public class User extends BasePage{
    private Integer id;

    private Date createDate;

    private String email;

    private String name;

    private String password;

    private Date updateDate;

    private String username;

    private List<JoinUserRole> joinUserRoles;

    public List<JoinUserRole> getJoinUserRoles() {
        return joinUserRoles;
    }

    public void setJoinUserRoles(List<JoinUserRole> joinUserRoles) {
        this.joinUserRoles = joinUserRoles;
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
        this.email = email == null ? null : email.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
        this.username = username == null ? null : username.trim();
    }
}