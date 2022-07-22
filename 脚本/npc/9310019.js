var 星星 = "#fEffect/CharacterEff/1114000/2/0#";
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 正方形 = "#fUI/UIWindow/Quest/icon3/6#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var 幸运草 ="#fUI/GuildMark/Mark/Plant/00003006/15#";
var 香水 ="#fUI/GuildMark/Mark/Pattern/00004008/15#";
var 中条白 ="#fUI/Basic/HScr7/disabled/base#";
var 中条蓝 ="#fUI/ChatBalloon/tutorial/w#";

var 中条猫 ="#fUI/ChatBalloon/169/n#";
var 猫右 =  "#fUI/ChatBalloon/169/ne#";
var 猫左 =  "#fUI/ChatBalloon/169/nw#";
var 右 =    "#fUI/ChatBalloon/169/e#";
var 左 =    "#fUI/ChatBalloon/169/w#";
  
var 下条猫 ="#fUI/ChatBalloon/169/s#";
var 猫下右 ="#fUI/ChatBalloon/169/se#";
var 猫下左 ="#fUI/ChatBalloon/169/sw#";

var 黑色 ="#fUI/GuildMark/Mark/Pattern/00004003/16#";
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
            text += "                 #k"+皇冠白+" #w#r#e小小冒险岛#n#k "+皇冠白+"\r\n";//#n#k豆豆点：#r" + cm.getBeans() + "#k点\t\t//
			
            text += "    #L1##d" + 蓝色箭头 + "月妙组队副本#l         #L2##d" + 蓝色箭头 + "废弃组队副本#l\r\n\r\n"//3
            text += "    #L3##d" + 蓝色箭头 + "玩具组队副本#l         #L4##d" + 蓝色箭头 + "天空组队副本#l\r\n\r\n"//3
            text += "    #L5##d" + 蓝色箭头 + "毒物组队副本#l         #L6##d" + 蓝色箭头 + "海盗组队副本#l\r\n\r\n"//3
            text += "    #L7##d" + 蓝色箭头 + "逆奥组队副本#l         #L8##d" + 蓝色箭头 + "罗密欧与朱丽叶副本#l\r\n\r\n\r\n\r\n\r\n"//3
            cm.sendSimple(text);
        } else if (selection == 1) { //月妙组队副本
            cm.dispose();
			cm.openNpc(1012112, 0);
        } else if (selection == 2) {  //废弃组队副本
            cm.dispose();
			cm.openNpc(9020000, 0);
        } else if (selection == 3) { //玩具组队副本
            cm.dispose();
			cm.openNpc(2040034, 0);
        } else if (selection == 4) {//天空组队副本
            cm.dispose();
			cm.openNpc(2013000, 0);
        } else if (selection == 5) {//毒物组队副本
            cm.warp(300030100);
            cm.dispose();
        } else if (selection == 6) {//海盗组队副本
            cm.dispose();
			cm.openNpc(2094000, 0);
        } else if (selection == 7) {//逆奥组队副本
            cm.dispose();
			cm.warp(800000000, 0);
        } else if (selection == 8) {//男女组队副本
            cm.dispose();
			cm.warpParty(261000011, 0);
        }
    }
}


