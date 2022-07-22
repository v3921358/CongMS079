/*
	NPC Name: 		Kisan
	Description: 		Quest - Cygnus tutorial helper
*/

var status = -1;

function start(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status == 2) {
	    qm.sendNext("你不想来？它甚至不是很难做的，您会收到特殊装备作为奖励！好了，给它一些想法，让你改变了主意，我知道.");
	    qm.safeDispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	qm.sendNext("有许多方法打猎，但最基本的方法是用你的 #b普通攻击#k. 所有你需要的是在你的手的武器，因为它只是摆动你的武器在怪物一件简单的事情。");
    } else if (status == 1) {
	qm.sendNextPrev("请按 #bCtrl#k 使用你的普通攻击. 通常下 Ctrl 位于 #b键盘的左下角#k, 但你并不需要我告诉你对不对？ 发现Ctrl 并尝试攻击！");
    } else if (status == 2) {
	qm.askAcceptDecline("现在，你已经尝试过了，我们一定要测试它。在这方面，你可以找到最薄弱 #r#o100120##k 在耶雷弗, 这是您的最佳选择。尝试狩猎 #r1只#k. 当你回来我给你的奖励。.");
    } else if (status == 3) {
	qm.forceStartQuest();
	qm.summonMsg(4);
	qm.dispose();
    }
}

function end(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }
    if (status == 0) {
	qm.sendNext("很棒哟看你学得很快，将来一定是强大的王者！");
    } else if (status == 1) {
	qm.sendNextPrev("这身装备是贵族专属的。 它将送给你穿，穿上它吧！ 然后按照箭头的方向去找我的兄弟 #b#p1102006##k. 他会告诉你下一步该怎么做。 \r\n\r\n#fUI/UIWindow.img/QuestIcon/4/0# \r\n#i1002869# #t1002869# - 1 \r\n#i1052177# #t1052177# - 1 \r\n\r\n#fUI/UIWindow.img/QuestIcon/8/0# 30 经验值");
    } else if (status == 2) {
	qm.gainItem(1002869, 1);
	qm.gainItem(1052177, 1);
	qm.forceCompleteQuest();
	qm.gainExp(30);
	qm.summonMsg(6);
	qm.dispose();
    }
}