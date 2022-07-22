var 红色箭头 = "#fUI/UIWindow/Quest/icon6/7#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 点券图标 = "#fUI/CashShop/CashItem/0#";
var status = 0;
//普通奖池
var itemList1 = [
//[ID, 概率(1-100%), 数量, 是否喇叭(1为抽到该物品就广播,0抽到不广播)]
[1102354, 1, 1,1],
//紫金武器
[4000100, 30, 1,1],
[4000109, 50, 1, 1],
[4000476, 50, 1, 1],
[4000101, 50, 1, 1],
[4000127, 50, 1, 1],
[4032391, 50, 1, 1],
[4032392, 50, 1, 1],
[4001230, 50, 1, 1],
[4001228, 50, 1, 1],
[4001227, 50, 1, 1],
[4001226, 50, 1, 1],
[4001229, 50, 1, 1],
[2614000, 20, 1, 1],
[4250802, 5, 1, 1],
[4250902, 5, 1, 1],
[4251002, 5, 1, 1],
[4251102, 5, 1, 1],
[4251202, 5, 1, 1],
[4251302, 5, 1, 1],
[4251402, 5, 1, 1],
[4250001, 10, 1, 1],
[4250101, 10, 1, 1],
[4250201, 10, 1, 1],
[4250301, 10, 1, 1],
[4250401, 10, 1, 1],
[4250501, 10, 1, 1],
[4250601, 10, 1, 1],
[4250701, 10, 1, 1],
[4250801, 10, 1, 1],
[4250901, 10, 1, 1],
[4251001, 10, 1, 1],
[4251101, 10, 1, 1],
[4251201, 10, 1, 1],
[4251301, 10, 1, 1],
[4251401, 10, 1, 1],
[4250000, 15, 1, 1],
[4250100, 15, 1, 1],
[4250200, 15, 1, 1],
[4250300, 15, 1, 1],
[4250400, 15, 1, 1],
[4250500, 15, 1, 1],
[4250600, 15, 1, 1],
[4250700, 15, 1, 1],
[4250800, 15, 1, 1],
[4250900, 15, 1, 1],
[4251000, 15, 1, 1],
[4251100, 15, 1, 1],
[4251200, 15, 1, 1],
[4251300, 15, 1, 1],
[4251400, 15, 1, 1],


[1332055, 15, 1, 1],
[1482022, 15, 1, 1],
[1372034, 15, 1, 1],
[1382039, 15, 1, 1],
[1402039, 15, 1, 1],
[1412027, 15, 1, 1],
[1432040, 15, 1, 1],
[1452045, 15, 1, 1],
[1462040, 15, 1, 1],
[1472055, 15, 1, 1],
[1442024, 15, 1, 1],
[1312056, 15, 1, 1],
[1492095, 15, 1, 1],
[1302064, 15, 1, 1],
[1052202, 15, 1, 1],
[1072533, 15, 1, 1],
[1102071, 15, 1, 1],
[1082440, 15, 1, 1],
[1003454, 15, 1, 1],
[1092030, 15, 1, 1],
[1092045, 15, 1, 1],
[1092046, 15, 1, 1],
[1092047, 15, 1, 1],



[3600001, 50, 1, 1],
[3600001, 50, 1, 1]





];

var useNx = 1;
var sel0 = -1;
function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (status == 0 && mode == 0) {
        cm.dispose();
        return;
    }
    if (mode == 1) {
        status++;
    } else {
        status--;
    }

    if (status == 0) {
    	var txt = "#d                   六一活动礼包\r\n";
		
    	txt += "             #r#L1##b《《《点击抽奖》》》#l\r\n\r\n";
		txt += "\t#b"+正方箭头+"奖品展示： \r\n";
		
		var txt2 = "";
		for (var i = 0; i < itemList1.length;  i++){
			txt2 += "#i"+itemList1[i][0]+":#";
		}
    	cm.sendSimple(txt + txt2);//可抽奖页面查看奖品
		cm.sendSimple(txt );
    } else if (status == 1) {
		元宝 = getmoneyb();
		
		sel0 = selection;
		cm.sendGetNumber("#d       请输入抽奖次数\r\n"
		+"#d       元宝抽奖一元宝一次\r\n"
		//+"#r抽奖有保底，请看群文件"
		+"#r       当前拥有元宝数量"+点券图标+": "+元宝+"#k\r\n#r " ,
		1, 1, 999);//设置次数999表示可以一次性抽999次
		
	} else if(status == 2) {
		元宝 = getmoneyb();
		if (!cm.checkNumSpace(0, selection)) {
			cm.sendOk("背包空间不足"+selection+"格");
			cm.dispose();
			return;
		}
    	switch (sel0) {
			case 1:
                if (元宝 < (useNx*selection)) {
					cm.sendOk("元宝不足"+(useNx*selection)+"，无法抽奖");
					cm.dispose();
					return;
				} else {
					cm.setmoneyb(-useNx*selection);
				}
				break;
			case 2:
                if (!cm.haveItem(4001126,400*selection)) {
					cm.sendOk("#v4001126#数量不足#r "+(400*selection)+" #k，无法抽奖");
					cm.dispose();
					return;
				} else {
					cm.gainItem(4001126, -(400*selection));
				}
				break;
			default:
				cm.sendOk("脚本出错，请联系管理员");
				cm.dispose();
				return;
        }
		var txt = "恭喜你获得道具：\r\n";
		for (var i = 0; i < selection; i++) {
			var item;
			var ran = Math.floor(Math.random() * 100);
			var ran1 = null;
			ran1 = finalGift(itemList1);
			if(cm.getBossRankCount("屏蔽"+ran1[0]) > 0){
				cm.gainGachaponItem2(4001126, 1, "抽奖机", ran1[3]);
			}else{
				cm.gainGachaponItem2(ran1[0], ran1[2], "抽奖机", 1);
			}
			cm.gainItem(4000313, 1);
			
			txt += "#v" + ran1[0] + "#\r\n";
			var result = cm.setBossRankCount("随机奖池抽奖");
			
		}
		cm.sendOk(txt);
		cm.dispose();
		return;
    }
}

function finalGift(lists) {
	var maxChance = 0;
	for (var i in lists) {
		if (lists[i][1] > maxChance) {
			maxChance = lists[i][1];
		}
	}
	var chance = Math.floor(Math.random() * maxChance);
	var finalitem = Array();
	for (var i = 0; i < lists.length; i++) {
		if (lists[i][1] >= chance) {
			finalitem.push(lists[i]);
		}
	}
	var ran1 = Math.floor(Math.random() * finalitem.length);
	return finalitem[ran1];
}
function getmoney() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("money");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}

function setmoney(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET money = "+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function gainmoney(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET money = money+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}


function getmoneyb() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyb");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}
function setmoneyb(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyb = "+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}

function 充值点() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyg");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}
function 扣除充值点(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyg = moneyg-"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}





function getmoneyc() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("moneyc");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}

function gainmoneyc(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET moneyc = moneyc+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}