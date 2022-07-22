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

//------------------------------------------------------------------------

var chosenMap = -1;
var monsters = 0;
var towns = 0;
var bosses = 0;
var fuben = 0;

//------------------------------------------------------------------------



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

            var add =  " \t\t#d#e#b#v4030001# 欢迎来到#r" + cm.getServerName() + " 兑换中心 #e#b#v4030001##b#k#n\r\r\n";
				add += ""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\r\n";
				add += "#e  #r全免费,#d只要肯花时间,那你就是最牛逼的靓仔/靓妹#n\r\n"
				//add +="\t#d#e您今天已在线了：#r" + cm.getGamePoints() + "#k分钟,请注意休息哦~!#k#n\r\n";
                         //add += "  #L0##b自由市场#l #k#n\r\n";	
						add += "#e#L1##b豆豆兑换技能书#l";
						add += "#L2##b豆豆兑换宝库#l";
						add += "#L3##b黄金枫叶兑换#l";
						 
						 


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
                cm.openNpc(9120106);
	    }

	    if (selection == 2) {
		cm.dispose();
                cm.openNpc(9330079, "豆豆兑换宝库");
	    }

	    if (selection == 3) {
		cm.dispose();
                cm.openNpc(2101018, 0);
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
                cm.openNpc(9330079, "装备强化");

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
                cm.openNpc(9330079, "革命武器兑换");

            }
			
			if (selection == 22) {
		cm.dispose();
		cm.openNpc(9330079, "兑换中心");
                //cm.openNpc(9330079, "紫金枫叶武器兑换");

            }
			

		
             
        }
    }
}

