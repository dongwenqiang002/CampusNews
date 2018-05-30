package team.agile.campusnews.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import team.agile.campusnews.app.service.UserService;

/**
 * @Time 2018/5/28 15:03
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    UserService userServicel;

    @RequestMapping("/")
    public ModelAndView login11(String username, String password){
        ModelAndView mv = new ModelAndView("/login");

        return mv;
    }
    @GetMapping("/login")
    public ModelAndView login(){

            return new ModelAndView("/login");
    }

    @PostMapping("/login")
    public ModelAndView login(String username,String password){
        ModelAndView mv = new ModelAndView("/index");
        if(username.equals("admin")&&password.equals("admin")) {
            login("admin",null);
            return mv;
        }else{
            return new ModelAndView("/login");
        }
    }
}
