



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
			text += "                #k"+皇冠白+" #r#e#w 领 取 工 资 #n#k "+皇冠白+"\r\n\r\n";
			text += "  "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"\r\n";
			
            text += "哈喽！只要您拥有工资卡，就可以在我这里每天领取物资工资\r\n\r\n";
			text += "#b每天都来找我领取一次，有丰富的物资给到您，游戏快人一步#k\r\n\r\n";
			text += "#d包含：金杯，金币，枫叶，纪念币，怪物之心，玩具和天空蛋#k\r\n\r\n";
			text += "#d赠送：黑暗龙王BOSS和品克缤BOSS每天挑战次数增加至10次！#k\r\n\r\n\r\n";
			
			text += "\t\t  #L1##r● ● ● 点击领取 ● ● ●#l\r\n\r\n"
		
            cm.sendSimple(text);
		} 
		else if (selection == 1) { 
			if(cm.getBossLog("工资") >= 1){
				cm.sendOk("\t今天已经领过工资了哟！请明天再来.");
				cm.dispose();
				return;
			}
			if (!cm.checkNumSpace(4, 6)) {
			cm.sendOk("背包其他栏空间不足6格，请预留好空余位置，我才可以给您物资哟！");
			cm.dispose();
			return;
		    }
			var zliang = cm.getPlayer().getItemQuantity(3700067, false);
			if (zliang == 0) {
                    cm.sendOk("你的没有工资卡，领取不到工资哟！");
                    cm.dispose();
				    return;
                } else {
					cm.getPlayer().setBossLog('工资');
					
			        cm.gainMeso(1000000);//金币100万
					cm.gainItem(4001126,10);//枫叶
				    cm.gainItem(4170006,5);//天空蛋
					cm.gainItem(4170005,5);//玩具蛋
					cm.gainItem(4001230,5);//怪物之心（绿）
					cm.gainItem(4000463,2);//国庆纪念币
					cm.gainItem(4000038,2);//金杯
				    Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『塔纳』" + " : " + "[" + cm.getChar().getName() + "]通过领取工资，获得了丰厚的物资！")); 
				    cm.dispose();
					return;
					}
        } 
		
		  
    }
}