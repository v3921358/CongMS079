var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣   唯一QQ:3066318387  ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var head = "#fUI/UIWindow2.img/Quest/quest_info/summary_icon/summary#\r\n";
var icon = "#fUI/UIWindow/Minigame/Common/mark#";
var sl1 = 0;//兑换数量

var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == 1) {
		status++;
	} else {
		if (status >= 0) {
			cm.dispose();
			return;
		}
		status--;
	}
          /*if (status == 0) {
                 var text = "";
			    var text = "\t\t"+彩虹+"  #e#d 新 人 礼 包 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n#r恭喜您将获得以下物品：确认点下一页立即领取#k#n\r\n";
				text += "#v1332066#  四维属性+2   #v1142358#  四维属性+1\r\n";
				text += "#v1012071#  四维属性+10   #v1112400#  四维属性+10\r\n";
				text += "#v1112171#   #v5000066#   #v5030000#   #v5150040#   \r\n";
				text += "#v2120000#10个 #v4000463#10个  #v4000313#10个  #v2001000#50个#k#n\r\n";
				text += ""+ 金币图标 +"金币:100万 点卷：100万#k#n\r\n";
			cm.sendSimple(text);
		cm.sendNextS(text, 1);
		} else*/ if (status == 0) {

		//if (cm.getPlayer().getPrizeLog("新手驾到") < 1 && cm.getPlayerStat("LVL") > 9) {	
          if (cm.getPlayer().getPrizeLog("新手驾到") < 1 ) {		
			cm.getPlayer().setPrizeLog("新手驾到");	
			cm.gainItem(2001000, 50);//西瓜消耗
			cm.gainItem(1332066,2,2,2,2,0,0,10,10,0,0,0,0,0,0,120);//新手刮胡刀
			cm.gainItem(1112400,10,10,10,10,0,0,0,0,0,0,0,0,0,0,120);//戒指
			cm.gainItem(1012071,10,10,10,10,0,0,0,0,0,0,0,0,0,0,120);
			cm.gainItem(2120000, 10);
			cm.gainItem(1142358,1,1,1,1,10,10,1,1,0,0,0,0,0,0); //新手勋章我最可爱
			cm.gainPet(5000066, 520, 0, 0, 0, 0);//啊呜啊呜	
			cm.gainItem(1112171,1); //狗子名片
			cm.gainItem(5030000,1);
			//cm.给属性装备(1332066, 1, 0, 20, 20, 20, 20, 50, 50, 30, 30,0, 0, 0, 0, 0, 0, 24);//新手刮胡刀时间单位/小时
			//cm.给属性装备(1142358, 1, 0, 1, 1, 1, 1, 50, 50, 1, 1,0, 0, 0, 0, 0, 0, 0);//新手勋章我最可爱
			//cm.给属性装备(1142099, 1, 0, 20, 20, 20, 20, 500, 500, 35, 35,0, 0, 0, 0, 40, 40, 24);//时间单位/小时
			//cm.给属性装备(1142101, 1, 0, 20, 20, 20, 20, 500, 500, 15, 15,0, 0, 0, 0, 40, 40, 0);
			cm.gainMeso(10000000);//金币
			//开服:
			cm.gainItem(5150040,1);//皇家美发卡
			//cm.gainItem(4000463,10);//国庆纪念币
			//cm.gainItem(4000313,10);//进阶币
			//cm.gainDY(8000);//给予抵用卷8000点  
            cm.getPlayer().modifyCSPoints(2,10000, true);//点券			

			cm.全服黄色喇叭("[公告事项] : 恭喜玩家 "+cm.getPlayer().getName()+" 成功领取了萌新大礼包。")
            cm.dispose();
				
		} else {
            cm.sendOk("一个账号只可以领取一次。");
            cm.dispose();



            }
        }

}
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";
var 金币图标 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";