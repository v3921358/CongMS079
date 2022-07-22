 



var setupTask;
 
function init() {
    scheduleNew();
}
 
function scheduleNew() {
    var cal = java.util.Calendar.getInstance();
    cal.set(java.util.Calendar.SECOND, 5);
    var nextTime = cal.getTimeInMillis();
    while (nextTime <= java.lang.System.currentTimeMillis()) {
        nextTime += 1800 * 1000; //這裡就是設定多久存檔一次啦，單位是毫秒，可依據玩家數做調整
}
		em.broadcastYellowMsg("冒险岛 : 本服已开启自动存档,如有延迟现象属于正常");

		setupTask = em.scheduleAtTimestamp("start", nextTime);
}

 
function cancelSchedule() {
    setupTask.cancel(true);
}
 
function start() {
    scheduleNew();
    em.getChannelServer().saveAll();
    var iter = em.getInstances().iterator();
    while (iter.hasNext()) {
        var eim = iter.next();
    }
}  