/* Kedrick
	Fishking King NPC
*/

var status = -1;
var sel;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }

    if (status == 0) {
	
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
	
	
	
            text += "#e你好！钓鱼请购买钓鱼杆#v5340001#还有鱼饵#v2300000#好了介绍这么多，加油！\r\n"//3
            
            //text += "#L2##v3011000# 300W金币(12小时)#l"//3
            text += "#L3##r购买#b【#z5340001#】#r需要 #b【2000点券(12小时)】#l\r\n\r\n"//3
			
            text += "#L6##r购买#b【#z2300000#】#r需要 #b【200W金币(100个)】#l\r\n"//3
			text += "#L8##r购买#b【#z2300000#】#r需要 #b【2000W金币(1000个)】#l\r\n\r\n"//3
			
            text += "#L4##r购买#b【#z2300001#】#r需要 #b【5000点券(100个)】#l\r\n"//3
            text += "#L7##r购买#b【#z2300001#】#r需要 #b【50000点券(1000个)】#l\r\n\r\n"//3
			
			text += "#L5##r钓鱼道具兑换中心 #b【金币、抵用、国庆币等】#k#l\r\n"//3
            text += "#L12##r进入钓鱼场#k#b【享受垂钓的乐趣】#l\r\n\r\n"//3
		   
      /*      text += "#v5021007##v5021007##v5021007##v5021007##v5021007##v5021007##v5021007##v5021007##v5021007#\r\n"//3
            text += "#v5021007#欢迎各位参加雪花冒险岛第一届#r钓鱼大赛#k！#v5021007#\r\n\r\n"//3
            text += "#L10##v3011000##v5340001##v2300001#领取。#l\r\n"//3
            text += "#L11##v4031627##v4031628##v4031630##v4031631#查询当前钓鱼积分#l\r\n"//3
			
	sl = cm.getPlayer().getItemQuantity(4031627, false);
            sl = cm.getPlayer().getItemQuantity(4031627, false);
            slA = cm.getPlayer().getItemQuantity(4031628, false);
            slB = cm.getPlayer().getItemQuantity(4031630, false);
            slC = cm.getPlayer().getItemQuantity(4031631, false);
            //slD = cm.getPlayer().getItemQuantity(4031632, false);
            slE = cm.getPlayer().getItemQuantity(4031633, false);
            slF = cm.getPlayer().getItemQuantity(4031634, false);
            slG = cm.getPlayer().getItemQuantity(4031635, false);
            slH = cm.getPlayer().getItemQuantity(4031636, false);
            slI = cm.getPlayer().getItemQuantity(4031637, false);
            slJ = cm.getPlayer().getItemQuantity(4031638, false);
            slK = cm.getPlayer().getItemQuantity(4031639, false);
            slL = cm.getPlayer().getItemQuantity(4031640, false);
            slM = cm.getPlayer().getItemQuantity(4031641, false);
            slN = cm.getPlayer().getItemQuantity(4031642, false);
            slO = cm.getPlayer().getItemQuantity(4031643, false);
            slP = cm.getPlayer().getItemQuantity(4031644, false);
            slQ = cm.getPlayer().getItemQuantity(4031645, false);
            slR = cm.getPlayer().getItemQuantity(4031646, false);
            slS = cm.getPlayer().getItemQuantity(4031647, false);
            slT = cm.getPlayer().getItemQuantity(4031648, false);
            //slU = cm.getPlayer().getItemQuantity(4031629, false);
			slD=0;
			slU=0;
			var jifen = sl + slA + slB + slC + slD + slE + slF + slG + slH + slI + slJ + slK + slL + slM + slN + slO + slP + slQ + slR + slS + slT + slU;
			cm.喇叭(1,"玩家：["+cm.getName()+"] "+jifen+" ！");
			
			
		   */
		   
		   
		   
		   
		   
		if (cm.getPlayer().getClient().getChannel() != 1) {
			cm.sendOk("只能在1线钓鱼哦！");
			cm.dispose();
			return;
		}
	
		   if (cm.getPlayer().getMapId()==741000200){
            cm.sendSimple(text);
		   }else{
			//cm.sendOk("等GM通知。");
			//cm.dispose();
				//cm.喇叭(3,"玩家："+cm.getName()+" a。");
				  
		if (cm.getLevel() > 29){
			//cm.givePartyExp(651111100);//给队伍所有人经验
            cm.sendSimple(text);
		}else{
			cm.sendOk("30级以上才可以参加哦。");
			cm.dispose();
		}
           // cm.sendSimple(text);
		   }
       } else if (status == 1) {
	if (sel == 0) {
		
	} else if (selection == 1) {
			//cm.warp(741000200, 0);
			cm.dispose();
			cm.openNpc(9330108, 0);
		    
	} else if (selection == 10) {
     if (cm.getBossLog('钓鱼大赛') == 1) {
		    cm.sendOk("只能领取一次！");
		    cm.dispose();
		 } else {
		if (cm.haveItem(3011000)) {
		cm.sendOk("你已经有一把钓鱼椅。每个角色只能有1个钓鱼椅。");
	    } else {
		    cm.gainItem(3011000, 1, 1);
		    cm.gainItem(5340001, 1, 1);
		    cm.gainItem(2300001, 100, 1);
			cm.setBossLog('钓鱼大赛');
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		}
		}
	} else if (selection == 11) {
		    cm.sendOk("稍等");
		    cm.dispose();
	} else if (selection == 2) {
	    if (cm.haveItem(3011000)) {
		cm.sendOk("你已经有一把钓鱼椅。每个角色只能有1个钓鱼椅。");
	    } else {
		  if (cm.getLevel() > 49){
				if (cm.canHold(3011000) && cm.getMeso() >= 3000000) {
		    cm.gainMeso(-3000000);
		    cm.gainItem(3011000, 1, 12);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
				} else {
		    cm.sendOk("请检查是否有所需的金币或足够的背包空间。");
		    cm.dispose();
				}
		  }else{
		    cm.sendOk("最少要50级才可以来钓鱼！");
		    cm.dispose();
		  }	
	    }
	}else if (selection == 3) {
	    if (cm.haveItem(5340001)) {
		cm.sendOk("你已经有一把高级鱼竿了。");
	    } else {
		if (cm.getPlayer().getCSPoints(1) >= 2000) {
		    cm.gainNX(-2000);	//加减点券
		    cm.gainItem(5340001, 1,12);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		} else {
		    cm.sendOk("请检查是否有所需的金币或足够的背包空间。");
		    cm.dispose();
		}
	    }
	}else if (selection == 6) {
		if (cm.canHold(2300001) && cm.getMeso() >= 2000000) {
		    cm.gainMeso(-2000000);
		    cm.gainItem(2300000, 100);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		} else {
		    cm.sendOk("请检查是否有所需的金币或足够的背包空间。");
		    cm.dispose();
		}
		
		}else if (selection == 8) {
		if (cm.canHold(2300001) && cm.getMeso() >= 20000000) {
		    cm.gainMeso(-20000000);
		    cm.gainItem(2300000, 1000);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		} else {
		    cm.sendOk("请检查是否有所需的金币或足够的背包空间。");
		    cm.dispose();
		}
		
	}else if (selection == 4) {
		if (cm.getPlayer().getCSPoints(1) >= 5000) {
		    cm.gainNX(-5000);	//加减点券
		    cm.gainItem(2300001, 100);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		} else {
		    cm.sendOk("请检查是否有所需的金币或足够的背包空间。");
		    cm.dispose();
		}
		
		}else if (selection == 7) {
		if (cm.getPlayer().getCSPoints(1) >= 50000) {
		    cm.gainNX(-50000);	//加减点券
		    cm.gainItem(2300001, 1000);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		} else {
		    cm.sendOk("请检查是否有所需的金币或足够的背包空间。");
		    cm.dispose();
		}
		
	}else if (selection == 5) {
		cm.dispose();
		cm.openNpc(9330045, 5);
	
	
	}else if (selection == 12) {
		cm.dispose();
		cm.openNpc(9330108, 0);
	}
	
    } else if (status == 2) {
	if (sel == 1) {
	    if (cm.canHold(2300001,120) && cm.getMeso() >= 300000) {
		if (!cm.haveItem(2300001)) {
		    cm.gainMeso(-300000);
		    cm.gainItem(2300001, 120);
		    cm.sendNext("快乐钓鱼~");
		} else {
		    cm.sendNext("你已经有了钓鱼的诱饵。");
		}
	    } else {
		cm.sendOk("请检查是否有所需的300000金币或足够的背包空间。");
	    }
	    cm.safeDispose();
	}
    }
}
