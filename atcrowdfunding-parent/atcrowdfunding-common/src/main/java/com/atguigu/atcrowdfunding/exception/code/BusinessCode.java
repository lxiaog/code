package com.atguigu.atcrowdfunding.exception.code;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 业务层异常码
 * 开头BUSINESS_数字 1000开始
 */
@Accessors(chain = true)
public enum BusinessCode implements BaseCode{
    // 接口参数错误码
    //user 登陆模块
    USER_USERNAME_NON("10100", "用户名不存在"),
    USER_PASSWORD_WRONG("10102", "密码错误"),
    USER_BATCH_DELETE_FAIL("10203", "批量删除用户失败"),
    //role模块
    ROLE_BATCH_DELETE_FAIL("10200", "批量删除角色失败"),
    ;


    BusinessCode(String code, String msg) {
        this.code = "BUSINESS_"+code;
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
