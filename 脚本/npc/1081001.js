/**
	Pison - Florina Beach(110000000)
**/
var status = -1;
var returnmap = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	cm.sendNext("你不回去 #m"+returnmap+"# 那真是太棒了!\r\n看看我在這邊還不是過得好好，和你講話彷彿回到了以前呢!");
	cm.safeDispose();
	return;
    }
    if (status == 0) {
	returnmap = cm.getSavedLocation("FLORINA");
	cm.sendSimple("所以你想離開 #b#m110000000##k? 如果你想我可以幫助你回到 #b#m"+returnmap+"##k. 但是需要1500楓幣 r\n\r\n#L0##b 我願意付 1500 楓幣.#l");
    } else if (status == 1) {
	cm.sendYesNo("你確定你想回到 #b#m"+returnmap+"##k? 好吧，我們得走快點了");
    } else if (status == 2) {
	if (cm.getMeso() < 1500) {
		cm.sendOk("好像楓幣不足耶!");
		cm.dispose();
	} else {
	if (returnmap < 0) {
		returnmap = 104000000;
	}
	cm.gainMeso(-1500);
	cm.warp(returnmap, 0);
	cm.clearSavedLocation("FLORINA");
	cm.dispose();
    }
}
}