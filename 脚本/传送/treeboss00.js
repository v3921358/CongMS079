function enter(pi) {
	    if (pi.getPlayer().getBossLog("树精王") > 3) {
		pi.playerMessage(1, "您今天挑战克雷塞尔次数已经没有了，请明天再来。");
		return false;
	}
	var jobid=pi.getPlayer().getJob();
		if (jobid==112||jobid==122||jobid==132||jobid==212||jobid==222||jobid==232||jobid==312||jobid==322||jobid==412||jobid==422||jobid==512||jobid==522||jobid==2112||jobid==1112||jobid==1111||jobid==1211||jobid==1311||jobid==1411||jobid==1511){
    if (pi.getPlayerCount(541020800) <= 0) { // krex. Map
	var krexMap = pi.getMap(541020800);

	krexMap.resetFully();
	pi.setBossLog("树精王");
	pi.playPortalSE();
	pi.warp(541020800, "sp");
	return true;
    } else {
	if (pi.getMap(541020800).getSpeedRunStart() == 0 && (pi.getMonsterCount(541020800) <= 0 || pi.getMap(541020800).isDisconnected(pi.getPlayer().getId()))) {
	    pi.playPortalSE();
		pi.setBossLog("树精王");
	    pi.warp(541020800, "sp");
	    return true;
	} else {
	    pi.playerMessage(5, "有人在挑战中.");
	    return false;
	}
    }
		}else {
            pi.playerMessage(5,"检测到你实力不够，还不可以挑战！");
            return false;
            }
}