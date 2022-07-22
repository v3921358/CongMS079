var setupTask;

function init() {
    scheduleNew();
}

function scheduleNew() {
    var cal = java.util.Calendar.getInstance();
    cal.set(java.util.Calendar.HOUR, 0);
    cal.set(java.util.Calendar.MINUTE, 30);
    cal.set(java.util.Calendar.SECOND, 0);
    var nextTime = cal.getTimeInMillis();
    while (nextTime <= java.lang.System.currentTimeMillis()) {
        nextTime += 1000 * 60 * 60 * 1;
    }
    setupTask = em.scheduleAtTimestamp("start", nextTime);
}

function cancelSchedule() {
    setupTask.cancel(true);
}

function start() {
	scheduleNew();
	//var allPlayers = em.getChannelServer().getMapFactory().getMap(910000000).getCharacters();//取得当前地图上面的所有玩家
	var allPlayers = em.getChannelAllChr();
	allPlayers = allPlayers.iterator();
	while (allPlayers.hasNext()) {//循环每一个玩家
		var player = allPlayers.next();
		if (player.getEventCount("自动领取点券系统") >= 1 && player.getEventCount("9分钟奖励点卷") == 0){
			player.openNpc(9330079,"jiance2");
	    } 
	}
}