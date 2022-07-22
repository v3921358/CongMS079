function enter(pi) {
	if (pi.haveItem(2030011,1)) {
		pi.gainItem(2030011, -1);
		pi.warp(120000101, "earth01");
	} else {
		pi.playerMessage(5,"我需要一個東西...");
	}
}