﻿ //符号 648215571 
var status = 0;

var eff = "#fEffect/CharacterEff/1112905/0/1#"; //
var epp = "#fEffect/CharacterEff/1082312/0/0#";  //彩光
var epp1 = "#fEffect/CharacterEff/1082312/2/0#"; //彩光1
var axx = "#fEffect/CharacterEff/1051294/0/0#";  //爱心
var xxx = "#fEffect/CharacterEff/1082565/2/0#"; //星系
var ppp = "#fEffect/CharacterEff/1112907/4/0#"; //泡炮 
var epp3 = "#fEffect/CharacterEff/1112908/0/1#";  //彩光3
var axx1 = "#fEffect/CharacterEff/1062114/1/0#";  //爱心
var zs = "#fEffect/CharacterEff/1112946/2/0#";  //砖石粉
var zs1 = "#fEffect/CharacterEff/1112946/1/1#";  //砖石蓝
var dxxx = "#fEffect/CharacterEff/1102232/2/0#"; //星系
var tz = "#fEffect/CharacterEff/1082565/2/0#";  //兔子蓝
var tz1 = "#fEffect/CharacterEff/1082565/4/0#";  //兔子粉
var tz5 = "#fUI/UIWindow2.img/QuestAlarm/BtQ/normal/0#";
var iconEvent = "#fUI/UIToolTip.img/Item/Equip/Star/Star#";
var ttt2 ="#fUI/UIWindow/Quest/icon6/7#";////美化2
var yun ="#fUI/UIWindow/Quest/icon7/0#";////红沙漏
var yun1 ="#fUI/UIWindow/Quest/icon7/10#";////红色圆
var yun2 ="#fUI/UIWindow/Quest/icon8/0#";////蓝指标
var yun3 ="#fUI/UIWindow/Quest/prob#";////机率获得
var yun4 ="#fUI/UIWindow/Quest/reward#";////奖励
var yun5 ="#fUI/UIWindow/Quest/summary#";////任务简洁
var yun6 ="#fUI/UIWindow/PartySearch2/BtPrev/mouseOver/0#";////左指标
var yun7 ="#fUI/UIWindow/PartySearch2/BtNext/mouseOver/0#";////右指标
var yun8 ="#fUI/UIWindow/MonsterBook/arrowLeft/normal/0#";////金左指标
var yun9 ="#fUI/UIWindow/MonsterBook/arrowRight/normal/0#";////金右指标
var yun12 ="#fUI/UIWindow/Megaphone/2#";////骷髅
var xiaoyun1 ="#fUI/UIWindow/AriantMatch/characterIcon/0#";////红方
var xiaoyun2 ="#fUI/UIWindow/AriantMatch/characterIcon/1#";////蓝方
var xiaoyun3 ="#fUI/UIWindow/AriantMatch/characterIcon/2#";////绿方
var xiaoyun4 ="#fUI/UIWindow/AriantMatch/characterIcon/3#";////黄方
var xiaoyun5 ="#fUI/UIWindow/AriantMatch/characterIcon/4#";////紫方
var xiaoyun6 ="#fUI/UIWindow/AriantMatch/characterIcon/5#";////橙方
var xiaoyun7 ="#fUI/UIWindow/Minigame/Common/btStart/mouseOver/0#";////开始
var xiaoyun8 ="#fUI/UIWindow/Minigame/Common/mark#";////冒险岛图标

