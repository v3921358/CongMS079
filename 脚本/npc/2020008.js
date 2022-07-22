var status = 0;
var job;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0 && status == 1) {
            cm.sendOk("等您下定決心再次找我吧.");
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            if (cm.getQuestStatus(6192) == 1) {
                if (cm.getParty() != null) {
                    var ddz = cm.getEventManager("ProtectTylus");
                    if (ddz == null) {
                        cm.sendOk("未知的錯誤");
                    } else {
                        var prop = ddz.getProperty("state");
                        if (prop == null || prop.equals("0")) {
                            ddz.startInstance(cm.getParty(), cm.getMap());
                        } else {
                            cm.sendOk("別人已經在努力保護Tylus，請稍後重試了一下.");
                        }
                    }
                } else {
                    cm.sendOk("請隊長來找我談話！");
                }
            } else if (cm.getQuestStatus(6192) == 2) {
                cm.sendOk("你保護了我。謝謝。我會教你的立場技巧.");
                if (cm.getJob() == 112) {
                    if (cm.getPlayer().getMasterLevel(1121002) <= 0) {
                        cm.teachSkill(1121002, 0, 10);
                    }
                } else if (cm.getJob() == 122) {
                    if (cm.getPlayer().getMasterLevel(1221002) <= 0) {
                        cm.teachSkill(1221002, 0, 10);
                    }
                } else if (cm.getJob() == 132) {
                    if (cm.getPlayer().getMasterLevel(1321002) <= 0) {
                        cm.teachSkill(1321002, 0, 10);
                    }
                }
            }
            if (cm.getJob() == 111 || cm.getJob() == 121 || cm.getJob() == 131 || cm.getJob() == 112 || cm.getJob() == 122 || cm.getJob() == 132) {
                cm.sendOk("您屬於劍士部,但是您已經成功三轉了,已經超越了教官的強度了!");
                cm.dispose();
                return;
            }
            if (!(cm.getJob() == 110 || cm.getJob() == 120 || cm.getJob() == 130)) {
                cm.sendOk("請找您的轉職教官,您不屬於劍士部的滾吧!");
                cm.dispose();
                return;
            } else if (cm.getPlayer().getLevel() < 70) {
                cm.sendOk("你的等級尚未滿70等");
                cm.dispose();
                return;
            }
            if (cm.haveItem(4031057, 1)) {
                cm.sendNext("恭喜你到達這裡,最後我將給你一個考驗!");
            } else if (!(cm.haveItem(4031057, 1))) {
                cm.warp(102000003);
                cm.sendOk("去找 #r劍士轉職官#k 他會幫助你的!");
                cm.dispose();
            } else if (cm.getPlayer().getRemainingSp() <= (cm.getLevel() - 70) * 3) {
                cm.sendNext("你的技能點數還沒點完..");
            } else {
                cm.sendOk("你還不能轉職...");
                cm.dispose();
            }
        } else if (status == 1) {
            if (cm.haveItem(4031058, 1)) {
                if (cm.getJob() == 110) {
                    cm.changeJob(111);
                    //cm.getPlayer().gainAp(5);
                    cm.gainItem(4031057, -1);
                    cm.gainItem(4031058, -1);
                    cm.sendOk("恭喜轉職了!");
                    cm.worldMessage("『轉職快報』：恭喜玩家." + cm.getChar().getName() + "  成功三轉-十字軍讓我們熱烈的祝福他/她吧！");
                    cm.dispose();
                } else if (cm.getJob() == 120) {
                    cm.changeJob(121);
                    //cm.getPlayer().gainAp(5);
                    cm.gainItem(4031057, -1);
                    cm.gainItem(4031058, -1);
                    cm.sendOk("恭喜轉職了!");
                    cm.worldMessage("『轉職快報』：恭喜玩家." + cm.getChar().getName() + "  成功三轉-騎士讓我們熱烈的祝福他/她吧！");
                    cm.dispose();
                } else if (cm.getJob() == 130) {
                    cm.changeJob(131);
                    //cm.getPlayer().gainAp(5);
                    cm.gainItem(4031057, -1);
                    cm.gainItem(4031058, -1);
                    cm.sendOk("恭喜轉職了!");
                    cm.worldMessage("『轉職快報』：恭喜玩家." + cm.getChar().getName() + "  成功三轉-龍騎士讓我們熱烈的祝福他/她吧！");
                    cm.dispose();
                }
            } else if (cm.haveItem(4031057, 1))
                cm.sendAcceptDecline("你準備承擔最終測試??");
            else
                cm.sendAcceptDecline("但是，我可以讓你更加強大。雖然你必須證明不僅是你的實力，但你的知識。你準備好挑戰了嗎？");
        } else if (status == 2) {
            if (cm.haveItem(4031057, 1)) {
                cm.sendOk("去找神聖的石頭測驗吧!!.");
                cm.dispose();
            }
        }
    }
}