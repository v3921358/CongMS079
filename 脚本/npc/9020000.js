var FY0 = "��                      ��";
var FY1 = "��       - ��Ҷ -       ��";
var FY2 = "�� �ű�����  �����ƽű� ��";
var FY3 = "�� ����֧�� �� ��Ϸ���� ��";
var FY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var FY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var FY6 = "��       �ٶ��ƹ�       ��";
var FY7 = "�� ΨһQQ:1848350048    ��";
var FY8 = "��                      ��";
importPackage(Packages.handling.word);
importPackage(Packages.client.inventory);
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //������
var month = ca.get(java.util.Calendar.MONTH) + 1; //����·�
var day = ca.get(java.util.Calendar.DATE); //��ȡ��
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //���Сʱ
var minute = ca.get(java.util.Calendar.MINUTE); //��÷���
var second = ca.get(java.util.Calendar.SECOND); //�����
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);

var status;
var fbmc = "��������-(��������)";//��������
var minLevel = 15;//��͵ȼ�
var maxLevel = 255;//��ߵȼ�
var minPartySize = 3;//�������
var maxPartySize = 6;//�������
var cishuxianzhi = 15;//���ƴ���
var maxjinbi = 50000;//�ж���������
var ���ֶһ� = 38;//�жϻ��ֶһ�����
var ���ƶһ����� = 999;//�жϻ��ֶһ�����
var eventname = "KerningPQ";//���������ļ�

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
			//��ʾ��ƷIDͼƬ�õĴ�����  #v����д��ID#
			
			
            text += "#k\t\t\t"+�ʺ�+"#e#d�� �� �� �� #k#n  #r"+�ʺ�+" #k\r\n\r\n��������Ҫ�����£�\r\n����������:#r " + minPartySize + " #b- #r" + maxPartySize + "#k��Ա\t�ڵȼ����ƣ�#r " + minLevel + " #b- #r" + maxLevel + "�� #k\r\n"
			text += "#k����ר������:#r"+get����("��������")+"#k��   ÿ��ֻ����ս:#b"+ cishuxianzhi +"#k�� ������ѽ���:#b"+ cm.getPlayer().getBossLog("feiqics") +"#k��#k\r\n\r\n"
            text += "#L1##r����ʼ��Ӹ�����#l      \r\n"
			text += "#L3##r���һ�����װ����("+���ֶһ�+"��������)#v1112793##l\r\n\r\n"
			text += "#L68##r���鿴����������#l      "
			//text += "#L5##r�����뾺�����С�#l      #L6##r������������ȡ��#l "
            cm.sendSimple(text);
	} else if (selection == 68) {//��������
				cm.dispose();
				cm.openNpc(9020000, 68);
        }
	
	else if (selection == 1) {
        if (cm.getParty() == null) {
            cm.sendOk("��û�ж����޷����룡");
            cm.dispose();
			return;
        } else if (!cm.isLeader()) { 
            cm.sendOk("������Ķӳ�����˵��~");
            cm.dispose();
			return;
        } else {
            var party = cm.getParty().getMembers();
            var inMap = cm.partyMembersInMap();
            var levelValid = 0;
            for (var i = 0; i < party.size(); i++) {
                if (party.get(i).getLevel() >= minLevel && party.get(i).getLevel() <= maxLevel)
                    levelValid++;
            }
            if (inMap < minPartySize || inMap > maxPartySize) {
                cm.sendOk("��Ķ�����������"+minPartySize+"��.�����Ķ�����Ա�ټ������������ڽ��븱��.");
                cm.dispose();
				return;
            } else if (levelValid != inMap) {
                cm.sendOk("��ȷ����Ķ�����������Ա���ڱ���ͼ������С�ȼ��� "+minLevel+" �� "+maxLevel+"֮��.");
                cm.dispose();
				return;
			}else if(cm.getPartyBosslog("feiqics",(cishuxianzhi)) == false) {//�ж�����Ƿ�2��
	            cm.sendOk("�����ж�����ս�����Ѿ�����"+ cishuxianzhi +"�Σ�");
                cm.dispose();
				return;
			}else if( cm.getPlayer().getBossLog("feiqics") >= cishuxianzhi) {
	            cm.sendOk("����,�޶�ÿ��ֻ����ս"+ cishuxianzhi +"�Σ�");
                cm.dispose();
				return;
            } else {
                var em = cm.getEventManager("KerningPQ");
                if (em == null) {
                    cm.sendOk("��̨�����ǵ�ǰ������.");
                //} else if (em.getProperty("KPQOpen").equals("true")) {
                } else {
        if (cm.getPlayerCount(103000800) <= 0 && cm.getPlayerCount(103000801) <= 0 && cm.getPlayerCount(103000802) <= 0 && cm.getPlayerCount(103000803) <= 0 && cm.getPlayerCount(103000804) <= 0) {
		/*var papuMap = cm.getMap(103000804);
         cm.getMap(103000804).resetFully();
        cm.spawnMobOnMap(9300002,1,297,-2188,103000804);
        cm.spawnMobOnMap(9300002,1,433,-2192,103000804);
        cm.spawnMobOnMap(9300002,1,132,-2193,103000804);
		cm.spawnMobOnMap(9300000,1,-18,-1480,103000804);
		cm.spawnMobOnMap(9300000,1,80,-1486,103000804);
		cm.spawnMobOnMap(9300000,1,391,-1488,103000804);
		cm.spawnMobOnMap(9300000,1,247,-1485,103000804);
		cm.spawnMobOnMap(9300000,1,-111,-1475,103000804);
		cm.spawnMobOnMap(9300000,1,299,-1485,103000804);
		cm.spawnMobOnMap(9300003,1,162,-451,103000804);
        //var papuMap = pi.getMap(103000804);
		//pi.getPlayer().setbosslog(1);
        //pi.playPortalSE();
*/
			//}
				//em.startInstance(cm.getPlayer().getParty(), cm.getPlayer().getMap());
                em.startInstance(cm.getParty(), cm.getPlayer().getMap());
				cm.setPartyBosslog("feiqics");//���ŶӴ���
				if (weekday != 7) { //�����������콱�����콱��
						//	cm.getPlayer().Atime(); //���ٿ�ʼ
						} else {
							cm.getPlayer().dropMessage(5, "������һ�춼���콱�ڼ䣬���ܲ��뾺��");
						}
				
				
				
		} else {
                            cm.sendOk("���Ե�...�������ڽ�����.");
                        }

                }
                cm.dispose();
            }
        }
		} else if (selection == 5) {
		cm.getPlayer().showtimePLC("ͨ�ط���");
		cm.dispose();
	} else if (selection == 6) {

		if (weekday == 7 && hour <= 23) { //�����������콱�����콱��
			cm.openNpc(9900004, "�������������콱");
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
		} else if(cm.getPlayer().getOneTimeLog("feiqi1") >= ���ƶһ�����){//�ж����ü�¼
		cm.sendOk("���Ѿ���ȡ����,�޷����ظ���ȡ!");
        cm.dispose();
		} else if(get����("��������") < ���ֶһ�){//�ж����ü�¼
		cm.sendOk("�����������ֲ���"+���ֶһ�+"��,��ǰ����:"+get����("��������")+"��!");
        status = -1;
		} else {
		gain����("��������",-���ֶһ�);
        cm.getPlayer().setOneTimeLog("feiqi1");//�����ü�¼
		cm.gainItem(1112793, 1, true);//��Ʒ����,����,�������
		//cm.gainItem(1112793, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);//���ֽ�ָ
		cm.sendOk("�һ���������ר�����߳ɹ�����챳��!");
        cm.worldMessage(6,"��ϲ��ң�["+cm.getName()+"]�ɹ�ʹ�ø������ֻ�����(��������)ר��װ����!");
	    status = -1;
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