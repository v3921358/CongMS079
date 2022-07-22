function act() {
    rm.changeMusic("Bgm14/HonTale");
    rm.spawnMonster(8810026, 71, 260);
    rm.mapMessage("随着一声娇喘，可爱的暗黑龙王出现了，赶快抡起你的袖子，干。");
	//rm.scheduleWarp(43200, 240000000);
/*	if (!rm.getPlayer().isGM()) {
		rm.getMap().startSpeedRun();
	}*/
}