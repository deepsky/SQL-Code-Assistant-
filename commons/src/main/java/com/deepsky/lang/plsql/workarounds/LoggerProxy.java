/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.lang.plsql.workarounds;

import org.apache.log4j.Logger;

public abstract class LoggerProxy {

    public abstract void error(String message);
    public abstract void error(String message, Throwable e);
    public abstract void warn(String message);
    public abstract void info(String message);
    public abstract void debug(String message);

    public static LoggerProxy getInstance(String name){
        String e = System.getProperty("log.type");
        if("log4j".equalsIgnoreCase(e)){
            Logger l = Logger.getLogger(name);
            return new LoggerProxyLog4j(l);
        } else if("intellij.logger".equalsIgnoreCase(e)){
            // intellij idea's default logger
            com.intellij.openapi.diagnostic.Logger log = com.intellij.openapi.diagnostic.Logger.getInstance(name);
            return new LoggerProxyIntellij(log);
        } else {
            // dummy
            return new IdleLogger();
        }
    }

    static class LoggerProxyLog4j extends LoggerProxy {

        Logger logger;
        public LoggerProxyLog4j(Logger logger){
            this.logger = logger;
        }

        public void error(String message){
            logger.error(message);
        }

        public void error(String message, Throwable e){
            logger.error(message, e);
        }

        public void warn(String message){
            logger.warn(message);
        }

        public void info(String message){
            logger.info(message);
        }

        public void debug(String message){
            logger.debug(message);
        }
    }

    static class LoggerProxyIntellij extends LoggerProxy {

        com.intellij.openapi.diagnostic.Logger logger;
        public LoggerProxyIntellij(com.intellij.openapi.diagnostic.Logger logger){
            this.logger = logger;
        }

        public void error(String message){
            logger.error(message);
        }

        @Override
        public void error(String message, Throwable e) {
            logger.error(message, e);
        }

        public void warn(String message){
            logger.warn(message);
        }

        public void info(String message){
            logger.info(message);
        }

        public void debug(String message){
            logger.debug(message);
        }
    }

    static class IdleLogger extends LoggerProxy {
        public IdleLogger(){
        }

        public void error(String message){
        }

        @Override
        public void error(String message, Throwable e) {
        }

        public void warn(String message){
        }

        public void info(String message){
        }

        public void debug(String message){
        }
    }
}
