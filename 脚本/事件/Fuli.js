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
	var hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
	var min = cal.get(java.util.Calendar.MINUTE);
	var sec = cal.get(java.util.Calendar.SECOND);
	var weekday = cal.get(java.util.Calendar.DAY_OF_WEEK);
	var month = cal.get(java.util.Calendar.MONTH) + 1; //获得月份
	var day = cal.get(java.util.Calendar.DATE); //获取日
	weekday-=1;
	scheduleNew();

	//国庆节福利

		if ((month == 4) && (day == 13 || day == 20) ) {
		em.broadcastServerMsg(5120008,"内测期间女神大量赐福！请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 20 && min == 04){
		em.broadcastServerMsg(5120008,"内测期间女神大量赐福！请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 10 && min == 35){
		em.broadcastServerMsg(5120008,"内测期间女神大量赐福！请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 11 && min == 38){
		em.broadcastServerMsg(5120008,"内测期间女神大量赐福！请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 11 && min == 40){
		em.broadcastServerMsg(5120008,"内测期间女神大量赐福！请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 16 && min == 35){
		em.broadcastServerMsg(5120008,"内测期间女神大量赐福！请留意女神赐福系统公告，赐福时千万别掉线~~",true);

}
if (hour == 20 && min >= 11&& min <= 13){
	//if ((month==12 && day>=4 && day<= 11 && hour ==11 && (min>=56 && min < 58)) {//10月12-17日19点00分发放奖励
		java.lang.System.out.println("正在执行点卷发放函数，执行时间"+hour+"点"+min+"分"+sec+"秒。");
		em.broadcastServerMsg(5120008,"管理员正在发放礼物中，请保持在线。",true);
		var allPlayers = em.getMapFactoryMap(910000000).getCharacters();
		//var allPlayers = em.getChannelServer().getPlayerStorage().getAllCharacters();
		allPlayers = allPlayers.iterator();
		while (allPlayers.hasNext()) {//循环每一个玩家
			
			var player = allPlayers.next();
			
			if (player.getLevel() < 10) {
				if (player.getBossLog("双十一点卷失败提示") == 0) {
					player.dropMessage(6, "您因等级不足10级，无法获得今日赠出的点卷");
					player.setBossLog("双十一点卷失败提示");
				}
				continue;	
			}
			
			var p = player.getBossLog("双十一送点卷11");
			if (p <= 0) {
				player.setBossLog("双十一送点卷11");
				var text = "\r\n您获得了管理员增出的110,000点卷以及一个庆祝箱！\r\n";
					//player.dropMessage(6, "您因等级不足10级，无法获得今日赠出的点卷");
				player.modifyCSPoints(1, 110000);
				//player.addById(2434364, 1, "庆祝箱");
				//player.addById(2434364, 1, "国庆活动");
				player.dropMessage(1,text);	
			}
			
		}
	}

	/*//国庆节福利
		if ((month == 10) && (day == 12 || day == 17) && (hour == 00 && ( min == 35) {
		em.broadcastServerMsg(5120074,"内测期间女神大量赐福！请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 07 && min == 35){
		em.broadcastServerMsg(5121047,"内测期间女神大量赐福！请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 10 && min == 35){
		em.broadcastServerMsg(5121047,"内测期间女神大量赐福！请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 12 && min == 35){
		em.broadcastServerMsg(5121047,"内测期间女神大量赐福！请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 14 && min == 35){
		em.broadcastServerMsg(5121047,"内测期间女神大量赐福！请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 16 && min == 35){
		em.broadcastServerMsg(5121047,"内测期间女神大量赐福！请留意女神赐福系统公告，赐福时千万别掉线~~",true);


	} else if(hour == 18 && min == 55){
		em.broadcastServerMsg(5121047,"还有5分钟，女神马上要来赐福了哦，千万别掉线~~",true);
	} else if(hour == 18 && min == 59){
		em.broadcastServerMsg(5121047,"还有1分钟，女神马上要来赐福了哦，千万别掉线~~",true);
	} else if(hour == 19 && min == 01){
		em.broadcastServerMsg(5121047,"19点0分钟，女神赐福完毕了哦，请在19点30分钟继续迎接女神赐福~~",true);

	
}
	} elseif (month==10 && day>=12 && day<= 17 && hour ==19 && (min>=00 && min < 1)) {//10月12-17日19点00分发放奖励
		java.lang.System.out.println("正在执行点卷发放函数，执行时间"+hour+"点"+min+"分"+sec+"秒。");
		em.broadcastServerMsg(5121048,"女神管理员 小雨 正在发放礼物中，请保持在线。",true);
		var allPlayers = em.getChannelServer().getPlayerStorage().getAllCharacters();
		allPlayers = allPlayers.iterator();
		while (allPlayers.hasNext()) {//循环每一个玩家
			
			var player = allPlayers.next();
			
			if (player.getLevel() < 200) {
				if (player.getBossLog("国庆点卷失败提示") == 0) {
					player.dropMessage(6, "您因等级不足200级，无法获得今日赠出的点卷");
					player.setBossLog("国庆点卷失败提示");
				}
				continue;	
			}
			
			var p = player.getBossLog("国庆送点卷");
			if (p <= 0) {
				player.setBossLog("国庆送点卷");
				var text = "< " + em.getChannelServer().getServerName() + " >\r\n您获得了管理员增出的10,000点卷以及一个国庆节庆祝箱！\r\n";
				player.modifyCSPoints(1, 10000);
				player.addById(2434364, 1, "国庆活动");
				player.dropMessage(1,text);	
			}
			
		}
	}
*/
/*
	if(hour == 08 && min == 35){
		em.broadcastServerMsg(5121047,"光棍节女神赐福！双11,20点20分请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 10 && min == 39){
		em.broadcastServerMsg(5121047,"光棍节女神赐福！双11,20点20分请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 12 && min == 39){
		em.broadcastServerMsg(5121047,"光棍节女神赐福！双11,20点20分请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 14 && min == 39){
		em.broadcastServerMsg(5121047,"光棍节女神赐福！双11,20点20分请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 16 && min == 39){
		em.broadcastServerMsg(5121047,"光棍节女神赐福！双11,20点20分请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 18 && min == 39){
		em.broadcastServerMsg(5121047,"光棍节女神赐福！双11,20点20分请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 19 && min == 39){
		em.broadcastServerMsg(5121047,"光棍节女神赐福！双11,20点10分请留意女神赐福系统公告，赐福时千万别掉线~~",true);
	} else if(hour == 20 && min == 15){
		em.broadcastServerMsg(5121047,"还有5分钟，女神马上要来赐福了哦，千万别掉线~~",true);
	} else if(hour == 20 && min == 19){
		em.broadcastServerMsg(5121047,"还有1分钟，女神马上要来赐福了哦，千万别掉线~~",true);
	} else if(hour == 20 && min == 21){
		em.broadcastServerMsg(5121047,"女神赐福完毕了哦，请大家自由活动~~",true);


	}




*/
	 if(hour == 19 && min == 52){
		em.broadcastServerMsg(5120008,"还有15分钟，女神马上要来赐福送礼物了哦，请保持在线，千万别掉线~~",true);
	} else if(hour == 19 && min == 55){
		em.broadcastServerMsg(5120008,"还有5分钟，女神马上要来赐福送礼物了哦，请保持在线，千万别掉线~~",true);
	} else if(hour == 20 && min == 01){
		em.broadcastServerMsg(5120008,"还有1分钟，女神马上要来赐福送礼物了哦，请保持在线，千万别掉线~~",true);


	}

	if (hour == 20 && min >= 02 && min <= 04){
		em.broadcastServerMsg(5120008,"女神正在赐福送礼物，幸运时可以获得国庆纪念币！",true);
		//var allPlayers = em.getChannelServer().getPlayerStorage().getAllCharacters();
		    //var allPlayers = em.getMapFactoryMap(910000000).getCharacters();
			var allPlayers = em.getChannelServer().getMapFactory().getMap(910000000).getCharacters();//取得当前地图上面的所有玩家
		allPlayers = allPlayers.iterator();
		while (allPlayers.hasNext()) {//循环每一个玩家
			var player = allPlayers.next();
			var p = player.getBossLog("女神赐福");
			if (p <= 0) {
				player.setBossLog("女神赐福");
				var text = "< " + em.getChannelServer().getServerName() + " >\r\您获得了女神在线赐福！\r\n";
				var gifts = Array(
					Array(4001126, 20, "枫叶"),
					Array(4000463, 20, "国庆币")

				);
				var random = Math.floor(Math.random()*100);
				if (random < 3) {
					gifts.push(Array(4000463, 100, "[幸运]国庆纪念币"));
				}
				for(var key in gifts) {
				//	player.getClient().getCM().gainItem(gifts[key][0], gifts[key][1]);
					player.addById(gifts[key][0], gifts[key][1], "女神赐福");
					text+=gifts[key][2]+"×"+gifts[key][1]+"\r\n";
				}
				text+="感谢您的支持！";
				player.dropMessage(1,text);	
			} 
		}
	}



   // java.lang.System.out.println(month+"-"+day+" "+hour+":"+min+" LABA WORK");
}
