//奖励
var gifts = [
	[
		109080000,
		"打果子",
		3,//奖池随机次数
		[//赢的奖池
			//1点券2金币3元宝4物品，数量，概率
			[1, 100, 50],
			[2, 500, 50],
			[3, 10, 50],
			[4000000, 10, 50],
		],
		[//输的奖池(非对抗活动默认使用此奖池)
			//1点券2金币3元宝4物品，数量，概率
			[1, 10, 50],
			[2, 50, 50],
			[3, 1, 50],
			[4000000, 1, 5],
		],
	],
	
	[
		109080010,
		"打瓶盖",
		10,//奖池随机次数
		[//赢的奖池
			//1点券2金币3元宝4物品，数量，概率
			//[1, 40, 50],
			[2, 30, 50],
			//[3, 20, 50],
			[4000000, 10, 50],
		],
		[//输的奖池(非对抗活动默认使用此奖池)
			//1点券2金币3元宝4物品，数量，概率
			//[1, 4, 50],
			[2, 3, 50],
			//[3, 2, 50],
			[4000000, 1, 50],
		],
	],
	
	[
		109030003,
		"爬绳子",
		1,//奖池随机次数
		[//赢的奖池
			//1点券2金币3元宝4物品，数量，概率
			[1, 4, 50],
			[2, 3, 50],
			[3, 2, 50],
			[4000000, 1, 50],
		],
		[//输的奖池(非对抗活动默认使用此奖池)
			//1点券2金币3元宝4物品，数量，概率
			[1, 4, 50],
			[2, 3, 50],
			[3, 2, 50],
			[4000000, 1, 50],
		],
	],
	
	[
		109050000,
		"终极忍耐",
		1,//奖池随机次数
		[//赢的奖池
			//1点券2金币3元宝4物品，数量，概率
			[1, 4, 50],
			[2, 3, 50],
			[3, 2, 50],
			[4000000, 1, 50],
		],
		[//输的奖池(非对抗活动默认使用此奖池)
			//1点券2金币3元宝4物品，数量，概率
			[1, 4, 50],
			[2, 3, 50],
			[3, 2, 50],
			[4000000, 1, 50],
		],
	],
	
	[
		109060000,
		"滚雪球",
		1,//奖池随机次数
		[//赢的奖池
			//1点券2金币3元宝4物品，数量，概率
			[1, 4, 50],
			[2, 3, 50],
			[3, 2, 50],
			[4000000, 1, 50],
		],
		[//输的奖池(非对抗活动默认使用此奖池)
			//1点券2金币3元宝4物品，数量，概率
			[1, 4, 50],
			[2, 3, 50],
			[3, 2, 50],
			[4000000, 1, 50],
		],
	],
	
	[
		109010206,
		"寻宝",
		1,//奖池随机次数
		[//赢的奖池
			//1点券2金币3元宝4物品，数量，概率
			[1, 4, 50],
			[2, 3, 50],
			[3, 2, 50],
			[4000000, 1, 50],
		],
		[//输的奖池(非对抗活动默认使用此奖池)
			//1点券2金币3元宝4物品，数量，概率
			[1, 4, 50],
			[2, 3, 50],
			[3, 2, 50],
			[4000000, 1, 50],
		],
	],
];
var status;
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
		var eventName = "";
		var txt = "恭喜你获得奖励：\r\n"
		var mapid = cm.getPlayer().getMap().getId();
		for (var i in gifts) {
			if (mapid == gifts[i][0]) {
				eventName = gifts[i][1];
				var xgifts = [];
				if (cm.getPlayer().getMapleEventResult() == 0) {
					xgifts = gifts[i][4]
				} else if (cm.getPlayer().getMapleEventResult() == 1) {
					xgifts = gifts[i][3]
				} else {
					cm.getPlayer().dropMessage(5, "活动奖池计算错误，地图id["+mapid+"]");
					cm.dispose();
					return;
				}
				var total = 0;
				for (var j in xgifts) {
					total += xgifts[j][2]
				}
				for (var k = 0; k < gifts[i][2]; k++) {
					var ran = Math.floor(Math.random()*total);
					var finalgift = [];
					var x = 0;
					for (var j in xgifts) {
						x += xgifts[j][2];
						if (ran < x) {
							switch(xgifts[j][0]) {
								case 1:
									cm.gainPotion(1, xgifts[j][1])
									txt += xgifts[j][1]+"点券"
									break;
								case 2:
									cm.gainMeso(xgifts[j][1])
									txt += xgifts[j][1]+"金币"
									break;
								case 3:
									setmoneyb(xgifts[j][1])
									txt += xgifts[j][1]+"元宝"
									break;
								default:
									cm.gainItem(xgifts[j][0], xgifts[j][1])
									txt += "#v"+xgifts[j][0]+"##z"+xgifts[j][0]+"# *" + xgifts[j][1]
									break;
							}
							break;
						}
					}
				}
				break;
			}
		}
		if (eventName == "") {
			cm.getPlayer().dropMessage(5, "此活动没有配置活动奖励，地图id["+mapid+"]")
		} else {
			cm.sendOk("["+eventName+"]"+txt);
		}
		cm.dispose();
    }
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
	var sql = "UPDATE accounts SET moneyb = moneyb+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
}