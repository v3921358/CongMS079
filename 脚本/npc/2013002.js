/*
 ZEVMSð�յ�(079)��Ϸ�����
 �ű���Ů��������
 */
var status;
var ���ͨ�� = 5000;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    var em = cm.getEventManager("OrbisPQ");
    if (em == null) {
        cm.dispose();
        return;
    }
    for (var i = 4001044; i < 4001064; i++) {
        cm.removeAll(i);
    }
    switch (cm.getMapId()) {
        case 920010100:
            cm.showEffect(false, "quest/party/clear");
            cm.playSound(false, "Party1/Clear");
            cm.gainExp(33000);
            cm.getPlayer().endPartyQuest(1203);
            cm.warp(920011300);
            cm.dispose();
            break;
        default:
            if (mode == -1) {
                cm.dispose();
            }
            if (mode == 1)
                status++;
            else
                status--;
            if (status == 0) {
                cm.sendNext("��ȷ�������������û�пհ˸�,����������λ��һ��ͺ�,ȷ�϶��п��������ҶԻ�");
            } else if (status == 1) {
                //Ů������������Ʒ�������ڸ��ʸ�����������				
				
                
                cm.���ʸ���Ʒ2(4004000, 1, 80, "����ĸ��");
                cm.���ʸ���Ʒ2(4004001, 1, 80, "�ǻ�ĸ��");
                cm.���ʸ���Ʒ2(4004002, 1, 80, "����ĸ��");
                cm.���ʸ���Ʒ2(4004003, 1, 80, "����ĸ��");
                cm.���ʸ���Ʒ2(4004004, 1, 80, "�ڰ�ˮ��ĸ��");
                cm.���ʸ���Ʒ2(4170006, 1, 100, "��յ�");
                cm.���ʸ���Ʒ2(4000313, 5, 70, "�ƽ��Ҷ");
                cm.���ʸ���Ʒ2(4001126, 5, 70, "��Ҷ");
                cm.���ʸ���Ʒ2(2049100, 1, 50, "�������60%");
                


                //��¼ͨ����Ϣ
				cm.������(cm.getLevel()*���ͨ��);
				gain����("��ջ���",+1);
				cm.getPlayer().setBossLog("��ո���");//������һ����ÿ�����
	            gain����("��������",+1);
				cm.gainzdjf(+1);//����������
                cm.setBossRankCount("Ů����", 1);
                cm.setBossLog("Ů����");
                cm.worldMessage(2, "[����-Ů����] : ��ϲ " + cm.getPlayer().getName() + " �������ո�����");
                
                cm.warp(200080101);
                cm.dispose();
                break;
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