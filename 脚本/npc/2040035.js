/* ==================
 脚本类型:  玩具副本	    
 脚本作者： 颜林 
 联系方式： 1500663066
 =====================
 */
var status;

var itemList = new Array(1032055, 1032056, 1032057, 1032058, 1052165, 1052166, 1052167, 1002797, 1002800, 1072366, 1072368, 1072262, 1072264, 1082244, 1082245, 1102174, 1322003, //equips
		2100000, 2100017, 2100018, 2100019, 2100037, 2100038, 2100039, 2100040, 2044901, 2044902, 2044802, 2044801, 2044702, 2044701, 2044602,
		2044601, 2044501, 2044502, 2044402, 2044401, 2044302, 2044301, 2044201, 2044202, 2044102, 2044101,
		2044002, 2044001, 2043802, 2043801, 2043702, 2043701, 2043302, 2043301, 2043202, 2043201, 2043102,
		2043101, 2043002, 2043001, 2040801, 2040814, 2040815, 2040816, 2040817, 2040802, 2040915, 2040914, 2040805, 2040804, 2040532, 2040534, 2040517, 2040516,
		2040514, 2040513, 2040502, 2040501, 2040323, 2040321, 2040317, 2040316, 2040302, 2040301, //1x use items
		2000002, 2000003, 2000004, 2000005, 2000006, 2000006, 2000006, 2000006, 2000006, 2000005, 2000005,
		2000005, 2000005, 2000002, 2000002, 2000002, 2000002, 2000003, 2000003, 2000003, 2000004, 2000004,
		2000004, 2000004, 2022003, 2070004, 2070005, //multiuse items

		4020000, 4020000, 4020001, 4020001, 4020002, 4020002, 4020003, 4020003, 4020004, 4020004, 4020005,
		4020005, 4020006, 4020006, 4010000, 4010000, 4010001, 4010001, 4010002, 4010002, 4010003, 4010003,
		4010004, 4010004, 4010005, 4010005, 4010006, 4020007, 4020008, 4003000); //etc itemsitems

var randNum = Math.floor(Math.random() * (itemList.length));
var randItem = itemList[randNum];
var qty;

switch (randItem) {
case 4020000:
case 4020001:
case 4020002:
case 4020003:
case 4020004:
case 4020005:
case 4020006:
case 4010000:
case 4010001:
case 4010002:
case 4010003:
case 4010004:
case 4010005:
	qty = 16;
	break;
case 4010006:
case 4020007:
case 4020008:
	qty = 8;
	break;
case 4003000:
	qty = 30;
	break;
case 2000002:
case 2000006:
	qty = 100;
	break;
case 2000003:
	qty = 200;
	break;
case 2000004:
	qty = 50;
	break;
case 2000005:
case 2022003:
	qty = 10;
	break;
default:
	qty = 1;
}

function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	}
	if (mode == 1)
		status++;
	else
		status--;
	if (status == 0) {
		cm.sendNext("请确认你的背包有没有满。");
	} else if (status == 1) {
		if (cm.getInventory(1).isFull(0)) { //判断第一个也就是装备栏的装备栏是否有一个空格
			cm.sendOk("#b请保证装备栏位至少有1个空格,否则无法领取.");
			cm.dispose();
		} else if (cm.getInventory(2).isFull(0)) { //判断第二个也就是消耗栏的装备栏是否有一个空格
			cm.sendOk("#b请保证消耗栏位至少有1个空格,否则无法领取.");
			cm.dispose();
		} else if (cm.getInventory(3).isFull(0)) { //判断第三个也就是设置栏的装备栏是否有一个空格
			cm.sendOk("#b请保证设置栏位至少有1个空格,否则无法领取.");
			cm.dispose();
		} else if (cm.getInventory(4).isFull(0)) { //判断第四个也就是其它栏的装备栏是否有一个空格
			cm.sendOk("#b请保证其它栏位至少有1个空格,否则无法领取.");
			cm.dispose();
		} else if (cm.getInventory(5).isFull(0)) { //判断第五个也就是现金栏的装备栏是否有一个空格
			cm.sendOk("#b请保证现金栏位至少有1个空格,否则无法领取.");
			cm.dispose();
		} else {

			cm.removeAll(4001022);
			cm.removeAll(4001023);
			cm.getPlayer().endPartyQuest(1202);
			//cm.setPartyBosslog("玩具副本");//给团队每日副本积分
			cm.getPlayer().setBossLog("玩具副本");//给个人副本积分
			cm.getPlayer().setOneTimeLog("wanju"); //给永久记录
			副本奖励(45);
			cm.gainItem(randItem, qty);
			cm.warp(221024500);
			gain积分("玩具积分",+1);
	        gain积分("副本积分",+1);
			if (cm.isLeader()) {
				cm.worldMessage(6, "恭喜玩家：[" + cm.getName() + "]带领他/她的队伍通过了玩具副本!");
				cm.dispose();
			} else {
				cm.worldMessage(6, "恭喜玩家：[" + cm.getName() + "]跟他/她的队伍通过了玩具副本!");
				cm.dispose();
			}
		}
	}
}

