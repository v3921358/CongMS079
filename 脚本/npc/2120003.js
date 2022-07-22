/* 鬼娃恰吉PQ by:Kodan*/
var Ghostbaby = 1; //一天五場
var status = -1;
var randTalk = Math.floor(Math.random() * 10) + 1;

function action(mode, type, selection) {

    var Editing = true; //false 開始
    if (Editing) {
        cm.sendOk("維修中");
        cm.dispose();
        return;
    }

    if (status == 1 && mode == 0 || status == 5 && mode == 1 || status == 10 && mode == 1 || status == 13 && mode == 1 || status == 15 && mode == 0 　 || status == 0 && mode == -1) {
        cm.dispose();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
        if (cm.getBossLog('Ghostbaby') >= 1) {
            cm.sendOk("每天只能打1次鬼娃恰吉！");
            cm.dispose();
        }
        cm.sendNext("蒐集到解夢鑰匙了嗎？讓我來幫你解夢吧！看看你在萬聖節會出現什麼樣的夢，解夢鑰匙就由我來拿走吧！");
    } else if (status == 1) {
        if (randTalk >= 5) {
            cm.sendNext("夢裡面的南瓜正在睡覺呢~如果你帶一些南瓜碎片以及300萬楓幣，他有可能會喚醒也說不定？");
            cm.gainItem(4001337, -1);
            cm.gainMeso(-3000000);
            cm.dispose();
        } else {
            cm.sendNext("哦不~可怕的噩夢就要開始了，你夢見了鬼娃恰吉正在開始破壞萬聖節派對，並搶走孩子們的糖果！好好教訓他們，並把他們趕出去吧！");
        }
    } else if (status == 2) {
        if (cm.haveItem(4001337)) {
            var em = cm.getEventManager("Ghostbaby");
            if (em == null) {
                cm.sendOk("當前副本有問題，請聯絡管理員....");
                cm.dispose();
            } else {
                var prop = em.getProperty("state");
                if (prop.equals("0") || prop == null) {
                    em.startInstance(cm.getPlayer(), cm.getMap());
                    cm.setBossLog("Ghostbaby");
                    cm.gainItem(4001337, -1);
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("裡面已經有人在挑戰鬼娃恰吉了...");
                    cm.dispose();
                }
            }
        } else {
            cm.sendOk("貌似沒有鑰匙呢不能做夢了！");
            cm.dispose();
        }
    }
}
