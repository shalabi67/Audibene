package com.shalabi.logging;

import java.util.logging.Level;

/**
 * Logger enables application logging. it supports exception, error, warning, and info levels.
 * at this stage it supports only console logging. it supports method start and end logging.
 *
 *
 * @author mohammad
 */
public class Logger {
    protected java.util.logging.Logger logger = null;

    private static boolean methodLogging = false;
    private static Logger loging = null;

    /**
     *enables method starts and end logging
     * @param flag if true method logging will be enable and every time a method is called a start and end method logs will be loged.
     */
    public static void enableMethodLogging(boolean flag){
        methodLogging = flag;
    }

    /**
     * this methods create a logger singleton object. It sets the level logging to INFO.
     * @return Logger singleton object.
     */
    public static Logger create()
    {
        if(loging == null)
        {
            Logger log = new Logger();
            log.logger = java.util.logging.Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);

            log.logger.setLevel(Level.INFO);

            loging = log;
        }
        return loging;
    }

    protected void log(Level level, String message)
    {
        logger.log(level, message);
    }
    protected void logException(String message)
    {
        log(Level.SEVERE, message);
    }
    protected void logError(String message)
    {
        log(Level.SEVERE, message);
    }
    protected void logWarning(String message)
    {
        log(Level.WARNING, message);
    }
    protected void logInfo(String message)
    {
        log(Level.INFO, message);
    }

    public static void LogException(String message)
    {
        Logger.create().logException(message);
    }
    public static void LogError(String message)
    {
        Logger.create().logError(message);
    }
    public static void LogWarning(String message)
    {
        Logger.create().logWarning(message);
    }
    public static void LogInfo(String message)
    {
        Logger.create().logInfo(message);
    }

    /**
     * This method logs a method information. it logs the class of the methos, method name, is this starting of ther method or ending of it, and a message.
     * if method logging is false this method will not log any information. this is used to improve system performance.
     * @param name this is a class object we of the calling method.
     * @param methodName this is the method name we want to log
     * @param message the message we want to log
     * @param status either a "start" or "end" strings
     */
    private static void LogMethod(Class name, String methodName, String message,  String status)
    {
        if(!methodLogging)
            return;
        Logger.create().logInfo(name.getName()  +  "::" + methodName + status + message);
    }

    /**
     * what is the calling method name. if methodLogging is false this will return an empty string.
     * @return calling method name
     */
    public static String getMethodName() {
        if(!methodLogging)
            return "";
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    /**
     * This method will log the class name, method name had been started
     * @param name this is a class object we of the calling method.
     * @param methodName this is the method name we want to log
     * @param message the message we want to log
     */
    public static void LogMethodStart(Class name, String methodName, String message) {
        LogMethod(name, methodName, message, " start ");
    }
    /**
     * This method will log the class name, method name had been ended
     * @param name this is a class object we of the calling method.
     * @param methodName this is the method name we want to log
     * @param message the message we want to log
     */
    public static void LogMethodEnd(Class name, String methodName, String message) {
        LogMethod(name, methodName, message, " end ");
    }

    /**
     * This method will log the class name, method name had been started
     * @param name this is a class object we of the calling method.
     * @param methodName this is the method name we want to log
     * */
    public static void LogMethodStart(Class name, String methodName) {
        LogMethodStart(name, methodName, "");
    }

    /**
     * This method will log the class name, method name had been ended
     * @param name this is a class object we of the calling method.
     * @param methodName this is the method name we want to log
     * */
    public static void LogMethodEnd(Class name, String methodName) {
        LogMethodEnd(name, methodName, "");
    }

}
