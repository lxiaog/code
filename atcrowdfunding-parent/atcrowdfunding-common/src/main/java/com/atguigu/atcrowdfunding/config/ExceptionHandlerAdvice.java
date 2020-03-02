package com.atguigu.atcrowdfunding.config;

import com.atguigu.atcrowdfunding.bean.rest.RestResult;
import com.atguigu.atcrowdfunding.exception.ApiParamException;
import com.atguigu.atcrowdfunding.exception.BusinessException;
import com.atguigu.atcrowdfunding.exception.DaoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 接口全局异常拦截处理
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = ApiParamException.class)
    public RestResult errorHandler(ApiParamException e) {
        log.warn("业务错误-" + e.getCode().toString());
        return RestResult.fail(e.getCode()).setMsg(e.getCode().toFieldMsg());
    }

    @ExceptionHandler(value = BusinessException.class)
    public RestResult errorHandler(BusinessException e) {
        log.warn("业务错误-" + e.getCode().toString());
        return RestResult.fail(e.getCode()).setMsg(e.getCode().toCodeMsg());
    }

    @ExceptionHandler(value = DaoException.class)
    public RestResult errorHandler(DaoException e) {
        log.warn("SQL错误-" + e.getCode().toString());
        return RestResult.fail(e.getCode()).setMsg(e.getCode().toCodeMsg());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public RestResult errorHandler(RuntimeException e) {
        log.warn("运行异常:" + e.getMessage());
        return RestResult.error().setMsg(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public RestResult errorHandler(Exception e) {
        log.warn("系统异常:" + e.getMessage());
        return RestResult.error().setMsg(e.getMessage());
    }

    @ExceptionHandler(value = Throwable.class)
    public RestResult errorHandler(Throwable e) {
        log.warn("系统异常:" + e.getMessage());
        return RestResult.error().setMsg(e.getMessage());
    }
}
