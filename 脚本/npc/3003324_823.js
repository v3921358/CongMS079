var status = -1;

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
            cm.sendOk("װ������һ���������Ʒ����Ŷ~!");
            cm.dispose();
            return;
        }
		if (cm.getInventory(1).getItem(1).getLevel() >= 10) {
            cm.sendOk("ǿ����װ���ȼ����ܴ���#r120#k��Ŷ~!\r\n��ǰ#v"+Id+"##t"+Id+"# #b ��ǿ������:#r"+cm.getInventory(1).getItem(1).getLevel()+"");
            cm.dispose();
            return;
        }

		if ((cm.getInventory(1).getItem(1).getUpgradeSlots() + cm.getInventory(1).getItem(1).getLevel()) >= 10) {
			var Id = cm.getInventory(1).getItem(1).getItemId();
            cm.sendOk("�������������ܳ���#r 10 #k��Ŷ~!\r\n��ǰ#v"+Id+"##t"+Id+"# #b ��ǿ������Ϊ:#r"+(cm.getInventory(1).getItem(1).getUpgradeSlots() + cm.getInventory(1).getItem(1).getLevel())+"");
            cm.dispose();
            return;
        }
		if (!cm.haveItem(4000463,5)) {
		//if (!cm.haveItem(4000463,1) && !cm.haveItem(4000464,1) ){
            cm.sendOk("����ǿ��ϵͳʹ����Ҫ���²���:\r\n\r\n�������ı���ȱ���ĸ�����\r\n#v4000463##t4000463# x 1\r\n\r\n#v4000464##t4000464# x 1");
            cm.dispose();
            return;
        }

		if (!cm.haveItem(4000464,1) ){
            cm.sendOk("����ǿ��ϵͳʹ����Ҫ���²���:\r\n\r\n�������ı���ȱ���ĸ�����\r\n#v4000463##t4000463# x 5\r\n\r\n#v4000464##t4000464# x 1");
            cm.dispose();
            return;
        }
		
		var Id = cm.getInventory(1).getItem(1).getItemId();
        var selStr = "���û�ӭ����#rǿ������#k\r\n\r\n#b";
            selStr += "��Ҫǿ������Ʒ�� #v"+Id+"##t"+Id+"# \r\n��ǰ��ǿ������:#r"+cm.getInventory(1).getItem(1).getLevel()+"#b ��ǿ������Ϊ:#r"+cm.getInventory(1).getItem(1).getUpgradeSlots()+"#b\r\n����ǿ��ϵͳʹ����Ҫ���²���:\r\n\r\n#v4000463##t4000463# x 5��#v4000464##t4000464# x 1�Ƿ�ǿ����";
        cm.sendYesNo(selStr);
    } else if (status == 1) {
		cm.gainItem(4000463,-5);//�����
		cm.gainItem(4000464,-1);//�й���
        cm.getInventory(1).getItem(1).setUpgradeSlots(cm.getInventory(1).getItem(1).getUpgradeSlots()+1);
		cm.ˢ��״̬();
		cm.����(2,"��ϲ[" + cm.getPlayer().getName() + "]��ǿ�����������Ĳ���������һ�ο�ǿ��������"); 
        cm.dispose();
    }
}