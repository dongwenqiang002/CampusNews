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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;

/**
 * @author 董文强
 * @Time 2018/5/17 11:22
 * 控制用户登录时跳转逻辑
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    WxMaService wxMaUserService;
    /**
     * 没有登录
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
    public String wxLogin(String encryptedData, String iv, String code){
        LOGGER.info("微信登录");
        LOGGER.info("{}",code);
        try {
            WxMaUserService s = wxMaUserService.getUserService();
            WxMaJscode2SessionResult sess = wxMaUserService.getUserService().getSessionInfo(code);
            WxMaUserInfo userInfo = s.getUserInfo(sess.getSessionKey(),encryptedData,iv);
            //userInfo.
            //s.

            LOGGER.info("session: {}",sess.getSessionKey());
            LOGGER.info("openid: {}",sess.getOpenid());
            LOGGER.info("unionid: {}",sess.getUnionid());
            LOGGER.info("userINFO");
            LOGGER.info("getNickName: {}",userInfo.getNickName());
            LOGGER.info("getUnionId: {}",userInfo.getUnionId());
            LOGGER.info("getCity: {}",userInfo.getCity());
            WxMaPhoneNumberInfo iphone = wxMaUserService.getUserService().getPhoneNoInfo(sess.getSessionKey(),encryptedData,iv);

            LOGGER.info("电话号码: {}", iphone.getPurePhoneNumber());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
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


    @GetMapping("/reg")
    public Boolean register() {
        return true;
    }
}
