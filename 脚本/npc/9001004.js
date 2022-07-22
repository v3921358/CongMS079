var status = 0;


function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1 || mode == 0) {
		cm.dispose();
	} else {
		status++;
		if (status == 0) {
			cm.sendSimple("嗨，我是#p9001004#，需要什麼幫忙嗎？？\r\n#L0#帶我離開這裡。#l\r\n#L1#買一把活動武器 (1 楓幣)#l");
		} else if (status == 1) {
			if (selection == 0) {
				cm.sendYesNo("你想要離開這裡？？");
			} else if (selection == 1) {
				if (cm.getPlayer().getMeso() < 1 || !cm.canHold(1322005)) {
					cm.sendOk("你沒有足夠的楓幣，或者道具欄位滿了。");
				} else {
					cm.gainItem(1322005, 1);
					cm.gainMeso(-1); //lool
				}
				cm.dispose();
			}
		} else if (status == 2) {
			var map = cm.getSavedLocation("EVENT");
			if (map > -1) {
				cm.warp(map, 0);
			} else {
    				cm.warp(910000000, 0);
			}
			cm.dispose();
		}
	}
}

function getEimForGuild(em, id) {
        var stringId = "" + id;
        return em.getInstance(stringId);
}