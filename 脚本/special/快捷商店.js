/* ==================
 脚本类型: 在线奖励	    
 脚本作者：枫叶   
 联系方式：1848350048
 =====================
 */
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
   cm.sendSimple("\t\t"+彩虹+"  #e#d 快 捷 商 店#k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n#e您好，我是快捷商店售货员！请问您需要购买些什么呢？\r\n#d背包剩余金币:#b" + cm.getMeso() + "\r\n\r\n#r#L1#"+红色箭头+"杂货小铺#l    #L4#"+红色箭头+"冲级武器#l     #L6#"+红色箭头+"冲级防具#l\r\n#k#l#l ");//#L5#"+红色箭头+"卷轴专卖#l
    } else if (status == 1) {
           if (selection == 0) {
      cm.sendOk("#e游戏中的金币完全可以靠努力自己赚取，如有问题可在QQ群交流");
            cm.dispose();
    }else if  (selection == 1) {
           //cm.openShop(20);
		   cm.openShop(43);
		       cm.dispose();
    }else if  (selection == 4) {
           cm.openShop(74);
		       cm.dispose();
    }else if  (selection == 6) {
           cm.openShop(63);
		       cm.dispose(); 
    }else if  (selection == 2) {
           cm.openShop(109);
		       cm.dispose(); 
    }else if  (selection == 5) {
           cm.openShop(77);
		       cm.dispose();
    }else if  (selection == 3) {
           cm.openShop(104);
		       cm.dispose();
    }else if  (selection == 7) {
           cm.openShop(110);
		       cm.dispose(); 
    }else if  (selection == 8) {
           cm.openShop(111);
		       cm.dispose();
    }else if  (selection == 9) {
           cm.openShop(93);
		       cm.dispose();
    }else if  (selection == 10) {
           cm.openShop(66);
                       cm.dispose();
    }else if  (selection == 11) {
           cm.openShop(109);
    }else if  (selection == 12) {
           cm.openNpc(108);
    }else if  (selection == 13) {
           cm.openShop(103);
      

}
}
}
}

var acc = "#fEffect/CharacterEff/1112903/0/0#";//红桃心
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//红色右箭头
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//选择道具
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
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
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 草莓 = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // 红色草莓
var 草莓1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // 淡蓝色草莓
var 草莓2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // 紫色草莓
var 草莓3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // 白色草莓
var 草莓4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // 黄色草莓
var 草莓5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // 绿色草莓
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 大黄星 = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var 小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】
var 小水滴 = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var 大水滴 = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var 红爱心 ="#fEffect/CharacterEff/1112905/0/1#";
var 金币图标 = "#fUI/UIWindow.img/QuestIcon/7/0#";
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";