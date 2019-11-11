/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.home.template;

import org.excellentcoder.tutorial.common.util.enums.ResultCodeEnum;
import org.excellentcoder.tutorial.web.home.result.WebResult;

/**
 * result工具类
 *
 * @author xbyan
 * @version $Id: WebResultUtil.java, v 0.1 2019-11-06 10:11 PM xbyan Exp $$
 */
public class WebResultUtil {

    /**
     * 生成对应的web结果对象
     * 
     * @param webResult
     * @param resultCode
     * @param result
     * @param <T>
     */
    public static <T> void generateResult(WebResult<T> webResult, ResultCodeEnum resultCode,
                                          T result) {
        //内部返回结果为空时需要设置详细描述信息
        if (null == resultCode) {
            webResult.setResultCode(ResultCodeEnum.SYSTEM_ERROR.getCode());
            webResult.setResultMsg(ResultCodeEnum.SYSTEM_ERROR.getDesc());
            webResult.setSuccess(false);
            webResult.setResult(null);

            return;
        }

        //补全相关参数
        webResult.setResult(result);
        webResult.setResultCode(resultCode.getCode());
        webResult.setResultMsg(resultCode.getDesc());
        webResult.setSuccess(resultCode == ResultCodeEnum.SUCCESS);
    }
}