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
    if (mode == 1) {
	status++;
    } else {
	status--;
    }
    if (status == 0) {
	cm.sendNextS("我是弗兰西斯，黑色翅膀的木偶。你怎么敢打扰我的木偶…这真的让我不安，但我会让它幻灯片这个时候。如果我抓到你再做一次，我以黑色法师的名义发誓，我会让你付出。 ", 9);
    } else if (status == 1) {
	cm.sendNextPrevS("#b（黑色的翅膀？嗯？他们是谁？所有这些都与黑魔法师有什么关系？嗯，也许你应该报告这个信息，真的。）#k", 3);
    } else if (status == 2) {
	cm.forceStartQuest(21760, "0");
	cm.warp(105070300, 3);
	cm.dispose();
    }
}