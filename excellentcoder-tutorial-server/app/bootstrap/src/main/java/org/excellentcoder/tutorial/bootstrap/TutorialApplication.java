package org.excellentcoder.tutorial.bootstrap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "org.excellentcoder.tutorial")
@MapperScan(basePackages = "org.excellentcoder.tutorial.common.dal.mapper") //没找到可以去除的办法，参见tutorial-dal-db
public class TutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutorialApplication.class, args);
    }

}
