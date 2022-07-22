var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣    唯一微信:ZerekY   ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
function enter(pi) {
	 if (pi.getPlayerCount(108010700) <= 0 && pi.isQuestActive(21301) ==1 ) {//判断地图是否有人。判断任务
	pi.getMap(108010700).resetFully();//地图刷新
    pi.warp(108010700);
	} else{
	if (pi.isQuestActive(21301) ==0 ) {
	pi.warp(140020300, 1);
	} else{
    pi.playerMessage(5, "里面有人.");
}
}}