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
			if(qm.getQuestStatus(4767)==2){
			qm.sendOk("���Ѿ���ȡ������������Ŭ����60�����Ի�ø��ཱ���");
			qm.dispose();
			}else{
			qm.sendNext("��ϲ�㵱ǰ�ȼ��Ѿ�����#b50#k����");
			}
		} else if (status == 1) {
			if (qm.getInventory(1).isFull(1)){//�жϵ��ĸ�Ҳ������������װ�����Ƿ���һ���ո�
			qm.sendOk("#b\t�����ռ䲻��");	
			qm.dispose();			
			
			}else{
			qm.sendOk("��ϲ����ϵͳ������\r\n\r\n#fUI/UIWindow.img/QuestIcon/4/0#\r\n#v1002357# ");
			qm.gainItemPeriod(1002357, 1, 3);
			qm.forceCompleteQuest(4767);
			qm.dispose();
			}
		} 
	}
}