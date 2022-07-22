﻿

var chengqiang = 5;
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
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
            if (cm.getPlayer().getClient().getChannel() != 1 && cm.getPlayer().getClient().getChannel() != 2) {
                cm.sendOk("城牆只能在頻道 1 或 2 能打而已。");
                cm.dispose();
                return;
            } else {
                cm.sendSimple("#b親愛的 #k#h  ##e\r\n#b是否要挑戰城牆副本??#k \r\n#L0##r城牆下1#k#l\r\n#L1##r城牆下2#k#l\r\n#L2##r城牆下3#k#l\r\n#L3##r城牆下4#k#l\r\n#L4##r城牆下5#k#l\r\n#L5##r挑戰獅王#k#l\r\n#L6##r返回自由市場#k#l");
            }
        } else if (status == 1) {

            if (selection == 0 || selection == 1 || selection == 2 || selection == 3 || selection == 4) {
                var pt = cm.getPlayer().getParty();
                if (cm.getBossLog('chengqiang') >= 999) {
                    cm.sendOk("每天只能打5次城牆！");
                    cm.dispose();
                    return;
                } else if (cm.getParty() == null) {
                    cm.sendOk("請組隊再來找我....");
                    cm.dispose();
                    return;
                } else if (!cm.isLeader()) {
                    cm.sendOk("請叫你的隊長來找我!");
                    cm.dispose();
                    return;
                } else if (pt.getMembers().size() < 6) {
                    cm.sendOk("需要 6 人以上的組隊才能進入！!");
                    cm.dispose();
                    return;
                } else {
                    var party = cm.getParty().getMembers();
                    var mapId = cm.getMapId();
                    var channel = cm.getChannelNumber();
                    var next = true;
                    var levelValid = 0;
                    var inMap = 0;

                    var it = party.iterator();
                    while (it.hasNext()) {
                        var cPlayer = it.next();
                        if ((cPlayer.getLevel() >= 140 && cPlayer.getLevel() <= 200) || cPlayer.getJobId() == 900) {
                            levelValid += 1;
                        } else {
                            next = false;
                        }
                        if ((cPlayer.getMapid() == mapId) && (!cPlayer.isCs()) && (cPlayer.isOnline()) && (!cPlayer.isHp0()) && (cPlayer.getChannel() == channel)) {
                            inMap += (cPlayer.getJobId() == 900 ? 2 : 1);
                        }
                    }
                    if (inMap < 2) {
                        next = false;
                    }
                    if (next) {
                        var em;
                        if (selection == 0) {
                            em = cm.getEventManager("chengqiang1");
                        } else if (selection == 1) {
                            em = cm.getEventManager("chengqiang2");
                        } else if (selection == 2) {
                            em = cm.getEventManager("chengqiang3");
                        } else if (selection == 3) {
                            em = cm.getEventManager("chengqiang4");
                        } else if (selection == 4) {
                            em = cm.getEventManager("chengqiang5");
                        } else {
                            cm.sendOk("當前副本有問題，請聯絡管理員....");
                            cm.dispose();
                            return;
                        }
                        if (em == null) {
                            cm.sendOk("當前副本有問題，請聯絡管理員....");
                            cm.dispose();
                            return;
                        } else {
                            var check1;
                            if (selection == 0) {
                                check1 = cm.getMapFactory().getMap(211060100);
                            } else if (selection == 1) {
                                check1 = cm.getMapFactory().getMap(211060300);
                            } else if (selection == 2) {
                                check1 = cm.getMapFactory().getMap(211060500);
                            } else if (selection == 3) {
                                check1 = cm.getMapFactory().getMap(211060700);
                            } else if (selection == 4) {
                                check1 = cm.getMapFactory().getMap(211060900);
                            } else {
                                cm.sendOk("當前副本有問題，請聯絡管理員....");
                                cm.dispose();
                                return;
                            }

                            if (check1.playerCount() != 0) {
                                cm.sendOk("其它遠征隊，正在對戰中。");
                                cm.dispose();
                                return;
                            }

                            var prop = em.getProperty("state");
                            if (prop.equals("0") || prop == null) {
                                em.startInstance(cm.getParty(), cm.getMap());
                                cm.setPartyBossLog("chengqiang");
                                cm.dispose();
                                return;
                            } else {
                                cm.sendOk("裡面已經有人在挑戰...");
                                cm.dispose();
                                return;
                            }
                        }
                    } else {
                        cm.sendOk("你的隊伍貌似沒有達到要求...需要 6 人以上且達到 140 等到 200 等。");
                        cm.dispose();
                        return;
                    }
                }

            } else if (selection == 5) {
                cm.warp(211070000, 1);
                cm.dispose();
                return;
            } else if (selection == 6) {
                cm.warp(910000000, 1);
                cm.dispose();
                return;
            } else {
                cm.sendOk("當前副本有問題，請聯絡管理員....");
                cm.dispose();
                return;
            }
            cm.dispose();
            return;
        }
    }
}
