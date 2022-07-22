var head = "#fUI/UIWindow2.img/Quest/quest_info/summary_icon/summary#\r\n";
var icon = "#fUI/UIWindow/Minigame/Common/mark#";
var sl1 = 0;

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
		   if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("背包其他栏空间不足1格");
			cm.dispose();
			return;
		}
          if (cm.getPlayer().getPrizeLog("森林跳跳") < 5 ) {		
			cm.getPlayer().setPrizeLog("森林跳跳");	
			cm.gainItem(4001129,1);	
			cm.warp(105040300);
			cm.sendOk("领取成功！");
			cm.全服黄色喇叭("[公告事项] : 恭喜玩家 "+cm.getPlayer().getName()+" 成功领取了跳跳地图森林跳跳的奖励。")
            cm.dispose();
				
		} else {
            cm.sendOk("一个账号只可以领取五次。");
            cm.dispose();



            }
        }

}
