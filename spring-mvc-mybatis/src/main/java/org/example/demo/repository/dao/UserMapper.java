package org.example.demo.repository.dao;

import org.apache.ibatis.annotations.MapKey;
import org.example.demo.repository.entity.User;

import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * key 就是列名
     * value 列名对应的值
     */
    Map queryMapById(Integer id);

    /**
     * 返回多条数据
     * id:user
     * @MapKey 告诉mybatis哪个属性为key
     */
    @MapKey(value = "id")
    Map<Integer,User> queryMaps();
}