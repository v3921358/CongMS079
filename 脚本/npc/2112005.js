function action(mode, type, selection) {
    var em = cm.getEventManager("Juliet");
    if (em == null) {
	cm.sendOk("请稍后再试.");
	cm.dispose();
	return;
    }
    switch(cm.getPlayer().getMapId()) {
	case 261000021:
	    cm.removeAll(4001130);
	    cm.removeAll(4001131);
	    cm.removeAll(4001132);
	    cm.removeAll(4001133);
	    cm.removeAll(4001134);
	    cm.removeAll(4001135);
	    if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
		cm.sendOk("党的领导人一定在这里.");
	    } else {
		var party = cm.getPlayer().getParty().getMembers();
		var mapId = cm.getPlayer().getMapId();
		var next = true;
		var size = 0;
		var it = party.iterator();
		while (it.hasNext()) {
			var cPlayer = it.next();
			var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
			if (ccPlayer == null || ccPlayer.getLevel() < 70 || ccPlayer.getLevel() > 120) {
				next = false;
				break;
			}
			size += (ccPlayer.isGM() ? 4 : 1);
		}	
		if (next && (cm.getPlayer().isGM() || size == 4)) {
			em.startInstance(cm.getPlayer().getParty(), cm.getPlayer().getMap());
		} else {
			cm.sendOk("你们队伍的四个成员都必须在这里，71级以上.");
		}
	    }
	    break;
	case 926110000:
	    cm.sendOk("你应该在这里调查一下。看看图书馆里的文件直到你找到实验室的入口.");
	    break;
	case 926110001:
	    cm.sendOk("拜托，消灭所有的怪物！我马上跟在你后面.");
	    break;
	case 926110100:
	    cm.sendOk("这些烧杯有漏洞。我们必须把可疑的液体倒到烧杯边上，这样我们才能继续.");
	    break;
	case 926110200:
	    if (cm.haveItem(4001131,1)) {
		cm.sendOk("哦，我写的信！谢谢你!");
		cm.gainItem(4001131,-1);
		em.setProperty("stage", "1");
	    } else if (cm.haveItem(4001134,1)) {
		cm.gainItem(4001134,-1);
		cm.sendOk("谢谢您！现在请找到Zenumist文件.");
		em.setProperty("stage4", "1");
	    } else if (cm.haveItem(4001135,1)) {
		cm.gainItem(4001135,-1);
		cm.sendOk("谢谢您！现在请继续.");
		em.setProperty("stage4", "2");
		cm.getMap().getReactorByName("jnr3_out3").hitReactor(cm.getClient());
	    } else {
	    	cm.sendOk("我们必须制止阿尔卡德诺和泽努米斯特之间的冲突！先找Alcadno文件，然后找Zenumist!");
	    }
	    break;
	case 926110300:
	    cm.sendOk("我们必须到达实验室的顶部，你们每个成员.");
	    break;
	case 926110400:
	    cm.sendOk("只要你准备好了，我们就去救我的爱人.");
	    break;
	case 926110401:
	    cm.warpParty(926110500); //urete
	    break;
    }
    cm.dispose();
}