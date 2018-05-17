package team.agile.campusnews;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author 董文强
 * @Time 2018/5/17 12:15
 */
public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
       BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
       String c = passwordEncoder.encode("123");
        System.out.println(c);
        System.out.println(passwordEncoder.matches("123",c));
    }
}
