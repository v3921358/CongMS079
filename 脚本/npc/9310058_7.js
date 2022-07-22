var status = 0;
var typed = 0;
var currenttimes;
var maxGifts = 18;

function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1) {
		cm.dispose();
	} else {
		if (mode == 0 && status == 0) {
			cm.dispose();
			return;
		}
		if (mode == 1)
			status++;
		else
			status--;
		if (status == 0) {
			var currentTimestamp = java.lang.System.currentTimeMillis();
			var ca = java.util.Calendar.getInstance();
			ca.set(2015, 8 - 1, 9, 21, 10, 0); //设置开放时间 月份要-1才是当前月份
			var startTimestamp = ca.getTimeInMillis();
			if (currentTimestamp < startTimestamp) {
				var lasttime = (startTimestamp - currentTimestamp) / (60 * 1000);
				cm.sendOk("限量礼包将于今晚" + ca.get(java.util.Calendar.HOUR_OF_DAY) + ":" + ca.get(java.util.Calendar.MINUTE) + "开始发放，本次发放#r18#k个礼包，距离活动开始还有#r" + Math.floor(lasttime) + "#k分钟，抓紧时间把包裹整理干净吧！！请各位做好准备！");
				cm.dispose();
				return;
			}
			var queryCurrenttimes = cm.getConnection().prepareStatement("select currenttimes from gamegift where id = 1;").executeQuery();
			if (queryCurrenttimes.next()) {
				currenttimes = queryCurrenttimes.getString("currenttimes");
			} else {
				currenttimes = 0;
			}
			//var text = "";
			var text = "#e#b【惊喜礼包】#n#k\r\n\r\n还剩#r" + (maxGifts - currenttimes) + "#k个礼包，每个账号只能领取一次，快来领取吧！\r\n领取前请确认您的包裹有足够的空间\r\n";
			text += "#b#L1#我要领取#l";
			if ((maxGifts - currenttimes) <= 0) {
				text = "真抱歉，你来晚了，礼包已经被领光了。";
			}
			cm.sendSimple(text);
			//cm.dispose();
		} else if (status == 1) {
			if (cm.getBossLogAcc("惊喜礼包") == -1) {
				cm.sendOk("对不起，您已经领过礼包了。");
				cm.dispose();
				return;
			}
			var queryCurrenttimes = cm.getConnection().prepareStatement("select currenttimes from gamegift where id = 1;").executeQuery();
			if (queryCurrenttimes.next()) {
				currenttimes = queryCurrenttimes.getString("currenttimes");
			} else {
				currenttimes = 0;
			}
			if (currenttimes >= maxGifts) {
				cm.sendOk("真悲剧，就在你犹豫的这一瞬间，礼包已经被一抢而空。");
				cm.dispose();
				return;
			}
			var text = "您成功领取了下列物品：\r\n#b";
			/*if (currenttimes <= 0) {
				cm.gainItem(3010715, 1);
				text += "幸福9周年蛋糕气球椅，";
			}
			if (currenttimes <= 2) {
				cm.gainItem(1003843, 1);
				text += "奇怪的狐狸面具，";
			}*/
			if (currenttimes <= 18) {
				//cm.gainItem(2049323, 10);
				//cm.gainItem(2049137, 10);
				//cm.gainItem(2431354, 1);
				cm.gainItem(5062009, 10);
				cm.gainItem(5062500, 10);
				//cm.gainItem(4310036, 1000);
				cm.gainItem(4001714, 10);
				cm.gainItem(4001893, 1000);
				cm.gainNX(2, 20000);
				cm.gainNX(1, 10000);
				//text += "惊人正义混沌卷轴 40%x10，无损高级装备强化卷x10，星火幸运箱子x1，超级魔方x10，大师附加魔方x10，征服币x1000，定居金100W金币x20，星星x1000，抵用卷x10000，点卷x5000";
				text += "超级魔方x10，大师附加魔方x10，定居金100W金币x10，星星x1000，抵用卷x20000，点卷x10000";
			}
			cm.getConnection().prepareStatement("update gamegift set currenttimes = currenttimes +1 where id = 1;").executeUpdate();
			cm.setBossLogAcc("惊喜礼包", -2);
			cm.sendOk(text);
			cm.worldSpouseMessage(0x24, "[系统公告] : 恭喜玩家【" + cm.getChar().getName() + "】抢到了第" + (currenttimes + 1) + "份限量大礼包！真是羡慕！");
			cm.dispose();
		}
	}
}