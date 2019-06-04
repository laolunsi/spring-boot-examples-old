package com.eknown.config;

import com.eknown.config.security.MyUserDetailService;
import com.eknown.model.beans.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/6/4 10:54
 */
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MyUserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("token"); // 约定

        if (StringUtils.isNotBlank(token)) {
            // token不为空
            User user = null;
            String key = SystemVariable.REDIS_SPACE + ":" + SystemVariable.TOKEN_SPACE + ":" + token;
            if (redisTemplate.hasKey(key)) {
                user = (User) redisTemplate.opsForValue().get(SystemVariable.REDIS_SPACE + ":" + SystemVariable.TOKEN_SPACE + ":" + token);
            }

            if (user != null && StringUtils.isNotBlank(user.getName())) {
                UserDetails userDetails = userDetailService.loadUserByUsername(user.getName());
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(userDetails);
                Authentication old = SecurityContextHolder.getContext().getAuthentication();
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
