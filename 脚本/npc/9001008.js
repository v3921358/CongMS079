var status = 0;
var fstype = 0;
var price = 25000000; //砸卷价格
var types = new Array("装备栏", "消耗栏", "任务栏", "杂物栏", "现金栏");
var chance3 = (Math.floor(Math.random() * 8) + 1);

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
            cm.sendSimple("\t\t   #fMob/0100101.img/move/0#我能帮你提升装备回合#fMob/0100101.img/move/0#\r\n\r\n\t#r注意:#k1.装备回合上限是125\r\n         2.提升后装备将上锁\r\n\r\n#L1##b1.使用点卷提升装备回合(100%成功)#l#k                    \r\n#L2##b2.使用10万点卷提升装备回合10次（100%成功）#k");

//#L2##b2.使用10万点卷提升装备回合10次（100%成功）#k
//--------------------------------------------------------------------------------------------------------------------------

        } else if (status == 1) {
            if (selection == 2) { //点卷强化回合
                fstype = 2;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
             if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
             } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("商城点卷物品暂不支持.");
                    cm.dispose();
			} else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("限时装备不能强化.");
				cm.dispose();
             } else if(cm.getInventory(1).getItem(1)!= null && cm.getPlayer().getCSPoints(1) > 100000){
			 cm.sendNext("#b你目前选择的是用10万点卷提升装备回合10次,请一定要把提升的装备放在背包第一格\r\n\r\n#r（100%成功）");
			 }else{
			cm.sendOk("    提升失败：\r\n    装备不再第一格或者点券不足！");	
			cm.dispose();
                }
            } else if (selection == 1) { //冒险币强化回合
                fstype = 1;
			    var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
             if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
             } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("商城点卷物品暂不支持.");
                    cm.dispose();
			} else if(cm.getInventory(1).getItem(1).getExpiration() != -1) {
				cm.sendOk("限时装备不能强化.");
				cm.dispose();
             } else if(cm.getInventory(1).getItem(1)!= null && cm.getPlayer().getCSPoints(1) > 10000){	
                cm.sendNext("#b你目前选择的是用点卷提升装备回合.请一定要把提升的装备放在背包第一格\r\n\r\n#r需要10000点（100%成功）");
							 }else{
			cm.sendOk("    提升失败：\r\n    装备不再第一格或者点券不足！");	
			cm.dispose();
                 }
            } else if (selection == 3) { //点卷 100%
                fstype = 3;
                cm.sendNext("#b你目前选择的是用点卷提升装备回合.请一定要把提升的装备放在背包第一格\r\n\r\n#r需要10000点（100%成功）");
            }


//--------------------------------------------------------------------------------------------------------------------------

        } else if (status == 2) {

            if (fstype == 2) { //五彩提升回合
                fstype = 13;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("商城点卷物品暂不支持.");
                    cm.dispose();
                }
            }




            if (fstype == 3) { //冒险币强化回合
                fstype = 14;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("商城点卷物品暂不支持.");
                    cm.dispose();
                }
            }

            if (fstype == 1) { //点卷
                fstype = 15; 
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("商城点卷物品暂不支持.");
                    cm.dispose();
                }
            }

//--------------------------------------------------------------------------------------------------------------------------

if (fstype == 15) { //点卷
var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
var 装备 = [1102612,1082540,1052647,1003946,1072853,1402210,1302289,1432178,1372188,1382222,1452216,1462204,1472226,1482179,1492190,1332238,1442234,1052882,1052887,1052888,1052889,1052890,1004422,1004423,1004424,1004425,1004426,1102775,1102794,1102795,1102796,1102797,1082636,1082637,1082638,1082639,1082640,1073030,1073032,1073033,1073034,1073035,1402196,1302275,1432167,1372177,1382208,1452205,1462193,1472214,1332225,1492179,1482168,1442223,1402214,1432182,1452220,1462208,1472230,1482183,1492194,1332242,1382226];
var id = item.getItemId();
var pd = 装备.indexOf(id);
var 已升级次数= item.getLevel();
var 剩余回合 = item.getUpgradeSlots();
var 强化上限 = 100;
var pd = 强化上限 - (已升级次数 + 剩余回合);
if (pd != -1 & pd > 0 ){



                //var chance = Math.floor(Math.random() * 4);
               // if (chance <= 1) {
                    var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                    var statup = new java.util.ArrayList();

                    item.setLocked(1); //上锁!
                    item.setUpgradeSlots((item.getUpgradeSlots() + 1));
                    Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                    Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
                    cm.gainNX(-10000);
                    cm.sendOk("恭喜你成功拉，快快看你的包裹吧！");
                    cm.worldMessage("[提升回合]：恭喜[" + cm.getChar().getName() + "]使用10000点卷提升装备回合");
                    //cm.记录(2,"玩家" + cm.getPlayer().getName() + ": 装备["+ii.getName(item.getItemId())+"]使用10000点卷提升装备回合,当前次数为["+item.getUpgradeSlots()+"]装备唯一ID["+item.getEquipOnlyId()+"]")
                    
                    cm.dispose();
               	    } else {
                   // cm.gainNX(-2500);
                    cm.sendOk("真遗憾，次数达到上限了");
               // }
                    cm.dispose();
            }
}

