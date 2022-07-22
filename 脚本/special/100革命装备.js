var 金币图标 = "#fUI/UIWindow.img/QuestIcon/7/0#";
var weapon = new Array(

	Array(1003946,1003529),//帽子+
	Array(1052647,1052457),//衣服
	Array(1072853,1072660),//鞋子
	Array(1082540,1082430),//手套+
	Array(1102612,1102394),//披风+
	//Array(1132151,1132151),//腰带
	Array(1302289,1302212),//紫金枫单手剑剑+
	Array(1312165,1312114),//紫金枫单手斧
	Array(1402210,1402145),//紫金枫叶双手剑+
	Array(1412147,1412102),//紫金枫叶双手战斧+
	Array(1432178,1432135),//紫金枫叶之枪+
	Array(1442234,1442173),//紫金枫叶矛+
	Array(1332238,1332186),//紫金枫叶刀+
	Array(1472226,1472177),//紫金枫叶拳甲+
	Array(1372188,1372131),//紫金枫叶治愈短杖+
	Array(1382222,1382160),//紫金枫叶治愈长杖+
	Array(1452216,1452165),//紫金枫叶弓+
	Array(1462093,1462156),//紫金枫叶弩+
	Array(1482179,1482138),//紫金枫叶拳爪+
	Array(1492190,1492138)//紫金枫叶红杰克抢+
	
	

	
	
	
);
//公共材料设置
var req = [
    [4310029, 2],//十字币
    [4310034, 2],//正义币
    [4001126, 8888],//枫叶
	[4000313, 300],//黄金枫叶
	[4251200, 1],//下等五彩
	[4000463, 88]
];
var rem = 100000000;
var gailv = 100;//输入百分之几不要输入百分号只要数字在里面就行了!!
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
            msg += "#i" + req[ii][0] + ":##z" + req[ii][0] + "#x" + req[ii][1];
            if (ii % 2 == 0 && ii !=0) {
                msg += "\r\n";
            }
        }
		
		msg += " "+金币图标+":#r1亿#k";
        msg += "\r\n";
        msg += "#g----------------------------------------------\r\n";
        for (var i = 0; i < weapon.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "#i" + weapon[i][1] + ":##z" + weapon[i][1] + "# #b升级#r #i" + weapon[i][0] + ":##z" + weapon[i][0] + "##l\r\n";
        }
        cm.sendSimple("#d想要合成装备吗? \t\t目前合成概率为: #r#e"+gailv+"%#d#n\r\n温馨提示：合成失败材料不返还，请选择：" + msg + "");
    } else if (status == 1) {
        sels = selection;
        if (!cm.canHold(weapon[sels][0])) {
            cm.sendNext("#r背包空间不足");
            cm.dispose();
            return;
        }
        for (var i = 0; i < req.length; i++) {
            if (!cm.haveItem(req[i][0], req[i][1])) {
                cm.sendNext("#b身上没有#r#i" + req[i][0] + ":##z" + req[i][0] + "#x" + req[i][1] + "");
                cm.dispose();
                return;
            }
        }
		if (!cm.haveItem(weapon[sels][1],1)) {
            cm.sendNext("#b身上没有#r#i" + weapon[sels][1] + ":##z" + weapon[sels][1] + "#");
            cm.dispose();
            return;
        }
		if(cm.getMeso() < rem){
			cm.sendNext("#b身上没有#r"+rem+"金币");
            cm.dispose();
            return;
		}
        cm.sendYesNo("#b是否要兑换装备#r #i" + weapon[sels] + ":#? \r\n");
    } else if (status == 2) {
		s1 = Math.floor(Math.random() * (100 - 1) + 1);
		if(s1 <= gailv){
			for (var i = 0; i < req.length; i++) {
				cm.gainItem(req[i][0], -req[i][1]);
			}
			cm.gainMeso(-rem);
			cm.gainItem(weapon[sels][1],-1);
			cm.gainItem(weapon[sels][0], 1);
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『合成中心』" + " : " + "[" + cm.getChar().getName() + "]成功合成了精品革命装备！！")); 
			cm.sendNext("#b已经兑换了武器 #i" + weapon[sels] + "#");
			cm.dispose();
		} else {
			for (var i = 0; i < req.length; i++) {
				cm.gainItem(req[i][0], -req[i][1]);
			}
			cm.gainMeso(-rem);
			cm.sendNext("#b合成失败,你投入的材料消失了~!");
			cm.dispose();
		}
    } else {
        //cm.sendNext("#r发生错误: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}