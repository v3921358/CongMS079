/*
ð�յ�(079)��Ϸ�����
 �ű�������ɭ��
 */

var ����ͨ�ؾ��� = 8000;
var ���� = 1;



function start() {
    status = -1;
    action(1, 0, 0)
}

function action(mode, type, selection) {
    if (status <= 0 && mode <= 0) {
        cm.dispose();
        return
    }
    if (mode == 1) {
        status++
    } else {
        status--
    }
    if (status <= 0) {
        var selStr = "\r\n";
        if (cm.getMapId() == 930000000) {			
            selStr += "\t�������ȥ����ʼð�հɣ�\r\n";
        }
        if (cm.getMapId() == 930000100) {
            selStr += "\t�Ͷ��Ѻ�������յ�ǰ��ͼ���Ｔ��ͨ�ء�\r\n";
        }
        if (cm.getMapId() == 930000200) {
            selStr += "\t�Ѹ�Ⱦ�Ĺ������������ϻ�ɱ������һ�°ѡ�\r\n";
        }

        if (cm.getMapId() == 930000300) {
            selStr += "\r\n\r\n   #L2##b���ƹ����ˣ�#l\r\n";
        }
        if (cm.getMapId() == 930000600) {
            selStr += "\t����#b#v4001163##t4001163##k���ұ�ʯ���ٻ����׶����ˣ�����սʤ���ɡ�\r\n";
        }

        if (cm.getMapId() == 930000700) {
            selStr += "\r\n\r\n   #L3##b��ʿ����������ͨ����#l\r\n";	
			
        }


        if (cm.getMapId() != 930000700) {
            selStr += "\r\n\r\n   #L1##b�˳�����#l\r\n";

        }
        cm.sendSimple(selStr)
    } else if (status == 1) {
	var next = true;
	var size = 0;
	var it = cm.getPlayer().getParty().getMembers().iterator();
			
        switch (selection) {
            case 1:
                cm.warp(300030100, 0);
                cm.dispose();
                break
            case 2:
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
				cm.warpParty(930000500);
                cm.dispose();
				} else {
				cm.sendOk("�����Ա����û�����");
				cm.dispose();
			    }	
                break
            case 3:
                //����ʢ����Ʊ����
                cm.���ʸ���Ʒ2(4020001, 2, 60, "��ˮ��ĸ��");
                cm.���ʸ���Ʒ2(4020002, 2, 60, "����ʯĸ��");
                cm.���ʸ���Ʒ2(4020003, 2, 60, "��ĸ��ĸ��");
                cm.���ʸ���Ʒ2(4020004, 2, 60, "����ʯĸ��");
				cm.���ʸ���Ʒ2(4020005, 2, 60, "����ʯĸ��");
				cm.���ʸ���Ʒ2(4020006, 2, 60, "�ƾ�ĸ��");
				cm.���ʸ���Ʒ2(4020007, 2, 60, "��ʯĸ��");
				cm.���ʸ���Ʒ2(4020008, 2, 60, "��ˮ��ĸ��");
				cm.���ʸ���Ʒ2(4032391, 5, 60, "����ľ�����Ƭ1");
				cm.���ʸ���Ʒ2(4032392, 5, 60, "����ľ�����Ƭ2");

                //��¼ͨ����Ϣ
				cm.gainzdjf(����);//����������
                cm.gainExp(����ͨ�ؾ���);
				gain����("�������",+1);
	            gain����("��������",+1);
				cm.getPlayer().setBossLog("���︱��");//��һ��������
                cm.getPlayer().endPartyQuest(1206);
                cm.removeAll(4001161);
                cm.removeAll(4001162);
                cm.removeAll(4001163);
                cm.removeAll(4001164);
                cm.removeAll(4001169);
                cm.removeAll(2270004);
                cm.warp(930000800, 0);
                cm.setBossRankCount("����ɭ��", 1);
                cm.setBossLog("����ɭ��");
                cm.worldMessage(2, "[����-����ɭ��] : ��ϲ " + cm.getPlayer().getName() + " ��ɶ���ɭ�ָ�����");
                break
				cm.dispose();

        }
		cm.dispose();
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