function action(mode, type, selection) {
    cm.removeAll(4001101);
    cm.removeAll(4001095);
    cm.removeAll(4001096);
    cm.removeAll(4001097);
    cm.removeAll(4001098);
    cm.removeAll(4001099);
    cm.removeAll(4001100);
    if (cm.getPlayer().getMapId() == 910010200 ) {
        cm.warp(100000200);
		cm.dispose();
	} else if (cm.getPlayer().getMapId() == 910010100 || cm.getPlayer().getMapId() == 910010300) {//通关
        cm.gainItem(4001126,cm.随机数(15));////枫叶
		cm.gainItem(4000313,cm.随机数(2));////黄金枫叶
		cm.gainItem(4170000,cm.随机数(2));///月秒蛋
		cm.getPlayer().setBossLog("月妙副本");//给一条龙次数
		cm.warp(100000200);
		//cm.getPlayer().setOneTimeLog("yuemiao");//给永久记录
		cm.dispose();
    } else {
        cm.warp(100000200);
		cm.dispose();
    }
}
