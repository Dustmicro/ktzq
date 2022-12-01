package com.fzt.ktzq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
//@EnableTransactionManagement//开启事务管理
@MapperScan("com.fzt.ktzq.mapper")
public class KtzqApplication {
    private static final Logger logger = LoggerFactory.getLogger(KtzqApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(KtzqApplication.class, args);
        logger.info("启动成功！！ ");
    }

}
