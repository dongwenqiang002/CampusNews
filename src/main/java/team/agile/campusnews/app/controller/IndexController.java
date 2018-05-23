package team.agile.campusnews.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import team.agile.campusnews.app.dao.SchoolOsMapper;
import team.agile.campusnews.app.dao.UserMapper;
import team.agile.campusnews.app.model.SchoolOs;
import team.agile.campusnews.app.model.User;

import javax.servlet.http.HttpServletResponse;

/**
 * 测试
 * */
@RestController
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    SchoolOsMapper schoolOsMapper;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public User index(HttpServletResponse response){
        //return schoolOsMapper.selectByPrimaryKey(20);
        //cn.binarywang.wx.miniapp.api.WxMaUserService
        return userMapper.selectByUserName("dwq");
        //return "index";
    }

    @PostMapping("/jj")
    public User u(HttpServletResponse response){
        User user = new User();
        user.setPassword("123");
        return user;
    }



}
