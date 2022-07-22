var status = -1;
function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	cm.dispose();
	return;
    }
    if (status == 0) {
		if (cm.getPlayer().hasEquipped(1072419) || cm.getPlayer().hasEquipped(1082256) || cm.haveItem(1072419)|| cm.haveItem(1082256)) {//判断
		cm.sendOk("你已经拥有了。\r\n#v1072419##z1072419#或#v1082256##z1082256#.");
		cm.dispose();
		} else if (cm.getPlayer().getLevel() < 41 && cm.haveItem(4032491)) {
			cm.sendYesNo("你想移动到隐藏地图?");
		} else {
			cm.sendOk("你需要小于40级，需要进入印第安老斑鸠徽章.");
			cm.dispose();
		}
} else {
	cm.getMap(677000005).resetFully();
	cm.spawnMobOnMap(9400609,1,1,75,677000004);
	cm.warp(677000004,0);
	cm.dispose();
    }
}