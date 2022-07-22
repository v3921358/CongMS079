var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
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
			text += "                   #k"+皇冠白+" #r#e#w小小冒险岛#n#k "+皇冠白+"\r\n\r\n";//#n#k豆豆点：#r" + cm.getBeans() + "#k点\t\t//"
			text += "   "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"#d\r\n\r\n";	
			text += "\t#L1#等级排行榜#l\t#L2#物品爆率查询#l\t#L3#删除物品#l";	
            cm.sendSimple(text);
        } else if (selection == 1) {
			cm.showlvl();
            cm.dispose();
        }else if (selection == 2) {
			cm.dispose();
			cm.openNpc(9310095,1);
		}else if (selection == 3) {
			cm.dispose();
			cm.openNpc(9900004,17);
		}
	}
}