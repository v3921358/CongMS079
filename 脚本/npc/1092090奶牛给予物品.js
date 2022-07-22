var ca = java.util.Calendar.getInstance();
var year = ca.get(java.util.Calendar.YEAR); //获得年份
var month = ca.get(java.util.Calendar.MONTH) + 1; //获得月份
var day = ca.get(java.util.Calendar.DATE);//获取日
var hour = ca.get(java.util.Calendar.HOUR_OF_DAY); //获得小时
var minute = ca.get(java.util.Calendar.MINUTE);//获得分钟
var second = ca.get(java.util.Calendar.SECOND); //获得秒
var weekday = ca.get(java.util.Calendar.DAY_OF_WEEK);
var status = -1;
var head = "#fUI/UIWindow2.img/Quest/quest_info/summary_icon/summary#\r\n";
var icon = "#fUI/UIWindow2.img/QuestAlarm/BtQ/normal/0#";
var em = null;
function start() {
	if (em == null) em = cm.getEventManager("NewEvent45");
	if (em.getProperty("NiuNai_Count") == null)
		em.setProperty("NiuNai_Count", 0);
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {   
	if (mode == -1) {
		cm.dispose();
	}else {
        if (status >= 0 && mode == 0) {
		   cm.dispose();
		   return;                    
		}
		if (mode == 1) {
		   status++;
		}else {
		   status--;
		}
		if (status == 0) {
			weekday-=1;
			//3990000 - 红色数字 1 - 可以装饰圣诞树的红色数字1
			//3990001 - 红色数字 2 - 可以装饰圣诞树的红色数字2
			//3990002 - 红色数字 3 - 可以装饰圣诞树的红色数字3
			var text = head+="\r\n\t#e#d#h ##k#n，每周末#d（星期五、星期六、星期天）#k晚上8点30分至8点35分来找我参与挤牛奶活动吧，丰厚点卷大量魔方卷轴等你来抢哦！\r\n#b#L0#好，我知道了#l";
			//var text = "#r#h ##k，你好啊，每#r周五、周六、周日#k晚上的#b08:00至08:05#k可以参与挤牛奶活动，帮我挤牛奶，每挤一次即可获得#r2点#k点券~还有几率获得#b封印解除卷、魔方、防爆、祝福卷轴#k哦！";
			//text = "今晚9点至9点05分，4倍收益，抄点";
			if(hour == 20 && (minute >= 30 && minute <= 35) && (weekday == 6 || weekday == 5 || weekday == 0)){// || cm.getPlayer().getName() == "管理员卡菲"){
				var random = Math.floor(Math.random()*8000);
				if (random == 1258)
				{
					cm.getMap().startMapEffect("恭喜玩家 "+cm.getChar().getName()+" 额外获得【封印解除卷】一张。", 5120012);
					cm.worldSpouseMessage(0x15, "[奶牛妈妈] : 恭喜 【" + cm.getChar().getName() + "】 在挤牛奶的时候额外获得【封印解除卷】一张.");
					cm.gainItem(2610001, 1);
				} else if (random >= 1000 && random <= 5000) {
					var itemList = Array(2431894,2430112,2430915,2430481,2430760);
					var itemIdx = Math.floor(Math.random()*itemList.length);
					cm.gainItem(itemList[itemIdx], 1);
				} else if (random >= 5000 && random <= 5300) {
					var itemList = Array(5062002,5062500,4001713,4001713,4001713,2432352);
					var itemIdx = Math.floor(Math.random()*itemList.length);
					cm.worldSpouseMessage(0x15, "[奶牛妈妈] : 恭喜 【" + cm.getChar().getName() + "】 在挤牛奶的时候额外获得【"+cm.getItemName(itemList[itemIdx])+"】一个.");
				}
				/*
				var nCount = parseInt(em.getProperty("NiuNai_Count"));
				em.setProperty("NiuNai_Count", ++nCount);
				if (nCount == 5400) {
					cm.gainItem(3990002, 1);
					cm.worldSpouseMessage(0x15, "[奶牛妈妈] : 运气爆棚！恭喜 【" + cm.getChar().getName() + "】 三等奖。");
					cm.getMap().startMapEffect("[奶牛妈妈] : 恭喜 【" + cm.getChar().getName() + "】 获得今日三等奖。", 5120012);
				} else if (nCount == 10800){
					cm.gainItem(3990001, 1);
					cm.worldSpouseMessage(0x15, "[奶牛妈妈] : 运气爆棚！恭喜 【" + cm.getChar().getName() + "】 二等奖。");
					cm.getMap().startMapEffect("[奶牛妈妈] : 恭喜 【" + cm.getChar().getName() + "】 获得今日二等奖。", 5120012);
				} else if (nCount == 11200) {
					cm.gainItem(3990000, 1);
					cm.worldSpouseMessage(0x15, "[奶牛妈妈] : 运气爆棚！恭喜 【" + cm.getChar().getName() + "】 一等奖。");
					cm.getMap().startMapEffect("[奶牛妈妈] : 恭喜 【" + cm.getChar().getName() + "】 获得今日一等奖。", 5120012);
				}*/
				//cm.gainNX(1);
				cm.dispose();
			} else {
				em.setProperty("NiuNai_Count", 0);
				cm.sendOk(text);
				cm.dispose();
			}
		}
	}
}
