var weapon = [1462202, 1472223, 1372186, 1302285, 1322213, 1402204, 1422149,1332235,1442232,1382220,1312162,1412144,1482177,1452214,1432176];
var req = [
    [4251200, 10],//下等五彩水晶 
	[4021009, 20],//星石
    [4011007, 20],//月石
	//[4000010, 400],//蓝蜗牛珠
	[4001126, 15000],//枫叶
	[4000313, 1000],//黄金枫叶
	
    
     
    
    //[4021008, 20],//黑水晶 
	//[4000016, 400],//红色蜗牛壳
    //[4000000 ,400],//蓝色蜗牛壳
	[4001083, 1],//扎昆
	[4001084, 1],//闹钟
	[4001085, 1],//鱼王
	//[4310030, 20],//运动会币
	//[4310030, 20],//运动会币
	[4000487, 150],//暗影币
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
        cm.sendSimple("#b#e您好，制作#r巨匠武器#b需要以下材料，没有材料可不行哦\r\n\r\n" + msg + "");
    } else if (status == 1) {
        sels = selection;
        if (!cm.canHold(weapon[sels])) {
            cm.sendNext("#r背包空间不足");
            cm.dispose();
            return;
        }
        for (var i = 0; i < req.length; i++) {
            if (!cm.haveItem(req[i][0], req[i][1])) {
                cm.sendNext("#b你身上没有#r足够的材料#k，继续收集材料去吧！");
                cm.dispose();
                return;
            }
        }
        cm.sendYesNo("#b是否要兑换#r巨匠武器系列#r #i" + weapon[sels] + "#? \r\n");
    } else if (status == 2) {
        for (var i = 0; i < req.length; i++) {
            cm.gainItem(req[i][0], -req[i][1]);
        }
        cm.gainItem(weapon[sels], 1);
        cm.sendNext("#b已经兑换好了，请前往背包查看 #i" + weapon[sels] + "#");
        cm.dispose();
    } else {
        //cm.sendNext("#r发生错误: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}