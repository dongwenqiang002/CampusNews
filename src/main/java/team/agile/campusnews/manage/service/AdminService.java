package team.agile.campusnews.manage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.agile.campusnews.data.dao.ManageUserMapper;
import team.agile.campusnews.data.dao.MenuMapper;
import team.agile.campusnews.data.dao.UserMapper;
import team.agile.campusnews.data.model.ManageUser;
import team.agile.campusnews.data.model.Menu;
import team.agile.campusnews.data.model.User;

import java.util.List;

/**
 * @Time 2018/6/1 17:33
 */
@Service
public class AdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ManageUserMapper manageUserMapper;
    @Autowired
    private MenuMapper menuMapper;

    public User getManageUser(String username) {
        ManageUser manageUser = manageUserMapper.selectByUserName(username);
        if (manageUser == null || manageUser.getUserId() == null) {
            return null;
        }
        User user = userMapper.selectByPrimaryKey(manageUser.getUserId());
        return user;
    }
    public User getManageUserLogin(String username,String password) {
        ManageUser manageUser = manageUserMapper.selectByUserName(username);
        if (manageUser == null || manageUser.getUserId() == null) {
            return null;
        }
        if(!password.equals(manageUser.getPassword()))return null;
        User user = userMapper.selectByPrimaryKey(manageUser.getUserId());
        return user;
    }
    public List<Menu> getMenu() {
        return menuMapper.selectAll();
    }
}
