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
	qm.forceStartQuest();//开始任务
	//qm.sendNextS("#p1002104#说收到了和黑色之翼有关的情报，让我去见见#b#m200000000##k的#b妖精#p2012012##k。在#p2012012#那里，应该可以了解到详细的情况。", 3);
	qm.dispose();
}

function end(mode, type, selection) {
	//qm.teachSkill(21100005, qm.getPlayer().getSkillLevel(21100005), 10);   // Combo Ability 
	qm.forceCompleteQuest();//完成任务
	qm.dispose();
}