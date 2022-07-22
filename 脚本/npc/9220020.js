var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }
    if (!cm.isLeader()) {
	cm.sendNext("請找隊長來和我說話。");
	cm.dispose();
	return;
    }
    if (cm.haveItem(4032119,17)) {
	cm.warpParty(674030200);
	cm.gainItem(4032119,-17);
    } else {
	cm.sendOk("歡迎來到V怪客副本 我需要地圖上岩塊堆 中的\r\n#r17個 #b#t4032119##k。");
    }
    cm.dispose();
}