/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.common.service.facade.dto.result;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 分页查询结果
 * 
 * @author xbyan
 * @version $Id: TutorialPagedResult.java, v 0.1 2019-11-06 3:21 PM xbyan Exp $$
 */
@Data
@ToString(callSuper = true)
public class TutorialPagedResult<T> extends AbstractTutorialResult {
    /**
     * 返回结果: 列表形式
     */
    private List<T> result;

    /** 页数 */
    private int     page;

    /** 每页大小 */
    private int     size;

    /** 总共元素 */
    private long    totalElements;

    /** 总共页面数 */
    private int     totalPages;

    /** 是否为最后一页 */
    private boolean last;

    /**
     * 根据返回成功与否构造响应结果
     */
    public TutorialPagedResult() {
        this.setSuccess(false);
    }

    /**
     * 根据返回成功与否，及返回消息构造响应结果
     *
     * @param errorCode    错误码
     * @param errorMessage 返回消息
     */
    public TutorialPagedResult(String errorCode, String errorMessage) {
        this.setSuccess(false);
        this.setResultCode(errorCode);
        this.setResultMsg(errorMessage);
    }

    /**
     * 构造函数
     * 
     * @param result
     * @param page
     * @param size
     * @param totalElements
     * @param totalPages
     * @param last
     */
    public TutorialPagedResult(boolean success, List<T> result, int page, int size,
                               long totalElements, int totalPages, boolean last) {
        this.setSuccess(success);
        this.setResult(result);
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }
}