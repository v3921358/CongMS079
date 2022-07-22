var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ttt ="#fUI/UIWindow.img/Quest/icon9/0#";
var xxx ="#fUI/UIWindow.img/Quest/icon8/0#";
var sss ="#fUI/UIWindow.img/QuestIcon/3/0#";
var 红色 = "#fEffect/CharacterEff/1114000/2/0#";
var status = 0;
var fstype = 0;


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

	    var textz = "#r在我这里强化装备! #k\r\n#b注意:装备请放第一格，#k#k#l#r强化成功会全区报喜哦!#k\r\n";

		textz += "------------------------------------------------------\r\n";
               
	//    textz += "#b#L9#" + 红色 + "使用1个#z4170000##v4170000#增加装备30HP、30MP、1物防、1魔防（100%成功）\r\n\r\n";
		
		//textz += "#b#L4#" + 红色 + "使用30个充值币增加装备四维各20点（失败返10币）\r\n\r\n";
		
		
		//textz += "#b#L55#" + 红色 + "使用2500点卷增加装备1次升级（上限7次）（#r失败返还#k）\r\n\r\n";
		textz += "#b#L66#" + 红色 + "使用100点卷增加装备50点#rHP(血)#b（失败不返还）#l\r\n\r\n";
		textz += "#b#L7#" + 红色 + "使用200抵用券增加装备50点#rMP(蓝)#b（失败不返还）#l\r\n\r\n";
		//textz += "\t\t#e#r#L777#" + 红色 + "#v4000038#强化装备升级次数\r\n\r\n";
		
		
		//textz += "#b#L6#" + 红色 + "使用2000W金币增加装备四维各5点（失败不返还）\r\n\r\n";
		//textz += "#b#L66#" + 红色 + "使用2000点卷增加装备100点HP  （失败返700点）\r\n\r\n";
		
		//textz += "#b#L44#" + 红色 + "使用3个充值币增加装备四维各2点(失败返1币)限10次\r\n\r\n";
		
		//textz += "#b#L10#" + 红色 + "使用1个#z4170009##v4170009#兑换一个#z2370000##v2370000#\r\n\r\n";
		//textz += "#r#L8#" + 蓝色角点 + "用1个时间之泪增加装备攻击10（100%成功，扣除回合）\r\n";



		cm.sendSimple (textz);  
  
