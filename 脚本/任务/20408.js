/*
 * Cygnus 2nd Job advancement - Proof of test
 * Soul
 */

var status = -1;

function start(mode, type, selection) {
	end(mode,type,selection); //idk lol
}

function end(mode, type, selection) {
    if (mode == 0) {
	if (status == 0) {
	    qm.sendNext("我猜你還沒準備好。");
	    qm.dispose();
	    return;
	} else if (status >= 2) {
	    status--;
	} else {
	    qm.dispose();
	    return;
	}
    } else {
	status++;
    }
    if (status == 0) {
		qm.sendYesNo("你存在皇家騎士團，那麼你想成為一名騎士官員？");
    } else if (status == 1) {
	    qm.forceCompleteQuest();
	    if (qm.getJob() == 1111) {
		qm.changeJob(1112);
	    } else if (qm.getJob() == 1211) {
		qm.changeJob(1212);
	    } else if (qm.getJob() == 1311) {
		qm.changeJob(1312);
	    } else if (qm.getJob() == 1411) {
		qm.changeJob(1412);
	    } else if (qm.getJob() == 1511) {
		qm.changeJob(1512);
	    }
	    qm.sendNext("你現在皇家騎士團的騎士官員。");
    } else if (status == 3) {
	qm.sendPrev("現在回去找女皇吧。");
	qm.dispose();
    }
}