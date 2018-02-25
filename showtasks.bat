call runcrud.bat
if "%ERRORLEVEL%"=="0" goto openexplorer
.echo
echo runcrud.bat has errors - breaking work
goto fail

:openexplorer
start /d "C:\Program Files (x86)\Google\Chrome\Application" CHROME.EXE http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.