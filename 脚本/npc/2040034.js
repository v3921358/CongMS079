/* ==================
 �ű�����:  ��߸���	    
 �ű����ߣ� ���� 
 ��ϵ��ʽ�� 1500663066
 =====================
 */
importPackage(java.lang);
importPackage(Packages.tools);
importPackage(Packages.client);
importPackage(Packages.server);
importPackage(Packages.tools.packet);
	
importPackage(Packages.handling.word);
importPackage(Packages.client.inventory);
var ca = java.util.Calendar.getInstance();
var �ʹڰ� ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE); //��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE); //��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);

var status = 0;
var fbmc = "��߳�-(�����101����)";//��������
var eventname = "LudiPQ";//���������ļ�
var maxjinbi = 100000;//�ж���������
var minLevel = 35;//��͵ȼ�
var maxLevel = 250;//��ߵȼ�
var cishuxianzhi = 10;//���ƴ���
var minPartySize = 3;//�������
var maxPartySize = 6;//�������
var ���ֶһ� = 30;//�жϻ��ֶһ�����
var ���ƶһ����� = 999;//�жϻ��ֶһ�����
var open = true;//false true//��������
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
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
            text += "#k\t\t\t\t"+�ʹڰ�+" #r#e#w�� �� �� ��#n#k "+�ʹڰ�+"#k\r\n\r\n��������Ҫ�����£�\r\n����������:#r " + minPartySize + " #b- #r" + maxPartySize + "#k��Ա\t�ڵȼ����ƣ�#r " + minLevel + " #b- #r" + maxLevel + "�� #k\r\n"
			text += "#k����ר������:#r"+get����("��߻���")+"#k��   ÿ��ֻ����ս:#b"+ cishuxianzhi +"#k�� ������ѽ���:#b"+ cm.getPlayer().getBossLog("wanjucs") +"#k��#k\r\n\r\n"
            text += "#L1##r����ʼ��Ӹ�����#l      \r\n"
			text += "#L3##r���һ�����װ����("+���ֶһ�+"ר������)#v1022073##l\r\n\r\n"
			text += "#L68##r���鿴����������#l   "
            cm.sendSimple(text);
        } else if (selection == 1) {
	   cm.removeAll(4001022);
	   cm.removeAll(4001023);
	   
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
		
       if (!cm.isLeader()) { //�ж϶ӳ�
	   cm.sendSimple("��������������� #b�ӳ�#k ����̸."); 
	   cm.dispose();
	} else if (cm.getParty() == null) { //�ж���û�����
	   cm.sendSimple("��ò��û�дﵽҪ��...:\r\n\r\n#r��ҳ�Ա����Ҫ��: " + minPartySize + " , ÿ���˵ĵȼ������� " + minLevel + " �� �ȼ� " + maxLevel + ".");
	   cm.dispose();
	}else if(cm.getPartyBosslog("wanjucs",(cishuxianzhi)) == false) {//�ж�����Ƿ�2��
	            cm.sendOk("�����ж�����ս�����Ѿ�����"+ cishuxianzhi +"�Σ�");
                cm.dispose();
				return;
	}else if( cm.getPlayer().getBossLog("wanjucs") >= cishuxianzhi) {
	            cm.sendOk("����,�޶�ÿ��ֻ����ս"+ cishuxianzhi +"�Σ�");
                cm.dispose();
				return;
	}else {

	    var party = cm.getParty().getMembers();//��ȡ��Ա
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
		var em = cm.getEventManager("LudiPQ");
		if (em == null) {
		    cm.sendSimple("�Ҳ����ű�������GM#b\r\n");//#L0#��Ҫ�һ����Ѻ۵��۾�#l
		} else {
		    var prop = em.getProperty("state");
		    if (prop.equals("0") || prop == null) {
			em.startInstance(cm.getParty(), cm.getMap());
		cm.getMap(922010401).resetFully();
		cm.getMap(922010402).resetFully();
		cm.getMap(922010403).resetFully();
		cm.getMap(922010404).resetFully();
		cm.getMap(922010405).resetFully();
		cm.setPartyBosslog("wanjucs");//���ŶӴ���
			cm.removeAll(4001022);
			cm.removeAll(4001023);
			if (weekday != 7) { //�����������콱�����콱��
						//	cm.getPlayer().Atime(); //���ٿ�ʼ
						} else {
							cm.getPlayer().dropMessage(5, "������һ�춼���콱�ڼ䣬���ܲ��뾺��");
						}
			cm.dispose();
			return;
		    } else {
			cm.sendSimple("���������Ѿ��������� #r���������#k �볢�Ի�Ƶ�����ߵ�����������ɡ�");//#b\r\n#L0#��Ҫ�һ����Ѻ۵��۾�#l
		    cm.dispose();
			}
		}
	    } else {
		cm.sendSimple("��Ķ���ò��û�дﵽҪ��...:\r\n\r\n#rҪ������: " + minPartySize + " ��ҳ�Ա, ÿ���˵ĵȼ������� " + minLevel + " �� �ȼ� " + maxLevel + ".");//#b\r\n#L0#��Ҫ�һ����Ѻ۵��۾�#l
		cm.dispose();
	    }
	cm.dispose();
    }	
       } else {
				cm.sendOk("��ӳ�Ա����ȫ�������");
				cm.dispose();
			}	
	} else if (selection == 5) {
		cm.getPlayer().showtimePLC("ͨ�����");
		cm.dispose();
	}else if (selection == 68) {//��������
				cm.dispose();
				cm.openNpc(2040034, 68);
        }
	else if (selection == 6) {

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
        } else if (selection == 3) {//��߻���
		if (cm.getInventory(1).isFull(0)){//�жϵ�һ��Ҳ����װ������װ�����Ƿ���һ���ո�
		cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷��һ�.");
		cm.dispose();
		} else if(cm.getPlayer().getOneTimeLog("wanju1") >= ���ƶһ�����){//�ж����ü�¼
		cm.sendOk("���Ѿ���ȡ����,�޷����ظ���ȡ!");
        cm.dispose();
		} else if(get����("��߻���") < ���ֶһ�){//�ж����ü�¼
		cm.sendOk("��߸������ֲ���"+���ֶһ�+"��,��ǰ����:"+get����("��߻���")+"��!");
        status = -1;
		} else {
		gain����("��߻���",-���ֶһ�);
        cm.getPlayer().setOneTimeLog("wanju1");//�����ü�¼
		cm.gainItem(1022073, 7, 7, 7, 7, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0);//����۾�
		cm.sendOk("�һ���߸���ר�����߳ɹ�����챳��!");
        cm.worldMessage(6,"��ϲ��ң�["+cm.getName()+"]�ɹ�ʹ�ø������ֻ�����(��߸���)ר��װ����");
	    status = -1;
	}
    }
	}}


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


