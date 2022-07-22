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
            text += "\t"+彩虹+"     #e#d BOOSS 重 返 #k#n  #r     "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n您好,尊敬的#k #h ##k 你是想返回战场？\r\n重返一次金币费用:1000万！每种BOSS每天可以重返2次\r\n"+金币+"背包拥有金币：#r"+cm.判断金币() +"#k#n\r\n";
            text += "#L2##b\t﹤﹤﹤（点消费金币重返轧昆）﹥﹥﹥\t#l\r\n\r\n"//3
            text += "#L3##b\t﹤﹤﹤（点消费金币重返黑龙）﹥﹥﹥\t#l\r\n\r\n"//3
			text += "#L4##b   ﹤﹤﹤（点消费金币重返品克缤）﹥﹥﹥\t#l\r\n\r\n"//3
            cm.sendSimple(text);
        } else if (selection == 1) {
			
			if(cm.getPlayer().getBossLogD == 1 && cm.getMap(220080001).getCharactersSize() > 0){
			cm.warp(220080001);
            cm.dispose();
			}else{
			cm.setBossLog(0);
            cm.sendOk("地图可能已经没有人了,或您所在的频道不对!");
            cm.dispose();
			}
        } else if (selection == 2) {
		    cm.dispose();
		    cm.openNpc(2030013, 1);
        } else if (selection == 3) {
			cm.dispose();
			cm.openNpc(2083004, 1);
		} else if (selection == 4) {
			cm.dispose();
			cm.openNpc(2141001, 1);
		}
    }
}
var 金币 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";

