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
	text += "#r翅膀三级进价需要以下物品:#n\r\n        #v1102349##d#z1102349# X 1 #v4000463# X 600\r\n        #v4000313# X 1000 #v4001126# X 1000 金币3000万\r\n         "+奖励+"\r\n#v1102604##z1102604# 全属性+9 X 1"
	text += "\r\n#e#k#L1#"+正方箭头+"确定进价三级翅膀";//永久
	//text += "     \r\n"
        cm.sendSimple(text);
        } else if (selection == 1) {
                      if(!cm.canHold(1102604,1)){
			cm.sendOk("请清理你的背包，至少空出2个位置！");
            cm.dispose();
        } 
		else if(cm.getMeso() < 30000000) {
            cm.sendOk("抱歉您的金币不足3000万，请凑足了再来！");
            cm.dispose();
        }else if(cm.haveItem(1102349,1) && cm.haveItem(4000463,600) && cm.haveItem(4000313,1000) && cm.haveItem(4001126,1000)){
				cm.gainItem(1102349, -1);
				cm.gainItem(4000313, -1000);
				cm.gainMeso(-30000000);
				cm.gainItem(4000463, -600);
				cm.gainItem(4001126, -1000);
            cm.给属性装备(1102604, 0, 0, 9, 9, 9, 9, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0);
            cm.sendOk("恭喜你成功进价为三级翅膀全属性+9，祝你游戏愉快。");
            cm.dispose();
cm.全服黄色喇叭("[翅膀进价] : 恭喜玩家 【"+cm.getPlayer().getName()+"】 成功进价三级翅膀全属性+9，未来的明日之星！")
 
			}else{
            cm.sendOk("进价翅膀材料或者不足#v1102349#X1#v4000463#X600#v4000313#X1000#v4001126#X1000\r\n");
            cm.dispose();
			}
		}
    }
}




