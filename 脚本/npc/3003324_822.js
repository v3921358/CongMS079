var status = -1;
����1= 4001083;
����1���� = 1;
����2 = 4000313;
����2���� = 50;
����3 = 4000463;
����3���� = 5;
ð�ձ� = 20000000;
function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
			cm.sendOk("��лʹ��~!");
            cm.dispose();
        }
        status--;
    }
    if (status == 0) {
        if (cm.getInventory(1).getItem(1) == null) {
            cm.sendOk("���Ҫǿ���������Ʒ���ڱ�����һ��!");
            cm.dispose();
            return;
        }
		���� = cm.getInventory(1).getItem(1).getStr();
		���� = cm.getInventory(1).getItem(1).getDex();
		���� = cm.getInventory(1).getItem(1).getLuk();
		���� = cm.getInventory(1).getItem(1).getInt();
		���� = cm.getInventory(1).getItem(1).getWatk();
		ħ�� = cm.getInventory(1).getItem(1).getMatk();
		s1 = Math.floor(Math.random() * (20 - 5) + 5);
		s2 =  Math.floor(Math.random() * (20 - 5) + 5);
		s3 =  Math.floor(Math.random() * (20 - 5) + 5);
		s4 =  Math.floor(Math.random() * (20 - 5) + 5);
		s5 =  Math.floor(Math.random() * (20 - 5) + 5);
		s6 =  Math.floor(Math.random() * (20 - 5) + 5);
		var Id = cm.getInventory(1).getItem(1).getItemId();
        var selStr = "���û�ӭ����#rǿ������#k\r\n\r\n#b";
            selStr += "��Ҫǿ������Ʒ�� #v"+Id+"##t"+Id+"# \r\n";
			selStr +="Ŀǰ����:"+cm.getInventory(1).getItem(1).getStr()+"\r\n";
			selStr +="Ŀǰ����:"+cm.getInventory(1).getItem(1).getDex()+"\r\n";
			selStr +="Ŀǰ����:"+cm.getInventory(1).getItem(1).getInt()+"\r\n";
			selStr +="Ŀǰ����:"+cm.getInventory(1).getItem(1).getLuk()+"\r\n";
			selStr +="Ŀǰ������:"+cm.getInventory(1).getItem(1).getWatk()+"\r\n";
			selStr +="Ŀǰħ������:"+cm.getInventory(1).getItem(1).getMatk()+"\r\n";
			selStr +="#eǿ�������Ի��������#b5-20#k.\r\n����ǿ��ϵͳʹ����Ҫ���²���:\r\n#v"+����1+"#x"+����1����+" #v"+����2+"#x"+����2����+" #v"+����3+"#x"+����3����+" ð�ձ� = "+ð�ձ�+"�Ƿ�ǿ����";
        cm.sendYesNo(selStr);
    } else if (status == 1) {
		if(!cm.haveItem(����1,����1����)){
			cm.sendOk("����ǿ��ϵͳʹ����Ҫ���²���:\r\n#v"+����1+"#x"+����1����+"");
            cm.dispose();
            return;
		} else if(!cm.haveItem(����2,����2����)){
			cm.sendOk("����ǿ��ϵͳʹ����Ҫ���²���:\r\n#v"+����2+"#x"+����2����+"");
            cm.dispose();
            return;
		} else if(!cm.haveItem(����3,����3����)){
			cm.sendOk("����ǿ��ϵͳʹ����Ҫ���²���:\r\n#v"+����3+"#x"+����3����+"");
            cm.dispose();
            return;
		} else if(cm.getMeso() < ð�ձ�){
			cm.sendOk("����ǿ��ϵͳʹ����Ҫ���²���:\r\nð�ձ� = "+ð�ձ�+"");
            cm.dispose();
            return;
		} else if(cm.getBossLog("ÿ�ղ���ǿ��") >= 8){
			cm.sendOk("ÿ��ֻ��ǿ��8��Ӵ");
            cm.dispose();
            return;
		} else {
		cm.gainItem(����1,-����1����);
		cm.gainItem(����2,-����2����);
		cm.gainItem(����3,-����3����);
		cm.gainMeso(-ð�ձ�);
		cm.setBossLog("ÿ�ղ���ǿ��");
		cm.getInventory(1).getItem(1).setStr(����+s1);
		cm.getInventory(1).getItem(1).setDex(����+s2);
		cm.getInventory(1).getItem(1).setInt(����+s3);
		cm.getInventory(1).getItem(1).setLuk(����+s4);
		cm.getInventory(1).getItem(1).setWatk(����+s5);
		cm.getInventory(1).getItem(1).setMatk(ħ��+s6);
		cm.ˢ��״̬();
		cm.����(2,"��ϲ[" + cm.getPlayer().getName() + "]��ǿ���������Ĳ���������5-20������ԣ�"); 
        cm.dispose();
    }
}
}