var status = -1;

function start(mode, type, selection) {
}

function end(mode, type, selection) {
    qm.sendNext("感謝您~~");
    qm.forceCompleteQuest();
    qm.dispose();
}