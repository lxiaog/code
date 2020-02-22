package org.example.demo.service;

import org.example.demo.jpa.entity.Person;

import java.util.List;

public interface JpaService {

    Person add(String lastName);

    List<Person> geiList();

    void test();

    int update(Integer id, String email);

}
