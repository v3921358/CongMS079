importPackage(java.lang);
importPackage(Packages.tools);
importPackage(Packages.client);
importPackage(Packages.server);
importPackage(Packages.tools.packet);
var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE); //获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE); //获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
var 开始 = "#fUI/UIWindow.img/RpsGame/BtStart/normal/0#";
var 继续 = "#fUI/UIWindow.img/RpsGame/BtContinue/normal/0#";
var 再挑战 = "#fUI/UIWindow.img/RpsGame/BtRetry/normal/0#";
var 橙色0 = "#fUI/Basic.img/LevelNo/0#";
var 橙色1 = "#fUI/Basic.img/LevelNo/1#";
var 橙色2 = "#fUI/Basic.img/LevelNo/2#";
var 橙色3 = "#fUI/Basic.img/LevelNo/3#";
var 橙色4 = "#fUI/Basic.img/LevelNo/4#";
var 橙色5 = "#fUI/Basic.img/LevelNo/5#";
var 橙色6 = "#fUI/Basic.img/LevelNo/6#";
var 橙色7 = "#fUI/Basic.img/LevelNo/7#";
var 橙色8 = "#fUI/Basic.img/LevelNo/8#";
var 橙色9 = "#fUI/Basic.img/LevelNo/9#";
var 黑底 = "#fUI/Basic.img/LevelNo/blank#";
var 红炸弹 = "#fUI/ChatBalloon.img/30/w#";

var 概率范围 = Math.floor(Math.random() * 10) + 1; //概率1-10范围

var 抵用券60至69范围 = Math.floor(Math.random() * 10) + 20; //概率20-30范围
var 点券60至69范围 = Math.floor(Math.random() * 10) + 10; //概率10-20范围
var 积分60至69范围 = Math.floor(Math.random() * 3) + 1; //概率1-3范围

var 抵用券70至79范围 = Math.floor(Math.random() * 10) + 30; //概率30-40范围
var 点券70至79范围 = Math.floor(Math.random() * 10) + 20; //概率20-30范围
var 积分70至79范围 = Math.floor(Math.random() * 3) + 3; //概率3-5范围

var 抵用券80范围 = Math.floor(Math.random() * 10) + 40; //概率40-50范围
var 点券80范围 = Math.floor(Math.random() * 10) + 30; //概率30-40范围
var 积分80范围 = Math.floor(Math.random() * 6) + 5; //概率5-10范围

var headtext = ""
	var text = "";
