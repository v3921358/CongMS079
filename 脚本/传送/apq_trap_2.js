function enter(pi) {
	var map = pi.getPlayer().getMap();
	var reactor = map.getReactorByName("gate02");
	var state = reactor.getState();
	if (state >= 4) {
		pi.warp(670010600, 6);
		return true;
	} else {
		pi.getClient().sendPacket(org.rise.tools.MaplePacketCreator.serverNotice(5, "The gate is closed."));
		return false;
	}
}