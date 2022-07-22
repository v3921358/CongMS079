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
	status++;
	if (status == 0) {
		qm.sendNext("嘿! 你可以帮我个忙吗？#p20000#似乎有点怪，这些天...");
	} else if (status == 1) {
		qm.sendNext("他曾皱眉，并抱怨他的关节炎直到最近，但他的突然成为所有快乐和笑脸!!");
	} else if (status == 2) {
		qm.sendNext("我有一种感觉有背后木箱秘密。你可以悄悄地窥视木箱旁#p20000#?");
	} else {
		qm.sendNext("你知道在哪里 #p20000#是对的？他的右边。只要保持下去，直到你看到海盗是，低着头过去的悬挂鲨鱼和章鱼，并会带您看看约翰。这个盒子应该是他旁边.");
		qm.forceStartQuest();
		qm.dispose();
	}
}
function end(mode, type, selection) {
	qm.gainExp(200);
	qm.forceCompleteQuest();
	qm.dispose();
}
