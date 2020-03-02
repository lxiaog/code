package org.example.demo.jpa.repository;

import org.example.demo.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {

    @Query(value = "select o from Order o where o.person in (select p from Person p where p.lastName='yy')")
    List<Order> testSubSelect();

    @Query(value = "select count(o.id) from Order o")
    int testCount();
}
