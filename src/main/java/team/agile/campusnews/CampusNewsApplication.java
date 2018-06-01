package team.agile.campusnews;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("team.agile.campusnews.data.dao")
@EnableTransactionManagement(proxyTargetClass = true) //开启事务
public class CampusNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusNewsApplication.class, args);
    }

}
