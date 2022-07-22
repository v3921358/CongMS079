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

function action(mode, type, selection) {
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
		if (cm.getQuestStatus(21612) == 1 || cm.getQuestStatus(21614) == 1) {
			cm.sendNext("你是來找我们老大的是吧...");
		} else {
			cm.sendOk("是人类吗？？沒事的话赶紧离开这里吧！");
			cm.dispose();
		}
    } else if (status == 1) {
		cm.sendNext("那我就帶你去见我们老大吧!");
	} else if (status == 2) {
		cm.warp(140010210,0);
		cm.dispose();
	}
}