var head = "#fUI/UIWindow2.img/Quest/quest_info/summary_icon/summary#\r\n";
var icon = "#fUI/UIWindow/Minigame/Common/mark#";
var ���ͼ�� = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var sl1 = 0;//�һ�����

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

			//var text = "#h0#e#d ��ӭ����" + cm.getServerName() + "#k,�ȴ���˽�һ�±���������ɫ\r\n";
			    var text = "#r���������ܣ�ȷ�ϵ���һҳ������ȡ#k#n\r\n#r�ȼ�����50�����Ϳ�����ȡ��#k#n\r\n";
				
				
				text += ""+ ���ͼ�� +" ���10000#k#n\r\n\r\n";
				
			cm.sendSimple(text);
		cm.sendNextS(text, 1);
		} else if (status == 1) {

		if (cm.getPlayer().getPrizeLog("������1") < 1 && cm.getPlayerStat("LVL") > 49) {
			
			cm.getPlayer().setPrizeLog("������1");	
            cm.getPlayer().modifyCSPoints(1,10000, true);//��ȯ			
			cm.sendOk("��ȡ�ɹ���");
			cm.ȫ����ɫ����("[��������] : ��ϲ��� "+cm.getPlayer().getName()+" �ɹ���ȡ�˵��������")
            cm.dispose();
				
		} else {
            cm.sendOk("���ĵȼ���û����50����һ���˺�ֻ������ȡһ�Ρ�");
            cm.dispose();



            }
        }

}
