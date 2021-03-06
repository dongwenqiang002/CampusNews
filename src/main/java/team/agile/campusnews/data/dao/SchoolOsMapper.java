package team.agile.campusnews.data.dao;

import org.springframework.stereotype.Repository;
import team.agile.campusnews.data.model.SchoolOs;

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

    List<SchoolOs> selectByParentId(Integer id);

    SchoolOs selectSchool();
}