package team.agile.campusnews.app.mapper;

import org.springframework.stereotype.Repository;
import team.agile.campusnews.app.model.News;
@Repository
public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}