type README.txt
@echo off
Chcp 1254
#java -Duser.country=ENTR -Duser.language=entr -jar pinara-0.0.1.jar> nohup.out 2> nohup.err
java -Duser.country=ENTR -Duser.language=entr -jar pinara-0.0.1.jar 2> nohup.err


