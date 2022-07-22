var weapon = new Array(
//合成物品ID,前一个装备ID
	//武器 ffn    巨匠
	Array(1302275,1302285), //单手剑
	Array(1312153,1312162),//单手斧
	Array(1332225,1332235),//短刀飞侠
	Array(1342082,1342084),//飞侠小刀
	Array(1372177,1372186),//短仗
	Array(1382208,1382220), //长仗
	Array(1402196,1402204),//双手剑
	Array(1412135,1412144),//双手斧
	Array(1432167,1432176),//枪
	Array(1442223,1442232),//矛
	
	Array(1452205,1452214), //弓
	Array(1462193,1462202),//弩
	Array(1472214,1472223),//拳套
	Array(1482168,1482177),//指节
	Array(1492179,1492188)//短枪
	
	
	
	
	
	
);
//公共材料设置
var req = [
    [4310059, 1],//绝世币
    [4310012, 10],//十字币
	[4310029, 10],//十字币
    [4310034, 10],//正义币
    [4251202, 10],//高等五彩水晶
	[4001126, 8888],//枫叶
	[4000313, 8888],//黄金枫叶
	[4310143, 50],//boss币
	[4000463, 88]//国庆纪念币
];
var rem = 8888;
var gailv = 100;//输入百分之几不要输入百分号只要数字在里面就行了!!
var sels;
var status = -1;
/* 
测试概率代码 
		var aa = "";
		for (var i = 0; i < 1000; i++) {
			s1 = Math.floor(Math.random() * (100 - 1) + 1);
			if(s1 <= gailv){
				aa +="#g"+s1+"#k ";
			} else {
				aa +="#r"+s1+"#k ";
			}
		}
		cm.sendOk(aa);
*/
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
		msg += "\t\t";
		msg += rem+"金币";
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
			Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『合成中心』" + " : " + "[" + cm.getChar().getName() + "]成功合成了法弗纳装备！！")); 
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