var status = 0;
var slot = Array();
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
			cm.sendNext("��ӭ����#r" + cm.getChannelServer().getServerName() + "\r\n\�����������ʹ����������װ������ǿ��!\r\n#e#r\r\n#b��������ǿ������#r1��,#b�뽫��Ҫǿ����װ������#r��һ��!\r\nע�⣺ǿ���������Ч�ɾ��������#k\r\n");
		} else if (status == 1){
			var cc = cm.getInventory(1).getItem(1).getItemId();
        text = "#e ��ѡ��Ҫʹ�õľ��� #n   ��ǰ��һ����ƷΪ��#v"+cc+"#\r\n\r\n#b";
		text += "\r\n#L1##v2640010##z2640010##b";
		text += "\r\n#L2##v2640011##z2640011##b";
		text += "\r\n\r\n#L3##v2615001##z2615001##b";
		//text += "\r\n#L4##v2640011##z2640011##b";
        cm.sendSimple(text);
		} else if (status == 2) {
			if (cm.getInventory(1).getItem(1) == null)  {
				cm.sendOk("���Ҫǿ����װ�����ڵ�һ����ܽ���.");
				cm.dispose();
				return;
			}
			if (selection == 1){//��
				if(cm.haveItem(2640010)){
					cm.sendSimple("�Ƿ�ʹ��ף�����᣿\r\n#L11##r��#k#l\t\t#L12#��#l");
				}else {
					cm.sendOk("��ȷ�ϱ������Ƿ���#v2640010##z2640010#");
					cm.dispose();
				}
			}else if (selection == 2){//˫
				if(cm.haveItem(2640011)){
					cm.sendSimple("�Ƿ�ʹ��ף�����᣿\r\n#L21##r��#k#l\t\t#L22#��#l");
				}else {
					cm.sendOk("��ȷ�ϱ������Ƿ���#v2640011##z2640011#");
					cm.dispose();
				}
			}else if (selection == 3){//����
				if(cm.haveItem(2615001)){
					cm.sendSimple("�Ƿ�ʹ��ף�����᣿\r\n#L31##r��#k#l\t\t#L32#��#l");
				}else {
					cm.sendOk("��ȷ�ϱ������Ƿ���#v2615001##z2615001#");
					cm.dispose();
				}
			} else {
				cm.dispose();
			}
		} else if (status == 3) {
			if(selection == 11){
				var cc = cm.getInventory(1).getItem(1).getItemId();
				if(cc==1302289||cc==1312165||cc==1322215||cc==1332238||cc==1372188||cc==1382222||cc==1472226||cc==1482179||cc==1492190){
					status = 3;
					cm.sendYesNo("��Ҫǿ����װ��Ϊ:\r\n\r\n#v"+cc+"#\r\n\r\n#r#eȷ��Ҫ��ʼǿ����?");
				} else {
					cm.sendOk("#b�þ��᲻�����ڸ�װ����ǿ����#k");	
					cm.dispose();
				}
			}else if(selection == 12) {
				var cc = cm.getInventory(1).getItem(1).getItemId();
				if(cc==1302289||cc==1312165||cc==1322215||cc==1332238||cc==1372188||cc==1382222||cc==1472226||cc==1482179||cc==1492190){
					status = 4;
					cm.sendYesNo("��Ҫǿ����װ��Ϊ:\r\n\r\n#v"+cc+"#\r\n\r\n#r#eȷ��Ҫ��ʼǿ����?");
				} else {
					cm.sendOk("#b�þ��᲻�����ڸ�װ����ǿ����#k");	
					cm.dispose();
				}
			}else if(selection == 21) {
				var cc = cm.getInventory(1).getItem(1).getItemId();
				if(cc==1402210||cc==1412147||cc==1422152||cc==1432178||cc==1442234||cc==1452216||cc==1462204){
					status = 5;
					cm.sendYesNo("��Ҫǿ����װ��Ϊ:\r\n\r\n#v"+cc+"#\r\n\r\n#r#eȷ��Ҫ��ʼǿ����?");
				} else {
					cm.sendOk("#b�þ��᲻�����ڸ�װ����ǿ����#k");	
					cm.dispose();
				}
			}else if (selection == 22){
				var cc = cm.getInventory(1).getItem(1).getItemId();
				if(cc==1402210||cc==1412147||cc==1422152||cc==1432178||cc==1442234||cc==1452216||cc==1462204){
					status = 6;
					cm.sendYesNo("��Ҫǿ����װ��Ϊ:\r\n\r\n#v"+cc+"#\r\n\r\n#r#eȷ��Ҫ��ʼǿ����?");
				} else {
					cm.sendOk("#b�þ��᲻�����ڸ�װ����ǿ����#k");	
					cm.dispose();
				}
			}else if (selection == 31){
				var cc = cm.getInventory(1).getItem(1).getItemId();
				if(cc==1032222||cc==1032223||cc==1113074||cc==1113075||cc==1122266||cc==1122267||cc==1132245||cc==1132246){
					status = 7;
					cm.sendYesNo("��Ҫǿ����װ��Ϊ:\r\n\r\n#v"+cc+"#\r\n\r\n#r#eȷ��Ҫ��ʼǿ����?");
				} else {
					cm.sendOk("#b�þ��᲻�����ڸ�װ����ǿ����#k");	
					cm.dispose();
				}
			}else if (selection == 32){
				var cc = cm.getInventory(1).getItem(1).getItemId();
				if(cc==1032222||cc==1032223||cc==1113074||cc==1113075||cc==1122266||cc==1122267||cc==1132245||cc==1132246){
					status = 8;
					cm.sendYesNo("��Ҫǿ����װ��Ϊ:\r\n\r\n#v"+cc+"#\r\n\r\n#r#eȷ��Ҫ��ʼǿ����?");
				} else {
					cm.sendOk("#b�þ��᲻�����ڸ�װ����ǿ����#k");	
					cm.dispose();
				}
			}else {
				cm.dispose();
			}			
		} else if (status == 4){//��ף��
			if(!cm.haveItem(2340000)){
				cm.sendOk("ף�����᲻�㣡");
				cm.dispose();
				return;
				}
			var item = cm.getInventory(1).getItem(1).copy();
			if (item.getUpgradeSlots() <= 0){
				cm.sendOk("��ѡ���װ��ʣ��ǿ���������㣡");
				cm.dispose();
				return;
			}
		    if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ������ʹ��ǿ������.");
				cm.dispose();
				return;
			} else {
				var statup = new java.util.ArrayList();
				var sj = Math.floor(Math.random()*3);
				var itemId1 = cm.getInventory(1).getItem(1).getItemId();
				var item = cm.getInventory(1).getItem(1).copy();
				var ii = MapleItemInformationProvider.getInstance();
				var type =  ii.getInventoryType(itemId1);
				if(sj !=0){
					cm.gainItem(2640010,-1);
					cm.gainItem(2340000,-1);
					item.setUpgradeSlots(item.getUpgradeSlots()-1);
					item.setLevel(item.getLevel()+1);
					item.setStr(item.getStr()+3);
					item.setDex(item.getDex()+3);
					item.setInt(item.getInt()+3);
					item.setLuk(item.getLuk()+3);
					item.setWatk(item.getWatk()+8);
					item.setMatk(item.getMatk()+8);
					MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г���ˮ��ʹ�þ���ǿ���ɹ���",true).getBytes()); //����  
					//cm.laba(cm.getPlayer().getName() + "��ǿ�����桻" + " : " + "���������ʹ�þ���ǿ���ɹ���",9);
					cm.dispose();
					cm.getPlayer().fakeRelog();
				}else {
					cm.gainItem(2640010,-1);
					cm.gainItem(2340000,-1);
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г���ˮ��ʹ�þ���ǿ��ʧ�ܣ�",true).getBytes()); //����  
					cm.dispose();
				}
			}
		} else if (status == 5) {//����ף��
			var item = cm.getInventory(1).getItem(1).copy();
			if (item.getUpgradeSlots() <= 0){
				cm.sendOk("��ѡ���װ��ʣ��ǿ���������㣡");
				cm.dispose();
				return;
			}
		    if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ������ʹ��ǿ������.");
				cm.dispose();
				return;
			} else {
				var statup = new java.util.ArrayList();
				var sj = Math.floor(Math.random()*3);
				var itemId1 = cm.getInventory(1).getItem(1).getItemId();
				var item = cm.getInventory(1).getItem(1).copy();
				var ii = MapleItemInformationProvider.getInstance();
				var type =  ii.getInventoryType(itemId1);
				if(sj !=0){
					cm.gainItem(2640010,-1);
					//cm.gainItem(2340000,-1);
					item.setUpgradeSlots(item.getUpgradeSlots()-1);
					item.setLevel(item.getLevel()+1);
					item.setStr(item.getStr()+3);
					item.setDex(item.getDex()+3);
					item.setInt(item.getInt()+3);
					item.setLuk(item.getLuk()+3);
					item.setWatk(item.getWatk()+8);
					item.setMatk(item.getMatk()+8);
					MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г���ˮ��ʹ�þ���ǿ���ɹ���",true).getBytes()); //����  
					cm.dispose();
					cm.getPlayer().fakeRelog();
				}else {
					cm.gainItem(2640010,-1);
					item.setUpgradeSlots(item.getUpgradeSlots()-1);
					//cm.gainItem(2340000,-1);
					MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г���ˮ��ʹ�þ���ǿ��ʧ�ܣ�",true).getBytes()); //����  
					cm.dispose();
				}
			}
		} else if (status == 6) {//��ף��
			var item = cm.getInventory(1).getItem(1).copy();
			if (item.getUpgradeSlots() <= 0){
				cm.sendOk("��ѡ���װ��ʣ��ǿ���������㣡");
				cm.dispose();
				return;
			}
		    if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ������ʹ��ǿ������.");
				cm.dispose();
				return;
			} else {
				var statup = new java.util.ArrayList();
				var sj = Math.floor(Math.random()*3);
				var itemId1 = cm.getInventory(1).getItem(1).getItemId();
				var item = cm.getInventory(1).getItem(1).copy();
				var ii = MapleItemInformationProvider.getInstance();
				var type =  ii.getInventoryType(itemId1);
				if(sj !=0){
					cm.gainItem(2640011,-1);
					cm.gainItem(2340000,-1);
					item.setUpgradeSlots(item.getUpgradeSlots()-1);
					item.setLevel(item.getLevel()+1);
					item.setStr(item.getStr()+3);
					item.setDex(item.getDex()+3);
					item.setInt(item.getInt()+3);
					item.setLuk(item.getLuk()+3);
					item.setWatk(item.getWatk()+8);
					item.setMatk(item.getMatk()+8);
					MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г���ˮ��ʹ�þ���ǿ���ɹ���",true).getBytes()); //����  
					cm.dispose();
					cm.getPlayer().fakeRelog();
				}else {
					cm.gainItem(2640011,-1);
					//item.setUpgradeSlots(item.getUpgradeSlots()-1);
					cm.gainItem(2340000,-1);
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г���ˮ��ʹ�þ���ǿ��ʧ�ܣ�",true).getBytes()); //����  
					cm.dispose();
				}
			}
		} else if (status == 7) {//����ף��
			var item = cm.getInventory(1).getItem(1).copy();
			if (item.getUpgradeSlots() <= 0){
				cm.sendOk("��ѡ���װ��ʣ��ǿ���������㣡");
				cm.dispose();
				return;
			}
		    if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ������ʹ��ǿ������.");
				cm.dispose();
				return;
			} else {
				var statup = new java.util.ArrayList();
				var sj = Math.floor(Math.random()*3);
				var itemId1 = cm.getInventory(1).getItem(1).getItemId();
				var item = cm.getInventory(1).getItem(1).copy();
				var ii = MapleItemInformationProvider.getInstance();
				var type =  ii.getInventoryType(itemId1);
				if(sj !=0){
					cm.gainItem(2640011,-1);
					//cm.gainItem(2340000,-1);
					item.setUpgradeSlots(item.getUpgradeSlots()-1);
					item.setLevel(item.getLevel()+1);
					item.setStr(item.getStr()+3);
					item.setDex(item.getDex()+3);
					item.setInt(item.getInt()+3);
					item.setLuk(item.getLuk()+3);
					item.setWatk(item.getWatk()+8);
					item.setMatk(item.getMatk()+8);
					MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г���ˮ��ʹ�þ���ǿ���ɹ���",true).getBytes()); //����  
					cm.dispose();
					cm.getPlayer().fakeRelog();
				}else {
					cm.gainItem(2640011,-1);
					item.setUpgradeSlots(item.getUpgradeSlots()-1);
					//cm.gainItem(2340000,-1);
					MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г���ˮ��ʹ�þ���ǿ��ʧ�ܣ�",true).getBytes()); //����  
					cm.dispose();
				}
			}
		} else if (status == 8) {//����ף��
			var item = cm.getInventory(1).getItem(1).copy();
			if (item.getUpgradeSlots() <= 0){
				cm.sendOk("��ѡ���װ��ʣ��ǿ���������㣡");
				cm.dispose();
				return;
			}
		    if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ������ʹ��ǿ������.");
				cm.dispose();
				return;
			} else {
				var statup = new java.util.ArrayList();
				var sj = Math.floor(Math.random()*4);
				var itemId1 = cm.getInventory(1).getItem(1).getItemId();
				var item = cm.getInventory(1).getItem(1).copy();
				var ii = MapleItemInformationProvider.getInstance();
				var type =  ii.getInventoryType(itemId1);
				if(sj >=2){
					cm.gainItem(2615001,-1);
					//cm.gainItem(2340000,-1);
					item.setUpgradeSlots(item.getUpgradeSlots()-1);
					item.setLevel(item.getLevel()+1);
					item.setStr(item.getStr()+3);
					item.setDex(item.getDex()+3);
					item.setInt(item.getInt()+3);
					item.setLuk(item.getLuk()+3);
					item.setWatk(item.getWatk()+4);
					item.setMatk(item.getMatk()+4);
					MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г���ˮ��ʹ�þ���ǿ���ɹ���",true).getBytes()); //����  
					cm.dispose();
					cm.getPlayer().fakeRelog();
				}else {
					cm.gainItem(2615001,-1);
					item.setUpgradeSlots(item.getUpgradeSlots()-1);
					//cm.gainItem(2340000,-1);
					MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г���ˮ��ʹ�þ���ǿ��ʧ�ܣ�",true).getBytes()); //����  
					cm.dispose();
				}
			}
		}else if (status == 8) {//����ף��
			var item = cm.getInventory(1).getItem(1).copy();
			if (item.getUpgradeSlots() <= 0){
				cm.sendOk("��ѡ���װ��ʣ��ǿ���������㣡");
				cm.dispose();
				return;
			}
		    if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ������ʹ��ǿ������.");
				cm.dispose();
				return;
			} else {
				var statup = new java.util.ArrayList();
				var sj = Math.floor(Math.random()*4);
				var itemId1 = cm.getInventory(1).getItem(1).getItemId();
				var item = cm.getInventory(1).getItem(1).copy();
				var ii = MapleItemInformationProvider.getInstance();
				var type =  ii.getInventoryType(itemId1);
				if(sj >= 2){
					cm.gainItem(2615001,-1);
					cm.gainItem(2340000,-1);
					item.setUpgradeSlots(item.getUpgradeSlots()-1);
					item.setLevel(item.getLevel()+1);
					item.setStr(item.getStr()+3);
					item.setDex(item.getDex()+3);
					item.setInt(item.getInt()+3);
					item.setLuk(item.getLuk()+3);
					item.setWatk(item.getWatk()+4);
					item.setMatk(item.getMatk()+4);
					MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г���ˮ��ʹ�þ���ǿ���ɹ���",true).getBytes()); //����  
					cm.dispose();
					cm.getPlayer().fakeRelog();
				}else {
					cm.gainItem(2615001,-1);
					//item.setUpgradeSlots(item.getUpgradeSlots()-1);
					cm.gainItem(2340000,-1);
					MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
					Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"��ǿ�����桻" + " : " + " ��� [" + cm.getPlayer().getName() + "]�������г���ˮ��ʹ�þ���ǿ��ʧ�ܣ�",true).getBytes()); //����  
					cm.dispose();
				}
			}
		}
	}
}	