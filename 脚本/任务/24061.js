var status = -1;

function start(mode, type, selection) {
	qm.sendNext("我不會放棄的！");
	qm.gainExp(5000);
	qm.forceCompleteQuest();
	qm.dispose();
}
function end(mode, type, selection) {
	qm.dispose();
}
