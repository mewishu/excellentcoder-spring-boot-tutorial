/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.security;

import org.excellentcoder.tutorial.common.util.logger.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Jwt授权入口点
 * 
 * @author xbyan
 * @version $Id: JwtAuthenticationEntryPoint.java, v 0.1 2019-11-08 6:11 PM xbyan Exp $$
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    /**
     * @see org.springframework.security.web.AuthenticationEntryPoint#commence(HttpServletRequest, HttpServletResponse, AuthenticationException)
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        LoggerUtil.error(logger, "Responding with unauthorized error. Message - {0}",
            e.getMessage());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }
}
