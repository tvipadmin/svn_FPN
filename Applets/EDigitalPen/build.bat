@echo off
echo ###################################################################
echo 		Building StartVitalTrack.jar
echo ###################################################################

REM **** CREATE STAGING DIRECTORIES ****
SET ROOT=..\


javac  -cp ".;commons-httpclient.jar" -d . *.java
if NOT %errorlevel%==0 GOTO end

jar -cvf StartDigitalPen.jar *.class 
if NOT %errorlevel%==0 GOTO VERISIGN

REM jar tvf start.jar
if NOT %errorlevel%==0 GOTO jarv
REM jarsigner -keystore televital -storepass TeleVital -keypass TeleVital -signedjar StartVitalTrack.jar start.jar signFiles

jarsigner -keystore tvstore  StartDigitalPen.jar tvkey
REM jarsigner -keystore tvstore  commons-fileupload-1.2.1.jar tvkey
jarsigner -keystore tvstore  commons-httpclient.jar tvkey
REM jarsigner -keystore tvstore  commons-io-1.4.jar tvkey

if NOT %errorlevel%==0 GOTO jarc

if NOT %errorlevel%==0 GOTO jarcv

del *.class

goto end

:java
echo cannot compile files
goto end

:jars
echo Cannot make jar 
goto end

:jarv
echo Cannot test jar file
goto end

:jarc
echo Cannot sign the jar file 
goto end

:jarcv
echo Cannot verify jar file
goto end

:end
pause
