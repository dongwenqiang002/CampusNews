package team.agile.campusnews.config;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import me.chanjar.weixin.common.exception.WxErrorException;
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
 *
 * @Time 2018/3/13 16:55
 * 验证用户名与密码
 */
@Component
public class UserVerificationConfig implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserVerificationConfig.class);

    @Autowired
    private UserService userService;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        LOGGER.info("有人登录");
        String  username = authentication.getName();
        //String password = (String) authentication.getCredentials();
        UserDetails userDetials;

        try {
            userDetials = userService.loadUserByUsername(username);
        }catch (UsernameNotFoundException e){
            return null;
        }
        //获取用户权限列表
        Collection<? extends GrantedAuthority> authorities = userDetials.getAuthorities();

        //判断用户密码是否正确

        if (true) {

            return new UsernamePasswordAuthenticationToken(userDetials, null, authorities);

        } else {
            /*密码不正确*/
            return null;//new UsernamePasswordAuthenticationToken(userDetials,password,null);
        }
    }


    @Override
    public boolean supports(Class<?> aClass) {
        //设置可用
        return true;
    }





}