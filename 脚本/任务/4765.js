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
			if(qm.getQuestStatus(4765)==2){
			qm.sendOk("���Ѿ���ȡ������������Ŭ����40�����Ի�ø��ཱ���");
			qm.dispose();
			}else{
			qm.sendNext("��ϲ�㵱ǰ�ȼ��Ѿ�����#b30#k����");
			}
		} else if (status == 1) {
			qm.sendOk("��ϲ����ϵͳ������\r\n\r\n#fUI/UIWindow.img/QuestIcon/4/0#\r\n#v5160004##v5160005##v5160006##v5160007# 1��");
			qm.gainItemPeriod(5160004, 1, 3);
			qm.gainItemPeriod(5160005, 1, 3);
			qm.gainItemPeriod(5160006, 1, 3);
			qm.gainItemPeriod(5160007, 1, 3);
			qm.forceCompleteQuest(4765);
			qm.dispose();
		} 
	}
}