//----------------------------------------------------------------------------------------------------------------------------------------------	
//----------------------------------------------------------------------------------------------------------------------------------------------		
	}else if (status == 1) {

            if (selection == 0) { //力量母矿
                fstype = 0;
                cm.sendNext("你目前选择的是用力量母矿增加装备10力量（有几率失败，失败退回一半，成功了回合减1）");

            }else if (selection == 1) { //智慧母矿
                fstype = 1;
                cm.sendNext("你目前选择的是用智慧母矿增加装备10智力（有几率失败，失败退回一半，成功了回合减1）");

            }else if (selection == 2) { //敏捷母矿
                fstype = 2;
                cm.sendNext("你目前选择的是用敏捷母矿增加装备10敏捷（有几率失败，失败退回一半，成功了回合减1）");

            }else if (selection == 3) { //幸运母矿
                fstype = 3;
                cm.sendNext("你目前选择的是用幸运母矿增加装备10运气（有几率失败，失败退回一半，成功了回合减1）");
        
			}else if (selection == 4) { //象征
                fstype = 4;
                cm.sendNext("你目前选择的是用30充值币增加装备四维各20.\r\n（失败返还10充值币）");
			
			}else if (selection == 44) { //象征
                fstype = 44;
                cm.sendNext("你目前选择的是用3充值币增加装备四维各2.\r\n（失败返还1充值币）");

            }else if (selection == 5) { //象征
                fstype = 5;
                cm.sendNext("你目前选择的是用2万点券增加装备四维各10，（失败不返还）");

            }else if (selection == 55) { //象征
                fstype = 55;
                 cm.openNpc(9900004, 1246);

            }else if (selection == 6) { //象征
                fstype = 6;
                cm.sendNext("你目前选择的是用2000W金币增加装备四维各5点（失败不返还）");

            }else if (selection == 7) { //黑龙角
                fstype = 7;
                cm.sendNext("你目前选择的是用200点抵用增加装备50点MP（失败不返还）");
				}else if (selection == 777) { //黑龙角
                fstype = 777;
                cm.openNpc(9900004,122222);	

				}else if (selection == 66) { //黑龙角
                fstype = 66;
                cm.sendNext("你目前选择的是用100点卷增加装备50点HP（失败不返还）");
				
            }else if (selection == 8) { //时间之石
                fstype = 8;
                cm.sendNext("你目前选择的是用时间之石增加装备攻击、魔法力10.加工费2000点卷（100%成功，回合减1）");
            }else if (selection == 9) { //飞天猪的蛋
                fstype = 9;
                cm.sendNext("你目前选择的是用飞天猪的蛋增加装备30HP、30MP、1物防、1魔防（100%成功，不减回合））");
            }else if (selection == 10) { //海盗副本蛋
                fstype = 10;
                cm.sendNext("你目前选择的是用海盗副本蛋兑换孙子兵法书");
            }

//----------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------------

      } else if (status == 2) {

            if (fstype == 0) { //力量母矿
                fstype = 0;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("暂不支持点券装备升星，请使用普通装备！");
                    cm.dispose();
  
                }
            }


            if (fstype == 1) { //智慧母矿
                fstype = 1;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("暂不支持点券装备升星，请使用普通装备！");
                    cm.dispose();
                }
            }

            if (fstype == 2) { //敏捷母矿
                fstype = 2;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("暂不支持点券装备升星，请使用普通装备！");
                    cm.dispose();
                }
            }

            if (fstype == 3) { //运气母矿
                fstype = 3;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("暂不支持点券装备升星，请使用普通装备！");
                    cm.dispose();
                }
            }
             

            if (fstype == 4) { //运气母矿
                fstype = 4;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("暂不支持点券装备升星，请使用普通装备！");
                    cm.dispose();
                }
            }


			if (fstype == 44) { //运气母矿
                fstype = 44;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("暂不支持点券装备升星，请使用普通装备！");
                    cm.dispose();
                }
            }
			
			
            if (fstype == 5) { //运气母矿
                fstype = 5;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("暂不支持点券装备升星，请使用普通装备！");
                    cm.dispose();
                }
            }


            if (fstype == 6) { //运气母矿
                fstype = 6;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("暂不支持点券装备升星，请使用普通装备！");
                    cm.dispose();
                }
            }


            if (fstype == 66) { //运气母矿
                fstype = 66;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("暂不支持点券装备升星，请使用普通装备！");
                    cm.dispose();
                }
            }

            if (fstype == 7) { //运气母矿
                fstype = 7;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                } else if (ii.isCash(item.getItemId()) == true) {
                    cm.sendOk("暂不支持点券装备升星，请使用普通装备！");
                    cm.dispose();
                }
            }
			
			if (fstype == 9) { //运气母矿
                fstype = 9;
                var ii = Packages.server.MapleItemInformationProvider.getInstance();
                var item = cm.getInventory(1).getItem(1);
                var statup = new java.util.ArrayList();
                if (item == null) {
                    cm.sendOk("对不起,你装备栏第一格没有装备!");
                    cm.dispose();
                }
            }

