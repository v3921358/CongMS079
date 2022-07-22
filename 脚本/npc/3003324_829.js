/* global cm */
var 爱心 = "";
var 音符 = "#fEffect/CharacterEff/1022223/4/0#";
var 小雪花 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 爱心1 = "#fEffect/CharacterEff/1032063/0/0#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";
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
			text ="兑换#v2550014#需要以下材料：\r\n\r\n#v4000313# × 30\t#v4031217# × 1\t#v4251202# × 1\t#v4011008# × 20\r\n温馨提示:每日只能兑换3次~！\r\n";
			text +="#L0##b我要兑换#l";
            cm.sendSimple(text);
        } else if (selection == 0) {//材料强化 增加随即2-5
		
            if(cm.getBossLog("材料兑换强化箱子") >= 3){
			    cm.sendOk("您今天已经兑换了3次了！");
				cm.dispose();
		    } else if(!cm.haveItem(4000313,30)){
				cm.sendOk("您没有#v4000313# × 30 ！");
				cm.dispose();
			} else if(!cm.haveItem(4031217,1)){
				cm.sendOk("您没有#v4031217# × 1 ！");
				cm.dispose();
			} else if(!cm.haveItem(4251202,1)){
				cm.sendOk("您没有#v4251202# × 1 ！");
				cm.dispose();
			} else if(!cm.haveItem(4011008,20)){
				cm.sendOk("您没有#v4011008# × 20 ！");
				cm.dispose();
			} else {
				cm.gainItem(4000313,-30);
				cm.gainItem(4031217,-1);
				cm.gainItem(4251202,-1);
				cm.gainItem(4011008,-20);
				cm.setBossLog("材料兑换强化箱子");
				cm.gainItem(2550014,1);
				cm.sendOk("兑换成功~！！");
				cm.dispose();
			}
		
        } else if (selection == 1) {//点卷强化 增加随机2-5
            cm.openNpc(9310074,821);
			 } else if (selection == 2) {//材料强化 杂卷
            cm.openNpc(9310074,823);
	    } else if (selection == 3) {//强化 砸卷  点卷
            cm.openNpc(9310074,820);
        }
    }
}



