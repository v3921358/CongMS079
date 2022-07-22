var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 0) {
	    cm.dispose();
	}
	status--;
    }
    if (cm.getPlayer().getLevel() < 50) {
	cm.sendOk("在你受傷以前，趕快離開吧.");
	cm.dispose();
	return;
    }
    if (status == 0) {
	cm.sendYesNo("你看起來很強的樣子，要不要去一趟巴洛古寺廟呢?");
    } else if (status == 1) {
	cm.warp(105100100);
	cm.dispose();
    }
}