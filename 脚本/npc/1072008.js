/**
	Author: xQuasar
	NPC: Kyrin - Pirate Job Advancer
	Inside Test Room
**/

var status;

function start() {
    status = -1;
    action(1,0,0);
}

function action(mode,type,selection) {
    if (status == -1) {
	if (cm.getMapId() == 108000500) {
	    if (!(cm.haveItem(4031857,15))) {
		cm.sendNext("快去收集 15個 #b列風結晶#k 給我.");
		cm.dispose();
	    } else {
		status = 2;
		cm.sendNext("wow 果然是個大俠恭喜通過這次個考驗 你已經是個強大的海盜了所以我將頒贈給你神秘的小禮物.");
	    }
	} else if (cm.getMapId() == 108000502) {
	    if (!(cm.haveItem(4031856,15))) {
		cm.sendNext("快去收集15個 #b強大力量結晶#k 給我.");
		cm.dispose();
	    } else {
		status = 2;
		cm.sendNext("wow 果然是個大俠恭喜通過這次個考驗 你已經是個強大的海盜了所以我將頒贈給你神秘的小禮物.");
	    }
	} else {
	    cm.sendNext("錯誤請再嘗試一次.");
	    cm.dispose();
	}
    } else if (status == 2) {
	cm.gainItem(4031012, 1);
	cm.warp(120000101,0);
	cm.dispose();
    }
}