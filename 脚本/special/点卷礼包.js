var head = "#fUI/UIWindow2.img/Quest/quest_info/summary_icon/summary#\r\n";
var icon = "#fUI/UIWindow/Minigame/Common/mark#";
var 金币图标 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var sl1 = 0;//兑换数量

var status = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == 1) {
		status++;
	} else {
		if (status >= 0) {
			cm.dispose();
			return;
		}
		status--;
	}
          if (status == 0) {

			//var text = "#h0#e#d 欢迎来到" + cm.getServerName() + "#k,先大概了解一下本服萌新特色\r\n";
			    var text = "#r点卷礼包介绍：确认点下一页立即领取#k#n\r\n#r等级超过50级，就可以领取。#k#n\r\n";
				
				
				text += ""+ 金币图标 +" 点卷：10000#k#n\r\n\r\n";
				
			cm.sendSimple(text);
		cm.sendNextS(text, 1);
		} else if (status == 1) {

		if (cm.getPlayer().getPrizeLog("点卷礼包1") < 1 && cm.getPlayerStat("LVL") > 49) {
			
			cm.getPlayer().setPrizeLog("点卷礼包1");	
            cm.getPlayer().modifyCSPoints(1,10000, true);//点券			
			cm.sendOk("领取成功！");
			cm.全服黄色喇叭("[公告事项] : 恭喜玩家 "+cm.getPlayer().getName()+" 成功领取了点卷大礼包。")
            cm.dispose();
				
		} else {
            cm.sendOk("您的等级还没超过50级，一个账号只可以领取一次。");
            cm.dispose();



            }
        }

}
