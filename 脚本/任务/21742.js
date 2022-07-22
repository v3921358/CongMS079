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
	//qm.sendNextS("不久之前，#p2090004#从一个自称是黑色之翼成员的名叫#r#o9300351##k的男人那里得到了#t4220151#。但是因为保管的失误，#t4220151#的内容全部不见了。必须复原#t4220151#，看看上面写的是什么内容。去向#b画师#p2091008##k咨询复原#t4220151#的方法吧,他就在#r武陵#k。", 3);
	//qm.removeAll(4220151);暂时没办法修复
	//qm.gainItem(4220151, 1);
	qm.forceStartQuest(21742);//开始任务
	qm.forceCompleteQuest(21742);//完成任务
	qm.forceStartQuest(21743);//开始任务
	qm.forceCompleteQuest(21743);//完成任务
	qm.forceStartQuest(21744);//开始任务
	qm.forceCompleteQuest(21744);//完成任务
	qm.forceStartQuest(21745);//开始任务
	qm.forceCompleteQuest(21745);//完成任务
	qm.forceStartQuest(21746);//开始任务
	qm.forceCompleteQuest(21746);//完成任务
	qm.forceStartQuest(21747);//开始任务
	qm.forceCompleteQuest(21747);//完成任务
	qm.forceStartQuest(21748);//开始任务
	qm.forceCompleteQuest(21748);//完成任务
	qm.sendNextS("恭喜你学会了战神突进技能,国服任务直接跳过了!。", 3);
	//qm.teachSkill(21100002,0,30,-1);//战神突进
	qm.teachSkill(21100002, qm.getPlayer().getSkillLevel(21100002), 30);   // Combo Ability 
	qm.dispose();
}


