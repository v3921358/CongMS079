/* JM from tha Streetz
 Victoria Road: Kerning City (103000000)
 
 Refining NPC: 
 * Gloves
 * Glove Upgrade
 * Claw
 * Claw Upgrade
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
        var selStr = "孩子...如果你有一些適合的東西，我可以把它變成什麼...#b"
        var options = new Array("製作手套", "升級手套", "製作拳套", "升級拳套", "木材與螺絲釘製作");
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
            var selStr = "那麼，你想要我製作什麼樣的手套?#b";
            var gloves = new Array("工作手套#k - 需要等級 Lv. 10#b", "褐短指手套#k - 需要等級 Lv. 15#b", "藍短指手套#k - 需要等級 Lv. 15#b", "黑短指手套#k - 需要等級 Lv. 15#b", "青銅盜賊手套#k - 需要等級 Lv. 20#b", "青銅精神手套#k - 需要等級 Lv. 25#b", "鋼鐵暴風手套#k - 需要等級 Lv. 30#b",
                    "鋼鐵追擊手套#k - 需要等級 Lv. 35#b", "紅盜賊手套#k - 需要等級 Lv. 40#b", "青月手套#k - 需要等級 Lv. 50#b", "青銅柔絲手套#k - 需要等級 Lv. 60#b");
            for (var i = 0; i < gloves.length; i++) {
                selStr += "\r\n#L" + i + "# " + gloves[i] + "#l";
            }
            equip = true;
            cm.sendSimple(selStr);
        } else if (selectedType == 1) { //glove upgrade
            var selStr = "要升級手套?當然可以, 但要注意，升級後將不會延續到新的項目... #b";
            var gloves = new Array("鋰礦盜賊手套#k - 需要等級 Lv. 20#b", "黑色盜賊手套#k - 需要等級 Lv. 20#b", "鋰礦精神手套#k - 需要等級 Lv. 25#b",
                    "黑精神手套#k - 需要等級 Lv. 25#b", "銀暴風手套#k - 需要等級 Lv. 30#b", "黃金暴風手套#k - 需要等級 Lv. 30#b", "紫礦追擊手套#k - 需要等級 Lv. 35#b", "黃金追擊手套#k - 需要等級 Lv. 35#b", "黃金盜賊手套#k - 需要等級 Lv. 40#b",
                    "黑盜賊手套#k - 需要等級 Lv. 40#b", "赤月手套#k - 需要等級 Lv. 50#b", "黃月手套#k - 需要等級 Lv. 50#b", "鋼鐵柔絲手套#k - 需要等級 Lv. 60#b", "黃金柔絲手套#k - 需要等級 Lv. 60#b");
            for (var i = 0; i < gloves.length; i++) {
                selStr += "\r\n#L" + i + "# " + gloves[i] + "#l";
            }
            equip = true;
            cm.sendSimple(selStr);
        } else if (selectedType == 2) { //claw refine
            var selStr = "那麼，你想要我製作什麼樣的拳套呢?#b";
            var claws = new Array("鋼鐵拳套#k - 需要等級 Lv. 15#b", "青銅指虎#k - 需要等級 Lv. 20#b", "狼牙#k - 需要等級 Lv. 25#b",
                    "鋼鐵鬥拳#k - 需要等級 Lv. 30#b", "青銅守護指套#k - 需要等級 Lv. 35#b", "鋼鐵護腕#k - 需要等級 Lv. 40#b", "鋼鐵手甲#k - 需要等級 Lv. 50#b");
            for (var i = 0; i < claws.length; i++) {
                selStr += "\r\n#L" + i + "# " + claws[i] + "#l";
            }
            equip = true;
            cm.sendSimple(selStr);
        } else if (selectedType == 3) { //claw upgrade
            var selStr = "要升級拳套?當然可以, 但要注意，升級後將不會延續到新的項目...#b";
            var claws = new Array("鋰礦拳套#k - 需要等級 Lv. 15#b", "黃金拳套#k - 需要等級 Lv. 15#b", "鋼鐵指虎#k - 需要等級 Lv. 20#b", "朱礦指虎#k - 需要等級 Lv. 20#b", "鋰礦鬥拳#k - 需要等級 Lv. 30#b", "朱礦鬥拳#k - 需要等級 Lv. 30#b", "銀守護拳套#k - 需要等級 Lv. 35#b", "黑守護拳套#k - 需要等級 Lv. 35#b", "赤紅護腕#k - 需要等級 Lv. 40#b", "朱礦護腕#k - 需要等級 Lv. 40#b", "黑護腕#k - 需要等級 Lv. 40#b", "赤紅手甲#k - 需要等級 Lv. 50#b", "藍寶手甲#k - 需要等級 Lv. 50#b");
            for (var i = 0; i < claws.length; i++) {
                selStr += "\r\n#L" + i + "# " + claws[i] + "#l";
            }
            equip = true;
            cm.sendSimple(selStr);
        } else if (selectedType == 4) { //material refine
            var selStr = "材料？我知道一些材料，我可以幫你...?#b";
            var materials = new Array("用10個樹枝製作1個木材", "用5個木柴製作1個木材", "製作螺絲釘(1次15個)");
            for (var i = 0; i < materials.length; i++) {
                selStr += "\r\n#L" + i + "# " + materials[i] + "#l";
            }
            equip = false;
            cm.sendSimple(selStr);
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
        if (selectedType == 4) { //material refine
            var itemSet = new Array(4003001, 4003001, 4003000);
            var matSet = new Array(4000003, 4000018, new Array(4011000, 4011001));
            var matQtySet = new Array(10, 5, new Array(1, 1));
            var costSet = new Array(0, 0, 0);
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

        if (selectedType == 0) { //glove refine
            var itemSet = new Array(1082002, 1082029, 1082030, 1082031, 1082032, 1082037, 1082042, 1082046, 1082075, 1082065, 1082092);
            var matSet = new Array(4000021, new Array(4000021, 4000018), new Array(4000021, 4000015), new Array(4000021, 4000020), new Array(4011000, 4000021), new Array(4011000, 4011001, 4000021), new Array(4011001, 4000021, 4003000), new Array(4011001, 4011000, 4000021, 4003000), new Array(4021000, 4000014, 4000021, 4003000), new Array(4021005, 4021008, 4000030, 4003000), new Array(4011007, 4011000, 4021007, 4000030, 4003000));
            var matQtySet = new Array(15, new Array(30, 20), new Array(30, 20), new Array(30, 20), new Array(2, 40), new Array(2, 1, 10), new Array(2, 50, 10), new Array(3, 1, 60, 15), new Array(3, 200, 80, 30), new Array(3, 1, 40, 30), new Array(1, 8, 1, 50, 50));
            var costSet = new Array(1000, 7000, 7000, 7000, 10000, 15000, 25000, 30000, 40000, 50000, 70000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        } else if (selectedType == 1) { //glove upgrade
            var itemSet = new Array(1082033, 1082034, 1082038, 1082039, 1082043, 1082044, 1082047, 1082045, 1082076, 1082074, 1082067, 1082066, 1082093, 1082094);
            var matSet = new Array(new Array(1082032, 4011002), new Array(1082032, 4021004), new Array(1082037, 4011002), new Array(1082037, 4021004), new Array(1082042, 4011004), new Array(1082042, 4011006), new Array(1082046, 4011005), new Array(1082046, 4011006), new Array(1082075, 4011006), new Array(1082075, 4021008), new Array(1082065, 4021000), new Array(1082065, 4011006, 4021008), new Array(1082092, 4011001, 4000014), new Array(1082092, 4011006, 4000027));
            var matQtySet = new Array(new Array(1, 1), new Array(1, 1), new Array(1, 2), new Array(1, 2), new Array(1, 2), new Array(1, 1), new Array(1, 3), new Array(1, 2), new Array(1, 4), new Array(1, 2), new Array(1, 5), new Array(1, 2, 1), new Array(1, 7, 200), new Array(1, 7, 150));
            var costSet = new Array(5000, 7000, 10000, 12000, 15000, 20000, 22000, 25000, 40000, 50000, 55000, 60000, 70000, 80000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        } else if (selectedType == 2) { //claw refine
            var itemSet = new Array(1472001, 1472004, 1472007, 1472008, 1472011, 1472014, 1472018);
            var matSet = new Array(new Array(4011001, 4000021, 4003000), new Array(4011000, 4011001, 4000021, 4003000), new Array(1472000, 4011001, 4000021, 4003001), new Array(4011000, 4011001, 4000021, 4003000), new Array(4011000, 4011001, 4000021, 4003000), new Array(4011000, 4011001, 4000021, 4003000), new Array(4011000, 4011001, 4000030, 4003000));
            var matQtySet = new Array(new Array(1, 20, 5), new Array(2, 1, 30, 10), new Array(1, 3, 20, 30), new Array(3, 2, 50, 20), new Array(4, 2, 80, 25), new Array(3, 2, 100, 30), new Array(4, 2, 40, 35));
            var costSet = new Array(2000, 3000, 5000, 15000, 30000, 40000, 50000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        } else if (selectedType == 3) { //claw upgrade
            var itemSet = new Array(1472002, 1472003, 1472005, 1472006, 1472009, 1472010, 1472012, 1472013, 1472015, 1472016, 1472017, 1472019, 1472020);
            var matSet = new Array(new Array(1472001, 4011002), new Array(1472001, 4011006), new Array(1472004, 4011001), new Array(1472004, 4011003), new Array(1472008, 4011002), new Array(1472008, 4011003), new Array(1472011, 4011004), new Array(1472011, 4021008), new Array(1472014, 4021000), new Array(1472014, 4011003), new Array(1472014, 4021008), new Array(1472018, 4021000), new Array(1472018, 4021005));
            var matQtySet = new Array(new Array(1, 1), new Array(1, 1), new Array(1, 2), new Array(1, 2), new Array(1, 3), new Array(1, 3), new Array(1, 4), new Array(1, 1), new Array(1, 5), new Array(1, 5), new Array(1, 2), new Array(1, 6), new Array(1, 6));
            var costSet = new Array(1000, 2000, 3000, 5000, 10000, 15000, 20000, 25000, 30000, 30000, 35000, 40000, 40000);
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
        if (!complete) {
            cm.sendOk("不要以為我是外國人就想騙我啊...再確認你的包包是否有材料吧");
        } else {
            if (mats instanceof Array) {
                for (var i = 0; i < mats.length; i++) {
                    cm.gainItem(mats[i], -matQty[i] * qty);
                }
            } else {
                cm.gainItem(mats, -matQty * qty);
            }
            if (cost > 0) {
                cm.gainMeso(-cost * qty);
            }
            if (item == 4003000) {//screws
                cm.gainItem(4003000, 15 * qty);
            } else {
                cm.gainItem(item, qty);
            }
            cm.sendOk("很棒吧?我的手藝,如果還有需要歡迎來找我,我哪都不會去的.");
        }
        cm.dispose();
    }
}