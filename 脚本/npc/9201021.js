var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.sendOk("好吧，等你有需要再來找我。");
            cm.dispose();
            return;
        }
        status--;
    }
    if (cm.getMapId() == 680000300 && cm.getQuestRecord(160002).getCustomData() != null) {
        var dat = parseInt(cm.getQuestRecord(160002).getCustomData());
        if (cm.getPlayer().getMarriageId() > 0) {
            var WeddingMap = cm.getMap(680000400);
            WeddingMap.resetFully();
            var BounsMap = cm.getMap(680000401);
            BounsMap.resetFully();
            cm.warpMap(680000400, 0);
            cm.dispose();
            return;
        } else {
            cm.dispose();
        }
    }
    if (cm.getMapId() == 680000400 && cm.getQuestRecord(160002).getCustomData() != null) {
        var dat = parseInt(cm.getQuestRecord(160002).getCustomData());
        var chr = cm.getMap().getCharacterById(cm.getPlayer().getMarriageId());
        var map = cm.getMap(680000401);
		if (cm.getMonsterCount(680000400) <= 0) {
        if (cm.getPlayer().getMarriageId() > 0 && chr != null) {
            cm.getPlayer().changeMap(map, map.getPortal(0));
            chr.changeMap(map, map.getPortal(0));
            cm.dispose();
            return;
        } else if (chr == null) {
            cm.sendOk("你的另一半跑哪去了?");
        } else {
            cm.dispose();
        }
		} else {
			cm.sendOk("請先把地圖上的怪物給消滅。");
			cm.dispose();
			return;
		}
    }
    if (cm.getMapId() == 680000401 && cm.getQuestRecord(160002).getCustomData() != null) {
        var dat = parseInt(cm.getQuestRecord(160002).getCustomData());
        cm.warpMap(680000500, 0);
        cm.dispose();
        return;
    }
    if (status == 0) {
        cm.sendYesNo("你確定要離開婚禮會場嗎? 離開了就近不來了喔");
    } else if (status == 1) {
        cm.warp(680000500, 0);
        cm.dispose();
    }
}