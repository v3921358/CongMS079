@echo off
@title   CongMS(������ɹرգ�QQ327321366
Color 0A
set path=config\bin;%SystemRoot%\system32;%SystemRoot%;%SystemRoot%
set CLASSPATH=.;lib\*;

javaw -XX:-UseGCOverheadLimit -Xms512m -Xmx1024m -Xss128k -Dnet.sf.odinms.wzpath=wz gui.CongMS
pause
