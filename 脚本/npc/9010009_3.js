var ���� = "#fEffect/CharacterEff/1022223/4/0#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon6/7#";
var ������ = "#fUI/UIWindow/Quest/icon3/6#";
var ��ɫ��ͷ = "#fUI/UIWindow/Quest/icon2/7#";
function start() {
    status = -1;

    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("��л��Ĺ��٣�");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "����������Թ���˫������Ŷ��\r\n\r\n";
            			
            //text += "" + ��ɫ��ͷ + "#L1##b#eС�ڰ� (24Сʱ)#v1142145#��Ҫ�����=1200��#l \r\n\r\n"//3				
            text += "" + ��ɫ��ͷ + "#L2##b#e�ڰ�#v5370001#   ��24Сʱ��    ��Ҫ������ȯ=500��#l \r\n\r\n"//3				
           // text += "" + ��ɫ��ͷ + "#L3##b#e˫�����鿨 ��12Сʱ��#v5211047#��Ҫ�����=500��#l \r\n\r\n"//3				
            //text += "" + ��ɫ��ͷ + "#L9##b#e˫�����ʿ���6Сʱ��#v5360014#��Ҫ�����=300��#l \r\n\r\n"//3
			//text += "" + ��ɫ��ͷ + "#L4##b#e˫�����鿨 ��һ�ܣ�#v5210001#��Ҫ�����=3000��#l \r\n\r\n"//3	
			//text += "" + ��ɫ��ͷ + "#L5##b#e˫�����ʿ���һ�ܣ�#v5360016#��Ҫ�����=3000��#l \r\n\r\n"//3
			//text += "" + ��ɫ��ͷ + "#L11##r����#v5520000#һ����Ҫ����ֵ��=20#l \r\n\r\n"//3
            //text += "" + ��ɫ��ͷ + "#L14##r#e#v5211060##z5211060# ��7��Ȩ����Ҫ����ֵ��10��\r\n\r\n"//3
		    //text += "" + ��ɫ��ͷ + "#L13##r#e����#r��ɫλһ����Ҫ����ֵ��30��+5500��ȯ#l \r\n\r\n"//3
			cm.sendSimple(text);
            }
			
			
			} else if (selection == 13) {
                if(cm.getmoneyb() < 30){
				cm.sendOk("��ֵ�Ҳ����޷�������");
                cm.dispose();
				} else if (cm.getPlayer().getNX() >= 5500) {
					cm.gainNX(-5500);
				cm.increaseCharacterSlots(0);//
				 cm.setmoneyb(-30);	
cm.����(2, "[" + cm.getPlayer().getName() + "]�ɹ�����[��ɫλ1��]");
            cm.dispose();
			}else{
            cm.sendOk("��ȯ�����޷�������");
            cm.dispose();
               }
			   
			   } else if (selection == 11) {
               // if (cm.getPlayer().getNX() >= 5000) {
				   if(cm.getmoneyb() >= 20){
				cm.setmoneyb(-20);	
				cm.gainItem(5520000,1,1);
cm.����(2, "[" + cm.getPlayer().getName() + "]�ɹ�����[��������]���������~");
            cm.dispose();
			}else{
            cm.sendOk("������޷�������");
            cm.dispose();
			}
			   
        } else if (selection == 1) {
                if (cm.getPlayer().getNX() >= 1200) {
				cm.gainNX(-1200);
				cm.gainItem(1142145,1,12);
cm.����(2, "[" + cm.getPlayer().getName() + "]�ɹ�����[����ѫ�¾�������30%��12Сʱ��]���������~");
            cm.dispose();
			}else{
            cm.sendOk("������޷�������");
            cm.dispose();
			}
        } else if (selection == 2) {
                if (cm.getPlayer().getDY() >= 500) {
				cm.gainDY(-500);
				cm.gainItem(5370001,1,24);
cm.����(2, "[" + cm.getPlayer().getName() + "] ��500���þ� �ɹ����� [�ڰ壨24СʱȨ��] ");
            cm.dispose();
			}else{
            cm.sendOk("����ȯ�����޷�������");
            cm.dispose();
			}
        } else if (selection == 3) {
                if (cm.getPlayer().getNX() >= 500) {
				cm.gainNX(-500);
				cm.gainItem(5211047,1,12);
cm.����(2, "[" + cm.getPlayer().getName() + "]�ɹ�����[˫�����鿨��12СʱȨ��]���������~");
            cm.dispose();
			}else{
            cm.sendOk("������޷�������");
            cm.dispose();
			}
        } else if (selection == 4) {
               if (cm.getPlayer().getNX() >= 3000) {
				cm.gainNX(-3000);
				cm.gainItem(5210001,1,168);
cm.����(2, "[" + cm.getPlayer().getName() + "]�ɹ�����[˫�����鿨��7��Ȩ��]���������~");
            cm.dispose();
			}else{
            cm.sendOk("��ֵ�Ҳ����޷�������");
            cm.dispose();
			}
        } else if (selection == 5) {
                if (cm.getPlayer().getNX() >= 3000) {
				cm.gainNX(-3000);
				cm.gainItem(5360016,1,168);
cm.����(2, "[" + cm.getPlayer().getName() + "]�ɹ�����[˫�����ʿ���һ��Ȩ��]���������~");
            cm.dispose();
			}else{
            cm.sendOk("��ֵ�Ҳ����޷�������");
            cm.dispose();
			}
			
			} else if (selection == 14) {
                if(cm.getmoneyb() >= 10){
				 cm.setmoneyb(-10);	
				cm.gainItem(5211060,1,168);
cm.����(2, "[" + cm.getPlayer().getName() + "]�ɹ�����[�������鿨��168СʱȨ��]���������~");
            cm.dispose();
			}else{
            cm.sendOk("������޷�������");
            cm.dispose();
			}
        } else if (selection == 6) {
                if (cm.getPlayer().getNX() >= 300) {
				cm.gainNX(-300);
				cm.gainItem(5360014,1,10);
cm.����(2, "[" + cm.getPlayer().getName() + "]�ɹ�����[˫�����ʿ���10СʱȨ��]���������~");
            cm.dispose();
			}else{
            cm.sendOk("������޷�������");
            cm.dispose();
			}
        } else if (selection == 7) {
                if (cm.getPlayer().getNX() >= 600) {
				cm.gainNX(-600);
				cm.gainItem(5360014,1,24);
cm.����(2, "[" + cm.getPlayer().getName() + "]�ɹ�����[˫�����ʿ���24СʱȨ��]���������~");
            cm.dispose();
			}else{
            cm.sendOk("������޷�������");
            cm.dispose();
			}
        } else if (selection == 8) {
                if (cm.getPlayer().getNX() >= 2000) {
				cm.gainNX(-2000);
				cm.gainItem(5360014,1,168);
cm.����(2, "[" + cm.getPlayer().getName() + "]�ɹ�����[˫�����ʿ���7��Ȩ��]���������~");
            cm.dispose();
			}else{
            cm.sendOk("������޷�������");
            cm.dispose();
			}
        } else if (selection == 9) {
                if (cm.getPlayer().getNX() >= 300) {
				cm.gainNX(-300);
				cm.gainItem(5360014,1,6);
cm.����(2, "[" + cm.getPlayer().getName() + "]�ɹ�����[˫�����ʿ���6СʱȨ��]���������~");
            cm.dispose();
			}else{
            cm.sendOk("���þ����޷�������");
            cm.dispose();
			}
        } else if (selection == 10) {
                if (cm.getPlayer().getDY() >= 600) {
				cm.gainDY(-600);
				cm.gainItem(5360014,1,3);
cm.����(2, "[" + cm.getPlayer().getName() + "]�ɹ�����[˫�����ʿ���3СʱȨ��]���������~");
            cm.dispose();
			}else{
            cm.sendOk("���þ����޷�������");
            cm.dispose();
			}
		}
    }
}


