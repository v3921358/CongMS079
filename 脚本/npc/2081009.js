/*
Moose
*/

var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }

    if (status == 0) {
	if (cm.getQuestStatus(6180) == 1) {
	    cm.sendOk("不錯。我會送你屏蔽訓練場。再次跟我說話。" );
	} else {
		cm.sendNext("找我有事情嗎？？");
	    cm.dispose();
	}
    } else if (status == 1) {
	cm.warp(924000000, 0);
	cm.dispose();
    }
}