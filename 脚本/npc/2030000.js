/**
	Jeff - El Nath : El Nath : Ice Valley II (211040200)
**/

var status = 0;

function start() {
    if (cm.haveItem(4031450)) {
	cm.warp(921100100, 0);
	cm.dispose();
    } else {
	status = -1;
	action(1, 0, 0);
    }
}

function action(mode, type, selection) {
    if (status == 1 && mode == 0 && cm.getPlayerStat("LVL") >= 40) {
	cm.sendNext("即使你的等級在高，很難真正去那裡，但是如果你改變主意，請找我。畢竟我的工作就是保護這個地方。");
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	cm.sendNext("嗨我是傑夫你要前往此地方？");
    } else if (status == 1) {
	if (cm.getPlayerStat("LVL") >= 50) {
	    cm.sendYesNo("準備去了??");
	} else {
	    cm.sendPrev("你的等級好像不夠。");
	}
    } else if (status == 2) {
	if (cm.getPlayerStat("LVL") >= 40) {
	    cm.warp(211040300, 5);
	}
	cm.dispose();
    }
}