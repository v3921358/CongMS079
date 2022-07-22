
var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }

    if (status == 0) {
	if (!(cm.getJob() == 411 || cm.getJob() == 421 || cm.getJob() == 433)) {
	    cm.sendOk("为什么你要见我?还有你想要问我关于什么事情?");
	    cm.dispose();
	    return;
		} else if (!cm.isQuestFinished(6930)) {
			cm.sendOk("你还没有去找3转教官#r艾瑞克#k接受相关任务吧吧。");
			cm.dispose();
			return;
			} else if (!cm.isQuestFinished(6934)) {
			cm.sendOk("请完成#d任务#r英雄的潜力#k在找我。");
			cm.dispose();
			return;
	} else if (cm.getPlayer().getLevel() < 120) {
	    cm.sendOk("你等级尚未达到120级.");
	    cm.dispose();
	    return;
	} else {
		if (cm.getJob() == 411){
		    cm.sendSimple("恭喜你有资格4转.请问你想4转吗?\r\n#b#L0#我想成为隐士#l");
		} else if (cm.getJob() == 421){
		    cm.sendSimple("恭喜你有资格4转.请问你想4转吗?\r\n#b#L0#我想成为侠盗#l");
	    } else {
		cm.sendOk("好吧,假如你想要4转.请麻烦再来找我");
		cm.safeDispose();
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
		if (cm.getJob() == 411) {
		    cm.changeJob(412);
		    cm.teachSkill(4120002,0,10);
		    cm.teachSkill(4121006,0,10);
		    cm.teachSkill(4120005,0,10);
		    cm.sendNext("恭喜你转职为 #b隐士#k.");
		} else if (cm.getJob() == 421) {
		    cm.changeJob(422);
		    cm.teachSkill(4220002,0,10);
		    cm.teachSkill(4221007,0,10);
		    cm.teachSkill(4220005,0,10);
		    cm.sendNext("恭喜你转职为 #b侠盗#k.");
	}
	}
    } else if (status == 2) {
	cm.sendNextPrev("不要忘记了这一切都取决于你强大了多少.");
    } else if (status == 3) {
	cm.dispose();
    }
}