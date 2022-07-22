/*
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
*/
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 正方形 = "#fUI/UIWindow/Quest/icon3/6#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 正在进行中 = "#fUI/UIWindow/Quest/Tab/enabled/1#";
var 完成 = "#fUI/UIWindow/Quest/Tab/enabled/2#";
var 正在进行中蓝 = "#fUI/UIWindow/MonsterCarnival/icon1#";
var 完成红 = "#fUI/UIWindow/MonsterCarnival/icon0#";
var 礼包物品 = "#v1302000#";
var x1 = "1302000,+1";// 物品ID,数量
var x2;
var x3;
var x4;
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 美化new = "#fUI/UIWindow/Quest/icon2/7#";
var 美化ne = "#fUI/UIWindow/Quest/icon6/7#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
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
        cm.sendOk("感谢你的光临！");
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
		text = "                   #k"+皇冠白+" #r#e#w" + cm.getServerName() + "#n#k "+皇冠白+"\r\n\r\n";
		text += ""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\r\n";
        text += "#e     #r全免费,#d只要肯花时间,那你就是最靓的崽/妹#n\r\n\r\n"      
	    text += "\t\t\#b#b#L1#"+正方箭头+"[美发师01]#l\t\t\#b#b#L2#"+正方箭头+"[美发师02]#l\r\n"
		text += "\t\t\#b#b#L3#"+正方箭头+"[美发师03]#l\t\t\#b#b#L4#"+正方箭头+"[美发师04]#l\r\n"
		text += "\t\t\#b#b#L5#"+正方箭头+"[美发师05]#l\t\t\#b#b#L6#"+正方箭头+"[美发师06]#l\r\n"
		text += "\t\t\#b#b#L9#"+正方箭头+"[美发师07]#l\t\t\#b#b#L10#"+正方箭头+"[改变发色]#l\r\n"
		text += "\t\t\#b#b#L7#"+正方箭头+"[整 形 师]#l\t\t\#L8##b"+正方箭头+"[改变眼色]#l\r\n"
        text += "\t\t\#b#b#L11#"+正方箭头+"[改变肤色]#l\r\n"
		if(cm.getPlayer().getGMLevel() > 1){
			//text += "#d\r\n以下是GM特权>>>>>>>>>>正在添加\r\n"
			//text += "#L9991#满技能#l\r\n"
		}
        cm.sendSimple(text);
      }  else if (selection == 1) { //快速传送
		cm.dispose();
		cm.openNpc(9310073, 1);
        //cm.openNpc(2012007, 0);
      } else if (selection == 2) { //药水商店
        cm.dispose();
		cm.openNpc(9310073, 2);
      } else if (selection == 3) { //专职
        cm.dispose();
		cm.openNpc(9310073, 3);
      }  else if (selection == 4) {//新手武器
        cm.dispose();
		cm.openNpc(9310073, 4);
      }  else if (selection == 5) { //快速更换脸型No传送
        cm.dispose();
		cm.openNpc(9310073, 5);
      } else if (selection == 6) { //更换脸型No
        cm.dispose();
		cm.openNpc(9310073, 6);
      } else if (selection == 7) { //专职
        cm.dispose();
		cm.openNpc(1052004, 0);
      }  else if (selection == 8) {//新手武器
        cm.dispose();
		cm.openNpc(1052005, 0);
      }  else if (selection == 9) {//新手武器
        cm.dispose();
		cm.openNpc(9310073, 9);
      }  else if (selection == 10) {//新手武器
        cm.dispose();
		cm.openNpc(9310073, 10);
      } else if (selection == 11) {//新手武器
        cm.dispose();
		cm.openNpc(9310073, 11);
      }else if (selection == 9991){
		cm.dispose();
		cm.openNpc(2081400, 0);
	  }
   }
}