package com.atguigu.atcrowdfunding.bean.rest;

import com.atguigu.atcrowdfunding.exception.code.BaseCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Accessors(chain = true)
public enum RestCode implements BaseCode {
    //系统模块
    SUCCESS("0","操作成功"),

    FAIL("1","操作失败"),

    ERROR("2","操作异常"),

    TIMEOUT("3","操作超时"),;

    RestCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String msg;
    @Setter
    @Getter
    private String clazz;
    @Setter
    @Getter
    private String method;
    @Setter
    @Getter
    private String field;


    public String toCodeMsg() {
        return "[" + code + "]" + msg;
    }

    public String toFieldMsg() {
        return "[" + field + "]" + msg;
    }

    @Override
    public String toString() {
        return "ApiParamCode{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", class='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", field='" + field + '\'' +
                '}';
    }
}
