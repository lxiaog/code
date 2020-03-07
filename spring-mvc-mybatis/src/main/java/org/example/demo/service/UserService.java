package org.example.demo.service;

import org.example.demo.repository.entity.JoinUserRole;
import org.example.demo.repository.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Object add(User user);

    Object delete(User user);

    Object update(User user);

    Map queryMapById(Integer id);

    Map<Integer, User> queryMaps();

    List<JoinUserRole> queryAssociationRole();

    List<JoinUserRole> querySwitchAssociationRole();

    User queryById(Integer id);
}
