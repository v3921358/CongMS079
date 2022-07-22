var status = -1;

function start() {
    if (cm.getQuestStatus(3360) == 2) {
        if (cm.getMapId() == 261010000) {
            cm.playerMessage("您的名字已經在名單上，現在您可以隨時進出。");
            cm.warp(261030000, "sp_jenu");
        } else {
            cm.playerMessage("您的名字已經在名單上，現在您可以隨時進出。");
            cm.warp(261030000, "sp_alca");
        }
        cm.dispose();
    } else if (cm.getQuestStatus(3360) == 1) {
        cm.sendGetText("請輸入密碼。");
    } else {
        cm.dispose();
    }
}

function action(mode, type, selection) {
    var pw = cm.getText();
    if (cm.getQuestRecord(3360).getCustomData().equals(pw)) {
        cm.forceCompleteQuest(3360);
        cm.playerMessage("密碼正確設備已經開啟。");
    } else {
        cm.sendOk("密碼錯誤。");
    }
    cm.dispose();
}