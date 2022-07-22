
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
    if (pi.getQuestStatus(21728) == 1) {
        pi.warp(910510001, 0);
        return true;
    } else{
	 if (pi.getPlayerCount(910510201) <= 0 && pi.isQuestActive(21731) ==1 ) {//判断地图是否有人。判断任务
	pi.getMap(910510201).resetFully();//地图刷新
	pi.spawnMobOnMap(9300344,1,220,240,910510201);//召唤怪物
    pi.warp(910510201);
        return true;
    } else {
        pi.playerMessage(5, "里面有人或者没有接受相关任务而无法进入此洞穴。");
        return false;
    }
}
}