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
			if(qm.getQuestStatus(4766)==2){
			qm.sendOk("���Ѿ���ȡ������������Ŭ����50�����Ի�ø��ཱ���");
			qm.dispose();
			}else{
			qm.sendNext("��ϲ�㵱ǰ�ȼ��Ѿ�����#b40#k����#k");
			}
		} else if (status == 1) {
			qm.sendOk("��ϲ����ϵͳ������\r\n\r\n#fUI/UIWindow.img/QuestIcon/4/0#\r\n#v1002186##v1072153##v1092064##v1052085##v1082102##v1102039##v1012057##v1022048##v1032024# 1��");
			qm.gainItemPeriod(1002186, 1, 3);
			qm.gainItemPeriod(1072153, 1, 3);
			qm.gainItemPeriod(1092064, 1, 3);
			qm.gainItemPeriod(1052085, 1, 3);
			qm.gainItemPeriod(1082102, 1, 3);
			qm.gainItemPeriod(1102039, 1, 3);
			//qm.gainItemPeriod(1012057, 1, 3);
			qm.gainItemPeriod(1022048, 1, 3);
			qm.gainItemPeriod(1032024, 1, 3);
			qm.forceCompleteQuest(4766);
			qm.dispose();
		} 
	}
}