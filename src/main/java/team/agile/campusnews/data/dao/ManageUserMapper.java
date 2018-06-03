package team.agile.campusnews.data.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import team.agile.campusnews.data.model.ManageUser;

@Repository
public interface ManageUserMapper  {
    int deleteByPrimaryKey(Integer id);

    int insert(ManageUser record);

    int insertSelective(ManageUser record);

    ManageUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManageUser record);

    int updateByPrimaryKey(ManageUser record);

    ManageUser selectByUserName(String userName);
}