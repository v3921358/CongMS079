var status = 0;
var ��ˮ�� = 4021008;
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var Բ�� = "#fUI/UIWindow/Quest/icon3/6#";
var ����new = "#fUI/UIWindow/Quest/icon5/1#";
var ��̾�� = "#fUI/UIWindow/Quest/icon0#";
var ������ͷ = "#fUI/Basic/BtHide3/mouseOver/0#";
var �Ҹ� = "#k��ܰ��ʾ���κηǷ��������ҷ�Ŵ���.��ɱ��������.";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.dispose();
            return;
        }
        if (mode == 1)
            status++;
        else
            status--;
        if (status == 0) {
	var a1 = "#L1#" + ��ɫ��ͷ + " #r����ÿ��ǩ��" + ��̾�� + "\r\n\r\n";
	var a2 = "#L2#" + ��ɫ��ͷ + " #r����ÿ������" + ��̾�� + "\r\n\r\n";
	var a3 = "#L3#" + ��ɫ��ͷ + " #r��100��#v4000406#���������#v4001266#һ��" + ��̾�� + "\r\n\r\n";
			cm.sendSimple("ÿ�ռ��������أ�20��һ������70��2����100��3��\r\n"+a1+""+a2+"");

        } else if (status == 1) {
            if (selection == 1) {//��
				cm.dispose();
				cm.openNpc(2010009,1);
	    } else if (selection == 2) {//��
				cm.dispose();
				cm.openNpc(2010009,2);

	    } else if (selection == 3) {//��
	if(cm.getLevel() >= 100 ){
		if(cm.getBossLog('100����������') < 1){
			if(cm.haveItem(4000406,100) ){
				cm.gainItem(4000406,-100);
				cm.gainItem(4001266,1);
				cm.setBossLog('100����������');
				cm.sendOk("������˼�������!");
				cm.dispose();
			} else {
				cm.sendOk("����ϲ�����");
				cm,dispose();
			}
		} else {
			cm.sendOk("���Ѿ���ɹ�һ����!");
			cm.dispose();
		}
	}else {
		cm.sendOk("��ȼ�����100����");
		cm.dispose();
	}

	    } else if (selection == 4) {//��
				cm.dispose();
				cm.openNpc(9330067, 4);

	    } else if (selection == 5) {//��
				cm.dispose();
				cm.openNpc(1072006, 0);

	    } else if (selection == 6) {//��
				cm.dispose();
				cm.openNpc(9220018, 0);

	    }
			
        }
    }
}