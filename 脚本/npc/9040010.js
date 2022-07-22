/* 
 * @Author Lerk
 * 
 * Tiger Statue (990000900)
 * 
 * Guild Quest - end of boss
 */
load('nashorn:mozilla_compat.js');
importPackage(java.lang);

function start() {
	action(1, 0, 0);
}

function action(mode, type, selection) {
    var eim = cm.getEventInstance();
    if (eim != null) {
	if (eim.getProperty("leader").equals(cm.getName())) {
	    if (cm.haveItem(4001024)) {
		cm.removeAll(4001024);
		var prev = eim.setProperty("bossclear","true",true);
		if (prev == null) {
		    var start = parseInt(eim.getProperty("entryTimestamp"));
		    var diff = System.currentTimeMillis() - start;
		    var points = 3000 - Math.floor(diff / (100 * 60));
		    cm.gainGP(points);
		}
		eim.finishPQ();
	    } else {
                cm.sendOk("這是你最後的挑戰。請打帶惡靈13後並帶著他的遺物來找我。");
	    }
	}
    } else {
	cm.warp(990001100);
    }
	cm.dispose();
}