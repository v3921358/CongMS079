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
    if (mode == 0 && status == 1) {
	qm.sendNext("我猜你还没准备好。");
	qm.dispose();
	return;
    } else if (mode == 0) {
	status--;
    } else {
	status++;
    }

    if (status == 0) {
	qm.sendNext("你所带回来的宝石是神兽的眼泪，它拥有非常强大的力量。如果被黑磨法师给得手了，那我们全部都可能要倒大楣了....");
    } else if (status == 1) {
	qm.sendYesNo("女皇为了报答你的努力，将任命你为皇家骑士团的上级骑士，你准备好了嘛？");
    } else if (status == 2) {
	if (qm.getPlayerStat("RSP") > (qm.getPlayerStat("LVL") - 70) * 3) {
	    qm.sendNext("请确认你的技能点数点完没。");
	} else {
	    if (qm.canHold(1142068)) {
		qm.removeAll(4032179);
		qm.gainItem(1142068, 1);
		qm.changeJob(1211);
		qm.gainAp(5);
		qm.sendOk("因为这一刻，你现在是高级火焰魔法师。从这一刻起，你是受万人尊严和尊重的高级火焰魔法师。");
	    } else {
		qm.sendOk("请先把道具栏空出一些空间哦。");
	    }
	}
	qm.dispose();
    }
}

function end(mode, type, selection) {
}