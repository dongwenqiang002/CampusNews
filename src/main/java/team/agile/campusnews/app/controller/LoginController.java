package team.agile.campusnews.app.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaUserService;
import cn.binarywang.wx.miniapp.api.impl.WxMaUserServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.agile.campusnews.data.model.User;
import team.agile.campusnews.app.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Action;

/**
 * @Time 2018/5/17 11:22
 * 控制用户登录时跳转逻辑
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private  WxMaService wxMaService;
    @Autowired
    private UserService userService;
    /**
     * 没有登录
     * @return 没有登录的内容
     */
    @GetMapping("/NoLogin")
    public boolean noLogin() {
        //TODO 没有登录
        return false;
     /*   return "您没登录" +
                "<h1>测试使用的POST登录</h1>" +
                " <form action=\"/login\" method=\"post\">\n" +
                "            <input name=\"username\"/>\n" +
                "            <input name=\"password\"/>\n" +
                "            <button type=\"submit\">提交</button>\n" +
                "        </form>";*/
    }

    /**
     * 使用微信小程序前端发来的code向腾讯的微信服务器上获取信息
     * @param code 前端发来的
     * @return //TODO
     * */
    //todo
    @RequestMapping("/wxlogin")
    @ResponseBody
    public String wxLogin(String code, HttpServletRequest request){
        LOGGER.info("微信登录");
        if(code == null|| code.isEmpty())return null;
        LOGGER.info("{}",code);
        try {
            WxMaUserService s = wxMaService.getUserService();
            WxMaJscode2SessionResult sess = wxMaService.getUserService().getSessionInfo(code);
            LOGGER.info("session: {}",sess.getSessionKey());
            LOGGER.info("openid: {}",sess.getOpenid());
            userService.loadUserByUsername(sess.getOpenid());
            request.login(sess.getOpenid(),null);

        } catch (WxErrorException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.toString());
            return null;
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.toString());
            return null;
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            LOGGER.error(e.toString());
            e.printStackTrace();
            return null;
        }
        LOGGER.info("登录用的 sessionID{}", request.getSession().getId());
        return request.getSession().getId();
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
