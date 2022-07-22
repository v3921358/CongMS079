/* Mu Young
 Boss Balrog
 */


var status = -1;

function action(mode, type, selection) {
    switch (status) {
        case -1:
            status = 0;
            switch (cm.getChannelNumber()) {
                default:
                    cm.sendNext("哈喽，想要挑战强大的巨魔蝙蝠怪吗？只有等级达到120级才可以来挑战哦！");
                    break;
            }
            break;
        case 0:
            var em = cm.getEventManager("BossBalrog");

            if (em == null) {
                cm.sendOk("目前副本出了一点问题，请联络GM！");
                cm.safeDispose();
                return;
            }

            var check1 = cm.getMapFactory().getMap(105100301);
            if (check1.playerCount() != 0) {
                cm.sendNext("其它远征队，正在对战中。");
                cm.dispose();
                return;
            }

            var prop = em.getProperty("state");
            if (prop == null || prop.equals("0")) {
                var squadAvailability = cm.getSquadAvailability("BossBalrog");
                if (squadAvailability == -1) {
                    status = 1;
                    cm.sendYesNo("现在可以申请远征队，你想成为远征队队长吗？");

                } else if (squadAvailability == 1) {
                    // -1 = Cancelled, 0 = not, 1 = true
                    var type = cm.isSquadLeader("BossBalrog");
                    if (type == -1) {
                        cm.sendOk("已经结束了申请。");
                        cm.safeDispose();
                    } else if (type == 0) {
                        var memberType = cm.isSquadMember("BossBalrog");
                        if (memberType == 2) {
                            cm.sendOk("在远征队的制裁名单里。");
                            cm.safeDispose();
                        } else if (memberType == 1) {
                            status = 5;
                            cm.sendSimple("你要做什么? \r\n#b#L0#加入远征队#l \r\n#b#L1#退出远征队#l \r\n#b#L2#查看远征队名单#l");
                        } else if (memberType == -1) {
                            cm.sendOk("远征队员已经达到30名，请稍后再试。");
                            cm.safeDispose();
                        } else {
                            status = 5;
                            cm.sendSimple("你要做什么? \r\n#b#L0#查看远征队名单#l \r\n#b#L1#加入远征队#l \r\n#b#L2#退出远征队#l");
                        }
                    } else { // Is leader
                        status = 10;
                        cm.sendSimple("你要做什么？\r\n#b#L0#查看远征队名单。#l \r\n#b#L1#管理远征队成员。#l \r\n#b#L2#编辑限制列表。#l \r\n#r#L3#进入挑战。#l");
                        // TODO viewing!
                    }
                } else {
                    var eim = cm.getDisconnected("BossBalrog");
                    if (eim == null) {
                        cm.sendOk("已经有人在挑战了哦，请换线或等待.");
                        cm.safeDispose();
                    } else {
                        cm.sendYesNo("你要继续进行远征任务吗？");
                        status = 2;
                    }
                }
            } else {
                var eim = cm.getDisconnected("BossBalrog");
                if (eim == null) {
                    cm.sendOk("远征队的挑战已经开始.");
                    cm.safeDispose();
                } else {
                    cm.sendYesNo("你要继续进行远征任务吗？");
                    status = 2;
                }
            }
            break;
        case 1:
            if (mode == 1) {
                var lvl = cm.getPlayerStat("LVL");
                if (lvl >= 120 ) {
                    if (cm.registerSquad("BossBalrog", 5, " 已经成为了远征队队长。如果你想加入远征队，请重新打开对话申请加入远征队。")) {
                        cm.sendOk("已经成为了远征队队长。接下來的5分钟，请等待队员们的申请。");
                    } else {
                        cm.sendOk("未知错误.");
                    }
                } else {
                    cm.sendNext("有一个远征队成员的等级不是120到200之间！！！。");
                }
            } else {
                cm.sendOk("如果你想再次申请远征队的话请告诉我。");
            }
            cm.safeDispose();
            break;
        case 2:
            if (!cm.reAdd("BossBalrog", "BossBalrog")) {
                cm.sendOk("由于未知错误，操作失败。");
            }
            cm.safeDispose();
            break;
        case 5:
            if (selection == 0) {
                if (!cm.getSquadList("BossBalrog", 0)) {
                    cm.sendOk("由于未知错误，操作失败。");
                    cm.safeDispose();
                } else {
                    cm.dispose();
                }
            } else if (selection == 1) { // join
                var lvlel = cm.getPlayer().getLevel();
                if (lvlel >= 120 ) {
                    var ba = cm.addMember("BossBalrog", true);
                    if (ba == 2) {
                        cm.sendOk("远征队队员已经达到30名，请稍后再试。");
                        cm.safeDispose();
                    } else if (ba == 1) {
                        cm.sendOk("申请加入远征队成功，请等待队长的指示。");
                        cm.safeDispose();
                    } else {
                        cm.sendOk("你已经参加了远征队，请等候队长指示。");
                        cm.safeDispose();
                    }
                } else {
                    cm.sendNext("等级不是50到200之间。");
                }

            } else {// withdraw
                var baa = cm.addMember("BossBalrog", false);
                if (baa == 1) {
                    cm.sendOk("成功退出远征队。");
                    cm.safeDispose();
                } else {
                    cm.sendOk("你沒有參加远征队。");
                    cm.safeDispose();
                }
            }
            break;
        case 10:
            if (selection == 0) {
                if (!cm.getSquadList("BossBalrog", 0)) {
                    cm.sendOk("由于未知错误，操作失败。");
                }
                cm.safeDispose();
            } else if (selection == 1) {
                status = 11;
                if (!cm.getSquadList("BossBalrog", 1)) {
                    cm.sendOk("由于未知错误，操作失败。");
                }
                cm.safeDispose();
            } else if (selection == 2) {
                status = 12;
                if (!cm.getSquadList("BossBalrog", 2)) {
                    cm.sendOk("由于未知错误，操作失败。");
                }
                cm.safeDispose();
            } else if (selection == 3) { // get insode
                if (cm.getSquad("BossBalrog") != null) {
                    var dd = cm.getEventManager("BossBalrog");
                    dd.startInstance(cm.getSquad("BossBalrog"), cm.getMap());
                    cm.dispose();
                } else {
                    cm.sendOk("由于未知错误，操作失败。");
                    cm.safeDispose();
                }
            }
            break;
        case 11:
            cm.banMember("BossBalrog", selection);
            cm.dispose();
            break;
        case 12:
            if (selection != -1) {
                cm.acceptMember("BossBalrog", selection);
            }
            cm.dispose();
            break;
    }
}