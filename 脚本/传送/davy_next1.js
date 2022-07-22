function enter(pi) {
    if (pi.haveItem(4001120,30)&&pi.haveItem(4001121,30)&&pi.haveItem(4001122,30)) {
	pi.gainItem(4001120,-30);
	pi.gainItem(4001121,-30);
	pi.gainItem(4001122,-30);
	pi.removeAll(4001117);
	pi.removeAll(4001120);
	pi.removeAll(4001121);
	pi.removeAll(4001122);
	pi.warpParty(925100400,0); 
    } else {
	pi.playerMessage(5, "给我 30 个 初级 中级 高级 海盗道具");
    }
}