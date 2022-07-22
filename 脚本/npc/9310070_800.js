/*
 * 
 * @QILIN
 * @npc翅膀进价+2级
 */
 var 奖励 = "#fUI/CashShop/CSDiscount/bonus#";
 var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
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
                
   cm.sendOk("感谢使用.");
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
	for(i = 0; i < 10; i++){
		text += "";
	}				
	text += "#r进价终极翅膀需要以下物品:#n\r\n#v1103026##d#z1103026# X 1 #v4000463##d#z4000463# X 5000\r\n        #v4000313##d#z4000313# X 8000 #v4001126##d#z4001126# X 8000\r\n        #v4000038##z4000038# X 2000 #v4310012##z4310012# X 5\r\n                    金币：8000万\r\n         "+奖励+"\r\n#v1103027##z1103027# 全属性+25,功魔+25, X 1"
	text += "\r\n#e#k#L1#"+正方箭头+"确定进价八级翅膀";//永久
	//text += "     \r\n"
        cm.sendSimple(text);
        } else if (selection == 1) {
                      if(!cm.canHold(1103027,1)){
			cm.sendOk("请清理你的背包，至少空出2个位置！");
            cm.dispose();
        }
else if(cm.getMeso() < 80000000) {
            cm.sendOk("抱歉您的金币不足8000万，请凑足了再来！");
            cm.dispose();
        }		else if(cm.haveItem(1103026,1) && cm.haveItem(4310012,5) && cm.haveItem(4000463,5000) && cm.haveItem(4000313,8000) && cm.haveItem(4001126,8000) && cm.haveItem(4000038,2000)){
				cm.gainItem(1103026, -1);
				cm.gainItem(4310012, -5);
				cm.gainItem(4000313, -8000);
				cm.gainItem(4000463, -5000);
				cm.gainMeso(-80000000);
				cm.gainItem(4001126, -8000);
				cm.gainItem(4000038, -2000);
            cm.给属性装备(1103027, 0, 0, 25, 25, 25, 25, 0, 0, 25, 25,0, 0, 0, 0, 0, 0, 0);
            cm.sendOk("恭喜你成功进价为八级翅膀全属性+25，功魔+25,祝你游戏愉快。");
            cm.dispose();
cm.全服黄色喇叭("[翅膀进价] : 恭喜玩家 【"+cm.getPlayer().getName()+"】 成功进价八级翅膀全属性+25，功魔+25,未来的明日之星！")
 
			}else{
            cm.sendOk("进价翅膀材料不足或者金币不足\r\n");
            cm.dispose();
			}
		}
    }
}




