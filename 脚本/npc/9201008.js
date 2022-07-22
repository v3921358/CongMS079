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
	if (cm.getPlayer().getMapId() == 680000000) {
	    cm.sendYesNo("請問你想要去參觀結婚禮堂嗎？？");
	} else {
	    cm.sendYesNo("請問你想要回去#m680000000#？？");
	}
    } else if (status == 1) {
	cm.warp(cm.getPlayer().getMapId() == 680000000 ? 680000100 : 680000000);
	cm.dispose();
    }
}