function start() {
	if (cm.getQuestStatus(6410) == 1) {
		cm.warp(925010000,0);
	} else {
		cm.sendOk("在世界上什麼，你說什麼？");
	}
    cm.dispose();
}