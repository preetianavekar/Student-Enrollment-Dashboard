@echo off
set MAVEN_VERSION=3.9.6
set MAVEN_DIR=%~dp0apache-maven-%MAVEN_VERSION%
set MAVEN_ZIP=%~dp0maven.zip
set MAVEN_URL=https://archive.apache.org/dist/maven/maven-3/%MAVEN_VERSION%/binaries/apache-maven-%MAVEN_VERSION%-bin.zip

@rem Check for Java
java -version >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo ERROR: Java is not installed or not in your PATH. 
    echo Please install Java 11 or higher to run this project.
    pause
    exit /b 1
)

if not exist "%MAVEN_DIR%\bin\mvn.cmd" (
    echo Maven not found. Downloading Maven %MAVEN_VERSION%...
    "%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -Command "& { [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri '%MAVEN_URL%' -OutFile '%MAVEN_ZIP%' }"
    if %ERRORLEVEL% neq 0 (
        echo Failed to download Maven.
        pause
        exit /b 1
    )
    echo Extracting Maven...
    "%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -Command "Expand-Archive -Path '%MAVEN_ZIP%' -DestinationPath '%~dp0' -Force"
    if %ERRORLEVEL% neq 0 (
        echo Failed to extract Maven.
        pause
        exit /b 1
    )
    del "%MAVEN_ZIP%"
)

"%MAVEN_DIR%\bin\mvn.cmd" %*
