
var status = 0;
var selectedType = -1;
var selectedItem = -1;
var item;
var 皇冠 = "#fUI/UIWindow.img/SkillMacro/Macroicon/4/iconMouseOver#";
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
    if (mode == 1){
        status++;
    }else{
        cm.dispose();
	}

    if (cm.getInventory(4).isFull()) {
        cm.说明文字("请保证 #b其他栏#k 至少有2个位置。");
        cm.对话结束();
        return;
    } 
    if (status == 0 && mode == 1) {
        var selStr = "    \t\t\t"+皇冠+"#d#e矿石材料中心#k"+皇冠+"#k#n\r\n\r\n"
        //菜单
        var options = new Array(
                "做一些金属",
                "做一些宝石",
                "做一些高级水晶",
				"#b五彩水晶兑换"
				//"#r高级材料兑换"
                );
        for (var i = 0; i < options.length; i++) {
            selStr += "\r\n#L" + i + "# " + options[i] + "#l";
        }
        cm.sendSimple(selStr);
    } else if (status == 1 && mode == 1) {
        selectedType = selection;
        //合成一些金属
        if (selectedType == 0) {
            var selStr = "   Hi #b#h ##k，你要合成什么金属呢？？#b";
            var minerals = new Array(
                    "" + cm.显示物品(4011000) + "",
                    "" + cm.显示物品(4011001) + "",
                    "" + cm.显示物品(4011002) + "",
                    "" + cm.显示物品(4011003) + "",
                    "" + cm.显示物品(4011004) + "",
                    "" + cm.显示物品(4011005) + "",
                    "" + cm.显示物品(4011006) + ""
					//"" + cm.显示物品(4011008) + ""
                    );
            for (var i = 0; i < minerals.length; i++) {
                selStr += "\r\n#L" + i + "# " + minerals[i] + "#l";
            }
            equip = false;
            cm.sendSimple(selStr);
            //合成一些宝石
        } else if (selectedType == 1) {
            var selStr = "   Hi #b#h ##k，你要合成什么宝石呢？？#b";
            var jewels = new Array(
                    "" + cm.显示物品(4021000) + "",
                    "" + cm.显示物品(4021001) + "",
                    "" + cm.显示物品(4021002) + "",
                    "" + cm.显示物品(4021003) + "",
                    "" + cm.显示物品(4021004) + "",
                    "" + cm.显示物品(4021005) + "",
                    "" + cm.显示物品(4021006) + "",
                    "" + cm.显示物品(4021007) + "",
                    "" + cm.显示物品(4021008) + ""
                    );
            for (var i = 0; i < jewels.length; i++) {
                selStr += "\r\n#L" + i + "# " + jewels[i] + "#l";
            }
            equip = false;
            cm.sendSimple(selStr);
            //五彩水晶
        } else if (selectedType == 3) {
            var selStr = "   Hi #b#h ##k，你要合成什么五彩水晶呢？？#b";
            var items = new Array(
                    "" + cm.显示物品(4251200) + "",
                    "" + cm.显示物品(4251201) + "",
					"" + cm.显示物品(4251202) + ""
                    );
            for (var i = 0; i < items.length; i++) {
                selStr += "\r\n#L" + i + "# " + items[i] + "#l";
            }
            cm.sendSimple(selStr);
            //高级水晶
        } else if (selectedType == 2) {
            var selStr = "   Hi #b#h ##k，你要合成什么高级水晶呢？？#b";
            var crystals = new Array(
                    "" + cm.显示物品(4005000) + "",
                    "" + cm.显示物品(4005001) + "",
                    "" + cm.显示物品(4005002) + "",
                    "" + cm.显示物品(4005003) + "",
                    "" + cm.显示物品(4005004) + ""
                    );
            for (var i = 0; i < crystals.length; i++) {
                selStr += "\r\n#L" + i + "# " + crystals[i] + "#l";
            }
			cm.sendSimple(selStr);
            //高级水晶
        } else if (selectedType == 4) {
            var selStr = "   Hi #b#h ##k，你要合成什么高级材料呢？？#b";
            var crystals = new Array(
                    "" + cm.显示物品(4310034) + "",
                    "" + cm.显示物品(4310029) + "",
                    "" + cm.显示物品(4310027) + "",
                    "" + cm.显示物品(4310038) + "",
					"" + cm.显示物品(4032056) + "",
                    "" + cm.显示物品(4310148) + "",
					"" + cm.显示物品(4011007) + "",
					"" + cm.显示物品(4021009) + ""
                    );
            for (var i = 0; i < crystals.length; i++) {
                selStr += "\r\n#L" + i + "# " + crystals[i] + "#l";
            }
            equip = false;
            cm.sendSimple(selStr);
		}
        if (equip)
            status++;
    } else if (status == 2 && mode == 1) {
        selectedItem = selection;
        if (selectedType == 0) {
            //金属
            var itemSet = new Array(4011000, 4011001, 4011002, 4011003, 4011004, 4011005, 4011006);
            var matSet = new Array(4010000, 4010001, 4010002, 4010003, 4010004, 4010005, 4010006,4010007);
            var matQtySet = new Array(10, 10, 10, 10, 10, 10, 10,10);
            var costSet = new Array(300, 300, 300, 500, 500, 500, 800,1000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        } else if (selectedType == 1) {
            //宝石
            var itemSet = new Array(4021000, 4021001, 4021002, 4021003, 4021004, 4021005, 4021006, 4021007, 4021008);
            var matSet = new Array(4020000, 4020001, 4020002, 4020003, 4020004, 4020005, 4020006, 4020007, 4020008);
            var matQtySet = new Array(10, 10, 10, 10, 10, 10, 10, 10, 10);
            var costSet = new Array(500, 500, 500, 500, 500, 500, 500, 1000, 3000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        } else if (selectedType == 3) {
			//五彩水晶
            var itemSet = new Array(4251200, 4251201 , 4251202 );
            var matSet = new Array(
			new Array(4005000, 4005001, 4005002, 4005003, 4005004), 
			new Array(4250801, 4250901, 4251001, 4251101, 4251401),
			new Array(4250802, 4250902, 4251002, 4251102, 4251402));
            var matQtySet = new Array(new Array(1, 1, 1, 1, 1, 1), new Array(1, 1, 1, 1, 1, 1), new Array(1, 1, 1, 1, 1, 1));
            var costSet = new Array(10000, 15000, 30000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        } else if (selectedType == 2) {
			//水晶
            var itemSet = new Array(4005000, 4005001, 4005002, 4005003, 4005004);
            var matSet = new Array(4004000, 4004001, 4004002, 4004003, 4004004);
            var matQtySet = new Array(10, 10, 10, 10, 10);
            var costSet = new Array(5000, 5000, 5000, 5000, 1000000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
		} else if (selectedType == 4) {
			//高级材料
            var itemSet = new Array(4310034, 4310029,4310027,4310038,4032056,4310148,4011007,4021009);
            var matSet = new Array(
			new Array(4250202, 4250502, 4250402, 4251302, 4250702, 4250302, 4250002, 4250602), 
			new Array(4250102, 4250502, 4250402, 4251302, 4250702, 4250302, 4250002, 4250602),
			new Array(4000463, 2049118),
			new Array(4000463, 2049118),
			new Array(4001126, 4001126),//枫叶
			new Array(4310150, 2049118),
			new Array(4011000, 4011001, 4011002, 4011003, 4011004, 4011005, 4011006),
			new Array(4021000, 4021001, 4021002, 4021003, 4021004, 4021005, 4021006, 4021007, 4021008));
            var matQtySet = new Array(
			new Array(1, 1, 1, 1, 1, 1, 1, 1), 
			new Array(1, 1, 1, 1, 1, 1, 1, 1), 
			new Array(50, 1),
			new Array(50, 1), 
			new Array(200, 200), 
			new Array(10, 1),
			new Array(1, 1, 1, 1, 1, 1, 1, 1),
			new Array(1, 1, 1, 1, 1, 1, 1, 1, 1, 1));
            var costSet = new Array(100000, 100000, 100000, 100000, 100000, 100000, 10000, 10000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        } 
        var prompt = "   你想要做一些 " + cm.显示物品(item) + " ? 在这种情况下, 我为了要做出最棒的品质，我建议你确保背包空间足够。\r\n\r\n   输入你要制作的数量:";
        cm.sendGetNumber(prompt, 1, 1, 100)
    } else if (status == 3 && mode == 1) {
        if (equip)
        {
            selectedItem = selection;
            qty = 1;
        } else
            qty = selection;

        if (selectedType == 5) {
            var itemSet = new Array(2060000, 2061000, 2060001, 2061001, 2060002, 2061002);
            var matSet = new Array(new Array(4003001, 4003004), new Array(4003001, 4003004), new Array(4011000, 4003001, 4003004), new Array(4011000, 4003001, 4003004),
                    new Array(4011001, 4003001, 4003005), new Array(4011001, 4003001, 4003005));
            var matQtySet = new Array(new Array(1, 1), new Array(1, 1), new Array(1, 3, 10), new Array(1, 3, 10), new Array(1, 5, 15), new Array(1, 5, 15));
            var costSet = new Array(0, 0, 0, 0, 0, 0);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        }

        var prompt = "你想要我做 ";
        if (qty == 1){
            prompt += " #r1#k 个 " + cm.显示物品(item) + " ?";
        }else{
            prompt += "#r" + qty + "#k 个 " + cm.显示物品(item) + " ?";
		}
        prompt += " 我为了要做出最棒的品质，我建议你确保背包空间足够。#b\r\n";

        if (mats instanceof Array) {
            for (var i = 0; i < mats.length; i++) {
                //prompt += "\r\n#i" + mats[i] + "# " + matQty[i] * qty + " #t" + mats[i] + "#";
                prompt += "\r\n#i" + mats[i] + "# #t" + mats[i] + "# x " + matQty[i] * qty + "";
            }
        } else {
            prompt += "\r\n#i" + mats + "# #t" + mats + "# x " + matQty * qty + "";
        }

        if (cost > 0)
            prompt += "\r\n#i4031138# x " + cost * qty + "";

        cm.sendYesNo(prompt);
    } else if (status == 4 && mode == 1) {
        var complete = true;

        if (cm.getMeso() < cost * qty) {
            cm.sendOk("请你准备足够的钱再来找我吧。")
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
                if (!cm.haveItem(mats, matQty * qty)){
                    complete = false;
                }
            }
        }

        if (!complete) {
            cm.sendOk("很抱歉由于你的材料不足，所以我不想帮你做了。");
        } else {
            if (mats instanceof Array) {
                for (var i = 0; i < mats.length; i++) {
                    cm.gainItem(mats[i], -matQty[i] * qty);
                }
            } else
                cm.gainItem(mats, -matQty * qty);

            if (cost > 0) {
                cm.gainMeso(-cost * qty);
            } else {
                cm.sendNext("发生错误请回报管理员");
                cm.dispose();
                return;

            }
            if (item >= 2060000 && item <= 2060002)
                cm.gainItem(item, 1000 - (item - 2060000) * 100);
            else if (item >= 2061000 && item <= 2061002)
                cm.gainItem(item, 1000 - (item - 2061000) * 100);
            else if (item == 4003000)
                cm.gainItem(4003000, 15 * qty);
            else
                cm.gainItem(item, qty);
            cm.sendOk("制作完毕。");
        }
        cm.dispose();
    }
}
