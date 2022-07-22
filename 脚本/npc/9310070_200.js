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
	text += "#r翅膀二级进价需要以下物品:#n\r\n        #v1102184##d#z1102184# X 1 #v4000463# X 300\r\n        #v4000313# X 600 #v4001126# X 600 金币：2000万\r\n         "+奖励+"\r\n#v1102349##z1102349# 全属性+7 X 1"
	text += "\r\n#e#k#L1#"+正方箭头+"确定进价二级翅膀";//永久
	//text += "     \r\n"
        cm.sendSimple(text);
        } else if (selection == 1) {
                      if(!cm.canHold(1102349,1)){
			cm.sendOk("请清理你的背包，至少空出2个位置！");
            cm.dispose();
        } else if(cm.getMeso() < 20000000) {
            cm.sendOk("抱歉您的金币不足2000万，请凑足了再来！");
            cm.dispose();
        }
		else if(cm.haveItem(1102184,1) && cm.haveItem(4000463,300) && cm.haveItem(4000313,600) && cm.haveItem(4001126,600)){
				cm.gainItem(1102184, -1);
				cm.gainMeso(-20000000);
				cm.gainItem(4000313, -600);
				cm.gainItem(4000463, -300);
				cm.gainItem(4001126, -600);
            cm.给属性装备(1102349, 0, 0, 7, 7, 7, 7, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0);
            cm.sendOk("恭喜你成功进价为二级翅膀全属性+7，祝你游戏愉快。");
            cm.dispose();
cm.全服黄色喇叭("[翅膀进价] : 恭喜玩家 【"+cm.getPlayer().getName()+"】 成功进价二级翅膀全属性+7，未来的明日之星！")
 
			}else{
            cm.sendOk("进价翅膀材料或者不足#v1102184#X1#v4000463#X300#v4000313#X600#v4001126#X600\r\n");
            cm.dispose();
			}
		}
    }
}




