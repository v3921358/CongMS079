var status = -1;
function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	cm.dispose();
	return;
    }
    if (status == 0) {
		if (cm.getPlayer().hasEquipped(1072421) || cm.getPlayer().hasEquipped(1082258) || cm.haveItem(1072421)|| cm.haveItem(1082258)) {//判断
		cm.sendOk("你已经拥有了。\r\n#v1072421##z1072421#或#v1082258##z1082258#.");
		cm.dispose();
		} else if (cm.getPlayer().getLevel() < 41 && cm.haveItem(4032485)) {
			cm.sendYesNo("你想移动到隐藏地图?");
		} else {
			cm.sendOk("你需要小于40级，需要进入要有大型钱币模型.");
			cm.dispose();
		}
} else {
	cm.getMap(677000009).resetFully();
	cm.spawnMobOnMap(9400613,1,43,66,677000009);
	cm.warp(677000008,0);
	cm.dispose();
    }
}