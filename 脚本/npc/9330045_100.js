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
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
	
	
	
            text += "\t\t"+彩虹+"  #e#d 钓 鱼 必 备 品 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n#b重要提示：购买鱼饵前务必清理背包，消费之后没有获得鱼饵概不补偿#k\r\n"//3
            text += "#L2##r#v3011000# 300万金币#l          #L3##r#v5340001# 88888点卷#l\r\n"
            text += "#L6##v2300000# 200万金币100个#l      #L12##v2300000# 1000万金币500个#l\r\n"
            text += "#L4##v2300001# 1万点卷100个#l       #L13##v2300001# 4万点卷500个#k#l\r\n\r\n"
			text += "#L14#      →→→→→  进入钓鱼场#k  ←←←←←  #l\r\n\r\n"

		   
		   
		   
		   
		   
		if (cm.getPlayer().getClient().getChannel() != 1) {
			cm.sendOk("只能在1线钓鱼哦！");
			cm.dispose();
			return;
		}
	
		   if (cm.getPlayer().getMapId()==741000200){
            cm.sendSimple(text);
		   }else{
			
		if (cm.getLevel() > 29){
			
            cm.sendSimple(text);
		}else{
			cm.sendOk("30级以上才可以参加哦。");
			cm.dispose();
		}
		   }
    
       } else if (status == 1) {
	if (sel == 0) {
		
	} else if (selection == 1) {
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
		    cm.gainItem(3011000, 1);
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
		if (cm.getPlayer().getCSPoints(1) >= 88888) {
		    cm.gainNX(-88888);	
		    cm.gainItem(5340001, 1);
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
		if (cm.canHold(2300001) && cm.getPlayer().getCSPoints(1) >= 10000) {
		    cm.gainNX(-10000);	
		    cm.gainItem(2300001, 100);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		} else {
		    cm.sendOk("请检查是否有所需的点卷或足够的背包空间。");
		    cm.dispose();
		}
		
		}else if (selection == 12) {
		if (cm.canHold(2300000) && cm.getMeso() >= 10000000) {
		    cm.gainMeso(-10000000);
		    cm.gainItem(2300000, 500);
		    cm.sendOk("祝你快乐钓鱼！");
		    cm.dispose();
		} else {
		    cm.sendOk("请检查是否有所需的金币或足够的背包空间。");
		    cm.dispose();
		}
	}else if (selection == 13) {
		if (cm.canHold(2300001) && cm.getPlayer().getCSPoints(1) >= 40000) {
		    cm.gainNX(-40000);	
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