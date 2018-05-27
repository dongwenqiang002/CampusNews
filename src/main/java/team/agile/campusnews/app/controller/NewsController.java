package team.agile.campusnews.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.agile.campusnews.app.model.News;
import team.agile.campusnews.app.service.NewsService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * @author 董文强
 * @Time 2018/5/17 13:37
 * 用来获取自己可以看到的新闻
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    private NewsService newsService;

    @GetMapping("/get")
    public List<News> getNews(Principal user, HttpServletRequest request) {
        String userName = user.getName();

        //学生获取新闻
        if (request.isUserInRole("学生")) {
            return newsService.getNews(userName);
        }else{
            return null;
        }
      //  return null;
    }
    @GetMapping("/addNews")
    public List<News> addNews(Principal user, HttpServletRequest request,String context) {
        String userName = user.getName();
        News news = new News();

        //news.setAuthor();
        //学生获取新闻
        if (request.isUserInRole("学生")) {
            return newsService.getNews(userName);
        }else{
            return null;
        }
        //  return null;
    }
    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }
}
