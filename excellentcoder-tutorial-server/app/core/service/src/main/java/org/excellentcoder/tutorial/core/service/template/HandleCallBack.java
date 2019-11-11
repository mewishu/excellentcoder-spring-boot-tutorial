/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.service.template;

/**
 * 操作回调接口(不带有返回值)
 * 
 * @author xbyan
 * @version $Id: HandleCallBack.java, v 0.1 2019-11-06 9:04 PM xbyan Exp $$
 */
public interface HandleCallBack {
    /**
     *  检查及补全参数
     */
    void checkAndFillParams();

    /**
     * 执行待处理操作，比如模型的创建，修改，删除等
     */
    void process();

    /** 失败处理 */
    void handleFailure();
}