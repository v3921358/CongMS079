var equiplist;
var equip;
var equipscrolllist;
var equipscroll;
var str = "";
var strs = "";
var modea = 0;
var yesno = false;

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
    if (mode == 1) {
        status++;
    } else if (mode == 0) {
        status--;
    } else {
        cm.dispose();
        return;
    }
    if (status == 0) {
        cm.sendSimple("你好，我是裝備強化概率提升師。\r\n" +
                "#L0#提升裝備強化卷軸概率10%#l\r\n");

    } else if (status == 1) {
        if (selection == 0) {
            equiplist = cm.getEquippedlist();
            if (equiplist != null) {
                for (var i = 0; i < equiplist.size(); i++) {
                    str += "#L" + i + "##i" + equiplist.get(i).getItemId() + "##t" + equiplist.get(i).getItemId() + "##k\r\n";
                }
            }
            cm.sendSimple("選擇你想要強化的裝備。\r\n" + str);

        }

    } else if (status == 2) {
        equip = selection;
        //cm.setMonsterRiding(equiplist.get(equipscroll).getItemId());
        equipscrolllist = cm.getEquipScrolllist();
        if (equipscrolllist != null) {
            for (var i = 0; i < equipscrolllist.size(); i++) {
                strs += "#L" + i + "##i" + equipscrolllist.get(i).getItemId() + "##t" + equipscrolllist.get(i).getItemId() + "##k\r\n";
            }
        }
        cm.sendSimple("選擇你想要強化的強化卷軸。\r\n" + strs);
    } else if (status == 3) {
        equipscroll = selection;
        yesno = cm.UseUpgradeScroll(equiplist.get(equip), equipscrolllist.get(equipscroll), 10);
        if (yesno) {
            cm.sendOk("恭喜您強化成功。");
        } else {
            cm.sendOk("很抱歉，強化失敗。");
        }
        cm.dispose();
        return;
    }
}
