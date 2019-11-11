/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.common.service.facade.dto.result;

import lombok.Getter;
import lombok.Setter;

/**
 * 抽象通用结果类
 * 
 * @author xbyan
 * @version $Id: AbstractTutorialResult.java, v 0.1 2019-11-06 3:29 PM xbyan Exp $$
 */
@Getter
@Setter
public abstract class AbstractTutorialResult {
    /**
     * 是否成功
     */
    protected boolean success;

    /**
     * 结果码
     */
    protected String  resultCode;

    /**
     * 结果描述
     */
    protected String  resultMsg;

    /**
     * 外围系统错误码
     */
    protected String  outResultCode;

}