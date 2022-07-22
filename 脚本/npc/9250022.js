﻿/*
 六手邪神 -- 入口NPC
 */

        function start() {
            status = -1;
            action(1, 0, 0);
        }

function action(mode, type, selection) {
    /*if (cm.getPlayer().getClient().getChannel() != 2 && cm.getPlayer().getClient().getChannel() != 3) {
        cm.sendOk("六手邪神只能在頻道 2 或 3 能打而已。");
        cm.dispose();
    } else*/ if (cm.getPlayer().getMapId() != 501030104) {
        cm.sendOk("要打 #b六手邪神#k 請到 #r#m501030104##k 找我申請吧！！");
        cm.dispose();
    } else if (!cm.haveItem(4031722)) {
        cm.sendOk("不明的力量無法進入，需要有太陽火花。");
        cm.dispose();
    }
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
            cm.sendSimple("#b親愛的 #k#h  ##e\r\n#b是否要挑戰六手邪神副本??#k \r\n#L0##r我要挑戰六手邪神#k#l");
        } else if (status == 1) {
            if (selection == 0) {
                var pt = cm.getPlayer().getParty();
                if (cm.getParty() == null) {
                    cm.sendOk("請組隊再來找我....");
                } else if (!cm.isLeader()) {
                    cm.sendOk("請叫你的隊長來找我!");
                } else if (pt.getMembers().size() < 1) {
                    cm.sendOk("需要 1 人以上的組隊才能進入！!");
                    cm.dispose();
                } else {
                    var party = cm.getParty().getMembers();
                    var mapId = cm.getMapId();
                    var next = true;
                    var levelValid = 0;
                    var inMap = 0;

                    var it = party.iterator();
                    while (it.hasNext()) {
                        var cPlayer = it.next();
                        if ((cPlayer.getLevel() >= 90 && cPlayer.getLevel() <= 200) || cPlayer.getJobId() == 900) {
                            levelValid += 1;
                        } else {
                            next = false;
                        }
                        if (cPlayer.getMapid() == mapId) {
                            inMap += (cPlayer.getJobId() == 900 ? 3 : 1);
                        }
                    }
                    if (party.size() < 1 || inMap < 1) {
                        next = false;
                    }
                    if (next) {
                        var em = cm.getEventManager("Ravana");
                        if (em == null) {
                            cm.sendOk("當前副本有問題，請聯絡管理員....");
                        } else {
                            var prop = em.getProperty("state");
                            if (prop.equals("0") || prop == null) {
                                em.startInstance(cm.getParty(), cm.getMap());
                                cm.dispose();
                                return;
                            } else {
                                cm.sendOk("裡面已經有人在挑戰...");
                            }
                        }
                    } else {
                        cm.sendOk("等級尚未達到 #r90#k 或者已經超過 #r200#k");
                    }
                }
                cm.dispose();
            }
        }
    }
}