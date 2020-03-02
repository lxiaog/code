package com.atguigu.atcrowdfunding.bean.rest;

import com.atguigu.atcrowdfunding.exception.code.BaseCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Setter
@Getter
@Accessors(chain = true)
public final class RestResult {
    private String code;
    private String msg;
    private Object data;


    private RestResult() {
    }

    private static RestResult result() {
        return new RestResult();
    }

    private static RestResult result(BaseCode code) {
        return result().setCode(code.getCode()).setMsg(code.getMsg());
    }

    public static RestResult success() {
        return result(RestCode.SUCCESS);
    }


    public static RestResult fail(BaseCode code) {
        return result(code);
    }

    public static RestResult fail() {
        return fail(RestCode.FAIL);
    }


    public static RestResult error(BaseCode code) {
        return result(code);
    }

    public static RestResult error() {
        return error(RestCode.ERROR);
    }


    public static RestResult timeout(BaseCode code) {
        return result(code);
    }

    public static RestResult timeout() {
        return timeout(RestCode.TIMEOUT);
    }


}
