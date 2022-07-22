
var chosenMap = -1;
var monsters = 0;
var towns = 0;
var bosses = 0;
var fuben = 0;


//---------------------------------------------------------------------------
function start() {
    status = -1;
    action(1, 0, 0);
}
function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status >= 0 && mode == 0) {
            cm.sendOk("#b好的,下次再见.");
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }
        if (status == 0) {		
            var add =  "\t      "+彩虹+"#e#d  技 能 使 者  #k#n  #r  "+彩虹+"#b#k#n\r\r\n";

			 add += ""+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+"\r\n";
add +="\t#d#e当前点卷：#r" +cm.getPlayer().getCSPoints(1) +  "#k点  #d#e在线时间：#r" + cm.getGamePoints() + "#k分钟\r\n\r\n";
if(cm.getPlayer().getLevel() < 120){
	add +=   "啊哈，你还没达到120级，这里还没有适合您的技能哟\r\n\r\n";
}
if(cm.getPlayer().getLevel() >= 120){
add +=   "               #L4#"+皇冠白+"四转技能"+皇冠白+"#l    \r\n\r\n";
add +=   "               #L5#"+皇冠白+"骑士技能"+皇冠白+"#l    \r\n\r\n";
}
if(cm.getPlayer().getLevel() >= 150){
add +=   "               #L3#"+皇冠白+"偷学技能"+皇冠白+"#l    \r\n\r\n";
}						 
			 
		add += ""+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+红爱心+"\r\n";

            cm.sendSimple(add);

//------------------------------------------------------------------------

        } else if (status == 1) {
			if (selection == 5) {//充值
				cm.dispose();
				cm.openNpc(9270053, "骑士团技能");
        }
			if (selection == 4) {//充值
				cm.dispose();
				cm.openNpc(9270053, "四转技能");
        }
			if (selection == 3) {//充值
				cm.dispose();
				cm.openNpc(3003331, "偷学技能");
        }		
            if (selection == 2) {//充值
				cm.dispose();
				cm.openNpc(9310059, 1893);
        }		
		    if (selection == 1) {//充值
				cm.dispose();
				cm.openNpc(9900004, 71);
        }			
		
             
        }
    }
}

var 金币图标 = "#fUI/UIWindow.img/QuestIcon/7/0#";
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";
var acc = "#fEffect/CharacterEff/1112903/0/0#";//红桃心
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//红色右箭头
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//选择道具
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 美化new = "#fUI/UIWindow/Quest/icon2/7#";
var 美化ne = "#fUI/UIWindow/Quest/icon6/7#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 皇冠白 ="#fUI/GuildMark/Mark/Etc/00009004/16#";
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 草莓 = "#fUI/GuildMark/Mark/Plant/00003000/1#"; // 红色草莓
var 草莓1 = "#fUI/GuildMark/Mark/Plant/00003000/10#"; // 淡蓝色草莓
var 草莓2 = "#fUI/GuildMark/Mark/Plant/00003000/11#"; // 紫色草莓
var 草莓3 = "#fUI/GuildMark/Mark/Plant/00003000/15#"; // 白色草莓
var 草莓4 = "#fUI/GuildMark/Mark/Plant/00003000/3#"; // 黄色草莓
var 草莓5 = "#fUI/GuildMark/Mark/Plant/00003000/8#"; // 绿色草莓
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 大黄星 = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var 小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】
var 小水滴 = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var 大水滴 = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var 红爱心 ="#fEffect/CharacterEff/1112905/0/1#";