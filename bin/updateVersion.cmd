@echo off
cd ..
cls
call mvn versions:set -DnewVersion=B2.6.2
pause
::If you made a mistake, do
:: mvn versions:revert
::afterwards, or
call mvn versions:commit
::if you're happy with the results.

pause
