var itemList = new Array(
//兑换后物品id 兑换后数量 需要物品ID 需要物品数量
	Array(1302289,1,4001126,500),//革命剑
	Array(1312165,1,4001126,500),//革命战斧
	Array(1402210,1,4001126,500),//革命双手剑
	Array(1412147,1,4001126,500),//革命双手战斧
	Array(1432178,1,4001126,500),//革命之矛
	Array(1442234,1,4001126,500),//革命长枪
	Array(1332238,1,4001126,500),//革命切割者
	Array(1472226,1,4001126,500),//革命拳甲
	Array(1372188,1,4001126,500),//革命短杖
	Array(1382101,1,4001126,500),//革命长杖
	Array(1452216,1,4001126,500),//革命弓
	Array(1462093,1,4001126,500),//革命弩
	Array(1482179,1,4001126,500),//革命冲拳
	Array(1492190,1,4001126,500) //革命红杰克
	
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
            msg += "使用#i" + itemList[i][2] + ":# × " + itemList[i][3] + " 兑换 #z" + itemList[i][0] + ":# × " + itemList[i][1] + "#l\r\n";
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