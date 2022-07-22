/*
	NPC Name: 		Han the Broker
	Map(s): 		Magatia
	Description: 	Quest - Test from the Head of Alcadno Society
*/

var status = -1;
var oreArray;

function start(mode, type, selection) {}

function end(mode, type, selection) {
    qm.sendNext("請等一下我去拿個東西，以幫助您更容易通過卡帕萊特協會長的考驗。");
    qm.forceCompleteQuest();
    qm.dispose();
}