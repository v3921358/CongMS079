var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY1 = "┣       - 创意 -       ┫";
var CY2 = "┣ 玩法仿制  　定制脚本 ┫";
var CY3 = "┣ 技术支持 　 游戏顾问 ┫";
var CY4 = "┣ ＷＺ添加　  地图制作 ┫";
var CY5 = "┣ 加盾防御　  售登陆器 ┫";
var CY7 = "┣ 手游开服    端游开服 ┫";
var CY8 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var CY9 = "┣    唯一微信:ZerekY   ┫";
var CY0 = "┣━━━━━━━━━━━━━━ ━━━━━━━━━━━━━━━━┫";
var MapleItemInformationProvider = Java.type('server.MapleItemInformationProvider');
var MaplePacketCreator = Java.type('tools.MaplePacketCreator');

var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 礼包物品 = "#v1302000#";
var add = "#fEffect/CharacterEff/1112903/0/0#";//红桃心
var aaa = "#fUI/UIWindow.img/Quest/icon9/0#";//红色右箭头
var zzz = "#fUI/UIWindow.img/Quest/icon8/0#";//蓝色右箭头
var sss = "#fUI/UIWindow.img/QuestIcon/3/0#";//选择道具
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 感叹号 = "#fUI/UIWindow/Quest/icon0#";
var 美化new = "#fUI/UIWindow/Quest/icon5/1#";
var 红色箭头 = "#fEffect/CharacterEff/1112908/0/1#";  //彩光3
var ttt1 = "#fEffect/CharacterEff/1062114/1/0#";  //爱心
var 蓝色角点 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var 正方箭头 = "#fUI/Basic/BtHide3/mouseOver/0#";
var 爱心 = "#fEffect/CharacterEff/1022223/4/0#";
var 粉爱心 = "#fItem/Etc/0427/04270005/Icon8/1#";  //
var 菊花 = "#fUI/PredictHarmony/card/19#";//卡片效果菊花
var 笑 = "#fUI/GuildBBS/GuildBBS/Emoticon/Basic/0#";//笑脸
var 金枫叶 ="#fMap/MapHelper/weather/maple/2#";
var 红枫叶 ="#fMap/MapHelper/weather/maple/1#";
var 巫女 ="#fMap/MapHelper/weather/witch/0#";//巫女
var 气球 ="#fMap/MapHelper/weather/balloon/4#";//气球
var 射箭 ="#fMap/MapHelper/weather/LoveEffect2/4/0#";//射箭
var 玫瑰 ="#fMap/MapHelper/weather/rose/0#";//玫瑰花
var 烟花 ="#fMap/MapHelper/weather/squib/squib1/3#";//烟花

var 大粉红爱心 = "#fItem/Etc/0427/04270001/Icon8/4#";  //
var 小粉红爱心 = "#fItem/Etc/0427/04270001/Icon8/5#";  //
var 小黄星 = "#fItem/Etc/0427/04270001/Icon9/0#";  //
var 大黄星 = "#fItem/Etc/0427/04270001/Icon9/1#";  //
var 小水滴 = "#fItem/Etc/0427/04270001/Icon10/5#";  //
var 大水滴 = "#fItem/Etc/0427/04270001/Icon10/4#";  //
var tz = "#fEffect/CharacterEff/1082565/4/0#";  //粉兔子
var tz1 = "#fEffect/CharacterEff/1082565/0/0#";  //橙兔子
var tz2 = "#fEffect/CharacterEff/1082565/2/0#";  //蓝兔子
var 邪恶小兔 = "#fEffect/CharacterEff/1112960/3/0#";  //邪恶小兔 【小】
var 邪恶小兔2 = "#fEffect/CharacterEff/1112960/3/1#";  //邪恶小兔 【大】
var 花草 ="#fEffect/SetEff/208/effect/walk2/4#";
var 花草1 ="#fEffect/SetEff/208/effect/walk2/3#";
var 小花 ="#fMap/MapHelper/weather/birthday/2#";
var 桃花 ="#fMap/MapHelper/weather/rose/4#";
var 银杏叶 ="#fMap/MapHelper/weather/maple/3#";
var 小烟花 ="#fMap/MapHelper/weather/squib/squib4/1#";
var 星星 ="#fMap/MapHelper/weather/witch/3#";
var 礼包1 = "#v5680069#";
var 奖励 = "#fUI/UIWindow.img/Quest/reward#";
var 金币 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var 点券图标 = "#fUI/CashShop/CashItem/0#";
var 气球 ="#fMap/MapHelper/weather/balloon/4#";//气球



