function enter(pi) {
	if (pi.getQuestStatus(21610) == 1) {
	if (pi.getPlayerCount(921110000) == 0) {
		pi.warp(921110000, 1);
	} else {
		pi.playerMessage(5, "已經有人在裡面挑戰了請稍後在嘗試。");
	}
	} else {
		pi.playerMessage(5, "你好像不能進入這個門。");
}
}