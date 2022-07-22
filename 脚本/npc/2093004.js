/*
	Dolphin - Pier on the Beach(251000100)
*/

var status = -1;
var menu;
var cost = 10000;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	cm.sendNext("需要的時候再來找我。");
	cm.safeDispose();
	return;
    }
    if (status == 0) {
	cm.sendYesNo("你現在想要前往 #b#m230000000##k ? 費用是 #b"+cost+" 楓幣#k.");
    } else if (status == 1) {
	if (cm.getMeso() < cost) {
	    cm.sendOk("很抱歉，您沒有足夠的楓幣...");
	    cm.safeDispose();
	} else {
	    cm.gainMeso(-cost);
	    cm.warp(230000000);
	    cm.dispose();
	}
    }
}