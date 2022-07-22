/* global cm */

var status = -1;
var select = -1;

function start() {
    cm.sendSimple(
            "#r我是咖哩豬的細漢，有什麼需要我替您服務嗎?#k\r\n" +
            "#b#L1#【中秋活動-200個糯米粉兌換】#l#k\r\n" +
            "#b#L2#【中秋活動-脆皮雞烤肉趣】#l#k\r\n"
            );
}

function action(mode, type, selection) {
    if (select === -1) {
        select = selection;
    }
    var level = cm.getPlayer().getLevel();
    switch (select) {
        case 1:
        {
            if (cm.getAcLogS("中秋活動") < 1) {
                if (level >= 30) {
                    if (cm.haveItem(4031184, 200)) {
                        if (cm.canHoldByType(1, 7)) {
                            cm.setAcLog("中秋活動");
                            cm.gainItem(4031184, -200);
                            cm.gainItem(1102369, 1);
                            cm.gainItem(1002552, 1);
                            cm.gainItem(1052077, 1);
                            cm.gainItem(1072274, 1);
                            cm.gainItem(1082169, 1);
							cm.gainItem(1702576, 1);
                            cm.getPlayer().modifyCSPoints(2, 888, true);
                            cm.sendOk("領取成功。");
                            cm.dispose();
                            return;
                        } else {
                            cm.sendOk("您的背包已滿,請保留9格以上。");
                            cm.dispose();
                            return;
                        }
                    } else {
                        cm.sendOk("你的道具不足。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("你的等級還不夠30等。");
                    cm.dispose();
                    return;
                }

            } else {
                cm.sendOk("你已經領取過了。");
                cm.dispose();
                return;
            }
            cm.dispose();
            break;


        }
        case 2:
        {
            if (level >= 30) {
                if (!cm.haveItem(4000188, 100) || !cm.haveItem(4000187, 100) || !cm.haveItem(4000006, 100) ||
                        !cm.haveItem(4000247, 100) || !cm.haveItem(4000253, 100) || !cm.haveItem(4000252, 100)
                        || !cm.haveItem(4000166, 100) || !cm.haveItem(4000189, 200)) {
                    cm.sendOk("你的道具不足，需要鴨蛋100個，雞爪100個，章魚腳100個，青蛙後腿100個，白色雞蛋100個，新鮮肌肉100個，蝦肉100個，羊毛200個。");
                    cm.dispose();
                    return;
                } else {
                    if (cm.canHoldByType(1, 2)) {
                        cm.gainItem(4000188, -100);
                        cm.gainItem(4000187, -100);
                        cm.gainItem(4000006, -100);
                        cm.gainItem(4000247, -100);
                        cm.gainItem(4000253, -100);
                        cm.gainItem(4000252, -100);
                        cm.gainItem(4000166, -100);
                        cm.gainItem(4000189, -200);
                        cm.gainItem(1142813, 1);
                        cm.sendOk("領取成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("您的背包已滿。");
                        cm.dispose();
                        return;
                    }
                }
            } else {
                cm.sendOk("你的等級還不夠30等。");
                cm.dispose();
                return;
            }
            cm.dispose();
            break;
        }
        default :
        {
            cm.sendOk("此功能未完成");
            cm.dispose();
        }
    }
}

