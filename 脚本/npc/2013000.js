/* ==================
 �ű�����:  ��ո���NPC	    
 �ű����ߣ� ����ˡ�����
 ��ϵ��ʽQQ�� 1500663066
 =====================
 */
importPackage(Packages.handling.word);
importPackage(Packages.client.inventory);
var С�̻� ="#fMap/MapHelper/weather/squib/squib4/1#";
var ����è ="#fUI/ChatBalloon/37/n#";
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE); //��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE); //��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
 
var status = 0;
//��������
var fbmc = "ͨ����-(Ů�񸱱�)";
var eventname = "OrbisPQ";//���������ļ�
var maxjinbi = 50000;//�ж���������
var minLevel = 50;
var maxLevel = 250;//�ȼ�����
var cishuxianzhi = 10;//���ƴ���
var minPartySize = 3;
var maxPartySize = 6;//��������
var ���ֶһ� = 30;//�жϻ��ֶһ�����
var ���ƶһ����� = 999;//�жϻ��ֶһ�����


function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("��л��Ĺ��٣�");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {	
			if (cm.getMapId() == 920010000) { 
		cm.sendOk("���Ǳ��������� ��Ҫ20���Ƶ���ƬȻ�󶪵��м�Ĺ⻷֮��,�м�ǧ��Ҫһ��һ����!");
		cm.dispose();
		return;
	}
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
            text += "#k\t\t\t\t"+С�̻�+"#e#d���� �� �� ����"+С�̻�+" #k\r\n\r\""+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"    ��������Ҫ�����£�\r\n����������:#r " + minPartySize + " #b- #r" + maxPartySize + "#k��Ա\t�ڵȼ����ƣ�#r " + minLevel + " #b- #r" + maxLevel + "�� #k\r\n"
			text += "#k����ר������:#r"+get����("��ջ���")+"#k��   ÿ��ֻ����ս:#b"+ cishuxianzhi +"#k�� \r\n������ѽ���:#b"+ cm.getPlayer().getBossLog("nvshengcs") +"#k��#k\r\n"
            text += "#L1##r����ʼ��Ӹ�����#l      \r\n"
			text += "#L3##r���һ�����װ����("+���ֶһ�+"ר������)#v1112915##l\r\n\r\n";
			text += "#L68##r���鿴����������#l      "
			
            cm.sendSimple(text);
        } else if (selection == 68) {//��������
				cm.dispose();
				cm.openNpc(2013000, 68);
        }
		else if (selection == 1) {
	for (var i = 4001044; i < 4001064; i++) {
		cm.removeAll(i); 
	}
	if (cm.getParty() == null) { //�ж���û�����
	    cm.sendSimple("��ò��û�дﵽҪ��...:\r\n\r\n#r��ҳ�ԱҪ��: " + minPartySize + " , ÿ���˵ĵȼ������� " + minLevel + " �� �ȼ� " + maxLevel + "����û�����.");
		cm.dispose();
	} else if (!cm.isLeader()) { //�ж϶ӳ�
	    cm.sendSimple("���������������#b�ӳ�#k ����̸.");
		cm.dispose();
	} else if(cm.getPartyBosslog("nvshengcs",(cishuxianzhi)) == false) {//�ж���Ӵ���
	            cm.sendOk("�����ж�����ս�����Ѿ�����"+ cishuxianzhi +"�Σ�");
                cm.dispose();
				return;
	}else if( cm.getPlayer().getBossLog("nvshengcs") >= cishuxianzhi) {
	            cm.sendOk("����,�޶�ÿ��ֻ����ս"+ cishuxianzhi +"�Σ�");
                cm.dispose();
				return;
	} else {
	    // Check if all ��Ա are within PQ levels
	    var party = cm.getParty().getMembers();
	    var mapId = cm.getMapId();
	    var next = true;
	    var levelValid = 0;
	    var inMap = 0;
	    var it = party.iterator();

	    while (it.hasNext()) {
		var cPlayer = it.next();
		if ((cPlayer.getLevel() >= minLevel) && (cPlayer.getLevel() <= maxLevel)) {
		    levelValid += 1;
		} else {
		    next = false;
		}
		if (cPlayer.getMapid() == mapId) {
		    inMap += (cPlayer.getJobId() == 900 ? 6 : 1);
		}
	    }
	    if (party.size() > maxPartySize || inMap < minPartySize) {
		next = false;
	    }
	    if (next) {
		var em = cm.getEventManager("OrbisPQ");
		if (em == null) {
		    cm.sendSimple("�Ҳ����ű�������GM#b\r\n");
		} else {
		    var prop = em.getProperty("state");
		    if (prop.equals("0") || prop == null) {
			em.startInstance(cm.getParty(), cm.getMap());
			cm.setPartyBosslog("nvshengcs");//���ŶӴ���
			if (weekday != 7) { //�����������콱�����콱��
	//						cm.getPlayer().Atime(); //���ٿ�ʼ
						} else {
							cm.getPlayer().dropMessage(5, "������һ�춼���콱�ڼ䣬���ܲ��뾺��");
						}
						
			cm.dispose();
			return;
		    } else {
			cm.sendSimple("���������Ѿ��������� #r���������#k �볢�Ի�Ƶ�����ߵ�����������ɡ�");
			cm.dispose();
		    }
		}
	    } else {
		cm.sendSimple("��Ķ���ò��û�дﵽҪ��...:\r\n\r\n#rҪ��: " + minPartySize + " ��ҳ�Ա, ÿ���˵ĵȼ������� " + minLevel + " �� �ȼ� " + maxLevel + ".");
	    }
		cm.dispose();
	}
	} else if (selection == 5) {
		cm.getPlayer().showtimePLC("ͨ�����");
		cm.dispose();
	} else if (selection == 6) {

		if (weekday == 7 && hour <= 23) { //�����������콱�����콱��
			cm.openNpc(9900004, "������������콱");
		} else {;
			cm.sendOk("������ (0-23)�� �����콱��");
			cm.dispose();
			return;
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
		} else if(cm.getPlayer().getOneTimeLog("nvsheng1") >= ���ƶһ�����){//�ж����ü�¼
		cm.sendOk("���Ѿ���ȡ����,�޷����ظ���ȡ!");
        cm.dispose();
		} else if(get����("��ջ���") < ���ֶһ�){//�ж����ü�¼
		cm.sendOk("��ո������ֲ���"+���ֶһ�+"��,��ǰ����:"+get����("��ջ���")+"��!");
        status = -1;
		} else {
		gain����("��ջ���",-���ֶһ�);
        cm.getPlayer().setOneTimeLog("nvsheng1");//�����ü�¼
		//cm.gainItem(1112915, 1, true);//��Ʒ����,����,�������
		cm.gainItem(1112915, 8, 8, 8, 8, 0, 0, 8, 8, 0, 0, 0, 0, 0, 0);//��ն���
		cm.sendOk("��ϲ��,�ɹ��Ķһ���ո���ר�����߳ɹ�����챳��!!");
        cm.worldMessage(6,"��ϲ��ң�["+cm.getName()+"]�ɹ�ʹ�ø������ֻ�����(��ո���)ר��װ����");
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
