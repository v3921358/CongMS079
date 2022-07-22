
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

            var add =  " \t\t#d#e#b#v4030001# 欢迎来到#r" + cm.getServerName() + " 合成中心 #e#b#v4030001##b#k#n\r\r\n";
				add += ""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n\r\n";
				add += "#e  #r全免费,#d只要肯花时间,那你就是最牛逼的靓仔/靓妹#n\r\n\r\n"
                       
				if(cm.getPlayer().getLevel() <= 109 ){
				add += "你的等级还不够，快去努力提升等级吧#l\r\n";
				}		
               
				if(cm.getPlayer().getLevel() >= 110 ){
				add += "#L4##r - - - <<<制作寻宝装备>>> - - -【概率100%】#l\r\n\r\n";
				}
				if(cm.getPlayer().getLevel() >= 145 ){
				add += "#L5##r - - - <<<制作巨匠武器>>> - - -【概率100%】#l\r\n\r\n";
				}
				if(cm.getPlayer().getLevel() >= 150 ){
				add += "#L1##r - - - <<<制作弗纳武器>>> - - -【概率100%】#l\r\n\r\n";
				add += "#L2##r - - - <<<制作高贵装备>>> - - -【概率100%】#l\r\n\r\n";
				}
				if(cm.getPlayer().getLevel() >= 200 ){
				add += "#L3##r - - - <<<制作红绝武器>>> - - -【概率100%】#l\r\n\r\n";
				}
				//add += "#L10##r 合成 150级 法弗纳套装 #d【概率70%】#l\r\n";
				
				

            cm.sendSimple(add);

//------------------------------------------------------------------------

        } else if (status == 1) {

		if (selection == 0) {
		cm.dispose();
		//cm.warp(910000000);//自由市场
                cm.openNpc(9330079, "10周年装备");
	    }

	    if (selection == 1) {
		cm.dispose();
        cm.openNpc(9310054, "150FFN概率合成");
	
            }
		if (selection == 2) {
		cm.dispose();
        cm.openNpc(9310054, "150高贵概率合成");
	
            }
		if (selection == 3) {
		cm.dispose();
        cm.openNpc(9310054, "红绝武器");
	
            }

	    if (selection == 4) {
		cm.dispose();
                cm.openNpc(9201052, "12周年装备");
	    }

	    if (selection == 5) {
		cm.dispose();
                cm.openNpc(9201052, "145巨匠概率合成");
	    }

	    if (selection == 6) {
		cm.dispose();
                cm.openNpc(9330079, "135布莱克武器");

		
            }


		

	    if (selection == 10) {
		cm.dispose();
                cm.openNpc(9330079, "150FFN概率合成");

		
            }
	    if (selection == 11) {
		cm.dispose();
                cm.openNpc(9330079, "145巨匠装备");

		
            }
	    if (selection == 12) {
		cm.dispose();
                cm.openNpc(9330079, "150FFN武器");

		
            }
 	   
			

		
             
        }
    }
}

