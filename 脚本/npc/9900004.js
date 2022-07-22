
var FY0 = "┏━━━━━━━━━━━┓";
var FY1 = "┃       - 枫叶 -       ┃";
var FY2 = "┃ 脚本仿制  　定制脚本 ┃";
var FY3 = "┃ 技术支持 　 游戏顾问 ┃";
var FY4 = "┃ ＷＺ添加　  地图制作 ┃";
var FY5 = "┣━━━━━━━━━━━┫";
var FY6 = "┃ 唯一QQ:1848350048    ┃";
var FY7 = "┗━━━━━━━━━━━┛";

var xmxsz = new Array(
{ 物品: 5210000, 时间: 3,  点卷: 0,    log: "每日免费领取双倍" },
{ 物品: 5360000, 时间: 3,  点卷: 100 , log: "每日购买双爆"},
{ 物品: 5211060, 时间: 2,  点卷: 150 , log: "每日购买三倍"}

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
        msg += "请选择你需要购买的物品:\r\n\r\n";
        
        for (var i = 0; i < xmxsz.length; i++) {
            msg += "#r#L" + i + "#";
            msg += "#v" + xmxsz[i].物品 + "##t" + xmxsz[i].物品 + "# 时限：" + xmxsz[i].时间 + "小时  需要 点卷 " + xmxsz[i].点卷 + "#l\r\n";
        }
        cm.sendSimple("" + msg + "");
    } else if (status == 1) {
        sels = selection;
        if (cm.getBossLog(xmxsz[sels].log) >= 1) {
			cm.sendNext("#r每天只能一次哦！");
            cm.dispose();
            return;
		}
		
		if (!cm.canHold(xmxsz[sels].物品)) {
            cm.sendNext("#r背包空间不足");
            cm.dispose();
            return;
        }
        if (cm.getPlayer().getCSPoints(1) < xmxsz[sels].点卷) {
			cm.sendNext("点卷不足，无法兑换！");
            cm.dispose();
            return;
        }
        cm.sendYesNo("#b是否要兑换#r #v" + xmxsz[sels].物品 + "##t" + xmxsz[sels].物品 + "#? \r\n");
    } else if (status == 2) {
		cm.setBossLog(xmxsz[sels].log);
        cm.getPlayer().modifyCSPoints(1, -xmxsz[sels].点卷, true);
        cm.gainItem(xmxsz[sels].物品,1,xmxsz[sels].时间);
        cm.sendNext("#b成功兑换物品");
        cm.dispose();
    } else {
        cm.sendNext("#r发生错误: mode : " + mode + " status : " + status);
        cm.dispose();
    }
}