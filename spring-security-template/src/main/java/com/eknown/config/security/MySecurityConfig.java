package com.eknown.config.security;

import com.eknown.config.TokenFilter;
import com.eknown.config.security.MyAuthenticationHandler;
import com.eknown.config.security.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author eknown
 * @version 1.0
 * @since 2019/5/29 14:44
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService userDetailService;

    @Autowired
    private MyAuthenticationHandler authenticationHandler;

    @Autowired
    private TokenFilter tokenFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity //httpBasic() // http弹框验证-alert形式
                .formLogin() // 表单验证方式
/*                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/").permitAll()*/
                .failureHandler(authenticationHandler)
                .successHandler(authenticationHandler)
                .and()
                .logout()
                .logoutSuccessHandler(authenticationHandler)
                .permitAll()
                .and()
                .authorizeRequests() // 授权配置
                .anyRequest() // 对所有请求生效
                .authenticated() //; // 都需要认证
                .and().csrf().disable(); //

        httpSecurity.exceptionHandling().authenticationEntryPoint(authenticationHandler); // 未登录
        httpSecurity.exceptionHandling().accessDeniedHandler(authenticationHandler); // 无权限

        httpSecurity.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class); // 将token过滤器放在用户密码过滤器前面
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

/*    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }*/
}
