var status = -1;

function action(mode, type, selection) {
    status++;
    if (mode == 0) {
	cm.sendOk("這是好的選擇！！");
	cm.safeDispose();
	return;
    }
    if (status == 0) {
	cm.sendYesNo("你要離開船上??");
    } else if(status == 1) {
	cm.warp(200000131, 0);
	cm.dispose();
    }
}