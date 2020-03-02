package com.atguigu.atcrowdfunding.exception.code;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * dao层异常码
 */
@Accessors(chain = true)
public enum DaoCode implements BaseCode {
    INSERT_FAIL("1001", "添加失败"),
    DELETE_FAIL("1002", "删除失败"),
    UPDATE_FAIL("1003", "更新失败"),
    SELECT_FAIL("1004", "查询失败"),
    INSERT_BATCH_FAIL("1005", "批量添加失败"),
    DELETE_BATCH_FAIL("1006", "批量删除失败"),
    UPDATE_BATCH_FAIL("1007", "批量更新失败");

    DaoCode(String code, String msg) {
        this.code = "SQL_" + code;
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
