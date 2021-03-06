package team.agile.campusnews.data.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.agile.campusnews.data.model.User;

import java.util.Date;
import java.util.List;

@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int regUserS(@Param("userId") Integer userId, @Param("schoolId") Integer schoolId);

    int regUserR(@Param("userId") Integer userId, @Param("role") String role_name);

    int regUserStu(@Param("stu_code") String stuCode, @Param("user_id") Integer userId, @Param("school_time") Date schoolTime);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(String userName);

    List selectBySchoolId(Integer schhoID);
}