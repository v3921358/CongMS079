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
    if (mode == 1) {
	status++;
    } else {
	if (status == 2) {
	    qm.dispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	qm.sendNextS("是不是你曾经是在一个#m101000000# 直到不久以前？ 我终于找到你了！你知不知道过了多长时间，我终于找到你？", 8);
    } else if (status == 1) {
	qm.sendNextPrevS("你是谁?", 2);
    } else if (status == 2) {
	qm.askAcceptDecline("我？如果你想知道的，停止我的山洞。我甚至可以向你发送邀请。你只要你接受直接发送到我的山洞。期待与您相见.");
    } else if (status == 3) {
	qm.forceCompleteQuest(); 
	qm.warp(910510200, 0);
	qm.dispose();
    }
}

function end(mode, type, selection) {
    qm.dispose();
}

