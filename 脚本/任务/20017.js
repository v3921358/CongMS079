/*
	NPC Name: 		Cygnus
	Description: 		Quest - Encounter with the Young Queen
*/

/* global qm */

var status = -1;

function start(mode, type, selection) {
  //  x
     if (mode == 1) {
	status++;
    } else {
	if (status == 2) {
	    qm.sendNext("嗯，没什么好担心的。这对你这个级别的人来说是轻而易举的。鼓起勇气，准备好后告诉我.");
	    qm.safeDispose();
	    return;
	}
	status--;
    }
    if (status == 0) {
	qm.sendNext("隐马尔可夫模型？冒险骑士团派你来了，嗯？你一定是最近加入天鹅座骑士团的新手。欢迎，很高兴见到你！我的名字是奇库。我是训练像你这样的贵族的教练。当然，你看我不是人.");
    } else if (status == 1) {
	qm.sendNextPrev("我叫奇库。你一直在皇后身边见过谁，不是吗？奇库和南哈特属于同一个家族，但我们属于不同的类型。当然，自从我们住在埃雷夫以后，他就再也没见过我们了。你很快就会习惯埃雷夫了.");
    } else if (status == 2) {
	qm.sendNextPrev("OH，你知道埃雷夫没有怪物吗？即使是一点点邪恶也不敢进入埃雷夫。但你不用担心。你将能够与被称作咪咪的南哈特创造的虚幻怪物一起训练。.");
    } else if (status == 3) {
	qm.askAcceptDecline("你好像准备好了！看看你所取得的成就，我认为你应该马上去寻找更高级的咪咪。你在M130010100里猎杀B15，R，O100122，怎么样？使用左侧的入口到达B培训森林II K.");
    } else if (status == 4) {
	qm.summonMsg(12);
	qm.forceStartQuest(20020);
	qm.forceCompleteQuest(20100);
	qm.forceStartQuest();
	qm.dispose();
    }
}

function end(mode, type, selection) {
}
