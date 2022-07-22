var status = -1;
function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	cm.dispose();
	return;
    }
    if (status == 0) {
		if (cm.getPlayer().hasEquipped(1072420) || cm.getPlayer().hasEquipped(1082257) || cm.haveItem(1072420)|| cm.haveItem(1082257)) {//判断
		cm.sendOk("你已经拥有了。\r\n#v1072420##z1072420#或#v1082257##z1082257#.");
		cm.dispose();
		} else if (cm.getPlayer().getLevel() < 41 && cm.haveItem(4032495)) {
			cm.sendYesNo("你想移动到隐藏地图?");
		} else {
			cm.sendOk("你需要小于40级，进入需要牛魔王徽章.");
			cm.dispose();
		}
} else {
	cm.getMap(677000001).resetFully();
	cm.spawnMobOnMap(9400612,1,179,60,677000001);
	cm.warp(677000000,0);
	cm.dispose();
    }
}