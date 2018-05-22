package team.agile.campusnews.app.dao;

import org.springframework.stereotype.Repository;
import team.agile.campusnews.app.model.SchoolOs;

import java.util.List;

@Repository
public interface SchoolOsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SchoolOs record);

    int insertSelective(SchoolOs record);

    SchoolOs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SchoolOs record);

    int updateByPrimaryKey(SchoolOs record);

    List<SchoolOs> selectByUserId(Integer userId);
}