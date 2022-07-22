/* Author: Xterminator
	NPC Name: 		Rain
	Map(s): 		Maple Road : Amherst (1010000)
	Description: 		Talks about Amherst
*/
var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1)
	status++;
    else
	status--;
	
    if (status == 0) {
	cm.sendNext("这个小镇叫做 #bAmherst#k,位于东北小小冒险岛的一部分。你是初学者,对吧?我很高兴只有弱的怪物在这个地方.");
    } else if (status == 1) {
	cm.sendNextPrev("If you want to get stronger, then go to #bSouthperry#k where there's a harbor. Ride on the gigantic ship and head to the place called #bVictoria Island#k. It's incomparable in size compared to this tiny island.");
    } else if (status == 2) {
	cm.sendPrev("At the Victoria Island, you can choose your job. Is it called #bPerion#k...? I heard there's a bare, desolate town where warriors live. A highland...what kind of a place would that be?");
    } else if (status == 3) {
	cm.dispose();
    }
}