﻿/* Brittany
	Henesys Random Hair/Hair Color Change.
*/
var status = -1;
var beauty = 0;
var hair_Colo_new;

//function action(mode, type, selection) {
    if (mode == 0) {
	cm.dispose();
	return;
    } else {
	status++;
    }

    if (status == 0) {
	cm.sendSimple("嗨，我是#p9330050# 如果你有 #b#t5151023##k 或者 #b#t5151023##k, 我就可以免費幫你弄好看的頭髮。 \r\n#L0#使用: #i5150028##t5150028##l\r\n#L1#使用: #i5151023##t5151023##l");
    } else if (status == 1) {
	if (selection == 0) {
	    var hair = cm.getPlayerStat("HAIR");
	    hair_Colo_new = [];
	    beauty = 1;

	    if (cm.getPlayerStat("GENDER") == 0) {
		hair_Colo_new = [30310, 30330, 30060, 30150, 30410, 30210, 30140, 30120, 30200, 30560, 30510, 30610, 30470, 30920, 30860, 30800];
	    } else {
			hair_Colo_new = [31150, 31310, 31300, 31160, 31100, 31410, 31030, 31080, 31070, 31610, 31350, 31510, 31560, 31710, 31880];//31742
	    }
	    for (var i = 0; i < hair_Colo_new.length; i++) {
		hair_Colo_new[i] = hair_Colo_new[i] + (hair % 10);
	    }
	    cm.sendYesNo("確定要使用 #b#t5150028##k 隨機剪髮了？？");

	} else if (selection == 1) {
	    var currenthaircolo = Math.floor((cm.getPlayerStat("HAIR") / 10)) * 10;
	    hair_Colo_new = [];
	    beauty = 2;

	    for (var i = 0; i < 8; i++) {
		hair_Colo_new[i] = currenthaircolo + i;
	    }
	    cm.sendYesNo("確定要使用 #b#t5151023##k 隨機染髮了？？");
	}
    } else if (status == 2){
	if (beauty == 1){
	    if (cm.setRandomAvatar(5150028, hair_Colo_new) == 1) {
		cm.sendOk("享受！");
	    } else {
		cm.sendOk("痾.... 貌似沒有#b#t5150028##k。");
	    }
	} else {
	    if (cm.setRandomAvatar(5151023, hair_Colo_new) == 1) {
		cm.sendOk("享受！");
	    } else {
		cm.sendOk("痾.... 貌似沒有#b#t5151023##k。");
	    }
	}
	cm.safeDispose();
    }
}