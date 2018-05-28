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
import org.springframework.transaction.annotation.Transactional;
import team.agile.campusnews.app.dao.UserMapper;
import team.agile.campusnews.app.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 董文强
 * @Time 2018/5/3 14:15
 */
@Service
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    private UserMapper userMapper;

    private SimpleDateFormat dateFormat;

    @Autowired
    public UserService(UserMapper userMapper,SimpleDateFormat dateFormat) {
        this.userMapper = userMapper;
        this.dateFormat = dateFormat;
    }

    @Bean
    public SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            // 从数据库查找用户
            user = userMapper.selectByUserName(username);
            if (username == null || username.isEmpty()) {
                throw new UsernameNotFoundException("用户名不能为空");
            }
            if (user == null || user.getUsername() == null || user.getUsername().isEmpty()) {
                throw new UsernameNotFoundException("用户不存在");
            }

            //TODO 这里添加验证用户 有效的代码

        } catch (Exception e) {
            throw new UsernameNotFoundException("用户异常");
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
        //  user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        if (user.getRole() == null || user.getRole().isEmpty()) {
            return false;
        }
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
           /* if(userMapper.updateByPrimaryKeySelective(user) == 1){
                return true;
            }*/
            return false;
        } catch (Exception e) {
            return false;
        }
        // return false;
    }

    /**
     * 注册 TODO
     */
    @Transactional
    public Boolean regUser(User user, String classId, String role, String schoolTime) throws ParseException {
        user.setId(null);
        userMapper.insert(user);
        Integer id = user.getId();
        userMapper.regUserR(id, role);
        userMapper.regUserS(id, Integer.parseInt(classId));
        Date date = dateFormat.parse(schoolTime);
        userMapper.regUserStu(user.getCode(), id, date);
        return false;
    }
}
