
var status;
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
		var txt = "完成副本英语与智力考核，可以获得优秀印章兑换到还原卷轴\r\n请选择你想要还原的装备，每次还原将要消耗一张#v2049601#：\r\n";
		var b = false;
		for (var i = 1; i <= 96; i++) {
			var eq = cm.getInventory(1).getItem(i);
			if (eq != null && !cm.isCash(eq.getItemId())) {
				b = true;
				txt += "#L"+i+"#[位置 "+i+"]#v"+eq.getItemId()+"##l\r\n";
			}
		}
		if (!b) {
			cm.sendOk(txt + "#r\r\n你没有可以还原的装备");
			cm.dispose();
			return;
		}
		cm.sendSimple(txt);
    } else if (status == 1) {
		var eq = cm.getInventory(1).getItem(selection);
		if (eq == null) {
			cm.sendOk("你选择的装备不存在");
		} else if (cm.isCash(eq.getItemId())) {
			cm.sendOk("时装不能还原");
		} else if (!cm.haveItem(2049601, 1)) {
			cm.sendOk("你没有#v2049601#");
		} else if (Packages.client.inventory.ItemFlag.LOCK.check(eq.getFlag())) {
			cm.sendOk("已锁定的装备需要先解锁才可以还原");
		} else if(eq.getExpiration() != -1) {
            cm.sendOk("限时装备不能还原.");
            cm.dispose();
            return;
        } else {
			cm.removeSlot(1, selection, 1);
			cm.gainItem(eq.getItemId(), 1);
			cm.gainItem(2049601, -1);
			cm.sendOk("恭喜你，还原成功");
			
		}
		
		cm.dispose();
	}
}
