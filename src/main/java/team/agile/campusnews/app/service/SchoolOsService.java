package team.agile.campusnews.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.agile.campusnews.app.dao.SchoolOsMapper;
import team.agile.campusnews.app.model.SchoolOs;

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
            return schoolOsMapper.selectByParentId(id);
        }catch (Exception e){
            LOGGER.error(e.toString());
            return null;
        }

    }
}
