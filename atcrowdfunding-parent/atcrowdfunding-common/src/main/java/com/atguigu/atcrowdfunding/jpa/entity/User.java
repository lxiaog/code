package com.atguigu.atcrowdfunding.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String username;
    private String password;
    private Date createDate;
    private Date updateDate;
}
