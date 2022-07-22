function start() {
    if (cm.getQuestStatus(6108) == 1) {
        var ret = checkJob();
        if (ret == -1) {
            cm.sendOk("請先組隊在確認一次。");
        } else if (ret == 0) {
            cm.sendOk("請確認隊伍裡面有兩個人。");
        } else if (ret == 1) {
            cm.sendOk("隊伍其中一個人沒有符合資格。");
        } else if (ret == 2) {
            cm.sendOk("隊伍裡有成員尚未符合。");
        } else {
            var em = cm.getEventManager("s4aWorld");
            if (em == null) {
                cm.sendOk("找不到副本請聯繫管理員。");
            } else if (em.getProperty("started").equals("true")) {
                cm.sendOk("已經有隊伍正在挑戰，請稍後再嘗試。");
            } else {
                em.startInstance(cm.getParty(), cm.getMap());
            }
        }
    }
    cm.dispose();
}

function action(mode, type, selection) {}

function checkJob() {
    var party = cm.getParty();

    if (party == null) {
        return -1;
    }

    var it = party.getMembers().iterator();

    while (it.hasNext()) {
        var cPlayer = it.next();

        if (cPlayer.getJobId() == 312 || cPlayer.getJobId() == 322 || cPlayer.getJobId() == 900) {
            if (cPlayer.getLevel() < 120) {
                return 2;
            }
        } else {
            return 1;
        }
    }
    return 3;
}