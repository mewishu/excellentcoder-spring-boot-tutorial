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
    /**
     * 返回结果
     */
    private T result;

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
}