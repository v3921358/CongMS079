/*麒麟端作者qq1500663066或327321366*/

var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
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
			text += "                  #k"+皇冠白+" #r#e#w金 币 洗 血 #n#k "+皇冠白+"\r\n\r\n";
			text += "  "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"\r\n";
            
			//text += "     #L68##b500枫叶兑换#d#fUI/Basic/BtHide3/mouseOver/0#10HP  (#r比例 500:10#d)#l\r\n\r\n"
			text += "     #L688#"+红色箭头+"洗血中心#l            #L6888#"+zzz+"洗蓝中心#l\r\n\r\n"
			//text += "     #L6888#"+zzz+"洗蓝中心#l\r\n\r\n"
			//text += "     #L68888##b50000枫叶兑换#d#fUI/Basic/BtHide3/mouseOver/0#1000HP  (#r比例 50000:1000#d)#l\r\n\r\n"
			
			//text += "     #L69##b500枫叶兑换#d#fUI/Basic/BtHide3/mouseOver/0#10MP  (#r比例 500:10#d)#l\r\n\r\n"
			//text += "     #L699##b2500枫叶兑换#d#fUI/Basic/BtHide3/mouseOver/0#50MP  (#r比例 2500:50#d)#l\r\n\r\n"
			//text += "     #L6999##b5000枫叶兑换#d#fUI/Basic/BtHide3/mouseOver/0#100MP  (#r比例 50000:100#d)#l\r\n\r\n"
			//text += "     #L69999##b10000枫叶兑换#d#fUI/Basic/BtHide3/mouseOver/0#1000MP  (#r比例 10000:1000#d)#l\r\n\r\n"
            cm.sendSimple(text);
		}else if (selection == 48) {//游戏中心
				cm.dispose();
				cm.openNpc(3003302, 48);
        } 
		else if (selection == 70) { 
			var zliang = cm.getPlayer().getItemQuantity(4032226, false);
			if (zliang == 0) {
                    cm.sendOk("你的物品不足兑换.");
                    status = -1;
                } else {
                    beauty = 70
					cm.sendYesNo("当前共有: #r"+zliang+"#k 个，是否把它们全部兑换吗？");
					}
        }
		else if (selection == 68) {
			var PlayselfMaxHp = cm.getPlayer().getMaxHp();
			if(cm.haveItem(4001126,500) ){
				cm.gainItem(4001126,-500);
				cm.getPlayer().getStat().setMaxHp(cm.getPlayer().getStat().getMaxHp()+ 10);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了10HP！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
		}else if (selection == 688) {//游戏中心
				cm.dispose();
				cm.openNpc(3003302, "洗血脚本");
        } 
		else if (selection == 6888) {//游戏中心
				cm.dispose();
				cm.openNpc(3003302, "洗蓝脚本");
        } 
		else if (selection == 68888) {
			var PlayselfMaxHp = cm.getPlayer().getMaxHp();
			if(cm.haveItem(4001126,50000) ){
				cm.gainItem(4001126,-50000);
				cm.getPlayer().getStat().setMaxHp(cm.getPlayer().getStat().getMaxHp()+ 1000);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了1000HP！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
		}
		else if (selection == 69) {
			var PlayselfMaxMp = cm.getPlayer().getMaxMp();
			if(cm.haveItem(4001126,500) ){
				cm.gainItem(4001126,-500);
				cm.getPlayer().getStat().setMaxMp(cm.getPlayer().getStat().getMaxMp()+ 10);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了10MP！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
		}else if (selection == 699) {
			var PlayselfMaxMp = cm.getPlayer().getMaxMp();
			if(cm.haveItem(4001126,2500) ){
				cm.gainItem(4001126,-2500);
				cm.getPlayer().getStat().setMaxMp(cm.getPlayer().getStat().getMaxMp()+ 50);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了50MP！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
		}else if (selection == 6999) {
			var PlayselfMaxMp = cm.getPlayer().getMaxMp();
			if(cm.haveItem(4001126,5000) ){
				cm.gainItem(4001126,-5000);
				cm.getPlayer().getStat().setMaxMp(cm.getPlayer().getStat().getMaxMp()+ 100);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了100MP！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t材料不足。");
				cm.dispose();
			}
		}
		else if (selection == 69999) {
			var PlayselfMaxMp = cm.getPlayer().getMaxMp();
			if(cm.haveItem(4001126,10000) ){
				cm.gainItem(4001126,-10000);
				cm.getPlayer().getStat().setMaxMp(cm.getPlayer().getStat().getMaxMp()+ 1000);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『兑换中心』" + " : " + "[" + cm.getChar().getName() + "]通过兑换物品，获得了1000MP！")); 
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