/* ==================
 �ű�����:  ��߸���	    
 �ű����ߣ� ���� 
 ��ϵ��ʽ�� 1500663066
 =====================
 */
var status;

var itemList = new Array(1032055, 1032056, 1032057, 1032058, 1052165, 1052166, 1052167, 1002797, 1002800, 1072366, 1072368, 1072262, 1072264, 1082244, 1082245, 1102174, 1322003, //equips
		2100000, 2100017, 2100018, 2100019, 2100037, 2100038, 2100039, 2100040, 2044901, 2044902, 2044802, 2044801, 2044702, 2044701, 2044602,
		2044601, 2044501, 2044502, 2044402, 2044401, 2044302, 2044301, 2044201, 2044202, 2044102, 2044101,
		2044002, 2044001, 2043802, 2043801, 2043702, 2043701, 2043302, 2043301, 2043202, 2043201, 2043102,
		2043101, 2043002, 2043001, 2040801, 2040814, 2040815, 2040816, 2040817, 2040802, 2040915, 2040914, 2040805, 2040804, 2040532, 2040534, 2040517, 2040516,
		2040514, 2040513, 2040502, 2040501, 2040323, 2040321, 2040317, 2040316, 2040302, 2040301, //1x use items
		2000002, 2000003, 2000004, 2000005, 2000006, 2000006, 2000006, 2000006, 2000006, 2000005, 2000005,
		2000005, 2000005, 2000002, 2000002, 2000002, 2000002, 2000003, 2000003, 2000003, 2000004, 2000004,
		2000004, 2000004, 2022003, 2070004, 2070005, //multiuse items

		4020000, 4020000, 4020001, 4020001, 4020002, 4020002, 4020003, 4020003, 4020004, 4020004, 4020005,
		4020005, 4020006, 4020006, 4010000, 4010000, 4010001, 4010001, 4010002, 4010002, 4010003, 4010003,
		4010004, 4010004, 4010005, 4010005, 4010006, 4020007, 4020008, 4003000); //etc itemsitems

var randNum = Math.floor(Math.random() * (itemList.length));
var randItem = itemList[randNum];
var qty;

switch (randItem) {
case 4020000:
case 4020001:
case 4020002:
case 4020003:
case 4020004:
case 4020005:
case 4020006:
case 4010000:
case 4010001:
case 4010002:
case 4010003:
case 4010004:
case 4010005:
	qty = 16;
	break;
case 4010006:
case 4020007:
case 4020008:
	qty = 8;
	break;
case 4003000:
	qty = 30;
	break;
case 2000002:
case 2000006:
	qty = 100;
	break;
case 2000003:
	qty = 200;
	break;
case 2000004:
	qty = 50;
	break;
case 2000005:
case 2022003:
	qty = 10;
	break;
default:
	qty = 1;
}

function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	}
	if (mode == 1)
		status++;
	else
		status--;
	if (status == 0) {
		cm.sendNext("��ȷ����ı�����û������");
	} else if (status == 1) {
		if (cm.getInventory(1).isFull(0)) { //�жϵ�һ��Ҳ����װ������װ�����Ƿ���һ���ո�
			cm.sendOk("#b�뱣֤װ����λ������1���ո�,�����޷���ȡ.");
			cm.dispose();
		} else if (cm.getInventory(2).isFull(0)) { //�жϵڶ���Ҳ������������װ�����Ƿ���һ���ո�
			cm.sendOk("#b�뱣֤������λ������1���ո�,�����޷���ȡ.");
			cm.dispose();
		} else if (cm.getInventory(3).isFull(0)) { //�жϵ�����Ҳ������������װ�����Ƿ���һ���ո�
			cm.sendOk("#b�뱣֤������λ������1���ո�,�����޷���ȡ.");
			cm.dispose();
		} else if (cm.getInventory(4).isFull(0)) { //�жϵ��ĸ�Ҳ������������װ�����Ƿ���һ���ո�
			cm.sendOk("#b�뱣֤������λ������1���ո�,�����޷���ȡ.");
			cm.dispose();
		} else if (cm.getInventory(5).isFull(0)) { //�жϵ����Ҳ�����ֽ�����װ�����Ƿ���һ���ո�
			cm.sendOk("#b�뱣֤�ֽ���λ������1���ո�,�����޷���ȡ.");
			cm.dispose();
		} else {

			cm.removeAll(4001022);
			cm.removeAll(4001023);
			cm.getPlayer().endPartyQuest(1202);
			//cm.setPartyBosslog("��߸���");//���Ŷ�ÿ�ո�������
			cm.getPlayer().setBossLog("��߸���");//�����˸�������
			cm.getPlayer().setOneTimeLog("wanju"); //�����ü�¼
			��������(45);
			cm.gainItem(randItem, qty);
			cm.warp(221024500);
			gain����("��߻���",+1);
	        gain����("��������",+1);
			if (cm.isLeader()) {
				cm.worldMessage(6, "��ϲ��ң�[" + cm.getName() + "]������/���Ķ���ͨ������߸���!");
				cm.dispose();
			} else {
				cm.worldMessage(6, "��ϲ��ң�[" + cm.getName() + "]����/���Ķ���ͨ������߸���!");
				cm.dispose();
			}
		}
	}
}

function ��������(a) { //�������4001094
    cm.gainItem(4010003, 1);	//����Ʒ ����
	cm.gainItem(4010004, 1);
	cm.gainItem(4010005, 1);
	cm.gainItem(4010006, 1);
	cm.gainItem(4020000, 1);
	cm.gainItem(4000313, 3);
	cm.gainItem(4001126, 2);
	cm.gainItem(4170005, 1);
	if (cm.getLevel() >= a) { //�жϵȼ�����45
	//	cm.gainDJ(88); //����ȯ�ڶ��ֺ���
		cm.gainExp(100000); //���������˼
		cm.gainItem(4000463, 1);
		cm.gainMeso(50000);//���5w
		cm.worldMessage(6, "��ϲ��ң�[" + cm.getName() + "]����/���Ķ�������߸������Ľ���!");
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
		case "���»���":
			jf = Number(cm.getQuestRecord(844450).getCustomData());
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
		case "���»���":
			jf = Number(cm.getQuestRecord(844450).getCustomData());
			cm.getQuestRecord(844450).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		}

}


