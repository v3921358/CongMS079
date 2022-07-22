var weapon = [
1332055, 
1482022,
1372034, 
1382039, 
1402039, 
1412027, 
1432040, 
1452045, 
1462040, 
1472055,
1442024,
1312056,
1492095,
1302064,
1052202,//套服
1072533,//鞋子
1102071,//披风
1082440,//手套
1003454,//枫叶冒
1092030,//枫叶盾
1092045,//法师盾
1092046,//战士盾
1092047//飞侠盾

];

var req = [
	[4001126, 100],
	[4000000, 88],//蓝蜗牛壳
	[4000016, 88],//红蜗牛壳
	[4000019, 88],//绿蜗牛壳
    [4000313, 5]
];
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
        msg += "\r\n#d需要:#b ";
        msg += "\r\n";
        for (var ii = 0; ii < req.length; ii++) {
            msg += "#i" + req[ii][0] + "##t" + req[ii][0] + "#x" + req[ii][1];
            if (ii % 3 == 0) {
                msg += "\r\n";
            }
        }
        msg += "\r\n";
        msg += "#g----------------------------------------------\r\n请选择您想要铸造的装备：\r\n";
        for (var i = 0; i < weapon.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "#i" + weapon[i] + ":##l\n";
        }
        cm.sendSimple("#r              #v4000110# 新 手 装 备 制 造 #v4000110#\r\n 想要铸造吗？\r\n" + msg + "");
    } else if (status == 1) {
        sels = selection;
        if (!cm.canHold(weapon[sels])) {
            cm.sendNext("#r背包空间不足");
            cm.dispose();
            return;
        }
        for (var i = 0; i < req.length; i++) {
            if (!cm.haveItem(req[i][0], req[i][1])) {
                cm.sendNext("#b身上没有#r#i" + req[i][0] + "##t" + req[i][0] + "#x" + req[i][1] + "");
                cm.dispose();
                return;
            }
        }
        cm.sendYesNo("#b是否要铸造#r #i" + weapon[sels] + "#? \r\n");
    } else if (status == 2) {
        for (var i = 0; i < req.length; i++) {
            cm.gainItem(req[i][0], -req[i][1]);
        }
        cm.gainItem(weapon[sels], 1);
		Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『合成中心』" + " : " + "[" + cm.getChar().getName() + "]成功合成了4周年装备！！")); 
        cm.sendNext("#b已经铸造了武器 #i" + weapon[sels] + "#");
        cm.dispose();
    } else {
        //cm.sendNext("#r发生错误: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}