var bomnum = 20;
var total = 100;
var score = 0;
var bomxy = new Array(); //地雷位置
var bommap = new Array(); //地雷图
var finalmap = new Array();
var initialmap = new Array();
var status = 0;
var repeat = new Array();
var repeatnum = 0;
status = 0;
var mosttime = 2; //限制每日次数
function start() {
	headtext = "决战80分扫雷活动次数:#b" + mosttime + "#k次 已挑战次数:#b #k当前得分为:#r" + score + "\r\n";
	//随机生成初始地雷数下标数组bommxy
	for (var i = 0; i < bomnum; i++) {
		while (1) {
			var sameflag = 0;
			var newbom = Math.floor(Math.random() * 100);
			for (var j = 0; j < i; j++) {
				if (newbom == bomxy[j])
					sameflag = 1;
			}
			if (!sameflag)
				break;
		}
		bomxy[i] = newbom;
	}
	//
	for (var i = 0; i < total; i++) {
		var sameflag = 0;
		for (var j = 0; j < bomnum; j++) {
			if (i == bomxy[j])
				sameflag = 1;
		}

		if (sameflag) {
			bommap[i] = 9;
		} else {
			bommap[i] = 0;
		}
	}
	//加工成完全地雷图
	for (var i = 0; i < 100; i++) {
		if (bommap[i] >= 9) {
			if (i % 10 > 0) {
				bommap[i - 11] += 1;
				if (bommap[i - 11] > 9)
					bommap[i - 11] = 9;
			}
			if (i - 10 >= 0) {
				bommap[i - 10] += 1;
				if (bommap[i - 10] > 9)
					bommap[i - 10] = 9;
			}
			if (i % 10 != 9) {
				bommap[i - 9] += 1;
				if (bommap[i - 9] > 9)
					bommap[i - 9] = 9;
			}
			if (i % 10 > 0) {
				bommap[i - 1] += 1;
				if (bommap[i - 1] > 9)
					bommap[i - 1] = 9;
			}
			if (i % 10 != 9) {
				bommap[i + 1] += 1;
				if (bommap[i + 1] > 9)
					bommap[i + 1] = 9;
			}
			if (i % 10 > 0 && i < 90) {
				bommap[i + 9] += 1;
				if (bommap[i + 9] > 9)
					bommap[i + 9] = 9;
			}
			if (i < 90) {
				bommap[i + 10] += 1;
				if (bommap[i + 10] > 9)
					bommap[i + 10] = 9;
			}
			if (i % 10 != 9 && i < 90) {
				bommap[i + 11] += 1;
				if (bommap[i + 11] > 9)
					bommap[i + 11] = 9;
			}
		}
	}
	//生成最终效果图
	for (var i = 0; i < 100; i++) {
		switch (bommap[i] % 10) {
		case 0:
			finalmap[i] = "#L" + i + "#" + 橙色0 + "#l";
			break;
		case 1:
			finalmap[i] = "#L" + i + "#" + 橙色1 + "#l";
			break;
		case 2:
			finalmap[i] = "#L" + i + "#" + 橙色2 + "#l";
			break;
		case 3:
			finalmap[i] = "#L" + i + "#" + 橙色3 + "#l";
			break;
		case 4:
			finalmap[i] = "#L" + i + "#" + 橙色4 + "#l";
			break;
		case 5:
			finalmap[i] = "#L" + i + "#" + 橙色5 + "#l";
			break;
		case 6:
			finalmap[i] = "#L" + i + "#" + 橙色6 + "#l";
			break;
		case 7:
			finalmap[i] = "#L" + i + "#" + 橙色7 + "#l";
			break;
		case 8:
			finalmap[i] = "#L" + i + "#" + 橙色8 + "#l";
			break;
		default:
			finalmap[i] = "#L" + i + "#" + 红炸弹 + "#l";
			break;
		}
		if (i % 10 == 9 && i != 0) {
			finalmap[i] += "\r\n";
		}
	}
	action(1, 0, 0);
}
function action(mode, type, selection) {
	//生成初始图
	if (status == 0) {

		//if (hour == 20 || hour == 21) {
		if (cm.getPlayer().getBossLoga("小游戏-扫雷") >= 2) {
			cm.sendOk("你当前账号活动参与次数已经消耗完毕，无法继续参与！\r\n" +
				"#d#e游戏说明：#n#k\r\n" +
				"每天每个账号可以参与2次。\r\n" +
				"扫雷活动次数:#b" + cm.getPlayer().getBossLoga("小游戏-扫雷") + "#k/2次\r\n" +
				"扫雷小于60分次数:#b" + cm.getPlayer().getBossLoga("小游戏-扫雷小于60分") + "#k\r\n" +
				"扫雷超过60分次数:#b" + cm.getPlayer().getBossLoga("小游戏-扫雷超过60分") + "#k\r\n" +
				"扫雷超过70分次数:#b" + cm.getPlayer().getBossLoga("小游戏-扫雷超过70分") + "#k\r\n" +
				"扫雷达到80分次数:#b" + cm.getPlayer().getBossLoga("小游戏-扫雷达到80分") + "#k");
			cm.dispose();
		} else if (cm.getLevel() <= 120) { //判断等级
			cm.sendOk("#e#r120级之前无法挑战扫雷.\r\n");
			cm.dispose();
			} else {

				cm.sendNext("嘿嘿，想挑战一下决战80分扫雷吗?\r\n" +
					"#d#e游戏说明：#n#k\r\n" +
					"每天每个账号可以参与2次。\r\n" +
					"0. 分数超过80后点一次地雷即可领奖。\r\n" +
					"1. 扫雷活动可以进行2次。50级以下无法参与\r\n" +
					"2. 每个账号至多可以进行2次，扫雷中途退出不计次数。\r\n" +
					"#b3. 扫雷分数 1-59  获得：#v4000313#*5。\r\n" +

					"4. 扫雷分数 60-69 获得：#v4000313#*20。\r\n" +

					"5. 扫雷分数 70-79 获得：#v2022355#*1。\r\n" +

					"6. 扫雷分数 80以上获得：#v4000313#*20+#v2022355#*1\r\n#v1902426##v1912426##v1142731#（永久）500抵用卷。\r\n" +
					"\t\t\t\t" + 开始);
				//	}
				//
				//	} else {
				//	cm.sendOk("扫雷小游戏活动时间未开始，或已经结束。");
				//	cm.dispose();
			}

			status++;
		} else if (status == 1) {
			var thistime = cm.getPlayer().getBossLoga("小游戏-扫雷");
			//if(cm.getPlayer().getBeans() < 100){
			//cm.sendOk("豆豆不足100，无法参与游戏活动。");
			//cm.dispose();
			//}
			if (thistime >= mosttime) {
				cm.sendOk("您的挑战次数已经用完.");
				cm.dispose();
				return;
			} else if (cm.haveItem(2022355, 1)) { //判断物品
				cm.sendOk("#b背包里有#v2022355#则无法开始游戏.");
				cm.dispose();
				return;
			}
			for (var i = 0; i < 100; i++) {
				if (i % 10 == 9 && i != 0) {
					initialmap[i] = "#L" + i + "#" + 黑底 + "\r\n" + "#l";
				} else {
					initialmap[i] = "#L" + i + "#" + 黑底 + "#l";
				}
				text += initialmap[i];
			}
			status++;
			cm.sendSimple(headtext + text);
		} else if (status == 2) {
			//检测是否踩雷
			for (var i = 0; i < bomnum; i++) {
				if (selection == bomxy[i]) {
					text = "";
					headtext = "噢,天呐!你踩到地雷了，最终得分为:#r" + score + "\r\n" + "\r\n";
					for (var i = 0; i < 100; i++) {
						text += finalmap[i];
					}

					//0-59分奖励
					if (score >= 0 && score < 60) {
						//cm.gainD(100);
						cm.gainItem(4000313, 5);
						cm.getPlayer().setBossLoga("小游戏-扫雷小于60分");
						cm.itemlabaB("" + cm.getName() + "", "在扫雷小游戏中，获得：" + score + "分", 4001126, 16);
					}
					//60-69分奖励
					if (score >= 60 && score < 70) {
						//cm.gainD(500);
						cm.gainItem(4000313, 20, 1);
						cm.getPlayer().setBossLoga("小游戏-扫雷超过60分");
						cm.itemlabaB("" + cm.getName() + "", "在扫雷小游戏中，获得：" + score + "分", 4001126, 16);
					}
					//70-79分奖励
					else if (score >= 70 && score < 80) {
						//cm.gainD(800);
						cm.gainItem(2022355, 1, 1);
						//cm.gainItem(2000017, 50);
						cm.getPlayer().setBossLoga("小游戏-扫雷超过70分");
						cm.itemlabaB("" + cm.getName() + "", "在扫雷小游戏中，获得：" + score + "分", 4001126, 16);
					}
					//80分奖励
					else if (score >= 80) {
						//cm.gainD(1000);
						cm.gainItem(2022355, 2, 1);
						cm.gainItem(4000313, 20, 1);
						cm.gainItem(1912426, 1);
						cm.gainItem(1902426, 1);
						//var sj = Math.floor(Math.random()*8);
						var ii = MapleItemInformationProvider.getInstance();
						var type = ii.getInventoryType(1142731); //获得装备的类形
						var toDrop = ii.randomizeStats(ii.getEquipById(1142731)).copy(); // 生成一个Equip类
						//var temptime = (System.currentTimeMillis() + 1 * 24 * 60 * 60 * 1000); //时间
						//toDrop.setExpiration(temptime);
						//toDrop. setFlag(1);//上锁
						toDrop.setStr(7); //给力量
						toDrop.setDex(17); //给敏捷
						toDrop.setInt(7); //给智力
						toDrop.setLuk(7); //给运气
						//toDrop. setHp(sj);//给HP
						//toDrop. setMp(sj);//给MP
						toDrop.setWatk(8); //攻击
						toDrop.setMatk(16); //魔法力
						toDrop.setWdef(10); //物理防御
						toDrop.setMdef(10); //魔法防御
						toDrop.setAcc(10); //命中
						toDrop.setAvoid(10); //回避
						toDrop.setHands(15); //手技
						toDrop.setSpeed(3); //移动
						//toDrop. setJump(99);//跳跃*
						cm.getPlayer().getInventory(type).addItem(toDrop); //将这个装备放入包中
						cm.getC().getSession().write(MaplePacketCreator.addInventorySlot(type, toDrop, false)); //刷新背包
						cm.getPlayer().setBossLoga("小游戏-扫雷达到80分");
						cm.itemlabaB("" + cm.getName() + "", "在扫雷小游戏中，获得：" + score + "分，奖励扫雷之王称号 ", 4001126, 16);
					}
					cm.getPlayer().setBossLoga("小游戏-扫雷");
					//cm.sendOk(headtext + text);
					cm.dispose();
					return;
				}
			}
			repeat[repeatnum] = selection;
			initialmap[selection] = finalmap[selection];
			var samescoreflag = 0;
			//重复点不加分
			for (var i = 0; i < repeatnum; i++) {
				if (repeat[i] == selection) {
					samescoreflag = 1;
				}
			}
			if (samescoreflag == 0) {
				score += 1;
				repeat[i] = selection
					repeatnum += 1;
			}
			headtext = "80分扫雷活动次数:#b" + mosttime + "#k次 已挑战次数:#b" + cm.getPlayer().getBossLoga("小游戏-扫雷") + " #k当前得分为:#r" + score + "\r\n";
			text = "";
			for (var i = 0; i < 100; i++) {
				text += initialmap[i];
			}
			cm.sendSimple(headtext + text);
		}
	}
