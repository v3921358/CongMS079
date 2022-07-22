//黑輪王任務副本
var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selected) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0 && status == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            if (cm.getQuestStatus(8626) == 1) {
                cm.sendNext("準備好挑戰黑輪王了嗎??");
            } else if (cm.getQuestStatus(8626) == 2) {
                //if (cm.getBossLog("heilunwang") >= 5) {
                //     cm.sendOk("每天只能打5次黑輪王！");
                //      cm.dispose();
                //      return;
                //  } else {
                cm.setBossLog("heilunwang");
                cm.warp(741020102, 0);
                cm.dispose();
                //   }
            } else {
                cm.sendNext("很抱歉，您無法通過這裡。");
                cm.dispose();
            }
        } else if (status == 1) {
            var em = cm.getEventManager("SnackBar");
            if (em == null) {
                cm.sendOk("當前副本有問題，請聯絡管理員....");
                cm.dispose();
            } else {
                var prop = em.getProperty("state");
                if (prop.equals("0") || prop == null) {
                    // if (cm.getBossLog("heilunwang") >= 5) {
                    //     cm.sendOk("每天只能打5次黑輪王！");
                    //     cm.dispose();
                    //     return;
                    // } else {
                    cm.setBossLog("heilunwang");
                    em.startInstance(cm.getPlayer(), cm.getMap());
                    cm.dispose();
                    return;
                    //  }
                } else {
                    cm.sendOk("裡面已經有人在挑戰黑輪王了...");
                    cm.dispose();
                }
            }
        }
    }
}