function enter(pi) {
    if (pi.getQuestStatus(21000) == 0) {
		pi.playerMessage(5, "必須接受了赫麗娜的任務才能通過。");
    } else {
		pi.teachSkill(20000017, 0, -1);
		pi.teachSkill(20000018, 0, -1);
		pi.teachSkill(20000017, 1, 0);
		pi.teachSkill(20000018, 1, 0);
		pi.playPortalSE();
		pi.warp(914000200, 1);
    }
}