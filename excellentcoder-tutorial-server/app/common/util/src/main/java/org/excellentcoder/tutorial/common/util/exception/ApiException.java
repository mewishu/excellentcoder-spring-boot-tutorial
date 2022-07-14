/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.common.util.exception;

import lombok.Getter;
import lombok.Setter;
import org.excellentcoder.tutorial.common.util.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Api异常处理类
 * 
 * @author xbyan
 * @version $Id: ApiException.java, v 0.1 2019-10-11 10:09 PM xbyan Exp $$
 */
@Getter
@Setter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiException extends RuntimeException {
    /** 错误码. */
    private String errorCode;

    /** 错误信息 */
    private String errorMsg;

    /**
     * 构造函数
     *
     * @param resultCode 错误码枚举
     */
    public ApiException(ResultCode resultCode) {
        this.errorCode = resultCode.getCode();
        this.errorMsg = resultCode.getDesc();
    }

    /**
     * 创建一个<code>BeyonddomainException</code>
     *
     * @param resultCode 结果码
     * @param message    错误描述
     */
    public ApiException(ResultCode resultCode, String message) {
        super(message);

        this.errorCode = resultCode.getCode();
        this.errorMsg = message;
    }

    /**
     * 构造函数.
     * @param resultCode 错误枚举
     */
    public ApiException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getCode(), cause);

        this.errorCode = resultCode.getCode();
        this.errorMsg = resultCode.getDesc();
    }

    /**
     * 默认构造器.
     */
    public ApiException(String message) {
        super(message);
    }

    /**
     * 构造函数.
     * @param code 错误枚举
     * @param errorMsg  错误描述
     */
    public ApiException(String code, String errorMsg) {
        super(errorMsg);

        this.errorCode = code;
        this.errorMsg = errorMsg;
    }

    /**
     * 构造函数
     * @param code
     * @param errorMsg
     */
    public ApiException(String code, String errorMsg, Throwable cause) {
        super(errorMsg, cause);

        this.errorCode = code;
        this.errorMsg = errorMsg;
    }

}