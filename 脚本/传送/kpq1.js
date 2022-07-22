﻿
/*
Kerning PQ: 1st stage to 2nd stage portal
*/

function enter(pi) {
    var eim = pi.getEventManager("KerningPQ").getInstance("KerningPQ");

    // only let people through if the eim is ready
    if (eim.getProperty("1stageclear") == null) { // do nothing; send message to player
        pi.playerMessage(5, "该洞口目前无法进入。");
    } else {
        pi.warp(103000801, "st00");
		pi.removeAll(4001008);
    }
}