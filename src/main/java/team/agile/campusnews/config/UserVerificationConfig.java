package team.agile.campusnews.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.stereotype.Component;
import team.agile.campusnews.app.service.UserService;
import java.util.Collection;

/**
 * @author 董文强
 * @Time 2018/3/13 16:55
 * 验证用户名与密码
 */
@Component
public class UserVerificationConfig implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserVerificationConfig.class);

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LOGGER.info("MD5登录");
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetials;

        try {
            userDetials = userService.loadUserByUsername(username);
        }catch (UsernameNotFoundException e){
            return null;
        }
        //获取用户权限列表
        Collection<? extends GrantedAuthority> authorities = userDetials.getAuthorities();

        //判断用户密码是否正确

        if (passwordEncoder.matches(password,userDetials.getPassword())) {

            return new UsernamePasswordAuthenticationToken(userDetials, password, authorities);

        } else {
            /*密码不正确*/
            return null;//new UsernamePasswordAuthenticationToken(userDetials,password,null);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }





}