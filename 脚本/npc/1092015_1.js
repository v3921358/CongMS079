var MapleItemInformationProvider = Java.type('server.MapleItemInformationProvider');
var MapleInventoryManipulator = Java.type('server.MapleInventoryManipulator');
var status = 0;

function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	} else {
		if ((mode == 0 && status == 2) || (mode == 0 && status == 13)) {
			cm.dispose();
			return;
		}
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			cm.sendNext("��ӭ����#r" + cm.getChannelServer().getServerName() + "\r\n\�����������ʹ����Ʒǿ��װ��!\r\n#e#r60%���ӿ�ǿ��һ�Σ���Ҫ#v3992025#300��\n\r\n#bǿ������#r+1,#b�뽫��Ҫǿ����װ������#r��һ��!\r\nPS��ǿ�����ܽ���#k\r\nPS:ÿ��ÿ��ֻ��ʹ�� 20 �θù���\r\n");
		} else if (status == 1) {
			var cc = cm.getInventory(1).getItem(1);
			if (cm.haveItem(3992025,3)){
			} else {
				cm.sendOk("��û��#v3992025#������ǿ����");
				cm.dispose();
				}
				
			if(cc!= null ){
				cm.sendYesNo("��Ҫǿ����װ��Ϊ:\r\n\r\n#v"+cc.getItemId()+"#\r\n\r\n#r#eȷ��Ҫ��ʼǿ����?");
			} else {
				cm.sendOk("#b��һ�����޶�����#k");	
				cm.dispose();
			} 
		} else if (status == 2) {
		  if (cm.getBossLog("qianghua1") == 20){
				cm.sendOk("������Ѿ�ǿ����20����,������������!");
				cm.dispose();
			} else if (cm.getInventory(1).getItem(1) == null)  {
				cm.sendOk("���Ҫǿ����װ�����ڵ�һ����ܽ���.");
				cm.dispose();
			} else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ�����ܽ���ǿ��.");
				cm.dispose();
			} else {
				var statup = new java.util.ArrayList();
				var sj = Math.floor(Math.random()*20)+1;
				cm.gainItem(3992025,-3);//cm.gainNX(-5000);//�۳����
				if(sj >=10){
					var itemId1 = cm.getInventory(1).getItem(1).getItemId();
					var item = cm.getInventory(1).getItem(1).copy();
					var ii = MapleItemInformationProvider.getInstance();
					var type =  ii.getInventoryType(itemId1);
					item.setUpgradeSlots(item.getUpgradeSlots()+1);
					item.setLocked(1);
					MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
					cm.setBossLog('qianghua1');
					cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k \r\nװ����������+1\r\n��ǰǿ������#g"+item.getUpgradeSlots());
					cm.����(2,"��ϲ[" + cm.getPlayer().getName() + "]��ǿ�����������Ĳ���������һ�ο�ǿ��������"); 
					//Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г�-��ˮ��-ǿ���ɹ������ף����(��)�ɣ�",true).getBytes()); //
					//cm.laba(cm.getPlayer().getName() + "��ǿ�����桻" + " : " + "���������-ǿ���ɹ������һ��ϲ���������ɣ�",9);
					cm.dispose();
				}else {
					cm.setBossLog('qianghua1');
					cm.sendOk("#r#eǿ��ʧ�ܣ�,ף����Ϸ���!#kװ����δ���κα仯!");	 
					//Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г�-��ˮ��-ǿ��ʧ�ܣ����һ��ο��(��)�ɣ�",true).getBytes());
					//cm.laba(cm.getPlayer().getName() + "��ǿ�����桻" + " : " + "���������-ǿ��ʧ�ܣ����һ��ο���������ɣ�",9);
					//cm.����(2,"��ϲ[" + cm.getPlayer().getName() + "]��ǿ�����������Ĳ���������һ�ο�ǿ��������"); 
					cm.dispose();
				}
			}
		}
	}
}	