
/* Author: aaroncsn(MapleSea Like)(Incomplete)
NPC Name: 		Humanoid A
Map(s): 		Sunset Road: Magatia(2610000000)
Description: 		Unknown
 */

function start() {
	if (cm.isQuestActive(3335)) {
		if (!cm.canHold(4000361)) {
			cm.sendNext("請檢察背包空間。");
			if (cm.haveItem(4000361) && !cm.haveItem(4031695)) {
				cm.gainItem(4000361, -1);
				cm.gainItem(4031695, 1);
			} else {
				cm.sendNext("您好像沒有滿足條件呢。");
				cm.dispose();
			}
		}
	} else {
		cm.sendNext("我想是一個人，一個人有著溫暖的心臟......這樣也許我可以牽她的手。不幸的是，現在這是不可能的.");
	}
	cm.dispose();
}
