package team.agile.campusnews.app.dao;

import team.agile.campusnews.app.model.Organize;

public interface OrganizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Organize record);

    int insertSelective(Organize record);

    Organize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Organize record);

    int updateByPrimaryKey(Organize record);
}