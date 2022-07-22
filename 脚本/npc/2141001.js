







var status = -1;

function start() {

		if (cm.getPlayer().getLevel() < 120) {
			cm.sendOk("你的等级小于 120 级,无法挑战.");
			cm.dispose();
			return;
		}
		// if (cm.getPlayer().getClient().getChannel() != 2) {
			// cm.sendOk("品克缤只能在2频道挑战.");
			// cm.dispose();
			// return;
		// }
		
		/*if (cm.getMap(270050100).getCharactersSize() > 0  ) {
			cm.sendOk("里面已经有人挑战了....");
			cm.dispose();
			return;
		}*/		
    var em = cm.getEventManager("PinkBeanBattle");

    if (em == null) {
	cm.sendOk("配置清单为空，请联系管理员.");
	cm.dispose();
	return;
    }
    var eim_status = em.getProperty("state");
	if (eim_status == null || eim_status.equals("0")) {
    var squadAvailability = cm.getSquadAvailability("PinkBean");
    if (squadAvailability == -1) {
	status = 0;
	cm.sendYesNo("现在可以申请远征队，你想成为远征队队长吗？");

    } else if (squadAvailability == 1) {
	// -1 = Cancelled, 0 = not, 1 = true
	var type = cm.isSquadLeader("PinkBean");
	if (type == -1) {
	    cm.sendOk("已经结束了申请.");
	    cm.dispose();
	} else if (type == 0) {
	    var memberType = cm.isSquadMember("PinkBean");
	    if (memberType == 2) {
		cm.sendOk("在远征队的制裁名单.");
		cm.dispose();
	    } else if (memberType == 1) {
		status = 5;
		cm.sendSimple("你现在想做什么？\r\n#b#L2#查看远征队成员。#l \r\n#b#L0#加入远征队。#l \r\n#b#L1#退出远征队。#l");
	    } else if (memberType == -1) {
		cm.sendOk("远征队员已经达到30名，请稍后再试。");
		cm.dispose();
	    } else {
		status = 5;
		cm.sendSimple("你现在想做什么？ \r\n#b#L2#查看远征队成员。#l \r\n#b#L0#加入远征队。#l \r\n#b#L1#退出远征队。#l");
	    }
	} else { // Is leader
	    status = 10;
	    cm.sendSimple("你现在想做什么？\r\n#b#L0#查看远征队成员。#l \r\n#b#L1#管理远征队成员。#l \r\n#b#L2#编辑限制列表。#l \r\n#r#L3#进入地图。#l");
	// TODO viewing!
	}
	    } else {
			var eim = cm.getDisconnected("PinkBeanBattle");
			if (eim == null) {
				cm.sendOk("远征队的挑战已经开始1。");
				cm.safeDispose();
			} else {
				cm.sendYesNo("你要继续进行远征任务吗？");
				status = 2;
			}
	    }
	} else {
		var propssb = em.getProperty("leader");
            if (propssb != null && propssb.equals("true")) {
                var eima = em.getInstance("PinkBeanBattle");
                var propsa = eima.getProperty("isSquadPlayerID_" + cm.getPlayer().getId());
                var saya = "\r\n" + (eima == null ? "eima is null" : propsa) + "\r\n";
                if ((eima != null) && (propsa != null) && propsa.equals("1")) {
					if (cm.getMeso() >= 10000000 ) {
                        if( cm.getBossLog("品克宾重返") >= 2) {
	                    cm.sendOk("您好,p限定每天只能重返2次！");
                        cm.dispose();
				        return;
                        }
                        cm.setBossLog('品克宾重返');//给次数					
			            cm.gainMeso(-1000000);
                    status = 13;
                    saya += "#b现在是否要重新返回远征队所在场地？";
                    saya += "\r\n#r#L1#重新返回战场#l";
                    cm.sendSimple(saya);
					    }else{
				        cm.sendOk("\t抱歉哦！您的金币不足或次数超出。");
				        cm.dispose();
			            }
						
                }else {
		
			var eim = cm.getDisconnected("PinkBeanBattle");
			if (eim == null) {
				cm.sendOk("远征队的挑战已经开始2。");
				cm.safeDispose();
			} else {
				cm.sendYesNo("你要继续进行远征任务吗？");
				status = 2;
			}
				}
				} else {
                var eimb = em.getInstance("PinkBeanBattle");
                var propsb = eimb.getProperty("isSquadPlayerID_" + cm.getPlayer().getId());
                var sayb = "\r\n" + (eimb == null ? "eimb is null" : propsb) + "\r\n";
                if ((eimb != null) && (propsb != null) && propsb.equals("1")) {
                    status = 13;
                    sayb += "#b現在是否要重新返回遠征隊所在場地？";
                    sayb += "\r\n#r#L1#重新返回遠征隊所在場地#l";
                    cm.sendSimple(sayb);
                } else {

                    cm.sendOk("很抱歉你的遠征隊隊長離開了現場，所以你不能再返回戰場。");
                    cm.safeDispose();
                }
            }
        
	}
}

