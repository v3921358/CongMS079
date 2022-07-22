/**
	NPC Name: 		Han the Broker
	Map(s): 		Magatia
	Description: 	Quest - Test from the Head of Zenumist Society
*/

var status = -1;

function start(mode, type, selection) {
    qm.dispose();
}

function end(mode, type, selection) {
    qm.sendNext("請等一下我去拿個東西，以幫助您更容易通過蒙特鳩協會長的考驗。");
    qm.forceCompleteQuest();
    qm.dispose();
}