package com.example.triangle.myLogger;

import com.example.triangle.controllers.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLogger {
    static Logger logger = LoggerFactory.getLogger(MainController.class);
    public static void info(String message){
        logger.info(message);
    }

    public static void warn(String message){
        logger.warn(message);
    }

    public static void error(String message){
        logger.error(message);
    }
}