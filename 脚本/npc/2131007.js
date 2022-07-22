var status = -1;

var exchangeItem = 4000437;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	cm.dispose();
	return;
    }
    if (status == 0) {
        cm.sendSimple("這麼多的傷患，需要一點藥吧...#b\r\n#L0#嘿，這一些#t4000437#，幫我做更好的藥品。#l");
    } else if (status == 1) {
	if (!cm.haveItem(exchangeItem, 100)) {
	    cm.sendNext("你沒有足夠的數量我需要100個#t4000437#");
	    cm.dispose();
	} else {
	    cm.sendGetNumber("嗯，這是個好主意！ 我可以給你 #i2022457#每100個 #i" + exchangeItem + "##t" + exchangeItem + "# 你想要給我多少 (當前道具: " + cm.getPlayer().itemQuantity(exchangeItem) + ")", java.lang.Math.min(300.0, cm.getPlayer().itemQuantity(exchangeItem) / 100.0), 1, java.lang.Math.min(300.0, cm.getPlayer().itemQuantity(exchangeItem) / 100.0));
	}
    } else if (status == 2) { 
	if (selection >= 1 && selection <= cm.getPlayer().itemQuantity(exchangeItem) / 100) {
	    if (!cm.canHold(2022457, selection)) {
		cm.sendOk("請空出一些其他欄位。");
	    } else {
		cm.gainItem(2022457, selection);
		cm.gainItem(exchangeItem, -(selection * 100));
		cm.sendOk("謝謝你。");
	    }
	}
        cm.dispose();
    }
}