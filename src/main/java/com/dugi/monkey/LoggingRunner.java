package com.dugi.monkey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class LoggingRunner implements ApplicationRunner {
    private Logger logger
             = LoggerFactory.getLogger(LoggingRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.trace("trace로그");
        logger.debug("dubug로그");
        logger.info("info로그");
        logger.warn("warn로그");
        logger.error("error로그");
    }
}
