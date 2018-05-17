package team.agile.campusnews;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("team.agile.campusnews.app.mapper")
public class CampusNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusNewsApplication.class, args);
    }

}
