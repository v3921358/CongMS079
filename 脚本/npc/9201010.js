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
    if (cm.getMapId() != 680000100) {
	cm.dispose();
	return;
    }
    if (status == 0) {
	cm.sendYesNo("您想進入結婚禮堂？？");
    } else if (status == 1) {

	    var marr = cm.getQuestRecord(160001);
	    var data = marr.getCustomData();
	    if (data == null) {
		marr.setCustomData("0");
	        data = "0";
	    }
	    if (data.equals("1")) {
		if (cm.getPlayer().getMarriageId() <= 0) {
		    cm.sendOk("好像發生了錯誤了，您好像還沒有跟任何人結婚！");
		    cm.dispose();
		    return;
		}
	    	var chr = cm.getMap().getCharacterById(cm.getPlayer().getMarriageId());
	    	if (chr == null) {
		    cm.sendOk("請確認您的另一半是否在同一張地圖內。");
		    cm.dispose();
		    return;
	    	}
		var maps = Array(680000110, 680000300, 680000401);
		for (var i = 0; i < maps.length; i++) {
		    if (cm.getMap(maps[i]).getCharactersSize() > 0) {
			cm.sendNext("禮堂內已經有另外一對新人正在舉行婚禮，請稍後再來嘗試。");
			cm.dispose();
			return;
		    }
		}
		var map = cm.getMap(680000110);
		cm.getPlayer().changeMap(map, map.getPortal(0));
		chr.changeMap(map, map.getPortal(0));
		cm.worldMessage(5, "<頻道 " + cm.getClient().getChannel() + "> " + cm.getPlayer().getName() + " 與 " + chr.getName() + "即將步入禮堂，請大家快過來祝福他們。");
	    } else {
		if (cm.getMap(680000110).getCharactersSize() == 0) {
		    cm.sendNext("現在禮堂沒有舉辦任何婚禮喔，請稍後再來。");
		    cm.dispose();
		    return;
		}
		if (cm.haveItem(4150000)) {
		    cm.warp(680000110,0);
			cm.removeAll(4150000);
		} else {
		    cm.sendOk("請確認是否有#b#t4150000##k。");
		}
	    }
	cm.dispose();
    }
}