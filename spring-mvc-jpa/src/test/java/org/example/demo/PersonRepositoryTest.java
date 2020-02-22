package org.example.demo;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.jpa.entity.Address;
import org.example.demo.jpa.entity.Order;
import org.example.demo.jpa.entity.Person;
import org.example.demo.jpa.repository.AddressRepository;
import org.example.demo.jpa.repository.OrderRepository;
import org.example.demo.jpa.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:/spring/spring-data-source.xml",
        "classpath:/spring/spring-jpa.xml",
        "classpath:/spring/spring-mvc.xml",
        "classpath:/spring/spring-mybatis.xml"

})
@WebAppConfiguration
@Slf4j
public class PersonRepositoryTest {

//    @Autowired
//    private WebApplicationContext context;

    //模拟mvc环境
//    private MockMvc mvc;

    @Before
    public void before() {
//        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testOneToMany(){
        Person person = new Person();
        person.setBirth(new Date());
        person.setEmail("onoToMany@jpa.com");
        person.setLastName("onoToMany");
        Address address = addressRepository.getOne(1);
        person.setAddress(address);

        Order order = new Order();
        order.setPrice(10000)
                .setOrderName("电脑")
                .setCreateDate(new Date())
                .setUpdateDate(new Date());

        Order order1 = new Order();
        order1.setPrice(5000)
                .setOrderName("手机")
                .setUpdateDate(new Date())
                .setCreateDate(new Date());

        Set<Order> set = new HashSet<>();
        set.add(order);
        set.add(order1);
        person.setOrders(set);

        orderRepository.save(order);
        orderRepository.save(order1);

        personRepository.save(person);


    }

    /**
     * 保存多对一时, 建议先保存one的一端,
     * 后保存 n 的一端,
     * 这样不会多出额外的 UPDATE 语句.
     */
    @Test
    public void personAdd() {
//        String lastName = "aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,nn,oo,pp,xx";
//        Address address = new Address();
//        address.setProvince("甘肃").setCity("兰州");
//        addressRepository.save(address);
//        String[] names = lastName.split(",");
//        for (int i = 0; i < names.length; i++) {
//            Person person = new Person();
//            person.setBirth(new Date());
//            person.setEmail(names[i].toLowerCase() + "@jpa.com");
//            person.setLastName(names[i].toUpperCase());
//            person.setAddress(address);
//            personRepository.save(person);
//        }
        List<Person> personList = new ArrayList<>();
        List<Address> addressList = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; i++) {
            Person person = new Person();
            person.setBirth(new Date());
            person.setEmail((char) i + "" + (char) i + "@jpa.com");
            person.setLastName((char) i + "" + (char) i);
            Address address = new Address();
            if (i % 2 == 0) {
                address.setProvince("甘肃-" + i).setCity("兰州-" + i);
            } else {
                address.setProvince("四川-" + i).setCity("成都-" + i);
            }
            addressList.add(address);
            person.setAddress(address);
            personList.add(person);
        }
        addressRepository.saveAll(addressList);
        personRepository.saveAll(personList);
    }

    @Test
    public void personList() {
        List<Person> list = personRepository.findAll();

        log.error(JSON.toJSONString(list));
    }

    @Test
    public void personTest() {
        log.info("" + personRepository);
//        personRepository.queryByLastName("mm");
        List<Person> list = personRepository.queryByAddressIdGreaterThan(10);
        log.info(JSON.toJSONString(list));
        List<Person> _list = personRepository.queryByAddress_IdGreaterThan(10);
        log.error(JSON.toJSONString(_list));

    }

    @Test
    public void personQuery() {
        Person person = personRepository.queryMaxIdPerson();
        log.info(person.toString());
//        List<Person> list = personRepository.queryLastNameAndEmail("DD", "dd@jpa.com");
        List<Person> list = personRepository.findLastNameAndEmail("dd@jpa.com", "DD");
        log.info(list.toString());
    }

    @Test
    public void personQueryLike() {
        List<Person> list = personRepository.testQueryLikeParam("%D%", "%b%");
        List<Person> list1 = personRepository.testQueryLikeParam1("D", "b");
        List<Person> list2 = personRepository.testQueryLikeParam2("d", "C");
        log.info(list.toString());
        log.info(list1.toString());
        log.info(list2.toString());
    }

    @Test
    public void personNativeQuery() {
        long count = personRepository.getTotalCount();
        log.error("" + count);
    }

    @Test
    public void personPage() {
        int page = 1;
        int size = 10;
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = Sort.by(order);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Person> personList = personRepository.findAll(pageRequest);
        log.error(JSON.toJSONString(personList));
    }

    @Test
    public void personPageWhere() {
        int page = 1;
        int size = 10;
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = Sort.by(order);
        PageRequest pageRequest = PageRequest.of(page, size);
        Specification<Person> specification = new Specification<Person>() {
            /**
             *
             * @param root 代表查询的实体类
             * @param criteriaQuery 添加查询条件
             * @param criteriaBuilder 创建criteria工厂对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.gt(root.get("id"), 5);
                return predicate;
            }
        };
        Page<Person> personList = personRepository.findAll(specification, pageRequest);
        log.error(JSON.toJSONString(personList));
    }


    @Test
    public void test() {
        Person person = personRepository.one();
        log.error(person.toString());
    }


}
