var setupTask;

function init() {
    scheduleNew();
}

function scheduleNew() {
    var cal = java.util.Calendar.getInstance();
    cal.set(java.util.Calendar.HOUR, 0);
    cal.set(java.util.Calendar.MINUTE, 50);
    cal.set(java.util.Calendar.SECOND, 0);
	
    var nextTime = cal.getTimeInMillis();
	
    while (nextTime <= java.lang.System.currentTimeMillis()) {
        nextTime += 1000 * 60;
    }
    		setupTask = em.scheduleAtTimestamp("start", nextTime);
}

function cancelSchedule() {
	if (setupTask!=null)
  		setupTask.cancel(true);
}

function start() {
	var cal = java.util.Calendar.getInstance();
	var hour = cal.get(java.util.Calendar.HOUR);
	var minute = cal.get(java.util.Calendar.MINUTE);
	var sec = cal.get(java.util.Calendar.SECOND);
	var weekday = cal.get(java.util.Calendar.DAY_OF_WEEK);
	weekday-=1;
	scheduleNew();
	//每天第一个喇叭提醒。
	if (hour == 23 && minute == 59 ) {	
		em.broadcastServerMsg(5120008, " 亲爱的【帅哥/美女】，新的一天又开始了，祝您游戏愉快", true);
	}
	
	if (hour == 6 && minute == 00 ) {	
		em.broadcastServerMsg(5120008, " 亲爱的【帅哥/美女】，现在北京时间6点整，一日之计在于晨，祝您游戏愉快", true);
	}
	
	if (hour == 8 && minute == 2 ) {	
		em.broadcastServerMsg(5120008, " 亲爱的【帅哥/美女】，本服禁止一切非法程序，发现非法玩家举报有奖，祝您游戏愉快", true);
	}
	
	if (hour == 10 && minute == 2 ) {	
		em.broadcastServerMsg(5120008, " 亲爱的【帅哥/美女】，本服禁止一切非法程序，发现非法玩家举报有奖，祝您游戏愉快", true);
	}
	
	if (hour == 12 && minute == 00 ) {	
		em.broadcastServerMsg(5120008, " 亲爱的【帅哥/美女】，午餐时间到了，人是铁饭是钢，一顿不吃饿的慌，祝您游戏愉快", true);
	}
	
	if (hour == 14 && minute == 2 ) {	
		em.broadcastServerMsg(5120008, " 亲爱的【帅哥/美女】，本服禁止一切非法程序，发现非法玩家举报有奖，祝您游戏愉快", true);
	}
	
	if (hour == 16 && minute == 2 ) {	
		em.broadcastServerMsg(5120008, " 亲爱的【帅哥/美女】，本服禁止一切非法程序，发现非法玩家举报有奖，祝您游戏愉快", true);
	}
	
	if (hour == 18 && minute == 2 ) {	
		em.broadcastServerMsg(5120008, " 亲爱的【帅哥/美女】，烛光晚餐的时刻到来了，早上吃好，中午吃饱，晚上吃少，祝您游戏愉快", true);
	}
	
	if (hour == 20 && minute == 2 ) {	
		em.broadcastServerMsg(5120008, " 亲爱的【帅哥/美女】，本服禁止一切非法程序，发现非法玩家举报有奖，祝您游戏愉快", true);
	}
	
	if (hour == 22 && minute == 00 ) {	
		em.broadcastServerMsg(5120008, " 亲爱的【帅哥/美女】，现在北京时候22点整，忙碌了一天的伙伴们该睡觉了，祝您有个好梦", true);
	}
	
	
	
	if (minute == 49) {
		em.broadcastServerMsg(5120023," < 答题活动 > 每小时50分时的 < 答题 > 在市场【NPC活动中心】开放了,小伙伴们抓紧时间做作业吧..",true);
		} else if (minute == 59) {
		em.broadcastServerMsg(5120023," < 答题活动 >  本次答题活动结束了，请期待下个小时50分的时候继续回来答题哦.",true);
	}
	
	//if(hour == 20 && (minute == 20) && (weekday == 6 || weekday == 5 || weekday == 0)){
	if(hour == 20 && minute == 20 ){
		em.broadcastServerMsg(5121028," < 挤牛奶活动 >  10分钟后将开启 ，大家抓紧时间做好准备吧！",true);
		} else if (hour == 20 && minute == 30 ) {
		em.broadcastServerMsg(5120023," < 挤牛奶活动 >  挤牛奶活动开始了，请赶紧回来市场【NPC活动中心】参加.",true);
		} else if (hour == 20 && minute == 36 ) {
		em.broadcastServerMsg(5120023," < 挤牛奶活动 >  挤牛奶活动结束了，请期待明天20点30分的时候继续回来参加哦.",true);	
	}
	

	
	}
function monsterDrop(eim, player, mob) {}