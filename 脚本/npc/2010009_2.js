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
if (cm.getPlayer().getGuild() == null){
	cm.sendOk("�㻹û�м�����");
	cm.dispose();
} else {
	var a1 = "#L1#" + ��ɫ��ͷ + " #r��100��#v4000001#���������#v4001266#һ��" + ��̾�� + "\r\n\r\n";
	var a2 = "#L2#" + ��ɫ��ͷ + " #r��100��#v4000273#���������#v4001266#һ��" + ��̾�� + "\r\n\r\n";
	var a3 = "#L3#" + ��ɫ��ͷ + " #r��100��#v4000406#���������#v4001266#һ��" + ��̾�� + "\r\n\r\n";
			cm.sendSimple("ÿ�ռ��������أ�20��һ������70��2����100��3��\r\n"+a1+""+a2+""+a3+"");
}

        } else if (status == 1) {
            if (selection == 1) {//��
	if(cm.getLevel() >= 30 ){
		if(cm.getBossLog('30����������') < 1){
				if(cm.haveItem(4000001,100) ){
				cm.gainItem(4000001,-100);
				cm.gainItem(4001266,1);
                                cm.gainGP(+60);
				cm.setBossLog('30����������');
				cm.sendOk("������˼�������!");
				cm.dispose();
			} else {
				cm.sendOk("����ϲ�����");
				cm.dispose();
			}
		} else {
			cm.sendOk("���Ѿ���ɹ�һ����!");
			cm.dispose();
		}
	}else {
		cm.sendOk("��ȼ�����30����");
		cm.dispose();
	}

	    } else if (selection == 2) {//��
	if(cm.getLevel() >= 70 ){
		if(cm.getBossLog('70����������') < 1){
			if(cm.haveItem(4000273,100) ){
				cm.gainItem(4000273,-100);
				cm.gainItem(4001266,1);
				cm.setBossLog('70����������');
				cm.sendOk("������˼�������!");
				cm.dispose();
			} else {
				cm.sendOk("����ϲ�����");
				cm.dispose();
			}
		} else {
			cm.sendOk("���Ѿ���ɹ�һ����!");
			cm.dispose();
		}
	}else {
		cm.sendOk("��ȼ�����70����");
		cm.dispose();
	}

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
				cm.dispose();
			}
		} else {
			cm.sendOk("���Ѿ���ɹ�һ����!");
			cm.dispose();
		}
	}else {
		cm.sendOk("��ȼ�����100����");
		cm.dispose();
	}
		//cm.dispose();
	    
	  
	   
	    }
			
        }
    }
}