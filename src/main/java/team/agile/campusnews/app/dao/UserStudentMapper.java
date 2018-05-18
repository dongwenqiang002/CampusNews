package team.agile.campusnews.app.dao;

import team.agile.campusnews.app.model.UserStudent;

public interface UserStudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserStudent record);

    int insertSelective(UserStudent record);

    UserStudent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserStudent record);

    int updateByPrimaryKey(UserStudent record);
}