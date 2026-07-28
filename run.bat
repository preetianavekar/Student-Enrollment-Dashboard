@echo off
echo ==============================================
echo Starting the web server...
echo ==============================================

call "%~dp0mvnw.cmd" clean jetty:run

if %ERRORLEVEL% neq 0 (
    echo.
    echo An error occurred while running the project.
    pause
)

