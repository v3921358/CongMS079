/*
冒险岛(079)游戏服务端
 脚本：毒雾森林
 */

var 毒雾通关经验 = 8000;
var 积分 = 1;



function start() {
    status = -1;
    action(1, 0, 0)
}

function action(mode, type, selection) {
    if (status <= 0 && mode <= 0) {
        cm.dispose();
        return
    }
    if (mode == 1) {
        status++
    } else {
        status--
    }
    if (status <= 0) {
        var selStr = "\r\n";
        if (cm.getMapId() == 930000000) {			
            selStr += "\t从这里进去，开始冒险吧！\r\n";
        }
        if (cm.getMapId() == 930000100) {
            selStr += "\t和队友合作，清空当前地图怪物即可通关。\r\n";
        }
        if (cm.getMapId() == 930000200) {
            selStr += "\t把感染的怪物引导到桥上击杀，净化一下把。\r\n";
        }

        if (cm.getMapId() == 930000300) {
            selStr += "\r\n\r\n   #L2##b你绕过来了？#l\r\n";
        }
        if (cm.getMapId() == 930000600) {
            selStr += "\t拿着#b#v4001163##t4001163##k向右边石像召唤出巫毒巨人，并且战胜它吧。\r\n";
        }

        if (cm.getMapId() == 930000700) {
            selStr += "\r\n\r\n   #L3##b勇士你真厉害，通关了#l\r\n";	
			
        }


        if (cm.getMapId() != 930000700) {
            selStr += "\r\n\r\n   #L1##b退出副本#l\r\n";

        }
        cm.sendSimple(selStr)
    } else if (status == 1) {
	var next = true;
	var size = 0;
	var it = cm.getPlayer().getParty().getMembers().iterator();
			
        switch (selection) {
            case 1:
                cm.warp(300030100, 0);
                cm.dispose();
                break
            case 2:
                while (it.hasNext()) {
				var cPlayer = it.next();
				var ccPlayer = cm.getPlayer().getMap().getCharacterById(cPlayer.getId());
				if (ccPlayer == null) {
					next = false;
					break;
				}
				size++;
			    }
	            if (next && size >= 1) {
				cm.warpParty(930000500);
                cm.dispose();
				} else {
				cm.sendOk("队伍成员有人没在这里。");
				cm.dispose();
			    }	
                break
            case 3:
                //毒雾盛产邮票，恩
                cm.概率给物品2(4020001, 2, 60, "紫水晶母矿");
                cm.概率给物品2(4020002, 2, 60, "海蓝石母矿");
                cm.概率给物品2(4020003, 2, 60, "祖母绿母矿");
                cm.概率给物品2(4020004, 2, 60, "蛋白石母矿");
				cm.概率给物品2(4020005, 2, 60, "蓝宝石母矿");
				cm.概率给物品2(4020006, 2, 60, "黄晶母矿");
				cm.概率给物品2(4020007, 2, 60, "钻石母矿");
				cm.概率给物品2(4020008, 2, 60, "黑水晶母矿");
				cm.概率给物品2(4032391, 5, 60, "破碎的卷轴碎片1");
				cm.概率给物品2(4032392, 5, 60, "破碎的卷轴碎片2");

                //记录通关信息
				cm.gainzdjf(积分);//组队任务积分
                cm.gainExp(毒雾通关经验);
				gain积分("毒物积分",+1);
	            gain积分("副本积分",+1);
				cm.getPlayer().setBossLog("毒物副本");//给一条龙次数
                cm.getPlayer().endPartyQuest(1206);
                cm.removeAll(4001161);
                cm.removeAll(4001162);
                cm.removeAll(4001163);
                cm.removeAll(4001164);
                cm.removeAll(4001169);
                cm.removeAll(2270004);
                cm.warp(930000800, 0);
                cm.setBossRankCount("毒雾森林", 1);
                cm.setBossLog("毒雾森林");
                cm.worldMessage(2, "[副本-毒雾森林] : 恭喜 " + cm.getPlayer().getName() + " 完成毒雾森林副本。");
                break
				cm.dispose();

        }
		cm.dispose();
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