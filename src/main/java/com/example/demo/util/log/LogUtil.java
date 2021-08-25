package com.example.demo.util.log;

import org.slf4j.Logger;

/**
 * Created by 程祥 on 15/11/27.
 * Function：记录日志的公共方法
 */
public class LogUtil {
    private static final Logger commonLogger = new LogWrapper("CommonLogger");
    private static final Logger applicationLogger = new LogWrapper("ApplicationLogger");
    private static final Logger bigLogger = new LogWrapper("bigLogger");

    /**
     * 使用ChannelLogger,接入checklist用
     * @return
     */
    @Deprecated
    public static Logger getCommonLogger(){
        return commonLogger;
    }


    public static Logger getApplicationLogger(){
        return applicationLogger;
    }

    public static Logger getBigLogger(){
        return bigLogger;
    }

}
