/**
-- Odin JavaScript --------------------------------------------------------------------------------
	? - Victoria Road: Pet-Walking Road (100000202)
-- By ---------------------------------------------------------------------------------------------
	Xterminator
-- Version Info -----------------------------------------------------------------------------------
	1.0 - First Version by Xterminator
---------------------------------------------------------------------------------------------------
**/

var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
	cm.sendNext("#b(我不碰这片草)");
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;
    if (status == 0) {
	if (cm.getQuestStatus(4646) == 1) {
	    if (cm.haveItem(4031921)) {
		cm.sendNext("#b(这是什么……恶……宠物的粪便!)");
		cm.dispose();
	    } else {
		cm.sendYesNo("#b(我可以看到一些被草覆盖。我应该把它拽出来吗?)");
	    }
	} else {
	    cm.sendOk("#b(我找不到任何东西.)");
	    cm.dispose();
	}
    } else if (status == 1) {
	cm.sendNext("我发现项宠物训练师Bartos藏……这张钞票。");
	cm.gainItem(4031921, 1);
	cm.dispose();
    }
}