if (fstype == 13) {
var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
var 装备 = [1102612,1082540,1052647,1003946,1072853,1402210,1302289,1432178,1372188,1382222,1452216,1462204,1472226,1482179,1492190,1332238,1442234,1052882,1052887,1052888,1052889,1052890,1004422,1004423,1004424,1004425,1004426,1102775,1102794,1102795,1102796,1102797,1082636,1082637,1082638,1082639,1082640,1073030,1073032,1073033,1073034,1073035,1402196,1302275,1432167,1372177,1382208,1452205,1462193,1472214,1332225,1492179,1482168,1442223,1402214,1432182,1452220,1462208,1472230,1482183,1492194,1332242,1382226];
var id = item.getItemId();
var pd = 装备.indexOf(id);
var 已升级次数= item.getLevel();
var 剩余回合 = item.getUpgradeSlots();
var 强化上限 = 90;
var pd = 强化上限 - (已升级次数 + 剩余回合);
if (pd != -1 & pd > 0){





  



              //  var chance = Math.floor(Math.random() * 5);
              //  if (chance <= 1) {
                    var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                    var statup = new java.util.ArrayList();

                    item.setLocked(1); //上锁!
                    item.setUpgradeSlots((item.getUpgradeSlots() + 10));
                    Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                    Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
                   
                    cm.gainNX(-100000);
                    cm.sendOk("恭喜你成功拉，快快看你的包裹吧！");
                    cm.worldMessage("[提升回合]：恭喜[" + cm.getChar().getName() + "]使用10万点卷提升装备次数");
                    //cm.记录(2,"玩家" + cm.getPlayer().getName() + ": 装备["+ii.getName(item.getItemId())+"]使用10万点卷提升装备次数,当前次数为["+item.getUpgradeSlots()+"]装备唯一ID["+item.getEquipOnlyId()+"]")
                    cm.dispose();
					
                   } else{  
                   cm.sendOk("真遗憾，次数达到上限了");
               // }
                   cm.dispose();
            }
}


            if (fstype == 14) {
var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
var 装备 = [1102612,1082540,1052647,1003946,1072853,1402210,1302289,1432178,1372188,1382222,1452216,1462204,1472226,1482179,1492190,1332238,1442234,1052882,1052887,1052888,1052889,1052890,1004422,1004423,1004424,1004425,1004426,1102775,1102794,1102795,1102796,1102797,1082636,1082637,1082638,1082639,1082640,1073030,1073032,1073033,1073034,1073035,1402196,1302275,1432167,1372177,1382208,1452205,1462193,1472214,1332225,1492179,1482168,1442223,1402214,1432182,1452220,1462208,1472230,1482183,1492194,1332242,1382226];
var id = item.getItemId();
var pd = 装备.indexOf(id);
var 已升级次数= item.getLevel();
var 剩余回合 = item.getUpgradeSlots();
var 强化上限 = 125;
var pd = 强化上限 - (已升级次数 + 剩余回合);
if (pd != -1 & pd > 0){


                if (cm.getMeso() < 50000000 ) {
                    cm.sendOk("对不起,您没有5000万冒险币");
                    cm.dispose();
                    return;
                }
                var chance = Math.floor(Math.random() * 4);
                if (chance <= 1) {
                    var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                    var statup = new java.util.ArrayList();

                    item.setLocked(1); //上锁!
                    item.setUpgradeSlots((item.getUpgradeSlots() + 1));
                    Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                    Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
                    cm.gainMeso(-50000000);
                    cm.sendOk("恭喜你成功拉，快快看你的包裹吧！");
                    //cm.worldMessage("[提升回合]：恭喜[" + cm.getChar().getName() + "]使用5000万提升装备回合");
                    cm.记录(2,"玩家" + cm.getPlayer().getName() + ": 装备["+ii.getName(item.getItemId())+"]使用5000万提升装备回合,当前次数为["+item.getUpgradeSlots()+"]装备唯一ID["+item.getEquipOnlyId()+"]")
                    
                    cm.dispose();
                    } else {
                    cm.gainMeso(-25000000);
                    cm.sendOk("真遗憾，升级失败");
                }
                cm.dispose();
            }
            }
        }
    }
	    }
