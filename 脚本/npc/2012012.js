var status = -1;

function action(mode, type, selection) {
	if (cm.getQuestStatus(3004) == 1) {
        cm.sendNext("完成任務。");
		cm.forceCompleteQuest(3004);
    } else {
        cm.sendNext("請問有什麼事情嗎??");
		cm.dispose();
    }   
}

