package com.company.project.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class LoggingUtils {


    public static void logLoadingTime(Logger logger, long startTime) {

        long finalTime = System.currentTimeMillis();
        long timeConsumed = finalTime - startTime;
        logger.log(Level.INFO, "Page loaded in " + timeConsumed + " ms");

    }
}
