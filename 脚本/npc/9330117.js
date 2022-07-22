status = -1;
var itemList = Array(
        Array(2370000, 400, 1, 1), //兵法書(孫子)
        Array(2370000, 1, 1, 1), //兵法書(孫子)
        Array(2370001, 400, 1, 1), //兵法書(吳子) 
        Array(2370002, 400, 1, 1), //兵法書(尉繚子)
        Array(2370003, 400, 1, 1), //兵法書(六韜)
        Array(2370004, 400, 1, 1), //兵法書(三略)
        Array(2370005, 800, 1, 0), //兵法書(司馬法)
        Array(2370006, 800, 1, 0), //兵法書(李衛公問對)
        Array(2370007, 800, 1, 0), //兵法書(孫兵兵法)
        Array(2370008, 800, 1, 0), //兵法書(兵法三十六計)
        Array(2370009, 800, 1, 0), //兵法書(兵法二十四章)
        Array(2370010, 800, 1, 0), //兵法書(戰國策)
        Array(2370011, 800, 1, 0), //兵法書(百戰奇略)
        Array(2370012, 800, 1, 0)//兵法書(心書)
        );

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.sendOk("\t\t #i3994014##i3994018##i3994070##i3994061##i3994005##i3991038##i3991004#\r\n" +
                    "\t  #i3994060##i3994079##i3994060##i3994060##i3994070##i3994063##i3994071##i3994077#\r\n" +
                    "        #r此為泡泡谷轉蛋機，您不想抽獎嗎？");
            cm.dispose();
        }
        status--;
    }

    if (status == 0) {
        if (cm.getPlayer().getLevel() < 30) {
            cm.sendOk("\t\t #i3994014##i3994018##i3994070##i3994061##i3994005##i3991038##i3991004#\r\n" +
                    "\t  #i3994060##i3994079##i3994060##i3994060##i3994070##i3994063##i3994071##i3994077#\r\n" +
                    "        #r此為泡泡谷轉蛋機，需要30等才能抽獎。");
            cm.dispose();
        } else if (cm.haveItem(5220000)) {
            cm.sendYesNo("\t\t #i3994014##i3994018##i3994070##i3994061##i3994005##i3991038##i3991004#\r\n" +
                    "\t  #i3994060##i3994079##i3994060##i3994060##i3994070##i3994063##i3994071##i3994077#\r\n" +
                    "           #r此為泡泡谷轉蛋機，你要抽獎嗎?#k\r\n");
        } else {
            cm.sendOk("\t\t #i3994014##i3994018##i3994070##i3994061##i3994005##i3991038##i3991004#\r\n" +
                    "\t  #i3994060##i3994079##i3994060##i3994060##i3994070##i3994063##i3994071##i3994077#\r\n" +
                    "       #r此為泡泡谷轉蛋機，請到商城購買轉蛋卷。#k\r\n");
            cm.safeDispose();
        }
    } else if (status == 1) {
        var chance = Math.floor(Math.random() * 1000);
        if (chance > 800) {
            chance = 800;
        }
        if (chance < 1) {
            chance = 800;
        }
        var finalitem = Array();
        for (var i = 0; i < itemList.length; i++) {
            if (itemList[i][1] >= chance) {
                finalitem.push(itemList[i]);
            }
        }
        if (finalitem.length != 0) {
            var random = new java.util.Random();
            var finalchance = random.nextInt(finalitem.length);
            var itemId = finalitem[finalchance][0];
            var quantity = finalitem[finalchance][2];
            var notice = finalitem[finalchance][3];
            if (cm.haveItem(5220000) && cm.canHold()) {
                if (notice == 1) {
                    cm.gainGachaponItem(itemId, quantity, "兵法書轉蛋機");
                } else {
                    cm.gainItem(itemId, quantity);
                }
                cm.gainItem(5220000, -1);
                cm.getItemLog("兵法書轉蛋機", " 抽到 " + itemId + "(" + cm.getItemName(itemId) + ") " + quantity + "個。");
                cm.sendOk("\t\t #i3994014##i3994018##i3994070##i3994061##i3994005##i3991038##i3991004#\r\n" +
                        "\t  #i3994060##i3994079##i3994060##i3994060##i3994070##i3994063##i3994071##i3994077#\r\n" +
                        " #r此為泡泡谷轉蛋機，恭喜你得到了#k #b#t" + itemId + "##k #r" + quantity + "個。#k");
            } else {
                cm.sendOk("請確認背包是否已經滿了以及是否有轉蛋卷唷。");
            }
            cm.safeDispose();
        } else {
            cm.sendOk("出現未知問題，請稍後再試。");
            cm.safeDispose();
        }
    } else {
        cm.dispose();
    }
}