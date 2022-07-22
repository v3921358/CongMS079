/*
	Keroben - Leafre Cave of life - Entrance
*/

var morph;
var status = -1;

function start() {
    status = -1;
    morph = 0;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	status--;
    }

	
	
    if (status == 0) {
	/*if (cm.getPlayer().getBossLog("hlg") >= 2) {
	cm.playerMessage(5, "一天只能打2次黑龙。");
	cm.sendOk("一天只能进入黑龙挑战2次");
	cm.dispose();
	return ;
	}*/
	//pi.playerMessage(5, "一天只能打2次扎昆。");
	//
	var 黑龙次数 = 3;
	var 加黑龙次数 = 10;
	var jobid=cm.getPlayer().getJob();
	morph = cm.getMorphState();
	if(cm.haveItem(3700067,1)){
		if ((morph == 2210003  ||  cm.haveItem(5220006,1))&&(cm.getPlayer().getBossLog("hlg") <= 加黑龙次数)&&(jobid==112||jobid==122||jobid==132||jobid==212||jobid==222||jobid==232||jobid==312||jobid==322||jobid==412||jobid==422||jobid==512||jobid==522||jobid==2112||jobid==1112||jobid==1111||jobid==1211||jobid==1311||jobid==1411||jobid==1511)) {
		//判断是否变身             判断是否有入场卷         判断是否进去3次
		//if (morph == 2210003 || cm.isQuestFinished(7301)) {
	    cm.sendNext("哦，我的兄弟！不要担心人类的入侵。我会保护你的一切。进来。");
		    
	} else {
	    var hp = cm.getPlayerStat("HP");
	    if (hp > 500) {
		cm.addHP(-500);
	    } else if (hp > 1 && hp <= 500) {
		cm.addHP(-(hp - 1));
	    }
	    cm.sendNext("请检查背包有没有入场券和是否变身为龙族，你有工资卡每天可以进入10次，请离开此处!");
	}
	}else{
	if ((morph == 2210003  ||  cm.haveItem(5220006,1))&&(cm.getPlayer().getBossLog("hlg") <= 黑龙次数)&&(jobid==112||jobid==122||jobid==132||jobid==212||jobid==222||jobid==232||jobid==312||jobid==322||jobid==412||jobid==422||jobid==512||jobid==522||jobid==2112||jobid==1112||jobid==1111||jobid==1211||jobid==1311||jobid==1411||jobid==1511)) {
		//判断是否变身             判断是否有入场卷         判断是否进去3次
		//if (morph == 2210003 || cm.isQuestFinished(7301)) {
	    cm.sendNext("哦，我的兄弟！不要担心人类的入侵。我会保护你的一切。进来。");
	} else {
	    var hp = cm.getPlayerStat("HP");
	    if (hp > 500) {
		cm.addHP(-500);
	    } else if (hp > 1 && hp <= 500) {
		cm.addHP(-(hp - 1));
	    }
	    cm.sendNext("请检查背包有没有入场券和是否变身为龙族，每天只能进入3次，请离开此处!");
	}
	}
    } else if (status == 1) {
	var 黑龙次数 = 3;
	var 加黑龙次数 = 10;
	var jobid=cm.getPlayer().getJob();
    if(cm.haveItem(3700067,1)){
		if (morph == 2210003 ) {
		//if (morph == 2210003 || cm.isQuestFinished(7301)) {
	    cm.cancelItem(2210003);//销毁变身
	    cm.warp(240050400, 0);
	    if (cm.haveItem(4031454)) { // Paladin
		cm.gainItem(4031454, -1);
		cm.gainItem(4031455, 1);
	    }
	    if (cm.getQuestStatus(6169) == 1) { // Temporary
		cm.gainItem(4031461, 1);
	    }
	} else {
		if(cm.haveItem(5220006,1) &&(cm.getPlayer().getBossLog("hlg") <= 加黑龙次数)&&(jobid==112||jobid==122||jobid==132||jobid==212||jobid==222||jobid==232||jobid==312||jobid==322||jobid==412||jobid==422||jobid==512||jobid==522||jobid==2112||jobid==1112||jobid==1111||jobid==1211||jobid==1311||jobid==1411||jobid==1511)){//判断每天小于等于3次可进入
			cm.gainItem(5220006, -1);
			cm.setBossLog("hlg");
			cm.warp(240050400, 0);
		}
     else{
	    cm.warp(240040600, "st00");
    }
	}
	}else {
	if (morph == 2210003 ) {
		//if (morph == 2210003 || cm.isQuestFinished(7301)) {
	    cm.cancelItem(2210003);
	    cm.warp(240050400, 0);
	    if (cm.haveItem(4031454)) { // Paladin
		cm.gainItem(4031454, -1);
		cm.gainItem(4031455, 1);
	    }
	    if (cm.getQuestStatus(6169) == 1) { // Temporary
		cm.gainItem(4031461, 1);
	    }
	} else {
		if(cm.haveItem(5220006,1) &&(cm.getPlayer().getBossLog("hlg") <= 黑龙次数)&&(jobid==112||jobid==122||jobid==132||jobid==212||jobid==222||jobid==232||jobid==312||jobid==322||jobid==412||jobid==422||jobid==512||jobid==522||jobid==2112||jobid==1112||jobid==1111||jobid==1211||jobid==1311||jobid==1411||jobid==1511)){//判断每天小于等于3次可进入
			cm.gainItem(5220006, -1);
			cm.setBossLog("hlg");
			cm.warp(240050400, 0);
		}
     else{
	    cm.warp(240040600, "st00");
    }
	}
	}
	cm.dispose();
    }
}