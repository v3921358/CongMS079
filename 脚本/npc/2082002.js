﻿function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    status++;
    if(mode == 0) {
	cm.sendOk("很棒唷，不離開！");
	cm.dispose();
	return;
    }
    if(status == 0) {
	cm.sendYesNo("你要離開船上？");
    } else if(status == 1) {
	cm.warp(240000110, 0);
	cm.dispose();
    }
}
