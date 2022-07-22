﻿var status = -1;
var random = java.lang.Math.floor(Math.random() * 9 + 1);
var random1 = java.lang.Math.floor(Math.random() * 10 + 1);
var random2 = java.lang.Math.floor(Math.random() * 10 + 1);

function action(mode, type, selection) {
    if (mode == 0 && status == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        cm.sendSimple("你好，我是小老虎 #bRice Cakes#k...#b\r\n#L1#查看说明#l\r\n#L2#离开地图#l\r\n#L0#我给你带来了10个年糕!#l");
    } else if (status == 1) {
        if (selection == 0) {
            if (!cm.isLeader()) {
                cm.sendOk("请队长与我谈话.");
            } else {
                if (cm.haveItem(4001101,10)) {
                    cm.removeAll(4001101);
                    cm.givePartyExp(8000);
                    cm.endPartyQuest(1200);
                    cm.warpParty(910010300);
                    cm.gainMeso(+100000);//读取变量
	            //cm.givePartyItems(4001129,5);
                   cm.givePartyItems(4170013,1);
                   cm.setPartyBossLog('MRYM');
				   
				   Packages.handling.world.World.Broadcast.broadcastMessage(Packages.tools.MaplePacketCreator.serverNotice(3, cm.getClient().getChannel(), "『组队任务 - 月妙副本』" + " : " + "[" + cm.getChar().getName() + "]成功通关月妙副本 获得超级丰富的奖励！！")); 
        cm.dispose();
                } else {
                    cm.sendNext("你没有带来 #r10#k 块月妙的年糕... ");
                }
            }
        } else if (selection == 1) {
            cm.sendNext("请点击地块让它开花，6块土地全部开花时满月将出现。满月下会召唤月妙小仙子，每隔一段时间月妙小仙子会捣出年糕，收集 #r10 #k块年糕后交给队长然后交给NPC，即可通关。\r\n#r注：在月妙仙子捣年糕的时候保护它，月妙仙子被怪物攻击后死亡则任务失败.");
        } else if (selection == 2) {
        cm.removeAll(4001095);
        cm.removeAll(4001096);
        cm.removeAll(4001097);
        cm.removeAll(4001098);
        cm.removeAll(4001099);
        cm.removeAll(4001100);
     
                    cm.warp(100000200);
        }
        cm.dispose();
    }
}