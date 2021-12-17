@echo off
@set h=%time:~0,2%
@set h=%h: =0%
@set file=%date:~0,4%%date:~5,2%%date:~8,2%%h%%time:~3,2%%time:~6,2%
@md %CD%\jtl\%file%
@move %CD%\jtl\*.jtl %CD%\jtl\%file%\
@cd %CD%\bin\
@echo ------------------ jmx list -----------------
for /R "..\jmx" %%s in (*) do ( 
	@echo %%~ns.jmx
) 
@echo ------------------ Test Begin ------------------
for /R "..\jmx" %%s in (*) do ( 
	@echo ------------------ %%~ns begin ------------------
	@call jmeter -n -t %%s -l ..\jtl\%%~ns.jtl
	@echo ------------------- %%~ns end -------------------
) 
@echo ------------------ Test End ------------------
@pause
