@echo off
@title   CongMS(������ɹرգ�QQ327321366
Color 0A
set CLASSPATH=.;lib\*;

java -XX:-UseGCOverheadLimit -Xms512m -Xmx1024m -Dnet.sf.odinms.wzpath=wz gui.CongMS
pause
