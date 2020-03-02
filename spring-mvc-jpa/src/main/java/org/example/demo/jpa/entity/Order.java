package org.example.demo.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "jpa_order")
@Setter
@Getter
@Accessors(chain = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer price;
    private String orderName;
    private Date createDate;
    private Date updateDate;

    @JoinColumn(name = "person_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;
}
