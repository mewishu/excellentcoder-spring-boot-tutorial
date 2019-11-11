/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.common.util.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 结果码枚举
 *
 * @author xbyan
 * @version $Id: ResultCodeEnum.java, v 0.1 2019-10-11 10:15 PM xbyan Exp $$
 */
public enum  ResultCodeEnum {

    /** 成功 */
    SUCCESS("SUCCESS", "成功"),

    /******************************************************
     *
     * ***************  业务参数异常  *************
     *
     *****************************************************/
    /** 参数不合法 */
    PARAMETER_ILLEGAL("PARAMETER_ILLEGAL", "参数不合法"),



    /******************************************************
     *
     * ***************  其它异常  *************
     *
     *****************************************************/

    /** 未知异常 */
    UNKNOWN_EXCEPTION("UNKNOWN_EXCEPTION", "未知异常"),

    /** 系统错误. */
    SYSTEM_ERROR("SYSTEM_ERROR", "系统错误"), ;

    /**
     * 标识.
     */
    @Getter
    private String code;

    /**
     * 数字码.
     */
    @Getter
    private int    num;

    /**
     * 描述.
     */
    @Getter
    private String desc;

    /**
     * 构造方法.
     * @param code 标识
     * @param desc 描述
     */
    ResultCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 构造方法.
     * @param code 标识
     * @param desc 描述
     */
    ResultCodeEnum(String code, int num, String desc) {
        this.code = code;
        this.num = num;
        this.desc = desc;
    }


    /**
     * 通过枚举码获取枚举.
     *
     * @param code 枚举码
     * @return {@link ResultCodeEnum}
     */
    public static ResultCodeEnum getEnumByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (ResultCodeEnum elem : values()) {
            if (StringUtils.equals(elem.getCode(), code)) {
                return elem;
            }
        }

        return null;
    }

}