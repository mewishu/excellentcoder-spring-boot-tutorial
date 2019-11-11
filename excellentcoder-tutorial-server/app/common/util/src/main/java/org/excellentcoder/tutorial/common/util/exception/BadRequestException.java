/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.common.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Bad请求异常
 * 
 * @author xbyan
 * @version $Id: BadRequestException.java, v 0.1 2019-11-08 7:01 PM xbyan Exp $$
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    /**
     * 构造函数
     * 
     * @param message
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * 构造函数
     * 
     * @param message
     * @param cause
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
