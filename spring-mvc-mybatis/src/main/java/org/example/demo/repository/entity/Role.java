package org.example.demo.repository.entity;

import java.util.Date;
import java.util.List;

public class Role extends BasePage{
    private Integer id;

    private String roleName;

    private Date createDate;

    private Date updateDate;

    List<JoinPermissionRole> joinPermissionRoles;

    public List<JoinPermissionRole> getJoinPermissionRoles() {
        return joinPermissionRoles;
    }

    public void setJoinPermissionRoles(List<JoinPermissionRole> joinPermissionRoles) {
        this.joinPermissionRoles = joinPermissionRoles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}