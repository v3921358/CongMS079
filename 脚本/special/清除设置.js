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
load("nashorn:mozilla_compat.js");importPackage(java.lang);
load("nashorn:mozilla_compat.js");importPackage(Packages.client);
load("nashorn:mozilla_compat.js");importPackage(Packages.client.inventory);
load("nashorn:mozilla_compat.js");importPackage(Packages.server);
load("nashorn:mozilla_compat.js");importPackage(Packages.constants);
load("nashorn:mozilla_compat.js");importPackage(Packages.net.channel);
load("nashorn:mozilla_compat.js");importPackage(Packages.tools);
load("nashorn:mozilla_compat.js");importPackage(Packages.scripting);
load("nashorn:mozilla_compat.js");importPackage(Packages.tools.packet);
load("nashorn:mozilla_compat.js");importPackage(Packages.tools.data);
load("nashorn:mozilla_compat.js");importPackage(Packages.tools);
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/13#";
var status = -1;
var itemss;
var slot = Array();

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
	
    if (mode == 1) {
        status++;
    } else if (mode == 0 && status != 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }var MC = cm.getServerName();

    if (status == 0) {
		var avail = "";
		for (var i = 0; i < 96; i++) {
			if (cm.getInventory(3).getItem(i) != null) {
				avail += "#L" + cm.getInventory(3).getItem(i).getItemId() + "##i" + cm.getInventory(3).getItem(i).getItemId() + ":##l";
			}
			slot.push(i);
		}
		  
		cm.sendSimple("             #r"+清理背包+"#k\r\n\r\n #r设置清理\t提示：点击即清理，点下一步默认清理第一个\r\n\r\n#b" + avail);
	 
    } else if (status == 1) {
        itemss = selection;
		var shul = cm.getPlayer().getItemQuantity(itemss, false);
		cm.removeAll(itemss);
		//Ok("我已经将你背包里的 #d#i" + itemss + ":# #t" + itemss + ":# 数量：#e#r" + shul + "#n#b\r\n从你的背包删除！");
		
		 cm.dispose();	
		 cm.openNpc(3003332,"清除设置");
            	
    } else {
        cm.dispose();
    }//status
}// function

function Ok(text) {
    cm.sendOk(text);
}



