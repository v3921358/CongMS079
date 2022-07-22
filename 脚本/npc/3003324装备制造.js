function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {

            cm.sendOk("感谢你的光临！");
            cm.dispose();
            return;
        }
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
            text += " \t\t\t#e#d#r欢迎来到" + cm.getChannelServer().getServerName() + "制作装备系统#k#n\r\n"

            text += "\t\t\t#e#d当前在线时间：" + cm.getGamePoints() + "分钟！#k#n\r\n"

            text += "\t#e#d账户余额：剩余点卷" + cm.getNX(1) + "#k\t剩余抵用卷" + cm.getNX(2) + "#k#n\r\n"

            text += " #L1##r#e武器制作#l           #L2#首饰制作#l\r\n\r\n" 
						text += "\t\t   #L5##r#e材料合成#l           \r\n\r\n" 
						//#L7##r#e副本装备#l
			text += " #L3##b#e套装制作#l           #L4##b#e血衣制作#l\r\n\r\n"
			//text += " #L7##b#e制作首饰装备#l       #L8##b#e制作巨匠武器#l \r\n"
			
			cm.sendSimple(text);
			
		} else if (selection == 1) {
            cm.openNpc(9900004, 7021);
        
		} else if (selection == 2) {
            cm.openNpc(9900004, 7019);

        } else if (selection == 3) {
            cm.openNpc(9900004, 7022);

        } else if (selection == 4) {
            cm.openNpc(9900004, 7003);
			
			} else if (selection == 5) {
            cm.openNpc(9900004, 5);
			
			} else if (selection == 6) {
            cm.openNpc(9900004, 7015);

        } else if (selection == 7) {
            cm.openNpc(9900004, 7024);
			
        } else if (selection == 8) {
            cm.openNpc(9900004, 7017);
        }
    }
}
