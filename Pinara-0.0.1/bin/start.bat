type README.txt
@echo off
Chcp 1254
rem java -Duser.country=ENTR -Duser.language=entr -jar pinara-0.9.3.jar> nohup.out 2> nohup.err
java -Duser.country=ENTR -Duser.language=entr -jar pinara-0.9.3.jar 2> error.log


