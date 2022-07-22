var status = -1;

function start(mode, type, selection) {
	qm.sendNext("任務已完成");
	qm.gainExp(11280);
	qm.getPlayer().setFame(qm.getPlayer().getFame() + 8);
	qm.forceCompleteQuest();
	qm.dispose();
}
function end(mode, type, selection) {
	qm.dispose();
}