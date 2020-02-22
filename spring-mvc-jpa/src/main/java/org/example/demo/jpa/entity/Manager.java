package org.example.demo.jpa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "jpa_manager")
@Setter
@Getter
@Accessors(chain = true)
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String managerName;
    /**
     * 对于不维护关联关系，没有外键的一方
     * 使用@OneToOne来映射，
     * 设置mappedBy指定对方维护的属性
     */
    @OneToOne(mappedBy = "manager")
    private Dept dept;

}
