package team.agile.campusnews.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.agile.campusnews.app.service.SchoolOsService;
import team.agile.campusnews.data.model.News;
import team.agile.campusnews.data.model.SchoolOs;
import team.agile.campusnews.data.model.User;
import team.agile.campusnews.manage.service.AdminNewsService;
import team.agile.campusnews.manage.service.AdminSchoolOSService;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @Time 2018/6/3 16:44
 */
@RestController
@RequestMapping("/admin/news")
public class AdminNewsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminNewsController.class);

    @Autowired
    private AdminNewsService adminNewsService;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private SchoolOsService schoolOSService;


    @PostMapping("/add")
    public Integer addNews(String newsContent, String newsEndTime, String newsTitle, Principal user){
        LOGGER.info("\nnewsTitle：{}",newsContent);
        LOGGER.info("\nnewsEndTime：{}",newsEndTime);
        LOGGER.info("\nnewsTitle：{}",newsTitle);

        News news = new News();
        User user1 = (User) ((UsernamePasswordAuthenticationToken) user).getPrincipal();
        news.setAuthor(user1.getId().toString());
        news.setContext(newsContent);
        try {
            news.setEndTime(dateFormat.parse(newsEndTime));
        } catch (ParseException e) {
           return null;
        }
        news.setPubdate(new Date());
        news.setTitle(newsTitle);
        //news.setType("无");
        return adminNewsService.addNews(news);
        //return false;
    }

    @GetMapping("/getNewsList")
    public List<News> getNewsList(UsernamePasswordAuthenticationToken user){

        return adminNewsService.getNewsList( ((User)user.getPrincipal()).getId());
    }


    @GetMapping("/getSchoolOS")
    public List getSchool(Integer id){
        LOGGER.info("id: {}",id);
        if(id == null)id = 1;
       return schoolOSService.getSchhoByID(id);
    }

    @PostMapping("/setNewsPub")
    public boolean getSchool(String list,Integer newsId) throws JSONException {
        LOGGER.info("id: {}",list);
        //if(id == null)id = 1;
        JSONArray json = new JSONArray(list);
        JSONObject jsonOne;
        //Map<String,String> map=null;
        List<Map> scMap = new ArrayList<>();
        List<Map> userMap = new ArrayList<>();
        for(int i=0;i<json.length();i++){
            Map updataMap = new HashMap<String,String>();

            jsonOne = json.getJSONObject(i);
            updataMap.put("name", (String) jsonOne.get("name"));
            updataMap.put("id", (Integer) jsonOne.get("id"));
            if( jsonOne.has("remark")) {
                scMap.add(updataMap);
            }else{
                userMap.add(updataMap);

            }
        }
        LOGGER.info("newsID :{}",newsId);
        return  adminNewsService.pubNews(scMap,  userMap,newsId);
    }
}
