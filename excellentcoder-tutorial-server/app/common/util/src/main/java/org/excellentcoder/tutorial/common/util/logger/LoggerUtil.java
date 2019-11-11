/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.common.util.logger;

import org.slf4j.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;

/**
 * 规范化日志打印工具，注意日志的级别选择：<br>
 *
 * <p>
 *   <ol>
 *     <li>DEBUG <b>开发环境</b>应用调试，输出详细的应用状态
 *     <li>INFO <b>生产环境</b>运行状态观察，输出应用生命周期的中<b>正常重要事件</b>
 *     <li>WARN <b>生产环境</b>故障诊断，输出应用中的<b>可预期的异常事件</b>
 *     <li>ERROR <b>生产环境</b>境故障诊断，输出应用中的<b>未预期的异常事件</b>
 *   </ol>
 *  </p>
 *
 * @author xbyan
 * @version $Id: LoggerUtil.java, v 0.1 2019-10-11 9:20 PM xbyan Exp $$
 */
public class LoggerUtil {
    /** 线程编号修饰符 */
    private static final char THREAD_RIGHT_TAG = ']';

    /** 线程编号修饰符 */
    private static final char THREAD_LEFT_TAG  = '[';

    /** 换行符 */
    public static final char  ENTERSTR         = '\n';

    /** 逗号 */
    public static final char  COMMA            = ',';

    /**
     * 禁用构造函数
     */
    private LoggerUtil() {
        // 禁用构造函数
    }

    /**
     * 生成<font color="blue">调试</font>级别日志<br>
     *
     * @param logger
     * @param obj
     */
    public static void debug(Logger logger, Object obj) {
        if (logger.isDebugEnabled()) {
            logger.debug(getLogString(obj));
        }
    }

