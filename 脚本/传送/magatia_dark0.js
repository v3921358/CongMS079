function enter(pi) {
	if (pi.getQuestStatus(3309) == 1) {
		pi.warp(926120000, 0);
	} else {
		pi.warp(261020700, 0);
	}
	pi.playPortalSE();
}