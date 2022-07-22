/*
 NPC Name: 		Adobis
 Map(s): 		El Nath : Entrance to Zakum Altar
 Description: 		Zakum battle starter
 */
var status = 0;

function action(mode, type, selection) {
    if (cm.getPlayer().getMapId() == 211042200) {
        if (selection == -1) {
            cm.dispose();
            return;
        }

        if (selection < 100) {
            cm.sendSimple("#r#L100#扎昆入口#l\r\n#L101#进阶扎昆入口#l");
        } else {
            if (selection == 100) {
                cm.warp(211042300, 0);
            } else if (selection == 101) {
                cm.warp(211042301, 0);
            }
            cm.dispose();
            return;
        }
        return;
    } else if (cm.getPlayer().getMapId() == 211042401) {
        switch (status) {
            case 0:
                if (cm.getPlayer().getLevel() < 100) {
                    cm.sendOk("你的的等级不足100");
                    cm.dispose();
                    return;
                }
                if (cm.getPlayer().getClient().getChannel() != 1 && cm.getPlayer().getClient().getChannel() != 2) {
                    cm.sendOk("只能在1频道或2频道打");
                    cm.dispose();
                    return;
                }
                var emm = cm.getEventManager("ChaosZakum");

                if (emm == null) {
                    cm.sendOk("脚本错误，请联系GM.");
                    cm.safeDispose();
                    return;
                }
                var prop = emm.getProperty("state");
                var marr = cm.getQuestRecord(160102);
                var data = marr.getCustomData();
                if (data == null) {
                    marr.setCustomData("0");
                    data = "0";
                }
                var time = parseInt(data);
                if (prop == null || prop.equals("0")) {
                    var squadAvailability = cm.getSquadAvailability("ChaosZak");
                    if (squadAvailability == -1) {
                        status = 1;
                        cm.sendYesNo("你有兴趣成为远征队长吗?");

                    } else if (squadAvailability == 1) {
                        // -1 = Cancelled, 0 = not, 1 = true
                        var type = cm.isSquadLeader("ChaosZak");
                        if (type == -1) {
                            cm.sendOk("远征队已结束，请重新登记。");
                            cm.safeDispose();
                        } else if (type == 0) {
                            var memberType = cm.isSquadMember("ChaosZak");
                            if (memberType == 2) {
                                cm.sendOk("你已经被远征队长踢出.");
                                cm.safeDispose();
                            } else if (memberType == 1) {
                                status = 5;
                                cm.sendSimple("你想做什麽? \r\n#b#L0#查看成员#l \r\n#b#L1#加入远征队#l \r\n#b#L2#退出远征队#l");
                            } else if (memberType == -1) {
                                cm.sendOk("远征队已结束，请重新登记。");
                                cm.safeDispose();
                            } else {
                                status = 5;
                                cm.sendSimple("你想做什麽? \r\n#b#L0#查看成员#l \r\n#b#L1#加入远征队#l \r\n#b#L2#退出远征队#l");
                            }
                        } else { // Is leader
                            status = 10;
                            cm.sendSimple("你想做什麽? \r\n#b#L0#查看成员#l \r\n#b#L1#移除成员#l \r\n#b#L2#编辑限制列表#l \r\n#r#L3#进入地图#l");
                            // TODO viewing!
                        }
                    } else {
                        var props = emm.getProperty("leader");
                        if (props != null && props.equals("true")) {
                            var eim = cm.getDisconnected("ChaosZakum");
                            if (eim == null) {
                                cm.sendOk("远征队与扎昆的战斗已经开始。");
                                cm.safeDispose();
                            } else {
                                cm.sendOk("远征队与扎昆的战斗已经开始。");
                                cm.safeDispose();
                            }
                        } else {
                            cm.sendOk("很抱歉你的远征队队长离开了现场，所以你不能再返回战场。");
                            cm.safeDispose();
                        }
                    }
                } else {
                    var props = emm.getProperty("leader");
                    if (props != null && props.equals("true")) {
                        var eimc = emm.getInstance("ChaosZakum");
                        var propsc = eimc.getProperty("isSquadPlayerID_" + cm.getPlayer().getId());
                        var sayc = "\r\n" + (eimc == null ? "eimc is null" : propsc) + "\r\n";
                        if ((eimc != null) && (propsc != null) && propsc.equals("1")) {
                            status = 13;
                            sayc += "#b现在是否要重新返回远征队所在场地？";
                            sayc += "\r\n#r#L1#重新返回远征队所在场地#l";
                            cm.sendSimple(sayc);
                        } else {
                            eim = cm.getDisconnected("ChaosZakum");
                            if (eim == null) {
                                cm.sendOk("远征队与扎昆的战斗已经开始。" + sayc);
                                cm.safeDispose();
                            } else {
                                cm.sendOk("远征队与扎昆的战斗已经开始。" + sayc);
                                cm.safeDispose();
                            }
                        }
                    } else {
                        var eimd = emm.getInstance("ChaosZakum");
                        var propsd = eimd.getProperty("isSquadPlayerID_" + cm.getPlayer().getId());
                        var sayd = "\r\n" + (eimd == null ? "eimd is null" : propsd) + "\r\n";
                        if ((eimd != null) && (propsd != null) && propsd.equals("1")) {
                            status = 13;
                            sayd += "#b现在是否要重新返回远征队所在场地？";
                            sayd += "\r\n#r#L1#重新返回远征队所在场地#l";
                            cm.sendSimple(sayd);
                        } else {

                            cm.sendOk("很抱歉你的远征队队长离开了现场，所以你不能再返回战场。");
                            cm.safeDispose();
                        }
                    }
                }
                break;
            case 1:
                if (mode == 1) {
                    if (cm.getPlayer().getBossLogD("混沌扎昆次数") == 5) {
                        cm.sendOk("很抱歉每天只能打5次..");
                        cm.dispose();
                        return;
                    }
                    if (cm.registerSquad("ChaosZak", 5, " 被任命为远征队长，如果你想参加，请在规定时间内加入远征队。")) {
                        cm.sendOk("你被任命为远征队长，接下来的5分钟你可以等待你的成员加入。");
                        cm.getPlayer().setBossLog("混沌扎昆次数");
                    } else {
                        //cm.warp(280030000, 1);
                        cm.sendOk("添加远征队时发生错误。");
                    }
                } else {
                    cm.sendOk("如果你想成为远征队长，请和我谈话.");
                }
                cm.safeDispose();
                break;
            case 2:
                if (!cm.reAdd("ChaosZakum", "ChaosZak")) {
                    cm.sendOk("错误，请稍后再试。.");
                }
                cm.dispose();
                break;
            case 3:
                if (mode == 1) {
                    var squd = cm.getSquad("ChaosZak");
                    if (squd != null && !squd.getAllNextPlayer().contains(cm.getPlayer().getName())) {
                        if (cm.getPlayer().getBossLogD("混沌扎昆次数") == 5) {
                            cm.sendOk("很抱歉每天只能打5次..");
                            cm.dispose();
                            return;
                        }
                        squd.setNextPlayer(cm.getPlayer().getName());
                        cm.sendOk("你已经加入了远征队。");
                        cm.getPlayer().setBossLog("混沌扎昆次数");
                    }
                }
                cm.dispose();
                break;
            case 5:
                if (selection == 0) {
                    if (!cm.getSquadList("ChaosZak", 0)) {
                        cm.sendOk("由于未知错误，你加入远征队的请求被拒绝。");
                        cm.safeDispose();
                    } else {
                        cm.dispose();
                    }
                } else if (selection == 1) { // join
                    var ba = cm.addMember("ChaosZak", true);
                    if (ba == 2) {
                        cm.sendOk("远征队已满员，请稍后再试。");
                        cm.safeDispose();
                    } else if (ba == 1) {
                        if (cm.getPlayer().getBossLogD("混沌扎昆次数") == 5) {
                            cm.sendOk("很抱歉每天只能打5次..");
                            cm.dispose();
                            return;
                        }
                        cm.getPlayer().setBossLog("混沌扎昆次数");
                        cm.sendOk("你已成功加入远征队。");
                        cm.safeDispose();
                    } else {
                        cm.sendOk("你已加入远征队。");
                        cm.safeDispose();
                    }
                } else {// withdraw
                    var baa = cm.addMember("ChaosZak", false);
                    if (baa == 1) {
                        cm.sendOk("你已成功从远征队中退出。");
                        cm.safeDispose();
                    } else {
                        cm.sendOk("你不是远征队的一员。");
                        cm.safeDispose();
                    }
                }
                break;
            case 10:
                if (selection == 0) {
                    if (!cm.getSquadList("ChaosZak", 0)) {
                        cm.sendOk("由于未知错误，远征队请求被拒绝。");
                    }
                    cm.safeDispose();
                } else if (selection == 1) {
                    status = 11;
                    if (!cm.getSquadList("ChaosZak", 1)) {
                        cm.sendOk("由于未知错误，远征队请求被拒绝。");
                        cm.safeDispose();
                    }

                } else if (selection == 2) {
                    status = 12;
                    if (!cm.getSquadList("ChaosZak", 2)) {
                        cm.sendOk("由于未知错误，远征队请求被拒绝。");
                        cm.safeDispose();
                    }

                } else if (selection == 3) { // get insode
                    if (cm.getSquad("ChaosZak") != null) {
                        var dd = cm.getEventManager("ChaosZakum");
                        dd.startInstance(cm.getSquad("ChaosZak"), cm.getMap(), 160102);
                        cm.dispose();
                    } else {
                        cm.sendOk("由于未知错误，远征队请求被拒绝。");
                        cm.safeDispose();
                    }
                }
                break;
            case 11:
                cm.banMember("ChaosZak", selection);
                cm.dispose();
                break;
            case 12:
                if (selection != -1) {
                    cm.acceptMember("ChaosZak", selection);
                }
                cm.dispose();
                break;
            case 13:
                var emh = cm.getEventManager("ChaosZakum");
                if ((selection == 1) && (emh != null)) {
                    var eim = emh.getInstance("ChaosZakum");
                    if ((eim != null) && (eim.getProperty("isSquadPlayerID_" + cm.getPlayer().getId()) != null)) {
                        eim.registerPlayer(cm.getPlayer());
                    }
                }
                cm.dispose();
                break;
        }
    } else {
        switch (status) {
            case 0:
                if (cm.getPlayer().getLevel() < 50) {
                    cm.sendOk("你的等级未达到50等。");
                    cm.dispose();
                    return;
                }
                if (cm.getPlayer().getClient().getChannel() != 1 && cm.getPlayer().getClient().getChannel() != 2 && cm.getPlayer().getClient().getChannel() != 4) {
                    cm.sendOk("只能在1频或2频或4频打");
                    cm.dispose();
                    return;
                }
                var emz = cm.getEventManager("ZakumBattle");

                if (emz == null) {
                    cm.sendOk("脚本错误，请联繫管理员。");
                    cm.safeDispose();
                    return;
                }
                var prop = emz.getProperty("state");
                var marr = cm.getQuestRecord(160101);
                var data = marr.getCustomData();
                if (data == null) {
                    marr.setCustomData("0");
                    data = "0";
                }
                var time = parseInt(data);
                if (prop == null || prop.equals("0")) {
                    var squadAvailability = cm.getSquadAvailability("ZAK");
                    if (squadAvailability == -1) {
                        status = 1;
                        cm.sendYesNo("你有兴趣成为远征队长吗?");

                    } else if (squadAvailability == 1) {
                        // -1 = Cancelled, 0 = not, 1 = true
                        var type = cm.isSquadLeader("ZAK");
                        if (type == -1) {
                            cm.sendOk("远征队已结束，请重新登记。");
                            cm.safeDispose();
                        } else if (type == 0) {
                            var memberType = cm.isSquadMember("ZAK");
                            if (memberType == 2) {
                                cm.sendOk("你被远征队踢出。");
                                cm.safeDispose();
                            } else if (memberType == 1) {
                                status = 5;
                                cm.sendSimple("你想做什麽? \r\n#b#L0#查看成员#l \r\n#b#L1#加入远征队#l \r\n#b#L2#退出远征队#l");
                            } else if (memberType == -1) {
                                cm.sendOk("远征队已结束，请重新登记。");
                                cm.safeDispose();
                            } else {
                                status = 5;
                                cm.sendSimple("你想做什麽? \r\n#b#L0#查看成员#l \r\n#b#L1#加入远征队#l \r\n#b#L2#退出远征队#l");
                            }
                        } else { // Is leader
                            status = 10;
                            cm.sendSimple("你想做什麽? \r\n#b#L0#查看成员#l \r\n#b#L1#移除成员#l \r\n#b#L2#编辑限制列表#l \r\n#r#L3#进入地图#l");
                            // TODO viewing!
                        }
                    } else {
                        var propssa = emz.getProperty("leader");
                        if (propssa != null && propssa.equals("true")) {
                            var eim = cm.getDisconnected("ZakumBattle");
                            if (eim == null) {
                                cm.sendOk("远征队与扎昆的战斗已经开始。");
                                cm.safeDispose();
                            } else {
                                cm.sendOk("远征队与扎昆的战斗已经开始。");
                                cm.safeDispose();
                            }
                        } else {
                            cm.sendOk("很抱歉你的远征队队长离开了现场，所以你不能再返回战场。");
                            cm.safeDispose();
                        }

                    }
                } else {

                    var propssb = emz.getProperty("leader");
                    if (propssb != null && propssb.equals("true")) {
                        var eima = emz.getInstance("ZakumBattle");
                        var propsa = eima.getProperty("isSquadPlayerID_" + cm.getPlayer().getId());
                        var saya = "\r\n" + (eima == null ? "eima is null" : propsa) + "\r\n";
                        if ((eima != null) && (propsa != null) && propsa.equals("1")) {
                            status = 13;
                            saya += "#b现在是否要重新返回远征队所在场地？";
                            saya += "\r\n#r#L1#重新返回战场#l";
                            cm.sendSimple(saya);
                        } else {
                            eim = cm.getDisconnected("ZakumBattle");
                            if (eim == null) {
                                cm.sendOk("远征队与扎昆的战斗已经开始。" + saya);
                                cm.safeDispose();
                            } else {
                                cm.sendOk("远征队与扎昆的战斗已经开始。" + saya);
                                cm.safeDispose();
                            }
                        }
                    } else {
                        var eimb = emz.getInstance("ZakumBattle");
                        var propsb = eimb.getProperty("isSquadPlayerID_" + cm.getPlayer().getId());
                        var sayb = "\r\n" + (eimb == null ? "eimb is null" : propsb) + "\r\n";
                        if ((eimb != null) && (propsb != null) && propsb.equals("1")) {
                            status = 13;
                            sayb += "#b现在是否要重新返回远征队所在场地？";
                            sayb += "\r\n#r#L1#重新返回远征队所在场地#l";
                            cm.sendSimple(sayb);
                        } else {
                            cm.sendOk("很抱歉你的远征队队长离开了现场，所以你不能再返回战场。");
                            cm.safeDispose();
                        }
                    }
                }
                break;
            case 1:
                if (mode == 1) {
                    if (cm.getPlayer().getBossLogD("残暴扎昆次数") == 5) {
                        cm.sendOk("很抱歉每天只能打5次..");
                        cm.dispose();
                        return;
                    }
                    if (cm.registerSquad("ZAK", 5, " 被任命为远征队长，如果你想参加，请在规定时间内加入远征队。")) {
                        cm.sendOk("你被任命为远征队长，接下来的5分钟你可以等待你的成员加入。");
                        cm.getPlayer().setBossLog("残暴扎昆次数");
                    } else {
                        cm.sendOk("添加远征队时发生错误。");
                    }
                } else {
                    cm.sendOk("如果你想成为远征队长，请和我谈话.");
                }
                cm.safeDispose();
                break;
            case 2:
                if (!cm.reAdd("ZakumBattle", "ZAK")) {
                    cm.sendOk("错误，请稍后再试。");
                }
                cm.safeDispose();
                break;
            case 3:
                if (mode == 1) {
                    var squd = cm.getSquad("ZAK");
                    if (squd != null && !squd.getAllNextPlayer().contains(cm.getPlayer().getName())) {
                        if (cm.getPlayer().getBossLogD("残暴扎昆次数") == 5) {
                            cm.sendOk("很抱歉每天只能打5次..");
                            cm.dispose();
                            return;
                        }
                        squd.setNextPlayer(cm.getPlayer().getName());
                        cm.sendOk("你已经加入了远征队。");
                        cm.getPlayer().setBossLog("残暴扎昆次数");
                    }
                }
                cm.dispose();
                break;
            case 5:
                if (selection == 0) {
                    if (!cm.getSquadList("ZAK", 0)) {
                        cm.sendOk("由于未知错误，你加入远征队的请求被拒绝。");
                        cm.safeDispose();
                    } else {
                        cm.dispose();
                    }
                } else if (selection == 1) { // join
                    var ba = cm.addMember("ZAK", true);
                    if (ba == 2) {
                        cm.sendOk("远征队已满员，请稍后再试。");
                        cm.safeDispose();
                    } else if (ba == 1) {
                        if (cm.getPlayer().getBossLogD("残暴扎昆次数") == 5) {
                            cm.sendOk("很抱歉每天只能打5次..");
                            cm.dispose();
                            return;
                        }
                        cm.getPlayer().setBossLog("残暴扎昆次数");
                        cm.sendOk("你已成功加入远征队。");
                        cm.safeDispose();
                    } else {
                        cm.sendOk("你已加入远征队。");
                        cm.safeDispose();
                    }
                } else {// withdraw
                    var baa = cm.addMember("ZAK", false);
                    if (baa == 1) {
                        cm.sendOk("你已成功从远征队中退出。");
                        cm.safeDispose();
                    } else {
                        cm.sendOk("你不是远征队的一员。");
                        cm.safeDispose();
                    }
                }
                break;
            case 10:
                if (selection == 0) {
                    if (!cm.getSquadList("ZAK", 0)) {
                        cm.sendOk("由于未知错误，远征队请求被拒绝。");
                    }
                    cm.safeDispose();
                } else if (selection == 1) {
                    status = 11;
                    if (!cm.getSquadList("ZAK", 1)) {
                        cm.sendOk("由于未知错误，远征队请求被拒绝。");
                        cm.safeDispose();
                    }

                } else if (selection == 2) {
                    status = 12;
                    if (!cm.getSquadList("ZAK", 2)) {
                        cm.sendOk("由于未知错误，远征队请求被拒绝。");
                        cm.safeDispose();
                    }

                } else if (selection == 3) { // get insode
                    if (cm.getSquad("ZAK") != null) {
                        var dd = cm.getEventManager("ZakumBattle");
                        dd.startInstance(cm.getSquad("ZAK"), cm.getMap(), 160101);
                        cm.dispose();
                    } else {
                        cm.sendOk("由于未知错误，远征队请求被拒绝。");
                        cm.safeDispose();
                    }
                }
                break;
            case 11:
                cm.banMember("ZAK", selection);
                cm.dispose();
                break;
            case 12:
                if (selection != -1) {
                    cm.acceptMember("ZAK", selection);
                }
                cm.dispose();
                break;
            case 13:
                var emg = cm.getEventManager("ZakumBattle");
                if ((selection == 1) && (emg != null)) {
                    var eim = emg.getInstance("ZakumBattle");
                    if ((eim != null) && (eim.getProperty("isSquadPlayerID_" + cm.getPlayer().getId()) != null)) {
                        eim.registerPlayer(cm.getPlayer());
                    }
                }
                cm.dispose();
                break;
        }
    }
}