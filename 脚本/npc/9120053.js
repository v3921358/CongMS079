/*
	NPC Name: 		Entrance Lock
	Map(s): 		Zipangu : 2012 Roppongi Mall
	Description: 		Core Blaze battle
*/
var status = -1;

function start() {
    if (cm.getMapId() == 802000800) {
        if (cm.getPlayer().getClient().getChannel() != 2) {
            cm.sendOk("參加遠征任務請到 2 頻道.");
            cm.dispose();
            return;
        }
        var em = cm.getEventManager("CoreBlaze");

        if (em == null) {
            cm.sendOk("腳本出錯，請聯繫管理員.");
            cm.dispose();
            return;
        }
        var prop = em.getProperty("state");
        if (prop == null || prop.equals("0")) {
            var squadAvailability = cm.getSquadAvailability("Core_Blaze");
            if (squadAvailability == -1) {
                status = 0;
                cm.sendYesNo("你想成為遠征隊長嗎？");

            } else if (squadAvailability == 1) {
                var type = cm.isSquadLeader("Core_Blaze");
                if (type == -1) {
                    cm.sendOk("遠征隊已經註銷.請重新發起.");
                    cm.dispose();
                } else if (type == 0) {
                    var memberType = cm.isSquadMember("Core_Blaze");
                    if (memberType == 2) {
                        cm.sendOk("你被加入制裁名單，不能進行遠征任務.");
                        cm.dispose();
                    } else if (memberType == 1) {
                        status = 5;
                        cm.sendSimple("你想幹什麼? \r\n#b#L0#查看遠征隊#l \r\n#b#L1#加入遠征隊#l \r\n#b#L2#離開遠征隊#l");
                    } else if (memberType == -1) {
                        cm.sendOk("遠征隊已經註銷，請重新發起。");
                        cm.dispose();
                    } else {
                        status = 5;
                        cm.sendSimple("你想幹什麼? \r\n#b#L0#查看遠征隊#l \r\n#b#L1#加入遠征隊#l \r\n#b#L2#離開遠征隊#l");
                    }
                } else { // Is leader
                    status = 10;
                    cm.sendSimple("你想做什麼?遠征隊長。 \r\n#b#L0#查看遠征隊#l \r\n#b#L1#制裁遠征隊員#l \r\n#b#L2#查看制裁名單#l \r\n#r#L3#開始遠征任務#l");
                    // TODO viewing!
                }
            } else {
                var eim = cm.getDisconnected("CoreBlaze");
                if (eim == null) {
                    var squd = cm.getSquad("Core_Blaze");
                    if (squd != null) {
                        cm.sendYesNo("遠征任務已經開始.\r\n" + squd.getNextPlayer());
                        status = 3;
                    } else {
                        cm.sendOk("遠征任務已經開始");
                        cm.safeDispose();
                    }
                } else {
                    cm.sendYesNo("你要繼續遠征任務嗎?");
                    status = 2;
                }
            }
        } else {
            var eim = cm.getDisconnected("CoreBlaze");
            if (eim == null) {
                var squd = cm.getSquad("CoreBlaze");
                if (squd != null) {
                    cm.sendYesNo("遠征任務已經開始\r\n" + squd.getNextPlayer());
                    status = 3;
                } else {
                    cm.sendOk("遠征任務已經開始");
                    cm.safeDispose();
                }
            } else {
                cm.sendYesNo("你要繼續進行遠征任務嗎?");
                status = 2;
            }
        }
    } else {
        status = 25;
        cm.sendNext("你想退出遠征隊嗎?");
    }
}

function action(mode, type, selection) {
    switch (status) {
    case 0:
        if (mode == 1) {
            if (cm.registerSquad("Core_Blaze", 5, " 已經成為遠征隊長。如果你想參加遠征任務請在5分鐘內加入遠征隊。")) {
                cm.sendOk("你已經成為遠征隊長，請在5分鐘內整理好你的遠征隊伍，並開始遠征任務。");
            } else {
                cm.sendOk("未知錯誤。成為遠征隊長失敗");
            }
        }
        cm.dispose();
        break;
    case 2:
        if (!cm.reAdd("CoreBlaze", "Core_Blaze")) {
            cm.sendOk("錯誤。。請再試一次");
        }
        cm.safeDispose();
        break;
    case 3:
        if (mode == 1) {
            var squd = cm.getSquad("Core_Blaze");
            if (squd != null && !squd.getAllNextPlayer().contains(cm.getPlayer().getName())) {
                squd.setNextPlayer(cm.getPlayer().getName());
                cm.sendOk("You have reserved the spot.");
            }
        }
        cm.dispose();
        break;
    case 5:
        if (selection == 0) {
            if (!cm.getSquadList("Core_Blaze", 0)) {
                cm.sendOk("由於未知的錯誤，對遠征隊的要求被拒絕。");
            }
        } else if (selection == 1) { // join
            var ba = cm.addMember("Core_Blaze", true);
            if (ba == 2) {
                cm.sendOk("遠征隊人數已經足夠。請稍後再試");
            } else if (ba == 1) {
                cm.sendOk("加入遠征隊成功");
            } else {
                cm.sendOk("你已經加入遠征隊了.");
            }
        } else { // withdraw
            var baa = cm.addMember("Core_Blaze", false);
            if (baa == 1) {
                cm.sendOk("退出遠征隊成功");
            } else {
                cm.sendOk("你還沒有加入遠征隊.");
            }
        }
        cm.dispose();
        break;
    case 10:
        if (mode == 1) {
            if (selection == 0) {
                if (!cm.getSquadList("Core_Blaze", 0)) {
                    cm.sendOk("由於未知的錯誤，對遠征隊的要求被拒絕。");
                }
                cm.dispose();
            } else if (selection == 1) {
                status = 11;
                if (!cm.getSquadList("Core_Blaze", 1)) {
                    cm.sendOk("由於未知的錯誤，對遠征隊的要求被拒絕。");
                    cm.dispose();
                }
            } else if (selection == 2) {
                status = 12;
                if (!cm.getSquadList("Core_Blaze", 2)) {
                    cm.sendOk("由於未知的錯誤，對遠征隊的要求被拒絕。");
                    cm.dispose();
                }
            } else if (selection == 3) { // get insode
                if (cm.getSquad("Core_Blaze") != null) {
                    var dd = cm.getEventManager("CoreBlaze");
                    dd.startInstance(cm.getSquad("Core_Blaze"), cm.getMap());
                } else {
                    cm.sendOk("由於未知的錯誤，對遠征隊的要求被拒絕。");
                }
                cm.dispose();
            }
        } else {
            cm.dispose();
        }
        break;
    case 11:
        cm.banMember("CoreBlaze", selection);
        cm.dispose();
        break;
    case 12:
        if (selection != -1) {
            cm.acceptMember("CoreBlaze", selection);
        }
        cm.dispose();
        break;
    case 25:
        cm.warp(802000800, 0);
        cm.dispose();
        break;
    }
}