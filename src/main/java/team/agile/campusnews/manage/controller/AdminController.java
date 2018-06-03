package team.agile.campusnews.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team.agile.campusnews.app.service.UserService;
import team.agile.campusnews.data.model.Menu;
import team.agile.campusnews.manage.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Time 2018/5/28 15:03
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;

    @RequestMapping("/")
    public ModelAndView login11(String username, String password) {
        ModelAndView mv = new ModelAndView("/login");

        return mv;
    }

    @PostMapping("/login")
    @ResponseBody
    public Map manageLogin(String username, String password, HttpServletRequest request){
        Map map = new HashMap();
        LOGGER.info("{} -- {}",username,password);
        try {
            request.login(username,password);
            map.put("sessionId",request.getSession().getId());
            map.put("user",adminService.getManageUser(username));
        } catch (ServletException e) {
            map.put("message","登录失败，请检查用户名或密码！");
        }catch (Exception e){
            map.put("message","登录失败，请检查用户名或密码！");
        }

        return map;
    }

    @GetMapping("/menu")
    @ResponseBody
    public List<Menu> getMenu(Principal user){
        LOGGER.info("用户{}",user.getName());
        return adminService.getMenu();
    }

}
