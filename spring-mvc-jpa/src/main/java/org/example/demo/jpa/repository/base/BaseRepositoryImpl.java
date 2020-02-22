package org.example.demo.jpa.repository.base;

import org.example.demo.jpa.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BaseRepositoryImpl implements BaseRepository<Person> {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Person one() {
        return entityManager.find(Person.class, 1);
    }
}
