@echo off
cd /d "%~dp0"
java --enable-native-access=ALL-UNNAMED -cp "bin;..\jline-3.27.1.jar;..\gson-2.13.2.jar" Lab03.Main
