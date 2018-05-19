package team.agile.campusnews.app.dao;

import org.springframework.stereotype.Repository;
import team.agile.campusnews.app.model.NewsDetail;

@Repository
public interface NewsDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsDetail record);

    int insertSelective(NewsDetail record);

    NewsDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsDetail record);

    int updateByPrimaryKey(NewsDetail record);
}