var status = -1;

function action(mode, type, selection) {
    if (cm.isQuestActive(3929)) {
        cm.playerMessage("任務完成。");
        cm.forceCompleteQuest(3929);
    } else if (cm.isQuestActive(3926)) {
        if (cm.haveItem(4031579)) {
            if (cm.getBossLog("耍紅蠍子團3") == 1) {
                cm.sendNext("貌似已經給過米了。");
            } else {
                cm.setBossLog("耍紅蠍子團3");
                cm.gainItem(4031579, -1);
				cm.forceStartQuest(3926);
            }
        }
        cm.dispose();
    }
}