/* ==================
 脚本类型:  任务	    
 脚本版权：游戏创意
 联系扣扣：12384161
 =====================
 */
 var status = -1;

function start(mode, type, selection) {
	qm.forceStartQuest();//开始任务
	
	qm.dispose();
}

function end(mode, type, selection) {

	qm.forceCompleteQuest();//完成任务
	qm.dispose();
}