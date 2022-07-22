/*
    Zakum Entrance
*/

function enter(pi) {
    if (pi.getQuestStatus(100200) != 2) {
	pi.playerMessage(5, "您好像还沒准备好面对BOSS。");
	return false;

    } else if (!pi.haveItem(4001017)) {
	pi.playerMessage(5, "由于你沒有火眼之眼，所以不能挑战扎昆。");
	return false;
    }
	if (pi.getPlayerCount(280030000) > 0) { // 判断组队队长
			pi.playerMessage(5, "有人正在打扎昆，换别的线去打吧!");
		    
            return false;
		}
    if (pi.getPlayer().getBossLog("zku") >= 5) {
		pi.playerMessage(5, "一天只能进入5次挑战扎昆哦。");
		return false;
	}
	//pi.playerMessage(5, "一天只能打2次扎昆。");
	pi.setBossLog("zku");
    pi.playPortalSE();
    pi.warp(pi.getPlayer().getMapId() + 100, "west00");
    return true;
}