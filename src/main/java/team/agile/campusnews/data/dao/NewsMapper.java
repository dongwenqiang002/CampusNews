package team.agile.campusnews.data.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import team.agile.campusnews.data.model.News;

import java.util.List;

@Repository
public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News> selectBySchoolOsID(Integer schoolOsID);

    List<News> selectByUserId(Integer userId);

    List<News> selectByUserAll(@Param("userId") Integer userId, @Param("sIds") List<Integer> schoolOsId);
}