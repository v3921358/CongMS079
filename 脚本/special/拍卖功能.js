var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣   唯一QQ:3066318387  ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var chosenMap = -1;
var monsters = 0;
var towns = 0;
var bosses = 0;
var fuben = 0;
function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
       
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
        if (status == 0) {

		if(cm.getPlayer().getMapId()==180000001){
			
				cm.dispose();
				cm.openNpc(9900005);
				return;
			}
			if(cm.getPlayer().getMapId()==970000005 || cm.getPlayer().getMapId()==901111112 || cm.getPlayer().getMapId()==901111113){
			
				cm.dispose();
				return;
			}
		
		
            var add =             " \t\t"+彩虹+"  #e#r " + cm.getChannelServer().getServerName() + " #k#n  #r  "+彩虹+"#b#k#n\r\r\n";
            add += "  "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"\r\n";add +="\t   #e当前点卷：#r" +cm.getPlayer().getCSPoints(1) +  "#k点  #e在线时间：#r" + cm.getGamePoints() + "#k分钟\r\n";

			
add +="#L1#"+小兔+"#r快捷商店#l   #L2#"+小兔+"#r万能传送#l   #L3#"+小兔+"回去自由#l\r\n";
add +="#L4#"+小兔+"#r怪物掉落#l   #L5#"+小兔+"爆率查询#l   #L6#"+小兔+"新手入门#l\r\n\r\n";
add += "  "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+" #d美好的一天 "+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"#k\r\n";

add +="#L7#"+小兔+"#b礼包领取#l   #L8#"+小兔+"二十七关#l   #L9#"+小兔+"背包清理#l\r\n";
add +="#L10#"+小兔+"每日寻宝#l   #L11#"+小兔+"#b快捷赞助#l   #L12#"+小兔+"在线奖励#l\r\n\r\n";
add +=  "  "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+" #d从冒险开始 "+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"\r\n";
add +="#L803#"+小兔+"#b还原卷轴#l   #L806#"+小兔+"#b宿命剪刀#l   #L807#"+小兔+"#b主线任务#l\r\n";
add +="#L13#"+小兔+"#b一键转职#l   #L802#"+小兔+"#b怪物卡片#l   #L15#"+小兔+"#b身外之身#l\r\n\r\n";

					 
			 
		add += ""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n";

		if (cm.getPlayer().isGM()) {
			
			add += "#d- - - - - - 创 意 端 G M 控 制 台 - - - - - -\r\n"
			add += "#r   以下功能仅管理员可见：#k\r\n"
			add += "在线人数："+(cm.在线人数()+0)+"#L200##d"+小黄星+"换线跟踪人"+小黄星+"#l #L201##d"+小黄星+"在线跟踪人"+小黄星+"#l\r\n\r\n"
		
		add += " #L100#"+acc+"刷 金 币"+acc+"#l";
		add += " #L101#"+acc+"刷 点 卷"+acc+"#l";
		add += " #L102#"+acc+"刷新地图"+acc+"#l\r\n";
		
		add += " #L103#"+acc+"刷新状态"+acc+"#l";
		add += " #L104#"+acc+"角色管理"+acc+"#l";
		add += " #L13#"+acc+"一键转职"+acc+"#l\r\n";
		
		add += " #L105#"+acc+"回到起点"+acc+"#l";
		add += " #L106#"+acc+"一键技能"+acc+"#l\r\n";

		
		
		
		
		}
		
		 
						// #L13##b破攻升级#l #L6##b快速转职#l#L11##b填推广码#l #L12##b推广商店#l #L35#"+小水滴+"#b赏金任务#l#L36#"+小水滴+"#b杀怪任务#l

            cm.sendSimple(add);

//------------------------------------------------------------------------

        } else if (status == 1) {
			
		if (selection == 15) {
		cm.dispose();
		cm.openNpc(3003332,  "身外之身");
        }
		if (selection == 13) {
		cm.dispose();
        cm.openNpc(3003332, "快速转职");
		
        }	
		if (selection == 12) {
		cm.dispose();
		cm.openNpc(3003332,  "在线奖励");
        }
        if (selection == 11) {
		cm.dispose();
		cm.openNpc(3003385,71);
        }
        if (selection == 10) {
		cm.dispose();
		cm.openNpc(3003332,  "每日寻宝");
        }	
		if (selection == 9) {
		cm.dispose();
        cm.openNpc(3003332, "背包清理");

        }
			
		if (selection == 8) {
		cm.warp(970030000, 0 );
		cm.dispose();
        }
			
		if (selection == 7) {
		cm.dispose();
		cm.openNpc(3003332, 144);
        }
		if (selection == 6) {
		cm.dispose();
		cm.openNpc(3003332,  "4周年装备");
        }
		if (selection == 5) {
		cm.dispose();
		cm.openNpc(3003332,  "爆率查询");
        }
	    if (selection == 4) {
		cm.dispose();
        cm.openNpc(3003332, "怪物爆率");
	    }
		if (selection == 3) {//回去自由
		cm.dispose();
		cm.warp(910000000);
        }
		if (selection == 2) {
		cm.dispose();
        cm.openNpc(3003332, "万能传送");
	    }	
		if (selection == 1) {
		cm.dispose();
        cm.openNpc(3003332, "快捷商店");
	    }	
		
//-----------------------------------------------------管理员区域---------------------------------------------
        
		
		if (selection == 200) {
		cm.dispose();
		cm.openNpc(3003332, "换线跟踪");
        }
		if (selection == 201) {
		cm.dispose();
		cm.openNpc(3003332, "在线跟踪");
        }
		if (selection == 100) {//刷金币
            cm.dispose();
            cm.gainMeso(210000000);
			cm.sendOk("系统判断你是管理员：恭喜你获得了2.1E金币!");
			
		}
		if (selection == 101) {
            cm.dispose();
            cm.gainNX(999999);
			cm.sendOk("系统判断你是管理员：恭喜你获得了999999点卷!");
			
		}
		if (selection == 102) {
			cm.dispose();
			cm.刷新地图();
			cm.sendOk("系统判断你是管理员：当前地图状态已经刷新!");
			
        }
		if (selection == 103) {
		    cm.dispose();
            cm.刷新状态();
			cm.sendOk("系统判断你是管理员：当前状态已经刷新!");
			
		}
		if (selection == 104) {
				cm.dispose();
                cm.openNpc(3003332, "角色管理");
	    }
		if (selection == 105) {
            cm.dispose();
            cm.warp("http://wpa.qq.com/msgrd?V=1&Uin=1342041396&Site");	
			cm.sendOk("系统判断你是管理员：送你回到出生地方!");
			
		}
		if (selection == 106) {
		cm.dispose();
		cm.openNpc(3003332, "学习技能");

        }
		if (selection == 802) {
				cm.dispose();
                cm.openNpc(3003332, "怪物卡片");
	    }
		if (selection == 803) {
				cm.dispose();
				cm.openNpc(3003332,  "还原卷轴");
        }
        if (selection == 806) {
				cm.dispose();
				cm.openNpc(3003332,  "宿命剪刀");
        }
		if (selection == 807) {
				cm.dispose();
				cm.openNpc(3004661);
        }

             
        }
    }
}
//---------------------------------------------------------------------------
var acc = "#fEffect/CharacterEff/1112903/0/0#";//红桃心
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 粉杯 = "#fUI/UIWindow/Quest/icon7/0#";
var 美化ne = "#fUI/UIWindow/Quest/icon6/7#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 ="#fUI/ChatBalloon/37/nw#";
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
var 小兔 = "#fEffect/CharacterEff/1112960/3/0#" ; //邪恶小兔 【小】
var 小水滴 = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var 大水滴 = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var 红爱心 ="#fEffect/CharacterEff/1112905/0/1#";
var 金币图标 = "#fUI/UIWindow.img/QuestIcon/7/0#";
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";
