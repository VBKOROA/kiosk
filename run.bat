@echo off
echo build
CALL .\gradlew clean build
echo run
java -jar app/build/libs/app.jar
