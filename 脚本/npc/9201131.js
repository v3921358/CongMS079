var status = -1;
function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	cm.dispose();
	return;
    }
    if (status == 0) {
		if (cm.getPlayer().hasEquipped(1082259) || cm.getPlayer().hasEquipped(1072422) || cm.haveItem(1082259)|| cm.haveItem(1072422)) {//判断
		cm.sendOk("你已经拥有了。\r\n#v1082259##z1082259#或#v1072422##z1072422#.");
		cm.dispose();
		} else if (cm.getPlayer().getLevel() < 41 && cm.haveItem(1452084)) {
			cm.sendYesNo("你想移动到隐藏地图?");
		} else {
			cm.sendOk("你需要小于40级，需要进入要背包里有所罗门之弓.");
			cm.dispose();
		}
} else {
	cm.getMap(677000003).resetFully();
	cm.spawnMobOnMap(9400610,1,228,35,677000003);
	cm.warp(677000002,0);
	cm.dispose();
    }
}