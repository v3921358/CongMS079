var aaa ="#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";

var status = 0;
var typed=0;
var RMB = 0;

function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	} else {
		if (mode == 0 && status == 0) {
			cm.dispose();
			return;
		}
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			var selStr = "#g-------------------#e#r������#k#g-------------------#k\r\n\r\n#b#b������ʾ�����������ӣ��˸���Խ�ڷܻ�ø���Խ��\r\n\r\n��Ҫ˵û�и������Ϳ����ǲ����ڿ���\r\n\r\n#b�ռ���#r#z4034251#��#z4034252#��#z4034253#��#z4034254##k#b �� #k#r100 #b������ #r#z2430210##k\r\n\r\n#b��֪����������������ٺ���أ�\r\n\r\n";
			selStr +="#L1##r"+aaa+" �������˵��[�������鿴]#l#k\r\n\r\n"; 
			selStr +=" \r\n\r\n";
                        cm.sendSimple(selStr);	
		} else if (status == 1) {
			if (selection == 1) {
				typed=1;
				cm.sendYesNo("- #d---------------#e�Ҫ��---------------#n#k\r\n#b���������Ҫ #r#z4034251#��#z4034252#��#z4034253#��#z4034254##k#b �� #k#r100 #b��#n#k\r\n#r(PS:������ﶼ�е���,���ʣ��ܸ�)\r\n\r\n#b��ǰӵ�� #r#z4034251##k#b ����Ϊ��           #r" + cm.getItemQuantity(4034251) + " / 100 ��\r\n#b��ǰӵ�� #r#z4034252##k#b ����Ϊ��           #r" + cm.getItemQuantity(4034252) + " / 100 ��\r\n#b��ǰӵ�� #r#z4034253##k#b ����Ϊ��           #r" + cm.getItemQuantity(4034253) + " / 100 ��\r\n#b��ǰӵ�� #r#z4034254##k#b ����Ϊ��           #r" + cm.getItemQuantity(4034254) + " / 100 ��\r\n#b��ǰ����õ� #r#z2430210##k#b ����Ϊ��   #r" + cm.getItemQuantity(2430210) + " ��\r\n#b��ǰ�����Ѿ���� #r���þ�#k Ϊ��     #r"+(2000*cm.getBossLog("�������", 1))+" ��\r\n#e#d������ʾ��#n#k#bÿ����һ��#r#z2430210##k#b���Ի�� #r2000#b ����þ�\r\n�� #r#z2430210##k#b �����Ի��2000���\r\n���������������񷵻���һҳ����ѡ��");
			}
		} else if (status == 2) {
			if(typed==1){
                if (cm.haveItem(4034251,100) && cm.haveItem(4034252,100) && cm.haveItem(4034253,100) && cm.haveItem(4034254,100)&& cm.getSpace(4) >= 1) {
			cm.gainItem(4034251, -100);
			cm.gainItem(4034252, -100);
			cm.gainItem(4034253, -100);
			cm.gainItem(4034254, -100);
			cm.gainItem(2430210, 1);
			cm.gainNX(2, 2000);
			cm.setBossLog("�������", 1);
			cm.sendOk("#b�ɹ������һ�� #r���#b �� #r2000#b ����þ�����");
			cm.worldSpouseMessage(0x20, "����������� : "+ cm.getChar().getName() +" ����������2000����þ��ܹ������ "+(2000*cm.getBossLog("�������", 1))+" ����þ�");
			cm.dispose();;
				} else {
			cm.sendOk("������Ʒ�������߱����ռ䲻��.");
			cm.dispose();
				}
           }
		}
	  }
	}