/*
	NPC Name: 		Dida
	Description: 		Quest - Attack! Maverick Platoon of Robots
*/
var status = -1;

function start(mode, type, selection) {
    if (mode == -1) {
        qm.dispose();
    } else {
        if (mode == 1) {
            status++;
        }
        if (status == 0) {
            qm.sendNext("在這裡很危險！如果你打算挑戰它，你需要更多的組隊員，你想做什麼麼？想進去嗎？？");
        } else if (status == 1) {
            qm.warp(802000309, 0);
            //qm.forceStartQuest();
            qm.dispose();
        }
    }
}

function end(mode, type, selection) {
}