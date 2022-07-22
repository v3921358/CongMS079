/*
	Egnet - Before Takeoff To Ariant(200000152)
*/

var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	cm.sendOk("這是好的選擇！！");
	cm.safeDispose();
	return;
    }
    if (status == 0) {
	cm.sendYesNo("你要離開船上??");
    } else if (status == 1) {
	cm.warp(200000151);
	cm.dispose();
    }
}