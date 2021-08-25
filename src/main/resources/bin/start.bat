@echo off
:dynClasspath
set JAVA_HOME=/usr/local/jdk1.8
set DEPLOY_HOME=..
set _CLASSPATH=.;%DEPLOY_HOME%\lib\*;%DEPLOY_HOME%\config
start %JAVA_HOME%\bin\java -cp %_CLASSPATH% -Xms256M -Xmx1024M org.springframework.boot.loader.JarLauncher
