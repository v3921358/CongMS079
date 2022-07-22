
var CY0 = "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓";
var CY1 = "┃       - 创意 -       ┃";
var CY2 = "┃ 脚本仿制  　定制脚本 ┃";
var CY3 = "┃ 技术支持 　 游戏顾问 ┃";
var CY4 = "┃ ＷＺ添加　  地图制作 ┃";
var CY5 = "┃ 加盾防御　  售登陆器 ┃";
var CY6 = "┃ 端游开服　  手游开服 ┃";
var CY7 = "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫";
var CY8 = "┃   唯一QQ:12384161    ┃";
var CY9 = "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛";
var 枫叶 = "#fUI/ITC.img/Base/Tab/Enable/0#";
var 选择获得 = "#fUI/UIWindow/Quest/select#";
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
            var tex2 = "";
            var text = "";
            for (i = 0; i < 10; i++) {
                text += "";
            }
			
            text = "\t\t\t\t#e#r"+ 枫叶 +" 礼包福利 "+ 枫叶 +"#k#n\r\n\r\n"
			text += "你好,我是福利代理人,这里可以领取每日福利,领取VIP徽章!\r\n"//3
            text += "#L1##b#v1142609#领取每日礼包VIP①\r\n"
			text += "#L2##b#v1142617#领取每日礼包VIP②\r\n"
			text += "#L3##b#v1142623#领取每日礼包VIP③#l\r\n\r\n"
            text += "#r"+选择获得+"兑换VIP徽章①②③#k#n#l\r\n"
            text += "#L6##b#v4000423#礼包兑换VIP①#v1142609#   （累计赞助可以免费获取）\r\n"
			text += "#L4##b#v4000425# 礼包兑换VIP②#v1142617#   （累计赞助可以免费获取）\r\n"
            text += "#L5##b#v4000424#礼包兑换VIP③#v1142623#   （累计赞助可以免费获取）\r\n"

            cm.sendSimple(text);
        } else if (selection == 1) {
			cm.dispose();
		cm.openNpc(9900004, 777);
        }

		else if (selection == 2) {
			cm.dispose();
		cm.openNpc(9900004, 888);
		}
		else if (selection == 9) {
			cm.dispose();
		cm.openNpc(9900004, "万元户");
		}
		else if (selection == 10) {
			cm.dispose();
		cm.openNpc(9900004, "两万元户");
		}

		else if (selection == 3) {
			cm.dispose();
		cm.openNpc(9900004, 9999);
        }

else if (selection == 6) {
	cm.dispose();
		cm.openNpc(9900004, 1238);
        }  
else if (selection == 4) {
	cm.dispose();
		cm.openNpc(9900004, 1236);
        }  
else if (selection == 5) {
	cm.dispose();
		cm.openNpc(9900004, 1237);
        } 
		else if (selection == 7) {
	cm.dispose();
		cm.openNpc(9900004, "充值礼包4");
        } 
		else if (selection == 8) {
	cm.dispose();
		cm.openNpc(9900004, "充值礼包5");
        } 
		
		 
    }
}


