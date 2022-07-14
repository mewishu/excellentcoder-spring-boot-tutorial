/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package org.excellentcoder.tutorial.common.util;

/**
 * 通用结果码接口，可以被枚举实现，返回结果码和结果描述
 * 
 * @author xbyan
 * @version : ResultCode.java, v 0.1 2022-07-14 19:25 xbyan Exp $
 */
public interface ResultCode {
    /**
     * 获取结果码
     *
     * @return
     */
    String getCode();

    /**
     * 获取结果描述
     *
     * @return
     */
    String getDesc();
}
