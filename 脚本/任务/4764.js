var status = -1;

function start(mode, type, selection) {
	if (mode == -1) {
		qm.dispose();
	} else {
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			if(qm.getQuestStatus(4764)==2){
			qm.sendOk("你已经领取过奖励，继续努力到30级可以获得新奖励喔");
			qm.dispose();
			}else{
			qm.sendNext("恭喜你当前等级已经到达#b20#k级。");
			}
		} else if (status == 1) {
			qm.sendOk("恭喜你获得系统奖励！\r\n\r\n#fUI/UIWindow.img/QuestIcon/4/0#\r\n#v5160000##v5160001##v5160002##v5160003# 各1张");
			qm.gainItemPeriod(5160000, 1, 3);
                        qm.gainItemPeriod(5160001, 1, 3);
                        qm.gainItemPeriod(5160002, 1, 3);
                        qm.gainItemPeriod(5160003, 1, 3);
			qm.forceCompleteQuest(4764);
			qm.dispose();
		} 
	}
}