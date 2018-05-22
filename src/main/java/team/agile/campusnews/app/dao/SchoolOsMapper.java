package team.agile.campusnews.app.dao;

import team.agile.campusnews.app.model.SchoolOs;

public interface SchoolOsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SchoolOs record);

    int insertSelective(SchoolOs record);

    SchoolOs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SchoolOs record);

    int updateByPrimaryKey(SchoolOs record);
}