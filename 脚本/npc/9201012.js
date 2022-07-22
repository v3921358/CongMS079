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
    if (status == 0) {
	cm.sendYesNo("找我有什麼事情？？ 你想要預定一個婚禮？？");
    } else if (status == 1) {
	if (cm.getPlayer().getMarriageId() <= 0) {
	    cm.sendOk("好像發生了錯誤了，您好像還沒有跟任何人結婚！");
	} else if (!cm.canHold(4150000,60)) {
	    cm.sendOk("請空出一些其它欄位吧！！");
	} else if (!cm.haveItem(5251004,1) && !cm.haveItem(5251005,1) && !cm.haveItem(5251006,1)) {
	    cm.sendOk("很抱歉，您好像沒有#b#t521004#或者#t5251005#或者#t5251006##k！！");
	} else {
	    var chr = cm.getMap().getCharacterById(cm.getPlayer().getMarriageId());
	    if (chr == null) {
		cm.sendOk("請確認您的另一半是否在同一張地圖內。");
		cm.dispose();
		return;
	    }
	    var marr = cm.getQuestRecord(160001);
	    var data = marr.getCustomData();
	    if (data == null) {
		marr.setCustomData("0");
	        data = "0";
	    }
	    if (data.equals("0")) {
		marr.setCustomData("1");
		cm.setQuestRecord(chr, 160001, "1");
		var num = 0;
		if (cm.haveItem(5251006,1)) {
		    cm.gainItem(5251006,-1);
		    num = 60;
		} else if (cm.haveItem(5251005,1)) {
		    cm.gainItem(5251005,-1);
		    num = 30;
		} else if (cm.haveItem(5251004,1)) {
		    cm.gainItem(5251004,-1);
		    num = 10;
		}
		cm.setQuestRecord(cm.getPlayer(), 160002, num + "");
		cm.setQuestRecord(chr, 160002, num + "");
		cm.sendNext("您現在已經可以開始結婚了，但是在結婚之前先邀請一些貴賓前來參加您的婚禮吧，讓他們知道您與您的另一半愛情是很美的！！");
//		cm.gainItemPeriod(4150000,num,1);
	    } else {
		cm.sendOk("我想您已經是結過婚，或者是個單身漢。");
	    }
	}
	cm.dispose();
    }
}