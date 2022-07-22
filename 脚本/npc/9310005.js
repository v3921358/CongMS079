function start() {
        if (cm.haveItem(4000194, 50)&&(cm.getPlayer().getBossLog("每日蜈蚣") <= 9)) {
    cm.gainItem(4000194, -50);
	cm.setBossLog("每日蜈蚣");
    cm.warp(701010322, "sp");	
    cm.dispose();
	} else {
	    cm.sendOk("亲，需要50个黑羊毛，每天可以进入10次！");
    cm.dispose();
}
}