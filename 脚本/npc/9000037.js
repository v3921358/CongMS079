/*
	NPC Name: 		Agent Kitty
	Map(s): 		Special Training Camp for Agent (970030000)
	Description: 		Agent event Starter
*/

function start() {
	
    if (cm.getMapId() == 970030000) {
	if (cm.getBossLog("回忆27关") > 0){
	cm.sendOk("今日你已经挑战过了，请明天再来。");
	cm.dispose();
	return;
	}else{
	cm.setBossLog("回忆27关");
	cm.start_DojoAgent(false, false);
	cm.dispose();
	}
    } else if (cm.getMapId() == 910000000) {
	cm.sendYesNo("Do you want to go to Special Training Camp for Agent?")
	type = 1;
    } else {
	cm.sendYesNo("我很丑?  我说我很丑吗？   那我走？");
	type = 2;
    }
}

function action(mode, type, selection) {
	
    if (mode == 1) {
	
	cm.warp(970030000, 0);
	
    }
    cm.dispose();
}