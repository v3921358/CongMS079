/*function enter(pi) {
    if (pi.getMap().getAllMonstersThreadsafe().size() == 0) {
	pi.warp(925100100,0); //next
    } else {
	pi.playerMessage(5, "The portal is not opened yet.");
    }
}
*/

function enter(pi) {
    if (pi.haveItem(4001117,6) && pi.getMap().getAllMonstersThreadsafe().size() == 0) {//pi.getMap().getAllMonstersThreadsafe().size() == 0
	pi.gainItem(4001117,-6);
	pi.removeAll(4001117);
	pi.warpParty(925100100,0); //next
    } else {
	pi.playerMessage(5, "需要给我 鼓楼钥匙 * 6 个，并且青光所有怪物，才能开启下一关。");
    }
}