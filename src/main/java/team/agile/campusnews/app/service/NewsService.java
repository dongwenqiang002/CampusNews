package team.agile.campusnews.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.agile.campusnews.data.dao.NewsMapper;
import team.agile.campusnews.data.dao.SchoolOsMapper;
import team.agile.campusnews.data.dao.UserMapper;
import team.agile.campusnews.data.model.News;
import team.agile.campusnews.data.model.SchoolOs;
import team.agile.campusnews.data.model.User;

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
    private SchoolOsMapper schoolOsMapper;

    /**
     * @param userName 用户名
     * @return 此用户可以看到的新闻
     * @throws null
     */
    public List<News> getNews(String userName) {
        List<News> list = new ArrayList<>(15);
        try {
            //获取用户实体
            User user = userMapper.selectByUserName(userName);

            //该用户可以看到的新闻
            List<SchoolOs> schoolOs = user.getSchoolOs();
            List<Integer> schoolOsIds = new ArrayList<>(5);
            //获取用户的组织关系及其上级
            schoolOs.forEach(v -> {
                schoolOsIds.add(v.getId());
                for (SchoolOs parentSchoolOs = v.getParentSchoolOs(); parentSchoolOs != null; parentSchoolOs = parentSchoolOs.getParentSchoolOs()) {
                    schoolOsIds.add(parentSchoolOs.getId());
                }
            });
            //通过用户ID和用户所属组织获取所有News
            List<News> newsList = newsMapper.selectByUserAll(user.getId(), schoolOsIds);
            newsList.sort((dt1,dt2) -> {
                if (dt1.getPubdate().getTime() < dt2.getPubdate().getTime()) {
                    return 1;
                } else if (dt1.getPubdate().getTime() > dt2.getPubdate().getTime()) {
                    return -1;
                } else {
                    return 0;
                }
            });
            return newsList;

        } catch (Exception e) {
            LOGGER.error(e.toString());
            return null;
        }


    }


    public News getNews(Integer newsId) {
        return newsMapper.selectByPrimaryKey(newsId);
    }

    /**
     * @return 学校大事件
     * @throws null
     */
    public List<News> getNews() {
        List<News> newsList =  newsMapper.selectBySchoolOsID(1);
        newsList.sort((dt1,dt2) -> {
            if (dt1.getPubdate().getTime() < dt2.getPubdate().getTime()) {
                return 1;
            } else if (dt1.getPubdate().getTime() > dt2.getPubdate().getTime()) {
                return -1;
            } else {
                return 0;
            }
        });
        return newsList;
    }

    @Autowired
    public NewsService(UserMapper userMapper, NewsMapper newsMapper, SchoolOsMapper schoolOsMapper) {
        this.userMapper = userMapper;
        this.newsMapper = newsMapper;
        this.schoolOsMapper = schoolOsMapper;
    }
}