function 副本奖励(a) { //奖励最低4001094
    cm.gainItem(4010003, 1);	//给物品 龙蛋
	cm.gainItem(4010004, 1);
	cm.gainItem(4010005, 1);
	cm.gainItem(4010006, 1);
	cm.gainItem(4020000, 1);
	cm.gainItem(4000313, 3);
	cm.gainItem(4001126, 2);
	cm.gainItem(4170005, 1);
	if (cm.getLevel() >= a) { //判断等级超过45
	//	cm.gainDJ(88); //给点券第二种函数
		cm.gainExp(100000); //给经验的意思
		cm.gainItem(4000463, 1);
		cm.gainMeso(50000);//金币5w
		cm.worldMessage(6, "恭喜玩家：[" + cm.getName() + "]跟他/她的队伍获得玩具副本丰厚的奖励!");
	}

}
function get积分(a) {
	var jf = 0;
		switch (a) {
		case "废弃积分":
			jf = Number(cm.getQuestRecord(844440).getCustomData());
			break;
		case "玩具积分":
			jf = Number(cm.getQuestRecord(844441).getCustomData());
			break;
		case "天空积分":
			jf = Number(cm.getQuestRecord(844442).getCustomData());
			break;
		case "海盗积分":
			jf = Number(cm.getQuestRecord(844443).getCustomData());
			break;
		case "毒物积分":
			jf = Number(cm.getQuestRecord(844444).getCustomData());
			break;
		case "月妙积分":
			jf = Number(cm.getQuestRecord(844445).getCustomData());
			break;
		case "狗男积分":
			jf = Number(cm.getQuestRecord(844446).getCustomData());
			break;
		case "副本积分":
			jf = Number(cm.getQuestRecord(844447).getCustomData());
			break;
		case "带新积分":
			jf = Number(cm.getQuestRecord(844450).getCustomData());
			break;
		}

		return jf;
}

function gain积分(a,b) {
	var jf = 0;
		switch (a) {
		case "废弃积分":
			jf = Number(cm.getQuestRecord(844440).getCustomData());
			cm.getQuestRecord(844440).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "玩具积分":
			jf = Number(cm.getQuestRecord(844441).getCustomData());
			cm.getQuestRecord(844441).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "天空积分":
			jf = Number(cm.getQuestRecord(844442).getCustomData());
			cm.getQuestRecord(844442).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "海盗积分":
			jf = Number(cm.getQuestRecord(844443).getCustomData());
			cm.getQuestRecord(844443).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "毒物积分":
			jf = Number(cm.getQuestRecord(844444).getCustomData());
			cm.getQuestRecord(844444).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "月妙积分":
			jf = Number(cm.getQuestRecord(844445).getCustomData());
			cm.getQuestRecord(844445).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "狗男积分":
			jf = Number(cm.getQuestRecord(844446).getCustomData());
			cm.getQuestRecord(844446).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "副本积分":
			jf = Number(cm.getQuestRecord(844447).getCustomData());
			cm.getQuestRecord(844447).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "带新积分":
			jf = Number(cm.getQuestRecord(844450).getCustomData());
			cm.getQuestRecord(844450).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		}

}


