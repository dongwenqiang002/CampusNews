package team.agile.campusnews.manage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.agile.campusnews.data.dao.NewsMapper;
import team.agile.campusnews.data.model.ManageUser;
import team.agile.campusnews.data.model.News;
import team.agile.campusnews.data.model.User;

import java.util.List;
import java.util.Map;

/**
 *
 * @Time 2018/6/3 17:36
 */
@Service
public class AdminNewsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminNewsService.class);

    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private AdminService adminService;
    public Integer addNews(News news) {
        try {
            if(newsMapper.insertSelective(news)!=1)return null;
        }catch (Exception e){
            LOGGER.error(e.getLocalizedMessage());
            return null;
        }
        return news.getId();

    }

    public List<News> getNewsList(String name) {
        User user = adminService.getManageUser(name);
        return newsMapper.selectByAuthorId(user.getId());
    }
    public List<News> getNewsList(Integer id) {
        //User user = adminService.getManageUser(id);
        List<News> newsList = newsMapper.selectByAuthorId(id);
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

    public boolean pubNews(List<Map> scMap, List<Map> userMap,Integer newsId) {
        scMap.forEach( v->{
            LOGGER.info("{}",v.get("name"));
            newsMapper.pubNews((Integer) v.get("id"),null,newsId);
        });
        LOGGER.info("---------------");
        userMap.forEach( v->{
            LOGGER.info("{}",v.get("name"));
            newsMapper.pubNews(null,(Integer) v.get("id"),newsId);
        });
        return true;
    }
}
