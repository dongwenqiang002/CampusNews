package team.agile.campusnews.app.dao;

import team.agile.campusnews.app.model.UserOrganzie;

public interface UserOrganzieMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserOrganzie record);

    int insertSelective(UserOrganzie record);

    UserOrganzie selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserOrganzie record);

    int updateByPrimaryKey(UserOrganzie record);
}