 

var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";

//------------------------------------------------------------------------

var chosenMap = -1;
var monsters = 0;
var towns = 0;
var bosses = 0;
var fuben = 0;

//------------------------------------------------------------------------

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
var 大黄星 = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var 小水滴 = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var 大水滴 = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var 红爱心 ="#fEffect/CharacterEff/1112905/0/1#";

//---------------------------------------------------------------------------
function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.sendOk("#b好的,下次再见.");
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.sendOk("#b好的,下次再见.");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }

//------------------------------------------------------------------------

        if (status == 0) {

            var add =  " \t\t  #d#e#v4030001#  欢迎来到  #r" + cm.getServerName() + "  #e#b#v4030001##b#k#n\r\r\n";
			 //add +="\t#d#e当前点卷：#r" +cm.getPlayer().getCSPoints(1) +  "#k点  #d当前抵用卷：#r" +cm.getPlayer().getCSPoints(2) +  "#k点#b#k#n\r\n";
			 //add +="\t#d#e当前金币：#r" +cm.getPlayer().getMeso() +  "#k金币 #d#e在线时间：#r" + cm.getGamePoints() + "#k分钟\r\n";
			 //add += ""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n";
			 	add += ""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+"\r\n";

			 //add += ""+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+小黄星+"\r\n";
                         //add +="\t#d#e您今天已在线了：#r" + cm.getGamePoints() + "#k分钟,请注意休息哦~!\r\n";
                         add +="#L99#"+小水滴+"#e#b个人信息#l#L0#"+小水滴+"自由市场#l#L1#"+小水滴+"万能传送#l#L2#"+小水滴+"怪物爆率#l\r\n";	
                         add +="#L5#"+小水滴+"#b快捷商店#l#L7#"+小水滴+"装备强化#l#L8#"+小水滴+"师徒系统#l#L4#"+小水滴+"综合排行#l\r\n";
                         add +="#L9#"+小水滴+"#b每日任务#l#L10#"+小水滴+"推广系统#l#L14#"+小水滴+"BOSS召唤#l#L15#"+小水滴+"勋章强化#l\r\n\r\n";
                         add +="#L16#"+小水滴+"#b在线奖励#l#L17#"+小水滴+"新人礼包#l#L18#"+小水滴+"背包清理#l#L19#"+小水滴+"每日签到#l\r\n";
						 add +="#L20#"+小水滴+"#b等级奖励#l#L21#"+小水滴+"合成中心#l#L22#"+小水滴+"兑换中心#l#L23#"+小水滴+"装备租借#l\r\n";
						 add +="#L3#"+小水滴+"#b快速洗血#l\r\n";
						 
			add += "\r\n"+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+""+红爱心+"\r\n";
			 
						 
						 
						 //#L13##b破攻升级#l #L6##b快速转职#l#L11##b填推广码#l #L12##b推广商店#l 

            cm.sendSimple(add);

//------------------------------------------------------------------------

        } else if (status == 1) {

		if (selection == 0) {
		cm.dispose();
		cm.warp(910000000);//自由市场
                //cm.openNpc(9330079, "万能传送");
	    }

	    if (selection == 1) {
		cm.dispose();
                cm.openNpc(9330079, "万能传送");
	    }

	    if (selection == 2) {
		cm.dispose();
                cm.openNpc(9330079, "怪物爆率");
	    }

	    if (selection == 3) {
		cm.dispose();
                cm.openNpc(9330079, "快速洗血");
	    }

	    if (selection == 4) {
		cm.dispose();
                cm.openNpc(9330079, "综合排行");
	    }

	    if (selection == 5) {
		cm.dispose();
                cm.openNpc(9330079, "快捷商店");
	    }

	    if (selection == 6) {
		cm.dispose();
                cm.openNpc(9330079, "快速转职");

		
            }
	    if (selection == 7) {
		cm.dispose();
                //cm.openNpc(9330079, "装备强化");
				cm.openNpc(9330079, "9310072");

            }
	    if (selection == 8) {
		cm.dispose();
                cm.openNpc(9330079, "师徒系统");
            }
	    if (selection == 9) {
		cm.dispose();
                cm.openNpc(9330079, "每日任务");

		
            }
	    if (selection == 10) {
		cm.dispose();
                cm.openNpc(9330079, "推广系统");

		
            }
	    if (selection == 11) {
		cm.dispose();
                cm.openNpc(9330079, "填推广码");

		
            }
	    if (selection == 12) {
		cm.dispose();
                cm.openNpc(9330079, "推广商店");

		
            }
 	    if (selection == 13) {
		cm.dispose();
                cm.openNpc(9330079, "破攻升级");

		
            }
	    if (selection == 14) {
		cm.dispose();
                cm.openNpc(9330079, "BOSS召唤");

		
            }
	    if (selection == 15) {
		cm.dispose();
                cm.openNpc(9330079, "勋章强化");


            }
	    if (selection == 16) {
		cm.dispose();
                //cm.openNpc(9330079, "在线奖励");
				cm.openNpc(9330079, "在线时间");

            }
			
		if (selection == 17) {
		cm.dispose();
                cm.openNpc(9330079, "新人礼包");

            }

			if (selection == 18) {
		cm.dispose();
                cm.openNpc(9330079, "背包清理");

            }	
			
			if (selection == 19) {
		cm.dispose();
                cm.openNpc(9330079, "每日签到");

            }
			
			if (selection == 20) {
		cm.dispose();
                cm.openNpc(9330079, "等级奖励");

            }
			
			if (selection == 21) {
		cm.dispose();
                cm.openNpc(9330079, "合成中心");

            }
			
			if (selection == 22) {
		cm.dispose();
		cm.openNpc(9330079, "兑换中心");
                //cm.openNpc(9330079, "紫金枫叶武器兑换");

            }
			
			if (selection == 23) {
		cm.dispose();
		cm.openNpc(9330079, "大姐大租借装备");
                //cm.openNpc(9330079, "紫金枫叶武器兑换");

            }
			
			if (selection == 24) {
		cm.dispose();
		cm.openNpc(9330079, "概率制作");
                //cm.openNpc(9330079, "紫金枫叶武器兑换");

            }

			if (selection == 99) {//个人信息
		cm.dispose();
		cm.openNpc(9330079, 100);
                //cm.openNpc(9330079, "紫金枫叶武器兑换");

            }
		
             
        }
    }
}

