/*
��������
QQ327321366
*/
var MapleInventoryManipulator = Java.type('server.MapleInventoryManipulator');
var MapleItemInformationProvider = Java.type('server.MapleItemInformationProvider');
var status = 0;
var job;
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
		var ���� = "#fEffect/CharacterEff/1022223/4/0#";
		if (status == 0) {
			cm.sendNext("#r��������ѡ�����#d[����ǿ��]#k#rǿ��ϵͳ#k#l\r\n#d[����ǿ��]#bǿ���ɹ���:#r50%#k\r\n#b���Է���:#r-2~+8#k\r\n#bǿ������:#v4000000##r*200#k\r\n#bǿ������:#v4000134##r*100#k\r\n#bǿ������:#v4000175##r*2#k\r\n#bǿ������:500��#k\r\n#d��������:ÿ������ #r[50] #k��#l\r\n#bPS��#d���ڲ���ǿ�������˲��������ı���,���Ը�ǿ��ϵͳ����ǿ���ɹ���ʧ��,������۳�ǿ������!\r\n");
		} else if (status == 1) {
			var cc = cm.getInventory(1).getItem(1);
			var ii = Packages.server.MapleItemInformationProvider.getInstance();
		    var item = cm.getInventory(1).getItem(1);
			var statup = new java.util.ArrayList();
		//	var ���� = "#fEffect/CharacterEff/1022223/4/0#";
			if (cc == null) {
				cm.sendOk("#b��һ�����޶�����#k");
				status = -1;
			//} else if (!cc.getItemId() == 1902000 || !cc.getItemId() == 1902005) {
				} else if (!ii.isCash(item.getItemId())==true) {
				cm.sendYesNo(""+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+"\r\n#d#eBOSS��ʣ��:[#c4000000#]�� ������ǿ����["+cm.getPlayer().getBossLog('mhxzl') +"]��\r\n"+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+����+"\r\n\r\n��Ҫǿ����װ��Ϊ:\r\n\r\n#v" + cc.getItemId() + " \r\n\r\n#b#e��ҪBOSS�ң�#r 5 ��#k\r\n#bǿ����Ҫ:#v4000134#r*100��#k\r\n#bǿ����Ҫ:#v4000175##r*2#k\r\nǿ����Ҫ:500��#k\r\n#r#eȷ��Ҫ��ʼǿ����?");
			} else {
				cm.sendOk("�̳���Ʒ�ݲ�֧��." );
				status = -1;
			}
		} else if (status == 2) {
			if (!cm.haveItem(4000000,200)|| !cm.haveItem(4000175,2) || !cm.haveItem(4000134,100) ||  cm.getMeso() < 5000000){
				cm.sendOk("#b�Բ���,��BOSS����������:#r[5]�ţ�#k\r\n#b�Բ���,��#v4000175#����*2#k\r\n#b�Բ���,��#v4000134##r����*100#k\r\n#b�Բ���,������500��#k\r\n#b��ǰ��ӵ��#v4000000#:#r [#c4000000#] ��!#l#b��ǰ��ӵ��#v4000134#:#r [#c4000134#] ��!��ǰ��ӵ��#v4000175#:#r [#c4000175#] ��!#l");
				//cm.dispose();
				status = -1;
			} else if (cm.getBossLog("mhxzl") == 50) {
				cm.sendOk("������Ѿ�50��ǿ������,������������!");
				status = -1;
			} else if (cm.getInventory(1).getItem(1) == null) {
				cm.sendOk("���Ҫǿ����װ�����ڵ�һ����ܽ���.");
				status = -1;
			} else if (cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ�����ܽ���ǿ��.");
				status = -1;
			} else {
				var statup = new java.util.ArrayList();
				var sj =  Math.floor(Math.random() * 2);//1/3�ĳɹ����� 
			    cm.gainItem(4000000,-200);
				cm.gainItem(4000175,-2);
				cm.gainItem(4000134,-100);
				cm.gainMeso(-5000000);
				if (sj == 0) {
					var id = cm.getInventory(1).getItem(1).getItemId();
					var item = cm.getInventory(1).getItem(1).copy();
					var ii = MapleItemInformationProvider.getInstance();
					var type = ii.getInventoryType(id);
					var �������=Math.floor(Math.random()*8);
                    var �������=Math.floor(Math.random()*8);
                    var �������=Math.floor(Math.random()*8);
                    var �������=Math.floor(Math.random()*8);
				    var �﹥���=Math.floor(Math.random()*8);
                    var ħ�����=Math.floor(Math.random()*8);
					var ���ͷ = "#fUI/Basic/icon/arrow#"; // �� ���ͷ
					item.setWatk(item.getWatk()*1+�﹥���);
				    item.setMatk(item.getMatk()*1+ħ�����);
				    item.setStr(item.getStr()*1+�������);
				    item.setDex(item.getDex()*1+�������);
				    item.setInt(item.getInt()*1+�������);
				    item.setLuk(item.getLuk()*1+�������);
					//item.setDex(item.getDex()*1+lvsj);
					item.setLocked(1);
					MapleInventoryManipulator.removeFromSlot(cm.getC(), type, 1, 1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
					cm.setBossLog('mhxzl');
					cm.sendOk("#r#eǿ���ɹ�,ף����Ϸ���!#k \r\n     ������ " + ���ͷ + "#r + [" + ������� + "]#k\r\n     ���ݣ� " + ���ͷ + "#r + [" + ������� + "]#k\r\n     ������ " + ���ͷ + "#r + [" + ������� + "]#k\r\n     ������ " + ���ͷ + "#r + [" + ������� + "]#k\r\n     ������ " + ���ͷ + "#r + [" + �﹥��� + "]#k\r\n     ħ���� " + ���ͷ + "#r + [" + ħ����� + "]#k\r\n");
					//Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"[����ϵͳ]" + " : " + cm.getPlayer().getName() +"ǿ���˳���,���� [+"+lvsj+"] ����",true).getBytes());
           
					//cm.getC().getChannelServer().getWorldInterface().broadcastMessage(null,MaplePacketCreator.getItemMega(cm.getC().getChannel(),cm.getPlayer().getName() + " : " + "�������г�-ǿ��NPC-ǿ���ɹ������һ��ϲ���������ɣ�����",item, true).getBytes());
				//	cm.laba(cm.getPlayer().getName() + "��ǿ�����桻" + " : " + "������-ǿ���ɹ������һ��ϲ���������ɣ�", 9);
					status = -1;
				} else {
					var id = cm.getInventory(1).getItem(1).getItemId();
					var item = cm.getInventory(1).getItem(1).copy();
					var ii = MapleItemInformationProvider.getInstance();
					var type = ii.getInventoryType(id);
					var �������=Math.floor(Math.random()*2);
                    var �������=Math.floor(Math.random()*2);
                    var �������=Math.floor(Math.random()*2);
                    var �������=Math.floor(Math.random()*2);
				    var �﹥���=Math.floor(Math.random()*2);
                    var ħ�����=Math.floor(Math.random()*2);
					var ���ͷ = "#fUI/Basic/icon/arrow#"; // �� ���ͷ
					item.setWatk(item.getWatk()*1-�﹥���);
				    item.setMatk(item.getMatk()*1-ħ�����);
				    item.setStr(item.getStr()*1-�������);
				    item.setDex(item.getDex()*1-�������);
				    item.setInt(item.getInt()*1-�������);
				    item.setLuk(item.getLuk()*1-�������);
					//item.setDex(item.getDex()*1+lvsj);
					item.setLocked(1);
					MapleInventoryManipulator.removeFromSlot(cm.getC(), type, 1, 1, false);
					MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
					cm.setBossLog('mhxzl');
					cm.sendOk("#r#e���ź�,ǿ��ʧ��!#k \r\n     ������ " + ���ͷ + "#r - [" + ������� + "]#k\r\n     ���ݣ� " + ���ͷ + "#r - [" + ������� + "]#k\r\n     ������ " + ���ͷ + "#r - [" + ������� + "]#k\r\n     ������ " + ���ͷ + "#r - [" + ������� + "]#k\r\n     ������ " + ���ͷ + "#r - [" + �﹥��� + "]#k\r\n     ħ���� " + ���ͷ + "#r - [" + ħ����� + "]#k\r\n");
					//Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(9,cm.getC().getChannel(),"[����ϵͳ]" + " : " + cm.getPlayer().getName() +"ǿ���˳���,���� [+"+lvsj+"] ����",true).getBytes());
           
					status = -1;
				}
			}
		}
	}
}	