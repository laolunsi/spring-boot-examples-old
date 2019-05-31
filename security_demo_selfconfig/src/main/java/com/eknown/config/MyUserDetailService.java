package com.eknown.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义用户角色和权限绑定配置
 *
 * 一旦@Configuration生效，这个类就会自动替换默认的UserDetailService
 * @author eknown
 * @version 1.0
 * @since 2019/5/30 10:30
 */
/*@Configuration*/
public class MyUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
