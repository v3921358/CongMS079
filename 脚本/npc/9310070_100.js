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
	text += "#r翅膀一级进价需要以下物品:#n\r\n        #v1102005##d#z1102005# X 1  注：商场可以直接购买获得\r\n        #v4000463# X 100  注：任何怪物都有小机率爆\r\n        #v4000313# X 300  注：任何怪物都有机率爆\r\n        #v4001126# X 300  注：任何怪物都有大机率爆\r\n        金币：1000万\r\n        "+奖励+"\r\n#v1102184##z1102184# 全属性+5 X 1"
	text += "\r\n#e#k#L1#"+正方箭头+"确定进价一级翅膀";//永久
	//text += "     \r\n"
        cm.sendSimple(text);
        } else if (selection == 1) {
                      if(!cm.canHold(1102184,1)){
			cm.sendOk("请清理你的背包，至少空出2个位置！");
            cm.dispose();
        } 
		else if(cm.getMeso() < 10000000) {
            cm.sendOk("抱歉您的金币不足1000万，请凑足了再来！");
            cm.dispose();
        }
		else if(cm.haveItem(1102005,1) && cm.haveItem(4000463,100) && cm.haveItem(4000313,300) && cm.haveItem(4001126,300)){
				cm.gainItem(1102005, -1);
				cm.gainItem(4000313, -300);
				cm.gainMeso(-10000000);
				cm.gainItem(4000463, -100);
				cm.gainItem(4001126, -300);
            cm.给属性装备(1102184, 0, 0, 5, 5, 5, 5, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0);
            cm.sendOk("恭喜你成功进价为一级翅膀全属性+5，祝你游戏愉快。");
            cm.dispose();
cm.全服黄色喇叭("[翅膀进价] : 恭喜玩家 【"+cm.getPlayer().getName()+"】 成功进价一级翅膀全属性+5，未来的明日之星！")
 
			}else{
            cm.sendOk("进价翅膀材料或者不足#v1102005#X1#v4000463#X100#v4000313#X300#v4001126#X300\r\n");
            cm.dispose();
			}
		}
    }
}




