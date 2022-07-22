/*麒麟端作者qq1500663066或327321366*/



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
			text += "                  #k"+皇冠白+" #r#e#w 饰 品 升 星 #n#k "+皇冠白+"\r\n\r\n";
			text += "  "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"\r\n";
            
            text += "#L48##b#v1112426#【升星】       #L81##b#v1112915#【升星】\r\n\r\n"
			//text += "#L8##b#v1112422#【升星】       \r\n\r\n"
			text += "#L4888##b#v1132115#【升星】       #L488##b#v1012171#【升星】\r\n\r\n"
            cm.sendSimple(text);
		}else if (selection == 81) {
				cm.dispose();
				cm.openNpc(9900004, "蓝调升星");
        }
		else if (selection == 8) {
				cm.dispose();
				cm.openNpc(9900004, "炫色板升星");
        }
		else if (selection == 48) {
				cm.dispose();
				cm.openNpc(9900004, "蒲公英升星");
        } else if (selection == 488) {
				cm.dispose();
				cm.openNpc(9900004, "鬼娃面具");
        }else if (selection == 4888) {
				cm.dispose();
				cm.openNpc(9900004, "腰带升星");
        }
		else if (selection == 69) {
			var PlayselfMaxMp = cm.getPlayer().getMaxMp();
			if(cm.haveItem(4001126,100) ){
				cm.gainItem(4001126,-100);
				cm.getPlayer().getStat().setMaxMp(cm.getPlayer().getStat().getMaxMp()+ 10);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了10MP！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
		}
		else if (selection == 67) {
			if(cm.haveItem(4032392,100) ){
				cm.gainItem(4032392,-100);
				cm.gainItem(2049117,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了一张混沌卷轴！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
		}
		else if (selection == 66) {
			if(cm.haveItem(4032391,100) ){
				cm.gainItem(4032391,-100);
				cm.gainItem(2340000,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了一张祝福卷轴！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
		}
		else if (selection == 99) {
			if(cm.haveItem(4170002,30) ){
				cm.gainItem(4170002,-30);
				cm.gainItem(1902001,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了一张混沌卷轴！")); 
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