
function start() {
    if (cm.getLevel() >=200 && cm.getEventCount("180分钟奖励点卷") == 0 && cm.getOnlineTime() >= 180) {
			cm.gainNX(6000);
            cm.setEventCount("180分钟奖励点卷");
			cm.playerMessage(1, "自动检测在线时间点券奖励系统------------领取180分钟奖励成功！");
			cm.worldSpouseMessage(0x20,"[自动领取系统] "+ cm.getChar().getName() + " 玩家今天上线已经达到180分钟,给予6000点卷作为奖励.");
			cm.dispose();
		} else if (cm.getEventCount("180分钟奖励点卷") >= 1 && cm.getLevel() >=200 && cm.getEventCount("360分钟奖励点卷") == 0 && cm.getOnlineTime() >= 360){
			cm.gainNX(9000);
            cm.setEventCount("360分钟奖励点卷");
			cm.playerMessage(1, "自动检测在线时间点券奖励系统------------领取360分钟奖励成功！");
			cm.worldSpouseMessage(0x20,"[自动领取系统] "+ cm.getChar().getName() + " 玩家今天上线已经达到360分钟,给予9000点卷作为奖励.");
			cm.dispose();
		} else if (cm.getEventCount("360分钟奖励点卷") >= 1 && cm.getLevel() >=200 && cm.getEventCount("480分钟奖励点卷") == 0 && cm.getOnlineTime() >= 480){
			cm.gainNX(12000);
            cm.setEventCount("480分钟奖励点卷");
			cm.playerMessage(1, "自动检测在线时间点券奖励系统------------领取480分钟奖励成功！");
			cm.worldSpouseMessage(0x20,"[自动领取系统] "+ cm.getChar().getName() + " 玩家今天上线已经达到480分钟,给予12000点卷作为奖励.");
			cm.dispose();
		} else if (cm.getEventCount("480分钟奖励点卷") >= 1 && cm.getLevel() >=200 && cm.getEventCount("720分钟奖励点卷") == 0 && cm.getOnlineTime() >= 720){
			cm.gainNX(18000);
            cm.setEventCount("720分钟奖励点卷");
			cm.playerMessage(1, "自动检测在线时间点券奖励系统------------领取720分钟奖励成功！");
			cm.worldSpouseMessage(0x20,"[自动领取系统] "+ cm.getChar().getName() + " 玩家今天上线已经达到720分钟,给予18000点卷作为奖励.");
			cm.dispose();
		} else if (cm.getEventCount("720分钟奖励点卷") >= 1 && cm.getLevel() >=200 && cm.getEventCount("900分钟奖励点卷") == 0 && cm.getOnlineTime() >= 900){
			cm.addHyPay(-10);
			//cm.gainNX(10000);
            cm.setEventCount("900分钟奖励点卷");
			cm.playerMessage(1, "自动检测在线时间点券奖励系统------------领取900分钟奖励成功！");
			cm.worldSpouseMessage(0x20,"[自动领取系统] "+ cm.getChar().getName() + " 玩家今天上线已经达到900分钟,给予10余额作为奖励.");
			cm.dispose();
		} else {
			cm.dispose();
		}
}
