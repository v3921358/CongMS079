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
			text += "                  #k"+皇冠白+" #r#e#w 六 一 活 动 #n#k "+皇冠白+"\r\n\r\n";
			text += "  "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"\r\n";
            text += "        #d童年很短，未来很远 你快乐也好，淘气也罢\r\n";
			text += "             让我们一起来收集小时候的玩具吧#k\r\n\r\n";
			text += "\t   [#v4000109##r#c4000109##k/601]   [#v4000476##r#c4000476##k/601]   [#v4000101##r#c4000101##k/601]\r\n\r\n"
			text += "\t         [#v4000127##r#c4000127##k/601]      [#v4000100##r#c4000100##k/6]\r\n"
            
			text += "\t  #L66##k领取[#i1102354:#]#l   #L666##k进阶[#v1102354#]为全属性攻击40#l\r\n\r\n"
			text += "\t            活动持续时间到6月3号结束\r\n"
            cm.sendSimple(text);
		}
		
		else if (selection == 66) {
			if(cm.haveItem(1102354,3)){
				cm.sendOk("\t您已经有了六一披风了，一人只能有三个。");
				cm.dispose();
				return;
				}else{
			if(!cm.haveItem(4000109,601) ){
			cm.sendOk("\t#v4000109#不足601个，快去收集哟。");
			cm.dispose();
			return;
			}
			if(!cm.haveItem(4000476,601) ){
			cm.sendOk("\t#v4000476#不足601个，快去收集哟。");
			cm.dispose();
			return;
			}
			if(!cm.haveItem(4000101,601) ){
			cm.sendOk("\t#v4000101#不足601个，快去收集哟。");
			cm.dispose();
			return;
			}
			if(!cm.haveItem(4000127,601) ){
			cm.sendOk("\t#v4000127#不足601个，快去收集哟。");
			cm.dispose();
			return;
			}
			if(!cm.haveItem(4000100,6) ){
			cm.sendOk("\t#v4000100#不足6个，快去收集哟，加油哟。");
			cm.dispose();
			return;
			}
			if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("背包装备栏空间不足1格");
			cm.dispose();
			return;
		    }
			cm.gainItem(4000109,-601);
			cm.gainItem(4000476,-601);
			cm.gainItem(4000101,-601);
			cm.gainItem(4000127,-601);
			cm.gainItem(4000100,-6);
			cm.gainItem(1102354,1);
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『六一活动』" + " : " + "[" + cm.getChar().getName() + "]通过收集全玩具，获得了绝版六一披风！")); 
			cm.dispose();
			}	
			
			}else if (selection == 666) {
			if(!cm.haveItem(1102354,3)){
				cm.sendOk("\t您六一绝版披风不足三个，请去凑齐，再来进阶。");
				cm.dispose();
				return;
				}else{
			
			if (!cm.checkNumSpace(1, 1)) {
			cm.sendOk("背包装备栏空间不足1格");
			cm.dispose();
			return;
		    }
			cm.gainItem(1102354,-3);
			cm.gainItem(1102354, 40, 40, 40, 40, 0, 0, 40, 40, 0, 0, 0, 0, 0, 0);
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『六一活动』" + " : " + "[" + cm.getChar().getName() + "]通过收集绝版六一披风，获得了进阶绝版六一披风！")); 
			cm.dispose();
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
            }
			
        }
    }
}