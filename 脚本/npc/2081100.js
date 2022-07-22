
var status = -1;

function start() {
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == 0 && status == 0) {
		cm.dispose();
		return;
	}
	if (mode == 1)
		status++;
	else
		status--;

	if (status == 0) {
		if (!(cm.getJob() == 111 || cm.getJob() == 121 || cm.getJob() == 131)) {
			cm.sendOk("为什么你要见我??还有你想要问我关于什么事情??");
			cm.dispose();
			return;
			} else if (!cm.isQuestFinished(6900)) {
			cm.sendOk("你还没有去找泰勒斯接受相关任务吧吧。");
			cm.dispose();
			return;
			} else if (!cm.isQuestFinished(6904)) {
			cm.sendOk("请完成#d任务#r英雄的潜力#k在找我。");
			cm.dispose();
			return;
		} else if (cm.getPlayer().getLevel() < 120) {
			cm.sendOk("你等级尚未到达120级.");
			cm.dispose();
			return;
		} else {
			if (cm.getJob() == 111) {
				cm.sendSimple("恭喜你有资格4转,请问你想4转吗?\r\n#b#L0#我想成为英雄#l");
			} else if (cm.getJob() == 121) {
				cm.sendSimple("恭喜你有资格4转,请问你想4转吗?\r\n#b#L0#我想成为圣骑士#l");
			} else if (cm.getJob() == 131) {
				cm.sendSimple("恭喜你有资格4转,请问你想4转吗?\r\n#b#L0#我想成为黑骑士#l");
			} else {
				cm.sendOk("好吧假如你想要4转,请再来找我");
				cm.dispose();
				return;
			}
		}
	} else if (status == 1) {
		if (cm.getPlayerStat("RSP") > (cm.getPlayerStat("LVL") - 120) * 3) {
			cm.sendOk("你的技能点数还没点完..");
			cm.dispose();
			return;
		} else if (cm.getInventory(2).isFull(0)){//判断第二个也就是消耗栏的装备栏是否有一个空格		
            cm.sendOk("#b请保证消耗栏位至少有1个空格,要不然我可不能让你转职.");
            cm.dispose();
		} else {
				cm.gainItem(2280003, 1);
				if (cm.getJob() == 111) {
					cm.changeJob(112);
					cm.teachSkill(1121001, 0, 10);
					cm.teachSkill(1120004, 0, 10);
					cm.teachSkill(1121008, 0, 10);
					cm.sendNext("恭喜你转职为 #b英雄#k.我送你一些神秘小礼物");
					cm.dispose();
				} else if (cm.getJob() == 121) {
					cm.changeJob(122);
					cm.teachSkill(1221001, 0, 10);
					cm.teachSkill(1220005, 0, 10);
					cm.teachSkill(1221009, 0, 10);
					cm.sendNext("恭喜你转职为 #b圣骑士#k.我送你一些神秘小礼物");
					cm.dispose();
				} else if (cm.getJob() == 131) {
					cm.changeJob(132);
					cm.teachSkill(1321001, 0, 10);
					cm.teachSkill(1320005, 0, 10);
					cm.teachSkill(1321007, 0, 10);
					cm.sendNext("恭喜你转职为 #b黑骑士#k.我送你一些神秘小礼物");
					cm.dispose();
				
			}
		}
} else if (status == 2) {
		cm.sendNextPrev("不要忘记了这一切都取决于你强大了多少.");
		cm.dispose();
}}
