package team.agile.campusnews.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.agile.campusnews.data.model.News;
import team.agile.campusnews.data.model.User;
import team.agile.campusnews.app.service.NewsService;
import team.agile.campusnews.app.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

/**
 *
 * @Time 2018/5/17 13:37
 * 用来获取自己可以看到的新闻
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    private NewsService newsService;
    @Autowired
    private UserService userServicel;

    @GetMapping("/")
    public List<News> index(){
        return  newsService.getNews();
    }


    @GetMapping("/get")
    public List<News> getNews(Principal user, HttpServletRequest request) {
        String userName = user.getName();

        //学生获取新闻
        if (request.isUserInRole("学生")) {
            return newsService.getNews(userName);
        } else {
            return null;
        }
        //  return null;
    }
    @GetMapping("/detail")
    public News getNews(Integer newsId, HttpServletRequest request) {
       return  newsService.getNews(newsId);
        //  return null;
    }
    @GetMapping("/addNews")
    public List<News> addNews(Principal user, HttpServletRequest request, String context) {
        String userName = user.getName();
        News news = new News();

        //news.setAuthor();
        //学生获取新闻
        if (request.isUserInRole("学生")) {
            return newsService.getNews(userName);
        } else {
            return null;
        }
        //  return null;
    }

    /**
     * 获取用户个人信息
     * */
    @GetMapping("/userDetail")
    public String userDetail(Principal user){

        user.getName();
        User u = (User) userServicel.loadUserByUsername(user.getName());
        //user
        //  user.setPassword("123");
        return "[\n" +
                "  {\n" +
                "  \"name\": \"姓名\",\n" +
                "  \"data\": \""+u.getName()+"\"\n" +
                "  }, {\n" +
                "  \"name\": \"学院\",\n" +
                "  \"data\": \""+u.getSchoolOs().get(0).getParentSchoolOs().getName()+"\"\n" +
                "}, {\n" +
                "  \"name\": \"班级\",\n" +
                "  \"data\": \""+u.getSchoolOs().get(0).getName()+"\"\n" +
                "},{\n" +
                " \"name\": \"学号\",\n" +
                "  \"data\": \""+u.getCode()+"\"\n" +
                "}\n" +
                "  \n" +
                "]";
    }

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
}