    /**
     * 输出debug level的log信息.
     *
     * @param logger  日志记录器
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void debug(Logger logger, String message, Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(getFormatLogString(message, params));
        }
    }

    /**
     * 生成<font color="blue">通知</font>级别日志<br>
     *
     * @param logger
     * @param obj
     */
    public static void info(Logger logger, Object obj) {
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(obj));
        }
    }

    /**
     * 输出info level的log信息
     *
     * @param logger  日志记录器
     * @param message log信息
     * @param params  log格式化参数
     */
    public static void info(Logger logger, String message, Object... params) {
        if (logger.isInfoEnabled()) {
            logger.info(getFormatLogString(message, params));
        }
    }

    /**
     * 生成<font color="brown">警告</font>级别日志<br>
     *
     * @param logger
     * @param obj
     */
    public static void warn(Logger logger, Object obj) {
        if (logger.isWarnEnabled()) {
            logger.warn(getLogString(obj));
        }
    }

    /**
     * 生成<font color="brown">警告</font>级别日志<br>
     *
     * @param logger
     * @param obj
     */
    public static void warn(Logger logger, Throwable throwable, Object obj) {
        if (logger.isWarnEnabled()) {
            logger.warn(getLogString(obj), throwable);
        }
    }

    /**
     * 输出warn level的log信息.
     *
     * @param logger 日志记录器
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void warn(Logger logger, String message, Object... params) {
        if (logger.isWarnEnabled()) {
            logger.warn(getFormatLogString(message, params));
        }
    }

    /**
     * 输出warn level的log信息.
     *
     * @param logger  日志记录器
     * @param throwable 异常对象
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void warn(Logger logger, Throwable throwable, String message, Object... params) {
        if (logger.isWarnEnabled()) {
            logger.warn(getFormatLogString(message, params), throwable);
        }
    }

    /**
     * 生成<font color="brown">错误</font>级别日志<br>
     * 可处理不带有Throwable的错误
     *
     * @param logger
     * @param obj
     */
    public static void error(Logger logger, Object obj) {
        logger.error(getLogString(obj));
    }

    /**
     * 生成<font color="brown">错误</font>级别日志<br>
     *
     * @param logger
     * @param throwable
     * @param obj
     */
    public static void error(Logger logger, Throwable throwable, Object obj) {
        logger.error(getLogString(obj), throwable);
    }

    /**
     * 输出error level的log信息.
     *
     * @param logger  日志记录器
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void error(Logger logger, String message, Object... params) {
        if (logger.isErrorEnabled()) {
            logger.error(getFormatLogString(message, params));
        }
    }

    /**
     * 输出error level的log信息.
     *
     * @param logger  日志记录器
     * @param throwable 异常对象
     * @param message log信息,如:<code>xxx{0},xxx{1}...</code>
     * @param params log格式化参数,数组length与message参数化个数相同, 如:<code>Object[]  object=new Object[]{"xxx","xxx"}</code>
     */
    public static void error(Logger logger, Throwable throwable, String message, Object... params) {
        if (logger.isErrorEnabled()) {
            logger.error(getFormatLogString(message, params), throwable);
        }
    }

    /**
     * 打印DAO调用日志
     * @param dbName : 数据源名称ebill00 或 ebillg00
     * @param methodName：方法名
     * @param success
     * @param startTime: 操作开始时间
     *
     * result: [(数据库名称，函数体.方法名称，Y，1ms)]
     */
    public static void digestDAOLog(Logger logger, String dbName, String methodName,
                                    boolean success, long startTime) {
        StringBuilder sb = constructDigestContent(dbName, methodName, null, success, null,
            startTime);
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(sb));
        }
    }

    /**
     * 打印摘要日志
     *
     * @param logger  日志记录器
     * @param sysName : 调用外围系统名称，如sfs
     * @param methodName：方法名
     * @param success
     * @param startTime: 操作开始时间
     *
     * result: [(数据库名称，函数体.方法名称，Y，1ms)]
     */
    public static void digestSALLog(Logger logger, String sysName, String methodName,
                                    boolean success, long startTime) {
        StringBuilder sb = constructDigestContent(sysName, methodName, null, success, null,
            startTime);
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(sb));
        }
    }

    /**
     * 打印Service调用日志
     *
     * @param methodName：方法名
     * @param success
     * @param startTime: 操作开始时间
     *
     * result: [(数据库名称，函数体.方法名称，Y，1ms)]
     */
    public static void digestServiceLog(Logger logger, String methodName, boolean success,
                                        long startTime) {
        StringBuilder sb = constructDigestContent(null, methodName, null, success, null, startTime);
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(sb));
        }
    }

    /**
     * 打印通用的摘要调用日志
     *
     * @param methodName：方法名
     * @param args 参数名
     * @param success
     * @param resultCode 结果码
     * @param startTime: 操作开始时间
     *
     * result: [(数据库名称，函数体.方法名称，Y，1ms)]
     */
    public static void digestLog(Logger logger, String userDefineName, String methodName,
                                 Object[] args, boolean success, String resultCode,
                                 long startTime) {
        StringBuilder sb = constructDigestContent(userDefineName, methodName, args, success,
            resultCode, startTime);
        if (logger.isInfoEnabled()) {
            logger.info(getLogString(sb));
        }
    }

    /**
     *  摘要日志构造器
     *
     * @param userDefineName 自定义名称
     * @param methodName 方法名称
     * @param args 参数值
     * @param success Y,N
     * @param resultCode 结果码
     * @param startTime 开始时间
     * @return 日志
     */
    private static StringBuilder constructDigestContent(String userDefineName, String methodName,
                                                        Object[] args, boolean success,
                                                        String resultCode, long startTime) {

        long endTime = System.currentTimeMillis();
        long during = endTime - startTime;
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        if (StringUtils.isNotBlank(userDefineName)) {
            sb.append(userDefineName);
            sb.append(",");
        }

        sb.append(methodName);
        sb.append(",");

        sb.append(success ? "Y" : "N");
        sb.append(",");

        if (StringUtils.isNotBlank(resultCode)) {
            sb.append(resultCode);
            sb.append(",");
        }

        sb.append(during + "ms");

        if (ArrayUtils.isNotEmpty(args)) {
            sb.append(",");
            sb.append("(");
            sb.append(StringUtils.join(args, ","));
            sb.append(")");
        }

        sb.append("]");

        return sb;
    }

    /**
     * 生成输出到日志的字符串
     *
     * @param obj 任意个要输出到日志的参数
     * @return
     */
    public static String getLogString(Object obj) {
        return preTraceLog().append(obj).toString();
    }

    /**
     * 日志信息参数格式化，适用于日志信息包含多个参数
     *
     * @param message   log信息
     * @param params    log格式化参数
     * @return   格式化后的日志信息
     */
    private static String getFormatLogString(String message, Object... params) {
        if (params != null && params.length != 0) {
            return MessageFormat.format(preTraceLog().append(message).toString(), params);
        }
        return preTraceLog().append(message).toString();
    }

    /**
     * traceLog 前缀
     * @return traceLog
     */
    public static StringBuilder preTraceLog() {
        StringBuilder log = new StringBuilder();
        log.append(THREAD_LEFT_TAG).append(Thread.currentThread().getId()).append(THREAD_RIGHT_TAG);

        return log;
    }
}