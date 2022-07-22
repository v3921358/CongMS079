var CY0 = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓";
var CY1 = "┃       - 创意 -       ┃";
var CY2 = "┃ 脚本仿制  　定制脚本 ┃";
var CY3 = "┃ 技术支持 　 游戏顾问 ┃";
var CY4 = "┃ ＷＺ添加　  地图制作 ┃";
var CY5 = "┃ 加盾防御　  售登陆器 ┃";
var CY6 = "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫";
var CY7 = "┃   唯一QQ:12384161    ┃";
var CY8 = "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";
var 清理背包 = "#fString/zerek/LOGO/清理背包#"; 
var 图标1 = "#fUI/UIWindow.img/FadeYesNo/icon7#";
var 图标2 = "#fUI/StatusBar.img/BtClaim/mouseOver/0#";
var 关闭 = "#fUI/UIWindow.img/CashGachapon/BtOpen/mouseOver/0#";
var 打开 = "#fUI/UIWindow.img/CashGachapon/BtOpen/disabled/0#";
var JD = "#fUI/Basic/BtHide3/mouseOver/0#";
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
var 装备2 = "#fUI/CashShop.img/Base/Tab2/Enable/0#";
var 消耗2 = "#fUI/CashShop.img/Base/Tab2/Enable/1#";  
var 设置2 = "#fUI/CashShop.img/Base/Tab2/Enable/2#"; 
var 其他2 = "#fUI/CashShop.img/Base/Tab2/Enable/3#";   
var 特殊2 = "#fUI/CashShop.img/Base/Tab2/Enable/4#"; 
var a = "#fEffect/CharacterEff.img/1112926/0/1#";
function start() {
    status = -1;
    action(1, 0, 0)
}

function action(mode, type, selection) {
    if (status <= 0 && mode <= 0) {
        cm.dispose();
        return
    }
    if (mode == 1) {
        status++
    } else {
        status--
    }	 
	if (status <= 0) {
        var selStr = "  #r"+清理背包+"#k\r\n";
		
		selStr += "我可以帮你清除掉背包的物品哦，比如那些丢弃不掉的物品。#k\r\n\r\n";
				
		selStr += "#L1#"+装备2+"#l\t#L2#"+消耗2+"#l\t#L3#"+设置2+"#l\t#L4#"+其他2+"#l\t#L5#"+特殊2+"#l";
				
		
				
		selStr += "\r\n\r\n\r\n\t\t\t\t\t#L20##r一键删除#l#k";	
	
		selStr += "\r\n\r\n\r\n\t\t\t\t\t#L0##b返回界面#l";


        cm.sendSimple(selStr)
    } else if (status == 1) {
        switch (selection) {
			case 20:
                cm.dispose();
                cm.openNpc(3003332,"一键删除");
                break;
			case 0:
                cm.dispose();
                cm.openNpc(3003332,"拍卖功能");
                break;
            case 1:
                cm.dispose();
                cm.openNpc(3003332,"清除装备");
                break;
			case 2:
                cm.dispose();
                cm.openNpc(3003332,"清除消耗");
                break;
			case 3:
                cm.dispose();
                cm.openNpc(3003332,"清除设置");
                break;	
			case 4:
                cm.dispose();
                cm.openNpc(3003332,"清除其他");
                break;		
			case 5:
                cm.dispose();
                cm.openNpc(3003332,"清除特殊");
                break;	
			case 11:
                cm.dispose();
                cm.openNpc(3003332,56);
                break;
			case 12:
                cm.dispose();
                cm.openNpc(3003332,57);
                break;
			case 13:
                cm.dispose();
                cm.openNpc(3003332,58);
                break;	
			case 14:
                cm.dispose();
                cm.openNpc(3003332,59);
                break;		
			case 15:
                cm.dispose();
                cm.openNpc(3003332,60);
                break;	

			
        }
    }
}