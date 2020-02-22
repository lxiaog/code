package org.example.demo.jpa.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "jpa_dept")
@Data
@Accessors(chain = true)
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String deptName;

    /**
     * 使用@OneToOne映射一对一关联关系
     * 使用@JoinColumn映射关联字段，需要设置unique = true
     *
     */
    @JoinColumn(name = "manager_id",unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    private Manager manager;
}
