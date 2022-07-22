var status = -1;

function start(mode, type, selection) {
	qm.sendNext("也許你應該再回到#b死龍巢穴#k 看看是否有奇怪的跡象...");
	qm.forceStartQuest();
	qm.forceCompleteQuest();
	qm.dispose();
}

function end(mode, type, selection) {
	qm.forceStartQuest();
	qm.forceCompleteQuest();
	qm.dispose();
}