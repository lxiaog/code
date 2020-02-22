package org.example.demo.jpa.repository;

import org.example.demo.jpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product,Integer>,JpaSpecificationExecutor<Product> {
}
