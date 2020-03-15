package com.atguigu.atcrowdfunding.bean;

import com.atguigu.atcrowdfunding.exception.BusinessException;
import com.atguigu.atcrowdfunding.exception.code.BaseCode;

/**
 * 判断条件失败之后抛出异常
 * @author Work
 */
public final class Preconditions {
    /**
     * 判断对象只判断对象是否为null
     */
    public static void isNotNull(Object reference, BaseCode code) {
        if (reference == null) {
            throw new BusinessException(code);
        }
    }

    /**
     * 判断字符串是否为null可空字符
     */
    public static void isNotNullString(String reference, BaseCode code) {
        if (reference == null || reference.isEmpty()) {
            throw new BusinessException(code);
        }
    }

    /**
     * 条件类型判断：如1==1
     */
    public static void isExpression(boolean expression, BaseCode code) {
        if (!expression) {
            throw new BusinessException(code);
        }
    }

    /**
     * Boolean类型判断
     * state可以为null
     */
    public static void isState(Boolean state, BaseCode code) {
        if (state == null || !state) {
            throw new BusinessException(code);
        }
    }
}
