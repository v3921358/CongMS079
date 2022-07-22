/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：酷男孩
 */
var JT = "#fUI/Basic/BtHide3/mouseOver/0#";
var 心 = "#fUI/GuildMark.img/Mark/Etc/00009001/14#";
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        var selStr = "	  Hi~#b#h ##k我是酷男孩，很酷很酷的男孩，你找我有什么事情吗？\r\n";
		if(cm.判断地图()!=749020910){
        selStr += " #L1000##b极限挑战#k#l\r\n";
		
		
		
		
		selStr += " #L1##b去国庆蛋糕活动入场#k#l\r\n";
		
		
		
		}else{
			selStr += " #L1##b我要离开了#k#l\r\n";
			selStr += " #L2##b进入活动地图#k#l\r\n";
			selStr += " #L3##b兑换物品#k#l\r\n";
		}

        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
            case 1:
                cm.dispose();
				cm.openNpc(2007,7);
                break;
			case 2:
                cm.dispose();
				cm.openNpc(9330078,1);
                break;
			case 3:
                cm.dispose();
				cm.openNpc(9330078,2);
                break;
			case 100:
                cm.dispose();
				cm.openNpc(2007,9);
                break;
			case 1000:
                cm.dispose();
				cm.openNpc(9330078,1000);
                break;
        }
    }
}