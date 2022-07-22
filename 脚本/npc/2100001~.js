var status = 0;
var selectedType = -1;
var selectedItem = -1;
var item;
var mats;
var matQty;
var cost;
var qty;
var equip;
var last_use; //last item is a use item

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1)
	status++;
    else
	cm.dispose();
    if (status == 0) {
	var selStr = "我喜歡你的態度！我們只是照顧這個現在。你喜歡什麼樣的礦石提煉? #b";
	var options = new Array("提煉礦石","提煉的寶石礦","細化晶礦石");
	for (var i = 0; i < options.length; i++){
	    selStr += "\r\n#L" + i + "# " + options[i] + "#l";
	}
			
	cm.sendSimple(selStr);
    }
    else if (status == 1) {
	selectedType = selection;
	if (selectedType == 0){ //mineral refine
	    var selStr = "選擇一個你提煉的礦石?#b";
	    var minerals = new Array ("青銅","鋼鐵","鋰礦石","朱礦石","銀","紫礦石","黃金","鋰");
	    for (var i = 0; i < minerals.length; i++){
		selStr += "\r\n#L" + i + "# " + minerals[i] + "#l";
	    }
	    equip = false;
	    cm.sendSimple(selStr);
	}
	else if (selectedType == 1){ //jewel refine
	    var selStr = "選擇一個你提煉的寶石礦?#b";
	    var jewels = new Array ("石榴石","紫水晶","海藍寶石","祖母綠","蛋白石","藍寶石","黃晶","鑽石","黑水晶");
	    for (var i = 0; i < jewels.length; i++){
		selStr += "\r\n#L" + i + "# " + jewels[i] + "#l";
	    }
	    equip = false;
	    cm.sendSimple(selStr);
	}
		else if (selectedType == 2){ //Crystal refine
			var selStr = "選擇一個你細化晶礦石?#b";
			var crystals = new Array("力量水晶","智慧水晶","敏捷水晶","幸運水晶");
			for (var i = 0; i < crystals.length; i++){
				selStr += "\r\n#L" + i + "# " + crystals[i] + "#l";
			}
			cm.sendSimple(selStr);
			equip = false;
	}
	if (equip)
	    status++;
    }
    else if (status == 2 && mode == 1) {
	selectedItem = selection;
	if (selectedType == 0){ //mineral refine
	    var itemSet = new Array(4011000,4011001,4011002,4011003,4011004,4011005,4011006,4011008);
	    var matSet = new Array(4010000,4010001,4010002,4010003,4010004,4010005,4010006,4010007);
	    var matQtySet = new Array(10,10,10,10,10,10,10,10,10);
		var costSet = new Array(270,270,270,450,450,450,720,270);
	    item = itemSet[selectedItem];
	    mats = matSet[selectedItem];
	    matQty = matQtySet[selectedItem];
	    cost = costSet[selectedItem];
	}
	else if (selectedType == 1){ //jewel refine
		var itemSet = new Array(4021000,4021001,4021002,4021003,4021004,4021005,4021006,4021007,4021008);
		var matSet = new Array(4020000,4020001,4020002,4020003,4020004,4020005,4020006,4020007,4020008);
		var matQtySet = new Array(10,10,10,10,10,10,10,10,10);
		var costSet = new Array (450,450,450,450,450,450,450,900,2700);
	    item = itemSet[selectedItem];
	    mats = matSet[selectedItem];
	    matQty = matQtySet[selectedItem];
	    cost = costSet[selectedItem];
	}
	else if (selectedType == 2){ //special refine
		var itemSet = new Array(4005000,4005001,4005002,4005003);
		var matSet = new Array(4004000,4004001,4004002,4004003);
		var matQtySet = new Array(10,10,10,10);
		var costSet = new Array (4500,4500,4500,4500);
	    item = itemSet[selectedItem];
	    mats = matSet[selectedItem];
	    matQty = matQtySet[selectedItem];
	    cost = costSet[selectedItem];
	}
		
	var prompt = "所以弄了 #t" + item + "# 。你想要做多少個呢？";
		
	cm.sendGetNumber(prompt,1,1,100)
    }
	
    else if (status == 3) {
	if (equip)
	{
	    selectedItem = selection;
	    qty = 1;
	}
	else
	    qty = selection;
			
	last_use = false;
		
	if (selectedType == 3){ //claw refine
	    var itemSet = new Array (1472023,1472024,1472025);
	    var matSet = new Array(new Array (1472022,4011007,4021000,2012000),new Array (1472022,4011007,4021005,2012002),new Array (1472022,4011007,4021008,4000046));
	    var matQtySet = new Array (new Array (1,1,8,10),new Array (1,1,8,10),new Array (1,1,3,5));
	    var costSet = new Array (80000,80000,100000)
	    item = itemSet[selectedItem];
	    mats = matSet[selectedItem];
	    matQty = matQtySet[selectedItem];
	    cost = costSet[selectedItem];
	    if (selectedItem != 2)
		last_use = true;
	}
	
	var prompt = "你想要製造 ";
	if (qty == 1)
	    prompt += "一個 #t" + item + "#?";
	else
	    prompt += qty + " 個 #t" + item + "#?";
			
	prompt += " 在這種情況下，我會為了讓需要你的具體項目。請確保您有足夠的空間在您的庫存！#b";
		
	if (mats instanceof Array){
	    for (var i = 0; i < mats.length; i++) {
		prompt += "\r\n#i"+mats[i]+"# " + matQty[i] * qty + " #t" + mats[i] + "#";
	    }
	} else {
	    prompt += "\r\n#i"+mats+"# " + matQty * qty + " #t" + mats + "#";
	}
		
	if (cost > 0) {
	    prompt += "\r\n#i4031138# " + cost * qty + " 楓幣";
	}
	cm.sendYesNo(prompt);
    } else if (status == 4) {
	var complete = true;
		
	if (cm.getMeso() < cost * qty) {
	    cm.sendOk("Cash only, no credit.")
	} else {
	    if (mats instanceof Array) {
		for (var i = 0; complete && i < mats.length; i++) {
		    if (matQty[i] * qty == 1) {
			complete = cm.haveItem(mats[i]);
		    } else {
			complete = cm.haveItem(mats[i], matQty[i] * qty);
		    }
		}
	    } else {
		complete = cm.haveItem(mats, matQty * qty);
	    }
	}
		
	if (!complete)
	    cm.sendOk("我不能接受的替代品。如果你沒有什麼我需要的東西的話，我就不能來幫你.");
	else {
	    if (mats instanceof Array) {
		for (var i = 0; i < mats.length; i++){
		    cm.gainItem(mats[i], -matQty[i] * qty);
		}
	    } else {
		cm.gainItem(mats, -matQty * qty);
	    }
	    cm.gainMeso(-cost * qty);
	    cm.gainItem(item, qty);
	    cm.sendNext("嗯...我差點沒想到會工作的第二個......好吧，反正我希望你喜歡它.");
	}
	cm.dispose();
    }
}