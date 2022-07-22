var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 1)
            status++;
        else {
            cm.dispose();
            return;
        }
        if (status == 0) {
            var says = "你好，很高兴为您服务。\r\n#b";
            says += "你可以选择一件装备，消耗#v4000000#，使之可交易\r\n";
            var b = false;
            for (var i = 1; i <= 96; i++) {
                var eq = cm.getInventory(1).getItem(i);
                if (eq != null && !cm.isCash(eq.getItemId()) && (Packages.server.MapleItemInformationProvider.getInstance().isPickupRestricted(eq.getItemId()) || Packages.server.MapleItemInformationProvider.getInstance().isDropRestricted(eq.getItemId())) && !Packages.client.inventory.ItemFlag.KARMA_EQ.check(eq.getFlag())) {
                    says += "#L" + i + "#[栏位 " + i + "] #v" + eq.getItemId() + "##t" + eq.getItemId() + "##l\r\n";
                    b = true;
                }
            }
            if (!b) {
                cm.dispose();
                cm.sendOk(says + "\r\n\r\n#r你没有符合条件的装备");
            } else {
                cm.sendSimple(says);
            }

        } else if (status == 1) {
            var eq = cm.getInventory(1).getItem(selection);
            if (selection <= 0 || eq == null) {
                cm.sendOk("装备选择出错");
            } else if (!cm.haveItem(4000000, 1)) {
                cm.sendOk("#v4000000#不够");
            } else {
                var flag = eq.getFlag();
				if (Packages.server.MapleItemInformationProvider.getInstance().isUntradeableOnEquip(eq.getItemId()) && (flag != 0)) {
					flag = 0;
				} else {
					flag |= Packages.client.inventory.ItemFlag.KARMA_EQ.getValue();
				}
				eq.setFlag(flag);
                cm.forceReAddItem(eq,1 );
                cm.gainItem(4000000, -1);
                cm.sendOk("操作成功");
            }
            cm.dispose();
        } else {
            cm.dispose();
        }
    }
}