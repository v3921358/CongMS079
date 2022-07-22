/* Kedrick
 Fishking King NPC
 */

var status = -1;
var sel;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.dispose();
            return;
        }
        status--;
    }

    if (status == 0) {
        cm.sendSimple("我能為您做什麼嗎？？\r\n" +
                "#L1#使用10條舞癡魚換取 #i5072000#高效能喇叭#l\r\n"+
                "#L2#使用20條舞癡魚換取 #i5230000#貓頭鷹*10#l\r\n"+
                "#L3#使用40條舞癡魚換取 #i5561000#任意門高級券#l\r\n"+
                "#L4#使用40條舞癡魚換取 #i5130000#護身符*10#l\r\n"+
                "#L5#使用40條舞癡魚換取 #i5210001#經驗加倍凌晨券1日#l\r\n" +
                "#L6#使用40條舞癡魚換取 #i5210002#經驗加倍上午券1日#l\r\n" +
                "#L7#使用40條舞癡魚換取 #i5210003#經驗加倍下午券1日#l\r\n" +
                "#L8#使用40條舞癡魚換取 #i5210004#經驗加倍晚上券1日#l\r\n" +
                "#L9#使用40條舞癡魚換取 #i5360015#掉寶加倍全日券1日#l\r\n" +
                "#L10#使用60條舞癡魚換取 #i5150042#通用美髮券#l\r\n" +
				"#L11#使用120條舞癡魚換取 #i5210000#經驗加倍全日券1日#l\r\n" +
				"#L12#使用290條舞癡魚換取 #i2180000#能力點數重置*20#l\r\n" +
				"#L13#使用320條舞癡魚換取 #i5520000#神奇剪刀#l\r\n"+
				"#L14#使用500條舞癡魚換取 #i3010142#魚缸椅#l\r\n"
);
    } else if (status == 1) {
        sel = selection;
        switch (sel) {
            case 1:
            {
                if (cm.haveItem(4001188, 10)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -10);
                        cm.gainItem(5072000, 1);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有10條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
            case 2:
            {
                if (cm.haveItem(4001188, 20)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -20);
                        cm.gainItem(5230000, 10);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有20條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
            case 3:
            {
                if (cm.haveItem(4001188, 40)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -40);
                        cm.gainItem(5561000, 1);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有40條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
            case 4:
            {
                if (cm.haveItem(4001188, 40)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -40);
                        cm.gainItem(5130000, 10);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有40條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
            case 5:
            {
                if (cm.haveItem(4001188, 40)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -40);
                        cm.gainItemPeriod(5210001, 1, 1);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有40條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
            case 6:
            {
                if (cm.haveItem(4001188, 40)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -40);
                        cm.gainItemPeriod(5210002, 1, 1);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有40條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
            case 7:
            {
                if (cm.haveItem(4001188, 40)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -40);
                        cm.gainItemPeriod(5210003, 1, 1);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有40條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
            case 8:
            {
                if (cm.haveItem(4001188, 40)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -40);
                        cm.gainItemPeriod(5210004, 1, 1);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有40條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
            case 9:
            {
                if (cm.haveItem(4001188, 40)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -40);
                        cm.gainItemPeriod(5360015, 1, 1);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有40條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
            case 10:
            {
                if (cm.haveItem(4001188, 60)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -60);
                        cm.gainItem(5150042, 1);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有60條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
			 case 11:
            {
                if (cm.haveItem(4001188, 120)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -120);
                        cm.gainItem(5210000, 1);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有120條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
			 case 12:
            {
                if (cm.haveItem(4001188, 290)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -290);
                        cm.gainItem(5050000, 20);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有290條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
            case 13:
            {
                if (cm.haveItem(4001188, 320)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -320);
                        cm.gainItem(5520000, 1);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有320條舞癡魚哦");
                    cm.dispose();
                    return;
                }
                cm.dispose();
                break;
            }
			case 14:
            {
                if (cm.haveItem(4001188, 500)) {
                    if (cm.canHold()) {
                        cm.gainItem(4001188, -500);
                        cm.gainItem(3010142, 1);
                        cm.sendOk("兌換成功。");
                        cm.dispose();
                        return;
                    } else {
                        cm.sendOk("你的背包沒有多餘的空格。");
                        cm.dispose();
                        return;
                    }
                } else {
                    cm.sendOk("#b檢查一下背包有沒有500條舞癡魚哦");
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
}