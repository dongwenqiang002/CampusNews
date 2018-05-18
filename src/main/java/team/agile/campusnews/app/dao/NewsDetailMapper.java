package team.agile.campusnews.app.dao;

import team.agile.campusnews.app.model.NewsDetail;

public interface NewsDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsDetail record);

    int insertSelective(NewsDetail record);

    NewsDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsDetail record);

    int updateByPrimaryKey(NewsDetail record);
}