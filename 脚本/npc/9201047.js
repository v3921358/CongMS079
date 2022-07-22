var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
		status++;
    } else {
	if (status == 0) {
	    cm.dispose();
	}
	status--;
    }
    if (cm.getPlayer().getMapId() == 670011000) {
		//cm.gainNX(2000);
		cm.warp(670010000,0);
		cm.dispose();
	return;
    }
    var em = cm.getEventManager("Amoria");
    if (em == null || !cm.isLeader()) {
		cm.dispose();
		return;
    }
    if (em.getProperty("apq1").equals("0")) {
	if (cm.getMap().getAllMonstersThreadsafe().size() == 0) {
	    em.setProperty("apq1", "1");
	    cm.mapMessage(5, "某種東西已經在地圖上某個地方生成了。");
	    cm.spawnMonster(9400518,1);
	} else {
	    cm.sendOk("請問你可以幫我消除地圖上所有的怪物嗎？？");
	}
    } else if (em.getProperty("apq1").equals("1")) {
	if (cm.haveItem(4031595)) {
	    cm.gainItem(4031595, -1);
    	    cm.showEffect(true, "quest/party/clear");
    	    cm.playSound(true, "Party1/Clear");
	    em.setProperty("apq1", "2");
	} else {
	    cm.sendOk("請告訴我使用永錘從輪渡打破鏡子的證明。");
		}
    }
    cm.dispose();
}