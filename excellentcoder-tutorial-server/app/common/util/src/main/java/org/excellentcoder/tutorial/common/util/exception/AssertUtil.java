/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package org.excellentcoder.tutorial.common.util.exception;

import org.apache.commons.lang3.StringUtils;
import org.excellentcoder.tutorial.common.util.ResultCode;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 参数检查断言工具类，可以减少一些<code>if</code>代码逻辑<br>
 * 当断言不成立时，抛出指定错误代码的ApiException异常
 *  
 * @author xbyan
 * @version : AssertUtil.java, v 0.1 2022-07-14 19:31 xbyan Exp $
 */
public class AssertUtil {
    /**
     * 禁用构造函数
     */
    private AssertUtil() {
        // 禁用构造函数
    }

    /**
     * 断言对象为null，否则抛出传入的异常。
     *
     * @param object   断言对象
     * @param resutlCode
     */
    public static void isNull(Object object, ResultCode resutlCode) {
        if (object != null) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 断言对象为null，否则抛出传入的异常。
     *
     * @param object   断言对象
     * @param resutlCode
     */
    public static void isNull(Object object, ResultCode resutlCode, String message) {
        if (object != null) {
            throw new ApiException(resutlCode, message);
        }
    }

    /**
     * 期待对象为非空，如果检查的对象为<code>null</code>，抛出异常<code>ApiException</code>
     * @param object
     * @param resutlCode
     * @throws ApiException
     */
    public static void isNotNull(Object object, ResultCode resutlCode) throws ApiException {
        if (object == null) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 期待对象为非空，如果检查的对象为<code>null</code>，抛出异常<code>ApiException</code>
     * @param object
     * @param resutlCode
     * @param message
     * @throws ApiException
     */
    public static void isNotNull(Object object, ResultCode resutlCode, String message) {
        if (object == null) {
            throw new ApiException(resutlCode, message);
        }
    }

    /**
     * 期待字符串为非空，如果检查字符串是空白：<code>null</code>、空字符串""或只有空白字符，抛出异常<code>ApiException</code>
     *
     * @param text 待检查的字符串
     * @param resutlCode 异常代码
     * @throws ApiException
     */
    public static void isNotBlank(String text, ResultCode resutlCode) throws ApiException {
        if (StringUtils.isBlank(text)) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 期待字符串为非空，如果检查字符串是空白：<code>null</code>、空字符串""或只有空白字符，抛出异常<code>ApiException</code>
     *
     * @param text 待检查的字符串
     * @param resutlCode 异常代码
     * @param message 错误描述
     * @throws ApiException
     */
    public static void isNotBlank(String text, ResultCode resutlCode, String message) {
        if (StringUtils.isBlank(text)) {
            throw new ApiException(resutlCode, message);
        }
    }

    /**
     * 期待集合对象为空，否则抛出异常<code>ApiException</code>
     *
     * @param collection 集合对象
     * @param resutlCode 异常代码
     * @throws ApiException
     */
    public static void isEmpty(Collection<?> collection,
                               ResultCode resutlCode) throws ApiException {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 期待集合对象为空，否则抛出异常<code>ApiException</code>
     *
     * @param collection 集合对象
     * @param resutlCode 异常代码
     * @param message    错误描述
     * @throws ApiException
     */
    public static void isEmpty(Collection<?> collection, ResultCode resutlCode, String message) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new ApiException(resutlCode, message);
        }
    }

    /**
     * 期待集合对象为非空，如果检查集合对象是否为null或者空数据，抛出异常<code>ApiException</code>
     *
     * @param collection 集合对象
     * @param resutlCode 异常代码
     * @throws ApiException
     */
    public static void notEmpty(Collection<?> collection,
                                ResultCode resutlCode) throws ApiException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 期待集合对象为非空，如果检查集合对象是否为null或者空数据，抛出异常<code>ApiException</code>
     *
     * @param collection 集合对象
     * @param resutlCode 异常代码
     * @param message    错误描述
     * @throws ApiException
     */
    public static void notEmpty(Collection<?> collection, ResultCode resutlCode, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ApiException(resutlCode, message);
        }
    }

    /**
     * 期待集合对象包含特定的key值
     *
     * @param cols
     * @param key
     * @param resutlCode
     */
    public static <T> void assertContains(Collection<T> cols, T key, ResultCode resutlCode,
                                          String message) {
        if (!cols.contains(key)) {
            throw new ApiException(resutlCode, message);
        }
    }

    /**
     * 期待集合对象包含特定的key值
     *
     * @param cols
     * @param key
     * @param resutlCode
     */
    public static <T> void assertContains(Collection<T> cols, T key, ResultCode resutlCode) {
        if (!cols.contains(key)) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 如果数组为空或者数组里的元素为null,则抛出异常
     *
     * @param resutlCode
     * @param param
     */
    public static void isNotNull(ResultCode resutlCode, final String errorMsg, Object... param) {
        //如果要检验的数组为空,则
        if (null == param || param.length == 0) {
            isTrue(false, resutlCode, errorMsg);
        } else {
            for (Object obj : param) {
                if (null == obj) {
                    isTrue(false, resutlCode, errorMsg);
                } else if (obj instanceof String) {
                    isTrue(StringUtils.isNotBlank((String) obj), resutlCode, errorMsg);
                } else if (obj instanceof Collection) {
                    isTrue(!CollectionUtils.isEmpty((Collection<?>) obj), resutlCode, errorMsg);
                }
            }
        }
    }

    /**
     * 期待的正确值为true，如果实际为false，抛出异常<code>ApiException</code>
     *
     * @param expression
     * @param resutlCode 异常代码
     * @throws ApiException
     */
    public static void isTrue(boolean expression, ResultCode resutlCode) throws ApiException {
        if (!expression) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 期待的正确值为true，如果实际为false，抛出异常<code>ApiException</code>
     *
     * @param expression
     * @param resutlCode 异常代码
     * @throws ApiException
     */
    public static void isTrue(boolean expression, ResultCode resutlCode,
                              String message) throws ApiException {
        if (!expression) {
            throw new ApiException(resutlCode, message);
        }
    }

    /**
     * 期待的正确值为false，如果实际为true，抛出异常<code>ApiException</code>
     *
     * @param expression
     * @param resutlCode 异常代码
     * @param message    错误描述
     * @throws ApiException
     */
    public static void isFalse(boolean expression, ResultCode resutlCode,
                               String message) throws ApiException {
        if (expression) {
            throw new ApiException(resutlCode, message);
        }
    }

    /**
     * 期待的正确值为false，如果实际为true，抛出异常<code>ApiException</code>
     *
     * @param expression
     * @param resutlCode 异常代码
     * @throws ApiException
     */
    public static void isFalse(boolean expression, ResultCode resutlCode) throws ApiException {
        if (expression) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 断言两个对象相等，否则抛出传入的异常。
     *
     * @param obj1  对象1
     * @param obj2  对象2
     * @param resutlCode 异常代码
     */
    public static void objectEquals(Object obj1, Object obj2, ResultCode resutlCode) {
        if (obj1 == null) {
            isNull(obj2, resutlCode);
            return;
        }
        if (!obj1.equals(obj2)) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 断言两个对象不等，否则抛出传入的异常。
     *
     * @param obj1  对象1
     * @param obj2  对象2
     * @param resutlCode 异常代码
     */
    public static void objectNotEquals(Object obj1, Object obj2, ResultCode resutlCode) {
        if (obj1 == null) {
            isNotNull(obj2, resutlCode);
            return;
        }

        if (obj1.equals(obj2)) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 期待的字符串相等，如果实际为不等，抛出异常<code>ApiException</code>
     *
     * @param text 原字符串
     * @param text2 目的字符串
     * @exception ApiException
     */
    public static void equal(String text, String text2, ResultCode resutlCode) {
        if (!StringUtils.equals(text, text2)) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 期待的字符串不等，如果实际为相等，抛出异常<code>ApiException</code>
     *
     * @param text 原字符串
     * @param text2 目的字符串
     * @exception ApiException
     */
    public static void notEqual(String text, String text2, ResultCode resutlCode) {
        if (StringUtils.equals(text, text2)) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 期待的字符串最大长度为len，如果字符串超出了此长度，抛出异常<code>ApiException</code>
     * @param text 字符串
     * @param len 最大长度值
     * @param resutlCode
     * @exception ApiException
     */
    public static void maxLength(String text, int len, ResultCode resutlCode) {
        if (StringUtils.isBlank(text)) {
            return;
        }
        if (text.length() > len) {
            throw new ApiException(resutlCode);
        }
    }

    /**
     * 最小值断言
     *
     * @param value     值
     * @param minValue 最小值
     * @param resultCode 错误描述
     */
    public static void assertMin(int value, int minValue, ResultCode resultCode, String message) {
        if (value < minValue) {
            throw new ApiException(resultCode, message);
        }
    }

    /**
     * 最小值断言
     *
     * @param value     值
     * @param minValue 最小值
     * @param resultCode 错误描述
     */
    public static void assertMin(int value, int minValue, ResultCode resultCode) {
        if (value < minValue) {
            throw new ApiException(resultCode);
        }
    }

    /**
     * 最大值断言
     *
     * @param value 值
     * @param maxValue 最大值
     * @param resultCode 错误码
     */
    public static void assertMax(int value, int maxValue, ResultCode resultCode, String message) {
        if (value > maxValue) {
            throw new ApiException(resultCode, message);
        }
    }

    /**
     * 最大值断言
     *
     * @param value 值
     * @param maxValue 最大值
     * @param resultCode 错误码
     */
    public static void assertMax(int value, int maxValue, ResultCode resultCode) {
        if (value > maxValue) {
            throw new ApiException(resultCode);
        }
    }

    /**
     * 最小值断言
     *
     * @param value     值
     * @param minValue 最小值
     * @param resultCode 错误描述
     */
    public static void assertMin(long value, long minValue, ResultCode resultCode, String message) {
        if (value < minValue) {
            throw new ApiException(resultCode, message);
        }
    }

    /**
     * 最小值断言
     *
     * @param value     值
     * @param minValue 最小值
     * @param resultCode 错误描述
     */
    public static void assertMin(long value, long minValue, ResultCode resultCode) {
        if (value < minValue) {
            throw new ApiException(resultCode);
        }
    }

    /**
     * 最大值断言
     *
     * @param value 值
     * @param maxValue 最大值
     * @param resultCode 错误码
     */
    public static void assertMax(long value, long maxValue, ResultCode resultCode, String message) {
        if (value > maxValue) {
            throw new ApiException(resultCode, message);
        }
    }

    /**
     * 最大值断言
     *
     * @param value 值
     * @param maxValue 最大值
     * @param resultCode 错误码
     */
    public static void assertMax(long value, long maxValue, ResultCode resultCode) {
        if (value > maxValue) {
            throw new ApiException(resultCode);
        }
    }

    /**
     * 判断参数是否全不为空.
     * @param args 参数列表
     * @return
     */
    public static boolean allNotBlank(String... args) {
        if (null == args) {
            return false;
        }
        for (String str : args) {
            if (StringUtils.isBlank(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查请求参数字符串不允许同时为空
     *
     * @param resutlCode
     * @param params
     */
    public static void notAllBlank(ResultCode resutlCode, final String... params) {
        if (null == params) {
            return;
        }

        for (final String param : params) {
            if (StringUtils.isNotBlank(param)) {
                return;
            }
        }

        throw new ApiException(resutlCode);
    }
}
