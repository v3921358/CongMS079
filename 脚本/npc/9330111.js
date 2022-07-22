/* global cm */

var status = -1;
var select = -1;

function start() {
    cm.sendSimple(
            "\t\t #i3994014##i3994018##i3994070##i3994061##i3994005##i3991038##i3991004#\r\n" +
            "\t  #i3994060##i3994079##i3994060##i3994060##i3994070##i3994063##i3994071##i3994077#\r\n" +
            "#r   此為乖寶寶印章回收商人，有什麼需要我替您服務嗎?#k\r\n" +
            "#e#L1#  1. 戒子力量卷軸60% (需要1個印章)#l\r\n" +
            "#L2#  2. 戒子智力卷軸60% (需要1個印章)#l\r\n" +
            "#L3#  3. 戒子敏捷卷軸60% (需要1個印章)#l\r\n" +
            "#L4#  4. 戒子幸運卷軸60% (需要1個印章)#l\r\n" +
			"#r #k\r\n" +
			"#d    ------------------------------------#k\r\n" +
            "#e#L5#  5. 熱狗套服(力量) (需要5個印章)#l\r\n" +
            "#L6#  6. 熱狗套服(敏捷) (需要5個印章)#l\r\n" +
            "#L7#  7. 熱狗套服(智力) (需要8個印章)#l\r\n" +
            "#L8#  8. 熱狗套服(幸運) (需要5個印章)#l\r\n" +
			"#r #k\r\n" +
			"#d    ------------------------------------#k\r\n" +
            "#e#L9#  9. 魅惑之劍 (單手劍) (需要5個印章)#l\r\n" +
            "#L10#  10.魅惑之劍 (短劍)   (需要5個印章)#l\r\n" +
            "#L11#  11.魅惑之劍 (長杖)   (需要5個印章)#l\r\n" +
            "#L12#  12.魅惑之劍 (雙手劍) (需要5個印章)#l\r\n" +
            "#L13#  13.魅惑之劍 (槍)     (需要5個印章)#l\r\n" +
            "#L14#  14.魅惑之劍 (矛)     (需要5個印章)#l\r\n" +
            "#L15#  15.魅惑之劍 (弓)     (需要5個印章)#l\r\n" +
            "#L16#  16.魅惑之劍 (弩)     (需要5個印章)#l\r\n" +
            "#L17#  17.魅惑之劍 (拳套)   (需要5個印章)#l\r\n"+
			"#r #k\r\n" +
			"#d    ------------------------------------#k\r\n" +
            "#e#L18#  18.單手劍攻擊詛咒卷軸30% (需要30個印章)#l\r\n"+
            "#L19#  19.短劍攻擊詛咒卷軸30%   (需要30個印章)#l\r\n"+
            "#L20#  20.矛攻擊詛咒卷軸30%     (需要30個印章)#l\r\n"+
            "#L21#  21.弓攻擊詛咒卷軸30%     (需要30個印章)#l\r\n"+
            "#L22#  22.拳套攻擊詛咒卷軸30%   (需要30個印章)#l\r\n"+
            "#L23#  23.指虎攻擊詛咒卷軸30%   (需要30個印章)#l\r\n"+
            "#L24#  24.火槍攻擊詛咒卷軸30%   (需要30個印章)#l\r\n"+
			"#L25#  25.槍攻擊詛咒卷軸30%     (需要30個印章)#l\r\n"+
			"#L26#  26.弩攻擊詛咒卷軸30%     (需要30個印章)#l\r\n"+
			"#L27#  27.短杖魔力卷軸30%     (需要30個印章)#l\r\n"+
			"#L28#  28.長杖魔力卷軸30%     (需要30個印章)#l\r\n"+
			"#r #k\r\n" +
			"#d    ------------------------------------#k\r\n" +
            "#e#L29#  29.套服敏捷詛咒卷軸30% (需要40個印章)#l\r\n"+
            "#L30#  30.套服力量詛咒卷軸30% (需要40個印章)#l\r\n"+
            "#L31#  31.套服智力詛咒卷軸30% (需要40個印章)#l\r\n"+
            "#L32#  32.套服幸運詛咒卷軸30% (需要40個印章)#l"
            );
}

