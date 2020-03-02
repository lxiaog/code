package com.atguigu.atcrowdfunding.dao;

import com.atguigu.atcrowdfunding.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    Set<String> queryUrlList();

    List<Permission> queryListByPid(String pid);

    List<Permission> queryListChecked(Map map);

    List<Permission> queryListByUserId(Map map);

}