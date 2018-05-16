package team.agile.campusnews.app.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import team.agile.campusnews.app.model.User;

/**
 * @author 董文强
 * @Time 2018/5/3 14:15
 */
@Service
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("用户登录： {}", username);
        User user;
        try {
            //user = userMapper.selectByUserName(username);
            user = new User();
            user.setUsername(username);
            user.setPassword("$2a$10$BpXuY1.kDc206QLXqPX7s.a8jUlU8Zb7BW1KNF7c.cmKqxbf.2PpG");
            if (user == null) {
                throw new UsernameNotFoundException("用户登录异常");
            }
            if (user.getUsername() == null || user.getUsername().isEmpty()) {
                throw new UsernameNotFoundException("用户登录异常");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("用户名不正确");
        }
        return user;
    }






    public boolean addUser(User user) {
        if (user == null) {
            return false;
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return false;
        }
        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().length() < 4) {
            return false;
        }
        if (user.getRole() == null || user.getRole().isEmpty()) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
           /* if (userMapper.insert(user) == 1) {
                return true;
            }*/
           return false;
        } catch (Exception e) {
            return false;
        }
    }



    public boolean alert(User user) {
        if (user == null || user.getId() == null) {
            return false;
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            return false;
        }
        if (user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().length() < 3) {
            return false;
        }
        if ( user.getRole() == null || user.getRole().isEmpty()) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
           /* if(userMapper.updateByPrimaryKeySelective(user) == 1){
                return true;
            }*/
           return  false;
        } catch (Exception e) {
            return false;
        }
       // return false;
    }
}
