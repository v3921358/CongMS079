/*
 枫叶创意冒险岛(079)游戏服务端
 脚本：月妙
 */

//通关年糕数量
var 通关数量 = 10;
var 通关经验 = 30000;


var status = -1;

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.对话结束();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
        cm.sendSimple("请收集种子放在地块让它开花，6块土地全部开花时满月将出现。满月下会召唤月妙小兔子，每隔一段时间月妙小兔子会捣出年糕，收集 #r10 #k块年糕后交给队长然后交给NPC，即可通关。\r\n#r注：在月妙兔子捣年糕的时候保护它，月妙兔子被怪物攻击后死亡则任务失败#b\r\n#L0##v4001101# x " + 通关数量 + " 通关#l\r\n#L1##v4001101# x 20 换 #v1002798##l#k\r\n\r\n#L3##r《点击离开》#l");
    } else if (status == 1) {
        if (selection == 0) {
            if (!cm.isLeader()) {
                cm.sendNext("只有队长给的我才要吃");
                cm.对话结束();
            } else {
                if (cm.haveItem(4001101, 通关数量)) {
                    cm.gainItem(4001101, -通关数量);
					
					cm.removeAll(4001095);//扣除背包全部这个物品
					cm.removeAll(4001096);//扣除背包全部这个物品
					cm.removeAll(4001097);//扣除背包全部这个物品
					cm.removeAll(4001098);//扣除背包全部这个物品
					cm.removeAll(4001099);//扣除背包全部这个物品
					cm.removeAll(4001100);//扣除背包全部这个物品
					
                    cm.showEffect(true, "quest/party/clear");
                    cm.playSound(true, "Party1/Clear");
                    cm.givePartyExp(通关经验);
					cm.endPartyQuest(1200);
                    cm.warpParty(910010100);
                    
                    cm.worldMessage(2, "[副本-月妙] : 恭喜 " + cm.getPlayer().getName() + " 带领队伍，完成月妙副本。");
                    cm.对话结束();
                } else {
                    cm.sendNext("你没有年糕啊？");
                    cm.对话结束();
                }
            }
        } else if (selection == 1) {
            if (cm.haveItem(1002798, 1)) {
                cm.sendOk("你已经有了");
            } else if (!cm.canHold(1002798, 1)) {
                cm.sendOk("你已经有了");
            } else if (cm.haveItem(4001101, 20)) {
                cm.gainItem(4001101, -20); 
                cm.gainItem(1002798, 1);
            } else {
                cm.sendOk("你需要20个月妙的元宵");
            }
            cm.对话结束();
        } else if (selection == 3) {
			cm.removeAll(4001101);//扣除背包全部这个物品
			cm.removeAll(4001095);//扣除背包全部这个物品
			cm.removeAll(4001096);//扣除背包全部这个物品
			cm.removeAll(4001097);//扣除背包全部这个物品
			cm.removeAll(4001098);//扣除背包全部这个物品
			cm.removeAll(4001099);//扣除背包全部这个物品
			cm.removeAll(4001100);//扣除背包全部这个物品
            cm.warp(100000200);
			cm.对话结束();
        }
    }
}