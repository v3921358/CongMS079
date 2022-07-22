var status = 0;
//////////////////////////////////////////////////
//礼包名称
var bosslogId = "星之力勋章";
//礼包开始领取时间
var startTime = "2015-7-31 18:00:00";
//礼包结束领取时间
var endTime = "2015-8-6 23:00:00";
//领取要求最小等级
var minLevel = 50;
//需要在线时间
var minOnlineTime = 240;
//////////////////////////////////////////////////
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
			var text = "你好，我是福利活动特使，我会为你带来最新福利活动\r\n";
			text+= "#b管理提示：懒人请无视，此福利越勤奋获得福利越多#k\r\n";
			text+= "#b不要说没有福利，就看你是不是勤快了#k\r\n";
			text+= "#n请选择：#k\r\n";
			text+="#b#L8#红包福利\t#r[本福利长期有效]#l#k\r\n\r\n";
			text+="#b#L9#魔方福利\t#r[本福利长期有效]#l#k\r\n\r\n";
			//text+="#b#L1#领取星之力勋章 #r[开服7天有效]#l#k\r\n";
			//text+="#r#L6#暑假怎能少了猜谜，用智商碾压土豪！#l\r\n#b";
			//text+="#b#L3#领取开服冲级七天礼 #r[8月10日-8月12日]#l#k\r\n";
			//text+="#L3#领取开服首冲奖励[7月7日-7月13日]#l\r\n";
			text+="#b#L4#领取点卷礼包  #r[8月14日-8月20日]#l#k\r\n\r\n"
			//text+="#r#L5#28日停服维护补偿[2015年3月1日晚上8点40分]#l\r\n"
			//text+="#L3#了解什么是节日礼物#l\r\n";//限量礼包将于今晚8:30开始发放
			//text+="#b#L7#领取惊喜礼包#r[8月9日21点10分开始疯抢]#b限量18个#k#l\r\n"
			text+="#b#L10#收集情书活动  #r[8月13日-8月20日]#l#k\r\n"
			text+="\t"
			cm.sendSimple(text);
		} else if (status == 1){
			if (selection == 1) {
				
				if (cm.getPlayer().getLevel() < minLevel) {
					cm.sendOk("您的等级小于#r"+minLevel+"#k级，无法领取礼包，请加油哦！");
					cm.dispose();
					return; 
				}
				if (cm.getPlayer().getTodayOnlineTime() < minOnlineTime) {
					cm.sendOk("在线时间小于#r"+minOnlineTime+"#k分钟，无法领取礼包，请加油哦！");
					cm.dispose();
					return; 
				}
				var currentTimestamp = java.lang.System.currentTimeMillis();
				var startTimestamp = java.sql.Timestamp.valueOf(startTime).getTime();
				var endTimestamp = java.sql.Timestamp.valueOf(endTime).getTime();
				//限制领取时段
				if (currentTimestamp < startTimestamp || currentTimestamp > endTimestamp) {
					cm.sendOk("领取时间为：#r"+startTime.substring(0, 16)+"#k至#r"+endTime.substring(0, 16)+"#k，当前时间还未到或已经超时");
					cm.dispose();
					return ;
				}
				
				
					//定义礼包内容 ID,数量
					var itemList = Array(
						Array(2430193,1)
					);
					var str = "您成功领取了礼包，礼包内容如下：\r\n";
					for (var key in itemList) {
						str +="#b#v"+itemList[key][0]+"##t"+itemList[key][0]+"##kx#r"+itemList[key][1]+"#k\r\n";
						cm.gainItem(itemList[key][0], itemList[key][1]);
					}
					cm.sendOk(str);
					cm.dispose();
				
			} else if (selection == 2) {/*
				var giftBosslogId = '节日礼包20150101';
				if (cm.getBossLogAcc(giftBosslogId)!=-1) {
					
					if (cm.getPlayer().getLevel() < minLevel) {
						cm.sendOk("您的等级小于#r"+minLevel+"#k级，无法领取礼包，请加油哦！");
						cm.dispose();
						return; 
					}
					if (cm.getPlayer().getTodayOnlineTime() < minOnlineTime) {
						cm.sendOk("在线时间小于#r"+minOnlineTime+"#k分钟，无法领取礼包，请加油哦！");
						cm.dispose();
						return; 
					}
					//写入BOSSLOG
					cm.setBossLogAcc(giftBosslogId, -2);
					var itemList = Array(
						Array(5062000, 10),
						Array(5062002, 10),
						Array(5062500, 10),
						Array(2431741, 5)
					);
					var str = "您成功领取了节日礼包，礼包内容如下：\r\n";
					for (var key in itemList) {
						str +="#b#v"+itemList[key][0]+"##t"+itemList[key][0]+"##kx#r"+itemList[key][1]+"#k\r\n";
						cm.gainItem(itemList[key][0], itemList[key][1]);
					}
					cm.sendOk(str);
					cm.dispose();
				} else {
					cm.sendOk("您已经领过礼包了！");
					cm.dispose();
				}*/
				cm.dispose();
				cm.openNpc(9201116, 1);

			} else if (selection == 3) {//冲级礼包
				cm.dispose();
				cm.openNpc(9310058, 3);
				//cm.dispose();

			} else if (selection == 4) {//福利点卷
				cm.dispose();
				cm.openNpc(9310058, 4);
				//cm.dispose();

			} else if (selection == 5) {//停机维护补偿礼包
				cm.dispose();
				cm.openNpc(9310058, 5);
				//cm.dispose();

			} else if (selection == 6) {//猜谜活动
				cm.dispose();
				cm.openNpc(9310058, 6);
				//cm.dispose();

			} else if (selection == 7) {//限量礼包
				cm.dispose();
				cm.openNpc(9310058, 7);

			} else if (selection == 8) {//红包制作
				cm.dispose();
				cm.openNpc(9310058, 1);

			} else if (selection == 9) {// 魔方制作
				cm.dispose();
				cm.openNpc(9310058, 2);
				//cm.dispose();
			} else if (selection == 10) {// 贺年卡
				cm.dispose();
				cm.openNpc(9310058, 10);
				//cm.dispose();
			}
		}
   }
}