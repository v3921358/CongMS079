/*
	NPC Name: 		Ponicher
	Description: 		Quest - A Battle Against Vergamot
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
            qm.sendNext("你確定你要離開這裡嗎？挑戰貝爾加莫特是很不容易的，這些需要你和夥伴們的努力奮鬥，才能打敗他！如果你準備好了，我將送你到貝爾加莫特的基地，準備好了嗎？");
        } else if (status == 1) {
            qm.warp(802000209, 0);
            //qm.forceStartQuest();
            qm.dispose();
        }
    }
}

function end(mode, type, selection) {
}