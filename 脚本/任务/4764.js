var status = -1;

function start(mode, type, selection) {
	if (mode == -1) {
		qm.dispose();
	} else {
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			if(qm.getQuestStatus(4764)==2){
			qm.sendOk("���Ѿ���ȡ������������Ŭ����30�����Ի���½����");
			qm.dispose();
			}else{
			qm.sendNext("��ϲ�㵱ǰ�ȼ��Ѿ�����#b20#k����");
			}
		} else if (status == 1) {
			qm.sendOk("��ϲ����ϵͳ������\r\n\r\n#fUI/UIWindow.img/QuestIcon/4/0#\r\n#v5160000##v5160001##v5160002##v5160003# ��1��");
			qm.gainItemPeriod(5160000, 1, 3);
                        qm.gainItemPeriod(5160001, 1, 3);
                        qm.gainItemPeriod(5160002, 1, 3);
                        qm.gainItemPeriod(5160003, 1, 3);
			qm.forceCompleteQuest(4764);
			qm.dispose();
		} 
	}
}