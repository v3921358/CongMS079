







var status = -1;

function start() {

		if (cm.getPlayer().getLevel() < 120) {
			cm.sendOk("��ĵȼ�С�� 120 ��,�޷���ս.");
			cm.dispose();
			return;
		}
		// if (cm.getPlayer().getClient().getChannel() != 2) {
			// cm.sendOk("Ʒ����ֻ����2Ƶ����ս.");
			// cm.dispose();
			// return;
		// }
		
		/*if (cm.getMap(270050100).getCharactersSize() > 0  ) {
			cm.sendOk("�����Ѿ�������ս��....");
			cm.dispose();
			return;
		}*/		
    var em = cm.getEventManager("PinkBeanBattle");

    if (em == null) {
	cm.sendOk("�����嵥Ϊ�գ�����ϵ����Ա.");
	cm.dispose();
	return;
    }
    var eim_status = em.getProperty("state");
	if (eim_status == null || eim_status.equals("0")) {
    var squadAvailability = cm.getSquadAvailability("PinkBean");
    if (squadAvailability == -1) {
	status = 0;
	cm.sendYesNo("���ڿ�������Զ���ӣ������ΪԶ���Ӷӳ���");

    } else if (squadAvailability == 1) {
	// -1 = Cancelled, 0 = not, 1 = true
	var type = cm.isSquadLeader("PinkBean");
	if (type == -1) {
	    cm.sendOk("�Ѿ�����������.");
	    cm.dispose();
	} else if (type == 0) {
	    var memberType = cm.isSquadMember("PinkBean");
	    if (memberType == 2) {
		cm.sendOk("��Զ���ӵ��Ʋ�����.");
		cm.dispose();
	    } else if (memberType == 1) {
		status = 5;
		cm.sendSimple("����������ʲô��\r\n#b#L2#�鿴Զ���ӳ�Ա��#l \r\n#b#L0#����Զ���ӡ�#l \r\n#b#L1#�˳�Զ���ӡ�#l");
	    } else if (memberType == -1) {
		cm.sendOk("Զ����Ա�Ѿ��ﵽ30�������Ժ����ԡ�");
		cm.dispose();
	    } else {
		status = 5;
		cm.sendSimple("����������ʲô�� \r\n#b#L2#�鿴Զ���ӳ�Ա��#l \r\n#b#L0#����Զ���ӡ�#l \r\n#b#L1#�˳�Զ���ӡ�#l");
	    }
	} else { // Is leader
	    status = 10;
	    cm.sendSimple("����������ʲô��\r\n#b#L0#�鿴Զ���ӳ�Ա��#l \r\n#b#L1#����Զ���ӳ�Ա��#l \r\n#b#L2#�༭�����б�#l \r\n#r#L3#�����ͼ��#l");
	// TODO viewing!
	}
	    } else {
			var eim = cm.getDisconnected("PinkBeanBattle");
			if (eim == null) {
				cm.sendOk("Զ���ӵ���ս�Ѿ���ʼ1��");
				cm.safeDispose();
			} else {
				cm.sendYesNo("��Ҫ��������Զ��������");
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
                        if( cm.getBossLog("Ʒ�˱��ط�") >= 2) {
	                    cm.sendOk("����,p�޶�ÿ��ֻ���ط�2�Σ�");
                        cm.dispose();
				        return;
                        }
                        cm.setBossLog('Ʒ�˱��ط�');//������					
			            cm.gainMeso(-1000000);
                    status = 13;
                    saya += "#b�����Ƿ�Ҫ���·���Զ�������ڳ��أ�";
                    saya += "\r\n#r#L1#���·���ս��#l";
                    cm.sendSimple(saya);
					    }else{
				        cm.sendOk("\t��ǸŶ�����Ľ�Ҳ�������������");
				        cm.dispose();
			            }
						
                }else {
		
			var eim = cm.getDisconnected("PinkBeanBattle");
			if (eim == null) {
				cm.sendOk("Զ���ӵ���ս�Ѿ���ʼ2��");
				cm.safeDispose();
			} else {
				cm.sendYesNo("��Ҫ��������Զ��������");
				status = 2;
			}
				}
				} else {
                var eimb = em.getInstance("PinkBeanBattle");
                var propsb = eimb.getProperty("isSquadPlayerID_" + cm.getPlayer().getId());
                var sayb = "\r\n" + (eimb == null ? "eimb is null" : propsb) + "\r\n";
                if ((eimb != null) && (propsb != null) && propsb.equals("1")) {
                    status = 13;
                    sayb += "#b�F���Ƿ�Ҫ���·����h������ڈ��أ�";
                    sayb += "\r\n#r#L1#���·����h������ڈ���#l";
                    cm.sendSimple(sayb);
                } else {

                    cm.sendOk("�ܱ�Ǹ����h�����L�x�_�ˬF���������㲻���ٷ��ؑ�����");
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
			cm.sendOk("�����Ѿ�������ս��....");
			cm.dispose();
			return;
		}			
			
			if (cm.registerSquad("PinkBean", 5, " �Ѿ���Ϊ��Զ���Ӷӳ���")) {
				cm.sendOk("���Ѿ���Ϊ��Զ���Ӷӳ�����������5���ӣ���ȴ���Ա�ǵ����롣");
			} else {
				cm.sendOk("δ֪����.");
			}
	    }
	    cm.dispose();
	    break;
	case 2:
		if (!cm.reAdd("PinkBeanBattle", "PinkBean")) {
			cm.sendOk("����δ֪�Ĵ��󣬲���ʧ�ܡ�");
		}
		cm.safeDispose();
		break;
	case 5:
	    if (selection == 0) { // join
		var ba = cm.addMember("PinkBean", true);
		if (ba == 2) {
		    cm.sendOk("Զ����Ա�Ѿ��ﵽ30�������Ժ����ԡ�");
		} else if (ba == 1) {
		    cm.sendOk("�������Զ���ӳɹ�����Ⱥ�ӳ�ָʾ��");
		} else {
		    cm.sendOk("���Ѿ��μ���Զ���ӣ���Ⱥ�ӳ�ָʾ��");
		}
	    } else if (selection == 1) {// withdraw
		var baa = cm.addMember("PinkBean", false);
		if (baa == 1) {
		    cm.sendOk("�˳�Զ���ӳɹ���");
		} else {
		    cm.sendOk("��û�вμ�Զ���ӡ�");
		}
	    } else if (selection == 2) {
		if (!cm.getSquadList("PinkBean", 0)) {
		    cm.sendOk("����δ֪�Ĵ��󣬲���ʧ��.");
		}
	    }
	    cm.dispose();
	    break;
	case 10:
	    if (mode == 1) {
		if (selection == 0) {
		    if (!cm.getSquadList("PinkBean", 0)) {
			cm.sendOk("����δ֪�Ĵ��󣬲���ʧ��.");
		    }
		    cm.dispose();
		} else if (selection == 1) {
		    status = 11;
		    if (!cm.getSquadList("PinkBean", 1)) {
			cm.sendOk("����δ֪�Ĵ��󣬲���ʧ��.");
			cm.dispose();
		    }
		} else if (selection == 2) {
		    status = 12;
		    if (!cm.getSquadList("PinkBean", 2)) {
			cm.sendOk("����δ֪�Ĵ��󣬲���ʧ��.");
			cm.dispose();
		    }
		} else if (selection == 3) { // get insode
		    if (cm.getSquad("PinkBean") != null) {
			var dd = cm.getEventManager("PinkBeanBattle");
			dd.startInstance(cm.getSquad("PinkBean"), cm.getMap());
		    } else {
			cm.sendOk("����δ֪�Ĵ��󣬲���ʧ��.");
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

