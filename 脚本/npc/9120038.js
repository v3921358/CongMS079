var minlv = 140;

function start() {
	var level = cm.getPlayerStat("LVL");
	var trainMap = cm.getMap(802000311);
    if (cm.canHold(4032202)) {
		if (trainMap.playerCount() == 0) {
		trainMap.resetFully();
		}
        if (!(cm.haveItem(4032202)) && level >= minlv) {
			cm.gainItem(4032202,1);
			cm.warp(802000311,0);
		} else if (cm.haveItem(4032202)) {
			cm.warp(802000311,0);
		} else {
			cm.sendOk("你貌似等級不夠...")
		}
		cm.sendOk("走囉!");
	} else {
		cm.sendOk("請檢查你的裝備攔....");
	}
		cm.dispose();
}