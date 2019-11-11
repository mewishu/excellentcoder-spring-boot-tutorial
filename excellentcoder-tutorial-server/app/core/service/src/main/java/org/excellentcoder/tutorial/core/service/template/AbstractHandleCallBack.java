/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.service.template;

/**
 * 抽象操作回调接口(不带有返回值)
 * 
 * @author xbyan
 * @version $Id: AbstractHandleCallBack.java, v 0.1 2019-11-06 9:05 PM xbyan Exp $$
 */
public abstract class AbstractHandleCallBack implements HandleCallBack {
    /**
     * @see org.excellentcoder.tutorial.core.service.template.HandleCallBack#checkAndFillParams()
     */
    @Override
    public void checkAndFillParams() {

    }

    /**
     * @see org.excellentcoder.tutorial.core.service.template.HandleCallBack#handleFailure()
     */
    @Override
    public void handleFailure() {

    }
}