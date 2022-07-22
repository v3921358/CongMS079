var status = -1;

function start(mode, type, selection) {
}

function end(mode, type, selection) {
    if (qm.haveItem(2210006, 1)) {
        qm.sendNext("任務完成\r\n獎勵:#r經驗值:7500 名聲x3");
        qm.gainItem(2210006, -1);
        qm.getPlayer().addFame(3);
        qm.gainExp(7500);
        qm.forceCompleteQuest();
    } else {
        qm.sendNext("你沒有我需要的東西。");
    }
    qm.dispose();
}
