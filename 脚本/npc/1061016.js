var status = -1;
var itemids = Array(2040739, 2040728, 2040729, 2040730, 2040731, 2040732, 2040733, 2040734, 2040735, 2040736, 2040737, 2040738);

function start() {
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode != 1) {
		cm.dispose();
		return;
	}
	status++;
	if (status == 0) {
		cm.sendSimple("又來了？你已經來好多次了。看樣子蠻閒的嘛！看起來你有事要拜託我？已經取得巴洛古的皮了？\r\n\r\n#r#L1#兌換一些東西#l#k");
	} else if (status == 1) {
		var selStr = "要做成何種卷軸? 隨著卷軸的種類，所需的皮件個數也不同。\r\n\r\n#b";
		for (var i = 0; i < itemids.length; i++) {
			selStr += "#L" + i + "##i" + itemids[i] + "##z" + itemids[i] + "##l\r\n";
		}
		cm.sendSimple(selStr);
	} else if (status == 2) {
		if (!cm.canHold(itemids[selection], 1)) {
			cm.sendOk("請空出一些欄位。");
		} else if (cm.itemQuantity(4001261) < 1) {
			cm.sendOk("你沒有足夠的#b#t4001261##k。");
		} else {
			cm.gainItem(4001261, -1);
			cm.gainItem(itemids[selection], 1);
			cm.sendOk("感謝光臨，歡迎下次再來~");
		}
		cm.dispose();
	}
}