function action(mode, type, selection) {
    if (select === -1) {
        select = selection;
    }
    switch (select) {
        case 1:
        {
            if (!cm.haveItem(4001137, 1)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -1);
                    cm.gainItem(2041101, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 2:
        {
            if (!cm.haveItem(4001137, 1)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -1);
                    cm.gainItem(2041104, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 3:
        {
            if (!cm.haveItem(4001137, 1)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -1);
                    cm.gainItem(2041107, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 4:
        {
            if (!cm.haveItem(4001137, 1)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -1);
                    cm.gainItem(2041110, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 5:
        {
            if (!cm.haveItem(4001137, 5)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -5);
                    cm.gainItem(1052187, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 6:
        {
            if (!cm.haveItem(4001137, 5)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -5);
                    cm.gainItem(1052188, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 7:
        {
            if (!cm.haveItem(4001137, 8)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -8);
                    cm.gainItem(1052189, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 8:
        {
            if (!cm.haveItem(4001137, 5)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -5);
                    cm.gainItem(1052190, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 9:
        {
            if (!cm.haveItem(4001137, 5)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -5);
                    cm.gainItem(1302130, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 10:
        {
            if (!cm.haveItem(4001137, 5)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -5);
                    cm.gainItem(1332098, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 11:
        {
            if (!cm.haveItem(4001137, 5)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -5);
                    cm.gainItem(1382079, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 12:
        {
            if (!cm.haveItem(4001137, 5)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -5);
                    cm.gainItem(1402071, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 13:
        {
            if (!cm.haveItem(4001137, 5)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -5);
                    cm.gainItem(1432060, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 14:
        {
            if (!cm.haveItem(4001137, 5)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -5);
                    cm.gainItem(1442085, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 15:
        {
            if (!cm.haveItem(4001137, 5)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -5);
                    cm.gainItem(1452082, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 16:
        {
            if (!cm.haveItem(4001137, 5)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -5);
                    cm.gainItem(1462074, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }

        case 17:
        {
            if (!cm.haveItem(4001137, 5)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -5);
                    cm.gainItem(1472099, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 18:
        {
            if (!cm.haveItem(4001137, 30)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -30);
                    cm.gainItem(2043005, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 19:
        {
            if (!cm.haveItem(4001137, 30)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -30);
                    cm.gainItem(2043305, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 20:
        {
            if (!cm.haveItem(4001137, 30)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -30);
                    cm.gainItem(2044405, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 21:
        {
            if (!cm.haveItem(4001137, 30)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -30);
                    cm.gainItem(2044505, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 22:
        {
            if (!cm.haveItem(4001137, 30)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -30);
                    cm.gainItem(2044705, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 23:
        {
            if (!cm.haveItem(4001137, 30)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -30);
                    cm.gainItem(2044804, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 24:
        {
            if (!cm.haveItem(4001137, 30)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -30);
                    cm.gainItem(2044904, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
		case 25:
        {
            if (!cm.haveItem(4001137, 30)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -30);
                    cm.gainItem(2044305, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
		case 26:
        {
            if (!cm.haveItem(4001137, 30)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -30);
                    cm.gainItem(2044605, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
		case 27:
        {
            if (!cm.haveItem(4001137, 30)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -30);
                    cm.gainItem(2043705, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
		case 28:
        {
            if (!cm.haveItem(4001137, 30)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -30);
                    cm.gainItem(2043805, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 29:
        {
            if (!cm.haveItem(4001137, 40)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -40);
                    cm.gainItem(2040509, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 30:
        {
            if (!cm.haveItem(4001137, 40)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -40);
                    cm.gainItem(2040533, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 31:
        {
            if (!cm.haveItem(4001137, 40)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -40);
                    cm.gainItem(2040519, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
            }
            cm.dispose();
            break;
        }
        case 32:
        {
            if (!cm.haveItem(4001137, 40)) {
                cm.sendOk("#b檢查一下背包有沒有乖寶寶印章哦");
                cm.dispose();
                return;
            } else {
                if (cm.canHold()) {
                    cm.gainItem(4001137, -40);
                    cm.gainItem(2040521, 1);
                    cm.sendOk("#b兌換成功。~");
                    cm.dispose();
                    return;
                } else {
                    cm.sendOk("你的背包沒有多餘的空格。");
                    cm.dispose();
                    return;
                }
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