//目录界面
var fyszML = new Array(
{ 编号: 0,目录: 30 },
{ 编号: 1,目录: 100 },
{ 编号: 2,目录: 500 },
{ 编号: 3,目录: 688 },
{ 编号: 4,目录: 888 },
{ 编号: 5,目录: 1888 },
{ 编号: 6,目录: 3888 },
{ 编号: 7,目录: 5888 },
{ 编号: 8,目录: 8888 },
{ 编号: 9,目录: 12888 },
{ 编号: 10,目录: 16888 },
{ 编号: 11,目录: 20888 },
{ 编号: 12,目录: 24888 }

);

//详细参数设置  类型0=金币,类型1=点卷,类型2=抵用，数量是通用 直接物品ID自动识别装备和其他物品，交易1是上锁，0是可交易
var xmxsz = new Array(
/*{ 类型: 1112422,力量: 8, 敏捷: 8, 智力: 8, 运气: 8, HP: 80, MP: 80, 物攻: 5, 魔攻: 5, 物防: 5, 魔防: 5, 命中: 5, 回避: 5, 移动: 5, 跳跃: 5, 交易: 1, 数量: 1, 目录: 30 }*/



{ 类型: 4000423, 数量: 1, 目录: 30 },
{ 类型: 3700357, 数量: 1, 目录: 30 },
{ 类型: 4001126, 数量: 500, 目录: 30 },


{ 类型: 4000425, 数量: 1, 目录: 100 },
{ 类型: 4001126, 数量: 1000, 目录: 100 },

{ 类型: 4000424, 数量: 1, 目录: 500 },
{ 类型: 4001126, 数量: 1500, 目录: 500 },

{ 类型: 4170005, 数量: 2, 目录: 688 },
{ 类型: 4170006, 数量: 2, 目录: 688 },
{ 类型: 4001126, 数量: 100, 目录: 688 },
{ 类型: 4000313, 数量: 10, 目录: 688 },
{ 类型: 4000463, 数量: 10, 目录: 688 },

{ 类型: 4170005, 数量: 3, 目录: 888 },
{ 类型: 4170006, 数量: 3, 目录: 888 },
{ 类型: 2340000, 数量: 1, 目录: 888 },
{ 类型: 2049118, 数量: 1, 目录: 888 },
{ 类型: 4001126, 数量: 200, 目录: 888 },
{ 类型: 4000313, 数量: 20, 目录: 888 },
{ 类型: 4000463, 数量: 20, 目录: 888 },

{ 类型: 2340000, 数量: 2, 目录: 1888 },
{ 类型: 2049118, 数量: 2, 目录: 1888 },
{ 类型: 4001126, 数量: 300, 目录: 1888 },
{ 类型: 4000313, 数量: 30, 目录: 1888 },
{ 类型: 4000463, 数量: 30, 目录: 1888 },
{ 类型: 4251200, 数量: 1, 目录: 1888 },

{ 类型: 4310034, 数量: 3, 目录: 3888 },
{ 类型: 4310029, 数量: 3, 目录: 3888 },
{ 类型: 2340000, 数量: 3, 目录: 3888 },
{ 类型: 2049118, 数量: 3, 目录: 3888 },
{ 类型: 4310148, 数量: 1, 目录: 3888 },
{ 类型: 4001126, 数量: 400, 目录: 3888 },
{ 类型: 4000313, 数量: 50, 目录: 3888 },
{ 类型: 4000463, 数量: 40, 目录: 3888 },
{ 类型: 4310143, 数量: 1, 目录: 3888 },
{ 类型: 4251200, 数量: 2, 目录: 3888 },

{ 类型: 2340000, 数量: 5, 目录: 5888 },
{ 类型: 2049118, 数量: 5, 目录: 5888 },
{ 类型: 4310148, 数量: 2, 目录: 5888 },
{ 类型: 4310034, 数量: 5, 目录: 5888 },
{ 类型: 4310029, 数量: 5, 目录: 5888 },
{ 类型: 4001126, 数量: 500, 目录: 5888 },
{ 类型: 4000313, 数量: 100, 目录: 5888 },
{ 类型: 4000463, 数量: 50, 目录: 5888 },
{ 类型: 4310143, 数量: 2, 目录: 5888 },
{ 类型: 4251200, 数量: 3, 目录: 5888 },

{ 类型: 4000463, 数量: 70, 目录: 8888 },
{ 类型: 4000313, 数量: 150, 目录: 8888 },
{ 类型: 4000038, 数量: 100, 目录: 8888 },
{ 类型: 4310143, 数量: 3, 目录: 8888 },
{ 类型: 4310148, 数量: 3, 目录: 8888 },
{ 类型: 4251201, 数量: 1, 目录: 8888 },
{ 类型: 1, 数量: 2000000, 目录: 8888 },

{ 类型: 4170005, 数量: 5, 目录: 12888 },
{ 类型: 4170006, 数量: 5, 目录: 12888 },
{ 类型: 4310012, 数量: 1, 目录: 12888 },
{ 类型: 4000463, 数量: 90, 目录: 12888 },
{ 类型: 4000313, 数量: 200, 目录: 12888 },
{ 类型: 4000038, 数量: 150, 目录: 12888 },
{ 类型: 4310143, 数量: 5, 目录: 12888 },
{ 类型: 4310148, 数量: 4, 目录: 12888 },
{ 类型: 4251201, 数量: 3, 目录: 12888 },
{ 类型: 1, 数量: 4000000, 目录: 12888 },

{ 类型: 4310012, 数量: 2, 目录: 16888 },
{ 类型: 4000463, 数量: 110, 目录: 16888 },
{ 类型: 4000313, 数量: 260, 目录: 16888 },
{ 类型: 4000038, 数量: 200, 目录: 16888 },
{ 类型: 4310143, 数量: 6, 目录: 16888 },
{ 类型: 4310148, 数量: 5, 目录: 16888 },
{ 类型: 4251202, 数量: 1, 目录: 16888 },
{ 类型: 1, 数量: 6000000, 目录: 16888 },

{ 类型: 4170005, 数量: 6, 目录: 20888 },
{ 类型: 4170006, 数量: 6, 目录: 20888 },
{ 类型: 4310012, 数量: 3, 目录: 20888 },
{ 类型: 4000463, 数量: 150, 目录: 20888 },
{ 类型: 4000313, 数量: 300, 目录: 20888 },
{ 类型: 4000038, 数量: 260, 目录: 20888 },
{ 类型: 4310143, 数量: 7, 目录: 20888 },
{ 类型: 4310148, 数量: 6, 目录: 20888 },
{ 类型: 4251202, 数量: 2, 目录: 20888 },
{ 类型: 1, 数量: 8000000, 目录: 20888 },

{ 类型: 4310012, 数量: 4, 目录: 24888 },
{ 类型: 4000463, 数量: 200, 目录: 24888 },
{ 类型: 4000313, 数量: 300, 目录: 24888 },
{ 类型: 4000038, 数量: 300, 目录: 24888 },
{ 类型: 4310143, 数量: 8, 目录: 24888 },
{ 类型: 4310148, 数量: 7, 目录: 24888 },
{ 类型: 4251202, 数量: 3, 目录: 24888 },
{ 类型: 4310059, 数量: 1, 目录: 24888 },
{ 类型: 1, 数量: 10000000, 目录: 24888 },

{ 类型: 2, 数量: 12644, 目录: 9999999 }
);

