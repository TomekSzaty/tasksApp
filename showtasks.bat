call C:\Users\tomek\Desktop\tasks\tasks\runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo Cannot start app
goto fail

:runbrowser
call  "C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe" http://localhost:8080/crud/v1/task/getTasks


:fail
echo There were errors

:end
echo Work is finished