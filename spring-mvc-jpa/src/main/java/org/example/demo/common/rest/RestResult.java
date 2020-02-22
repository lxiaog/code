package org.example.demo.common.rest;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public final class RestResult {

    private String code;
    /**
     * 0 表示成功
     * 1 失败
     * 2 异常
     * 3 超时
     * 4
     */
    private Integer status;
    private Object data;
    private String mag;

    public static RestResult result() {
        return new RestResult();
    }

    public static RestResult success() {

        return result().setCode("0000").setStatus(0);
    }

    public static RestResult fail() {
        return result().setCode("404").setStatus(1);
    }

    public static RestResult error() {
        return result().setStatus(2).setCode("444");
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
}
