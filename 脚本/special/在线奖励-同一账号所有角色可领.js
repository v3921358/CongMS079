var ���ڽ����� = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var ��� = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var ���ڽ������� = "#fUI/UIWindow/MonsterCarnival/icon1#";
var ��ɺ� = "#fUI/UIWindow/MonsterCarnival/icon0#";
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
                text += "";
            }		
			text +="\t\t\t\t#e#dð�յ�Ver.085\r\n\t\t\t#d  ����ʱ�䣺#r" + cm.getGamePoints() + "����#k#n\r\n "
			text +="#b���߽�������Ϊ��[����ҩˮ][ף������][�齱��][��ȯ].\r\n"
			text +="#L1##r��ȡ���ù�Ӷ���ˣ�#v5030001# x1#l\r\n\r\n\r\n"//3

			
			if(cm.getPlayer().getGamePoints() >= 100 && cm.getPlayer().getGamePointsPD() == 0){
					text += "#L2##r"+��ɺ�+"����ʱ�䳬��100���ӣ�"+���+"#v2000019#x50��#l\r\n\r\n\r\n\r\n"//3
				} else if(cm.getPlayer().getGamePoints() >= 100 && cm.getPlayer().getGamePointsPD() > 0){
					text += ""+��ɺ�+"#r����ʱ�䳬��100���ӣ�#l"+���+"\r\n\r\n"//3
				} else {
					text += ""+���ڽ�������+"#r����ʱ�䳬��100���ӣ�#l"+���ڽ�����+"\r\n\r\n"//3
			}
			
			if(cm.getPlayer().getGamePoints() >= 200 && cm.getPlayer().getGamePointsPD() == 1){
					text += "#L3##r"+��ɺ�+"����ʱ�䳬��200���ӣ�"+���+"#v2340000#x2��.#v2049100#x2.#l\r\n\r\n\r\n"//3
				} else if(cm.getPlayer().getGamePoints() >= 200 && cm.getPlayer().getGamePointsPD() > 1){
					text += ""+��ɺ�+"#r����ʱ�䳬��200���ӣ�#l"+���+"\r\n\r\n"//3
				} else {
					text += ""+���ڽ�������+"#r����ʱ�䳬��200���ӣ�#l"+���ڽ�����+"\r\n\r\n"//3
			}
			
			if(cm.getPlayer().getGamePoints() >= 250 && cm.getPlayer().getGamePointsPD() == 2){
					text += "#L4##r"+��ɺ�+"����ʱ�䳬��250���ӣ�"+���+"#v4310030#x1��.#l\r\n\r\n\r\n"//3
				} else if(cm.getPlayer().getGamePoints() >= 250 && cm.getPlayer().getGamePointsPD() > 2){
					text += ""+��ɺ�+"#r����ʱ�䳬��250���ӣ�#l"+���+"\r\n\r\n"//3
				} else {
					text += ""+���ڽ�������+"#r����ʱ�䳬��250���ӣ�#l"+���ڽ�����+"\r\n\r\n"//3
			}
			if(cm.getPlayer().getGamePoints() >= 300 && cm.getPlayer().getGamePointsPD() == 3){
					text += "#L5##r"+��ɺ�+"����ʱ�䳬��300���ӣ�"+���+"��ȯ#x1500.#l\r\n\r\n\r\n"//3
				} else if(cm.getPlayer().getGamePoints() >= 300 && cm.getPlayer().getGamePointsPD() > 3){
					text += ""+��ɺ�+"#r����ʱ�䳬��300���ӣ�#l"+���+"\r\n\r\n"//3
				} else {
					text += ""+���ڽ�������+"#r����ʱ�䳬��300���ӣ�#l"+���ڽ�����+"\r\n\r\n"//3
			}
            cm.sendSimple(text);
        } else if (selection == 1) {
			if(cm.haveItem(5030001, 1)){
            cm.sendOk("���Ѿ���ȡ���ˡ��޷�������ȡ��");
            cm.dispose();
			}else if (cm.haveItem(5030000, 1)){
            cm.sendOk("���Ѿ���ȡ���ˡ��޷�������ȡ��");
            cm.dispose();
			}else{
			cm.gainItem(5030001, 1);//
			//cm.gainGamePointsPD(1);
            cm.sendOk("��ȡ�����ɹ���");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]��ȡ���ù�Ӷ���ˣ�");
            cm.dispose();
			}
        } else if (selection == 2) {
			cm.gainItem(2000019, 50);//����ҩˮ
			cm.gainGamePointsPD(1);
            cm.setBossLog("����ʱ�佱��");
			cm.worldMessage(6,"��ð�յ��������["+cm.getName()+"]��ȡ��100�������߽�����50������ҩˮ��");
            cm.dispose();
        } else if (selection == 3) {
			cm.gainItem(2340000, 2);//����ҩˮ
			cm.gainItem(2049100, 2);//����ҩˮ
			cm.gainGamePointsPD(1);
            cm.setBossLog("����ʱ�佱��");
			cm.worldMessage(6,"��ð�յ��������["+cm.getName()+"]��ȡ��200�������߽�����ף����������2����");
            cm.dispose();
        } else if (selection == 4) {
			cm.gainItem(4310030, 1);//����ҩˮ
			cm.gainGamePointsPD(1);
            cm.setBossLog("����ʱ�佱��");
			cm.worldMessage(6,"��ð�յ��������["+cm.getName()+"]��ȡ��250�������߽�������Ʒ�齱��1����");
            cm.dispose();
        } else if (selection == 5) {
               cm.gainNX(1500);
			cm.gainGamePointsPD(1);
            cm.sendOk("��ȡ�����ɹ���");
			cm.worldMessage(6,"��ð�յ��������["+cm.getName()+"]��ȡ��300�������߽�������ȯ1500��");
            cm.dispose();
        } else if (selection == 6) {
			cm.gainGamePointsPD(-300);
                        cm.gainGamePoints(-300)
			cm.gaintodayOnlineTime(-300);
			cm.gainItem(4001322, 2);//��ѩ�˷�������ʯ
			cm.gainGamePointsPD(1);
            cm.sendOk("��ȡ�����ɹ���");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]��ȡ��300�������߽�����");
            cm.dispose();
        } else if (selection == 7) {
                cm.gainD(200);
			cm.gainGamePointsPD(1);
            cm.sendOk("��ȡ�����ɹ���");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]��ȡ��360�������߽�����");
            cm.dispose();
        } else if (selection == 8) {
               cm.gainNX(100);
			cm.gainGamePointsPD(1);
            cm.sendOk("��ȡ�����ɹ���");
			cm.worldMessage(6,"��ң�["+cm.getName()+"]��ȡ��420�������߽�����");
            cm.dispose();
		}
    }
}


