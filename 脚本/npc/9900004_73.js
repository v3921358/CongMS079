
var 奖励 = "#fUI/UIWindow.img/Quest/reward#";
var 金币 = "#fUI/UIWindow.img/Item/BtCoin/normal/0#";
var 点券图标 = "#fUI/CashShop/CashItem/0#";
var 气球 ="#fMap/MapHelper/weather/balloon/4#";//气球
var ItemId = Array(//材料
		//物品  需要的多少个  隶属那个装

		//如果   需要的物品id = 0 就是代表金币
		//需要的物品id = 1 就是代表点卷
		//需要的物品id = 2 就是代表抵用卷
		//Array(物品id0，力量1 ，敏捷 2，智力3 ，运气4，物理攻击5 ，魔法攻击6 ，魔法防御7， 物理防御8，HP9，MP10，跳跃力11，移动力12，给多少数量13， 多少分钟14 ),
		
		
			
			
		//萌新大礼包	
		/*Array(1302924, 5, 5, 5, 5, 30, 30, 50, 50, 50, 50, 40, 40, 1, 1),
        Array(1432000, 5, 5, 5, 5, 30, 30, 50, 50, 50, 50, 40, 40, 1, 1),
        Array(1372013, 5, 5, 5, 5, 30, 30, 50, 50, 50, 50, 40, 40, 1, 1),
		Array(1472000, 5, 5, 5, 5, 20, 20, 50, 50, 50, 50, 40, 40, 1, 1),
		Array(1332005, 5, 5, 5, 5, 20, 20, 50, 50, 50, 50, 40, 40, 1, 1),
		Array(1452002, 5, 5, 5, 5, 30, 30, 50, 50, 50, 50, 40, 40, 1, 1),
		Array(1462001, 5, 5, 5, 5, 30, 30, 50, 50, 50, 50, 40, 40, 1, 1),
		Array(1492000, 5, 5, 5, 5, 30, 30, 50, 50, 50, 50, 40, 40, 1, 1),
		Array(1482001, 5, 5, 5, 5, 30, 30, 50, 50, 50, 50, 40, 40, 1, 1),
		Array(1442000, 5, 5, 5, 5, 30, 30, 50, 50, 50, 50, 40, 40, 1, 1),
		Array(5030000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1),
		Array(1142717, 2, 2, 2, 2, 5, 5, 50, 50, 50, 50, 5, 5, 1, 1),//反挂英雄勋章*/
		Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1000000, 1),//金币100万
		Array(2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10000, 1),//抵用券20000点

			
		//Array(1002508, 5, 5, 5, 5, 2, 2, 50, 50, 50, 50, 5, 5, 1, 30),			
		Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1000000, 30),
		Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2000, 30),
		
		
		
		Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1000000, 70),
		Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3000, 70),
		//Array(2022345, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 70),
		//Array(2000019, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 70),
		
		
		
		Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1000000, 100),
		Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3000, 100),
		//Array(2049100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 100),
		//Array(2340000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 100),
		
		
		//Array(1113014,5,5,5,5,5,5,5,5,50,50,5,5, 1, 150),
		//Array(2049100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 150),
		//Array(2340000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 150),
		Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1000000, 150),
		Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5000, 150),
		
		
		Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1000000, 180),
		Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10000, 180),
		//Array(2049100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 180),
		//Array(2340000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 180),
			
		
		//Array(1113016,10,10,10,10,10,10,10,10,100,100,10,10, 1, 200),	
		//Array(2049100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 200),
		//Array(2340000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 200),
		Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2000000, 200),
		Array(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50000, 200)
		


		);
		
/* 		60分钟奖励100抵用卷OK
120分钟奖励100点卷OK
240分钟奖励1个护身符有时效三天。OK 出席
360分钟奖励2个喇叭
480分钟奖励护身符 */
		
		

var jzid = Array(//戒指id  按顺序拍下去
		//Array(多少分钟，编号 ),
		
		Array(1, 0),
		Array(30, 1),
		Array(70, 2),
		Array(100, 3),
		Array(150, 4),
		Array(180, 5),
		Array(200, 6)
		);
		
