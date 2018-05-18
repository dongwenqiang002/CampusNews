package team.agile.campusnews.app.dao;

import team.agile.campusnews.app.model.PublishNews;

public interface PublishNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PublishNews record);

    int insertSelective(PublishNews record);

    PublishNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PublishNews record);

    int updateByPrimaryKey(PublishNews record);
}