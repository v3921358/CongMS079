﻿/*
	Noran
 */

var status = -1;

function start() {
    cm.sendSimple("有什麼我可以替你幫忙的嗎？ \r #b#L0# 請幫我解除石鎖#l \r #L1# 幫助我鍛練道具#l");
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else if (status == 1) {
        status--;
        selection = 0;
    } else {
        cm.dispose();
        return;
    }
    switch (status) {
    case 0:
        if (selection == 0) {
            cm.sendNext("我是諾蘭，司令部技術負責人。現在司令部始終都在談論著你的事。要是能協助我破壞機械軍團，我也會幫助你。使用機械軍團佈雷茲的技術，應該可以製造出強力的道具吧。")
        } else {
            status = 9;
            cm.sendSimple("決定那一個？\r\n#b#L0##t2070019##l\r\n#L1##t2330007##l");
        }
        break;
    case 1:
        cm.sendNextPrev("聽說佈雷茲已成功開發出收集宇宙能量的技術。若是屬實，一定會取得龐大的能量。你說你可以從佈雷茲那邊取得蓄積宇宙能量的特殊岩石。想要使用岩石的能量必須先解除石鎖（封印），要是能拿過來我就幫你解除封印。");
        break;
    case 2:
        cm.sendSimple("將封印的岩石交給我。\r\n#b#L0#交出#t4020010#與費用1,000#t4032181#。#l\r\n#L1#交出#t4020012#與費用1,000#t4032181#。#l\r\n#L2#交出#t4020011#與費用1,000#t4032181#。#l");
        break;
    case 3:
        if (selection == 0) {
            if (cm.haveItem(4020010, 1) && cm.haveItem(4032181, 1000)) {
                cm.gainItem(4032169, 1);
                cm.gainItem(4020010, -1);
                cm.gainItem(4032181, -1000);
            } else {
                cm.sendNext("你好像沒有道具喔。請交給我被封印的岩石和費用1,000#t4032181#。另外，再請順便確認一下道具欄上是否還有足夠空位。");
            }
        } else if (selection == 1) {
            if (cm.haveItem(4020012, 1) && cm.haveItem(4032181, 1000)) {
                cm.gainItem(4032171, 1);
                cm.gainItem(4020012, -1);
                cm.gainItem(4032181, -1000);
            } else {
                cm.sendNext("你好像沒有道具喔。請交給我被封印的岩石個和費用1,000#t4032181#。另外，再請順便確認一下道具欄上是否還有足夠空位。");
            }
        } else {
            if (cm.haveItem(4020011, 1) && cm.haveItem(4032181, 1000)) {
                cm.gainItem(4032170, 1);
                cm.gainItem(4020011, -1);
                cm.gainItem(4032181, -1000);
            } else {
                cm.sendNext("你好像沒有道具喔。請交給我被封印的岩石個和費用1,000#t4032181#。另外，再請順便確認一下道具欄上是否還有足夠空位。");
            }
        }
        cm.dispose();
        break;
    case 10:
        if (selection == 0) {
            if (cm.haveItem(4032168, 1) && cm.haveItem(4032181, 2500) && cm.haveItem(4032171, 1) && cm.haveItem(2070006, 1) && (cm.getMeso() >= 500000000)) {
                cm.gainItem(4032171, -1);
                cm.gainItem(4032168, -1);
                cm.gainItem(2070006, -1);
                cm.gainItem(4032181, -2500);
                cm.gainMeso(-500000000);
                cm.gainItem(2070019, 1);
            } else {
                cm.sendNext("咦？你好像沒有必要的素材嘛？\r\n製造#t2070019#需要有#t4032168#、#t4032171#、#t02070006#1個、#t4032181#2,500片以及500,000,000金幣。");
            }
        } else {
            if (cm.haveItem(4032168, 1) && cm.haveItem(4032181, 2500) && cm.haveItem(4032170, 1) && cm.haveItem(2330003, 1) && (cm.getMeso() >= 150000000)) {
                cm.gainItem(4032170, -1);
                cm.gainItem(4032168, -1);
                cm.gainItem(2330003, -1);
                cm.gainItem(4032181, -2500);
                cm.gainMeso(-150000000);
                cm.gainItem(2330007, 1);
            } else {
                cm.sendNext("咦？你好像沒有必要的素材嘛？\r\n製造#t2330007#需要有#t4032168#、#t4032170#、#t02330003#1個、#t4032181#2,500片以及150,000,000金幣。");
            }
        }
        cm.dispose();
        break;
    }
}