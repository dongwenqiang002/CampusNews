package team.agile.campusnews.app.mapper;

import org.springframework.stereotype.Repository;
import team.agile.campusnews.app.model.User;
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByUserName(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


}