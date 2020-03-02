package com.atguigu.atcrowdfunding.exception;

import com.atguigu.atcrowdfunding.exception.code.BaseCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BusinessException extends RuntimeException {

    private BaseCode code;

    public BusinessException(BaseCode code) {
        super(code.toString());
        this.code = code;
    }


}
