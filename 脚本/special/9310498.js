var ttt6 = "#fUI/UIWindow.img/PvP/Scroll/enabled/next2#";
var time = new Date();
var hour = time.getHours(); //获得小时
var minute = time.getMinutes(); //获得分钟
var second = time.getSeconds(); //获得秒
var Year = time.getFullYear();
var month = time.getMonth() + 1; //获取当前月份(0-11,0代表1月)
var dates = time.getDate(); //获取当前日(1-31)
var status = -1;
var rand = 0;
var InsertData = false;
var nx = false;
var nxx = false;
var RMB = 0;

function action(mode, type, selection) {
    if (mode == 1) {
        status++;
    } else {
        if (status == 0) {
            cm.dispose();
        }
        status--;
    }
    if (status == 0) {
        var RankDataBase = cm.getConnection().prepareStatement("SELECT * FROM Activity0512 ORDER BY id desc LIMIT 5").executeQuery();
        var text = ""
        var i = 1;
        text += "#d#e最新抽中大奖信息：#k#n#r(2余额抽1次,每抽一次返100点卷)#k\r\n\r\n-----------------------------------------------\r\n"
        while (RankDataBase.next()) {
            text += "#fUI/UIToolTip.img/Item/Equip/Star/Star# #r" + RankDataBase.getString("charName") + "#k 在 #b" + RankDataBase.getString("time") + "#k 抽中 #r" + RankDataBase.getString("itemid") + "#k"
            text += "\r\n"
            i++;
        }
        text += "-----------------------------------------------\r\n#L0##b【可抽 #r" + (cm.getHyPay(1)) + " #b次】#l #L1##d【奖品展览】#l #L2##r【次数领奖】#l#k \r\n\r\n \r\n"
        cm.sendSimple(text);

    } else if (status == 1) {
        if (selection == 0) {
				var ii = cm.getItemInfo();
            if (cm.getHyPay(1) >=2) {
                var item;
                var itemListAdcanced = Array(





			//3016101, //怪物水晶球秋千椅

			//3015304, //哗啦啦大水车
                        //3015002, //七彩摩天轮
                        //3010832, //太阳椅子
                        1102481, //暴君西亚戴斯披风 - (无描述)
                        1102482, //暴君赫尔梅斯披风 - (无描述
                        1102483, //暴君凯伦披风 - (无描述)
                        1102484, //暴君利卡昂披风 - (无描述)
                        1082543, //暴君西亚戴斯手套 - (无描述)
                        1102485, //暴君阿尔泰披风 - (无描述)
                        1082543, //暴君西亚戴斯手套 - (无描述)
                        1082544, //暴君赫尔梅斯手套 - (无描述)
                        1082545, //暴君凯伦手套 - (无描述)
                        1082546, //暴君利卡昂手套 - (无描述)
                        1082547, //暴君阿尔泰手套 - (无描述)
                        1072743, //暴君西亚戴斯靴 - (无描述)
			1182019, //新彩虹徽章 - 蕴含着彩虹的神秘力量的徽章。
                        1072744, //暴君赫尔梅斯靴 - (无描述)
                        1072745, //暴君凯伦靴 - (无描述)
                        1072746, //暴君利卡昂靴 - (无描述)
                        1072747, //暴君阿尔泰靴 - (无描述)
                        1132174, //暴君西亚戴斯腰带 - (无描述)
                        1132175, //暴君赫尔梅斯腰带 - (无描述)
                        1132176, //暴君凯伦腰带 - (无描述)
                        1132177, //暴君利卡昂腰带 - (无描述)
                        1132178, //暴君阿尔泰腰带 - (无描述)
                        1142742, //冒险岛奖杯

                        1112793, //快乐指环
                        2431938, //法弗纳武器箱
                        1122122, //真·觉醒冒险之心
                        1122123, //真·觉醒冒险之心
                        1122124, //真·觉醒冒险之心
                        1122125, //真·觉醒冒险之心
                        1122126, //真·觉醒冒险之心
                        1122266, //高级贝勒德刻印吊坠
                        //1122267, //最高级贝勒德刻印吊坠
                        1113074, //高级贝勒德戒指
                        //1113075, //最高级贝勒德戒指 
                        1132245, //高级贝勒德刻印腰带 
                        //1132246, //最高级贝勒德刻印腰带
                        1032222, //高级贝勒德耳环           
                        //1032223, //最高级贝勒德耳环
                        1032219//遗忘之神话耳环
                        );

                var itemListAdcanced22 = Array(

					3015274, //国庆节喷泉椅子
					3015303, //幸福的花鞋椅子
					3015328, //冒险岛电动缆车椅子
					3015329, //妖怪坛椅子
					3015338, //秘密花园椅子
					3015312, //农夫的乐园椅子
					3015029, //宇宙冲撞椅
					3014010, //捕获僵尸拍立得
                    3010853, //心花怒放椅子
                    3015130, //土豪婴儿推车
                    3015131, //巨大鼻涕泡椅子
                    3015132, //大粽子沙发椅
					3015075, //大自然椅子
					3016000, //大象跷跷板椅子
                    3015089, //训练师庭院椅子
                    3015135, //夏日沁饮椅子
                    3015015, //白羊座椅子
                    3015016, //金牛座椅子
                    3015017, //双鱼座椅子
                    3015018, //双子座椅子
                    3015019, //巨蟹座椅子
                    3015020, //狮子座椅子
                    3015021, //天秤座椅子
                    3015022, //天蝎座椅子
                    3015023, //处女座椅子
                    3015024, //射手座椅子
                    3015025, //山羊座椅子
                        3015026, //水瓶座椅子
                        3015027, //安德洛墨达椅子
                        3015096, //羊羊幻想牧场椅子
                        3012027, //羊羊热气球椅子
                        3015051, //巨无霸国际巨星
						3015225, //和大象一起自拍
						3015182, //蝶恋花椅子
						3012028, //萌宠下午茶
						3015129, //白色天堂椅子
						3015224, //梦幻水晶椅子

			1202083, //真·伊昆图腾 - (无描述)

			1202084, //金·伊昆图腾 - (无描述)

			1202085, //银·伊昆图腾 - (无描述)

			1202086, //铜·伊昆图腾 - (无描述)

			1190300, //白银枫叶徽章

			1190301, //金色枫叶徽章 - (无描述)

			1190302, //水晶枫叶徽章 - (无描述)

			1182021, //冒险岛学校铜徽章 - (无描述)

			1182022, //冒险岛学校银徽章 - (无描述)

			1182023 //冒险岛学校金徽章 - (无描述)





                        );

                var itemListNormal = new Array(
                        5062000,
                        5062002,
			5062009,
                        5064000,
                        2049704,
			5062009,
			2049119,
			2431762,
                        2040057,
			5062009,
			2049116,
			2047978,
			5062009,
                        2040058,
                        2040059,
			5062009,
			4034151,
			5062009,
			5062024,
			5062009,
			5062009,
			5062009,
			2340000,
			4001839,
			5062009,
			2046856,
			2046863,
			5062009,
			2049131,
			5062009,
			5062009,
			2431762,
			2046870,
			5062009,
			2049100,
			2049116,
			5062009,
                        2040060,
			5062009,
			2049168,
			5062009,
                        2040061,
			5062009,
                        2040062,
                        4310088,
                        4310036,
			5062009,
                        5072000,
			5062009,
                        5073000,
			5062009,
                        5074000,
			5062009,
                        5076000,
			5062009,
                        5390000,
			5062009,
                        5390001,
			5062009,
                        5390002,
			5062009,
                        5390002,
                        5390003,
			5062009,
                        5390005,
                        5390006,
                        5150040
                        );
                var xxx = Math.floor(Math.random() * 500);
                if (xxx == 1) {//100分之1的几率
                    // if (xxx == 1 || xxx == 10) {//暴君
                    rand = Math.floor(Math.random() * itemListAdcanced.length);
                    item = cm.gainGachaponItem(itemListAdcanced[rand], 1, " 明星转盘 ");
                    InsertData = true;

                    } else if (xxx == 2 || xxx == 20) {//椅子
                    rand = Math.floor(Math.random() * itemListAdcanced22.length);
                    item = cm.gainGachaponItem(itemListAdcanced22[rand], 1, " 明星转盘 ");
                    InsertData = true;

                } else if (xxx == 21 || xxx == 29) {//点券
                    cm.gainNX(1, 100000);
                    InsertData = true;
                    nx = true;
                } else if (xxx == 30 || xxx == 80) {//抵用券
                    cm.gainNX(2, 100000);
                    InsertData = true;
                    nxx = true;
                } else {
                    rand = Math.floor(Math.random() * itemListNormal.length);
                    item = itemListNormal[rand];
                    cm.gainItem(item, 1); //直接给予物品 不公告。
                }
                if (item == -1) {
                    cm.sendOk("对不起，你的背包已经满了。");
                    cm.dispose();
                } else {
                    cm.addHyPay(2);
		    cm.gainNX(1, 100);
		    cm.setPQLog("当天明星抽奖");
		    cm.setPQLog("累计明星抽奖", 1);
                    if (nx) {
                        var insert = cm.getConnection().prepareStatement("INSERT INTO Activity0512 (id,itemid,charid,charName,time) VALUES(?,?,?,?,?)"); // 载入数据
                        insert.setString(1, null); //载入记录ID
                        insert.setString(2, "100,000 点券"); //载入记录ID
                        insert.setString(3, cm.getPlayer().getId());
                        insert.setString(4, cm.getPlayer().getName());
                        insert.setString(5, Year + "-" + month + "-" + dates + "");
                        insert.executeUpdate(); //更新
                        cm.getMap().startMapEffect("恭喜玩家 " + cm.getChar().getName() + " 运气爆表抽中 100,000 点券。", 5120012);
                        cm.worldSpouseMessage(0x0F,  "『终极大奖』 :  恭喜" + cm.getChar().getName() + ",运气爆表抽中 100,000 点券。");
                        cm.sendOk("恭喜您从幸运抽奖中获得了 #b100,000 点卷#k.");
                        cm.safeDispose();
                    } else if (nxx) {
                        var insert = cm.getConnection().prepareStatement("INSERT INTO Activity0512 (id,itemid,charid,charName,time) VALUES(?,?,?,?,?)"); // 载入数据
                        insert.setString(1, null); //载入记录ID
                        insert.setString(2, "100,000 抵用卷"); //载入记录ID
                        insert.setString(3, cm.getPlayer().getId());
                        insert.setString(4, cm.getPlayer().getName());
                        insert.setString(5, Year + "-" + month + "-" + dates + "");
                        insert.executeUpdate(); //更新
                        //cm.getMap().startMapEffect("恭喜玩家 " + cm.getChar().getName() + " 运气爆表抽中 100,000 抵用卷。", 5120012);
                        cm.worldSpouseMessage(0x0F, "『终极大奖』 :  恭喜" + cm.getChar().getName() + ",运气爆表抽中100,000 抵用券。");
                        cm.sendOk("恭喜您从幸运抽奖中获得了 #b100,000 抵用券#k.");
                        cm.safeDispose();
                    } else {
                        if (InsertData) {
                            var insert = cm.getConnection().prepareStatement("INSERT INTO Activity0512 (id,itemid,charid,charName,time) VALUES(?,?,?,?,?)"); // 载入数据
                            insert.setString(1, null); //载入记录ID
                            insert.setString(2, "#t" + item + "#"); //载入记录ID
                            insert.setString(3, cm.getPlayer().getId());
                            insert.setString(4, cm.getPlayer().getName());
                            insert.setString(5, Year + "-" + month + "-" + dates + "");
                            insert.executeUpdate(); //更新
							//cm.getMap().startMapEffect("恭喜玩家 " + cm.getChar().getName() + " 运气爆表抽中终极大奖 " + ii.getName(item) + "。", 5120012);
                        }
                        cm.sendOk("恭喜您抽中获得了 #b#t" + item + "##k.并返还 #r100#k 点卷");
                        cm.safeDispose();
                    }

                }
            } else {
                cm.sendOk("您没有 2 余额，请充值后获得。");//暂时关闭。增加物品中。
                cm.safeDispose();
            }
        } else if (selection == 1) {
            cm.sendOk("#b终极大奖图片欣赏，并且抽中后可以在首页榜上公布。\r\n可以抽中 #r点卷100,000   抵用卷500,000#k #b以及下列：#k\r\n#i3010853##i3015130##i3015131##i3015132##i3015089##i3015135##i3015015##i3015016##i3015017##i3015018##i3015019##i3015020##i3015021##i3015022##i3015023##i3015024##i3015025##i3015026##i3015027##i3015096##i3012027##i3015051##i3015002##i3010832##i1102481##i1102482##i1102483##i1102484##i1102485##i1082543##i1082544##i1082545##i1082546##i1082547##i1072743##i1072744##i1072745##i1072746##i1072747##i1132174##i1132175##i1132176##i1132177##i1132178##i1142742##i1112793##i2431938##i1032219#");
            cm.dispose();
        } else if (selection == 2) {
			//cm.sendOk("暂时有问题待修复开放。具体等通知。")
            cm.dispose();
			cm.openNpc(9310498, "cslj");
        }
    }
}




