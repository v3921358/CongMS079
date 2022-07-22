var status = -1;
var items = Array(1102041, 1082149, 5220000);
var itemsp = Array(2000, 2000, 500);
var itemsu = Array(0, 2, 0); // extra slots, not set.
var itemsq = Array(1, 1, 150);
var itemse = Array(7, -1, -1);
var chairs = Array(3010025, 3010045, 3010054, 3012002, 3010014, 3010068, 3010022, 3010023, 3010041);
var chairsp = Array(1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000);
var fame = 100;
var famep = 500;
var acashp = 10000;
var sel = -1;

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode != 1) {
        cm.dispose();
        return;
    }
    status++;
    if (status == 0) {
        cm.sendSimple("嗨， #r#h ##k! 我的名字是 #r#p9000039##k. 如果你有 #b贊助點數#k. 可以找我換東西哦。 好了你需要什麼？？\r\n#b#L0#什麼是贊助點數？#l\r\n#b#L1#換一些贊助商品#l \r\n#b#L2#換一些椅子#l \r\n#b#L3#換一些名聲#l\r\n#L4#換一些點數#l\r\n#L6#我目前有多少贊助點數?\r\n#L5#紅色福袋換1千贊助點#l \r\n#L7#1千贊助點換紅色福袋#l#k");
    } else if (status == 1) {
        sel = selection;
        if (selection == 0) {
            cm.sendNext("贊助點數可以買一些物品，至於怎麼樣有贊助點數...這要問\r\nGM呢=)");
            status = -1;
        } else if (selection == 1) {
            var selStr = "你想要什麼？？#b\r\n\r\n";
            for (var i = 0; i < items.length; i++) {
                selStr += "#L" + i + "##v" + items[i] + "##t" + items[i] + "#" + (itemsu[i] > 0 ? "(附贈 " + itemsu[i] + " 捲)" : "") + " x " + itemsq[i] + " 為 #e" + itemsp[i] + "#n 贊助點#n" + (itemse[i] > 0 ? (" ...期限 #r#e" + itemse[i] + "#n#b天") : "") + "#l\r\n";
            }
            cm.sendSimple(selStr + "#k");
        } else if (selection == 2) {
            var selStr = "你想要什麼？？#b\r\n\r\n";
            for (var i = 0; i < chairs.length; i++) {
                selStr += "#L" + i + "##v" + chairs[i] + "##t" + chairs[i] + "# 為 #e" + chairsp[i] + "#n 贊助點#n#l\r\n";
            }
            cm.sendSimple(selStr + "#k");
        } else if (selection == 3) {
            cm.sendYesNo("你需要名聲?? 我可以跟你交換 #b#e" + fame + " 名聲為 " + famep + " 贊助點.#n#k. 請問你接受嗎??");
        } else if (selection == 4) {
            cm.sendYesNo("你需要GASH對吧?? 我可以跟你交換 #r#e1000 GASH 為 " + acashp + " 贊助點.#n#k. 請問你接受嗎??");
        } else if (selection == 5) {
            if (!cm.getPlayer().haveItem(3993003)) {
                cm.sendOk("你需要有一個#b#t3993003##k。");
                cm.dispose();
            } else {
                cm.sendGetNumber("你想要用多少#r#t3993003##k換贊助點數？？ \r\n比值: (1 #t3993003# = 1000 贊助點) \r\n(目前你有: " + cm.getPlayer().itemQuantity(3993003) + "個 #r#t3993003##k) \r\n(目前你有: " + cm.getPlayer().getPoints() + "贊助點)", cm.getPlayer().itemQuantity(3993003), 1, cm.getPlayer().itemQuantity(3993003));
            }
        } else if (selection == 7) {
            if (cm.getPlayer().getPoints() < 1000) {
                cm.sendOk("你需要有#b一千贊助點#k。");
                cm.dispose();
            } else {
                cm.sendGetNumber("你想要用多少#r贊助點數#k換#t3993003#？？ \r\n比值: (1 #t3993003# = 1000 贊助點) \r\n(目前你有: " + cm.getPlayer().getPoints() + "贊助點) (目前你有:  " + cm.getPlayer().itemQuantity(3993003) + "個 #r#t3993003##k)", cm.getPlayer().getPoints() / 1000, 1, cm.getPlayer().getPoints() / 1000);
            }
        } else if (selection == 6) {
            cm.sendOk("目前你有 #e" + cm.getPlayer().getPoints() + "#n 贊助點數。");
            cm.dispose();
        }
    } else if (status == 2) {
        if (sel == 1) {
            var it = items[selection];
            var ip = itemsp[selection];
            var iu = itemsu[selection];
            var iq = itemsq[selection];
            var ie = itemse[selection];
            if (cm.getPlayer().getPoints() < ip) {
                cm.sendOk("很抱歉，你沒有足夠的贊助點數 你目前有 " + cm.getPlayer().getPoints() + " 我需要 " + ip + ".");
            } else if (!cm.canHold(it, iq)) {
                cm.sendOk("請空出一些欄位。");
            } else {
                if (iu > 0) {
                    cm.gainItem(it, iq, false, ie, iu);
                } else {
                    cm.gainItemPeriod(it, iq, ie);
                }
                cm.getPlayer().setPoints(cm.getPlayer().getPoints() - ip);
                cm.sendOk("感謝！！");
            }
        } else if (sel == 2) {
            var it = chairs[selection];
            var cp = chairsp[selection];
            if (cm.getPlayer().getPoints() < cp) {
                cm.sendOk("很抱歉，你沒有足夠的贊助點數 你目前有 " + cm.getPlayer().getPoints() + " 我需要 " + cp + ".");
            } else if (!cm.canHold(it)) {
                cm.sendOk("請空出一些欄位。");
            } else {
                cm.gainItem(it, 1);
                cm.getPlayer().setPoints(cm.getPlayer().getPoints() - cp);
                cm.sendOk("感謝！！");
            }
        } else if (sel == 3) {
            if (cm.getPlayer().getPoints() < famep) {
                cm.sendOk("很抱歉，你沒有足夠的贊助點數 你目前有 " + cm.getPlayer().getPoints() + " 我需要 " + famep + ".");
            } else if (cm.getPlayer().getFame() > (30000 - fame)) {
                cm.sendOk("你已經有太多的名聲了。");
            } else {
                cm.getPlayer().setPoints(cm.getPlayer().getPoints() - famep);
                cm.getPlayer().addFame(fame);
                cm.getPlayer().updateSingleStat(client.MapleStat.FAME, cm.getPlayer().getFame());
                cm.sendOk("感謝！！");
            }
        } else if (sel == 4) {
            if (cm.getPlayer().getPoints() < acashp) {
                cm.sendOk("很抱歉，你沒有足夠的贊助點數 你目前有 " + cm.getPlayer().getPoints() + " 我需要 " + acashp + ".");
            } else if (cm.getPlayer().getCSPoints(1) > (java.lang.Integer.MAX_VALUE - 100000)) {
                cm.sendOk("你已經有太多GASH了。");
            } else {
                cm.getPlayer().setPoints(cm.getPlayer().getPoints() - acashp);
                cm.getPlayer().modifyCSPoints(1, 100, true);
                cm.sendOk("感謝！！");
            }
        } else if (sel == 5) {
            if (selection >= 1 && selection <= cm.getPlayer().itemQuantity(3993003)) {
                if (cm.getPlayer().getPoints() > (2147483647 - (selection * 1000))) {
                    cm.sendOk("你有太多贊助點了。");
                } else {
                    cm.gainItem(3993003, -selection);
                    cm.getPlayer().setPoints(cm.getPlayer().getPoints() + (selection * 1000));
                    cm.sendOk("你失去了 " + selection + " #r個#t3993003##k 和 獲得了 " + (selection * 1000) + " 贊助點. \r\n目前贊助點: " + cm.getPlayer().getPoints());
                }
            }
        } else if (sel == 7) {
            if (selection >= 1) {
                if (selection > (cm.getPlayer().getPoints() / 1000)) {
                    cm.sendOk("你最多只能得到 " + (cm.getPlayer().getPoints() / 1000) + ". 1 道具 = 1000 贊助點.");
                } else if (!cm.canHold(3993003, selection)) {
                    cm.sendOk("請空出一些裝飾欄。");
                } else {
                    cm.gainItem(3993003, selection);
                    cm.getPlayer().setPoints(cm.getPlayer().getPoints() - (selection * 1000));
                    cm.sendOk("你獲得了 " + selection + " #r個#t3993003##k 和 失去了 " + (selection * 1000) + " 贊助點. \r\n目前贊助點: " + cm.getPlayer().getPoints());
                }
            }
        }
        cm.dispose();
    }
}