/* Author: Xterminator
	NPC Name: 		Nautilus' Mid-Sized Taxi
	Map(s): 		Victoria Road : Nautilus Harbor (120000000)
	Description: 		Nautilus Harbor Taxi
*/

var status = -1;
var maps = Array(104000000, 102000000, 100000000, 103000000, 101000000);
var rCost = Array(1200, 1000, 1000, 1200, 1000);
var costBeginner = Array(120, 100, 100, 120, 100);
var cost = new Array("1,200", "1,000", "1,000", "1,200", "1,000");
var show;
var sCost;
var selectedMap = -1;

function action(mode, type, selection) {
    if (mode == 1) {
	status++;
    } else {
	if (status >= 2) {
	    cm.sendNext("有很多看到在這個鎮上了。回來找我們，當你需要去不同的鎮.");
	    cm.safeDispose();
	    return;
	}
	status--;
    }

    if (status == 0) {
	cm.sendNext("您好~! 鯨魚號計程車. 想要往其他村莊安全又快速的移動嗎? 如果是這樣 為了優先考量滿足顧客, 請使用 #b#p1092014##k 親切的送你到想要到達的地方！");
    } else if (status == 1) {
	if (cm.getJob() == 0) {
	    var selStr = "我們有特殊90%折扣，對於新手選擇你的目的地#b \n\r請選擇目的地.#b";
	    for (var i = 0; i < maps.length; i++) {
		selStr += "\r\n#L" + i + "##m" + maps[i] + "# (" + costBeginner[i] + " 楓幣)#l";
	    }
	} else {
	    var selStr = "請選擇目的地.#b";
	    for (var i = 0; i < maps.length; i++) {
		selStr += "\r\n#L" + i + "##m" + maps[i] + "# (" + cost[i] + " 楓幣)#l";
	    }
	}
	cm.sendSimple(selStr);
    } else if (status == 2) {
	if (cm.getJob() == 0) {
	    sCost = costBeginner[selection];
	    show = costBeginner[selection];
	} else {
	    sCost = rCost[selection];
	    show = cost[selection];
	}
	cm.sendYesNo("你在這裡沒有任何東西做，是吧? #b#m" + maps[selection] + "##k 他將花費你的 #b"+ show + " 楓幣#k.");
	selectedMap = selection;
    } else if (status == 3) {
	if (cm.getMeso() < sCost) {
	    cm.sendNext("很抱歉由於你沒有足夠的楓幣 所以你將無法乘坐出租車!");
	    cm.safeDispose();
	} else {
	    cm.gainMeso(-sCost);
	    cm.warp(maps[selectedMap], 0);
	    cm.dispose();
	}
    }
}