package team.agile.campusnews.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.agile.campusnews.app.model.User;


@RestController
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public User  index(){
        User user = new User();
        user.setPassword("1124aaa");
        user.setUsername("DDWWQQ");
        user.setRole("aaa");
        LOGGER.info("访问");
        return user;
    }
}
