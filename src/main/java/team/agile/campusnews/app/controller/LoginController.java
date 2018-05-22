package team.agile.campusnews.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 董文强
 * @Time 2018/5/17 11:22
 * 控制用户登录时跳转逻辑
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    /**
     * 没有登录
     *
     * @return 没有登录的内容
     */
    @GetMapping("/NoLogin")
    public String noLogin() {
        //TODO 没有登录
        return "您没登录" +
                "<h1>测试使用的POST登录</h1>" +
                " <form action=\"/login\" method=\"post\">\n" +
                "            <input name=\"username\"/>\n" +
                "            <input name=\"password\"/>\n" +
                "            <button type=\"submit\">提交</button>\n" +
                "        </form>";
    }

    /**
     * 使用微信小程序前端发来的code向腾讯的微信服务器上获取信息
     * @param code 前端发来的
     * @return //TODO
     * */
    @RequestMapping("/wxlogin")
    public String wxLogin(String code){
        LOGGER.info("微信登录");
        LOGGER.info("{}",code);

        return "{\n" +
                "  \"userInfo\":{\n" +
                "    \"nickName\": \"dddwww\"\n" +
                "  }\n" +
                "}";
    }

    /**
     * 登录失败URL
     * spring security 在登录失败时会跳转只当前url的POST
     * @return 失败是的返回值 false
     */
    @RequestMapping("/loginNO")
    public Boolean loginNO() {
        return false;
    }

    /**
     * 登录成功URL
     *
     * @return 成功时的返回值 true
     */
    @GetMapping("/loginOK")
    public Boolean loginOK() {
        return true;
    }
}
