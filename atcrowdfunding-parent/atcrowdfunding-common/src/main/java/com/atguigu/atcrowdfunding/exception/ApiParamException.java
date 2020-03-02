package com.atguigu.atcrowdfunding.exception;

import com.atguigu.atcrowdfunding.exception.code.ApiParamCode;
import com.atguigu.atcrowdfunding.exception.code.BaseCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * 接口参数异常
 */
@Setter
@Getter
@Accessors(chain = true)
@Slf4j
public class ApiParamException extends RuntimeException {

    private BaseCode code;

    public ApiParamException(BaseCode code) {
        super(code.toString());
        this.code = code;
    }

}
