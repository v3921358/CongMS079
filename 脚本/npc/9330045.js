/* ==================
 脚本类型: 在线奖励	    
 脚本作者：枫叶   
 联系方式：1848350048
 =====================
 */
var status = -1;
var sel;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }

    if (status == 0) {
	//cm.sendSimple("你想要做什么？\n\r #b#L0#进入钓鱼场#l \n\r #L1#买鱼饵#l \n\r #L2#买钓鱼椅#l \n\r #L3#用美味的饵可以#l \n\r #L4#钓鱼指南#l \n\r #L5##i1142146:#贸易500金蛋（渔王勋章[期：30天]）#l");
	
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
	
	
	
            text += "\t\t"+彩虹+"  #e#d 休 闲 钓 鱼 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n#e钓鱼请购买钓鱼椅#v3011000#钓鱼杆#v5340001#还有鱼饵#v2300000#\r\n#k\r\n"//3
            text += "#L100#    →→→→→  #r购买钓鱼具#k  ←←←←←#l#k\r\n\r\n"
            text += "#L14#    →→→→→  #r进入钓鱼场#k  ←←←←←  #l\r\n\r\n"
			text += "#L5# #v4031636##v4031640#  →  #r钓鱼兑换中心#k  ←  #v4031644##v4031648##l\r\n\r\n"//3
		   
  /*          text += "#v5021007##v5021007##v5021007##v5021007##v5021007##v5021007##v5021007##v5021007##v5021007#\r\n"//3
            text += "#v5021007#欢迎各位参加雪花冒险岛第一届#r钓鱼大赛#k！#v5021007#\r\n\r\n"//3
            text += "#L10##v3011000##v5340001##v2300001#领取。#l\r\n"//3
            text += "#L11##v4031627##v4031628##v4031630##v4031631#查询当前钓鱼积分#l\r\n"//3
			
	sl = cm.getPlayer().getItemQuantity(4031627, false);
            sl = cm.getPlayer().getItemQuantity(4031627, false);
            slA = cm.getPlayer().getItemQuantity(4031628, false);
            slB = cm.getPlayer().getItemQuantity(4031630, false);
            slC = cm.getPlayer().getItemQuantity(4031631, false);
            //slD = cm.getPlayer().getItemQuantity(4031632, false);
            slE = cm.getPlayer().getItemQuantity(4031633, false);
            slF = cm.getPlayer().getItemQuantity(4031634, false);
            slG = cm.getPlayer().getItemQuantity(4031635, false);
            slH = cm.getPlayer().getItemQuantity(4031636, false);
            slI = cm.getPlayer().getItemQuantity(4031637, false);
            slJ = cm.getPlayer().getItemQuantity(4031638, false);
            slK = cm.getPlayer().getItemQuantity(4031639, false);
            slL = cm.getPlayer().getItemQuantity(4031640, false);
            slM = cm.getPlayer().getItemQuantity(4031641, false);
            slN = cm.getPlayer().getItemQuantity(4031642, false);
            slO = cm.getPlayer().getItemQuantity(4031643, false);
            slP = cm.getPlayer().getItemQuantity(4031644, false);
            slQ = cm.getPlayer().getItemQuantity(4031645, false);
            slR = cm.getPlayer().getItemQuantity(4031646, false);
            slS = cm.getPlayer().getItemQuantity(4031647, false);
            slT = cm.getPlayer().getItemQuantity(4031648, false);
            //slU = cm.getPlayer().getItemQuantity(4031629, false);
			slD=0;
			slU=0;
			var jifen = sl + slA + slB + slC + slD + slE + slF + slG + slH + slI + slJ + slK + slL + slM + slN + slO + slP + slQ + slR + slS + slT + slU;
			cm.喇叭(1,"玩家：["+cm.getName()+"] "+jifen+" ！");
			
			
		   */
		   
		   
		   
		   
		   
		if (cm.getPlayer().getClient().getChannel() != 1) {
			cm.sendOk("只能在1线钓鱼哦！");
			cm.dispose();
			return;
		}
	
		   if (cm.getPlayer().getMapId()==741000200){
            cm.sendSimple(text);
		   }else{
			//cm.sendOk("等GM通知。");
			//cm.dispose();
				//cm.喇叭(3,"玩家："+cm.getName()+" a。");
				  
		if (cm.getLevel() > 29){
			//cm.givePartyExp(651111100);//给队伍所有人经验
            cm.sendSimple(text);
		}else{
			cm.sendOk("30级以上才可以参加哦。");
			cm.dispose();
		}
           // cm.sendSimple(text);
		   }
    //cm.sendSimple("你好！我是渔场管理员。\n\r如果你想要进行钓鱼，请购买钓鱼竿分#v5340001#/#v5340000#两种，高级10秒钓一次鱼，普通的是20秒一次，好了介绍这么多，多多搜集兑换丰厚奖励吧！\n\r #b#L0# #v4000411#  进入钓鱼场.#l \n\r #L2##v3011000#  1000万金币购买钓鱼椅.#l \n\r #L3##v5350000# 购买高级鱼饵100个1000W#l \n\r #L4##v4161001#   钓鱼指南.#l \n\r #L6##v4031630#   兑换钓鱼积分.#l \n\r #L5##v4001200#   小鱼抽奖处.#l");
       } else if (status == 1) {
	if (sel == 0) {
		
	} else if (selection == 1) {
			//cm.warp(741000200, 0);
			cm.warp(910000000, 0);
		    cm.dispose();
			
		} else if (selection == 14) {
			cm.warp(741000200, 0);
		    cm.dispose();
	
	} else if (selection == 10) {
     if (cm.getBossLog('钓鱼大赛') == 1) {
		    cm.sendOk("只能领取一次！");
		    cm.dispose();
		 } else {
		if (cm.haveItem(3011000)) {
		cm.sendOk("你已经有一把钓鱼椅。每个角色只能有1个钓鱼椅。");
	    } else {
		    cm.gainItem(3011000, 1, 1);
		    cm.gainItem(5340001, 1, 1);
		    cm.gainItem(2300001, 100, 1);
			cm.setBossLog('钓鱼大赛');
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		}
		}
	} else if (selection == 11) {
		    cm.sendOk("稍等");
		    cm.dispose();
	} else if (selection == 2) {
	    if (cm.haveItem(3011000)) {
		cm.sendOk("你已经有一把钓鱼椅。每个角色只能有1个钓鱼椅。");
	    } else {
		  if (cm.getLevel() > 49){
				if (cm.canHold(3011000) && cm.getMeso() >= 3000000) {
		    cm.gainMeso(-3000000);
		    cm.gainItem(3011000, 1, 12);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
				} else {
		    cm.sendOk("请检查是否有所需的金币或足够的背包空间。");
		    cm.dispose();
				}
		  }else{
		    cm.sendOk("最少要50级才可以来钓鱼！");
		    cm.dispose();
		  }	
	    }
	}else if (selection == 3) {
	    if (cm.haveItem(5340001)) {
		cm.sendOk("你已经有一把高级鱼竿了。");
		cm.dispose();
	    } else {
		if (cm.getPlayer().getCSPoints(1) >= 500) {//判断所需点卷是否足够500
		    cm.gainNX(-500);	//加减点券
		    cm.gainItem(5340001, 1,12);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		} else {
		    cm.sendOk("请检查是否有所需的点卷或足够的背包空间。");
		    cm.dispose();
		}
	    }
	}else if (selection == 6) {
		if (cm.canHold(2300001) && cm.getMeso() >= 2000000) {
		    cm.gainMeso(-2000000);
		    cm.gainItem(2300000, 100);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		} else {
		    cm.sendOk("请检查是否有所需的金币或足够的背包空间。");
		    cm.dispose();
		}
	}else if (selection == 4) {
		if (cm.getPlayer().getCSPoints(1) >= 1000) {
		    cm.gainNX(-1000);	//加减点券
		    cm.gainItem(2300001, 100);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		} else {
		    cm.sendOk("请检查是否有所需的点卷或足够的背包空间。");
		    cm.dispose();
		}
		
		}else if (selection == 12) {
		if (cm.canHold(2300001) && cm.getMeso() >= 10000000) {
		    cm.gainMeso(-10000000);
		    cm.gainItem(2300000, 500);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		} else {
		    cm.sendOk("请检查是否有所需的金币或足够的背包空间。");
		    cm.dispose();
		}
	}else if (selection == 13) {
		if (cm.getPlayer().getCSPoints(1) >= 4000) {
		    cm.gainNX(-4000);	//加减点券
		    cm.gainItem(2300001, 500);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		} else {
		    cm.sendOk("请检查是否有所需的点卷或足够的背包空间。");
		    cm.dispose();
		}
		
	}else if (selection == 5) {
		cm.dispose();
		cm.openNpc(9330045, 5);
	}else if (selection == 100) {
		cm.dispose();
		cm.openNpc(9330045, 100);
	}
    } else if (status == 2) {
	if (sel == 1) {
	    if (cm.canHold(2300001,120) && cm.getMeso() >= 300000) {
		if (!cm.haveItem(2300001)) {
		    cm.gainMeso(-300000);
		    cm.gainItem(2300001, 120);
		    cm.sendNext("快乐钓鱼~");
		} else {
		    cm.sendNext("你已经有了钓鱼的诱饵。");
		}
	    } else {
		cm.sendOk("请检查是否有所需的300000金币或足够的背包空间。");
	    }
	    cm.safeDispose();
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
var 金币图标 = "#fUI/UIWindow.img/QuestIcon/7/0#";