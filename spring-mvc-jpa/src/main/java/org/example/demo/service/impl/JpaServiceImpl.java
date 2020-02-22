package org.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.jpa.entity.Person;
import org.example.demo.jpa.repository.PersonRepository;
import org.example.demo.service.JpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class JpaServiceImpl implements JpaService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person add(String lastName) {
        Person person = new Person();
        person.setBirth(new Date());
        person.setEmail(lastName.toLowerCase() + "@jpa.com");
        person.setLastName(lastName.toUpperCase());
        return personRepository.save(person);
    }

    @Override
    public List<Person> geiList() {
        return personRepository.findAll();
    }

    @Override
    public void test() {
//        log.info(JSON.toJSONString(personRepository.queryByLastName("mm")));
//        log.info(JSON.toJSONString(personRepository.queryByLastNameStartingWithAndIdLessThan("g", 15)));
//        log.info(JSON.toJSONString(personRepository.queryByLastNameEndingWithAndIdGreaterThan("g", 15)));
//        List<String> list = new ArrayList<>();
//        list.add("mm@jpa.com");
//        list.add("aa@jpa.com");
//        list.add("bb@jpa.com");
//        list.add("cc@jpa.com");
//        log.info(JSON.toJSONString(personRepository.queryByEmailInOrBirthLessThan(list)));

        List<Person> list = personRepository.queryByAddressIdGreaterThan(10);

    }

    @Transactional
    public int update(Integer id,String email){
       return personRepository.updateEmailById(id, email);
    }
}
