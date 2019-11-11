/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.service.template;

/**
 * 抽象操作回调接口(带有返回值)
 * 
 * @author xbyan
 * @version $Id: AbstractHandleCallBackWithResult.java, v 0.1 2019-10-11 9:10 PM xbyan Exp $$
 */
public abstract class AbstractHandleCallBackWithResult<T> implements HandleCallBackWithResult<T> {
    /**
     * @see org.excellentcoder.tutorial.core.service.template.HandleCallBackWithResult#checkAndFillParams()
     */
    @Override
    public void checkAndFillParams() {

    }

    /**
     * @see org.excellentcoder.tutorial.core.service.template.HandleCallBackWithResult#handleFailure()
     */
    @Override
    public void handleFailure() {

    }
}