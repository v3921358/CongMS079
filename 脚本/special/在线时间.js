
var status = -1;
var text;
var sel;
var time;
var aaa ="#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var ca = java.util.Calendar.getInstance();
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE);//获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒

// 每个礼包所需的在线时长
var condition = new Array(30, 60, 120, 180, 240, 300, 360);
var reward = new Array(// 礼包编号、道具id、数量
					// 30
					Array(1, 2000005, 10), //超级药水
					Array(1, 5072000, 1, 1),  //喇叭5个
					Array(1, 3992025, 1),//圣诞大星
					//Array(1, 5062000, 1),//神奇魔方


					// 60
					Array(2, 2000005, 20),
					Array(2, 5072000, 2, 1),  //喇叭5个
					Array(2, 3992025, 2),//圣诞大星
					//Array(2, 5062000, 1),//神奇魔方
					
					// 120
					Array(3, 2049401, 1),//潜能卷70%
					Array(3, 2000005, 30), //超级药水
					Array(3, 5072000, 2, 1),  //喇叭5个
					Array(3, 3992025, 3),//圣诞大星
					//Array(3, 5062000, 1),//神奇魔方

					// 180
					Array(4, 2000005, 50), //超级药水3992025
					Array(4, 3992025, 5),//圣诞大星
					Array(4, 4000463, 1),
					//Array(4, 5062000, 1),//神奇魔方

					// 240
					Array(5, 2000005, 80), //超级药水3992025
					Array(5, 2340000, 1),
					Array(5, 3992025, 5),//圣诞大星
					Array(5, 4000463, 1),
					//Array(5, 5062000, 1),//神奇魔方

					// 300
					Array(6, 2000005, 100), //超级药水3992025
					Array(6, 2340000, 1),
					Array(6, 3992025, 5),//圣诞大星
					Array(6, 4000463, 1),
					//Array(6, 5062000, 2),//神奇魔方
					
					
					// 360
					Array(7, 2049400, 1),//高级潜能卷90%
					Array(7, 2000005, 100), //超级药水3992025
					Array(7, 2340000, 1),
					Array(7, 3992025, 10),//圣诞大星
					//Array(7, 4000464, 1),//中国心
					//Array(7, 5062000, 4),//神奇魔方
					Array(7, 4000463, 2)


			);

function start() {
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (status == 0 && mode == 0)
	{
		cm.dispose();
		return;
	}
	if (mode == 1)
	{
		status++;
	} else {
		status--;
	}

	var time = cm.getGamePoints();//在线时间函数
	var curlevel = -1;

	if (status == 0) {
		text = "#e#d您今天在[" + cm.getChannelServer().getServerName() + "]世界时长为： #r" + time + "#k #d分钟#n#k\r\n#e#d提示#n#k：#e#r23 ： 50#n #b至#r #e00 ： 10#n #b时无法领取在线奖励。#k\r\n#b请在 #e#r23：50#n#b 分前领取当天未领取的奖励。以免造成损失。#k\r\n\r\n";
		for (var i = 1; i <= condition.length; i++) {
			text += "#b#L" + i + "#"+aaa+" 领取在线" + condition[i-1] + "分钟奖励";
			if (cm.getBossLog("在线礼包" + i) > 0) {
				text += "(已领取)";
				curlevel = curlevel == -1 ? i : curlevel;
			}
			text += "#l\r\n";
		}
		text += "#k";
		cm.sendSimple(text);
	} else if (status == 1) {
		// 23:50 ~ 23: 59 前一天不领取的时间  00:00 ~ 00:10 第二天不领取的时间  
		if ((hour == 23 && (minute >= 50 && minute <= 59)) || (hour == 0 && (minute >= 0 && minute <= 10))){
			cm.sendOk("#d服务器当前时间： #r" + hour +" 时 " + minute + " 分 " + second + " 秒#k\r\n\r\n#e#d提示#n#k：#r23 ： 50 #b至#r 00 ： 10 #b时无法领取在线奖励。#k");
			cm.dispose();
			return;
		}
		if (cm.getBossLog("在线礼包" + selection) > 0) {
			cm.sendOk("这个礼包您已经领取过了");
			cm.dispose();
			return;
		}
		sel = selection;
		text = "\t\t\t\t#e#r- 在线 " + condition[selection - 1] + " 分钟奖励 -#k#n\r\n\r\n";
		for (var i = 0; i < reward.length; i++) {
			if (reward[i][0] == selection) {
				text += "\t\t\t#i" + reward[i][1] + "# #z" + reward[i][1] + "#[" + reward[i][2] + "个]\r\n";
			}
		}
		cm.sendYesNo(text);
	} else if (status == 2) {
		if (time < condition[sel-1]) {
			cm.sendOk("在线时间不足，无法领取。");
			cm.dispose();
			return;
		}
		var rewardlist = new Array();
		for (var i = 0; i < reward.length; i++) {
			if (reward[i][0] == sel) {
				if (reward[i][3] == null || reward[i][3] == '')
					reward[i][3]=0;
				rewardlist.push(new Array(reward[i][1], reward[i][2], reward[i][3]));
			}
		}
		if (!cm.canHoldSlots(rewardlist.length)) {
			cm.sendOk("包裹空间不足，请确保包裹每个栏位有至少 " + rewardlist.length + " 格空间");
			cm.dispose();
			return;
		}
		for (var i = 0; i < rewardlist.length; i++) {
			if (rewardlist[i][2] != 0) {
				//有期限道具
				cm.gainItemPeriod(rewardlist[i][0], rewardlist[i][1], rewardlist[i][2]);
				//java.lang.System.out.println("有");
			} else {
				//无期限道具
				cm.gainItem(rewardlist[i][0], rewardlist[i][1]);
			}
		}
		cm.setBossLog("在线礼包" + sel);
		cm.playerMessage(1, "领取成功！");
		//cm.worldMessage(6,"玩家：["+cm.getName()+"] 领取了在线 " + condition[sel-1] + " 分钟奖励。.");
		cm.全服黄色喇叭("[在线时间奖励] : " + " : " + "玩家 " + cm.getChar().getName() + " 领取了在线 " + condition[sel-1] + " 分钟奖励。")
		//cm.channelMessage(0x18, "『在线时间奖励』" + " : " + "玩家 " + cm.getChar().getName() + " 领取了在线 " + condition[sel-1] + " 分钟奖励。");
		if (sel == 4) {
			cm.finishActivity(120108);
		} else if (sel == 5) {
			cm.finishActivity(120109);
		}
		cm.dispose();
	}
}