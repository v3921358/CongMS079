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

var status = -1;

function end(mode, type, selection) {
    if (mode == 0) {
	if (status == 0) {
	    qm.sendNext("这个決定..非常重要.");
	    qm.safeDispose();
	    return;
	}
	status--;
    } else {
	status++;
    }
    if (status == 0) {
	qm.sendYesNo("你決定好了嘛? 这会是你最后的决定唷，所以想清楚你要做什么.你想要成为暗夜行者嘛?");
    } else if (status == 1) {
	qm.sendNext("恭喜成功转职。");
	if (qm.getJob() != 1400) {
	    qm.gainItem(1472061, 1);
	    qm.gainItem(2070015, 800);
	    qm.gainItem(2070015, 800);
	    qm.gainItem(1142066, 1);
		qm.resetStats(4, 4, 4, 4);
	    qm.expandInventory(1, 4);
	    qm.expandInventory(4, 4);
	    qm.changeJob(1400);
	}
	qm.forceCompleteQuest();
    } else if (status == 2) {
	qm.sendNextPrev("我还扩充您身上的装备栏空间");
    } else if (status == 3) {
	qm.sendNextPrev("好运！.");
	qm.safeDispose();
    }
}