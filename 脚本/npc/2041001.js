﻿function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    status++;
    if(mode == 0) {
	cm.sendOk("這是好的選擇！！");
	cm.dispose();
	return;
    }
    if(status == 0) {
	cm.sendYesNo("你要離開船上??");
    } else if(status == 1) {
	if (cm.getMapId() == 220000111) {
	    cm.warp(220000110, 0);
	} else {
	    cm.warp(200000121, 0);
	}
	cm.dispose();
    }
}
