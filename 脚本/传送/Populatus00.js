var pop = 2;

function enter(pi) {
    if (pi.getPlayer().getClient().getChannel() != 1 && pi.getPlayer().getClient().getChannel() != 2&& pi.getPlayer().getClient().getChannel() != 3&& pi.getPlayer().getClient().getChannel() != 4&& pi.getPlayer().getClient().getChannel() != 5) {
        pi.playerMessage(5, "闹钟只能在频道1-5 进行挑战。");
        return false;
    }
    if (pi.haveItem(4031870)) {
        pi.warp(922020300, 0);
        return true;
    }
    /*if (!pi.haveItem(4031172)) {
        pi.playerMessage(5, "不明的力量无法进入，需要有玩具獎牌。");
        return false;
    }*/
	if (pi.getPlayer().getBossLog("pop") >= 20) {
		pi.playerMessage(5, "一天只能进入20次挑战闹钟哦。");
		return false;
	}
    if (pi.getPlayerCount(220080001) <= 0) { // Papu Map
        var papuMap = pi.getMap(220080001);
        papuMap.resetFully();
        pi.setBossLog("pop");
        pi.playPortalSE();
        pi.warp(220080001, "st00");
        return true;
    } else {
        if (/*pi.getMap(220080001).getPapfight() == 0 &&*/ pi.getMap(220080001).getSpeedRunStart() == 0 && (pi.getMonsterCount(220080001) <= 0 || pi.getMap(220080001).isDisconnected(pi.getPlayer().getId()))) {
            var papuMap = pi.getMap(220080001);
            pi.setBossLog("pop");
            pi.playPortalSE();
            pi.warp(220080001, "st00");
            return true;
        } else {
            pi.playerMessage(5, "裡面的戰鬥已經開始，請稍後再嘗試。");
            return false;
        }
    }
}