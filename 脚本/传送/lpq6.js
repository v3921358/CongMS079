function enter(pi) {
	var nextMap = 922010700;
	var eim = pi.getPlayer().getEventInstance()
	var target = eim.getMapInstance(nextMap);
	var targetPortal = target.getPortal("st00");
	// only let people through if the eim is ready
	var avail = eim.getProperty("5stageclear");
	if (avail == null) {
		pi.getPlayer().changeMap(target, targetPortal);
		return true;
	}
}
