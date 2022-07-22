﻿load('nashorn:mozilla_compat.js');
importPackage(Packages.handling.world);

var points;

function start() {
    var record = cm.getQuestRecord(150001);
    points = record.getCustomData() == null ? "0" : record.getCustomData();
    cm.sendSimple(/*"想挑戰BOSS副本嗎？？\n\r\n\r #b#L3#查看可兌換點數#l#k \r\n\r\n #b#L0##v03994115##l#l#L1##v03994116##l#L2##v03994117##l#L28##v03994118##l \r\n" +*/"#b#L4#進入幸福村#l#k                                            #b#L5#沉睡森林(一層)#b#L6#沉睡森林(三層)#b#L7#沉睡森林(五層)                   #b#L8#忍耐之林(一層)#b#L9#忍耐之林(三層)                   #b#L10#障礙競走<第一階段>                                  #b#L11#進入獅王城<入口>");
}

function action(mode, type, selection) {
    if (mode == 1) {
        switch (selection) {
            case 0:
                if (cm.getParty() != null) {
                    if (cm.getDisconnected("BossQuestEASY") != null) {
                        cm.getDisconnected("BossQuestEASY").registerPlayer(cm.getPlayer());
                    } else if (cm.isLeader()) {
                        if (World.isShutDown) {
                            cm.sendNext("因為伺服器在維修中，所以暫時不能打BOSSPQ。");
                            cm.dispose();
                            return;
                        }
                        var party = cm.getPlayer().getParty().getMembers();
                        var mapId = cm.getPlayer().getMapId();
                        var next = true;
                        var it = party.iterator();
                        while (it.hasNext()) {
                            var cPlayer = it.next();
                            var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                            if (ccPlayer == null || ccPlayer.getLevel() < 70) {
                                next = false;
                                break;
                            }
                        }
                        if (next) {
                            var q = cm.getEventManager("BossQuestEASY");
                            if (q == null) {
                                cm.sendOk("找不到腳本，請聯繫GM！");
                                cm.dispose();
                                break;
                            } else {
                                q.startInstance(cm.getParty(), cm.getMap());
                            }
                        } else {
                            cm.sendOk("全部隊友必須達到70等.");
                            cm.dispose();
                            break;
                        }
                    } else {
                        cm.sendOk("你不是隊長.");
                        cm.dispose();
                        break;
                    }
                } else {
                    cm.sendOk("你沒有隊伍.");
                    cm.dispose();
                    break;
                }
                break;
            case 1:
                if (cm.getParty() != null) {
                    if (cm.getDisconnected("BossQuestMed") != null) {
                        cm.getDisconnected("BossQuestMed").registerPlayer(cm.getPlayer());
                    } else if (cm.isLeader()) {
                        if (World.isShutDown) {
                            cm.sendNext("因為伺服器在維修中，所以暫時不能打BOSSPQ。");
                            cm.dispose();
                            return;
                        }
                        var party = cm.getPlayer().getParty().getMembers();
                        var mapId = cm.getPlayer().getMapId();
                        var next = true;
                        var it = party.iterator();
                        while (it.hasNext()) {
                            var cPlayer = it.next();
                            var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                            if (ccPlayer == null || ccPlayer.getLevel() < 100) {
                                next = false;
                                break;
                            }
                        }
                        if (next) {
                            var q = cm.getEventManager("BossQuestMed");
                            if (q == null) {
                                cm.sendOk("找不到腳本，請聯繫GM！");
                                cm.dispose();
                                break;
                            } else {
                                q.startInstance(cm.getParty(), cm.getMap());
                            }
                        } else {
                            cm.sendOk("全部隊友必須達到100等.");
                            cm.dispose();
                            break;
                        }
                    } else {
                        cm.sendOk("你不是隊長.");
                        cm.dispose();
                        break;
                    }
                } else {
                    cm.sendOk("你沒有隊伍.");
                    cm.dispose();
                    break;
                }
                break;
            case 2:
                if (cm.getParty() != null) {
                    if (cm.getDisconnected("BossQuestHARD") != null) {
                        cm.getDisconnected("BossQuestHARD").registerPlayer(cm.getPlayer());
                    } else if (cm.isLeader()) {
                        if (World.isShutDown) {
                            cm.sendNext("因為伺服器在維修中，所以暫時不能打BOSSPQ。");
                            cm.dispose();
                            return;
                        }
                        var party = cm.getPlayer().getParty().getMembers();
                        var mapId = cm.getPlayer().getMapId();
                        var next = true;
                        var it = party.iterator();
                        while (it.hasNext()) {
                            var cPlayer = it.next();
                            var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                            if (ccPlayer == null || ccPlayer.getLevel() < 120) {
                                next = false;
                                break;
                            }
                        }
                        if (next) {
                            var q = cm.getEventManager("BossQuestHARD");
                            if (q == null) {
                                cm.sendOk("找不到腳本，請聯繫GM！");
                                cm.dispose();
                            } else {
                                q.startInstance(cm.getParty(), cm.getMap());
                            }
                        } else {
                            cm.sendOk("全部隊友必須達到120等.");
                            cm.dispose();
                            break;
                        }
                    } else {
                        cm.sendOk("你不是隊長.");
                        cm.dispose();
                        break;
                    }
                } else {
                    cm.sendOk("你沒有隊伍.");
                    cm.dispose();
                    break;
                }
                break;
            case 28:
                if (cm.getParty() != null) {
                    if (cm.getDisconnected("BossQuestHELL") != null) {
                        cm.getDisconnected("BossQuestHELL").registerPlayer(cm.getPlayer());
                    } else if (cm.isLeader()) {
                        if (World.isShutDown) {
                            cm.sendNext("因為伺服器在維修中，所以暫時不能打BOSSPQ。");
                            cm.dispose();
                            return;
                        }
                        var party = cm.getPlayer().getParty().getMembers();
                        var mapId = cm.getPlayer().getMapId();
                        var next = true;
                        var it = party.iterator();
                        while (it.hasNext()) {
                            var cPlayer = it.next();
                            var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
                            if (ccPlayer == null || ccPlayer.getLevel() < 160) {
                                next = false;
                                break;
                            }
                        }
                        if (next) {
                            var q = cm.getEventManager("BossQuestHELL");
                            if (q == null) {
                                cm.sendOk("找不到腳本，請聯繫GM！");
                                cm.dispose();
                            } else {
                                q.startInstance(cm.getParty(), cm.getMap());
                            }
                        } else {
                            cm.sendOk("全部隊友必須達到160等.");
                            cm.dispose();
                            break;
                        }
                    } else {
                        cm.sendOk("你不是隊長.");
                        cm.dispose();
                        break;
                    }
                } else {
                    cm.sendOk("你沒有隊伍.");
                    cm.dispose();
                    break;
                }
                break;
            case 3:
                cm.sendOk("#b點數數量 : " + points);
                cm.dispose();
                break;
            case 4:
                cm.warp(209000000);
                break;
            case 5:
                cm.warp(105040310);
                break;
            case 6:
                cm.warp(105040312);
                break;
            case 7:
                cm.warp(105040314);
                break;
            case 8:
                cm.warp(101000100);
                break;
            case 9:
                cm.warp(101000102);
                break;
            case 10:
                cm.warp(109040001);
                break;
            case 11:
                cm.warp(211060000);
                break;

        }
    }
    cm.dispose();
}
