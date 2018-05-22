package team.agile.campusnews.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.agile.campusnews.app.dao.NewsMapper;
import team.agile.campusnews.app.dao.UserMapper;
import team.agile.campusnews.app.model.News;
import team.agile.campusnews.app.model.SchoolOs;
import team.agile.campusnews.app.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 董文强
 * @Time 2018/5/17 13:54
 */
@Service
public class NewsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsService.class);

    private UserMapper userMapper;
    private NewsMapper newsMapper;

    public List<News> getNews(String userName) {
        List<News> list =  new ArrayList<>(15);
        //获取用户实体
        User user = userMapper.selectByUserName(userName);
        //user.getRole()
        //该用户可以看到的新闻
        List<SchoolOs> schoolOs = user.getSchoolOs();
        //通过用户所属组织获取 用户可以接受的组织新闻;
        schoolOs.forEach(v-> list.addAll(newsMapper.selectBySchoolOsID(v.getId())));
        list.addAll(newsMapper.selectByUserId(user.getId()));

        return list;
    }


    @Autowired
    public NewsService(UserMapper userMapper,NewsMapper newsMapper) {
        this.userMapper = userMapper;
        this.newsMapper = newsMapper;
        /*this.stuClassMapper = stuClassMapper;*/
    }
}
