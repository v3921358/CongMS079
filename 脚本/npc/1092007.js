function start() {
	if (cm.isQuestActive(2175)) {
	cm.sendOk("你準備幹黑魔法師的手下了嗎?? 我將把你傳送過去...");
    } else {
    cm.sendOk("這黑魔師真它媽的該死!!");
    cm.dispose();
}
}

function action(mode, type, selection) {
    cm.warp(912000000,0);
    cm.dispose();
}
