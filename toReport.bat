@echo off
@set h=%time:~0,2%
@set h=%h: =0%
@set file=%date:~0,4%%date:~5,2%%date:~8,2%%h%%time:~3,2%%time:~6,2%
@set resRet=%CD%\ResultReport\%file%
@set jtlpath=%CD%\jtl
@md %resRet%
@echo ------------------ jtl list ------------------
for /f "delims=" %%i in ('dir /a-d/b "%jtlpath%"') do @echo  %%i
@echo ------------------ jtl list ------------------
@cd %CD%\bin\
@copy /y reportgenerator.properties.win reportgenerator.properties
@echo ------------------ Report Begin ------------------
for /f "delims=" %%s in ('dir /a-d/b "%jtlpath%"')  do (
	@call jmeter -g %jtlpath%\%%s -o %resRet%\%%~ns
	@echo  %%~ns.jtl end 
)
@cd ..
@echo ------------------- Report End -------------------
@echo ���ɵ�Html����Ŀ¼��%resRet%
@echo.
@echo �Զ����ɱ������
@echo.
@start ���ܲ��Ա������.exe %resRet%
@pause
