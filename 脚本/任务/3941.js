var status = -1;

function start(mode, type, selection) {
    qm.sendNext("任務完成，意外得到一個東西。");
    qm.forceCompleteQuest(3941);
	qm.gainItem(4031571,1);
    qm.dispose();
}

function end(mode, type, selection) {}