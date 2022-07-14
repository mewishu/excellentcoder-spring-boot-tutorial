/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.common.service.facade.dto.result;

import lombok.Data;
import lombok.ToString;

/**
 * 通用返回结果
 * 
 * @author xbyan
 * @version $Id: TutorialResult.java, v 0.1 2019-11-06 3:17 PM xbyan Exp $$
 */
@Data
@ToString(callSuper = true)
public class TutorialResult<T> extends AbstractTutorialResult {
    /** 成功 */
    private static final String SUCCESS      = "SUCCESS";

    /** 系统异常 */
    private static final String SYSTEM_ERROR = "SYSTEM_ERROR";

    /**
     * 返回结果
     */
    private T                   result;

    /**
     * 根据返回成功与否构造响应结果
     */
    public TutorialResult() {
        this.setSuccess(false);
    }

    /**
     * 根据返回成功与否，及返回消息构造响应结果
     *
     * @param errorMessage 返回消息
     */
    public TutorialResult(String errorMessage) {
        this.setSuccess(false);
        this.setResultMsg(errorMessage);
    }

    /**
     * 根据返回成功与否，及返回消息构造响应结果
     *
     * @param errorCode    错误码
     * @param errorMessage 返回消息
     */
    public TutorialResult(String errorCode, String errorMessage) {
        this.setSuccess(false);
        this.setResultCode(errorCode);
        this.setResultMsg(errorMessage);
    }

    /**
     * 根据返回成功与否与T对象构造响应结果
     *
     * @param isSuccess 是否成功
     * @param t
     */
    public TutorialResult(boolean isSuccess, T t) {
        this.setSuccess(isSuccess);
        this.setResult(t);
    }

    /**
     * 成功返回值
     * 
     * @param data
     * @param <T>
     * @return
     */
    public static <T> TutorialResult<T> success(T data) {
        TutorialResult<T> result = new TutorialResult<T>();
        result.setSuccess(true);
        result.setResult(data);
        result.setResultCode(SUCCESS);
        return result;
    }

    /**
     * 成功返回值
     * 
     * @param resultCode
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> TutorialResult<T> success(String resultCode, String message, T data) {
        TutorialResult<T> result = new TutorialResult<T>();
        result.setSuccess(true);
        result.setResult(data);
        result.setResultCode(resultCode);
        result.setResultMsg(message);
        return result;
    }

    /**
     * 失败返回结果
     * 
     * @return
     */
    public static TutorialResult<String> fail() {
        return fail(SYSTEM_ERROR);
    }

    public static TutorialResult<String> fail(String code) {
        return fail(code, null);
    }

    public static <T> TutorialResult<T> fail(String code, T data) {
        return fail(code, "操作失败", data);
    }

    /**
     * 失败返回结果
     * 
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> TutorialResult<T> fail(String code, String message, T data) {
        TutorialResult<T> result = new TutorialResult<T>();
        result.setSuccess(false);
        result.setResult(data);
        result.setResultCode(code);
        result.setResultMsg(message);
        return result;
    }

}