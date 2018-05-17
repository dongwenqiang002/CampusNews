package team.agile.campusnews.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.agile.campusnews.app.mapper.NewsMapper;
import team.agile.campusnews.app.mapper.UserMapper;
import team.agile.campusnews.app.model.News;
import team.agile.campusnews.app.model.User;

import java.util.List;

/**
 * @author 董文强
 * @Time 2018/5/17 13:54
 */
@Service
public class NewsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsService.class);

    private NewsMapper newsMapper;
    private UserMapper userMapper;

    public List<News> getNews(String userName) {
        //TODO 获取新闻
        //获取用户实体
        User user = userMapper.selectByUserName(userName);
        user.getRole()
        //学院新闻

        return null;
    }




    @Autowired
    public NewsService(NewsMapper newsMapper,UserMapper userMapper) {
        this.newsMapper = newsMapper;
        this.userMapper = userMapper;
    }
}
