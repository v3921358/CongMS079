var status = -1;

function start(mode, type, selection) {
	if (qm.getPlayer().getJunior1() > 0) {
		qm.forceCompleteQuest();
		qm.gainExp(3000);
		qm.sendNext("棒極了！！");
	} else {
		qm.sendNext("請找一個徒弟來見我！");
	}
	qm.dispose();
}
function end(mode, type, selection) {
	if (qm.getPlayer().getJunior1() > 0) {
		qm.forceCompleteQuest();
		qm.gainExp(3000);
		qm.sendNext("棒極了！！");
	} else {
		qm.sendNext("請找一個徒弟來見我！");
	}
	qm.dispose();
}
