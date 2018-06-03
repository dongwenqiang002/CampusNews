package team.agile.campusnews.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.agile.campusnews.data.dao.SchoolOsMapper;
import team.agile.campusnews.data.model.SchoolOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Time 2018/5/24 19:57
 */
@Service
public class SchoolOsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolOsService.class);
    @Autowired
    private SchoolOsMapper schoolOsMapper;
    @Autowired
    private UserService userService;

    public List<SchoolOs> getSchhoByID(String schhoID) {
        try {
            Integer id = Integer.parseInt(schhoID);
            List schoolOsList = this.schoolOsMapper.selectByParentId(id);
            return schoolOsList;
        }catch (Exception e){
            LOGGER.error(e.toString());
            return null;
        }

    }
    public List<Object> getSchhoByID(Integer schhoID) {
        try {
            List schoolOsList = schoolOsMapper.selectByParentId(schhoID);
            if(schoolOsList == null ||schoolOsList.isEmpty()||schoolOsList.size()==0){
                schoolOsList = new ArrayList();
            }
            List userList = userService.getSchoolOSUser(schhoID);
            schoolOsList.addAll(userList);
            if(schoolOsList == null ||schoolOsList.isEmpty()||schoolOsList.size()==0){
                schoolOsList = null;
            }
            if(schhoID==1){
                schoolOsList.add(0,schoolOsMapper.selectSchool());
            }
            return schoolOsList;
        }catch (Exception e){
            LOGGER.error(e.toString());
            return null;
        }
    }


}
