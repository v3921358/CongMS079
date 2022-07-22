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
[3010070, 1, 1, 1],
[3015339, 1, 1, 1],
[3015332, 1, 1, 1],
[3015430, 10, 1, 1],//年兽椅子
[3015428, 10, 1, 1],//烟花椅子
[3015427, 10, 1, 1],//猴子椅子
[3015425, 10, 1, 1],//猴子椅子
[3015419, 5, 1, 1],//猴子椅子
[3015415, 5, 1, 1],//猴子椅子
[3015407, 5, 1, 1],//圣诞节椅子
[3015403, 5, 1, 1],//品克斌椅子
[3015398, 5, 1, 1],
[3015397, 5, 1, 1],
[3015393, 5, 1, 1],
[3015330, 5, 1, 1],
[3015312, 5, 1, 1],
[3015300, 5, 1, 1],
[3015279, 5, 1, 1],
[3010032, 36, 1, 1],
[3015287, 10, 1, 1],
[3015374, 10, 1, 1],
[3015344, 10, 1, 1],
[3015313, 10, 1, 1],
[3015306, 10, 1, 1],
[3015305, 10, 1, 1],
[3015297, 10, 1, 1],
[3015288, 15, 1, 1],
[3015273, 15, 1, 1],
[3015271, 20, 1, 1],
[3015268, 20, 1, 1],
[3015264, 20, 1, 1],
[3015260, 20, 1, 1],
[3015259, 20, 1, 1],
[3015258, 20, 1, 1],
[3015245, 20, 1, 1],
[3015243, 20, 1, 1],
[3015238, 10, 1, 1],
[3015235, 10, 1, 1],
[3015234, 10, 1, 1],
[3015227, 5, 1, 1],
[3015225, 5, 1, 1],
[3015223, 5, 1, 1],
[3015195, 15, 1, 1],
[3015175, 15, 1, 1],
[3015133, 15, 1, 1],
[3015112, 20, 1, 1],
[3015089, 20, 1, 1],
[3010946, 20, 1, 1],
[3010908, 40, 1, 1],
[3010920, 40, 1, 1],
[3010907, 40, 1, 1],
[3010806, 20, 1, 1],
[3010016, 58, 1, 1],
[3010004, 58, 1, 1],
[3010005, 58, 1, 1],
[3010097, 58, 1, 1],
[3010112, 38, 1, 1],
[3010320, 38, 1, 1],
[3010665, 38, 1, 1],
[3012017, 38, 1, 1],
[3015137, 30, 1, 1],
[3015244, 30, 1, 1],
[3010009, 5, 1, 1],
[3010063, 5, 1, 1],
[3010065, 5, 1, 1],
[3010080, 5, 1, 1],
[3010086, 5, 1, 1],
[3010092, 5, 1, 1],
[3010108, 5, 1, 1],
[3010114, 5, 1, 1],
[3010115, 5, 1, 1],
[3010138, 5, 1, 1],
[3010132, 5, 1, 1],
[3010133, 5, 1, 1],
[3010194, 5, 1, 1],
[3010376, 5, 1, 1],
[3010377, 5, 1, 1],
[3010404, 5, 1, 1],
[3010429, 5, 1, 1],
[3010513, 5, 1, 1],
[3010733, 5, 1, 1],
[3010113, 5, 1,1],
[3010186, 5, 1,1],
[3010208, 5, 1,1],
[3010374, 5, 1,1],
[3010431, 5, 1,1],
[3010432, 5, 1,1],
[3010433, 5, 1,1],
[3010434, 8, 1,1],
[3010435, 8, 1,1],
[3010436, 8, 1,1],
[3010437, 8, 1,1],
[3010438, 8, 1,1],
[3010439, 8, 1,1],
[3010440, 8, 1,1],
[3010441, 8, 1,1],
[3010442, 8, 1,1],
[3010446, 8, 1,1],
[3010447, 8, 1,1],
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

    if (status == 0) {
    	var txt = "#d              每抽一次奖多赠送一个黄金枫叶\r\n";
    	//txt += "#r#L1##b点券抽奖#l\r\n\r\n\r\n";
		txt += "#d#L2##b"+红色箭头+"进行抽奖#l\r\n\r\n";
		txt += "\t#b"+正方箭头+"奖品展示： \r\n";
		
		var txt2 = "";
		for (var i = 0; i < itemList1.length;  i++){
			txt2 += "#i"+itemList1[i][0]+":#";
		}
    	cm.sendSimple(txt + txt2);//可抽奖页面查看奖品
		cm.sendSimple(txt );
    } else if (status == 1) {
		sel0 = selection;
		cm.sendGetNumber("#d       请输入抽奖次数\r\n"
		+"#d       枫叶抽奖400枫叶一次\r\n"
		//+"#r抽奖有保底，请看群文件"
		+"#r       当前拥有枫叶数量: #v4001126##r#c4001126#个#k\r\n#r " ,
		1, 1, 10
		);
	} else if(status == 2) {
		if (!cm.checkNumSpace(0, selection)) {
			cm.sendOk("背包空间不足"+selection+"格");
			cm.dispose();
			return;
		}
    	switch (sel0) {
			case 1:
                if (cm.getPlayer().getCSPoints(1) < (useNx*selection)) {
					cm.sendOk("枫叶不足"+(useNx*selection)+"，无法抽奖");
					cm.dispose();
					return;
				} else {
					cm.getPlayer().modifyCSPoints(1, -useNx*selection);
				}
				break;
			case 2:
                if (!cm.haveItem(4001126,400*selection)) {
					cm.sendOk("#v4001126#数量不足#r "+(400*selection)+" #k，无法抽奖");
					cm.dispose();
					return;
				} else {
					cm.gainItem(4001126, -(400*selection));
				}
				break;
			default:
				cm.sendOk("脚本出错，请联系管理员");
				cm.dispose();
				return;
        }
		var txt = "恭喜你获得道具：\r\n";
		for (var i = 0; i < selection; i++) {
			var item;
			var ran = Math.floor(Math.random() * 100);
			var ran1 = null;
			ran1 = finalGift(itemList1);
			if(cm.getBossRankCount("屏蔽"+ran1[0]) > 0){
				cm.gainGachaponItem2(4001126, 1, "抽奖机", ran1[3]);
			}else{
				cm.gainGachaponItem2(ran1[0], ran1[2], "抽奖机", 1);
			}
			cm.gainItem(4000313, 1);
			txt += "#v" + ran1[0] + "#\r\n";
			var result = cm.setBossRankCount("随机奖池抽奖");
		}
		cm.sendOk(txt);
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