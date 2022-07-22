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

function start(mode, type, selection) {
}

function end(mode, type, selection) {
    if (mode == 0) {
	if (status == 0) {
	    qm.sendNext("我猜你还没准备好。");
	    qm.dispose();
	    return;
	} else if (status >= 2) {
	    status--;
	} else {
	    qm.dispose();
	    return;
	}
    } else {
	status++;
    }
    if (status == 0) {
	if (qm.getQuestStatus(20205) == 0) {
	    qm.forceStartQuest();
	    qm.dispose();
	} else {
	    if (qm.haveItem(4032100, 30)) {
		qm.sendYesNo("所以，你准备好二转了？");
	    } else {
		qm.dispose(); // Hack
	    }
	}
    } else if (status == 1) {
	if (!qm.canHold(1142067)) {
	    qm.sendOk("请确认装备栏是否足够。");
	    qm.dispose();
	} else {
	    qm.forceCompleteQuest();
	    if (qm.getJob() != 1510) {
		qm.changeJob(1510); // Striker
		qm.gainItem(4032100, -30);
		qm.gainItem(1142067, 1);
	    }
	    qm.sendNext("訓練已經結束。你現在皇家騎士團的騎士官員。");
	}
    } else if (status == 2) {
	qm.sendPrev("好運！");
	qm.dispose();
    }
}