function enter(pi) {
    if (pi.getQuestStatus(6134) == 1) {
	var em = pi.getEventManager("s4resurrection2");
	if (em == null) {
	    pi.playerMessage("尚未找到副本，請聯繫管理員。");
	} else {
	    var prop = em.getProperty("started");
	    if (prop == null || prop.equals("false")) {
		em.startInstance(pi.getPlayer());
		return true;
	    } else {
		pi.playerMessage("已經有人在挑戰此任務，請稍後在嘗試。");
	    }
	}
    } else {
	pi.playerMessage("由於太過強大您無法進入。");
    }
    return false;
}