
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
	if (!(cm.getJob() == 211 || cm.getJob() == 221 || cm.getJob() == 231)) {
	    cm.sendOk("为什么你要见我??还有你想要问我关于什么事情??");
	    cm.dispose();
	    return;
	} else if (!cm.isQuestFinished(6910)) {
			cm.sendOk("你还没有去找3转教官#r鲁碧#k接受相关任务吧吧。");
			cm.dispose();
			return;
	} else if (!cm.isQuestFinished(6914)) {
			cm.sendOk("请完成#d任务#r英雄的潜力#k在找我。");
			cm.dispose();
			return;
	} else if (cm.getPlayer().getLevel() < 120) {
	    cm.sendOk("你等级尚未到达120级.");
	    cm.dispose();
	    return;
	} else {
		if (cm.getJob() == 211) {
		    cm.sendSimple("恭喜你有资格4转.请问你想4转吗??\r\n#b#L0#我想成为魔导师(火,毒)#l");
		} else if(cm.getJob() == 221){
		    cm.sendSimple("恭喜你有资格4转.请问你想4转吗??\r\n#b#L0#我想成为魔导师(冰,雷)#l");
		} else if(cm.getJob() == 231){
		    cm.sendSimple("恭喜你有资格4转.请问你想4转吗??\r\n#b#L0#我想成为主教#l");
	    } else {
		cm.sendOk("好吧,假如你想要4转.请麻烦再来找我");
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
		if (cm.getJob() == 211) {
		    cm.changeJob(212);
		    cm.teachSkill(2121001,0,10);
		    cm.teachSkill(2121006,0,10);
		    cm.teachSkill(2121002,0,10);
		    cm.sendNext("恭喜你转职为 #b魔导师(火,毒)#k.我送你一些神秘小礼物");
			cm.dispose();
		} else if (cm.getJob() == 221) {
		    cm.changeJob(222);
		    cm.teachSkill(2221001,0,10);
		    cm.teachSkill(2221006,0,10);
		    cm.teachSkill(2221002,0,10);
		    cm.sendNext("恭喜你转职为 #b魔导师(冰,雷)#k.我送你一些神秘小礼物");
			cm.dispose();
		} else if (cm.getJob() == 231) {
		    cm.changeJob(232);
		    cm.teachSkill(2321001,0,10);
		    cm.teachSkill(2321005,0,10);
		    cm.teachSkill(2321002,0,10);
		    cm.sendNext("恭喜你转职为 #b主教#k.我送你一些神秘小礼物");
			cm.dispose();
		}
		}
    } else if (status == 2) {
	if (cm.getJob() == 212) {
	    cm.sendNext("不要忘记了这一切都取决于你强大了多少.");
	} else if (cm.getJob() == 222) {
	    cm.sendNextPrev("不要忘记了这一切都取决于你强大了多少.");
	} else {
	    cm.sendNextPrev("不要忘记了这一切都取决于你强大了多少.");
	}
    } else if (status == 3) {
	cm.sendNextPrev("我以你为荣.");
	cm.dispose();
    }
}