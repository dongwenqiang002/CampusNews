package team.agile.campusnews.app.mapper;

import org.springframework.stereotype.Repository;
import team.agile.campusnews.app.model.StudentInfo;
@Repository
public interface StudentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentInfo record);

    int insertSelective(StudentInfo record);

    StudentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentInfo record);

    int updateByPrimaryKey(StudentInfo record);
}