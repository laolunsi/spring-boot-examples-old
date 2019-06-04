package com.eknown.config.security;

import com.eknown.config.SystemVariable;
import com.eknown.logic.service.UserService;
import com.eknown.model.beans.common.JsonResult;
import com.eknown.model.beans.entity.User;
import com.eknown.utils.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 鉴权处理
 * 包括：登录成功、登录失败、未登录、无权限、退出登录成功等
 * 通过返回json数据，使得前后端分离得以实现
 * @author zfh
 * @version 1.0
 * @since 2019/5/31 9:44
 */
@Component
public class MyAuthenticationHandler implements AuthenticationFailureHandler, AuthenticationSuccessHandler, AuthenticationEntryPoint, AccessDeniedHandler, LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(MyAuthenticationHandler.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    /**
     * 登录失败处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        logger.info("登录失败");
        JsonResult jsonResult = new JsonResult(JsonResult.LOGIN_FAILED, false, e.getMessage());
        httpServletResponse.getWriter().write(jsonResult.toString());
    }

    /**
     * 登录成功
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
        JsonResult jsonResult = new JsonResult(true);
        String userName = authentication.getName();
        User user = userService.findByName(userName);
        String token = Md5Util.md5(userName + "***" + new Date().getTime());
        redisTemplate.opsForValue().set(SystemVariable.REDIS_SPACE + ":" + SystemVariable.TOKEN_SPACE + ":" + token, user, 6, TimeUnit.HOURS);

        user.setPassword(null);
        jsonResult.put("user", user);
        jsonResult.put("token", token);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(jsonResult.toString());
    }

    /**
     * 未登录时返回数据
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        logger.info("未登录");
        JsonResult jsonResult = new JsonResult(JsonResult.NO_LOGIN, false, "未登录");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(jsonResult.toString());
    }

    /**
     * 无权访问
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.info("无权限");
        JsonResult jsonResult = new JsonResult(JsonResult.NO_PERMISSION, false, "无权限");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonResult.toString());
    }

    /**
     * 成功退出登录
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("成功退出");
        JsonResult jsonResult = new JsonResult(true);
        String token = request.getHeader("token");
        if (StringUtils.isNotBlank(token)) {
            redisTemplate.opsForValue().set(SystemVariable.REDIS_SPACE + ":" + SystemVariable.TOKEN_SPACE + ":" + token, null, 1, TimeUnit.SECONDS);
        }
        response.getWriter().write(jsonResult.toString());
    }
}
