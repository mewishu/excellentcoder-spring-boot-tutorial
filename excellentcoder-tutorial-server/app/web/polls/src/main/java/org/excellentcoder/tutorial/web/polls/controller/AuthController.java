/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package org.excellentcoder.tutorial.web.polls.controller;

import org.excellentcoder.tutorial.common.util.enums.ResultCodeEnum;
import org.excellentcoder.tutorial.common.util.exception.ApiException;
import org.excellentcoder.tutorial.core.manager.RoleManager;
import org.excellentcoder.tutorial.core.manager.UserManager;
import org.excellentcoder.tutorial.core.model.enums.RoleName;
import org.excellentcoder.tutorial.core.model.poll.RoleBO;
import org.excellentcoder.tutorial.core.model.poll.UserBO;
import org.excellentcoder.tutorial.core.service.security.JwtTokenProvider;
import org.excellentcoder.tutorial.core.service.template.AbstractHandleCallBack;
import org.excellentcoder.tutorial.web.home.result.WebResult;
import org.excellentcoder.tutorial.web.home.template.WebOperateTemplate;
import org.excellentcoder.tutorial.web.home.template.WebResultUtil;
import org.excellentcoder.tutorial.web.polls.payload.JwtAuthenticationResponseVO;
import org.excellentcoder.tutorial.web.polls.payload.LoginRequestVO;
import org.excellentcoder.tutorial.web.polls.payload.SignUpRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

/**
 * 用户授权controller: rest风格
 *
 * @author xbyan
 * @version $Id: AuthController.java, v 0.1 2019-11-08 3:05 PM xbyan Exp $$
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    /**
     * 日志打印
     */
    private static final Logger   logger = LoggerFactory.getLogger(AuthController.class);

    /** 认证管理类 */
    @Autowired
    private AuthenticationManager authenticationManager;

    /** 用户管理器接口 */
    @Autowired
    private UserManager           userManager;

    /** 角色管理器接口 */
    @Autowired
    private RoleManager           roleManager;

    /** 密码Encoder */
    @Autowired
    private PasswordEncoder       passwordEncoder;

    /** JwtToken提供者 */
    @Autowired
    private JwtTokenProvider      tokenProvider;

    /**
     * 用户登陆
     * 
     *  @RequestBody将请求的Http Body和参数messge进行了映射(@RequestBody --> 提交的请求头部要带上"Content-Type:application/json")
     *  @ModelAttribute将请求的Http Body中的表单数据和参数messge进行了映射(提交的请求头部要带上"Content-Type:application/x-www-form-urlencoded"，表明请求体是表单数据，格式是"key1=value1&key2=value2&key3=value3")
     *      
     * @param loginRequest
     * @return
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestVO loginRequest) {

        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),
                loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponseVO(jwt));
    }

    /**
     * 新用户注册
     * 
     * @param signUpRequest
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequestVO signUpRequest) {
        // 1. 执行创建投票逻辑
        final WebResult<UserBO> result = new WebResult<>();

        WebOperateTemplate.operate(logger, "新用户注册", result, new AbstractHandleCallBack() {
            /**
             * @see org.excellentcoder.tutorial.core.service.template.AbstractHandleCallBack#checkAndFillParams()
             */
            @Override
            public void checkAndFillParams() {
                if (userManager.existsByUsername(signUpRequest.getUsername())) {
                    throw new ApiException("Username is already taken!");
                }

                if (userManager.existsByEmail(signUpRequest.getEmail())) {
                    throw new ApiException("Email Address already in use!");
                }
            }

            /**
             * @see org.excellentcoder.tutorial.core.service.template.AbstractHandleCallBackWithResult#process()
             */
            @Override
            public void process() {
                // 1. 创建用户信息
                UserBO user = new UserBO(signUpRequest.getName(), signUpRequest.getUsername(),
                    signUpRequest.getEmail(), signUpRequest.getPassword());

                user.setPassword(passwordEncoder.encode(user.getPassword()));

                // 2. 保存用户信息
                RoleBO userRole = roleManager.queryByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new ApiException("User Role not set."));
                user.setRoles(Collections.singleton(userRole));

                UserBO userBO = userManager.insert(user);

                WebResultUtil.generateResult(result, ResultCodeEnum.SUCCESS, userBO);
            }
        });

        // 2. 组装返回结果
        if (result.isSuccess()) {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/{username}").buildAndExpand(result.getResult().getUsername()).toUri();
            return ResponseEntity.created(location).body(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }
}