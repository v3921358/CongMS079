var status = -1;

function start(mode, type, selection) {
	qm.sendNext("我不知道但也許你能去打 #b殭屍#k 撿到殭屍東西將會有下一個步驟。");
	qm.forceStartQuest();
	qm.forceCompleteQuest();
	qm.dispose();
}

function end(mode, type, selection) {
	qm.forceStartQuest();
	qm.forceCompleteQuest();
	qm.dispose();
}