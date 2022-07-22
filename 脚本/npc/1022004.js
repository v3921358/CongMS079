/* Mr. Smith
 Victoria Road: Perion (102000000)
 
 Refining NPC: 
 * Warrior Gloves - 10-60 + upgrades
 * Processed Wood/Screws
 */

var status = 0;
var selectedType = -1;
var selectedItem = -1;
var item;
var mats;
var matQty;
var cost;
var qty;
var equip;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1)
        status++;
    else
        cm.dispose();
    if (status == 0 && mode == 1) {
        var selStr = "我是史密斯,很高興為你服務yo~#b"
        var options = new Array("製作手套", "升級手套", "木材與螺絲釘製作");
        for (var i = 0; i < options.length; i++) {
            selStr += "\r\n#L" + i + "# " + options[i] + "#l";
        }

        cm.sendSimple(selStr);
    } else if (status == 1 && mode == 1) {
        if (selection < 0) {
            cm.sendOk("腳本出錯，請聯繫管理員...");
            cm.dispose();
            return;
        }
        selectedType = selection;
        if (selectedType == 0) { //glove refine
            var selStr = "好der,你想要製作哪一種手套呢?#b";
            var items = new Array("腕甲#k - 需要等級 Lv. 10#b", "鋼製短手套#k - 需要等級 Lv. 15#b", "皮手套#k - 需要等級 Lv. 20#b", "白紋短手套#k - 需要等級 Lv. 25#b",
                    "青銅機器手套#k - 需要等級 Lv. 30#b", "鐵製輕便手套#k - 需要等級 Lv. 35#b", "鋼鐵長手套#k - 需要等級 Lv. 40#b", "鋼鐵合金手套#k - 需要等級 Lv. 50#b", "青銅戰鬥手套#k - 需要等級 Lv. 60#b");
            for (var i = 0; i < items.length; i++) {
                selStr += "\r\n#L" + i + "# " + items[i] + "#l";
            }
            cm.sendSimple(selStr);
            equip = true;
        } else if (selectedType == 1) { //glove upgrade
            var selStr = "升級手套? 可以哦~你想要升級哪一種手套呢?#b";
            var crystals = new Array("鋼製機器手套#k - 需要等級 Lv. 30#b", "紫礦機器手套#k - 需要等級 Lv. 30#b", "黃輕便手套#k - 需要等級 Lv. 35#b", "黑輕便手套#k - 需要等級 Lv. 35#b",
                    "朱礦長手套#k - 需要等級 Lv. 40#b", "黑色長手套#k - 需要等級 Lv. 40#b", "鋰礦合金手套#k - 需要等級 Lv. 50#b", "黃金合金手套#k - 需要等級 Lv. 50#b",
                    "藍戰鬥手套#k - 需要等級 Lv. 60#b", "黑戰鬥手套#k - 需要等級 Lv. 60#b");
            for (var i = 0; i < crystals.length; i++) {
                selStr += "\r\n#L" + i + "# " + crystals[i] + "#l";
            }
            cm.sendSimple(selStr);
            equip = true;
        } else if (selectedType == 2) { //material refine
            var selStr = "木材和螺絲釘,你需要什麼呢?#b";
            var materials = new Array("用10個樹枝製作1個木材", "用5個木柴製作1個木材", "製作螺絲釘(1次15個)");
            for (var i = 0; i < materials.length; i++) {
                selStr += "\r\n#L" + i + "# " + materials[i] + "#l";
            }
            cm.sendSimple(selStr);
            equip = false;
        }
        if (equip)
            status++;
    } else if (status == 2 && mode == 1) {
        if (selection < 0) {
            cm.sendOk("腳本出錯，請聯繫管理員...");
            cm.dispose();
            return;
        }
        selectedItem = selection;
        if (selectedType == 2) { //material refine
            var itemSet = new Array(4003001, 4003001, 4003000);
            var matSet = new Array(4000003, 4000018, new Array(4011000, 4011001));
            var matQtySet = new Array(10, 5, new Array(1, 1));
            var costSet = new Array(0, 0, 0)
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        }

        var prompt = "所以你需要我幫你做一些#t" + item + "#? 那你想要我做多少個呢?";

        cm.sendGetNumber(prompt, 1, 1, 100)
    } else if (status == 3 && mode == 1) {
        if (selection < 0) {
            cm.sendOk("腳本出錯，請聯繫管理員...");
            cm.dispose();
            return;
        }
        if (equip)
        {
            selectedItem = selection;
            qty = 1;
        } else
            qty = selection;

        if (selectedType == 0) { //glove refine
            var itemSet = new Array(1082003, 1082000, 1082004, 1082001, 1082007, 1082008, 1082023, 1082009, 1082059);
            var matSet = new Array(new Array(4000021, 4011001), 4011001, new Array(4000021, 4011000), 4011001, new Array(4011000, 4011001, 4003000), new Array(4000021, 4011001, 4003000), new Array(4000021, 4011001, 4003000),
                    new Array(4011001, 4021007, 4000030, 4003000), new Array(4011007, 4011000, 4011006, 4000030, 4003000));
            var matQtySet = new Array(new Array(15, 1), 2, new Array(40, 2), 2, new Array(3, 2, 15), new Array(30, 4, 15), new Array(50, 5, 40), new Array(3, 2, 30, 45), new Array(1, 8, 2, 50, 50));
            var costSet = new Array(1000, 2000, 5000, 10000, 20000, 30000, 40000, 50000, 70000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        } else if (selectedType == 1) { //glove upgrade
            var itemSet = new Array(1082005, 1082006, 1082035, 1082036, 1082024, 1082025, 1082010, 1082011, 1082060, 1082061);
            var matSet = new Array(new Array(1082007, 4011001), new Array(1082007, 4011005), new Array(1082008, 4021006), new Array(1082008, 4021008), new Array(1082023, 4011003), new Array(1082023, 4021008),
                    new Array(1082009, 4011002), new Array(1082009, 4011006), new Array(1082059, 4011002, 4021005), new Array(1082059, 4021007, 4021008));
            var matQtySet = new Array(new Array(1, 1), new Array(1, 2), new Array(1, 3), new Array(1, 1), new Array(1, 4), new Array(1, 2), new Array(1, 5), new Array(1, 4), new Array(1, 3, 5), new Array(1, 2, 2));
            var costSet = new Array(20000, 25000, 30000, 40000, 45000, 50000, 55000, 60000, 70000, 80000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        }

        var prompt = "你需要我幫你做";
        if (qty == 1)
            prompt += "#t" + item + "#?";
        else
            prompt += qty + "個#t" + item + "#?";

        prompt += " 好的我會幫你完成的,但請你確認你的背包是否有以下的材料與足夠的空間哦#b";

        if (mats instanceof Array) {
            for (var i = 0; i < mats.length; i++) {
                prompt += "\r\n#i" + mats[i] + "# " + matQty[i] * qty + " #t" + mats[i] + "#";
            }
        } else {
            prompt += "\r\n#i" + mats + "# " + matQty * qty + " #t" + mats + "#";
        }

        if (cost > 0) {
            prompt += "\r\n#i4031138# " + cost * qty + " meso";
        }
        cm.sendYesNo(prompt);
    } else if (status == 4 && mode == 1) {
        var complete = true;

        if (cm.getMeso() < cost * qty) {
            cm.sendOk("糟糕...你的錢好像不夠哦...")
            cm.dispose();
            return;
        } else {
            if (mats instanceof Array) {

                for (var i = 0; complete && i < mats.length; i++)
                {
                    if (!cm.haveItem(mats[i], matQty[i] * qty))
                    {
                        complete = false;
                    }
                }
            } else {
                if (!cm.haveItem(mats, matQty * qty))
                {
                    complete = false;
                }
            }
        }

        if (!complete)
            cm.sendOk("糟糕!? 你的材料好像不夠哦...這樣子我就不能幫你製作了,請重新確認一下");
        else {
            if (mats instanceof Array) {
                for (var i = 0; i < mats.length; i++) {
                    cm.gainItem(mats[i], -matQty[i] * qty);
                }
            } else
                cm.gainItem(mats, -matQty * qty);

            if (cost > 0)
                cm.gainMeso(-cost * qty);

            if (item == 4003000)//screws
                cm.gainItem(4003000, 15 * qty);
            else
                cm.gainItem(item, qty);
            cm.sendOk("很棒吧?我的手藝,如果還有需要歡迎來找我,我哪都不會去的.");
        }
        cm.dispose();
    }
}