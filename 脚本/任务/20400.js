var status = -1;

function start(mode, type, selection) {
	qm.sendNext("去冰原雪域找#b杰德#k 他會告訴你詳細情況。");
	qm.forceStartQuest();
	qm.forceCompleteQuest();
	qm.dispose();
}

function end(mode, type, selection) {
	qm.forceStartQuest();
	qm.forceCompleteQuest();
	qm.dispose();
}