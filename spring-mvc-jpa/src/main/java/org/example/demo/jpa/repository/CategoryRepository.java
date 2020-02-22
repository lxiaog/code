package org.example.demo.jpa.repository;

import org.example.demo.jpa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category,Integer>,JpaSpecificationExecutor<Category> {
}
