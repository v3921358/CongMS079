var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var status = 0;
//普通奖池
var itemList1 = [
//[ID, 概率(1-100%), 数量, 是否喇叭(1为抽到该物品就广播,0抽到不广播)]	

[3015304, 1, 1, 1],//大水车
[3010417, 1, 1, 1],//巨无霸企鹅王
[3015439, 1, 1, 1],//木偶戏椅子
[3015051, 1, 1, 1],
[3010448, 8, 1,1]





];

var useNx = 400;
var sel0 = -1;
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }

     if(status == 0) {

		var txt = "恭喜你获得道具：\r\n";
			var item;
			var ran = Math.floor(Math.random() * 100);
			var ran1 = null;
			ran1 = finalGift(itemList1);			
				cm.gainGachaponItem2(ran1[0], ran1[2], "黑龙箱子", 1);
			txt += "#v" + ran1[0] + "#\r\n";
			var result = cm.setBossRankCount("随机奖池抽奖");
		
		cm.dispose();
		return;
    }
}

function finalGift(lists) {
	var maxChance = 0;
	for (var i in lists) {
		if (lists[i][1] > maxChance) {
			maxChance = lists[i][1];
		}
	}
	var chance = Math.floor(Math.random() * maxChance);
	var finalitem = Array();
	for (var i = 0; i < lists.length; i++) {
		if (lists[i][1] >= chance) {
			finalitem.push(lists[i]);
		}
	}
	var ran1 = Math.floor(Math.random() * finalitem.length);
	return finalitem[ran1];
}