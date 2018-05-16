package team.agile.campusnews.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        LOGGER.info("登录拦截设置");
        http.authorizeRequests()
                .antMatchers().permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginProcessingUrl("/login").passwordParameter("password").usernameParameter("username")
                .permitAll()
                .defaultSuccessUrl("/").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll().and();
        http.csrf().disable();
    }

    //设置登录规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(userService);
        auth.authenticationProvider(userVerificationConfig);
    }


}
