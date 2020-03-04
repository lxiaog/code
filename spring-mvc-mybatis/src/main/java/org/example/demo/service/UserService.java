package org.example.demo.service;

import org.example.demo.repository.entity.User;

public interface UserService {

    Object add(User user);

    Object delete(User user);

    Object update(User user);
}
