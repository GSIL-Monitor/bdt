package cn.com.infaith.module.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/4/14.
 */
public class LogUtil {
    final static String callerFQCN = LogUtil.class.getName();

    public static void error(Class<?> clazz, Throwable e) {
        Logger logger = Logger.getLogger(clazz);
        StackTraceElement[] trace = e.getStackTrace();
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < trace.length; i++)
            message.append("\tat " + trace[i]);
        logger.error(clazz.getSimpleName() + " error, error type is [" + e.getClass().getSimpleName() + "], error message[" + e.getMessage() + "], stack trace-->" + message, e);
    }

    public static void error(Class<?> clazz, Throwable e, String message, Object... objs) {
        Logger logger = Logger.getLogger(clazz);
        if (objs == null)
            logger.log(callerFQCN, Level.ERROR, message, e);
        else
            logger.log(callerFQCN, Level.ERROR, String.format(message, objs), e);
    }

    public static void error(Class<?> clazz, String message, Object... objs) {
        Logger logger = Logger.getLogger(clazz);
        if (objs == null)
            logger.log(callerFQCN, Level.ERROR, message, null);
        else
            logger.log(callerFQCN, Level.ERROR, String.format(message, objs), null);
    }

    public static void info(Class<?> clazz, String message, Object... objs) {
        Logger logger = Logger.getLogger(clazz);
        if (objs == null)
            logger.log(callerFQCN, Level.INFO, message, null);
        else
            logger.log(callerFQCN, Level.INFO, String.format(message, objs), null);
    }

    public static void info(Class<?> clazz, Object message) {
        Logger logger = Logger.getLogger(clazz);
        logger.log(callerFQCN, Level.INFO, message, null);
    }

    public static void debug(Class<?> clazz, String message, Object... objs) {
        Logger logger = Logger.getLogger(clazz);
        if (objs == null)
            logger.log(callerFQCN, Level.DEBUG, message, null);
        else
            logger.log(callerFQCN, Level.DEBUG, String.format(message, objs), null);
    }

    public static void debug(Class<?> clazz, Object message) {
        Logger logger = Logger.getLogger(clazz);
        logger.log(callerFQCN, Level.DEBUG, message, null);
    }

}
