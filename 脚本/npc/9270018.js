// Kerny - Pilot
var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }
    if (status == 0) {
	if (cm.getMapId() == 540010002) {
		cm.sendOk("風景很美對吧?");
	    cm.dispose();
	} else if (cm.getMapId() == 540010101){
	cm.sendOk("風景很美對吧?");
	cm.dispose();
	} else {
	    cm.sendYesNo("這架飛機將在起飛不久，請問你現在離開？您將有再次購買飛機票到這裡來.");
	}
    } else {
	cm.warp(540010000, 0);
	cm.dispose();
    }
}