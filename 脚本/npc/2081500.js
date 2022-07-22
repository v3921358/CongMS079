
var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }

    if (status == 0) {
	if (!(cm.getJob() == 511 || cm.getJob() == 521)) {
	    cm.sendOk("为什么你要见我?还有你想要问我关于什么事情?");
	    cm.dispose();
	    return;
		} else if (!cm.isQuestFinished(6940)) {
			cm.sendOk("你还没有去找3转教官#r费德罗#k接受相关任务吧吧。");
			cm.dispose();
			return;
			} else if (!cm.isQuestFinished(6944)) {
			cm.sendOk("请完成#d任务#r英雄的潜力#k在找我。");
			cm.dispose();
			return;
	} else if (cm.getPlayer().getLevel() < 120) {
	    cm.sendOk("你等级尚未到达120级.");
	    cm.dispose();
	    return;
	} else {
		if (cm.getJob() == 511){
		    cm.sendSimple("恭喜你有资格4转.请问你想4转吗?\r\n#b#L0#我想成为冲锋队长#l");
		} else if (cm.getJob() == 521){
		    cm.sendSimple("恭喜你有资格4转.请问你想4转吗?\r\n#b#L0#我想成为船长#l");
	    } else {
		cm.sendOk("好吧,假如你想要4转,请麻烦再来找我");
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
		if (cm.getJob() == 511) {
		    cm.changeJob(512);
		    cm.teachSkill(5121007,0,10);
		    cm.teachSkill(5121001,0,10);
		    cm.teachSkill(5121002,0,10);
		    cm.teachSkill(5121009,0,10);
		    cm.sendNext("恭喜你转职为 #b冲锋队长#k.");
		} else if (cm.getJob() == 521) {
		    cm.changeJob(522);
		    cm.teachSkill(5221004,0,10);
		    cm.teachSkill(5220001,0,10);
		    cm.teachSkill(5220002,0,10);
		    cm.teachSkill(5220011,0,10);
		    cm.sendNext("恭喜你转职为 #b船长#k.");
		}
	}
    } else if (status == 2) {
	if (cm.getJob() == 512) {
	    cm.sendNext("不要忘记了这一切都取决于你强大了多少.");
	} else {
	    cm.sendNext("不要忘记了这一切都取决于你强大了多少.");
	}
    } else if (status == 3) {
	cm.sendNextPrev("我以你为荣.");
    } else if (status == 4) {
	cm.dispose();
    }
}