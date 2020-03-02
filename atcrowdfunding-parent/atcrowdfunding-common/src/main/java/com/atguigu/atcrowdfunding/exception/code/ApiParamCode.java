package com.atguigu.atcrowdfunding.exception.code;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * api 接口参数异常
 * 前缀：API_
 * 开始：1000
 */
@Accessors(chain = true)
public enum ApiParamCode implements BaseCode {
    // 接口参数错误码
    PARAM_NULL("1000","参数为空"),
    PARAM_EMPTY("1001","参数为空字符"),
    PARAM_TYPE("1002","参数类型错误"),
    PARAM_ZRO("1003","参数的值为0"),
    ;

    ApiParamCode(String code, String msg) {
        this.code = "API_" + code;
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
