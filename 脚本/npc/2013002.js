/*
 ZEVMS冒险岛(079)游戏服务端
 脚本：女神塔奖励
 */
var status;
var 最后通关 = 5000;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    var em = cm.getEventManager("OrbisPQ");
    if (em == null) {
        cm.dispose();
        return;
    }
    for (var i = 4001044; i < 4001064; i++) {
        cm.removeAll(i);
    }
    switch (cm.getMapId()) {
        case 920010100:
            cm.showEffect(false, "quest/party/clear");
            cm.playSound(false, "Party1/Clear");
            cm.gainExp(33000);
            cm.getPlayer().endPartyQuest(1203);
            cm.warp(920011300);
            cm.dispose();
            break;
        default:
            if (mode == -1) {
                cm.dispose();
            }
            if (mode == 1)
                status++;
            else
                status--;
            if (status == 0) {
                cm.sendNext("请确认你的其他栏有没有空八格,另外消耗栏位空一格就好,确认都有空再来跟我对话");
            } else if (status == 1) {
                //女神塔奖励：物品，数量内概率给数量，几率				
				
                
                cm.概率给物品2(4004000, 1, 80, "力量母矿");
                cm.概率给物品2(4004001, 1, 80, "智慧母矿");
                cm.概率给物品2(4004002, 1, 80, "敏捷母矿");
                cm.概率给物品2(4004003, 1, 80, "幸运母矿");
                cm.概率给物品2(4004004, 1, 80, "黑暗水晶母矿");
                cm.概率给物品2(4170006, 1, 100, "天空蛋");
                cm.概率给物品2(4000313, 5, 70, "黄金枫叶");
                cm.概率给物品2(4001126, 5, 70, "枫叶");
                cm.概率给物品2(2049100, 1, 50, "混沌卷轴60%");
                


                //记录通关信息
				cm.给经验(cm.getLevel()*最后通关);
				gain积分("天空积分",+1);
				cm.getPlayer().setBossLog("天空副本");//给副本一条龙每天次数
	            gain积分("副本积分",+1);
				cm.gainzdjf(+1);//组队任务积分
                cm.setBossRankCount("女神塔", 1);
                cm.setBossLog("女神塔");
                cm.worldMessage(2, "[副本-女神塔] : 恭喜 " + cm.getPlayer().getName() + " ，完成天空副本。");
                
                cm.warp(200080101);
                cm.dispose();
                break;
            }
    }
}
function get积分(a) {
	var jf = 0;
		switch (a) {
		case "废弃积分":
			jf = Number(cm.getQuestRecord(844440).getCustomData());
			break;
		case "玩具积分":
			jf = Number(cm.getQuestRecord(844441).getCustomData());
			break;
		case "天空积分":
			jf = Number(cm.getQuestRecord(844442).getCustomData());
			break;
		case "海盗积分":
			jf = Number(cm.getQuestRecord(844443).getCustomData());
			break;
		case "毒物积分":
			jf = Number(cm.getQuestRecord(844444).getCustomData());
			break;
		case "月妙积分":
			jf = Number(cm.getQuestRecord(844445).getCustomData());
			break;
		case "狗男积分":
			jf = Number(cm.getQuestRecord(844446).getCustomData());
			break;
		case "副本积分":
			jf = Number(cm.getQuestRecord(844447).getCustomData());
			break;
		case "带新积分":
			jf = Number(cm.getQuestRecord(844450).getCustomData());
			break;
		}

		return jf;
}

function gain积分(a,b) {
	var jf = 0;
		switch (a) {
		case "废弃积分":
			jf = Number(cm.getQuestRecord(844440).getCustomData());
			cm.getQuestRecord(844440).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "玩具积分":
			jf = Number(cm.getQuestRecord(844441).getCustomData());
			cm.getQuestRecord(844441).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "天空积分":
			jf = Number(cm.getQuestRecord(844442).getCustomData());
			cm.getQuestRecord(844442).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "海盗积分":
			jf = Number(cm.getQuestRecord(844443).getCustomData());
			cm.getQuestRecord(844443).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "毒物积分":
			jf = Number(cm.getQuestRecord(844444).getCustomData());
			cm.getQuestRecord(844444).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "月妙积分":
			jf = Number(cm.getQuestRecord(844445).getCustomData());
			cm.getQuestRecord(844445).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "狗男积分":
			jf = Number(cm.getQuestRecord(844446).getCustomData());
			cm.getQuestRecord(844446).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "副本积分":
			jf = Number(cm.getQuestRecord(844447).getCustomData());
			cm.getQuestRecord(844447).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		case "带新积分":
			jf = Number(cm.getQuestRecord(844450).getCustomData());
			cm.getQuestRecord(844450).setCustomData("" + (jf+b));
			cm.getPlayer().saveToDB(false, false);
			break;
		}

}