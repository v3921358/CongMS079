var CY0 = "�ǩ��������������������������� ����������������������������������";
var CY1 = "��       - ���� -       ��";
var CY2 = "�� �淨����  �����ƽű� ��";
var CY3 = "�� ����֧�� �� ��Ϸ���� ��";
var CY4 = "�� �ף���ӡ�  ��ͼ���� ��";
var CY5 = "�� �Ӷܷ�����  �۵�½�� ��";
var CY7 = "�� ���ο���    ���ο��� ��";
var CY8 = "�ǩ��������������������������� ����������������������������������";
var CY9 = "��   ΨһQQ:3066318387  ��";
var CY0 = "�ǩ��������������������������� ����������������������������������";
var head = "#fUI/UIWindow2.img/Quest/quest_info/summary_icon/summary#\r\n";
var icon = "#fUI/UIWindow/Minigame/Common/mark#";
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
          /*if (status == 0) {
                 var text = "";
			    var text = "\t\t"+�ʺ�+"  #e#d �� �� �� �� #k#n  #r  "+�ʺ�+"#b#k#n\r\r\n"+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+����è+"\r\n#r��ϲ�������������Ʒ��ȷ�ϵ���һҳ������ȡ#k#n\r\n";
				text += "#v1332066#  ��ά����+2   #v1142358#  ��ά����+1\r\n";
				text += "#v1012071#  ��ά����+10   #v1112400#  ��ά����+10\r\n";
				text += "#v1112171#   #v5000066#   #v5030000#   #v5150040#   \r\n";
				text += "#v2120000#10�� #v4000463#10��  #v4000313#10��  #v2001000#50��#k#n\r\n";
				text += ""+ ���ͼ�� +"���:100�� ���100��#k#n\r\n";
			cm.sendSimple(text);
		cm.sendNextS(text, 1);
		} else*/ if (status == 0) {

		//if (cm.getPlayer().getPrizeLog("���ּݵ�") < 1 && cm.getPlayerStat("LVL") > 9) {	
          if (cm.getPlayer().getPrizeLog("���ּݵ�") < 1 ) {		
			cm.getPlayer().setPrizeLog("���ּݵ�");	
			cm.gainItem(2001000, 50);//��������
			cm.gainItem(1332066,2,2,2,2,0,0,10,10,0,0,0,0,0,0,120);//���ֹκ���
			cm.gainItem(1112400,10,10,10,10,0,0,0,0,0,0,0,0,0,0,120);//��ָ
			cm.gainItem(1012071,10,10,10,10,0,0,0,0,0,0,0,0,0,0,120);
			cm.gainItem(2120000, 10);
			cm.gainItem(1142358,1,1,1,1,10,10,1,1,0,0,0,0,0,0); //����ѫ������ɰ�
			cm.gainPet(5000066, 520, 0, 0, 0, 0);//���ذ���	
			cm.gainItem(1112171,1); //������Ƭ
			cm.gainItem(5030000,1);
			//cm.������װ��(1332066, 1, 0, 20, 20, 20, 20, 50, 50, 30, 30,0, 0, 0, 0, 0, 0, 24);//���ֹκ���ʱ�䵥λ/Сʱ
			//cm.������װ��(1142358, 1, 0, 1, 1, 1, 1, 50, 50, 1, 1,0, 0, 0, 0, 0, 0, 0);//����ѫ������ɰ�
			//cm.������װ��(1142099, 1, 0, 20, 20, 20, 20, 500, 500, 35, 35,0, 0, 0, 0, 40, 40, 24);//ʱ�䵥λ/Сʱ
			//cm.������װ��(1142101, 1, 0, 20, 20, 20, 20, 500, 500, 15, 15,0, 0, 0, 0, 40, 40, 0);
			cm.gainMeso(10000000);//���
			//����:
			cm.gainItem(5150040,1);//�ʼ�������
			//cm.gainItem(4000463,10);//��������
			//cm.gainItem(4000313,10);//���ױ�
			//cm.gainDY(8000);//������þ�8000��  
            cm.getPlayer().modifyCSPoints(2,10000, true);//��ȯ			

			cm.ȫ����ɫ����("[��������] : ��ϲ��� "+cm.getPlayer().getName()+" �ɹ���ȡ�����´������")
            cm.dispose();
				
		} else {
            cm.sendOk("һ���˺�ֻ������ȡһ�Ρ�");
            cm.dispose();



            }
        }

}
var �ʺ� ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var ����è ="#fUI/ChatBalloon/37/n#";
var è�� =  "#fUI/ChatBalloon/37/ne#";
var è�� =  "#fUI/ChatBalloon/37/nw#";
var �� =    "#fUI/ChatBalloon/37/e#";
var �� =    "#fUI/ChatBalloon/37/w#";
var ����è ="#fUI/ChatBalloon/37/s#";
var è���� ="#fUI/ChatBalloon/37/se#";
var è���� ="#fUI/ChatBalloon/37/sw#";
var ���ͼ�� = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";