//----------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------------
	if (fstype == 0) {
		if (!cm.haveItem(4005000,10))  {
                    cm.sendOk("请带来#r 10 #k个#z4005000##v4005000#");
                    cm.dispose();
		}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                    cm.sendOk("升级次数没了，无法强化!");
                    cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
		 item.setStr(item.getStr()+10);
                 item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 cm.gainItem(4005000,-10);
		 cm.sendOk("#r#e强化成功,祝您游戏愉快!#k");
                 cm.serverNotice("『强化系统』：恭喜"+ cm.getChar().getName() +"        成功为装备增加10力量"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.gainItem(4005000,-2);
		 cm.sendOk("强化失败，退回你8个力量水晶");	
		}
		 cm.dispose();
		}



	} else if (fstype == 1) {
		if (!cm.haveItem(4005001,10))  {
                    cm.sendOk("请带来#r 10 #k个#z4005001##v4005001#");
                    cm.dispose();
		}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                    cm.sendOk("升级次数没了，无法强化!");
                    cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
		 item.setInt(item.getInt()+10);
                 item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 cm.gainItem(4005001,-10);
		 cm.sendOk("#r#e强化成功,祝您游戏愉快!#k");
                 cm.serverNotice("『强化系统』：恭喜"+ cm.getChar().getName() +"        成功为装备增加10智力"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.gainItem(4005001,-2);
		 cm.sendOk("强化失败，退回你8个智慧水晶");	
		}
		 cm.dispose();
		}

	} else if (fstype == 2) {
		if (!cm.haveItem(4005002,10))  {
                    cm.sendOk("请带来#r 10 #k个#z4005002##v4005002#");
                    cm.dispose();
		}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                    cm.sendOk("升级次数没了，无法强化!");
                    cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
		 item.setDex(item.getDex()+20);
                 item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 cm.gainItem(4005002,-10);
		 cm.sendOk("#r#e强化成功,祝您游戏愉快!#k");
                 cm.serverNotice("『强化系统』：恭喜"+ cm.getChar().getName() +"        成功为装备增加10敏捷"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.gainItem(4005002,-2);
		 cm.sendOk("强化失败，退回你8个敏捷水晶");	
		}
		 cm.dispose();
		}

	} else if (fstype == 3) {
		if (!cm.haveItem(4005003,10))  {
                    cm.sendOk("请带来#r 10 #k个#z4005003##v4005003#");
                    cm.dispose();
		}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                    cm.sendOk("升级次数没了，无法强化!");
                    cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
		 item.setLuk(item.getLuk()+20);
                 item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 cm.gainItem(4005003,-10);
		 cm.sendOk("#r#e强化成功,祝您游戏愉快!#k");
                 cm.serverNotice("『强化系统』：恭喜"+ cm.getChar().getName() +"        成功为装备增加10运气"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.gainItem(4005003,-2);
		 cm.sendOk("强化失败，退回你8个运气水晶");	
		}
		 cm.dispose();
		}

	}  else if (fstype == 4) {
              if (cm.getmoneyb() <= 30){
                    cm.sendOk("请带来30充值币加工费");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("升级次数没了，无法强化!");
                    //cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
                // item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 item.setStr(item.getStr() + 20); //给予装备力量
		 item.setDex(item.getDex() + 20);//给予装备敏捷
		 item.setInt(item.getInt() + 20);//给予装备智力
		 item.setLuk(item.getLuk() + 20);//给予装备运气
		 //cm.gainItem(4001084,-1);
		 cm.setmoneyb(-30);
		 cm.gainjf(+30);
		 cm.sendOk("#r#e强化成功,祝您游戏愉快!#k");
              //cm.serverNotice("『强化系统』：恭喜"+ cm.getChar().getName() +"        装备增加四维各20点");
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.setmoneyb(-20);
		 cm.gainjf(+20);
		 cm.sendOk("强化失败，退回你10个充值币");	
		}
		 cm.dispose();
		}

		
			}  else if (fstype == 44) {
              if (cm.getmoneyb() < 3){
                    cm.sendOk("请带来3充值币加工费");
                    cm.dispose();
					
		}	else    if (cm.getBossLog('PlayQuest40') >= 10) {
			cm.sendOk("你今天强化次数超过10次!");
			cm.dispose();	
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
                // item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 item.setStr(item.getStr()+2); //给予装备力量
		 item.setDex(item.getDex() + 2);//给予装备敏捷
		 item.setInt(item.getInt() + 2);//给予装备智力
		 item.setLuk(item.getLuk() + 2);//给予装备运气
		 //item.setWatk(item.getWatk() + 10);//加攻击
		 //item.setMatk(item.getMatk() + 10);//加魔力
		 //cm.gainItem(4001084,-1);
		 cm.setmoneyb(-3);
		 cm.gainjf(+3);
		 cm.setBossLog('PlayQuest40');
		 cm.sendOk("#r#e强化成功,祝您游戏愉快!#k");
             // cm.serverNotice("『王者强化系统』：恭喜"+ cm.getChar().getName() +"        使用3个充值币为装备增加四维各2点");
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.setmoneyb(-1);
		 cm.gainjf(+1);
		 cm.setBossLog('PlayQuest40');
		 cm.sendOk("强化失败，退回你1个充值币");	
		}
		 cm.dispose();
		}

		
		
	} else if (fstype == 5) {
		if (cm.getNX(1) <= 20000){
                    cm.sendOk("请带来2万点券加工费");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("升级次数没了，无法强化!");
                    //cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 3);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
                // item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 item.setStr(item.getStr()+5); //给予装备力量
		 item.setDex(item.getDex() + 5);//给予装备敏捷
		 item.setInt(item.getInt() + 5);//给予装备智力
		 item.setLuk(item.getLuk() + 5);//给予装备运气
		// cm.gainItem(4001085,-1);
		 cm.gainNX(-20000);
		 cm.sendOk("#r#e强化成功,祝您游戏愉快!#k");
            cm.serverNotice("『强化系统』：恭喜"+ cm.getChar().getName() +" 使用2000W为装备增加四维各5点"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		  cm.gainNX(-20000);
		 cm.sendOk("强化失败，杯具~~~~~");	
		}
		 cm.dispose();
		}

	} else if (fstype == 66) {//点卷血洗
		 if (cm.getPlayer().getNX() <=100){
                    cm.sendOk("请带来100点卷加工费");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("升级次数没了，无法强化!");
                    //cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
              //   item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 //item.setStr(item.getStr()+5); //给予装备力量
		 //item.setDex(item.getDex() + 5);//给予装备敏捷
		 //item.setInt(item.getInt() + 5);//给予装备智力
		 //item.setLuk(item.getLuk() + 5);//给予装备运气
		 item.setHp(item.getHp()+50);//给予装备100血
		// cm.setBossLog("废弃副本");//
		// cm.setBossLog("玩具副本");//
		 //cm.setBossLog("毒物副本");//
		// cm.gainItem(4001083,-1);
		 cm.gainNX(-100);
		 cm.sendOk("#r#e强化成功,祝您游戏愉快!#k");
                 cm.serverNotice("『强化系统』：恭喜"+ cm.getChar().getName() +"        使用100点卷为装备增加50点HP"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		cm.gainNX(-100);
		 cm.sendOk("强化失败，杯具~~~~~");	
		}
		 cm.dispose();
		}

		} else if (fstype == 6) {
		 if (cm.getMeso() <= 20000000){
                    cm.sendOk("请带来2000万金币加工费");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("升级次数没了，无法强化!");
                    //cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 5);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
              //   item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 item.setStr(item.getStr()+5); //给予装备力量
		 item.setDex(item.getDex() + 5);//给予装备敏捷
		 item.setInt(item.getInt() + 5);//给予装备智力
		 item.setLuk(item.getLuk() + 5);//给予装备运气
		// cm.gainItem(4001083,-1);
		 cm.gainMeso(-20000000);
		 cm.sendOk("#r#e强化成功,祝您游戏愉快!#k");
                 cm.serverNotice("『强化系统』：恭喜"+ cm.getChar().getName() +"        使用2000W金币为装备增加四维各5点"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
		 cm.gainMeso(-20000000);
		 cm.sendOk("强化失败，杯具~~~~~");	
		}
		 cm.dispose();
		}

		
	} else if (fstype == 7) {
                if (cm.getPlayer().getDY() <=200){
                    cm.sendOk("请带来200抵用");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("升级次数没了，无法强化!");
                    //cm.dispose();
			
		} else {

                 var chance = Math.floor(Math.random() * 4);
                 if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
                // item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 //item.setStr(item.getStr()+7); //给予装备力量
		 //item.setDex(item.getDex() + 7);//给予装备敏捷
		 //item.setInt(item.getInt() + 7);//给予装备智力
		 //item.setLuk(item.getLuk() + 7);//给予装备运气
				item.setMp(item.getMp()+50);
		// cm.gainItem(4001430,-1);
		 cm.gainDY(-200);
		 cm.sendOk("#r#e强化成功,祝您游戏愉快!#k");
               cm.serverNotice("『强化系统』：恭喜"+ cm.getChar().getName() +"        使用200点抵用券为装备增加50点MP"); 
			   //cm.喇叭(5,"[跑商系统]：玩家" + cm.getPlayer().getName() + "第六环完成!获得金币10万、经验8万、抵用卷200点");
			   cm.worldMessage(5,"【"+cm.getName()+"】在boss房间召唤召唤了女老板！");
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		} else {
	   cm.gainDY(-200);
	   cm.sendOk("强化失败，杯具~~~~");	
		}
		 cm.dispose();
		}

	} else if (fstype == 8) {
		if (!cm.haveItem(4021010,1))  {
                    cm.sendOk("请带来#r 1 #k个#z4021010##v4021010#");
                    cm.dispose();
		}else if (cm.getPlayer().getNX() <=2000){
                    cm.sendOk("请带来2000点卷");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("升级次数没了，无法强化!");
                    //cm.dispose();
			
		} else {

                 //var chance = Math.floor(Math.random() * 3);
                // if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
                 item.setUpgradeSlots((item.getUpgradeSlots() - 1));
		 item.setWatk(item.getWatk() + 10);
		 item.setMatk(item.getMatk() + 10);
		 cm.gainItem(4021010,-1);
		 cm.gainNX(-2000);
		 cm.sendOk("#r#e强化成功,祝您游戏愉快!#k");
                 cm.serverNotice("『黑龙之角强化装备』：恭喜"+ cm.getChar().getName() +"        使用1个时间之石为装备增加攻击10"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		//} else {
		 //cm.gainItem(4005003,-5);
		 //cm.sendOk("强化失败，退回你5个运气母矿");	
		//}
		 cm.dispose();
		}


	}	else if (fstype == 9) {
		if (!cm.haveItem(4170000,1)){
                    cm.sendOk("请带来#r 1 #k个#z4170000##v4170000#");
                    cm.dispose();
		}else if (cm.getMeso() <= 0){
                    cm.sendOk("身上至少带1金币");
                    cm.dispose();
		//}else if (cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy().getUpgradeSlots() <=0) {
                   // cm.sendOk("升级次数没了，无法强化!");
                    //cm.dispose();
			
		} else {

                 //var chance = Math.floor(Math.random() * 3);
                // if (chance <= 1) {
                 var item = cm.getChar().getInventory(Packages.client.inventory.MapleInventoryType.EQUIP).getItem(1).copy();
                 var statup = new java.util.ArrayList();
				item.setHp(item.getHp()+30);
				item.setMp(item.getMp()+30);
				item.setWdef(item.getWdef()+1);
				item.setMdef(item.getMdef()+1);
		//cm.gainItem(4005003,-1);
                 //item.setUpgradeSlots((item.getUpgradeSlots() - 1));
				cm.gainItem(4170000,-1);

				cm.sendOk("#r#e强化成功,祝您游戏愉快!#k");
		 
                // cm.serverNotice("『强化装备』：恭喜"+ cm.getChar().getName() +"成功为装备增加30HP、30MP、1物防、1魔防"); 
                 Packages.server.MapleInventoryManipulator.removeFromSlot(cm.getC(), Packages.client.inventory.MapleInventoryType.EQUIP, 1, 1, false);
                 Packages.server.MapleInventoryManipulator.addFromDrop(cm.getC(), item, false);
		//} else {
		 //cm.gainItem(4005003,-1);
		 //cm.sendOk("强化失败，退回你9个运气母矿");	
		//}
		 cm.dispose();
		}

	} else if (fstype == 10) {
		if (!cm.haveItem(4170009,1)){
            cm.sendOk("请带来#r 1 #k个#z4170009##v4170009#");
            cm.dispose();
		} else {
            cm.gainItem(4170009,-1);
			cm.gainItem(2370000,1);
			cm.sendOk("#r#e兑换成功,祝您游戏愉快!#k");
            cm.dispose();
		} 

	}
									
}
}
}
