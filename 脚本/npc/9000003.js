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
			cm.sendSimple("嗨，我是#p9000003#，需要帮忙吗？？\r\n#L0#我拿到宝箱了。");
		} else if (status == 1) {
			if(cm.getPlayer().getPrizeLog("找宝箱") < 5 ){
			if (!cm.checkNumSpace(4, 1)) {
			cm.sendOk("背包其他栏空间不足1格");
			cm.dispose();
			return;
		}
			if(cm.haveItem(4031017)){
			cm.getPlayer().setPrizeLog("找宝箱");
			cm.removeAll(4031017);//扣除背包全部这个物品
			cm.gainItem(4001129,1);
    		cm.全服黄色喇叭("[公告事项] : 恭喜玩家 "+cm.getPlayer().getName()+" 成功领取了跳跳地图寻宝的奖励。")
			cm.dispose();
			} else {
			cm.sendOk("你没有#b#t4031017##k，你找我做什么？");
			cm.dispose();
			}
			}else{
			cm.sendOk("一个账号只可以做5次寻宝任务哦");
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