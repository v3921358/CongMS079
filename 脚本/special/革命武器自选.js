status = -1;
var itemList = Array(
1302289,//革命剑
1312165,//革命战斧
1402210,//革命双手剑
1412147,//革命双手战斧
1432178,//革命之矛
1442234,//革命枪

1332238,//革命切割者
1472226,//革命拳甲

1372188,//革命短杖
1382121,//革命长杖

1452216,//革命弓
1462093,//革命弩

1482179,//革命冲拳
1492190//革命红杰克

);

function start() {
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
       if (mode == 0 && status == 0) {
			im.dispose();
			return;
		}
        status--;
    }
    if (status == 0) {
        var text = "";
		for(var i=0; i<itemList.length; i++) {
			text+="#L"+i+"##v"+itemList[i]+"##z"+itemList[i]+"##l\r\n";
		}
		cm.sendSimple("请选择你需要的武器：\r\n#r"+text);
    } else if(status == 1) {
		var itemid = itemList[selection];
		var itemnum = Math.floor(Math.random()*1+1);
		//var item = im.gainGachaponItem(itemid, itemnum, "革命武器自选", 3);
		//cm.gainItem(2430026, -1);
		cm.sendOk("恭喜您，获得了"+itemnum+"个#b#z"+itemid+"#");
		cm.safeDispose();
	}
}
