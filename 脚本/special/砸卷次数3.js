var status = -1;
var gailv = 50;//����ٷ�֮����Ҫ����ٷֺ�ֻҪ���������������!!
var xx;
importPackage(java.util);
importPackage(Packages.client);
importPackage(Packages.server);
importPackage(Packages.tools);
importPackage(Packages.tools.packet);
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
		
		
				
		
		//if (cm.getInventory(1).getItem(1).getLevel() >= 5) {
         //   cm.sendOk("ǿ����װ���ȼ����ܴ���#r120#k��Ŷ~!\r\n��ǰ#v"+Id+"##t"+Id+"# #b ��ǿ������:#r"+cm.getInventory(1).getItem(1).getLevel()+"");
         //   cm.dispose();
        //    return;
        //}
		
		/*if (cm.getInventory(1).getItem(1).getUpgradeSlots() >= 5) {
			var Id = cm.getInventory(1).getItem(1).getItemId();
            cm.sendOk("���Ҿ�������ܳ���#r 5 #k��Ŷ~!\r\n��ǰ#v"+Id+"##t"+Id+"# #b ���Ҿ����Ϊ:#r"+cm.getInventory(1).getItem(1).getUpgradeSlots()+"");
            cm.dispose();
            return;
        }*/
		
		
		
		/*if ((cm.getInventory(1).getItem(1).getUpgradeSlots() + cm.getInventory(1).getItem(1).getLevel()) >= 12) {
			var Id = cm.getInventory(1).getItem(1).getItemId();
            cm.sendOk("�������������ܳ���#r 12 #k��Ŷ~!\r\n��ǰ#v"+Id+"##t"+Id+"# #b ��ǿ������Ϊ:#r"+(cm.getInventory(1).getItem(1).getUpgradeSlots() + cm.getInventory(1).getItem(1).getLevel())+"");

            cm.dispose();
            return;
        }*/
		
		
		
		if (!cm.haveItem(2049401,10) && cm.getMeso() < 10000000) {
            cm.sendOk("ÿ��������Ҫ����#b10#k��#i2049401:#��1000����");
            cm.dispose();
            return;
        }
		var Id = cm.getInventory(1).getItem(1).getItemId();
        var selStr = "���û�ӭ����#rǿ������#k (�ɹ�����Ϊ50%)\r\n\r\n";
            selStr += "��Ҫ�����ĵ����� #v"+Id+"##t"+Id+"# \r\n#bĿǰ���Ҿ��ܴ���Ϊ:#r"+cm.getInventory(1).getItem(1).getUpgradeSlots()+"#b\r\nÿ����������#r1#b�ο��Ҿ����,����#b10#k��#i2049401:#��1000����.\r\n\r\n#r����ʧ�ܸ���==50%����ȷ��Ҫ������\r\n";//��ǰ��ǿ������:#r"+cm.getInventory(1).getItem(1).getLevel()+"
        cm.sendYesNo(selStr);
    } else if (status == 1) {
		var xx = cm.getInventory(1).getItem(1).getOwner();
		var statup = new java.util.ArrayList();
        var itemId1 = cm.getInventory(1).getItem(1).getItemId();
        var item = cm.getInventory(1).getItem(1).copy();
        var ii = Packages.server.MapleItemInformationProvider.getInstance();
        var type =  Packages.constants.GameConstants.getInventoryType(itemId1);
		//if (Math.floor(item.getItemId()/10000) >= 121 && Math.floor(item.getItemId()/10000) <= 171) {
            
        
		if (cm.getBossLog("ǿ������6") >= 5){
				cm.sendOk("������Ѿ��ɹ�ǿ����5����,������������!");
				cm.dispose();
			} else if (cm.getInventory(1).getItem(1) == null)  {
				cm.sendOk("���Ҫǿ����װ�����ڵ�һ����ܽ���.");
				cm.dispose();
			} else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("��ʱװ�����ܽ���ǿ��.");
				cm.dispose();
			} else if (xx != "�ߵȾ���+2"){
		    	cm.sendOk("���װ�����ǸߵȾ���+2������ǿ����+2��������Ӵ��");
                cm.dispose();
                return;
            } else if(cm.getInventory(1).getItem(1).copy().getFlag()==1){//�ж�װ������
                cm.sendOk("������װ������Ҫ�����ſ���ǿ��Ŷ!"); 		
                cm.dispose();    
                return;
            }
			else if (cm.getInventory(1).getItem(1).getUniqueId() !=-1) {
                cm.sendOk("ʱװ���������޷�����ǿ����");
                cm.dispose();
            
            }else if(cm.getMeso() < 10000000) {
                cm.sendOk("��Ǹ���Ľ�Ҳ���1000���������������");
                cm.dispose();
            }else if(!cm.haveItem(2049401,10)) {
                cm.sendOk("ÿ��ǿ����Ҫ����#b10#k��#i2049401���������������");
                cm.dispose();
            }else {
		
		
		s1 = Math.floor(Math.random() * (100 - 1) + 1);//�����
		if(s1 <= gailv){
			cm.gainMeso(-10000000);
			cm.gainItem(2049401,-10);
			item.setOwner("�ߵȾ���+3");
			MapleInventoryManipulator.removeFromSlot(cm.getC(),type,1,1, false);
		    MapleInventoryManipulator.addFromDrop(cm.getC(), item,false);
			
			

		    cm.getInventory(1).getItem(1).setUpgradeSlots(cm.getInventory(1).getItem(1).getUpgradeSlots()+1);
			cm.setBossLog('ǿ������6');
			cm.ˢ��״̬();
			cm.����(2,"��ϲ[" + cm.getPlayer().getName() + "]�ڸ߼���ͼǿ����������һ�ο�ǿ��������"); 
			cm.dispose();
		} else {
			//cm.setBossLog('ǿ������4');
			cm.gainMeso(-10000000);
			cm.gainItem(2049401,-10);
			cm.sendOk("ǿ��ʧ��~!");
            cm.dispose();
		}
    }
	/*}else {
		cm.sendOk("����������װ������ǿ�����ģ�ֻ���������ſ���Ӵ����");
        cm.dispose();
	}*/
	
}
}