var 当前等级 = 0;
var 领取次数 = 0;
var 选中 = -1;
var 要兑换的物品id = -1;
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

		当前等级 = cm.getPlayer().getLevel();
		领取次数 = cm.getPlayer().getxmwnjl("等级奖励_领取",1,"账号");
		//领取次数 = cm.getPlayer().getxmwnjl("等级奖励_领取",1,"角色");
		s = 0;		
		var text = "";
		text += "#e#r              "+星星+"等级奖励大礼包"+星星+"\r\n\r\n"
		text += "#d当前等级："+当前等级+"  "+领取次数+"#n\r\n"
		
		
		for (var c = 0; c < jzid.length; c++) { //
			if (jzid[c][1] >= 领取次数) {
				text += "\t\t#L" + jzid[c][1] + "##e#d"+星星 +"等级 Lv." + jzid[c][0] + " 礼包"+星星 +"#l\r\n\r\n"	
                s++;				
			}
		}
		
		//text += "恭喜你已经领取了所有礼包！"
		if(s==0){
		cm.sendOk("全部领完了");
		cm.dispose();
		return;	
		}
		cm.sendSimple(text);
	} else if (status == 1) {
		mulu = selection;
		if (selection < 0) {
			cm.sendOk("#b哇~~~你已经领取了所有礼包！");
			cm.dispose();
		    return;
		} else if (selection == 100) {
						
			text = "";
			
			
			cm.sendYesNo(text);
		} else {
		
		
		要兑换的物品id = jzid[selection][0];
		选中 = selection;

		var selStr = 奖励+"\r\n";
		for (var i = 0; i < ItemId.length; i++) { //需要固定材料
			if (ItemId[i][14] == 要兑换的物品id) {

				if (ItemId[i][0] == 0) { //金币
					selStr += ""+金币+" 金币 X " + ItemId[i][13] + "\r\n";
				} else if (ItemId[i][0] == 1) { //点卷
					selStr += ""+点券图标+" 点卷 X " + ItemId[i][13] + "\r\n";
				} else if (ItemId[i][0] == 2) { //抵用券
					selStr += ""+点券图标+" 抵用卷 X " + ItemId[i][13] + "\r\n";
				} else { //物品

					var sx = "";
					if (ItemId[i][1] != 0) {
						sx += " 力量+" + ItemId[i][1];
					}
					if (ItemId[i][2] != 0) {
						sx += " 敏捷+" + ItemId[i][2];
					}
					if (ItemId[i][3] != 0) {
						sx += " 智力+" + ItemId[i][3];
					}
					if (ItemId[i][4] != 0) {
						sx += " 运气+" + ItemId[i][4];
					}
					if (ItemId[i][5] != 0) {
						sx += " 物理攻击+" + ItemId[i][5];
					}
					if (ItemId[i][6] != 0) {
						sx += " 魔法攻击+" + ItemId[i][6];
					}
					if (ItemId[i][7] != 0) {
						sx += " 魔法防御+" + ItemId[i][7];
					}
					if (ItemId[i][8] != 0) {
						sx += " 物理防御+" + ItemId[i][8];
					}

					if (ItemId[i][9] != 0) {
						sx += " HP +" + ItemId[i][9];
					}

					if (ItemId[i][10] != 0) {
						sx += " MP +" + ItemId[i][10];
					}
					if (ItemId[i][11] != 0) {
						sx += " 跳跃+" + ItemId[i][11];
					}
					if (ItemId[i][12] != 0) {
						sx += " 移动+" + ItemId[i][12];
					}
					selStr += "#i" + ItemId[i][0] + "##z" + ItemId[i][0] + "##k * " + ItemId[i][13] + "\r\n" + sx + "\r\n";

				}

			}
		}

		//selStr += "#k------------------------------------------------";
		cm.sendYesNo(selStr);
		}
	} else if (status == 2) {
		if (mulu == 100) {
			if (cm.getPlayer().getxmwnjl("萌新礼包领取",1,"账号") == 0 ) {
			
			cm.dispose();
			return;
			} else {
			cm.sendOk("无法重复领取...");
			cm.dispose();
			return;	
			}
		}
		
		for (var i = 0; i < ItemId.length; i++) {
			if (ItemId[i][14] == 要兑换的物品id ) {
				if (ItemId[i][0] > 3 && cm.canHold(ItemId[i][0], ItemId[i][13]) == false) {
					cm.sendOk("#b请保证格子足够,否则无法兑换.");
					cm.dispose();
					return;
				}

			}
		}


		 if (领取次数 != 选中) { //
			cm.sendOk("#b请按顺序领取！先领取" + (领取次数+1) + "礼包");
			cm.dispose();
			return;
		} else if (要兑换的物品id > 当前等级) { //
			cm.sendOk("#等级不够无法领取  需要" + 要兑换的物品id + "以上");
			cm.dispose();
			return;
		} else {
			//----------------------------------------------------------------------
			for (var i = 0; i < ItemId.length; i++) {
				if (ItemId[i][14] == 要兑换的物品id) {
					//------------------------------------------------------
					if (ItemId[i][0] == 0) { //金币
						cm.gainMeso(+ItemId[i][13]);
					} else if (ItemId[i][0] == 1) { //点卷						
						cm.gainNX(+ItemId[i][13]);
					} else if (ItemId[i][0] == 2) { //抵用券
						cm.gainDY(+ItemId[i][13]);
					} else { //物品
					//"H:\MAOXAIN-FU-WU-DUAN\mSer Ver.079\mSer\server\life\MapleMonsterInformationProvider.java"
					//"H:\MAOXAIN-FU-WU-DUAN\mSer Ver.079\mSer\server\MapleItemInformationProvider.java"
						MapleItemInformationProvider = Packages.server.MapleItemInformationProvider;
						if (MapleItemInformationProvider.getInstance().getInventoryType(ItemId[i][0]).getType() == 1) {
							var ii = MapleItemInformationProvider.getInstance();
							var type = ii.getInventoryType(ItemId[i][0]); //获得装备的类形
							var toDrop = ii.randomizeStats(ii.getEquipById(ItemId[i][0])).copy(); // 生成一个Equip类
							//Array(物品id，力量 ，敏捷 ，智力 ，运气，物理攻击 ，魔法攻击 ，魔法防御， 物理防御， 编号 ),
							toDrop.setFlag(1); //上锁不能与时间一起，否则会BUG不消失//上锁
							if (ItemId[i][1] != 0) {
								toDrop.setStr(ItemId[i][1]); //给力量
							}

							if (ItemId[i][2] != 0) {
								toDrop.setDex(ItemId[i][2]); //给敏捷
							}

							if (ItemId[i][3] != 0) {
								toDrop.setInt(ItemId[i][3]); //给智力
							}

							if (ItemId[i][4] != 0) {
								toDrop.setLuk(ItemId[i][4]); //给运气
							}

							if (ItemId[i][5] != 0) {
								toDrop.setWatk(ItemId[i][5]); //攻击力
							}

							if (ItemId[i][6] != 0) {
								toDrop.setMatk(ItemId[i][6]); //魔法力
							}

							if (ItemId[i][7] != 0) {
								toDrop.setWdef(ItemId[i][7]); //物理防御
							}

							if (ItemId[i][8] != 0) {
								toDrop.setMdef(ItemId[i][8]); //魔法防御
							}

							if (ItemId[i][9] != 0) {
								toDrop.setHp(ItemId[i][9]); //HP
							}

							if (ItemId[i][10] != 0) {
								toDrop.setMp(ItemId[i][10]); //MP
							}

							if (ItemId[i][11] != 0) {
								toDrop.setJump(ItemId[i][11]); //跳跃
							}

							if (ItemId[i][12] != 0) {
								toDrop.setSpeed(ItemId[i][12]); //移动
							}
						
							cm.addFromDrop(toDrop);
							
						} else {
							cm.gainItem(ItemId[i][0], ItemId[i][13]);
							
						}

					}

				}
			}
			//------------------------------------------------------------------
			//cm.getPlayer().setOneTimeLog("等级奖励_领取");
			cm.worldMessage(11, cm.getC().getChannel(),"【等级礼包奖励】" + " : " + " [" + cm.getPlayer().getName() + "]领取了等级奖励!"+要兑换的物品id+"级礼包", true);
			cm.getPlayer().setxmwnjl("等级奖励_领取",1,领取次数+1,"账号");
			cm.sendOk("#b成功了.");
			cm.dispose();

		}

	}

}
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