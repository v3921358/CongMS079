var status = 0;


function start() {
	status = -1;
	action(1, 0, 0);
}

function action(mode, type, selection) {
	if (mode == -1 || mode == 0) {
		cm.dispose();
	} else {
		status++;
		if (status == 0) {
			cm.sendSimple("嗨，我是维护使者，快来领取您的回归的礼包哦,2天后我就消失了!\r\n#L0#领取礼包。");
		} else if (status == 1) {
			if(cm.getPlayer().getPrizeLog("回归礼包") < 1 ){
			if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("背包其他栏空间不足1格");
			cm.dispose();
			return;
		}
			
			cm.getPlayer().setPrizeLog("回归礼包");
			cm.gainItem(4000038,10);
			cm.getPlayer().modifyCSPoints(1,800000, true);//点券
    		cm.全服黄色喇叭("[公告事项] : 恭喜玩家 "+cm.getPlayer().getName()+" 成功领取了回归礼包奖励。")
			cm.dispose();
			
			}else{
			cm.sendOk("一个账号只可以领取一次哦");
			cm.dispose();
			}
			cm.dispose();
		}
	}
}

function getEimForGuild(em, id) {
        var stringId = "" + id;
        return em.getInstance(stringId);
}