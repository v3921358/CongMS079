var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣    唯一微信:ZerekY   ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    }
    else {
        if (status >= 0 && mode == 0) {

            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        }
        else {
            status--;
        }
        if (status == 0) {
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			text += ""
			text += "                  #k"+皇冠白+" #r#e#w 毕 业 饰 品 #n#k "+皇冠白+"\r\n\r\n";
			text += "  "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"\r\n";
            text += "    注意:固有道具的戒指一人只能有一个,多买只扣不给.\r\n";
			text += "\t#L66##k[#r金币200万#k]+[#v4170000##r#c4170000##k/10]兑换#d#fUI/Basic/BtHide3/mouseOver/0##i1112426:##l\r\n\r\n"
			text += "\t\t完成月妙副本，可以获得月妙蛋#l\r\n"
			text += "\t----------------------------------------------#l\r\n"
			text += "\t#L6##k[#r金币200万#k]+[#v4310020##r#c4310020##k/20]兑换#d#fUI/Basic/BtHide3/mouseOver/0##i1112422:##l\r\n\r\n"
			text += "\t\t通过藏宝图挖宝，可以获得宝藏币#l\r\n"
			text += "\t----------------------------------------------#l\r\n"
			text += "\t#L1##k[#r金币200万#k]+[#v4310108##r#c4310108##k/20]兑换#d#fUI/Basic/BtHide3/mouseOver/0##i1112425:##l\r\n\r\n"
			text += "\t\t通过怪物卡片集卡，可以获得集卡币#l\r\n"
						text += "\t----------------------------------------------#l\r\n"
			text += "\t#L666##k[#v1132004##r#c1132004##k/5]兑换#d#fUI/Basic/BtHide3/mouseOver/0##i1132115:##l\r\n\r\n"
			text += "\t\t完成武陵副本，可以获得腰带#l\r\n"
			text += "\t----------------------------------------------#l\r\n"
			//text += "\t#L67##k[#v4032392##r#c4032392##k/100]兑换#d#fUI/Basic/BtHide3/mouseOver/0##v2049118##l\r\n\r\n"//3
            //text += "\t#L99##k[#v4170002##r#c4170002##k/30]兑换#d#fUI/Basic/BtHide3/mouseOver/0##v1902001##l\r\n\r\n"//3
			//text += "\t#L100##k[#v4170005##r#c4170005##k/35]兑换#d#fUI/Basic/BtHide3/mouseOver/0##v1912000##l\r\n\r\n"//3
			//text += "\t#L1100##k[#v4001128##r#c4001128##k/100]兑换#d#fUI/Basic/BtHide3/mouseOver/0##v4001126##l50个\r\n\r\n"//3
            
			
			
			//text += "     #L68##b100枫叶兑换#d#fUI/Basic/BtHide3/mouseOver/0#10HP  (#r比例 100:10#d)#l\r\n\r\n"
			//text += "     #L69##b100枫叶兑换#d#fUI/Basic/BtHide3/mouseOver/0#10MP  (#r比例 100:10#d)#l\r\n\r\n"
            cm.sendSimple(text);
		}else if (selection == 48) {//游戏中心
				cm.dispose();
				cm.openNpc(9310072, 48);
        } else if (selection == 1) {
			if(cm.haveItem(1112425,1)){
				cm.sendOk("\t您已经有了这个戒指，一人只能有一个。");
				cm.dispose();
				return;
				}else{
			if(cm.getMeso() >= 2000000 && cm.haveItem(4310108,20) ){
				cm.gainItem(4310108,-20);
				cm.gainMeso(-2000000);
				cm.gainItem(1112425,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了一个金怀表戒指！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t金币或材料不足。");
				cm.dispose();
				}
				}
		}
		else if (selection == 666) {
			if(cm.haveItem(1132004,5) ){
				cm.gainItem(1132004,-5);
				cm.gainItem(1132115,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了一个黑腰带！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
		}else if (selection == 6) {
			if(cm.haveItem(1112422,1)){
				cm.sendOk("\t您已经有了炫色版戒指，一人只能有一个。");
				cm.dispose();
				return;
				}else{
			if(cm.getMeso() >= 2000000 && cm.haveItem(4310020,20) ){
				cm.gainItem(4310020,-20);
				cm.gainMeso(-2000000);
				cm.gainItem(1112422,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了一个炫色版戒指！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t金币或材料不足。");
				cm.dispose();
				}
				}
		}
		else if (selection == 66) {
			if(cm.haveItem(1112426,1)){
				cm.sendOk("\t您已经有了蒲公英戒指，一人只能有一个。");
				cm.dispose();
				return;
				}else{
			if (cm.getMeso() >= 2000000 && cm.haveItem(4170000,10)) { 
			    cm.gainItem(-4170000,10);
			    cm.gainMeso(-2000000);
				cm.gainItem(1112426,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过戒指兑换，获得了一个蒲公英戒指！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t金币或材料不足。");
				cm.dispose();
			}
			}	
				}
		
		
		else if (selection == 1100) {
			if(cm.haveItem(4001128,100) ){
				cm.gainItem(4001128,-100);
				cm.gainItem(4001126,50);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了50个枫叶！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
		}

		else if (selection == 5) {
			if(cm.haveItem(4170005,1) && cm.haveItem(4170013,1) && cm.haveItem(4170002,1)){
				cm.gainItem(4170005,-1);
				cm.gainItem(4170013,-1);
				cm.gainItem(4170002,-1);
				cm.gainItem(2340000,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『相框兑换』" + " : " + "[" + cm.getChar().getName() + "]通过团队任务收益，兑换了一张祝福卷轴！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
		}else if (selection == 6) {
			if(cm.haveItem(4170001,1) && cm.haveItem(4170004,1) && cm.haveItem(4170009,1) ){
				cm.gainItem(4170001,-1);
				cm.gainItem(4170004,-1);
				cm.gainItem(4170009,-1);
				cm.gainItem(2049116,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『相框兑换』" + " : " + "[" + cm.getChar().getName() + "]通过团队任务收益，兑换了一张正向混沌卷轴！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
		}else if (selection == 3) {
			
			if(cm.getPlayer().getMeso() >= 100 ){ //物品条件
				cm.getPlayer().modifyCSPoints(1,-100, true);//点券
				cm.gainItem(4001126,100);
				//cm.gainMeso(+18000000);//给金币
				
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]兑换了100个枫叶！")); 
		        cm.dispose();
			}else{
				cm.sendOk("\t点券不足。");
				cm.dispose();
			}
        }else if (selection == 2) { 
			/*if(cm.getPlayer().getBossLogD("金币兑换点券") > 4){
				cm.sendOk("\t今天已经兑换过5次.");
				cm.dispose();
				return;
			}*/
			if(cm.getPlayer().getMeso() >= 18000000 ){ //物品条件
				cm.getPlayer().modifyCSPoints(1,10000, true);//点券
				cm.gainMeso(-18000000);
				cm.getPlayer().setBossLog("金币兑换点券");
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『相框兑换』" + " : " + "[" + cm.getChar().getName() + "]努力搬砖兑换了10000点券！")); 
		        cm.dispose();
			}else{
				cm.sendOk("\t金币不足。");
				cm.dispose();
			}
        } else if (selection == 1) { 
			var zliang = cm.getPlayer().getItemQuantity(4001126, false);
			if (zliang == 0) {
                    cm.sendOk("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 1
					cm.sendYesNo("当前共有: #r"+zliang+"#k 个，是否把它们全部兑换吗？");
					}
        } else if (selection == 4) { 
			var zliang = cm.getPlayer().getItemQuantity(4001128, false);
			if (zliang == 0) {
                    cm.sendOk("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 4
					cm.sendYesNo("当前共有: #r"+zliang+"#k 个，是否把它们全部兑换吗？");
					}
        }  else if (status == 2) {
            if (beauty == 1) {
				var zliang = cm.getPlayer().getItemQuantity(4001126, false);
                if (zliang > 0){
					cm.removeAll(4001126);
					cm.gainMeso(8000*zliang);					
							
                    cm.sendOk("兑换成功。共兑换了:[#r"+(zliang)+"#k] 个。");
					cm.worldMessage(6,"[相框兑换]：玩家 "+cm.getName()+" 努力搬砖,在自由相框兑换了："+(zliang*8000)+" 金币。");
					cm.dispose();
                } else {
                    cm.sendOk("您的物品不足，无法兑换。");
                    cm.dispose()
                }            		
            }if (beauty == 4) {
				var zliang = cm.getPlayer().getItemQuantity(4001128, false);
                if (zliang > 0){
					cm.removeAll(4001128);  
					cm.getPlayer().modifyCSPoints(2,zliang*2, true);		
                    cm.sendOk("兑换成功。共兑换了:[#r"+(zliang)+"#k] 个。");
					cm.worldMessage(6,"[兑换中心]：玩家 "+cm.getName()+" 努力搬砖,在自由相框用炸药桶兑换了："+(zliang*2)+" 抵用卷。");//公告
					cm.dispose();
                } else {
                    cm.sendOk("您的物品不足，无法兑换。");
                    cm.dispose()
                }
            }
			if (beauty == 70) {
				var zliang = cm.getPlayer().getItemQuantity(4032226, false);
                if (zliang > 0){
					cm.removeAll(4032226);  
					cm.getPlayer().modifyCSPoints(1,zliang*1000, true);	//给点卷*数量，1为点卷，2为抵用卷	
                    cm.sendOk("兑换成功。共兑换了:[#r"+(zliang)+"#k] 个。");
					cm.worldMessage(6,"[兑换中心]：玩家 "+cm.getName()+" 努力搬砖,在自由相框用黄金猪猪兑换了："+(zliang*1000)+" 点卷。");//公告
					cm.dispose();
                } else {
                    cm.sendOk("您的物品不足，无法兑换。");
                    cm.dispose()
                }
            }
        }
    }
}