/*var epp = "#fEffect/SetItemInfoEff/298/0#";  //蓝色四叶草
var ax = "#fEffect/CharacterEff/1112903/0/1#";  //红色爱心
var xxx = "#fEffect/CharacterEff/1032054/0/0#";  //选项两边


var a = 0;
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
var selStr = "#b#e" + epp + "" + ax + "抽奖中心" + ax + "" + epp + "#l\r\n";
        selStr +="#e#r#L7#"+xxx+"终极大奖"+xxx+"#l  #L9#"+xxx+"一元大奖"+xxx+"#l  #L8#"+xxx+"金猪抽奖"+xxx+"#l#n\r\n";
		selStr +="#e#r#L3#"+xxx+"坐骑抽奖"+xxx+"#l  #L2#"+xxx+"皮肤抽奖"+xxx+"#l  #L1#"+xxx+"椅子抽奖"+xxx+"#l#n\r\n";
		selStr +="#e#r#L5#"+xxx+"转蛋抽奖"+xxx+"#l  #L10#"+xxx+"祖母绿箱"+xxx+"#l  #L6#"+xxx+"灵魂结晶"+xxx+"#l#n\r\n";
		selStr +="\r\n";
selStr +="\r\n";;

		selStr += "\r\n" + epp + "" + epp + "" + epp + "" + epp + ""


        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
			case 1:
            cm.dispose();
            cm.openNpc(2470018,"yzcj"); //其他商城
            break;
			case 2:
            cm.dispose();
            cm.openNpc(2470018,"shpf"); //其他商城
            break;
			case 3:
            cm.dispose();
            cm.openNpc(9310475,"zqcj2"); //其他商城
            break;
			case 4:
            cm.dispose();
            cm.openNpc(2140005,"zmlbxcj"); //其他商城
            break;
			case 5:
            cm.dispose();
             cm.warp(749050400);//其他商城
            break;
			case 6:
            cm.dispose();
            cm.openNpc(2470018,"jjcj");
            break;
			case 7:
            cm.dispose();
	//cm.sendOk("数据维护，暂停使用，公测再开启！");
            cm.openNpc(9000269,"xjcj"); //其他商城
            break;
			case 8:
            cm.dispose();
		cm.openNpc(9310022);//金猪抽奖
            //cm.warp(749050400);
			 break;
			case 9:
            cm.dispose();
            cm.openNpc(9000269,"yycj"); //其他商城
            break;
			case 10:
            cm.dispose();
            cm.openNpc(9000269,"zmlx"); //其他商城
            break;
			}
    }
}

*/