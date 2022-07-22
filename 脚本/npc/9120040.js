/*
	NPC Name: 		Ponicher
	Map(s): 		Neo Tokyo 2100 : Odaiba (802000210)
	Description: 		Vergamot Battle starter
*/
var status = -1;

function start() {
    if (cm.getMapId() == 802000210) {
        if (cm.getPlayer().getClient().getChannel() != 2) {
            cm.sendOk("參加遠征任務請到 2 頻道.");
            cm.dispose();
            return;
        }
        var em = cm.getEventManager("Vergamot");

        if (em == null) {
            cm.sendOk("腳本出錯，請聯繫管理員.");
            cm.dispose();
            return;
        }
        //	var prop = em.getProperty("vergamotSummoned");

        //	if (((prop.equals("PQCleared") || (prop.equals("1")) && cm.getPlayerCount(802000211) == 0)) || prop.equals("0") || prop == null) {
        var prop = em.getProperty("state");
        if (prop == null || prop.equals("0")) {
            var squadAvailability = cm.getSquadAvailability("VERGAMOT");
            if (squadAvailability == -1) {
                status = 0;
                cm.sendYesNo("你想成為遠征隊長嗎？");

            } else if (squadAvailability == 1) {
                // -1 = Cancelled, 0 = not, 1 = true
                var type = cm.isSquadLeader("VERGAMOT");
                if (type == -1) {
                    cm.sendOk("遠征隊已經註銷.請重新發起.");
                    cm.dispose();
                } else if (type == 0) {
                    var memberType = cm.isSquadMember("VERGAMOT");
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
                var eim = cm.getDisconnected("Vergamot");
                if (eim == null) {
                    var squd = cm.getSquad("VERGAMOT");
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
            var eim = cm.getDisconnected("Vergamot");
            if (eim == null) {
                var squd = cm.getSquad("VERGAMOT");
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
            if (cm.registerSquad("VERGAMOT", 5, " 已經成為遠征隊長。如果你想參加遠征任務請在5分鐘內加入遠征隊。")) {
                cm.sendOk("你已經成為遠征隊長，請在5分鐘內整理好你的遠征隊伍，並開始遠征任務。");
            } else {
                cm.sendOk("未知錯誤。成為遠征隊長失敗");
            }
        }
        cm.dispose();
        break;
    case 2:
        if (!cm.reAdd("Vergamot", "VERGAMOT")) {
            cm.sendOk("錯誤。。請再試一次");
        }
        cm.safeDispose();
        break;
    case 3:
        if (mode == 1) {
            var squd = cm.getSquad("VERGAMOT");
            if (squd != null && !squd.getAllNextPlayer().contains(cm.getPlayer().getName())) {
                squd.setNextPlayer(cm.getPlayer().getName());
                cm.sendOk("You have reserved the spot.");
            }
        }
        cm.dispose();
        break;
    case 5:
        if (selection == 0) {
            if (!cm.getSquadList("VERGAMOT", 0)) {
                cm.sendOk("由於未知的錯誤，對遠征隊的要求被拒絕。");
            }
        } else if (selection == 1) { // join
            var ba = cm.addMember("VERGAMOT", true);
            if (ba == 2) {
                cm.sendOk("遠征隊人數已經足夠。請稍後再試");
            } else if (ba == 1) {
                cm.sendOk("加入遠征隊成功");
            } else {
                cm.sendOk("你已經加入遠征隊了.");
            }
        } else { // withdraw
            var baa = cm.addMember("VERGAMOT", false);
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
                if (!cm.getSquadList("VERGAMOT", 0)) {
                    cm.sendOk("由於未知的錯誤，對遠征隊的要求被拒絕。");
                }
                cm.dispose();
            } else if (selection == 1) {
                status = 11;
                if (!cm.getSquadList("VERGAMOT", 1)) {
                    cm.sendOk("由於未知的錯誤，對遠征隊的要求被拒絕。");
                    cm.dispose();
                }
            } else if (selection == 2) {
                status = 12;
                if (!cm.getSquadList("VERGAMOT", 2)) {
                    cm.sendOk("由於未知的錯誤，對遠征隊的要求被拒絕。");
                    cm.dispose();
                }
            } else if (selection == 3) { // get insode
                if (cm.getSquad("VERGAMOT") != null) {
                    var dd = cm.getEventManager("Vergamot");
                    dd.startInstance(cm.getSquad("VERGAMOT"), cm.getMap());
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
        cm.banMember("VERGAMOT", selection);
        cm.dispose();
        break;
    case 12:
        if (selection != -1) {
            cm.acceptMember("VERGAMOT", selection);
        }
        cm.dispose();
        break;
    case 25:
        cm.warp(802000210, 0);
        cm.dispose();
        break;
    }
}