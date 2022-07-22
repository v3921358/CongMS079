var setupTask;

function init() {
    scheduleNew();
}

function scheduleNew() {
    var cal = java.util.Calendar.getInstance();
    cal.set(java.util.Calendar.HOUR, 0);
    cal.set(java.util.Calendar.MINUTE, 0);
    cal.set(java.util.Calendar.SECOND, 0);
    var nextTime = cal.getTimeInMillis();
    while (nextTime <= java.lang.System.currentTimeMillis()) {
        nextTime += 1000 * 30;
    }
    setupTask = em.scheduleAtTimestamp("start", nextTime);
}

function cancelSchedule() {
    setupTask.cancel(true);
}

function start() {
	scheduleNew();
	var allPlayers = em.getChannelServer().getMapFactory().getMap(910000000).getCharacters();//取得当前地图上面的所有玩家
	//var allPlayers = em.getChannelAllChr();
	allPlayers = allPlayers.iterator();
	while (allPlayers.hasNext()) {//循环每一个玩家
		var player = allPlayers.next();
		if (player.getMapId() == 910000000 && player.haveItem(2430865)) {
			var points = 30;
			//if (player.haveItem(2430865)) {
			//	points = 15;
			//}
			player.modifyCSPoints(1, 15);
			player.modifyCSPoints(2, points);
			//player.openNpc(2008);
			player.dropMessage(5,"[理财三倍泡点奖励]： 获得 [15] 点卷,[ "+points+" ] 抵用卷 ");
			player.dropMessage(-1,"[理财三倍泡点奖励]： 获得 [15] 点卷,[ "+points+" ]抵用卷 ");
			} else if (player.getMapId() == 910000000) {
             var points1 = 10;
			//if (player.haveItem(2430865)) {
			//	points = 15;
			//}
			player.modifyCSPoints(1, 5);
			player.modifyCSPoints(2, points1);
			player.openNpc(9330079,"4周年装备");
			//player.openNpc(2008);
			player.dropMessage(5,"[泡点奖励]： 获得 [5] 点卷,[ "+points1+" ] 抵用卷 ");
			player.dropMessage(-1,"[泡点奖励]： 获得 [5] 点卷,[ "+points1+" ]抵用卷 ");
			//player.dropMessage(-1,"[五倍泡点奖励]：获得 [5] 点卷,[50] 抵用卷,[25] 绑定回忆币。");
			//player.dropMessage(5,"[五倍泡点奖励]：8月17日-20日市场每30秒获得 [5] 点卷,[50] 抵用卷,[25] 绑定回忆币。");
		}			
	}
}