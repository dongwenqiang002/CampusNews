package team.agile.campusnews.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.agile.campusnews.data.dao.SchoolOsMapper;
import team.agile.campusnews.data.model.SchoolOs;

import java.util.List;

/**
 * @Time 2018/5/24 19:57
 */
@Service
public class SchoolOsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolOsService.class);
    @Autowired
    private SchoolOsMapper schoolOsMapper;


    public List<SchoolOs> getSchhoByID(String schhoID) {
        try {
            Integer id = Integer.parseInt(schhoID);
            List<SchoolOs> schoolOsList = schoolOsMapper.selectByParentId(id);
            if(schoolOsList == null ||schoolOsList.isEmpty()||schoolOsList.size()==0){
                return null;
            }
            return schoolOsList;
        }catch (Exception e){
            LOGGER.error(e.toString());
            return null;
        }

    }
}
