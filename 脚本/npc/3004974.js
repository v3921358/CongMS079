var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┃ 加盾防御　  售登陆器 ┃";
var FY6 = "┣━━━━━━━━━━━┫";
var FY7 = "┃ 唯一QQ:1848350048    ┃";
var FY8 = "┗━━━━━━━━━━━┛";
var chosenMap = -1;
var monsters = 0;
var towns = 0;
var bosses = 0;
var fuben = 0;
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

		if(cm.getPlayer().getMapId()==180000001)
			{
				cm.dispose();
				cm.openNpc(9900005);
				return;
			}
			if(cm.getPlayer().getMapId()==970000005 || cm.getPlayer().getMapId()==901111112)
			{
				cm.dispose();
				return;
			}
		
		
            var add =          " \t\t"+彩虹+"  #e#r 每 日 任 务 #k#n  #r  "+彩虹+"#b#k#n\r\r\n";
add += "  "+猫左+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+猫右+"\r\n";add +="\t #e当前点卷：#r" +cm.getPlayer().getCSPoints(1) +  "#k点  #e在线时间：#r" + cm.getGamePoints() + "#k分钟\r\n";
		

add +=   "#L1#"+圆形+"#b每日跑商"+圆形+"#l  #L2#"+圆形+"每日打野"+圆形+"#l  #L3#"+圆形+"每日打工"+圆形+"#l\r\n\r\n";
add +=   "#L5#"+圆形+"#b每日副本"+圆形+"#l  #L4#"+圆形+"家族任务"+圆形+"#l  #L6#"+圆形+"每日跑环"+圆形+"#l\r\n\r\n";
					 
			 
		add += ""+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n";


		
		 
						
            cm.sendSimple(add);

//------------------------------------------------------------------------

        } else if (status == 1) {
		if (selection == 1) {
				cm.dispose();
				cm.openNpc(9010009);
        }if (selection == 2) {
				cm.dispose();
				cm.openNpc(3004974,722);
        }if (selection == 3) {
				cm.dispose();
				cm.openNpc(3004974,  "打工系统");
        }if (selection == 4) {
				cm.dispose();
				cm.openNpc(3004974,  "家族任务");
        }if (selection == 5) {
				cm.dispose();
				cm.openNpc(3004974,  "每日副本");
        }if (selection == 6) {
				cm.dispose();
				cm.openNpc(3004974,  "每日跑环");
        }
		
		
             
        }
    }
}
//---------------------------------------------------------------------------
var acc = "#fEffect/CharacterEff/1112903/0/0#";//红桃心
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//红色右箭头
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//选择道具
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 蓝色箭头 = "#fUI/UIWindow/Quest/icon2/7#";
var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 圆形 = "#fUI/UIWindow/Quest/icon3/6#";
var 粉杯 = "#fUI/UIWindow/Quest/icon7/0#";
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
var 金币图标 = "#fUI/UIWindow.img/QuestIcon/7/0#";
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";