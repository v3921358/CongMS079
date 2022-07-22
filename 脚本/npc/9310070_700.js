var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┣━━━━━━━━━━━┫";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┗━━━━━━━━━━━┛";
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
	text += "#r翅膀七级进价需要以下物品:#n\r\n        #v1103029##d#z1103029# X 1 #v4000463##d#z4000463# X 3000\r\n        #v4000313##d#z4000313# X 5000 #v4001126##d#z4001126# X 5000\r\n        #v4000038##z4000038# X 1000  #v4310012##z4310012# X 2\r\n                   金币：8000万\r\n         "+奖励+"\r\n#v1103026##z1103026# 全属性+20 功魔+20,X 1"
	text += "\r\n#e#k#L1#"+正方箭头+"确定进价七级翅膀";//永久
	//text += "     \r\n"
        cm.sendSimple(text);
        } else if (selection == 1) {
                      if(!cm.canHold(1103026,1)){
			cm.sendOk("请清理你的背包，至少空出2个位置！");
            cm.dispose();
        }
else if(cm.getMeso() < 80000000) {
            cm.sendOk("抱歉您的金币不足8000万，请凑足了再来！");
            cm.dispose();
        }		else if(cm.haveItem(1103029,1) && cm.haveItem(4310012,2) && cm.haveItem(4000463,3000) && cm.haveItem(4000313,5000) && cm.haveItem(4001126,5000) && cm.haveItem(4000038,1000)){
				cm.gainItem(1103029, -1);
				cm.gainItem(4310012, -2);
				cm.gainItem(4000313, -5000);
				cm.gainItem(4000463, -3000);
				cm.gainMeso(-80000000);
				cm.gainItem(4001126, -5000);
				cm.gainItem(4000038, -1000);
            cm.给属性装备(1103026, 0, 0, 20, 20, 20, 20, 0, 0, 20, 20,0, 0, 0, 0, 0, 0, 0);
            cm.sendOk("恭喜你成功进价为七级翅膀全属性+20，功魔+20,祝你游戏愉快。");
            cm.dispose();
cm.全服黄色喇叭("[翅膀进价] : 恭喜玩家 【"+cm.getPlayer().getName()+"】 成功进价七级翅膀全属性+20，功魔+20,未来的明日之星！")
 
			}else{
            cm.sendOk("进价翅膀材料或者金币不足,请检查好背包是否集齐所需材料和金币!\r\n");
            cm.dispose();
			}
		}
    }
}




