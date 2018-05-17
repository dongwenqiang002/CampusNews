package team.agile.campusnews.app.mapper;

import org.springframework.stereotype.Repository;
import team.agile.campusnews.app.model.PublishNew;
@Repository
public interface PublishNewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PublishNew record);

    int insertSelective(PublishNew record);

    PublishNew selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PublishNew record);

    int updateByPrimaryKey(PublishNew record);
}