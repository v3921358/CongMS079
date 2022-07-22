var status = -1;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.dispose();
        }
        status--;
    }
    var em = cm.getEventManager("Amoria");
    if (em == null) {
        cm.dispose();
        return;
    }
    switch (cm.getMapId()) {
        case 670010500:
            if (!cm.isLeader()) {
                cm.sendOk("請找隊長來和我談話。");
                cm.dispose();
                return;
            }
            if (cm.haveItem(4031597, 50)) {
                cm.mapMessage(6, "跑，跑向前跑，絕對不要回頭！");
                cm.warpParty(670010600);
                cm.gainItem(4031597, -50);

            } else {
                cm.sendOk("我希望你和你的隊員們弄50個#t4031597#給我。");
            }
            cm.dispose();
            break;
        case 670010600:
            if (!cm.isLeader()) {
                cm.sendOk("請找隊長來和我談話。");
                cm.dispose();
                return;
            }
            cm.warpParty(670010700);
            cm.dispose();
            break;
        case 670010700:
            if (!cm.isLeader()) {
                cm.sendOk("請找隊長來和我談話。");
                cm.dispose();
                return;
            }
            if (em.getProperty("apq4").equals("0") || em.getProperty("apq4").equals("1")) {
                cm.warpParty(670010700, 18);
                cm.spawnMob(9400536, 1, 674, 511);
                em.setProperty("apq4", "2");
            } else {
                if (cm.haveItem(4031594, 1)) {
                    cm.gainItem(4031594, -1);
                    cm.warpParty(670010800, -1);
                } else {
                    cm.sendOk("我需要#t4031594#....");
                }
            }
            cm.dispose();
            break;
    }
}