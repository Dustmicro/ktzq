package com.fzt.ktzq;

import com.fzt.ktzq.util.ServiceStartInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
//@EnableTransactionManagement//开启事务管理
@MapperScan("com.fzt.ktzq.mapper")
public class KtzqApplication {
    private static final Logger logger = LoggerFactory.getLogger(KtzqApplication.class);
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(KtzqApplication.class, args);
        ServiceStartInit.initSystemConfig(context);
        logger.info("启动成功！！ ");
    }

}