var 当前充值 = 0;
var 领取次数 = 0;
var 选中 = -1;
var DHDCLX = -1;
function start() {
	status = -1;

	action(1, 0, 0);
}

function action(mode, type, selection) {

	if (mode == 1) {
		status++;
	} else if (mode == 0 && status != 0) {
		status--;
	} else {
		cm.dispose();
		return;
	}

	if (status == 0) {
		//总计 = cm.getHyPay(2)+cm.getHyPay(1);
		当前充值 = cm.getBossRankCount8("赞助提现积分");
		领取次数 = cm.getBossRankCount8("礼包次数");
		if (领取次数 < 0) {
				领取次数 = 0
			}
		if (当前充值 < 0) {
				当前充值 = 0
			}
		var 第二 = 领取次数;
		var 第二个 = ++第二;
		MapleItemInformationProvider = Packages.server.MapleItemInformationProvider;
		var text = "";
		text += "\t\t\t\t#e#d#v5120030#赞助大礼包#v5120030#\r\n\r\n";
		s = 0;
		for (var c = 0; c < fyszML.length; c++) { //

			//if (fyszML[c].编号 >= 领取次数) {
			if ((fyszML[c].编号 == 领取次数)||(fyszML[c].编号 == 第二个)) {
				text += "#L" + fyszML[c].编号 + "#"+星星+"#e#d累计赞助满  [#r" + 当前充值 + "#d/" + fyszML[c].目录 + "]\r\n\r\n"
              s++;
			}

		}
		if(s==0){
		cm.sendOk("全部领完了");
			cm.dispose();
			return;	
		}
		cm.sendSimple(text);
	} else if (status == 1) {
		DHDCLX = fyszML[selection].目录;

		var selStr = ""+奖励+"\r\n";
		for (var i = 0; i < xmxsz.length; i++) { //需要固定材料
			if (xmxsz[i].目录 == DHDCLX) {
				if (xmxsz[i].类型 == 0) { //金币
					selStr += ""+金币+"#r 金币 X " + xmxsz[i].数量 + "\r\n";
				} else if (xmxsz[i].类型 == 1) { //点卷
					selStr += ""+点券图标+"#r 点卷 X " + xmxsz[i].数量 + "\r\n";
				} else if (xmxsz[i].类型 == 2) { //抵用券
					selStr += ""+点券图标+"#r 抵用卷 X " + xmxsz[i].数量 + "\r\n";
				} else { //物品

					var sx = "";
					if (xmxsz[i].力量 != 0) {
						sx += " 力量+" + xmxsz[i].力量;
					}
					if (xmxsz[i].敏捷 != 0) {
						sx += " 敏捷+" + xmxsz[i].敏捷;
					}
					if (xmxsz[i].敏捷 != 0) {
						sx += " 智力+" + xmxsz[i].敏捷;
					}
					if (xmxsz[i].运气 != 0) {
						sx += " 运气+" + xmxsz[i].运气;
					}
					if (xmxsz[i].HP != 0) {
						sx += " HP+" + xmxsz[i].HP;
					}
					if (xmxsz[i].MP != 0) {
						sx += " MP+" + xmxsz[i].MP;
					}
					if (xmxsz[i].物攻 != 0) {
						sx += " 物攻+" + xmxsz[i].物攻;
					}
					if (xmxsz[i].魔攻 != 0) {
						sx += " 魔攻+" + xmxsz[i].魔攻;
					}

					if (xmxsz[i].物防 != 0) {
						sx += " 物防+" + xmxsz[i].物防;
					}

					if (xmxsz[i].魔防 != 0) {
						sx += " 魔防+" + xmxsz[i].魔防;
					}
					if (xmxsz[i].命中 != 0) {
						sx += " 命中+" + xmxsz[i].命中;
					}
					if (xmxsz[i].回避 != 0) {
						sx += " 回避+" + xmxsz[i].回避;
					}
					if (xmxsz[i].移动 != 0) {
						sx += " 移动+" + xmxsz[i].移动;
					}
					if (xmxsz[i].跳跃 != 0) {
						sx += " 跳跃+" + xmxsz[i].跳跃;
					}
					//var ii = net.sf.cherry.server.MapleItemInformationProvider.getInstance();		
					var ii = MapleItemInformationProvider.getInstance();
					var type = ii.getInventoryType(xmxsz[i].类型); 

					if(type == 1){
						selStr += "#r#i" + xmxsz[i].类型 + "##z" + xmxsz[i].类型 + "##k X " + xmxsz[i].数量 + "\r\n" + sx + "\r\n";
					} else {
						selStr += "#r#i" + xmxsz[i].类型 + "##z" + xmxsz[i].类型 + "##k X " + xmxsz[i].数量 + "\r\n";
					}
					

				}

			}
		}

		

		选中 = selection;
		cm.sendYesNo(selStr);
	} else if (status == 2) {
		
		if (cm.getInventory(1).isFull(3) || cm.getInventory(2).isFull(3) || cm.getInventory(3).isFull(3) || cm.getInventory(4).isFull(3)) {
			cm.sendOk("#b请保证全体背包4个格子,否则无法兑换.");
			cm.dispose();
			return;
		} else if (领取次数 != 选中) { //
			cm.sendOk("#b请按顺序一个一个领取" );
			cm.dispose();
			return;
		} else if (DHDCLX > 当前充值) { //
			cm.sendOk("#还没消费满[#r" + 当前充值 + "#d/" + DHDCLX + "]");
			cm.dispose();
			return;
		} else {
			//----------------------------------------------------------------------
			for (var i = 0; i < xmxsz.length; i++) {			
				if (xmxsz[i].目录 == DHDCLX) {
					
					//------------------------------------------------------
					if (xmxsz[i].类型 == 0) { //金币
						cm.gainMeso(+xmxsz[i].数量); //扣除多少金币
					} else if (xmxsz[i].类型 == 1) { //点卷
						cm.getPlayer().modifyCSPoints(1, +xmxsz[i].数量, true); //点券
					} else if (xmxsz[i].类型 == 2) { //抵用券
						cm.getPlayer().modifyCSPoints(2, +xmxsz[i].数量, true); //抵用券
					} else { //物品
						if (MapleItemInformationProvider.getInstance().getInventoryType(xmxsz[i].类型).getType() == 1) {
						var ii = MapleItemInformationProvider.getInstance();	
						var toDrop = ii.randomizeStats(ii.getEquipById(xmxsz[i].类型)).copy();
						var type = ii.getInventoryType(xmxsz[i].类型); //获得装备的类形
							
							
							//var type = ii.getInventoryType(xmxsz[i].类型); //获得装备的类形
							//var toDrop = ii.randomizeStats(ii.getEquipById(xmxsz[i].类型)).copy(); // 生成一个Equip类
							//Array(物品id，力量 ，敏捷 ，智力 ，运气，物理攻击 ，魔法攻击 ，魔法防御， 物理防御， 编号 ),
							toDrop.setFlag(xmxsz[i].交易); //上锁不能与时间一起，否则会BUG不消失//上锁
							if (xmxsz[i].力量 != 0) {
								toDrop.setStr(xmxsz[i].力量); //给力量
							}

							if (xmxsz[i].敏捷 != 0) {
								toDrop.setDex(xmxsz[i].敏捷); //给敏捷
							}

							if (xmxsz[i].智力 != 0) {
								toDrop.setInt(xmxsz[i].智力); //给智力
							}

							if (xmxsz[i].运气 != 0) {
								toDrop.setLuk(xmxsz[i].运气); //给运气
							}
							
							if (xmxsz[i].HP != 0) {
								toDrop.setHp(xmxsz[i].HP); //HP
							}
							if (xmxsz[i].MP != 0) {
								toDrop.setMp(xmxsz[i].MP); //MP
							}

							if (xmxsz[i].物攻 != 0) {
								toDrop.setWatk(xmxsz[i].物攻); //攻击力
							}

							if (xmxsz[i].魔攻 != 0) {
								toDrop.setMatk(xmxsz[i].魔攻); //魔法力
							}

							if (xmxsz[i].物防 != 0) {
								toDrop.setWdef(xmxsz[i].物防); //物理防御
							}

							if (xmxsz[i].魔防 != 0) {
								toDrop.setMdef(xmxsz[i].魔防); //魔法防御
							}
							
							if (xmxsz[i].命中 != 0) {
								toDrop.setAcc(xmxsz[i].命中); //命中
							}
							
							if (xmxsz[i].回避 != 0) {
								toDrop.setAvoid(xmxsz[i].回避); //回避率
							}

							

							

							if (xmxsz[i].跳跃 != 0) {
								toDrop.setSpeed(xmxsz[i].跳跃); //跳跃
							}

							if (xmxsz[i].移动 != 0) {
								toDrop.setJump(xmxsz[i].移动); //移动
							}
							cm.addFromDrop(toDrop);
							//cm.itemlaba("【赞助礼包】","恭喜玩家 " + cm.getPlayer().getName() + " 领取了累积 "+DHDCLX+" 赞助奖励礼包！", toDrop, 15);
							
							
							//item.getItemId();

						} else {
							cm.gainItem(xmxsz[i].类型, xmxsz[i].数量);
							//cm.itemlabaB("【赞助礼包】", "恭喜玩家 " + cm.getPlayer().getName() + " 领取了累积 "+DHDCLX+" 赞助奖励礼包！", xmxsz[i].类型, 15);
						}

					}

				}
			}
			cm.setBossRankCount8("礼包次数", +1);
			cm.sendOk("#b成功了.");
			cm.dispose();

		}

	}

}
function getlibao() {
	accid = cm.getPlayer().getAccountID();
	xmfhz = 0;
	var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "SELECT * FROM accounts WHERE id = "+accid+"   ;";
	var pstmt = conn.prepareStatement(sql);
	var result = pstmt.executeQuery();
	if (result.next()) {
	xmfhz = result.getString("libao");
	}
	result.close();
	pstmt.close();	
	return xmfhz;
}

function gainlibao(xiezhi) {
	accid = cm.getPlayer().getAccountID();
    var conn = Packages.database.DatabaseConnection.getConnection();
	var sql = "UPDATE accounts SET libao = libao+"+xiezhi+"  WHERE id = "+accid+"  ;";
    var pstmt = conn.prepareStatement(sql);
	pstmt.executeUpdate();
	pstmt.close();
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