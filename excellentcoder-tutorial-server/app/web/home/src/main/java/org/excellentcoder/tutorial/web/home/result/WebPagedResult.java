/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.home.result;

import lombok.Data;
import lombok.ToString;
import org.excellentcoder.tutorial.common.service.facade.dto.result.TutorialPagedResult;

import java.util.List;

/**
 * Web分页查询结果
 * 
 * @author xbyan
 * @version $Id: WebPagedResult.java, v 0.1 2019-11-06 3:37 PM xbyan Exp $$
 */
@Data
@ToString(callSuper = true)
public class WebPagedResult<T> extends TutorialPagedResult<T> {

    /**
     * 根据返回成功与否构造响应结果
     */
    public WebPagedResult() {
        super();
    }

    /**
     * 根据返回成功与否，及返回消息构造响应结果
     *
     * @param errorCode    错误码
     * @param errorMessage 返回消息
     */
    public WebPagedResult(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    /**
     * 根据返回成功与否与T对象构造响应结果
     *
     * @param success 是否成功
     * @param result
     */
    public WebPagedResult(boolean success, List<T> result, int page, int size, long totalElements,
                          int totalPages, boolean last) {
        super(success, result, page, size, totalElements, totalPages, last);
    }
}