var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┣━━━━━━━━━━━┫";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┗━━━━━━━━━━━┛";

var 金币图标 = "#fUI/UIWindow.img/QuestIcon/7/0#";
var 蓝色鼠标 = "#d#fUI/Basic/BtHide3/mouseOver/0#";
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
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
			text += "          #k"+彩虹+" #r#e#w 金 币 银 行 #n#k "+彩虹+"\r\n\r\n";
			text += "  "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"\r\n\r\n";
            text += "  #d#e背包拥有\r\n  #r"+金币图标+": " + cm.getMeso() + "#k\r\n";
			text += "  #L66#"+蓝色鼠标+"#b储存10亿金币,获得#i4310012:##l\r\n"
			text += "  #L666#"+蓝色鼠标+"#b用黄金雪晶提取金币(每次收取0.02手续费)\r\n"
			
            cm.sendSimple(text);
		} 
            
			else if (selection == 666) {
			
			if (cm.getMeso() <= 1000000000 ) {
			
			if (cm.haveItem(4310012, 1) ) { 

			    cm.gainMeso(980000000);
				cm.gainItem(4310012,-1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『金币银行』" + " : " + "[" + cm.getChar().getName() + "]用黄金雪晶提取了10亿金币！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t亲,您身上没有黄金雪晶,不可以提取金币哦。");
				cm.dispose();
			}
			}else{
				cm.sendOk("\t亲,您身上拥有的金币不可以多于10亿才可以提取哦。");
				cm.dispose();
			}
				
				}
			
		    else if (selection == 66) {
			if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("背包其他栏空间不足1格");
			cm.dispose();
			return;
		    }else{
			if (cm.getMeso() >= 1000000000 ) { 
			    
			    cm.gainMeso(-1000000000);
				cm.gainItem(4310012,1);
				Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『金币银行』" + " : " + "[" + cm.getChar().getName() + "]储存了10亿金币，获得了一个黄金雪晶！")); 
				cm.dispose();
			}else{
				cm.sendOk("\t亲,您身上拥有的金币达不到储存要求哦。");
				cm.dispose();
			}
			}	
				}
		
		
		

		  else if (status == 2) {
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