var status = -1;
function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	cm.dispose();
	return;
    }
    if (status == 0) {
if (cm.getPlayer().hasEquipped(1072423) || cm.getPlayer().hasEquipped(1082260) || cm.haveItem(1072423)|| cm.haveItem(1082260)) {//判断
		cm.sendOk("你已经拥有了。\r\n#v1072423##z1072423#或#v1082260##z1082260#.");
		cm.dispose();
		} else if (cm.getPlayer().getLevel() < 41 && cm.haveItem(4032494)) {
			cm.sendYesNo("你想移动到隐藏地图?");
		} else {
			cm.sendOk("你需要小于40级，需要进入要有雪之猫女徽章.");
			cm.dispose();
		}
} else {
	cm.getMap(677000007).resetFully();
	cm.spawnMobOnMap(9400611,1,123,73,677000007);
	cm.warp(677000006,0);
	cm.dispose();
    }
}