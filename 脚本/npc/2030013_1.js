/*
 NPC Name: 		Adobis
 Map(s): 		El Nath : Entrance to Zakum Altar
 Description: 		Zakum battle starter
 */
var status = 0;

function action(mode, type, selection) {
 if (cm.getPlayer().getMapId() == 211042401) {
        
    } else {
        switch (status) {
            case 0:
                if (cm.getPlayer().getLevel() < 50) {
                    cm.sendOk("你的等级未达到50等。");
                    cm.dispose();
                    return;
                }
                if (cm.getPlayer().getClient().getChannel() != 1 && cm.getPlayer().getClient().getChannel() != 2 && cm.getPlayer().getClient().getChannel() != 4) {
                    cm.sendOk("想要挑战扎昆的话，可以去1频或2频或4频");
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
                        cm.sendOk("你的远征队不在当前频道，或已经挑战结束了，不可以重返战场");
                            cm.safeDispose();

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
						if (cm.getMeso() >= 10000000 ) {
                        if( cm.getBossLog("轧昆重返") >= 2) {
	                    cm.sendOk("您好,轧昆限定每天只能重返2次！");
                        cm.dispose();
				        return;
                        }
                        cm.setBossLog('轧昆重返');//给次数					
			            cm.gainMeso(-1000000);
				        status = 13;
                        saya += "#b现在是否要重新返回远征队所在场地？";//轧昆重返
                        saya += "\r\n#r#L1#重新返回战场#l";
                        cm.sendSimple(saya);
			            }else{
				        cm.sendOk("\t抱歉哦！您的金币不足或次数超出。");
				        cm.dispose();
			            }
                            
                        } else {
                            eim = cm.getDisconnected("ZakumBattle");
                            if (eim == null) {
                                cm.sendOk("其他远征队正在挑战扎昆中。。。。" );
                                cm.safeDispose();
                            } else {
                                cm.sendOk("远征队与扎昆的战斗已经开始。" );
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