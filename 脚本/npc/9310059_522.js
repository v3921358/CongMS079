/*
麒麟服务端
QQ327321366
*/
var 金币觉醒 = 10;
var 点券觉醒 = 10;
var 道具觉醒 = 10;
var 充值币觉醒 = 10;
var chance = Math.floor(Math.random()*10);//神级觉醒概率

var 金币 = 10000000;
var 点券 = 8888;
var 签到 = 5;
var 充值币 = 30;
//道具代码：

var 道具 = 4032398;//签到图章

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
		if (status >= 0 && mode == 0) {
		cm.dispose();
		return;
		}
		if (mode == 1)
		status++;
		else
		status--;


	if (status == 0) {
        if (cm.getInventory(1).getItem(1) == null)  {
		            cm.sendOk("请保证第一格有装备.");
				    cm.dispose();
		}else{
	    var textz = "\t\t\t\t#v4055000##e时装觉醒#v4055000##k#n\r\n";

		textz += "#L0#使用说明（必看）\r\n\r\n";

		textz += "#L1#时装觉醒   全属性[2]  金币x"+金币+"\r\n";

		textz += "#L2#时装觉醒   随机全属性[1-5]  点卷x"+点券+"\r\n";
		
		textz += "#L3#时装觉醒   随机全属性[1-8]  #z"+道具+"#X"+签到+"\r\n";
		
		textz += "#L4#充值币神级觉醒（土豪必备） x"+充值币+"/次，神级全属性20-攻魔10 血量 200  \r\n";
		
		cm.sendSimple (textz);  
}

             } else if (status == 1) {			 
                   if (selection == 0) {
                      var selStr = "当玩家的等级达到#e#r120#k#n级时，可以使用时装觉醒系统，时装觉醒系统能够为你的点装增加一定的#b力量，敏捷，治理，运气#k，目前可以使用游戏币，点券，出席图章激活点装，每种觉醒的方式带来的能力效果都不同。\r\n";
				          selStr += " 使用#r游戏币#k觉醒点状只能为点状增加#b力量，敏捷，治理，运气#k这四个属性值，并且能力值均为#r2#k\r\n";
						  selStr += " 使用点券觉醒点状，能为点状增加上述所有能力值，并且随机波动#r1-5#k点\r\n";
						  selStr += " 使用#b#z"+道具+"##k则能够随机波动#r1-8#k点#k\r\n";
						  selStr += " 使用#b充值币#k则能够随机波动#r5-10#k点，并有30%几率产生四维属性+20以及 双攻+10 血量 200 的#r神级觉醒#k\r\n\r\n";
						  selStr += " #e#r（*） 觉醒后的点状可以重复觉醒";
                          cm.sendOk(selStr);
						  cm.dispose();
                   }
				   if (selection == 1) {
                      var selStr = "这将花费掉你#r"+金币+"#k的游戏币为你的点状觉醒，请确认你已经把你想要觉醒的点状放到#b装备栏的第一格#r\r\n\r\n";
				          selStr += " （*）注意：如果你的点装已经具备高于本档次的属性，也同样会被重置为该档次的属性，你是否还要继续？\r\n";
                          cm.sendYesNo(selStr);
                          金币觉醒 = 1;
                   }
				   if (selection == 2) {					
                       var selStr = "这将花费掉你#r"+点券+"#k的点券为你的点状觉醒，请确认你已经把你想要觉醒的点状放到#b装备栏的第一格#r\r\n\r\n";
				          selStr += " （*）注意：如果你的点装已经具备高于本档次的属性，也同样会被重置为该档次的属性，你是否还要继续？\r\n";
                          cm.sendYesNo(selStr);
                          点券觉醒 = 1;
                   }
				   
				   if (selection == 3) {	
                       var selStr = "这将花费掉你#r"+签到+"#k的#z"+道具+"#为你的点状觉醒，请确认你已经把你想要觉醒的点状放到#b装备栏的第一格#r\r\n\r\n";
				          selStr += " （*）注意：如果你的点装已经具备高于本档次的属性，也同样会被重置为该档次的属性，你是否还要继续？\r\n";
                          cm.sendYesNo(selStr);
                          道具觉醒 = 1;
                   }
				   if (selection == 4) {	
                       var selStr = "#e#r此操作将扣除#r"+充值币+"#r点充值币并以30%的几率出现神级觉醒。#n#k\r\n请确认你已经把你想要觉醒的点状放到#b装备栏的第一格#r\r\n\r\n";
				          selStr += " （*）注意：如果你的点状已经具备高于本档次的属性，也同样会被重置为该档次的属性，你是否还要继续？\r\n";
                          cm.sendYesNo(selStr);
                          充值币觉醒 = 1;
                   }
				   
				   
	}else if (status == 2) {
           var ItemID = cm.getInventory(1).getItem(1).getItemId(); 
           var 升级次数 = cm.getInventory(1).getItem(1).getUpgradeSlots();	
		   var 最大次数 = cm.getInventory(1).getItem(1).getLevel();
		   var 血量 = cm.getInventory(1).getItem(1).getHp();
		   var 蓝量 = cm.getInventory(1).getItem(1).getMp();
	       if (金币觉醒 == 1){		
		   if (cm.isCash(ItemID) == false)  {
		            cm.sendOk("请保证第一格为#b“点装装备”#k");
				    cm.dispose();					
    } else if (cm.getPlayer().getMeso() <= 金币)  {
                    cm.sendOk("请保证有足够的金币。");
				    cm.dispose();
    } else if (cm.canHold(ItemID) == false) {
                    cm.sendOk("请保证有足够的背包空间。");
					cm.dispose();
                    } else{
					var item = cm.getInventory(1).getItem(1).copy();
					var ii = MapleItemInformationProvider.getInstance();
					//var type =  ii.getInventoryType(ItemID);
					cm.gainItem(ItemID,-1);
					cm.gainItem(ItemID,2,2,2,2,0,0,0,0,0,0,0,0,0,0);
					cm.gainMeso(-金币);
		 		    var selStr = "恭喜你强化成功。\r\n\r\n";
		 		    cm.sendOk(selStr);
		 		    cm.dispose();
					}

    } else if (点券觉醒 == 1){		
		   if (cm.isCash(ItemID) == false)  {
		            cm.sendOk("请保证第一格为#b“点装装备”#k");
				    cm.dispose();					
    } else if (cm.getPlayer().getCSPoints(1) <= 点券) {
                    cm.sendOk("请保证有足够的点券。");
				    cm.dispose();
                    } else{
                    cm.gainItem(ItemID,-1);
					cm.gainItem(ItemID,Math.floor(Math.random()*1 +4),Math.floor(Math.random()*1 +4),Math.floor(Math.random()*1 +4),Math.floor(Math.random()*1 +4),0,0,0,0,0,0,0,0,0,0);
					cm.gainNX( -点券);
		 		    var selStr = "恭喜你强化成功。\r\n\r\n";
		 		    cm.sendOk(selStr);
		 		    cm.dispose();
					}
					
    } else if (道具觉醒 == 1){		
		   if (cm.isCash(ItemID) == false)  {
		            cm.sendOk("请保证第一格为#b“点装装备”#k");
				    cm.dispose();					
    } else if (cm.haveItem(道具, 签到) == false) {
                    cm.sendOk("请保证有足够的道具");
				    cm.dispose();
                    } else{
                    cm.gainItem(ItemID,-1);
					cm.gainItem(ItemID,Math.floor(Math.random()*1 +4),Math.floor(Math.random()*1 +4),Math.floor(Math.random()*1 +4),Math.floor(Math.random()*1 +4),0,0,0,0,0,0,0,0,0,0);
					cm.gainItem(道具, -签到);
		 		    var selStr = "恭喜你强化成功。\r\n\r\n";
		 		    cm.sendOk(selStr);
		 		    cm.dispose();
					}
					
   					
    } else if (充值币觉醒 == 1){		
		   if (cm.isCash(ItemID) == false)  {
		            cm.sendOk("请保证第一格为#b“点装装备”#k");
				    cm.dispose();					
    } else if (cm.getmoneyb() <= 充值币) {
                    cm.sendOk("请保证有足够的充值币");
				    cm.dispose();
                    } else{
					if (chance >= 8) {
                    cm.gainItem(ItemID,-1);
					cm.gainItem(ItemID,20,20,20,20,200,200,10,10,0,0,0,0,0,0);
					cm.setmoneyb(-充值币);
		 		    var selStr = "恭喜你完成【神级觉醒】。\r\n\r\n";
		 		    cm.sendOk(selStr);
		 		    cm.dispose();
					} else{
					cm.gainItem(ItemID,-1);
					cm.gainItem(ItemID,Math.floor(Math.random()*4 +7),Math.floor(Math.random()*4 +7),Math.floor(Math.random()*4 +7),Math.floor(Math.random()*4 +7),0,0,0,0,0,0,0,0,0,0);
	                cm.setmoneyb(-充值币);
					var selStr = "恭喜你强化成功。\r\n\r\n";
		 		    cm.sendOk(selStr);
		 		    cm.dispose();
					}}
}
}
}}
