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
    if (status == 0) {
	if (cm.getMap().getAllMonstersThreadsafe().size() <= 0) {
	    cm.sendOk("亲，当前身边没有怪物，获取不到数据，请在怪物身边使用。");
	    cm.dispose();
	    return;
	}
	var selStr = "请选择你想查询的怪物。\r\n\r\n#b";
	var iz = cm.getMap().getAllUniqueMonsters().iterator();
	while (iz.hasNext()) {
	    var zz = iz.next();
	    selStr += "#L" + zz + "##o" + zz + "##l\r\n";
	} 
	cm.sendSimple(selStr);
    } else if (status == 1) {
	cm.sendNext(cm.checkDrop(selection));
	cm.dispose();
    }
}