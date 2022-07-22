/* 
 * Shawn, Victoria Road: Excavation Site<Camp> (101030104)
 * Guild Quest Info
 */

var status;
var selectedOption;

function start() {
    selectedOption = -1;
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
	cm.dispose();
	return;
    }
    if (mode == 1)
	status++;
    else
	status--;
    if (mode == 1 && status == 3) {
	status = 0;
    }
    if (status == 0) {
	if (cm.getQuestStatus(6201) == 1) {
	    var dd = cm.getEventManager("Relic");
	    if (dd != null) {
		dd.startInstance(cm.getPlayer());
	    } else {
                cm.sendOk("未知的錯誤。");
	    }
	    cm.dispose();
	} else {
            var prompt = "\r\n#b#L0#威廉的古堡是什麼地方?#l\r\n#b#L1##t4001024#?#l\r\n#b#L2#公會戰守護戰?#l\r\n#b#L3#已經沒有問題了。#l";
	    if (selectedOption == -1) {
                prompt = "\r\n我們公會聯盟是從很久以前就開始，一直在努力解讀古代的遺跡'祖母綠碑'。得到結果是發現祖母綠碑記載者這裡其實就是楓之谷古文明的發源處'威廉的古堡'。而且還了解到傳說中的寶石魯碧安就在'威廉的古堡'的遺跡中，但由於寶石魯碧安擁有神秘的力量﹐因此被'惡靈13'所霸佔了。為了奪回魯碧安公會聯盟開始了公會守護戰。" + prompt;
	    } else {
                prompt = "還有要問的嘛?" + prompt;
	    }
	    cm.sendSimple(prompt);
	}
    } else if (status == 1) {
	selectedOption = selection;
	if (selectedOption == 0) {
            cm.sendNext("'威廉的古堡'是曾經統治維多利亞島全境的古代文明發源地。在石人寺院或森林深處的神殿之類的古代建築物都是'威廉的古堡'的遺址。");
        } else if (selectedOption == 1) {
            cm.sendNext("#t4001024#是傳說中的能夠使人永遠年輕的寶石。聽說擁有#t4001024#的人都滅亡了﹐也許'威廉的古堡'的滅亡也於此有關。");
	    status = -1;
        } else if (selectedOption == 2) {
            cm.sendNext("過去多次派勘到'威廉的古堡'。但是無人歸還。所以我們這次決定集結眾人之力展開公會守護戰。我相信你們這些一直在努力增強力量的公會。");
        } else if (selectedOption == 3) {
            cm.sendOk("是嗎？若有什麼問題，請隨時提出。");
	    cm.dispose();
        } else {
	    cm.dispose();
	}
    } else if (status == 2) {
	if (selectedOption == 0) {
            cm.sendNextPrev("'威廉的古堡'最後的王室威廉公爵﹐據說他非常聰明而又仁慈。但是在某一天突然滅亡了﹐其原因還沒弄清楚。");
        } else if (selectedOption == 2) {
            cm.sendNextPrev("這次公會守護戰的目的是到'威廉的古堡'探險﹐並奪回#t4001024#。這個任務並不是靠強大的力量就能完成的。最重要的是要與同伴合作。");
        } else {
	    cm.dispose();
	}
    }
}