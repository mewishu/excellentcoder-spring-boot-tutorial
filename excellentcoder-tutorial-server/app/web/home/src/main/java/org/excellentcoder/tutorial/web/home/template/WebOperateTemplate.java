/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.home.template;

import org.excellentcoder.tutorial.common.service.facade.dto.result.AbstractTutorialResult;
import org.excellentcoder.tutorial.common.util.enums.ResultCodeEnum;
import org.excellentcoder.tutorial.common.util.exception.ApiException;
import org.excellentcoder.tutorial.common.util.logger.LoggerUtil;
import org.excellentcoder.tutorial.core.service.template.HandleCallBack;

import org.slf4j.Logger;

import java.text.MessageFormat;

/**
 * Web通用操作类模板
 *
 *  <p>
 *      该模板主要用于规范并统一业务逻辑，通过利用模板可以统一补获到异常，并统一补全结果信息
 *  </p>
 *
 * @author xbyan
 * @version $Id: WebOperateTemplate.java, v 0.1 2019-10-11 9:04 PM xbyan Exp $$
 */
public class WebOperateTemplate {

    /**
     * 模板处理流程
     *
     * <br> 1.参数校验及补全
     * <br> 2.执行具体业务操作
     * <br> 3.异常处理
     * @param logger 日志对象
     * @param handleCallBack 操作回调接口
     */
    public static <T> void operate(Logger logger, String bizRequest,
                                   AbstractTutorialResult webResult,
                                   HandleCallBack handleCallBack) {
        long start = System.currentTimeMillis();

        // 业务请求
        String request = MessageFormat.format("[处理web请求],bizRequest={0}", bizRequest);

        try {
            //1. 参数校验及补全
            handleCallBack.checkAndFillParams();

            //2. 操作执行
            handleCallBack.process();

            LoggerUtil.info(logger, "[处理web请求]成功,request:{0}", request);

        } catch (ApiException ae) {

            webResult.setSuccess(false);
            webResult.setResultCode(ae.getErrorCode());
            webResult.setResultMsg(ae.getErrorMsg());

            LoggerUtil.warn(logger, ae, "[处理web请求]业务异常,request:{0}", request);

            handleCallBack.handleFailure();

        } catch (Exception e) {

            webResult.setSuccess(false);
            webResult.setResultCode(ResultCodeEnum.SYSTEM_ERROR.getCode());
            webResult.setResultMsg(ResultCodeEnum.SYSTEM_ERROR.getDesc());

            LoggerUtil.error(logger, e, "[处理web请求]系统异常,request:{0}", request);

            handleCallBack.handleFailure();

        } finally {
            long time = System.currentTimeMillis() - start;

            LoggerUtil.info(logger, "[处理web请求]结束，bizRequest={0},耗时:{1}ms.", bizRequest, time);
        }
    }
}