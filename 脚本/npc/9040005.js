var status = -1;

function action(mode, type, selection) {
    if (mode != 1) {
        cm.sendOk("祝福你們公會戰順利！！");
	cm.dispose();
	return;
    }
status++;
    if (status == 0) {
	if (cm.isPlayerInstance()) {
            cm.sendSimple("你想做什麼?? \r\n #L0#離開公會任務#l");
	} else {
            cm.sendOk("很抱歉我不能為你做任何事情。");
		cm.dispose();
	}
    } else if (status == 1) {
        cm.sendYesNo("你真的確定要離開嗎?");
    } else if (status == 2) {
	if (cm.isPlayerInstance()) { 
		cm.getPlayer().getEventInstance().removePlayer(cm.getPlayer());
	}
	cm.dispose();
	return;
    }
}
