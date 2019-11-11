/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.service.template;

/**
 * 操作回调接口(带有返回值)
 *
 * @author xbyan
 * @version $Id: HandleCallBackWithResult.java, v 0.1 2019-10-11 9:09 PM xbyan Exp $$
 */
public interface HandleCallBackWithResult<T> {
    /**
     *  检查及补全参数
     */
    void checkAndFillParams();

    /**
     * 执行待处理操作，比如模型的创建，修改，删除等
     */
    T process();

    /** 失败处理 */
    void handleFailure();
}