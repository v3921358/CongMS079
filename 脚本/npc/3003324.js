
var status = 0;
var selectedType = -1;
var selectedItem = -1;
var item;
var �ʹ� = "#fUI/UIWindow.img/SkillMacro/Macroicon/4/iconMouseOver#";
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
        cm.˵������("�뱣֤ #b������#k ������2��λ�á�");
        cm.�Ի�����();
        return;
    } 
    if (status == 0 && mode == 1) {
        var selStr = "    \t\t\t"+�ʹ�+"#d#e��ʯ��������#k"+�ʹ�+"#k#n\r\n\r\n"
        //�˵�
        var options = new Array(
                "��һЩ����",
                "��һЩ��ʯ",
                "��һЩ�߼�ˮ��",
				"#b���ˮ���һ�"
				//"#r�߼����϶һ�"
                );
        for (var i = 0; i < options.length; i++) {
            selStr += "\r\n#L" + i + "# " + options[i] + "#l";
        }
        cm.sendSimple(selStr);
    } else if (status == 1 && mode == 1) {
        selectedType = selection;
        //�ϳ�һЩ����
        if (selectedType == 0) {
            var selStr = "   Hi #b#h ##k����Ҫ�ϳ�ʲô�����أ���#b";
            var minerals = new Array(
                    "" + cm.��ʾ��Ʒ(4011000) + "",
                    "" + cm.��ʾ��Ʒ(4011001) + "",
                    "" + cm.��ʾ��Ʒ(4011002) + "",
                    "" + cm.��ʾ��Ʒ(4011003) + "",
                    "" + cm.��ʾ��Ʒ(4011004) + "",
                    "" + cm.��ʾ��Ʒ(4011005) + "",
                    "" + cm.��ʾ��Ʒ(4011006) + ""
					//"" + cm.��ʾ��Ʒ(4011008) + ""
                    );
            for (var i = 0; i < minerals.length; i++) {
                selStr += "\r\n#L" + i + "# " + minerals[i] + "#l";
            }
            equip = false;
            cm.sendSimple(selStr);
            //�ϳ�һЩ��ʯ
        } else if (selectedType == 1) {
            var selStr = "   Hi #b#h ##k����Ҫ�ϳ�ʲô��ʯ�أ���#b";
            var jewels = new Array(
                    "" + cm.��ʾ��Ʒ(4021000) + "",
                    "" + cm.��ʾ��Ʒ(4021001) + "",
                    "" + cm.��ʾ��Ʒ(4021002) + "",
                    "" + cm.��ʾ��Ʒ(4021003) + "",
                    "" + cm.��ʾ��Ʒ(4021004) + "",
                    "" + cm.��ʾ��Ʒ(4021005) + "",
                    "" + cm.��ʾ��Ʒ(4021006) + "",
                    "" + cm.��ʾ��Ʒ(4021007) + "",
                    "" + cm.��ʾ��Ʒ(4021008) + ""
                    );
            for (var i = 0; i < jewels.length; i++) {
                selStr += "\r\n#L" + i + "# " + jewels[i] + "#l";
            }
            equip = false;
            cm.sendSimple(selStr);
            //���ˮ��
        } else if (selectedType == 3) {
            var selStr = "   Hi #b#h ##k����Ҫ�ϳ�ʲô���ˮ���أ���#b";
            var items = new Array(
                    "" + cm.��ʾ��Ʒ(4251200) + "",
                    "" + cm.��ʾ��Ʒ(4251201) + "",
					"" + cm.��ʾ��Ʒ(4251202) + ""
                    );
            for (var i = 0; i < items.length; i++) {
                selStr += "\r\n#L" + i + "# " + items[i] + "#l";
            }
            cm.sendSimple(selStr);
            //�߼�ˮ��
        } else if (selectedType == 2) {
            var selStr = "   Hi #b#h ##k����Ҫ�ϳ�ʲô�߼�ˮ���أ���#b";
            var crystals = new Array(
                    "" + cm.��ʾ��Ʒ(4005000) + "",
                    "" + cm.��ʾ��Ʒ(4005001) + "",
                    "" + cm.��ʾ��Ʒ(4005002) + "",
                    "" + cm.��ʾ��Ʒ(4005003) + "",
                    "" + cm.��ʾ��Ʒ(4005004) + ""
                    );
            for (var i = 0; i < crystals.length; i++) {
                selStr += "\r\n#L" + i + "# " + crystals[i] + "#l";
            }
			cm.sendSimple(selStr);
            //�߼�ˮ��
        } else if (selectedType == 4) {
            var selStr = "   Hi #b#h ##k����Ҫ�ϳ�ʲô�߼������أ���#b";
            var crystals = new Array(
                    "" + cm.��ʾ��Ʒ(4310034) + "",
                    "" + cm.��ʾ��Ʒ(4310029) + "",
                    "" + cm.��ʾ��Ʒ(4310027) + "",
                    "" + cm.��ʾ��Ʒ(4310038) + "",
					"" + cm.��ʾ��Ʒ(4032056) + "",
                    "" + cm.��ʾ��Ʒ(4310148) + "",
					"" + cm.��ʾ��Ʒ(4011007) + "",
					"" + cm.��ʾ��Ʒ(4021009) + ""
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
            //����
            var itemSet = new Array(4011000, 4011001, 4011002, 4011003, 4011004, 4011005, 4011006);
            var matSet = new Array(4010000, 4010001, 4010002, 4010003, 4010004, 4010005, 4010006,4010007);
            var matQtySet = new Array(10, 10, 10, 10, 10, 10, 10,10);
            var costSet = new Array(300, 300, 300, 500, 500, 500, 800,1000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        } else if (selectedType == 1) {
            //��ʯ
            var itemSet = new Array(4021000, 4021001, 4021002, 4021003, 4021004, 4021005, 4021006, 4021007, 4021008);
            var matSet = new Array(4020000, 4020001, 4020002, 4020003, 4020004, 4020005, 4020006, 4020007, 4020008);
            var matQtySet = new Array(10, 10, 10, 10, 10, 10, 10, 10, 10);
            var costSet = new Array(500, 500, 500, 500, 500, 500, 500, 1000, 3000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
        } else if (selectedType == 3) {
			//���ˮ��
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
			//ˮ��
            var itemSet = new Array(4005000, 4005001, 4005002, 4005003, 4005004);
            var matSet = new Array(4004000, 4004001, 4004002, 4004003, 4004004);
            var matQtySet = new Array(10, 10, 10, 10, 10);
            var costSet = new Array(5000, 5000, 5000, 5000, 1000000);
            item = itemSet[selectedItem];
            mats = matSet[selectedItem];
            matQty = matQtySet[selectedItem];
            cost = costSet[selectedItem];
		} else if (selectedType == 4) {
			//�߼�����
            var itemSet = new Array(4310034, 4310029,4310027,4310038,4032056,4310148,4011007,4021009);
            var matSet = new Array(
			new Array(4250202, 4250502, 4250402, 4251302, 4250702, 4250302, 4250002, 4250602), 
			new Array(4250102, 4250502, 4250402, 4251302, 4250702, 4250302, 4250002, 4250602),
			new Array(4000463, 2049118),
			new Array(4000463, 2049118),
			new Array(4001126, 4001126),//��Ҷ
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
        var prompt = "   ����Ҫ��һЩ " + cm.��ʾ��Ʒ(item) + " ? �����������, ��Ϊ��Ҫ���������Ʒ�ʣ��ҽ�����ȷ�������ռ��㹻��\r\n\r\n   ������Ҫ����������:";
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

        var prompt = "����Ҫ���� ";
        if (qty == 1){
            prompt += " #r1#k �� " + cm.��ʾ��Ʒ(item) + " ?";
        }else{
            prompt += "#r" + qty + "#k �� " + cm.��ʾ��Ʒ(item) + " ?";
		}
        prompt += " ��Ϊ��Ҫ���������Ʒ�ʣ��ҽ�����ȷ�������ռ��㹻��#b\r\n";

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
            cm.sendOk("����׼���㹻��Ǯ�������Ұɡ�")
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
            cm.sendOk("�ܱ�Ǹ������Ĳ��ϲ��㣬�����Ҳ���������ˡ�");
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
                cm.sendNext("����������ر�����Ա");
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
            cm.sendOk("������ϡ�");
        }
        cm.dispose();
    }
}
