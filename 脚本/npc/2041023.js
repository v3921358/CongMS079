function start() {
    if (cm.getQuestStatus(6225) == 1 || cm.getQuestStatus(6315) == 1) {
	var ret = checkJob();
	if (ret == -1) {
            cm.sendOk("你貌似沒有組隊。");
	} else if (ret == 0) {
            cm.sendOk("你的隊伍人數必須兩個人。");
	} else if (ret == 1) {
            cm.sendOk("你的隊伍裡有一個職業不符合，無法進入另一個世界。");
	} else if (ret == 2) {
            cm.sendOk("你的隊伍裡有一個等級不符合，無法進入另一個世界。");
	} else {
	    var dd = cm.getEventManager("ElementThanatos");
	    if (dd != null) {
		dd.startInstance(cm.getParty(), cm.getMap());
	    } else {
                cm.sendOk("未知的錯誤。");
	    }
	}
    } else {
        cm.sendOk("你看起來似乎沒有足夠準備。");
    }
    cm.dispose();
}

function checkJob() {
    var party = cm.getParty();

    if (party == null) {
	return -1;
    }
    if (party.getMembers().size() != 2) {
	return 0;
    }
    var it = party.getMembers().iterator();

    while (it.hasNext()) {
	var cPlayer = it.next();

	if (cPlayer.getJobId() == 212 || cPlayer.getJobId() == 222 || cPlayer.getJobId() == 900) {
	    if (cPlayer.getLevel() < 120) {
		return 2;
	    }
	} else {
	    return 1;
	}
    }
    return 3;
}