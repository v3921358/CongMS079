var status = -1;

function action(mode, type, selection) {
	if (cm.getMapId() == 240050100) {
        if (cm.isLeader() && cm.haveItem(4001087, 1) && cm.haveItem(4001088, 1) && cm.haveItem(4001089, 1) && cm.haveItem(4001090, 1) && cm.haveItem(4001091, 1)) {
             cm.showEffect(true, "quest/party/clear");
             cm.playSound(true, "Party1/Clear");
             cm.gainItem(4001087, -1);
             cm.gainItem(4001088, -1);
             cm.gainItem(4001089, -1);
             cm.gainItem(4001090, -1);
             cm.gainItem(4001091, -1);
             cm.warpParty(240050300);
			 cm.dispose();
	} else {
             cm.sendOk("請叫你的隊長帶著5種迷宮室鑰匙來找我");
			 cm.dispose();
              }
        }
}
			
