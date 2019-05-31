package com.eknown.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author eknown
 * @version 1.0
 * @since 2019/5/29 14:44
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private MyAuthenticationHandler failureHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin() // 表单验证方式
                //.httpBasic() // http弹框验证-alert形式
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/").permitAll()
                .failureHandler(failureHandler)
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/login.html").permitAll() // login界面全部授权
                .anyRequest() // 对所有请求生效
                .authenticated() //; // 都需要认证
                .and().csrf().disable(); //
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }
}
