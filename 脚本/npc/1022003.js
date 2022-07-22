/* Mr. Thunder
 Victoria Road: Perion (102000000)
 
 Refining NPC:
 * Minerals
 * Jewels
 * Shields
 * Helmets
 */

var status = -1;
var selectedType = -1;
var selectedItem = -1;
var item;
var mats;
var matQty;
var cost;
var qty;
var equip;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        cm.dispose();
    }
    if (status == 0 && mode == 1) {
        var selStr = "你是聽誰說我會製作礦石的? 是的...但我會收取一些費用哦.#b"
        var options = new Array("製作母礦", "製作寶石", "升級頭盔", "升級盾牌");
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
        if (selectedType == 0) { //mineral refine
            var selStr = "所以,你喜歡哪一種礦石呢? 我可以幫你提煉.#b";
            var minerals = new Array("青銅", "鋼鐵", "鋰礦石", "朱礦石", "銀", "紫礦石", "黃金");
            for (var i = 0; i < minerals.length; i++) {
                selStr += "\r\n#L" + i + "# " + minerals[i] + "#l";
            }
            cm.sendSimple(selStr);
            equip = false;
        } else if (selectedType == 1) { //jewel refine
            var selStr = "所以,你喜歡哪一種寶石呢? 我可以幫你提煉#b";
            var jewels = new Array("石柳石", "紫水晶", "海藍寶石", "祖母綠", "蛋白石", "藍寶石", "黃晶", "鑽石,可是沒有五倍哦", "黑水晶");
            for (var i = 0; i < jewels.length; i++) {
                selStr += "\r\n#L" + i + "# " + jewels[i] + "#l";
            }
            cm.sendSimple(selStr);
            equip = false;
        } else if (selectedType == 2) { //helmet refine
            var selStr = "哦? 你想要升級頭盔? 好的那告訴我要升級成哪一頂吧!!#b";
            var helmets = new Array("藍色金屬頭箍#k - 需要等級 Lv. 15#b", "黃色金屬頭箍#k - 需要等級 Lv. 15#b", "金屬頭盔#k - 需要等級 Lv. 10#b", "鋰礦頭盔#k - 需要等級 Lv. 10#b", "鋼鐵帽#k - 需要等級 Lv. 12#b", "鋰礦帽#k - 需要等級 Lv. 12#b", "鐵製頭具#k - 需要等級 Lv. 15#b",
                    "鋰礦鋼盔#k - 需要等級 Lv. 15#b", "鋼製海盜帽#k - 需要等級 Lv. 20#b", "鋰礦海盜頭盔#k - 需要等級 Lv. 20#b", "鋼鐵球帽#k - 需要等級 Lv. 20#b", "鋰礦橄欖球帽#k - 需要等級 Lv. 20#b", "鋰礦尖頭盔#k - 需要等級 Lv. 22#b",
                    "黃金尖頭盔#k - 需要等級 Lv. 22#b",
                    "黃金騎士頭盔#k - 需要等級 Lv. 25#b", "紫礦騎士頭盔#k - 需要等級 Lv. 25#b", "紅色戰鬥頭盔#k - 需要等級 Lv. 35#b", "藍色戰鬥頭盔#k - 需要等級 Lv. 35#b", "鋰礦諾曼頭盔#k - 需要等級 Lv. 40#b", "黃金諾曼頭盔#k - 需要等級 Lv. 40#b", "鋰礦十字軍帽#k - 需要等級 Lv. 50#b",
                    "銀製十字軍帽子#k - 需要等級 Lv. 50#b", "舊諾曼頭盔#k - 需要等級 Lv. 55#b", "舊鋰礦諾曼頭盔#k - 需要等級 Lv. 55#b");
            for (var i = 0; i < helmets.length; i++) {
                selStr += "\r\n#L" + i + "# " + helmets[i] + "#l";
            }
            cm.sendSimple(selStr);
            equip = true;
        } else if (selectedType == 3) { //shield refine
            var selStr = "哦? 你想要升級盾牌? 好的那告訴我要升級成哪一個吧!?#b";
            var shields = new Array("朱礦方盾#k - 需要等級 Lv. 40#b", "鋰礦方盾#k - 需要等級 Lv. 40#b", "古老銀盾#k - 需要等級 Lv. 60#b", "古老朱礦盾#k - 需要等級 Lv. 60#b");
            for (var i = 0; i < shields.length; i++) {
                selStr += "\r\n#L" + i + "# " + shields[i] + "#l";
            }
            cm.sendSimple(selStr);
            equip = true;
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
        if (selectedType == 0) { //mineral refine
            var itemSet = new Array(4011000, 4011001, 4011002, 4011003, 4011004, 4011005, 4011006);
            var matSet = new Array(4010000, 4010001, 4010002, 4010003, 4010004, 4010005, 4010006);
            var matQtySet = new Array(10, 10, 10, 10, 10, 10, 10);
            var costSet = new Array(300, 300, 300, 500, 500, 500, 800);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        } else if (selectedType == 1) { //jewel refine
            var itemSet = new Array(4021000, 4021001, 4021002, 4021003, 4021004, 4021005, 4021006, 4021007, 4021008);
            var matSet = new Array(4020000, 4020001, 4020002, 4020003, 4020004, 4020005, 4020006, 4020007, 4020008);
            var matQtySet = new Array(10, 10, 10, 10, 10, 10, 10, 10, 10);
            var costSet = new Array(500, 500, 500, 500, 500, 500, 500, 1000, 3000);
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
        if (equip) {
            selectedItem = selection;
            qty = 1;
        } else
            qty = selection;

        if (selectedType == 2) { //helmet refine
            var itemSet = new Array(1002042, 1002041, 1002002, 1002044, 1002003, 1002040, 1002007, 1002052, 1002011, 1002058, 1002009, 1002056, 1002087, 1002088, 1002049, 1002050, 1002047, 1002048, 1002099, 1002098, 1002085, 1002028, 1002022, 1002101);
            var matSet = new Array(new Array(1002001, 4011002), new Array(1002001, 4021006), new Array(1002043, 4011001), new Array(1002043, 4011002), new Array(1002039, 4011001), new Array(1002039, 4011002), new Array(1002051, 4011001), new Array(1002051, 4011002), new Array(1002059, 4011001), new Array(1002059, 4011002),
                    new Array(1002055, 4011001), new Array(1002055, 4011002), new Array(1002027, 4011002), new Array(1002027, 4011006), new Array(1002005, 4011006), new Array(1002005, 4011005), new Array(1002004, 4021000), new Array(1002004, 4021005), new Array(1002021, 4011002), new Array(1002021, 4011006), new Array(1002086, 4011002),
                    new Array(1002086, 4011004), new Array(1002100, 4011007, 4011001), new Array(1002100, 4011007, 4011002));
            var matQtySet = new Array(new Array(1, 1), new Array(1, 1), new Array(1, 1), new Array(1, 1), new Array(1, 1), new Array(1, 1), new Array(1, 2), new Array(1, 2), new Array(1, 3), new Array(1, 3), new Array(1, 3), new Array(1, 3), new Array(1, 4), new Array(1, 4), new Array(1, 5), new Array(1, 5), new Array(1, 3), new Array(1, 3),
                    new Array(1, 5), new Array(1, 6), new Array(1, 5), new Array(1, 4), new Array(1, 1, 7), new Array(1, 1, 7));
            var costSet = new Array(500, 300, 500, 800, 500, 800, 1000, 1500, 1500, 2000, 1500, 2000, 2000, 4000, 4000, 5000, 8000, 10000, 12000, 15000, 20000, 25000, 30000, 30000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        } else if (selectedType == 3) { //shield refine
            var itemSet = new Array(1092014, 1092013, 1092010, 1092011);
            var matSet = new Array(new Array(1092012, 4011003), new Array(1092012, 4011002), new Array(1092009, 4011007, 4011004), new Array(1092009, 4011007, 4011003));
            var matQtySet = new Array(new Array(1, 10), new Array(1, 10), new Array(1, 1, 15), new Array(1, 1, 15));
            var costSet = new Array(100000, 100000, 120000, 120000);
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

        prompt += " 好的我會幫你完成的,但請你確認你的背包是否有足夠的空間哦#b";

        if (mats instanceof Array) {
            for (var i = 0; i < mats.length; i++) {
                prompt += "\r\n#i" + mats[i] + "# " + matQty[i] * qty + " #t" + mats[i] + "#";
            }
        } else {
            prompt += "\r\n#i" + mats + "# " + matQty * qty + " #t" + mats + "#";
        }

        if (cost > 0)
            prompt += "\r\n#i4031138# " + cost * qty + " meso";

        cm.sendYesNo(prompt);
    } else if (status == 4 && mode == 1) {
        var complete = true;

        if (cm.getMeso() < cost * qty) {
            cm.sendOk("我怕你付不起我的工資,抱歉囉.");
            cm.safeDispose();
            return;
        } else {
            if (mats instanceof Array) {

                for (var i = 0; complete && i < mats.length; i++) {
                    if (!cm.haveItem(mats[i], matQty[i] * qty)) {
                        complete = false;
                    }
                }
            } else {
                if (!cm.haveItem(mats, matQty * qty)) {
                    complete = false;
                }
            }

            if (!complete)
                cm.sendOk("你給我的材料可能不夠哦,再幫我檢查一下背包吧~");
            else {
                if (!cm.canHold(item, qty)) {
                    cm.sendNext("您的背包空間不足");
                    cm.dispose();
                    return;
                }
                if (mats instanceof Array) {
                    for (var i = 0; i < mats.length; i++) {
                        cm.gainItem(mats[i], -matQty[i] * qty);
                    }
                } else
                    cm.gainItem(mats, -matQty * qty);

                cm.gainMeso(-cost * qty);
                cm.gainItem(item, qty);
                cm.sendOk("完成了~看看我的手藝,不錯吧? 有需要請再來找我哦~");
            }
            cm.safeDispose();
        }
    }
}