var status = 0;

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
		var selStr = "\t  "+xiaoyun1+""+xiaoyun2+""+xiaoyun3+""+xiaoyun4+""+xiaoyun5+""+xiaoyun5+""+xiaoyun5+""+xiaoyun1+""+xiaoyun2+""+xiaoyun3+""+xiaoyun4+""+xiaoyun5+""+xiaoyun2+""+xiaoyun3+""+xiaoyun4+""+xiaoyun5+"\r\n" ;
		 selStr += "\t  "+xiaoyun1+"\t\t\t#e#r公测活动\t\t\t  "+xiaoyun5+"\r\n" ;
		 selStr += "\t "+xiaoyun1+""+xiaoyun2+""+xiaoyun3+""+xiaoyun4+""+xiaoyun5+""+xiaoyun5+""+xiaoyun6+""+xiaoyun1+""+xiaoyun2+""+xiaoyun3+""+xiaoyun4+""+xiaoyun5+""+xiaoyun2+""+xiaoyun3+""+xiaoyun4+""+xiaoyun5+"\r\n" ;
         selStr += "\t\t\t #L0##d"+zs+"公测活动玩法"+zs+"#l#k\r\n\r\n\t\t\t #d#L4#"+zs1+"活动地图传送"+zs1+"#l\r\n\r\n\t\t\t #d#L5#"+zs1+"收集活动排名"+zs1+"#l";

        cm.sendSimple(selStr);
    } else if (status == 1) {
        switch (selection) {
        case 0:
            cm.dispose();
            cm.openNpc(1500032,"gchdjs"); //双倍道具
            break;
        case 4:
            cm.dispose();
            cm.warp(130010000);
            break;
        case 5:
                     var conn = cm.getConnection();
			var sql = "select name,sjhd from characters where gm<=0 order by  sjhd desc limit 10;";
			var pstmt = conn.prepareStatement(sql);
			var result = pstmt.executeQuery();
			var text = "\t\t\t\t#e#d★ 公测收集活动排行 ★#k#n\r\n\r\n";
			text += "\t#e名次#n\t#e玩家昵称#n\t\t\t#e收集数量#n\t\t #e#n\r\n";
			for (var i = 1; i <= 10; i++) {
				if (!result.next()) {
					break;
				}
				if (i == 1) {
					text += "#r";
				} else if (i == 2) {
					text += "#g";
				} else if (i == 3) {
					text += "#b";
				}
				text += "\t " + i + "\t\t ";
				
				// 填充名字空格
				text += result.getString("name");
				for (var j = 16 - result.getString("name").getBytes().length; j > 0 ; j--) {
					text += " ";
				}
				text += "\t " + result.getString("sjhd");
				
				text += "\t\t\t #k";

				text += "\r\n";
			}
			result.close();
			pstmt.close();
			cm.sendOkS(text, 3);
			cm.dispose();
            break;
        case 6:
			var ii = cm.getItemInfo();	
			var toDrop = cm.getNewEquip(1190400); // 生成一个Equip类      
			toDrop.setEnhance(20)              
			toDrop.setStr(5); //装备力量
			toDrop.setDex(5); //装备敏捷
			toDrop.setInt(5); //装备智力
			toDrop.setLuk(5); //装备运气
			toDrop.setMatk(5); //物理攻击
			toDrop.setWatk(5); //魔法攻击 
			toDrop.setOwner("进击的少年");
			 cm.addFromDrop(toDrop);
			cm.sendOk("恭喜您获得 #r管理员送出的礼物#k 。");
									cm.worldSpouseMessage(0x2A, "[符号系统] : 恭喜 " + cm.getChar().getName() + " 领取BOSS符号.加油升级符号吧！");
        		    cm.dispose();
     			       break;
        case 0:
        case 7:
            cm.dispose();
            cm.openNpc(9310071, 2); //洗点卷轴
            break;
        case 8:
            cm.dispose();
            cm.openNpc(9900002, 24); //玩具商店
            break;
        case 9:
            cm.dispose();
            cm.openNpc(9110103); //骑宠商店
            break;
	case 10:
            cm.dispose();
            cm.openNpc(9110114); //破攻石头
            break;
	case 11:
            cm.dispose();
            cm.openNpc(9900002, 5); //一键潜能
            break;
	case 12:
            cm.dispose();
            cm.openNpc(9900002, 1301); //一键潜能
            break;
	case 13:
            cm.dispose();
            cm.openNpc(9270096, 13); //一键潜能
            break;
	case 14:
            cm.dispose();
            cm.openNpc(9900002, 1301); //一键潜能
            break;
	case 15:
            cm.dispose();
            cm.openNpc(9270096, 15); //一键潜能
            break;
        }
    }
}