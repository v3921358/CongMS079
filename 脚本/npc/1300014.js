﻿/* ===========================================================
			Resonance
	NPC Name: 		SELF
	Map(s): 		Mushroom Castle: Deep inside Mushroom Forest(106020300)
	Description: 	Upon reaching the magic barrier.
=============================================================
Version 1.0 - Script Done.(18/7/2010)
=============================================================
*/

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
		if (mode == 1)
            status++;
        else
            status--;
		}
	if(status == 0){
		if(cm.isQuestActive(2314))
			cm.PlayerToNpc("這是一個#b巨大的魔法屏障#k ...");
		else if(cm.isQuestActive(2322))
			cm.PlayerToNpc("這個地方還是先報告給 #b#p1300003##k 知道吧！");
		else {
			cm.PlayerToNpc("我是否該使用 #t2430014#？？");
			cm.dispose();
		}
	}if(status == 1){
		if(cm.isQuestActive(2314)){
			cm.ShowWZEffect("Effect/OnUserEff.img/normalEffect/mushroomcastle/chatBalloon1");
			cm.forceCompleteQuest(2314);
			cm.dispose();
		} else {
			cm.playerMessage("請先回去報告吧。");
		}
	}
}
			