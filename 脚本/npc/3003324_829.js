/* global cm */
var ���� = "";
var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var Сѩ�� = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ����1 = "#fEffect/CharacterEff/1032063/0/0#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ����new = "#fUI/UIWindow/Quest/icon5/1#";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.sendOk("��л��Ĺ��٣�");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (status == 0) {
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text ="�һ�#v2550014#��Ҫ���²��ϣ�\r\n\r\n#v4000313# �� 30\t#v4031217# �� 1\t#v4251202# �� 1\t#v4011008# �� 20\r\n��ܰ��ʾ:ÿ��ֻ�ܶһ�3��~��\r\n";
			text +="#L0##b��Ҫ�һ�#l";
            cm.sendSimple(text);
        } else if (selection == 0) {//����ǿ�� �����漴2-5
		
            if(cm.getBossLog("���϶һ�ǿ������") >= 3){
			    cm.sendOk("�������Ѿ��һ���3���ˣ�");
				cm.dispose();
		    } else if(!cm.haveItem(4000313,30)){
				cm.sendOk("��û��#v4000313# �� 30 ��");
				cm.dispose();
			} else if(!cm.haveItem(4031217,1)){
				cm.sendOk("��û��#v4031217# �� 1 ��");
				cm.dispose();
			} else if(!cm.haveItem(4251202,1)){
				cm.sendOk("��û��#v4251202# �� 1 ��");
				cm.dispose();
			} else if(!cm.haveItem(4011008,20)){
				cm.sendOk("��û��#v4011008# �� 20 ��");
				cm.dispose();
			} else {
				cm.gainItem(4000313,-30);
				cm.gainItem(4031217,-1);
				cm.gainItem(4251202,-1);
				cm.gainItem(4011008,-20);
				cm.setBossLog("���϶һ�ǿ������");
				cm.gainItem(2550014,1);
				cm.sendOk("�һ��ɹ�~����");
				cm.dispose();
			}
		
        } else if (selection == 1) {//���ǿ�� �������2-5
            cm.openNpc(9310074,821);
			 } else if (selection == 2) {//����ǿ�� �Ӿ�
            cm.openNpc(9310074,823);
	    } else if (selection == 3) {//ǿ�� �Ҿ�  ���
            cm.openNpc(9310074,820);
        }
    }
}



