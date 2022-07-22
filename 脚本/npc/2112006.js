function action(mode, type, selection) {
    var em = cm.getEventManager("Romeo");
    if (em == null) {
	cm.sendOk("Please try again later.");
	cm.dispose();
	return;
    }
    switch(cm.getPlayer().getMapId()) {
	case 261000011:
	    cm.removeAll(4001130);
	    cm.removeAll(4001131);
	    cm.removeAll(4001132);
	    cm.removeAll(4001133);
	    cm.removeAll(4001134);
	    cm.removeAll(4001135);
	    if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
		cm.sendOk("The leader of the party must be here.");
	    } else {
		var party = cm.getPlayer().getParty().getMembers();
		var mapId = cm.getPlayer().getMapId();
		var next = true;
		var size = 0;
		var it = party.iterator();
		while (it.hasNext()) {
			var cPlayer = it.next();
			var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
			if (ccPlayer == null || ccPlayer.getLevel() < 71 || ccPlayer.getLevel() > 250) {
				next = false;
				break;
			}
			size += (ccPlayer.isGM() ? 4 : 1);
		}	
		if (next && (cm.getPlayer().isGM() || size == 4)) {
			em.startInstance(cm.getPlayer().getParty(), cm.getPlayer().getMap());
		} else {
			cm.sendOk("所有组队的成员必须高于71级。");
		}
	    }
	    break;
	case 926100000:
	    cm.sendOk("在这里你应该调查，在图书馆看的文件,直到你可以找到入口的实验室.");
	    break;
	case 926100001:
	    cm.sendOk("请消灭所有的怪物!我马上就来.");
	    break;
	case 926100100:
	    cm.sendOk("这些烧杯有泄漏。我们必须把烧杯填满.");
	    break;
	case 926100200:
	    if (cm.haveItem(4001130,1)) {
		cm.sendOk("哦,我写的信!谢谢你！!");
		cm.gainItem(4001130,-1);
		em.setProperty("stage", "1");
	    } else if (cm.haveItem(4001134,1)) {
		cm.gainItem(4001134,-1);
		cm.sendOk("谢谢你！现在请查收文件.");
		em.setProperty("stage4", "1");
	    } else if (cm.haveItem(4001135,1)) {
		cm.gainItem(4001135,-1);
		cm.sendOk("谢谢你！现在请继续.");
		em.setProperty("stage4", "2");
		cm.getMap().getReactorByName("rnj3_out3").hitReactor(cm.getClient());
	    } else {
	    	cm.sendOk("现在我们必须停止冲突，请帮我找出#t4001134# 和 #t4001135#。!");
			cm.gainItem(4001130,1);
			cm.gainItem(4001134,1);
			cm.gainItem(4001135,1);
	    }
	    break;
	case 926100300:
		cm.warpParty(926100401); 
	    cm.sendOk("请帮我拯救我的爱人吧.");
	    break;
	case 926100400:
	    cm.sendOk("只要你准备好了,我们要去拯救我的爱.");
	    break;
	case 926100401:
	    cm.warpParty(926100500); //urete
	    break;
    }
    cm.dispose();
}