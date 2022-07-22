/*
	NPC Name: 		Old Fox Flagship Al
	Description: 		Quest - Battling Nibergen
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
            qm.sendNext("好了，你即將挑戰它了！謝謝……。只是讓你知道，敵人可能是非常強大的。你準備好了嗎？");
        } else if (status == 1) {
            qm.warp(802000609, 0);
            //qm.forceStartQuest();
            qm.dispose();
        }
    }
}

function end(mode, type, selection) {
}