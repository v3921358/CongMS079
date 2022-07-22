function action(mode, type, selection) {
    if (cm.getMapId() == 910010100) {
        //for (var i = 4001095; i < 4001099; i++) {
        //    cm.givePartyItems(i, 0, true);
        //}
        cm.givePartyItems(4001095, 0, true);
        cm.givePartyItems(4001096, 0, true);
        cm.givePartyItems(4001097, 0, true);
        cm.givePartyItems(4001098, 0, true);
        cm.givePartyItems(4001099, 0, true);
        cm.givePartyItems(4001100, 0, true);
        //cm.givePartyItems(4001101, 0, true);
        //cm.givePartyItems(4031184, 0, true);
        cm.warpParty(910010200);
        //cm.warp(910010200);
        cm.dispose();
    } else if (cm.getMapId() == 910010200) {
        //for (var i = 4001095; i < 4001099; i++) {
        //    cm.givePartyItems(i, 0, true);
        //}
        cm.givePartyItems(4001095, 0, true);
        cm.givePartyItems(4001096, 0, true);
        cm.givePartyItems(4001097, 0, true);
        cm.givePartyItems(4001098, 0, true);
        cm.givePartyItems(4001099, 0, true);
        //for (var i = 4001100; i < 4001101; i++) {
        //    cm.givePartyItems(i, 0, true);
        //}
        cm.givePartyItems(4001100, 0, true);
        //cm.givePartyItems(4001101, 0, true);
        //cm.givePartyItems(4031184, 0, true);
        cm.gainItem(4031184, 5);
        cm.warp(100000200);
        cm.dispose();
    } else {
        //for (var i = 4001095; i < 4001099; i++) {
        //    cm.givePartyItems(i, 0, true);
        //}
        cm.givePartyItems(4001095, 0, true);
        cm.givePartyItems(4001096, 0, true);
        cm.givePartyItems(4001097, 0, true);
        cm.givePartyItems(4001098, 0, true);
        cm.givePartyItems(4001099, 0, true);
        //for (var i = 4001100; i < 4001101; i++) {
        //    cm.givePartyItems(i, 0, true);
        //}
        cm.givePartyItems(4001100, 0, true);
        //cm.givePartyItems(4001101, 0, true);
        //cm.givePartyItems(4031184, 0, true);
        cm.warp(100000200);
        cm.dispose();
    }
}