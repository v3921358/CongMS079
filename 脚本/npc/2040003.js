// 機械零件的下落
var status = -1;
var id = 0;
var 任務道具 = Array(2040704, 2040705, 2040707);

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        status--;
    }
    if (status == 0) {
        var map = cm.getPlayer().getMapId();
        var mapid = 922000000;
        if (cm.getQuestStatus(3239) == 1 && map == mapid) {
            cm.sendNext("哦?完成了，讓我看看。");
            status++;
        } else if (cm.getQuestStatus(3239) == 1) {
            cm.sendNext("嗨，找我來破任務了阿??");
        } else {
            cm.sendNext("沒事別來妨礙我。");
            cm.dispose();
        }
    } else if (status == 1) {
        if (cm.getQuestStatus(3239) == 1) {
            var em = cm.getEventManager("Mechanical");
            if (em == null) {
                cm.sendOk("當前副本有問題，請聯絡管理員....");
            } else {
                var prop = em.getProperty("started");

                if (prop.equals("0") || prop == null) {
                    em.startInstance(cm.getPlayer());
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("裡面已經有人在挑戰...");
                }
            }
            cm.dispose();
        }
    } else if (status == 2) {
        cm.sendNext("啊哈~不簡單啊,在規定時間內把10個#b機械零件#k帶來了，好的！既然你為了我們玩具工廠付出這麼大的心力，我將送給你一個不錯的禮物，送給你之前，請先確認一下消耗欄內是否有一個以上的空間吧！");
    } else if (status == 3) {
        if (cm.haveItem(4031092, 10)) {
            if (cm.getQuestStatus(3239) == 1) {
                id = Math.floor(Math.random() * 任務道具.length);
                cm.gainItem(任務道具 [id], 1);
                cm.removeAll(4031092);
                cm.gainExp(2700);
                cm.forceCompleteQuest(3239);
            }
            cm.sendNext("怎麼樣？#b#t" + 任務道具 [id] + "##k收好了嗎？希望我的禮物對你會有所幫助。");
        } else {
            cm.sendNext("我的#b#t4031092##kx10個呢?");
            cm.dispose();
        }
    } else if (status == 4) {
        cm.sendNext("多虧了你，我們的玩具工廠已經恢復運轉了，真是幸運啊，現在我們已經特別注意，不會再弄丟零件了，你放心吧。好的，今天也要努力幹活囉！");
    } else if (status == 5) {
        cm.warp(922000009);
        cm.dispose();
    }
}