var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
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
	if (pi.isQuestFinished(21733)) {
	pi.warp(104000004, 1);
	} else if (!pi.getPlayerCount(910400000) <= 0 && pi.isQuestFinished(21732)) {//判断地图是否有人。判断任务
	pi.playerMessage(5, "里面有人,请稍后.");
	} else if (!pi.isQuestFinished(21732)) {
	pi.warp(104000004, 1);
	} else{
	pi.getMap(910400000).resetFully();//地图刷新
	pi.spawnMobOnMap(9300345,1,-21,120,910400000);
    pi.warp(910400000, 1);
}
}