/* ==================
 �ű�����:  ���︱��	    
 �ű����ߣ� ��һ   
 ��ϵ��ʽ�� 1500663066
 =====================
 */
importPackage(Packages.handling.word);
importPackage(Packages.client.inventory);
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE); //��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE); //��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
var С�̻� ="#fMap/MapHelper/weather/squib/squib4/1#";
var ����è ="#fUI/ChatBalloon/37/n#";
var status = -1;
var fbmc = "����ɭ��-(������)";//��������
var minLevel = 70;
var maxLevel = 250;
var minPartySize = 2;
var maxPartySize = 6;
var cishuxianzhi = 10;//���ƴ���
var ���ֶһ� = 50;//�жϻ��ֶһ�����
var ���ƶһ����� = 999;//�жϻ��ֶһ�����
var maxjinbi = 50000;//�ж���������
var eventname = "Ellin";//���������ļ�
var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 0) {
	    cm.dispose();
	}
	status--;
    }
    if (status == 0) {
	    cm.givePartyItems(4001161, 0, true);
	    cm.givePartyItems(4001162, 0, true);
	    cm.givePartyItems(4001163, 0, true);
	    cm.givePartyItems(4001169, 0, true);
	    cm.givePartyItems(2270004, 0, true);
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
            text += "#k\t\t\t\t#e#d   ���� �� �� ���� #k\r\n\r\""+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"#k\r\n��������Ҫ�����£�\r\n����������:#r " + minPartySize + " #b- #r" + maxPartySize + "#k��Ա\t�ڵȼ����ƣ�#r " + minLevel + " #b- #r" + maxLevel + "�� #k\r\n"
			text += "#k����ר������:#r"+get����("�������")+"#k��   ÿ��ֻ����ս:#b"+ cishuxianzhi +"#k��\r\n�������ѽ���:#b"+ cm.getPlayer().getBossLog("Ellincs") +"#k��#k\r\n\r\n"
            
			text += "#L1##r����ʼ��Ӹ�����#l      \r\n"
			text += "#L77##r���һ�����װ����("+���ֶһ�+"ר������)#v1012146##l\r\n\r\n"
			text += "#L68##r���鿴����������#l      "
			//text += "#L3##r��ս��50����ȡ#v1032060##z1032060##l\r\n\r\n"
			//text += "#L4##r��ս��100����ȡ#v1032061##z1032061##l\r\n\r\n"
			//text += "#L5##r�����뾺�����С�#l      #L6##r������������ȡ��#l "
    cm.sendSimple(text);
    } else if (selection == 68) {//��������
				cm.dispose();
				cm.openNpc(2133000, 68);
        }
	else if (status == 1) {
        if (selection == 1) {
			
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
		   
		   cm.removeAll(4001161);
	       cm.removeAll(4001162);
		   cm.removeAll(4001163);
	       cm.removeAll(4001164);
		    } else {
				cm.sendOk("��ӳ�Ա����ȫ�������");
				cm.dispose();
			}	
	    if (cm.getPlayer().getParty() == null || !cm.isLeader()) {
		cm.sendOk("�����Ķӳ�������̸����");
		cm.dispose();
		}else if(cm.getPartyBosslog("Ellincs",(cishuxianzhi)) == false) {//�ж�����Ƿ�2��
	            cm.sendOk("�����ж�����ս�����Ѿ�����"+cishuxianzhi+"�Σ�");
               cm.dispose();
				return;
			}else if( cm.getPlayer().getBossLog("Ellincs") >= cishuxianzhi) {
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
			if (ccPlayer == null || ccPlayer.getLevel() < minLevel || ccPlayer.getLevel() > maxLevel) {
				next = false;
				break;
			}
			size += (ccPlayer.isGM() ? 4 : 1);
		}	
		if (next && size >= minPartySize) {
			var em = cm.getEventManager("Ellin");
			if (em == null) {
				cm.sendOk("��ǰ����������,���������Ա.");
				cm.dispose();
			} else {
				var prop = em.getProperty("state");
                if (prop.equals("0") || prop == null) {
					em.startInstance(cm.getParty(), cm.getMap());
					cm.setPartyBosslog("Ellincs");//���ŶӴ���
					if (weekday != 7) { //�����������콱�����콱��
						//	cm.getPlayer().Atime(); //���ٿ�ʼ
						} else {
							cm.getPlayer().dropMessage(5, "������һ�춼���콱�ڼ䣬���ܲ��뾺��");
						}
						
						
					cm.dispose();
					return;
				} else {
					cm.sendOk("�����Ѿ�������,�����Ժ��ڽ��뿴��,���߸���Ƶ��");
					cm.dispose();
				}

			}
		} else {
			cm.sendOk("��Ķ���#b��Ա#k��Ҫ#b" +minPartySize+ "��#k���ϵȼ�" + minLevel + "~" + maxLevel + "�Ķ�Ա���ܽ���!");
			cm.dispose();
		}
	    }
		} else if (selection == 5) {
		cm.getPlayer().showtimePLC("ͨ�ض���");
		cm.dispose();
	} else if (selection == 6) {

		if (weekday == 7 && hour <= 23) { //�����������콱�����콱��
			cm.openNpc(9900004, "�������������콱");
		} else {;
			cm.sendOk("������ (0-23)�� �����콱��");
			cm.dispose();
			return;
		}
		} else if (selection == 2){
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
		} else if(cm.getPlayer().getOneTimeLog("Ellin1") >= 1){//�ж����ü�¼
		cm.sendOk("���Ѿ���ȡ����,�޷����ظ���ȡ!");
        cm.dispose();
		} else if(cm.getPlayer().getOneTimeLog("Ellin") < 50){//�ж����ü�¼
		cm.sendOk("�㻹û�гɹ���ս��50��,��ǰ�Ѿ���ս�ɹ���:"+cm.getPlayer().getOneTimeLog("Ellin")+"��!");
        status = -1;
		} else {
        cm.getPlayer().setOneTimeLog("Ellin1");//�����ü�¼
		cm.gainItem(1032060, 1, true);//��Ʒ����,����,�������
		cm.sendOk("��ϲ��,�ɹ�����ȡ��#v1032060##z1032060#!");
        cm.worldMessage(6,"��ϲ��ң�["+cm.getName()+"]�ڶ��︱���гɹ��Ķһ��˰���̩����!");
	    status = -1;
	}}
	else if (selection == 77) {
		if (cm.getInventory(1).isFull(0)){//�жϵ�һ��Ҳ����װ������װ�����Ƿ���һ���ո�
		cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷��һ�.");
		cm.dispose();
		} else if(cm.getPlayer().getOneTimeLog("nvsheng1") >= ���ƶһ�����){//�ж����ü�¼
		cm.sendOk("���Ѿ���ȡ����,�޷����ظ���ȡ!");
        cm.dispose();
		} else if(get����("�������") < ���ֶһ�){//�ж����ü�¼
		cm.sendOk("���︱�����ֲ���"+���ֶһ�+"��,��ǰ����:"+get����("�������")+"��!");
        status = -1;
		} else {
		gain����("�������",-���ֶһ�);
        cm.getPlayer().setOneTimeLog("nvsheng1");//�����ü�¼
		cm.gainItem(1012146, 10, 10, 10, 10, 10, 10, 5, 5, 0, 0, 0, 0, 0, 0);//�������
		cm.sendOk("��ϲ��,�ɹ��Ķһ����︱��ר�����߳ɹ�����챳��!!");
        cm.worldMessage(6,"��ϲ��ң�["+cm.getName()+"]�ɹ�ʹ�ø������ֻ�����(���︱��)ר��װ����");
	    status = -1;
		
					}						
	}
	
	
	else if (selection == 4) {
		if (cm.getInventory(1).isFull(0)){//�жϵ�һ��Ҳ����װ������װ�����Ƿ���һ���ո�
		cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷��һ�.");
		cm.dispose();
		} else if(cm.getPlayer().getOneTimeLog("Ellin2") >= 1){//�ж����ü�¼
		cm.sendOk("���Ѿ���ȡ����,�޷����ظ���ȡ!");
        cm.dispose();
		} else if(cm.getPlayer().getOneTimeLog("Ellin") < 100){//�ж����ü�¼
		cm.sendOk("�㻹û�гɹ���ս��100��,��ǰ�Ѿ���ս�ɹ���:"+cm.getPlayer().getOneTimeLog("Ellin")+"��!");
        status = -1;
		} else {
        cm.getPlayer().setOneTimeLog("Ellin2");//�����ü�¼
		cm.gainItem(1032061, 1, true);//��Ʒ����,����,�������
		cm.sendOk("��ϲ��,�ɹ�����ȡ��#v1032061##z1032061#!");
        cm.worldMessage(6,"��ϲ��ң�["+cm.getName()+"]�ڶ��︱���гɹ��Ķһ��˷���İ���̩����!");
	    status = -1;
	}
	}
	
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