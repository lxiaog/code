package com.atguigu.atcrowdfunding.exception.code;

public interface BaseCode {

    String getCode();

    BaseCode setCode(String code);

    String getMsg();

    BaseCode setMsg(String msg);

    String getClazz();

    BaseCode setClazz(String clazz);

    String getMethod();

    BaseCode setMethod(String method);

    String getField();

    BaseCode setField(String field);

    String toCodeMsg();

    String toFieldMsg();

    String toString();

}
