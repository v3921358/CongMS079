var head = "#fUI/UIWindow2.img/Quest/quest_info/summary_icon/summary#\r\n";
var icon = "#fUI/UIWindow/Minigame/Common/mark#";
var sl1 = 0;

var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == 1) {
		status++;
	} else {
		if (status >= 0) {
			cm.dispose();
			return;
		}
		status--;
	}
           if (status == 0) {	
		   if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("�����������ռ䲻��1��");
			cm.dispose();
			return;
		}
          if (cm.getPlayer().getPrizeLog("��ߵ�") < 5 ) {		
			cm.getPlayer().setPrizeLog("��ߵ�");	
			cm.gainItem(4001129,1);	
			cm.warp(910000000);
			cm.sendOk("��ս�ɹ���");
			cm.ȫ����ɫ����("[��������] : ��ϲ��� "+cm.getPlayer().getName()+" �ɹ���ȡ��������ͼ��ߵؽ�����")
            cm.dispose();
				
		} else {
            cm.sendOk("һ���˺�ֻ������ȡ��Ρ�");
            cm.dispose();



            }
        }

}
