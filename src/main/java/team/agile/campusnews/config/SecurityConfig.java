package team.agile.campusnews.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 董文强
 * @Time 2018/3/12 17:11
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);



    @Autowired
    UserVerificationConfig userVerificationConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置登录拦截验证跳转
        http.authorizeRequests()
                .antMatchers("/login/*").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginProcessingUrl("/login").loginPage("/login/NoLogin").failureForwardUrl("/login/loginNO").successForwardUrl("/login/loginOK").passwordParameter("password").usernameParameter("username")
                .permitAll()
                .defaultSuccessUrl("/").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll().and();
        //关闭打开的csrf保护
        http.csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(userService);
        //设置登录验证规则为 UserVerificationConfig 类
        auth.authenticationProvider(userVerificationConfig);
    }


}
