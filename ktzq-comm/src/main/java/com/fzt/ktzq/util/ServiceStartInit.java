package com.fzt.ktzq.util;

import com.fzt.ktzq.factory.ApplicationContextFactory;
import org.springframework.context.ApplicationContext;



public class ServiceStartInit {
    private ServiceStartInit(){

    }

    public static void initSystemConfig(ApplicationContext context){
        try {
            ApplicationContextFactory.setApplicationContext(context);
        } catch (Exception ex){
            throw new IllegalStateException(CommConstant.ERROR_CODE, ex);
        }
    }
}
