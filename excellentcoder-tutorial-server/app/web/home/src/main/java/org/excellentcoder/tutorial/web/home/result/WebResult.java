/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.home.result;

import lombok.Data;
import lombok.ToString;
import org.excellentcoder.tutorial.common.service.facade.dto.result.TutorialResult;

/**
 * Web返回结果
 * 
 * @author xbyan
 * @version $Id: WebResult.java, v 0.1 2019-10-11 9:47 PM xbyan Exp $$
 */
@Data
@ToString(callSuper = true)
public class WebResult<T> extends TutorialResult<T> {
    /**
     * 根据返回成功与否构造响应结果
     */
    public WebResult() {
        super();
    }

    /**
     * 根据返回成功与否，及返回消息构造响应结果
     *
     * @param errorMessage 返回消息
     */
    public WebResult(String errorMessage) {
        super(errorMessage);
    }

    /**
     * 根据返回成功与否，及返回消息构造响应结果
     *
     * @param errorCode    错误码
     * @param errorMessage 返回消息
     */
    public WebResult(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    /**
     * 根据返回成功与否与T对象构造响应结果
     *
     * @param success 是否成功
     * @param t
     */
    public WebResult(boolean success, T t) {
        super(success, t);
    }
}
