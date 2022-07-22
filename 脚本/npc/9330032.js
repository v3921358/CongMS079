// 黑輪王副本
var status = -1;

function start() {
	action(1,0,0);
}

function action(mode,type,selected) {
	if (mode == -1) {
		cm.dispose();
	} else {
		if (mode == 0 && status == 0) {
			cm.sendNext("想要離開再告訴我。");
			cm.dispose();
			return;
		}
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			cm.sendYesNo("想要離開這裡嗎??");
		} else if (status ==1 ) {
			cm.warp(741020100, 0);
			cm.dispose();
		}
	}
}