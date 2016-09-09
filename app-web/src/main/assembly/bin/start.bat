@echo off & setlocal enabledelayedexpansion
title appdemo

set LIB_JARS=""
cd ..\lib
for %%i in (*) do set LIB_JARS=!LIB_JARS!;..\lib\%%i
cd ..\bin

set DEPLOY_DIR="%~dp0.."
set LIB_DIR="%~dp0..\lib"

SET conf_file=%~dp0..\conf\cooas.properties

for /F "eol=# tokens=1,2 delims==" %%i in (!conf_file!) do (
if %%i == cooas.application.name set SERVER_NAME=%%j
if %%i == cooas.jetty.host set SERVER_HOST=%%j
if %%i == cooas.jetty.port set SERVER_PORT=%%j
if %%i == cooas.jmxport set SERVER_JMX_PORT=%%j
if %%i == cooas.debugport set SERVER_DEBUG_PORT=%%j
)

if "x%SERVER_PORT%" == "x" (
echo cooas.jetty.port is required!!!
pause
exit 1
)

if "x%SERVER_JMX_PORT%" == "x" (
set /a SERVER_JMX_PORT=%SERVER_PORT% + 10
)
if "x%SERVER_DEBUG_PORT%" == "x" (
set /a SERVER_DEBUG_PORT=%SERVER_PORT% + 20
)

set CLASS_PATH_PARAM=-Djna.library.path=%LIB_DIR% -classpath ..\conf;..\lib;%LIB_JARS%
set CLASS_PARAM=-Dhscooas.home=%DEPLOY_DIR% -Dcooas.jetty.webappPath=..\webapp com.xsoft.appdemo.jetty.JettyServer


if ""%1"" == ""debug"" goto debug
if ""%1"" == ""jmx"" goto jmx

"%JAVA_HOME%"\bin\java -Xms64m -Xmx1024m -XX:MaxPermSize=64M %CLASS_PATH_PARAM% %CLASS_PARAM%
goto end

:debug
"%JAVA_HOME%"\bin\java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=%SERVER_DEBUG_PORT%,server=y,suspend=n %CLASS_PATH_PARAM% %CLASS_PARAM%
goto end

:jmx
"%JAVA_HOME%"\bin\java -Xms64m -Xmx1024m -XX:MaxPermSize=64M -Dcom.sun.management.jmxremote.port=%SERVER_JMX_PORT% -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false %CLASS_PATH_PARAM% %CLASS_PARAM%

:end
pause