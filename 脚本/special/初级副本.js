

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
		var selStr = "#d【 #r" + cm.getServerName() + "#d】-副本一条龙[初级]\r\n";
        selStr += "#r难度：★\r\n";
		selStr += "#b副本要求：月妙副本-通关["+cm.getBossLog("月妙副本")+"/4]次#n\r\n"
		selStr += "          废弃副本-通关["+cm.getBossLog("废弃副本")+"/3]次#n\r\n"
		selStr += "          玩具副本-通关["+cm.getBossLog("玩具副本")+"/2]次#n\r\n"
		selStr += "          天空副本-通关["+cm.getBossLog("天空副本")+"/1]次#n\r\n"        
		selStr += "任务奖励：国庆币*1 黄金枫叶*10 点卷：10000\r\n";
        selStr += "#L0#领取[初级]奖励(请保持背包足够)\r\n";
		cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
        case 0:
            if (cm.getBossLog("月妙副本") < 4){
				cm.sendOk("月妙副本-通关["+cm.getBossLog("月妙副本")+"/4]次");
				cm.dispose();
			}else if (cm.getBossLog("废弃副本") < 3){
				cm.sendOk("废弃副本-通关["+cm.getBossLog("废弃副本")+"/3]次");
				cm.dispose();
			}else if (cm.getBossLog("玩具副本") < 2){
				cm.sendOk("玩具副本-通关["+cm.getBossLog("玩具副本")+"/2]次");
				cm.dispose();
			}else if (cm.getBossLog("天空副本") < 1){
				cm.sendOk("天空副本-通关["+cm.getBossLog("天空副本")+"/1]次");
				cm.dispose();
			}else if (cm.getBossLog("副本一条龙[初级]奖励") > 0){
				cm.sendOk("今日你已经领取过奖励了。");
				cm.dispose();
			}else if (!cm.checkNumSpace(4, 3)) {
			cm.sendOk("背包其他栏空间不足3格，请预留好空余位置哟！");
			cm.dispose();
			return;
		    }else{
				cm.setBossLog("副本一条龙[初级]奖励");
				cm.gainItem(4000313,10);//进阶币
				cm.gainItem(4000463,1);//国庆币
				cm.getPlayer().modifyCSPoints(1,10000, true);//给点卷200
				cm.sendOk("领取成功~")
				cm.worldMessage(2, "[副本一条龙] : 恭喜 " + cm.getName() + " ，领取副本一条龙[初级]奖励。");
				cm.dispose();
            break;
		}
    }
}}
