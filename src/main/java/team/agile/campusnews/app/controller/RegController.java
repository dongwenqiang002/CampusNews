package team.agile.campusnews.app.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.agile.campusnews.app.model.SchoolOs;
import team.agile.campusnews.app.model.User;
import team.agile.campusnews.app.service.SchoolOsService;
import team.agile.campusnews.app.service.UserService;

import java.text.ParseException;
import java.util.List;

/**
 *
 * @Time 2018/5/24 19:45
 */
@RestController
@RequestMapping("/reg")
public class RegController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegController.class);

    @Autowired
    private WxMaService wxMaService;
    @Autowired
    private UserService userService;
    @Autowired
    private SchoolOsService schoolOsService;

    @PostMapping("/reg")
    public Boolean register(String studentCode, String code, String iv, String encryptedData, String schoolTime, String classId) {
        try {
            LOGGER.info("studentCode:{}", studentCode);
            LOGGER.info("iv:{}", iv);
            LOGGER.info("code:{}", code);
            LOGGER.info("encryptedData:{}", encryptedData);
            LOGGER.info("schoolTime:{}", schoolTime);
            LOGGER.info("classId:{}", classId);
            WxMaJscode2SessionResult sess = wxMaService.getUserService().getSessionInfo(code);
            WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sess.getSessionKey(), encryptedData, iv);

            User user = new User();
            user.setUsername(sess.getOpenid());

            user.setCode(studentCode);
            user.setWxName(userInfo.getNickName());
            user.setSex(userInfo.getGender());

            userService.regUser(user, classId, "学生",schoolTime);
        } catch (WxErrorException e) {
           LOGGER.error(e.getError().getErrorMsg());
            return false;
        } catch (ParseException e) {
            LOGGER.error(e.getLocalizedMessage());
            return false;
        }
        return true;
    }

    @GetMapping("/school")
    public List<SchoolOs> getSchoolAll(String schoolID) {
        if (schoolID == null) schoolID = "1";
        return schoolOsService.getSchhoByID(schoolID);
    }
}
