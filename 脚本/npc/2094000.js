/* ==================
 �ű�����:  ��������NPC	    
 �ű����ߣ� ����   
 ��ϵ��ʽ�� 1500663066
 =====================
 */
var ����è ="#fUI/ChatBalloon/37/n#";
var status = 0;
var fbmc = "�ٲ���-(��������)";//��������
var minLevel = 80;//��͵ȼ�
var maxLevel = 250;//��ߵȼ�
var minPartySize = 2;//��������
var maxPartySize = 6;//�������
var cishuxianzhi = 10;//���ƴ���
var maxjinbi = 50000;//�ж���������
var ���ֶһ� = 50;//�жϻ��ֶһ�����
var ���ƶһ����� = 999;//�жϻ��ֶһ�����
var eventname = "Pirate";//���������ļ�


function checkMap() {
    var map = [925100000, 925100100, 925100200, 925100201, 925100202, 925100300, 925100301, 925100302, 925100400, 925100400, 925100500];
    for(var i = 0 ; i < map.length; i++) {
        if(cm.getPlayerCount(map[i]))
            return false;
    }
    return true;
}

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1)
        status++;
    else {
        cm.dispose();
        return;
    }
    if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text += "#k\t\t\t\t#e#d   ���� �� �� ���� #k\r\n\r\""+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"#k\r\n"
            text += "#k\r\n��������Ҫ�����£�\r\n����������:#r " + minPartySize + " #b- #r" + maxPartySize + "#k��Ա\t�ڵȼ����ƣ�#r " + minLevel + " #b- #r" + maxLevel + "�� #k\r\n"
			text += "#k��������:#r"+get����("��������")+"#k�� ÿ��ֻ����ս:#b"+ cishuxianzhi +"#k�� ������ѽ���:#b"+ cm.getPlayer().getBossLog("haidaocs") +"#k��#k\r\n"
            
			text += "#L1##r����ʼ��Ӹ�����#l      \r\n"
			text += "#L3##r���һ�����װ����("+���ֶһ�+"��������)#v1002571##l\r\n\r\n"
			text += "#L68##r���鿴����������#l      "
			//text += "#L3##r��ս��10����ȡ#v1002571##z1002571##l\r\n\r\n"
            cm.sendSimple(text);
	} else if (selection == 1) {
	
	
	var next = true;
	   var size = 0;
	   var it = cm.getPlayer().getParty().getMembers().iterator();
			while (it.hasNext()) {
				var cPlayer = it.next();
				var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
				if (ccPlayer == null) {
					next = false;
					break;
				}
				size++;
			}
	   if (next && size >= 1) {
		   cm.removeAll(4001117);
	       cm.removeAll(4031437);
	       cm.removeAll(4001120);
	       cm.removeAll(4001121);
	       cm.removeAll(4001260);
	       cm.removeAll(4001122);
		   } else {
				cm.sendOk("��ӳ�Ա����ȫ�������");
				cm.dispose();
			}	
	
    if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
        cm.sendOk("���Ҷӳ������ҡ�");
		cm.dispose();
	}else if(cm.getPartyBosslog("haidaocs",(cishuxianzhi)) == false) {//�ж�����Ƿ�2��
	            cm.sendOk("�����ж�����ս�����Ѿ�����"+ cishuxianzhi +"�Σ�");
                cm.dispose();
				return;
	}else if( cm.getPlayer().getBossLog("haidaocs") >= cishuxianzhi) {
	            cm.sendOk("����,�޶�ÿ��ֻ����ս"+ cishuxianzhi +"�Σ�");
                cm.dispose();
				return;
    } else {
        var party = cm.getPlayer().getParty().getMembers();
        var mapId = cm.getPlayer().getMapId();
        var next = true;
        var size = 0;
        var it = party.iterator();
        while (it.hasNext()) {
            var cPlayer = it.next();
            var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
            if (ccPlayer == null || ccPlayer.getLevel() <  minLevel || ccPlayer.getLevel() > maxLevel) {
                next = false;
                break;
            }
            size += (ccPlayer.isGM() ? 2 : 1);
        }
        if (next && size >= minPartySize) {
            if(checkMap()) {
                var em = cm.getEventManager("Pirate");
                if (em == null) {
                    cm.sendOk("�Ҳ����ű�������ϵGM����");
					cm.dispose();
                } else {
                    em.startInstance(cm.getPlayer().getParty(), cm.getPlayer().getMap());
					cm.setPartyBosslog("haidaocs");//���ŶӴ���
					cm.dispose();
                }
            } else {
                cm.sendOk("Ŀǰ�����ڴ򆪡�");
				cm.dispose();
            }
        }else {
            cm.sendOk("��Ҫ" + minPartySize + "��" + maxPartySize + "���� �ȼ�������" + minLevel+ "��" + maxLevel + "��");
			cm.dispose();
        }
    }
    
	} else if (selection == 2) {
            if (cm.getMeso() >= maxjinbi){//�ж϶��ٽ��
                cm.gainMeso(- maxjinbi );//�۳����ٽ��
				cm.laba(cm.getPlayer().getName() + " [������]" + " : " + "[" + fbmc + "]��Ҫ��ʿһ�����",11);
                cm.dispose();
                }else{
                    cm.sendOk("���ð�ձҲ���" + maxjinbi + "���޷�����������");
                   cm.dispose();
					}
        } else if (selection == 3) {
		if (cm.getInventory(1).isFull(0)){//�жϵ�һ��Ҳ����װ������װ�����Ƿ���һ���ո�
		cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷��һ�.");
		cm.dispose();
		} else if(cm.getPlayer().getOneTimeLog("haidaocs") >= ���ƶһ�����){//�ж����ü�¼
		cm.sendOk("���Ѿ���ȡ����,�޷����ظ���ȡ!");
        cm.dispose();
		} else if(get����("��������") < ���ֶһ�){//�ж����ü�¼
		cm.sendOk("�����������ֲ���"+���ֶһ�+"��,��ǰ����:"+get����("��������")+"��!");
        status = -1;
		}else {
        cm.getPlayer().setOneTimeLog("haidaocs");//�����ü�¼
		gain����("��������",-���ֶһ�);
		cm.gainItem(1002571, 20, 20, 20, 20, 20, 20, 10, 10, 0, 0, 0, 0, 0, 0);//id, ����, ����, ����, ����, hp, mp, �﹥, ħ��, ���, ħ��, �ر�, ����, ��Ծ, �ƶ��ٶ�
		cm.sendOk("��ϲ��,�ɹ�����ȡ��#v1002571##z1002571#!");
        cm.worldMessage(6,"��ϲ��ң�["+cm.getName()+"]�ں��������гɹ��Ķһ��˺�������ñ!");
	    status = -1;
		
					}						
	}else if (selection == 68) {
				cm.dispose();
				cm.openNpc(2094000, 68);			
	}
}
function get����(a) {
	var jf = 0;
		switch (a) {
		case "��������":
			jf = Number(cm.getQuestRecord(844440).getCustomData());
			break;
		case "��߻���":
			jf = Number(cm.getQuestRecord(844441).getCustomData());
			break;
		case "��ջ���":
			jf = Number(cm.getQuestRecord(844442).getCustomData());
			break;
		case "��������":
			jf = Number(cm.getQuestRecord(844443).getCustomData());
			break;
		case "�������":
			jf = Number(cm.getQuestRecord(844444).getCustomData());
			break;
		case "�������":
			jf = Number(cm.getQuestRecord(844445).getCustomData());
			break;
		case "���л���":
			jf = Number(cm.getQuestRecord(844446).getCustomData());
			break;
		case "��������":
			jf = Number(cm.getQuestRecord(844447).getCustomData());
			break;
		}

		return jf;
}

function gain����(a,b) {
	var jf = 0;
		switch (a) {
		case "��������":
			jf = Number(cm.getQuestRecord(844440).getCustomData());
			cm.getQuestRecord(844440).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "��߻���":
			jf = Number(cm.getQuestRecord(844441).getCustomData());
			cm.getQuestRecord(844441).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "��ջ���":
			jf = Number(cm.getQuestRecord(844442).getCustomData());
			cm.getQuestRecord(844442).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "��������":
			jf = Number(cm.getQuestRecord(844443).getCustomData());
			cm.getQuestRecord(844443).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "�������":
			jf = Number(cm.getQuestRecord(844444).getCustomData());
			cm.getQuestRecord(844444).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "�������":
			jf = Number(cm.getQuestRecord(844445).getCustomData());
			cm.getQuestRecord(844445).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "���л���":
			jf = Number(cm.getQuestRecord(844446).getCustomData());
			cm.getQuestRecord(844446).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "��������":
			jf = Number(cm.getQuestRecord(844447).getCustomData());
			cm.getQuestRecord(844447).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		}

}
