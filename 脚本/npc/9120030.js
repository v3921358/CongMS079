/*
	Marr - Tokyo Park 2095
*/

function start() {
    if (cm.getMapId() != 802000310) {
	cm.sendSimple("嗚嗚嗚嗚! \r\n#b#L0#我想要用#t4032192#兌換獎勵。#l \r\n#b#L1#我想要跑走!!#l");
    } else {
	cm.sendOk("沒事不要打擾我!");
	cm.dispose();
}
}

function action(mode, type, selection) {
    if (mode == 1) {
	if (selection == 0) {
	    if (cm.haveItem(4032192, 10)) {
		cm.removeAll(4032192);
		cm.warp(802000313, 0);
	    } else {
		cm.sendOk("請給我#t4032192#。");
	    }
	} else if (selection == 1) {
	    cm.warp(802000310, 0);
	}
    }
    cm.dispose();
}