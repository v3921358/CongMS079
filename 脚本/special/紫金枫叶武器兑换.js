var itemList = new Array(
//兑换后物品id 兑换后数量 需要物品ID 需要物品数量
	Array(1302212,1,4001126,500),//紫金枫叶剑
	Array(1312114,1,4001126,500),//紫金枫叶斧
	Array(1402145,1,4001126,500),//紫金枫叶双手剑
	Array(1412102,1,4001126,500),//紫金枫叶双手战斧
	Array(1432135,1,4001126,500),//紫金枫叶之枪
	Array(1442173,1,4001126,500),//紫金枫叶矛
	Array(1332186,1,4001126,500),//紫金枫叶龙牙破
	Array(1472177,1,4001126,500),//紫金枫叶拳甲
	Array(1372131,1,4001126,500),//紫金枫叶治愈短杖
	Array(1382160,1,4001126,500),//紫金枫叶治愈长杖
	Array(1452165,1,4001126,500),//紫金枫叶弓
	Array(1462156,1,4001126,500),//紫金枫叶弩
	Array(1482138,1,4001126,500),//紫金枫叶冲拳
	Array(1492138,1,4001126,500) //紫金枫叶红杰克
	
);
var sels;
var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
        var msg = "";
        msg += "请选择你要兑换的物品:\r\n\r\n";
        msg += "#g------------------------------------------------------\r\n";
        for (var i = 0; i < itemList.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "使用 #i" + itemList[i][2] + ":# × " + itemList[i][3] +" 兑换 #z" + itemList[i][0] + ":# × " + itemList[i][1] + "#l\r\n";
        }
        cm.sendSimple("" + msg + "");
    } else if (status == 1) {
        sels = selection;
        if (!cm.canHold(itemList[sels][0])) {
            cm.sendNext("#r背包空间不足");
            cm.dispose();
            return;
        }
        if (!cm.haveItem(itemList[sels][2], itemList[sels][3])) {
			cm.sendNext("#b身上没有#r#i" + itemList[sels][2] + "##t" + itemList[sels][2] + "#x" + itemList[sels][3] + "");
            cm.dispose();
            return;
        }
        cm.sendYesNo("#b是否要兑换#r #i" + itemList[sels][0] + "# × " + itemList[sels][1] + "? \r\n");
    } else if (status == 2) {
        cm.gainItem(itemList[sels][2], -itemList[sels][3]);
        cm.gainItem(itemList[sels][0], itemList[sels][1]);
        cm.sendNext("#b已经兑换了 #i" + itemList[sels][0] + "# × "+itemList[sels][1]+"");
        cm.dispose();
    } else {
        //cm.sendNext("#r发生错误: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}