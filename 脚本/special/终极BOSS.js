/*
 
 脚本内容：传送NPC
 制作人：枫叶.
 制作时间：2020-10-15 23:20:17
 
 */
 

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
		var selStr = "				#r#e < 终极BOSS挑战 > #k#n\r\n\r\n";
		selStr += "你今日已经挑战了: #r"+ cm.getBossLog("每日日BOSS挑战") +" #k次.\r\n\r\n";
	    selStr += "#b挑战BOSS\r\n\r\n等级达到200\r\n\r\n组队状态下1-3人.\r\n\r\n由队长找我进入,每天限制挑战10次.#l\r\n\r\n";
		
		selStr += "#L1##e#r开始挑战 - X轮BOSS战#l\r\n";
		cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
		    
		case 1:
			if (cm.getParty() == null) { 
            cm.sendOk("你没有队伍无法进入!");
            cm.dispose();
			} else if (!cm.isLeader()) { 
            cm.sendOk("请让你的队长和我说话!");
            cm.dispose();
			} else if(cm.partyMembersInMap() > 3){
			cm.sendOk("你的队伍人数不能超过3人!!!");
			cm.dispose();			
			} /*else if (cm.getPlayer().getMeso() < 20000000){//判断金币是否大于2000W.
			cm.sendOk("你的金币不足,入场费需要2000W.");
			cm.dispose();
			}*/ else if (cm.getPlayer().getLevel() < 200){//判断等级是否小于200.
			cm.sendOk("等级不足200,无法入场!");
			cm.dispose();
			} else if(cm.getBossLog("每日BOSS挑战") > 10){//判断每日入场次数是否大于10.
			cm.sendOk("你今天已经挑战过10次了,无法入场.");
			cm.dispose();
			}else if(cm.getPlayerCount(970000005) > 0){
			cm.sendOk("里面已经有人在挑战了,请更换频道或者等一会!!!");	
			}else{
			//cm.gainMeso(-20000000);//金币2000w
			cm.warpParty(970000005);
			cm.setBossLog("每日日BOSS挑战");
			}
			cm.dispose();
			
        }
    }
}