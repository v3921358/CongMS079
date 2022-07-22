var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┣━━━━━━━━━━━┫";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┗━━━━━━━━━━━┛";

var 金币图标 = "#fUI/UIWindow.img/QuestIcon/7/0#";
var 星星 ="#fMap/MapHelper/weather/witch/3#";
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
			text += ""
			text += "\t\t\t     "+ 星星 +"#e#d 段 段 中 心 #k#n"+ 星星 + "#l\r\n\r\n"; 
            text += "#e哈喽，在我这里可以制作装备哦，达到120级以后还可以去往特殊地图！#n\r\n\r\n"
            text += "#e  #r全免费,#d只要肯花时间,那你就是最牛逼的靓仔/靓妹#n\r\n"
			
			text += "#L11##e#b- - - <<<制作上品装备>>> - - -#l\r\n"
			text += "#L111##e#b- - - <<<制作精品装备>>> - - -#l\r\n"
			text += "#L4##e#b- - - <<<进入特殊地图>>> - - -#l\r\n"

            cm.sendSimple(text);
		}
              else  if (selection == 1) {
				cm.dispose();
                cm.openNpc(3003382, "4周年装备");
			}
			  else if (selection == 11) {
				cm.dispose();
                cm.openNpc(3003382, "79紫金装备");
			}
			  else if (selection == 111) {
				cm.dispose();
                cm.openNpc(3003382, "100革命装备");
			}
              else if (selection == 2) {
				cm.warpParty(970000003);
				cm.dispose();
			} else if (selection == 22) {
				cm.warpParty(970000004);
				cm.dispose();
			} else if (selection == 4) {	
                                      
				cm.warp(901111111);
				cm.dispose();
			}
		 
		
		  else if (status == 2) {
            if (beauty == 100) {
				var zliang = cm.getPlayer().getItemQuantity(4001126, false);
                if (zliang > 0){
					cm.removeAll(4001126);
					cm.gainMeso(8000*zliang);					
							
                    cm.sendOk("兑换成功。共兑换了:[#r"+(zliang)+"#k] 个。");
					cm.worldMessage(6,"[相框兑换]：玩家 "+cm.getName()+" 努力搬砖,在自由相框兑换了："+(zliang*8000)+" 金币。");
					cm.dispose();
                } else {
                    cm.sendOk("您的物品不足，无法兑换。");
                    cm.dispose()
                }            		
            }
			
        }
    }
}