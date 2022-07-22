function action(mode, type, selection) {
    if (cm.haveItem(1002971,1) && cm.canHold(1052202,1) && !cm.haveItem(1052202,1)) {
	cm.gainItem(1052202,1);
    } else {
    	cm.sendOk ("如果你給我的粉紅色豆帽子和一個空槽，你將獲得的粉紅豆總體，如果你不已經擁有了它。");
    }
    cm.safeDispose();
}