package team.agile.campusnews.app.dao;

import team.agile.campusnews.app.model.News;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News> selectBySchoolOsID(Integer id);

    List<News> selectByUserId(Integer id);
}