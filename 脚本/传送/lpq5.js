function enter(pi) {
    var eim = pi.getEventManager("LudiPQ").getInstance("LudiPQ");
    
    // only let people through if the eim is ready
    if (eim.getProperty("stage5status") == null) { // do nothing; send message to player
	pi.playerMessage(5, "该洞口目前无法进入。");
    } else {
	pi.removeAll(4001022);//扣除背包这个物品
	pi.warp(pi.getMapId() + 100, "st00");
    }
}