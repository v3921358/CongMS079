
var 小烟花 ="#fMap/MapHelper/weather/squib/squib4/1#";
var qilin = new Array(
{ 物品: 5211047, 时间: 2,  点卷: 0,    log: "免费双倍卡" },
{ 物品: 5211047, 时间: 3,  点卷: 5000,    log: "购买双倍卡" },
{ 物品: 5360015, 时间: 3,  点卷: 5000 , log: "购买双爆卡"},
{ 物品: 5211060, 时间: 3,  点卷: 9800 , log: "购买三倍卡"}


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
	
        msg += "\t\t"+彩虹+"  #e#d 双 倍 领 取 #k#n  #r  "+彩虹+"#b#k#n\r\r\n"+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+中条猫+"\r\n嗨，请选择你需要领取的卡片，领取后立即生效加倍\r\n";
        //{ 物品: 1122017, 时间: 24*7,  点卷: 200000 , log: "购买精灵吊坠"}
        for (var i = 0; i < qilin.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "#v" + qilin[i].物品 + "" +  "# 时限：" + qilin[i].时间 + "小时  " + qilin[i].log + "  需要 点卷 " + qilin[i].点卷 + "#l\r\n";
        }
        cm.sendSimple("" + msg + "");
    } 
	else if (status == 1) {
        sels = selection;
        if (cm.getBossLog(qilin[sels].log) >= 1) {
			cm.sendNext("#r每天只能一次哦！");
            cm.dispose();
            return;
		}
		
		if (!cm.canHold(qilin[sels].物品)) {
            cm.sendNext("#r背包空间不足");
            cm.dispose();
            return;
        }
        if (cm.getPlayer().getCSPoints(1) < qilin[sels].点卷) {
			cm.sendNext("点卷不足，无法兑换！");
            cm.dispose();
            return;
        }
        cm.sendYesNo("#b是否要兑换#r #v" + qilin[sels].物品 + "##t" + qilin[sels].物品 + "#? \r\n");
    } else if (status == 2) {
		cm.setBossLog(qilin[sels].log);
        cm.getPlayer().modifyCSPoints(1, -qilin[sels].点卷, true);
        cm.gainItem(qilin[sels].物品,1,qilin[sels].时间);
		cm.getPlayer().getStat().recalcLocalStats();
        cm.sendNext("#b成功兑换物品");
        cm.dispose();
    } else {
        cm.sendNext("#r发生错误: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}
var 花草 ="#fEffect/SetEff/208/effect/walk2/4#";
var 花草1 ="#fEffect/SetEff/208/effect/walk2/3#";
var 小花 ="#fMap/MapHelper/weather/birthday/2#";
var 桃花 ="#fMap/MapHelper/weather/rose/4#";
var 金枫叶 ="#fMap/MapHelper/weather/maple/2#";
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 彩虹 ="#fEffect/ItemEff/1071085/effect/walk1/2#";
var 中条猫 ="#fUI/ChatBalloon/37/n#";
var 猫右 =  "#fUI/ChatBalloon/37/ne#";
var 猫左 =  "#fUI/ChatBalloon/37/nw#";
var 右 =    "#fUI/ChatBalloon/37/e#";
var 左 =    "#fUI/ChatBalloon/37/w#";
var 下条猫 ="#fUI/ChatBalloon/37/s#";
var 猫下右 ="#fUI/ChatBalloon/37/se#";
var 猫下左 ="#fUI/ChatBalloon/37/sw#";