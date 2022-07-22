var weapon = [


//防具



//武器
	1302275,
	1312153,
	1332225,
	1342082,
	1372177,
	1382208,
	1402196,
	1412135,
	1432167,
	1442223,
	1452205,
	1462193,
	1472214,
	1482168,
	1492179


];
var req = [
//需要材料
	[4251202, 20],
    [4250902, 20],
	[4250802, 20],
	[4251102, 20],
	[4251002, 20],
    [4000313, 1000],//黄金枫叶
    [4000487, 100],//暗影币
    [4002000, 100],//蜗牛邮票
	[4000463, 100]//中介币
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
        msg += "\r\n\r\n";
        for (var ii = 0; ii < req.length; ii++) {
            msg += "#i" + req[ii][0] + "##z" + req[ii][0] + "#x" + req[ii][1];
            if (ii % 3 == 0) {
                msg += "\r\n";
            }
        }
        msg += "\r\n";
        msg += "#g----------------------------------------------\r\n";
        for (var i = 0; i < weapon.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "#i" + weapon[i] + "##z" + weapon[i] + "##l\r\n";
        }
        cm.sendSimple("#r         #v4000110# 我乃铸造炉 #v4000110#\r\n 想要铸造吗？\r\n" + msg + "");
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
        cm.sendYesNo("#b是否要铸造武器#r #i" + weapon[sels] + "#? \r\n");
    } else if (status == 2) {
        for (var i = 0; i < req.length; i++) {
            cm.gainItem(req[i][0], -req[i][1]);
        }
        cm.gainItem(weapon[sels], 1);
        cm.sendNext("#b已经铸造了武器 #i" + weapon[sels] + "#");
        cm.dispose();
    } else {
        //cm.sendNext("#r发生错误: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}