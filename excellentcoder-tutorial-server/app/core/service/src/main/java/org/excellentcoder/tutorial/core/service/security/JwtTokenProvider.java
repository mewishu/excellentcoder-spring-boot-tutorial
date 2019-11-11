/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.core.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.excellentcoder.tutorial.common.util.logger.LoggerUtil;
import org.excellentcoder.tutorial.core.model.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.util.Date;

/**
 * JWT token提供者
 * 
 * @author xbyan
 * @version $Id: JwtTokenProvider.java, v 0.1 2019-11-08 5:31 PM xbyan Exp $$
 */
@Component
public class JwtTokenProvider {
    /**
     * 日志打印
     */
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    /** jwt密码 */
    @Value("${app.jwtSecret}")
    private String              jwtSecret;

    /**  失效毫秒数 */
    @Value("${app.jwtExpirationInMs}")
    private int                 jwtExpirationInMs;

    /**
     * 产生token
     * 
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder().setSubject(Long.toString(userPrincipal.getId()))
            .setIssuedAt(new Date()).setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    /**
     * 获取userId
     * 
     * @param token
     * @return
     */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        return Long.parseLong(claims.getSubject());
    }

    /**
     * 校验token
     * 
     * @param authToken
     * @return
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {

            LoggerUtil.error(logger, "Invalid JWT signature");
        } catch (MalformedJwtException ex) {

            LoggerUtil.error(logger, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {

            LoggerUtil.error(logger, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {

            LoggerUtil.error(logger, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {

            LoggerUtil.error(logger, "JWT claims string is empty.");
        }

        return false;
    }

}