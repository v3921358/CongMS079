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
	cm.sendYesNo("你想要預定一個婚禮？？");
    } else if (status == 1) {
	if (cm.getPlayer().getMarriageId() <= 0) {
	    cm.sendOk("你是不是搞錯了？？");
	} else if (!cm.canHold(4150000,60)) {
	    cm.sendOk("請空出一些其他欄。。");
	} else if (!cm.haveItem(5251004,1) && !cm.haveItem(5251005,1) && !cm.haveItem(5251006,1)) {
	    cm.sendOk("請先從購物商場買預約票。");
	} else {
	    var chr = cm.getMap().getCharacterById(cm.getPlayer().getMarriageId());
	    if (chr == null) {
		cm.sendOk("確保你的伴侶在地圖上。");
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
		cm.sendNext("你現在有資格為婚禮。這裡是婚禮請柬，大家希望邀請將要求他們的客人。");
		cm.gainItemPeriod(4150000,num,1);
	    } else {
		cm.sendOk("我想你已經結婚或者已經做了預約。");
	    }
	}
	cm.dispose();
    }
}