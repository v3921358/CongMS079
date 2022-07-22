var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }
    switch(cm.getPlayer().getMapId()) {
	case 930000000:
	    cm.sendNext("欢迎，请进入。");
	    break;
	case 930000100:
	    cm.sendNext("我们必须消灭所有这里的怪物！");
	    break;
	case 930000200:
	    cm.sendNext("我们必须消除所有这些被污染的植物！");
	    break;
	case 930000300:
	    cm.warpParty(930000400);
	    break;
	case 930000400:
	    if (cm.haveItem(4001169,1)) {
                cm.warpParty(930000500, 0);
		cm.gainItem(4001169,-1);
		cm.gainItem(4001163,1);
	    } else if (!cm.haveItem(2270004)) {
		cm.gainItem(4001169,1);
		cm.sendOk("请净化这些怪物");
	    } else {
		cm.sendOk("请给我10个怪物珠!");
	    }
	    break;
	case 930000600:
	    cm.sendNext("就是这个！");
	    break;
	case 930000700:
            if (cm.canHold(4001198,1)) {
                cm.gainItem(4001198,1);
                cm.gainItem(4170001,1);
				cm.gainExp(10000);
	        cm.getPlayer().endPartyQuest(1206);
	        cm.removeAll(4001161);
	        cm.removeAll(4001162);
	        cm.removeAll(4001163);
	        cm.removeAll(4001164);
	        cm.removeAll(4001169);
	        cm.removeAll(2270004);
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『组队任务 - 毒物副本』" + " : " + "[" + cm.getChar().getName() + "]成功通关毒物副本 获得丰富的奖励！！")); 
			cm.setPartyBossLog("duwu1");
	        cm.warp(930000800,0);
	} else {
		cm.getPlayer().dropMessage(5, "請確認你的其他欄有沒有滿");
	}
	    break;
    }
    cm.dispose();
}
