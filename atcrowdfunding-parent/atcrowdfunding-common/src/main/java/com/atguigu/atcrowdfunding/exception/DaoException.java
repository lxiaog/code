package com.atguigu.atcrowdfunding.exception;

import com.atguigu.atcrowdfunding.exception.code.BaseCode;
import com.atguigu.atcrowdfunding.exception.code.DaoCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 数据库层异常
 */
@Setter
@Getter
public class DaoException extends RuntimeException {

    private BaseCode code;

    public DaoException(BaseCode code) {
        super(code.getMsg());
        this.code = code;
    }


}
