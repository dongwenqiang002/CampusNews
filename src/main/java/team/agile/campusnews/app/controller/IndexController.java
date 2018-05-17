package team.agile.campusnews.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import team.agile.campusnews.app.model.User;

import javax.servlet.http.HttpServletResponse;

/**
 * 测试
 * */
@RestController
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
<<<<<<< HEAD
    public User  index(){
        User user = new User();
        user.setPassword("1124aaa");
        user.setUsername("DDWWQQ");
        user.setRole("aaa");
        LOGGER.info("访问");
        return user;
=======
    public String index(HttpServletResponse response){
        return "index";
>>>>>>> 34096d99e73f682e17a7d9bf2b5813f46291c9fc
    }

    @PostMapping("/jj")
    public User u(HttpServletResponse response){
        User user = new User();
        user.setPassword("123");
        return user;
    }
}
