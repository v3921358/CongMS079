/* Author: aaroncsn(MapleSea Like)(Incomplete)
 NPC Name: 		Pam
 Map(s): 		Leafre: Pam's House(240000006)
 Description: 		Unknown
 */


function start() {
    var Editing = false //false 開始
    if (Editing) {
        cm.sendOk("維修中");
        cm.dispose();
        return;
    }
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1)
        status++;
    else
        status--;
    if (status == 0) {
        cm.sendSimple("你想獲得濃縮濃縮離乳食嗎？\r\n" +
                "#L0##d第一階段濃縮離乳食\r\n" +
                "#L1##d第二階段濃縮離乳食\r\n" +
                "#L2##d第三階段濃縮離乳食\r\n");

    } else if (status == 1) {
        if (selection == 0) {
            if (cm.haveItem(4000236, 30) && cm.haveItem(4000237, 30) && cm.haveItem(4000238, 30) && cm.getMeso() > 2000000) {
                cm.gainItem(4000236, -30);
                cm.gainItem(4000237, -30);
                cm.gainItem(4000238, -30);
                cm.gainMeso(-2000000);
                cm.gainItem(4032196, 1);
                cm.sendOk("兌換成功。");
                cm.dispose();
                return;
            } else {
                cm.sendOk("#d請確認背包中是否有：\r\n橡木甲蟲角x30\r\n金屬甲蟲角x30\r\n哈維羽毛x30\r\n200萬楓幣。");
                cm.dispose();
                return;
            }
        } else if (selection == 1) {
            if (cm.haveItem(4000239, 30) && cm.haveItem(4000241, 30) && cm.haveItem(4000242, 30) && cm.getMeso() > 3000000) {
                cm.gainItem(4000239, -30);
                cm.gainItem(4000241, -30);
                cm.gainItem(4000242, -30);
                cm.gainMeso(-3000000);
                cm.gainItem(4032197, 1);
                cm.sendOk("兌換成功。");
                cm.dispose();
                return;
            } else {
                cm.sendOk("#d請確認背包中是否有：\r\n血腥哈維的王冠x30\r\n邪惡綿羊嚼過的草x30\r\n惡魔綿羊尾巴x30\r\n300萬楓幣。");
                cm.dispose();
                return;
            }
        } else if (selection == 2) {
            if (cm.haveItem(4000262, 30) && cm.haveItem(4000263, 30) && cm.haveItem(4000265, 30) && cm.getMeso() > 4000000) {
                cm.gainItem(4000262, -30);
                cm.gainItem(4000263, -30);
                cm.gainItem(4000265, -30);
                cm.gainMeso(-4000000);
                cm.gainItem(4032198, 1);
                cm.sendOk("兌換成功。");
                cm.dispose();
                return;
            } else {
                cm.sendOk("#d請確認背包中是否有：\r\n裂開的背殼x30\r\n有毒的背殼x30\r\n奇異卵x30\r\n400萬楓幣。");
                cm.dispose();
                return;
            }
        }
    }
}