function action(mode, type, selection) {
    switch (status) {
	case 0:
	    if (mode == 1) {
			
		if (cm.getMap(270050100).getCharactersSize() > 0  ) {
			cm.sendOk("里面已经有人挑战了....");
			cm.dispose();
			return;
		}			
			
			if (cm.registerSquad("PinkBean", 5, " 已经成为了远征队队长。")) {
				cm.sendOk("你已经成为了远征队队长。接下来的5分钟，请等待队员们的申请。");
			} else {
				cm.sendOk("未知错误.");
			}
	    }
	    cm.dispose();
	    break;
	case 2:
		if (!cm.reAdd("PinkBeanBattle", "PinkBean")) {
			cm.sendOk("由于未知的错误，操作失败。");
		}
		cm.safeDispose();
		break;
	case 5:
	    if (selection == 0) { // join
		var ba = cm.addMember("PinkBean", true);
		if (ba == 2) {
		    cm.sendOk("远征队员已经达到30名，请稍后再试。");
		} else if (ba == 1) {
		    cm.sendOk("申请加入远征队成功，请等候队长指示。");
		} else {
		    cm.sendOk("你已经参加了远征队，请等候队长指示。");
		}
	    } else if (selection == 1) {// withdraw
		var baa = cm.addMember("PinkBean", false);
		if (baa == 1) {
		    cm.sendOk("退出远征队成功。");
		} else {
		    cm.sendOk("你没有参加远征队。");
		}
	    } else if (selection == 2) {
		if (!cm.getSquadList("PinkBean", 0)) {
		    cm.sendOk("由于未知的错误，操作失败.");
		}
	    }
	    cm.dispose();
	    break;
	case 10:
	    if (mode == 1) {
		if (selection == 0) {
		    if (!cm.getSquadList("PinkBean", 0)) {
			cm.sendOk("由于未知的错误，操作失败.");
		    }
		    cm.dispose();
		} else if (selection == 1) {
		    status = 11;
		    if (!cm.getSquadList("PinkBean", 1)) {
			cm.sendOk("由于未知的错误，操作失败.");
			cm.dispose();
		    }
		} else if (selection == 2) {
		    status = 12;
		    if (!cm.getSquadList("PinkBean", 2)) {
			cm.sendOk("由于未知的错误，操作失败.");
			cm.dispose();
		    }
		} else if (selection == 3) { // get insode
		    if (cm.getSquad("PinkBean") != null) {
			var dd = cm.getEventManager("PinkBeanBattle");
			dd.startInstance(cm.getSquad("PinkBean"), cm.getMap());
		    } else {
			cm.sendOk("由于未知的错误，操作失败.");
		    }
		    cm.dispose();
		}
	    } else {
		cm.dispose();
	    }
	    break;
	case 11:
	    cm.banMember("PinkBean", selection);
	    cm.dispose();
	    break;
	case 12:
	    if (selection != -1) {
		cm.acceptMember("PinkBean", selection);
	    }
	    cm.dispose();
	    break;
	case 13:
                var em = cm.getEventManager("PinkBeanBattle");
                if ((selection == 1) && (em != null)) {
                    var eim = em.getInstance("PinkBeanBattle");
                    if ((eim != null) && (eim.getProperty("isSquadPlayerID_" + cm.getPlayer().getId()) != null)) {
                        eim.registerPlayer(cm.getPlayer());
                    }
                }
                cm.dispose();
                break;	
    }
}

