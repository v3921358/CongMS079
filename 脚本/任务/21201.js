var status = -1;
var skills = Array(21001003, 21000000, 21100000, 21100002, 21100004, 21100005, 21110002);

function start(mode, type, selection) {
}

function end(mode, type, selection) {
	if (qm.isQuestFinished(21202)) {
        if (qm.getPlayerStat("RSP") > (qm.getPlayerStat("LVL") - 30) * 3) {
            qm.sendNext("請檢察你的技能點數是否有使用完畢。");
            qm.dispose();
            return;
        }
        qm.sendNextS("我的記憶都回來了....", 2);
        qm.changeJob(2110);
        qm.gainItem(1142130, 1);
        for (var i = 0; i < skills.length; i++) {
            qm.teachSkill(skills[i], qm.getPlayer().getSkillLevel(skills[i]));
        }
		qm.forceCompleteQuest();
		qm.dispose();
		return;
	}
	qm.forceStartQuest();
    qm.dispose();
}