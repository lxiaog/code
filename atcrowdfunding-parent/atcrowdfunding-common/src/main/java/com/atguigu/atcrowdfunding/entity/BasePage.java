package com.atguigu.atcrowdfunding.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
class BasePage implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
}
